package compiler;

import compiler.ParserClasses.*;
import compiler.token.Token_type;

import java.util.ArrayList;

/*

program
    decl1
        ob1
            e1
            e2
    decl2
        e2
            var
            num

*/
// all parse methods should be static
public class CMinusParser {
    public static void main(String[] args) throws parserErrorException {
        CMinusParser myParser = new CMinusParser("asdf");
    }

    CMinusScanner myScanner;
    static token nextToken;
    static token currentToken;
    Program p;

    public CMinusParser(String inFile) throws parserErrorException {
        myScanner = new CMinusScanner(inFile);
        currentToken = token.getToken();
        nextToken = token.getToken();
        p = parseProgram();
    }

    private static void advanceToken() {
        // move token pointer to next token
        currentToken = nextToken;
        nextToken = token.getToken();
    }

    private static void matchToken(Token_type type) throws parserErrorException {
        // check to see if token types match
        if (currentToken.getType() != type) {
            throw new parserErrorException("Token type does not match");
        } else {
            advanceToken();
        }
    }

    private static Program parseProgram() throws parserErrorException {
        Program p = new Program();
        p.decList.add(parseDecl());
        while (nextToken.getType() != token.Token_type.EOF_TOKEN) {
            p.decList.add(parseDecl());
        }

        return p;
    }

    private static Decl parseDecl() throws parserErrorException {
        Token_type type;
        String id;
        if (nextToken.getType() == token.Token_type.VOID_TOKEN) {
            type = Token_type.VOID_TOKEN;
            advanceToken();
            id = nextToken.getData();
            matchToken(token.Token_type.IDENTIFIER_TOKEN);
            return parseFunDecl(type, id);
        } else {
            matchToken(token.Token_type.INTEGER_TOKEN);
            type = Token_type.INTEGER_TOKEN;
            id = nextToken.getData();
            matchToken(token.Token_type.IDENTIFIER_TOKEN);
            return parseDeclPrime(type, id);
        }
    }

    private static Decl parseDeclPrime(Token_type type, String id) throws parserErrorException {
        if (nextToken.getType() == token.Token_type.OPEN_PAREN_TOKEN) {
            return parseFunDecl(type, id);
        } else {
            return parseVarDecl(type, id);
        }
    }

    private static FunDecl parseFunDecl(Token_type type, String id) throws parserErrorException {
        // match open paren
        matchToken(token.Token_type.OPEN_PAREN_TOKEN);

        // create param
        ArrayList<Param> params = new ArrayList<Param>();

        // check to see if not void
        if (nextToken.getType() != token.Token_type.VOID_TOKEN) {
            params.add(parseParam());
            while (nextToken.getType() != token.Token_type.COMMA_TOKEN) {
                params.add(parseParam());
            }
            matchToken(token.Token_type.CLOSE_PAREN_TOKEN);
        } else {
            advanceToken();
            matchToken(token.Token_type.CLOSE_PAREN_TOKEN);
        }

        // create compound statement

        compoundStatement cmpd_stmt = parseCompoundStatement();

        return new FunDecl(id, type, params, cmpd_stmt);
    }

    private static VarDecl parseVarDecl(Token_type type, String id) throws parserErrorException {
        if (currentToken.getType() == token.Token_type.SEMI_COLON_TOKEN) {
            advanceToken();
            // type, id, hasBrackets, number
            return new VarDecl(type, id, false, -1);
        } else {
            matchToken(token.Token_type.OPEN_BRACKET_TOKEN);
            int num = Integer.parseInt(nextToken.getData());
            matchToken(token.Token_type.NUMBER_TOKEN);
            matchToken(token.Token_type.CLOSE_BRACKET_TOKEN);
            matchToken(token.Token_type.SEMI_COLON_TOKEN);

            return new VarDecl(type, id, true, num);
        }
    }

