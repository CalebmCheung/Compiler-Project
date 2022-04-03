package compiler;

import compiler.ParserClasses.*;
import compiler.token.Token_type;

import java.util.ArrayList;
import java.util.Currency;
import java.util.concurrent.PriorityBlockingQueue;

import javax.lang.model.util.ElementScanner6;

import org.omg.PortableServer.AdapterActivator;

import compiler.token;

// all parse methods should be static
public class CMinusParser {
    public static void main(String[] args) throws parserErrorException {
        CMinusParser myParser = new CMinusParser("asdf");
    }

    CMinusScanner myScanner;
    static token nextToken;
    static token currentToken;

    public CMinusParser(String inFile) throws parserErrorException{
        myScanner = new CMinusScanner(inFile);
        currentToken = token.getToken();
        nextToken = token.getToken();
        Program p = parseProgram();
    }

    private static void advanceToken(){
        //move token pointer to next token
        currentToken = nextToken;
        nextToken = token.getToken();
    }

    private static void matchToken(Token_type type) throws parserErrorException {
        // check to see if token types match
        if(currentToken.getType() != type){
            throw new parserErrorException("Token type does not match");
        }
        else {
            advanceToken();
        }
    }
    
    private static Program parseProgram() throws parserErrorException {
        Program p = new Program();
        p.decList.add(parseDecl());
        while(nextToken.getType() != token.Token_type.EOF_TOKEN){
            p.decList.add(parseDecl());
        }

        return p;
    }

    private static Decl parseDecl() throws parserErrorException {
        Token_type type;
        String id;
        if(nextToken.getType() == token.Token_type.VOID_TOKEN){
            type = Token_type.VOID_TOKEN;
            advanceToken();
            id  = nextToken.getData();
            matchToken(token.Token_type.IDENTIFIER_TOKEN);
            return parseFunDecl(type, id);
        }
        else {
            matchToken(token.Token_type.INTEGER_TOKEN);
            type = Token_type.INTEGER_TOKEN;
            id  = nextToken.getData();
            matchToken(token.Token_type.IDENTIFIER_TOKEN);
            return parseDeclPrime(type, id);
        }
    }

    private static Decl parseDeclPrime(Token_type type, String id) throws parserErrorException {
        if(nextToken.getType() == token.Token_type.OPEN_PAREN_TOKEN){
            return parseFunDecl(type, id);
        }
        else{
            return parseVarDecl(type, id);
        }
    }

    private static FunDecl parseFunDecl(Token_type type, String id) throws parserErrorException {
        // match open paren
        matchToken(token.Token_type.OPEN_PAREN_TOKEN);

        // create param
        ArrayList<Param> params = new ArrayList<Param>();
        
        // check to see if not void
        if(nextToken.getType() != token.Token_type.VOID_TOKEN){
            params.add(parseParam());
            while(nextToken.getType() != token.Token_type.COMMA_TOKEN){
                params.add(parseParam());
            }
            matchToken(token.Token_type.CLOSE_PAREN_TOKEN);
        }
        else{
            advanceToken();
            matchToken(token.Token_type.CLOSE_PAREN_TOKEN);
        }

        // create compound statement
        ArrayList<compoundStatement> cmpd_stmt = parseCompoundStatement();
        
        return new FunDecl(id, type, params, cmpd_stmt);
    }

    private static VarDecl parseVarDecl(Token_type type, String id) throws parserErrorException {
        if(currentToken.getType() == token.Token_type.SEMI_COLON_TOKEN){
            advanceToken();
            // type, id, hasBrackets, number
            return new VarDecl(type, id, false, -1);
        }
        else{
            matchToken(token.Token_type.OPEN_BRACKET_TOKEN);
            int num = Integer.parseInt(nextToken.getData());
            matchToken(token.Token_type.NUMBER_TOKEN);
            matchToken(token.Token_type.CLOSE_BRACKET_TOKEN);
            matchToken(token.Token_type.SEMI_COLON_TOKEN);

            return new VarDecl(type, id, true, num);
        }
    }

