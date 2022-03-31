package compiler;

import compiler.ParserClasses.*;
import compiler.token.Token_type;

import java.util.ArrayList;
import java.util.Currency;
import java.util.concurrent.PriorityBlockingQueue;

import compiler.token;

// all parse methods should be static
public class CMinusParser {
    public static void main(String[] args) {
        CMinusParser myParser = new CMinusParser("asdf");
        myParser.parseProgram();
    }

    CMinusScanner myScanner;
    static token nextToken;
    static token currentToken;

    public CMinusParser(String inFile){
        myScanner = new CMinusScanner(inFile);
        currentToken = token.getToken();
        nextToken = token.getToken();
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
        compoundStatement cmpd_stmt = parseCompoundStatement();
        
        return new FunDecl(id, type, params, cmpd_stmt);
    }

    private static VarDecl parseVarDecl(Token_type type, String id) throws parserErrorException {
        return null;
    }

    private static Param parseParam() throws parserErrorException {
        Param param = new Param();
        param.ID = currentToken.getData();
        //match INT
        matchToken(token.Token_type.INTEGER_TOKEN);
        //match [] if present
        if(nextToken.getType() == token.Token_type.OPEN_BRACKET_TOKEN){
            matchToken(token.Token_type.OPEN_BRACKET_TOKEN);
            return param;
        }
        else{
            return param;
        }
    }

    private static compoundStatement parseCompoundStatement() throws parserErrorException {
        return null;
        //
    }

    
}