    private static Param parseParam() throws parserErrorException {
        // match INT
        matchToken(token.Token_type.INTEGER_TOKEN);
        String id = currentToken.getData();
        matchToken(token.Token_type.IDENTIFIER_TOKEN);
        // match [] if present
        if (nextToken.getType() == token.Token_type.OPEN_BRACKET_TOKEN) {
            matchToken(token.Token_type.OPEN_BRACKET_TOKEN);
            matchToken(token.Token_type.CLOSE_BRACKET_TOKEN);
            return new Param(id, true);
        } else {
            return new Param(id, false);
        }
    }

    private static compoundStatement parseCompoundStatement() throws parserErrorException {
        matchToken(token.Token_type.OPEN_CURLY_TOKEN);
        ArrayList<VarDecl> localDecls = parseLocalDecl();
        ArrayList<Statement> stmtList = parseStatementList();
        matchToken(token.Token_type.CLOSE_CURLY_TOKEN);
        return new compoundStatement(localDecls, stmtList);
    }

    private static ArrayList<VarDecl> parseLocalDecl() throws parserErrorException {
        ArrayList<VarDecl> varDecls = new ArrayList<VarDecl>();
        Token_type type;
        String id;
        while (currentToken.getType() == token.Token_type.INTEGER_TOKEN) {
            matchToken(token.Token_type.INTEGER_TOKEN);
            type = Token_type.INTEGER_TOKEN;
            id = nextToken.getData();
            matchToken(token.Token_type.IDENTIFIER_TOKEN);
            varDecls.add(parseVarDecl(type, id));
        }
        return varDecls;
    }

    private static ArrayList<Statement> parseStatementList() throws parserErrorException {
        ArrayList<Statement> stmt_list = new ArrayList<Statement>();
        while (currentToken.getType() == token.Token_type.IDENTIFIER_TOKEN
                || currentToken.getType() == token.Token_type.NUMBER_TOKEN ||
                currentToken.getType() == token.Token_type.OPEN_PAREN_TOKEN
                || currentToken.getType() == token.Token_type.IF_TOKEN ||
                currentToken.getType() == token.Token_type.WHILE_TOKEN
                || currentToken.getType() == token.Token_type.RETURN_TOKEN ||
                currentToken.getType() == token.Token_type.INTEGER_TOKEN) {

            stmt_list.add(parseStatement());
        }
        return stmt_list;
    }

    private static Statement parseStatement() throws parserErrorException {
        if (currentToken.getType() == token.Token_type.IDENTIFIER_TOKEN ||
                currentToken.getType() == token.Token_type.NUMBER_TOKEN ||
                currentToken.getType() == token.Token_type.OPEN_PAREN_TOKEN ||
                currentToken.getType() == token.Token_type.SEMI_COLON_TOKEN) {
            return parseExprStatement();
        } else if (currentToken.getType() == token.Token_type.IF_TOKEN) {
            return parseSelectionStatement();
        } else if (currentToken.getType() == token.Token_type.WHILE_TOKEN) {
            return parseIterationStatement();
        } else if (currentToken.getType() == token.Token_type.INTEGER_TOKEN) {
            return parseCompoundStatement();
        } else if (currentToken.getType() == token.Token_type.RETURN_TOKEN) {
            return parseReturnStatement();
        } else {
            throw new parserErrorException("Token of type: " + currentToken.getType() + ", cannot be a statement");
        }
    }

    private static exprStatement parseExprStatement() throws parserErrorException {
        Expr expr = null;
        if (currentToken.getType() != token.Token_type.SEMI_COLON_TOKEN) {
            expr = parseExpression();
        }
        matchToken(token.Token_type.SEMI_COLON_TOKEN);
        return new exprStatement(expr);
    }

    private static selectionStatement parseSelectionStatement() throws parserErrorException {
        matchToken(token.Token_type.IF_TOKEN);
        matchToken(token.Token_type.OPEN_PAREN_TOKEN);
        Expr expr = parseExpression();
        matchToken(token.Token_type.CLOSE_PAREN_TOKEN);
        Statement stmt = parseStatement();
        matchToken(token.Token_type.OPEN_BRACKET_TOKEN);
        Statement eStmt = parseStatement();
        matchToken(token.Token_type.CLOSE_BRACKET_TOKEN);
        return new selectionStatement(expr, stmt, eStmt);
    }