    private static Param parseParam() throws parserErrorException {
        //match INT
        matchToken(token.Token_type.INTEGER_TOKEN);
        String id = currentToken.getData();
        matchToken(token.Token_type.IDENTIFIER_TOKEN);
        //match [] if present
        if(nextToken.getType() == token.Token_type.OPEN_BRACKET_TOKEN){
            matchToken(token.Token_type.OPEN_BRACKET_TOKEN);
            matchToken(token.Token_type.CLOSE_BRACKET_TOKEN);
            return new Param(id, true);
        }
        else{
            return new Param(id, false);
        }
    }

    private static ArrayList<compoundStatement> parseCompoundStatement() throws parserErrorException {
        ArrayList<compoundStatement> cmpnd_stmt = new ArrayList<compoundStatement>();
        while(currentToken.getType() == token.Token_type.INTEGER_TOKEN){
            ArrayList<Decl> localDecls = parseLocalDecl();
            ArrayList<Statement> stmtList = parseStatementList();
            cmpnd_stmt.add(new compoundStatement(localDecls, stmtList));
        }
        return cmpnd_stmt;
    }

    private static ArrayList<Decl> parseLocalDecl() throws parserErrorException {
        ArrayList<Decl> varDecls = new ArrayList<Decl>();
        while(currentToken.getType() == token.Token_type.INTEGER_TOKEN){
            varDecls.add(parseDecl());
        }
        return varDecls;
    }

    private static ArrayList<Statement> parseStatementList() throws parserErrorException{
        ArrayList<Statement> stmt_list = new ArrayList<Statement>();
        while(currentToken.getType() == token.Token_type.IDENTIFIER_TOKEN || currentToken.getType() == token.Token_type.NUMBER_TOKEN ||
                currentToken.getType() == token.Token_type.OPEN_PAREN_TOKEN || currentToken.getType() == token.Token_type.IF_TOKEN ||
                currentToken.getType() == token.Token_type.WHILE_TOKEN || currentToken.getType() == token.Token_type.RETURN_TOKEN ||
                currentToken.getType() == token.Token_type.INTEGER_TOKEN) {
                    
            stmt_list.add(parseStatement());
        }
        return stmt_list;
    }

    private static Statement parseStatement() throws parserErrorException{
        if(currentToken.getType() == token.Token_type.IDENTIFIER_TOKEN || 
            currentToken.getType() == token.Token_type.NUMBER_TOKEN ||
            currentToken.getType() == token.Token_type.OPEN_PAREN_TOKEN ||
            currentToken.getType() == token.Token_type.SEMI_COLON_TOKEN){
            return parseExprStatement();
        }
        else if (currentToken.getType() == token.Token_type.IF_TOKEN){
            return parseSelectionStatement();
        }
        else if (currentToken.getType() == token.Token_type.WHILE_TOKEN){
            return parseIterationStatement();
        }
        else if(currentToken.getType() == token.Token_type.INTEGER_TOKEN){
            return parseCompoundStatement();
        }
        else if(currentToken.getType() == token.Token_type.RETURN_TOKEN){
            return parseReturnStatement();
        }
        else{
            throw new parserErrorException("Token of type: " + currentToken.getType() + ", cannot be a statement");
        }
    }

    private static exprStatement parseExprStatement() throws parserErrorException {
        Expr expr = null;
        if(currentToken.getType() != token.Token_type.SEMI_COLON_TOKEN){
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

    private static iterationStatement parseIterationStatement() throws parserErrorException{
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
        if(currentToken.getType() != token.Token_type.SEMI_COLON_TOKEN){
            expr = parseExpression();
        }
        matchToken(token.Token_type.SEMI_COLON_TOKEN);
        return new returnStatement(expr);
    }

    private static Expr parseExpression() throws parserErrorException{
        if(currentToken.getType() == token.Token_type.IDENTIFIER_TOKEN){
            matchToken(token.Token_type.IDENTIFIER_TOKEN);
        }
        else if(currentToken.getType() == token.Token_type.NUMBER_TOKEN){
            matchToken(token.Token_type.NUMBER_TOKEN);
        }
        else if(currentToken.getType() == token.Token_type.OPEN_PAREN_TOKEN){
            matchToken(token.Token_type.OPEN_PAREN_TOKEN);
        }
        return null;
    }
}
