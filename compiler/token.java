package compiler;

import java.io.FileReader;
import java.io.PushbackReader;
import java.lang.Character;

public class token {
    public enum Token {
        IF_TOKEN,
        ELSE_TOKEN,
        RETURN_TOKEN,
        VOID_TOKEN,
        WHILE_TOKEN,
        PLUS_TOKEN,
        MINUS_TOKEN,
        MULTIPLY_TOKEN,
        DIVIDE_TOKEN,
        LESS_THAN_TOKEN,
        LESS_THAN_OR_EQUAL_TOKEN,
        GREATER_THAN_TOKEN,
        GREATER_THAN_OR_EQUAL_TOKEN,
        EQUALITY_TOKEN,
        NONEQUALITY_TOKEN,
        SEMI_COLON_TOKEN,
        COMMA_TOKEN,
        OPEN_PARENT_TOKEN,
        CLOSE_PARENT_TOKEN,
        OPEN_BRACKET_TOKEN,
        CLOSE_BRACKET_TOKEN,
        OPEN_CURLY_TOKEN,
        CLOSE_CURLY_TOKEN,
        START_COMMENT_TOKEN,
        END_COMMENT_TOKEN,
        IDENTIFIER_TOKEN,
        NUMBER_TOKEN,
        LETTER_TOKEN,
        INT_TOKEN,
        DOUBLE_TOKEN,
        ERROR_TOKEN
    }

    public enum State {
        START,
        INCOMMENT,
        INNUM,
        INID,
        INCOMPARE,
        DONE
    }

    private PushbackReader pbr = new PushbackReader(new FileReader("File"));
    private Token nextToken;
    private Token tokenType;
    private Object tokenData;

    public static char getNextChar(){
        return 'a';
    }

    /*
    public CMinusScanner (BufferedReader file) {
        inFile = file;
        nextToken = getToken();
    }
    
    public token getNextToken () {
        token returnToken = nextToken;
        if (nextToken.getType() != token.Token.EOF_TOKEN)
            nextToken = getToken();
        return returnToken;
    }

    public token viewNextToken () {
        return nextToken;
    }
    
    public token (Token token){
        this (token, null);
    }
    */

    public token (Token type, Object data){
        tokenType = type;
        tokenData = data;
    }

    public static Token getToken(){
        int tokenStringIndex = 0;
        Token currentToken;
        State state = State.START;
        boolean save;

        while (state != State.DONE){
            char c = getNextChar();
            switch (state) {
                case START:
                    if(Character.isDigit(c)){
                        state = State.INNUM;
                    }
                    else if(Character.isLetter(c)){
                        state = State.INID;
                    }
                    else if(c == '=' || c == '<' || c == '>' || C == '!'){
                        state = State.INCOMPARE;
                    }
                    else if(c == ' ' || c == '\t' || c == '\n'){
                        break;
                    }
                    else if(c == '/'){
                        state = State.INCOMMENT;
                    }
                    else{
                        state = State.DONE;
                        switch(c){
                            case (char)-1:

                        }
                    }

                    
                    break;
            
                default:
                    break;
            }
        }

        

        return null;
    }
}
