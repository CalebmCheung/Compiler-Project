package compiler;

import compiler.ParserClasses.*;
import compiler.token.Token_type;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

import compiler.token;

// all parse methods should be static
public class CMinusParser {
    token nextToken;
    token currenToken;
    
    private void advanceToken(){
        //move token pointer to next token

    }

    private void matchToken(Token_type type){

    }
    
    private Program parseProgram(){
        Program p = new Program();
        p.decList.add(parseDecl());
        while(nextToken.getType() != token.Token_type.EOF_TOKEN){
            p.decList.add(parseDecl());
        }

        return p;
    }

    private Decl parseDecl(){
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

    private Decl parseDeclPrime(Token_type type, String id){
        if(nextToken.getType() == token.Token_type.OPEN_PAREN_TOKEN){
            return parseFunDecl(type, id);
        }
        else{
            return parseVarDecl(type, id);
        }
    }

    private FunDecl parseFunDecl(Token_type type, String id){
        // match open paren
        matchToken(token.Token_type.OPEN_PAREN_TOKEN);

        // create param
        ArrayList<Param> params = new ArrayList<Param>();
        // check to see if not void

        
        while(nextToken.getType() != token.Token_type.CLOSE_PAREN_TOKEN){
            params.add(parseParam());

        }
        
        // match close paren
        matchToken(token.Token_type.CLOSE_PAREN_TOKEN);

        // create compound statement
        compoundStatement cmpd_stmt = parseCompoundStatement();
        
        return new FunDecl(id, type, params, cmpd_stmt);
    }

    private VarDecl parseVarDecl(Token_type type, String id){
        return null;
    }

    private Param parseParam(){
        Param param = new Param();
        param.ID = currenToken.getData();
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

    private compoundStatement parseCompoundStatement(){
        //
    }

    
}