    private static iterationStatement parseIterationStatement() throws parserErrorException {
        matchToken(token.Token_type.WHILE_TOKEN);
        matchToken(token.Token_type.OPEN_PAREN_TOKEN);
        Expr expr = parseExpression();
        matchToken(token.Token_type.CLOSE_PAREN_TOKEN);
        Statement stmt = parseStatement();

        return new iterationStatement(expr, stmt);
    }

    private static returnStatement parseReturnStatement() throws parserErrorException {
        Expr expr = null;
        matchToken(token.Token_type.RETURN_TOKEN);
        if (currentToken.getType() != token.Token_type.SEMI_COLON_TOKEN) {
            expr = parseExpression();
        }
        matchToken(token.Token_type.SEMI_COLON_TOKEN);
        return new returnStatement(expr);
    }

    private static Expr parseExpression() throws parserErrorException {
        if (currentToken.getType() == token.Token_type.IDENTIFIER_TOKEN) {
            String ID = currentToken.getData();
            advanceToken();
            return parseExpressionPrime(ID);
        } else if (currentToken.getType() == token.Token_type.NUMBER_TOKEN) {
            // create new num expression to pass down
            NumExpr nExpr = new NumExpr(Integer.parseInt(currentToken.getData()));
            advanceToken();
            return parseSimpleExpressionPrime(nExpr);
        } else {
            matchToken(token.Token_type.OPEN_PAREN_TOKEN);
            Expr expr = parseExpression();
            matchToken(token.Token_type.CLOSE_PAREN_TOKEN);
            return parseSimpleExpressionPrime(expr);
        }
    }

    private static Expr parseExpressionPrime(String ID) throws parserErrorException {
        if (currentToken.getType() == token.Token_type.ASSIGN_TOKEN) {
            advanceToken();
            VarExpr vExpr = new VarExpr(ID, null);
            Expr rhs = parseExpression();
            return new AssignExpr(vExpr, rhs);
        } else if (currentToken.getType() == token.Token_type.OPEN_BRACKET_TOKEN) {
            // TODO: Ask Dr.G about this 
            advanceToken();
            Expr e = parseExpression();
            matchToken(token.Token_type.CLOSE_BRACKET_TOKEN);
            VarExpr vExpr = new VarExpr(ID, e);
            Expr exprPrime = parseExpressionPrimePrime(vExpr);
        } else if (currentToken.getType() == token.Token_type.OPEN_PAREN_TOKEN) {
            advanceToken();
            ArrayList<Expr> args = parseArgs();
            matchToken(token.Token_type.CLOSE_PAREN_TOKEN);
            CallExpr cExpr = new CallExpr(ID, args);
            return parseSimpleExpressionPrime(cExpr);
        } else if (currentToken.getType() == token.Token_type.IDENTIFIER_TOKEN ||
                currentToken.getType() == token.Token_type.NUMBER_TOKEN ||
                currentToken.getType() == token.Token_type.OPEN_PAREN_TOKEN) {
            return parseSimpleExpressionPrime(null);
        }
        throw new parserErrorException("incorrect token for parseExpressionPrime()");
    }

    private static Expr parseExpressionPrimePrime(VarExpr vExpr) throws parserErrorException {
        // TODO ask Gallagher
        return null;
    }

    private static Expr parseSimpleExpressionPrime(Expr expr) throws parserErrorException {
        Expr lhs = parseAdditiveExpressionPrime(expr);
        Expr rhs;
        Token_type op;
        if (currentToken.getType() == Token_type.LESS_THAN_TOKEN
                || currentToken.getType() == Token_type.LESS_THAN_OR_EQUAL_TOKEN
                || currentToken.getType() == Token_type.GREATER_THAN_TOKEN
                || currentToken.getType() == Token_type.GREATER_THAN_OR_EQUAL_TOKEN
                || currentToken.getType() == Token_type.EQUALITY_TOKEN
                || currentToken.getType() == Token_type.NONEQUALITY_TOKEN) {
            op = currentToken.getType();
            advanceToken();
            rhs = parseAdditiveExpression();
            return new BinaryExpr(lhs, rhs, op);
        }
        return lhs;
    }

    private static Expr parseAdditiveExpression() throws parserErrorException {
        Expr lhs = parseTerm();
        Expr rhs;
        Token_type op;

        while (nextToken.getType() == token.Token_type.PLUS_TOKEN
                || nextToken.getType() == token.Token_type.MINUS_TOKEN) {
            op = currentToken.getType();
            advanceToken();
            rhs = parseTerm();
            lhs = new BinaryExpr(lhs, rhs, op);
        }
        return lhs;
    }

    private static Expr parseAdditiveExpressionPrime(Expr expr) throws parserErrorException {
        Expr lhs = parseTermPrime(expr);
        Expr rhs;
        Token_type op;

        while (nextToken.getType() == token.Token_type.PLUS_TOKEN
                || nextToken.getType() == token.Token_type.MINUS_TOKEN) {
            op = currentToken.getType();
            advanceToken();
            rhs = parseTerm();
            lhs = new BinaryExpr(lhs, rhs, op);
        }
        return lhs;
    }

    private static Expr parseTerm() throws parserErrorException {
        Expr lhs = parseFactor();
        Expr rhs;
        Token_type op;

        while (nextToken.getType() == token.Token_type.MULTIPLY_TOKEN
                || nextToken.getType() == token.Token_type.DIVIDE_TOKEN) {
            op = currentToken.getType();
            advanceToken();
            rhs = parseFactor();
            lhs = new BinaryExpr(lhs, rhs, op);
        }
        return lhs;

    }

    private static Expr parseTermPrime(Expr expr) throws parserErrorException {
        Expr lhs = expr;
        Expr rhs;
        Token_type op;

        while (nextToken.getType() == token.Token_type.MULTIPLY_TOKEN
                || nextToken.getType() == token.Token_type.DIVIDE_TOKEN) {
            op = currentToken.getType();
            advanceToken();
            rhs = parseFactor();
            lhs = new BinaryExpr(lhs, rhs, op);
        }
        return lhs;

    }

    private static Expr parseFactor() throws parserErrorException {
        if(currentToken.getType() == token.Token_type.OPEN_PAREN_TOKEN){
            advanceToken();
            Expr expr = parseExpression();
            matchToken(token.Token_type.CLOSE_PAREN_TOKEN);
            return expr;
        }
        else if(currentToken.getType() == token.Token_type.IDENTIFIER_TOKEN){
            String ID = currentToken.getData();
            advanceToken();
            return parseVarCall(ID);
        }
        else {
            matchToken(token.Token_type.NUMBER_TOKEN);
            int num = Integer.parseInt(currentToken.getData());
            return new NumExpr(num);
        }
    }

    private static Expr parseVarCall(String ID) throws parserErrorException {
        if(currentToken.getType() == token.Token_type.OPEN_PAREN_TOKEN){
            advanceToken();
            ArrayList<Expr> args = parseArgs();
            matchToken(token.Token_type.CLOSE_PAREN_TOKEN);
            return new CallExpr(ID, args);
        }
        else if(currentToken.getType() == token.Token_type.OPEN_BRACKET_TOKEN){
            advanceToken();
            Expr expr = parseExpression();
            matchToken(token.Token_type.CLOSE_BRACKET_TOKEN);
            return new VarExpr(ID, expr);
        }
        else{
            throw new parserErrorException("Error in parseVarCall");
        }
    }

    private static ArrayList<Expr> parseArgs() throws parserErrorException {
        if(currentToken.getType() == token.Token_type.IDENTIFIER_TOKEN ||
                currentToken.getType() == token.Token_type.NUMBER_TOKEN ||
                currentToken.getType() == token.Token_type.OPEN_PAREN_TOKEN) {
            return parseArgList();
        }
        else{
            return null;
        }
    }

    private static ArrayList<Expr> parseArgList() throws parserErrorException {
        ArrayList<Expr> argList = new ArrayList<Expr>();
        argList.add(parseExpression());
        while(currentToken.getType() == Token_type.COMMA_TOKEN){
            argList.add(parseExpression());
        }
        return argList;
    }
}
