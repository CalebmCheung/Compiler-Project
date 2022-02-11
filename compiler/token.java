package compiler;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.Character;

public class token {
    public enum Token_type {
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
        OPEN_PAREN_TOKEN,
        CLOSE_PAREN_TOKEN,
        OPEN_BRACKET_TOKEN,
        CLOSE_BRACKET_TOKEN,
        OPEN_CURLY_TOKEN,
        CLOSE_CURLY_TOKEN,
        START_COMMENT_TOKEN,
        END_COMMENT_TOKEN,
        IDENTIFIER_TOKEN,
        NUMBER_TOKEN,
        LETTER_TOKEN,
        DIGIT_TOKEN,
        ERROR_TOKEN,
        EOF_TOKEN,
        DEFAULT_TOKEN;
    }

    public enum State {
        START,
        INCOMMENT,
        INNUM,
        INID,
        INCOMPARE,
        INEQUALS,
        INLESSTHAN,
        INGREATERTHAN,
        INNOTEQUALS,
        INSLASH,
        INCOMMENTEND,
        DONE

    }

    private static InputStream is = null;
    private static InputStreamReader isr = null;
    private static BufferedReader br = null;
    private static token nextToken;
    private static token tokenToPrint;
    private static Token_type tokenType;
    private static String tokenData;

    public static void CMinusScanner (String file) {
        try{
            is = new FileInputStream(file);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            nextToken = getToken();
            while (nextToken.getType() != Token_type.EOF_TOKEN){
                tokenToPrint = getNextToken();
                if(tokenToPrint.getType() != Token_type.DEFAULT_TOKEN){
                    System.out.println(tokenToPrint.getType() + " " + tokenData);
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }
        catch(IOException e){
            System.out.println("IO Exception Raised From BufferedReader (.mark(1))");
        }
    }

    public static char getNextChar(){
        try{
            br.mark(1);
            char ret = (char)br.read();
            return ret;
        }
        catch(IOException e){
            System.out.println("IO Exception Raised From BufferedReader (br.read)");
            return (char)-1;
        }
    }

    public static void unGetNextChar(){
        try{
            br.reset();
        }
        catch(IOException e){
            System.out.println("IO Exception Raised From BufferedReader (br.reset)");
        }
    }

    public token (Token_type type){
        this(type, null);
    }

    public token (Token_type type, String data){
        tokenType = type;
        tokenData = data;
    }
    
    public Token_type getType() {
        return this.tokenType;
    }

    public Object getData(){
        return this.tokenData;
    }

    public static token viewNextToken () {
        return nextToken;
    }

    public static token getNextToken () {
        token returnToken = nextToken;
        if (nextToken.getType() != Token_type.EOF_TOKEN)
            nextToken = getToken();
        return returnToken;
    }

    public static token getToken(){
        int tokenStringIndex = 0;
        String tokenString = "";
        token currentToken = new token(Token_type.DEFAULT_TOKEN);
        State state = State.START;
        boolean save;

        while (state != State.DONE){
            char c = getNextChar();
            switch (state) {
                case START:
                    if(Character.isDigit(c)){
                        state = State.INNUM;
                        tokenString = tokenString.concat(Character.toString(c));
                    }
                    else if(Character.isLetter(c)){
                        state = State.INID;
                        tokenString = tokenString.concat(Character.toString(c));
                    }
                    else if(c == '='){
                        state = State.INEQUALS;
                        tokenString = tokenString.concat(Character.toString(c));
                    }
                    else if(c == '<'){
                        state = State.INLESSTHAN;
                        tokenString = tokenString.concat(Character.toString(c));
                    }
                    else if(c == '>'){
                        state = State.INGREATERTHAN;
                        tokenString = tokenString.concat(Character.toString(c));
                    }
                    else if(c == '!'){
                        state = State.INNOTEQUALS;
                        tokenString = tokenString.concat(Character.toString(c));
                    }
                    else if(c == '/'){
                        state = State.INSLASH;
                    }
                    else if(c == ' ' || c == '\t' || c == '\n'){
                        state = State.DONE;
                        break;
                    }
                    else{
                        state = State.DONE;
                        switch(c){
                            case (char)-1:
                                currentToken = new token(Token_type.EOF_TOKEN);
                                break;

                            case '+':
                                currentToken = new token(Token_type.PLUS_TOKEN);
                                break;

                            case '-':
                                currentToken = new token(Token_type.MINUS_TOKEN);
                                break;

                            case '*':
                                currentToken = new token(Token_type.MULTIPLY_TOKEN);
                                break;

                            case ';':
                                currentToken = new token(Token_type.SEMI_COLON_TOKEN);    
                                break;

                            case ',':
                                currentToken = new token(Token_type.COMMA_TOKEN);
                                break;

                            case '(':
                                currentToken = new token(Token_type.OPEN_PAREN_TOKEN);
                                break;

                            case ')':
                                currentToken = new token(Token_type.CLOSE_PAREN_TOKEN);
                                break;

                            case '[':
                                currentToken = new token(Token_type.OPEN_BRACKET_TOKEN);
                                break;

                            case ']':
                                currentToken = new token(Token_type.CLOSE_BRACKET_TOKEN);
                                break;

                            case '{':
                                currentToken = new token(Token_type.OPEN_CURLY_TOKEN);
                                break;

                            case '}':
                                currentToken = new token(Token_type.CLOSE_CURLY_TOKEN);
                                break;
                            
                            default:
                                currentToken = new token(Token_type.ERROR_TOKEN);
                                break;
                        }
                    }
                    break;
                    
                case INNUM:
                    if(c < 48 || c > 57){
                        state = State.DONE;
                        unGetNextChar();
                        currentToken = new token(Token_type.NUMBER_TOKEN, tokenString);
                        tokenString = "";
                    }
                    else{
                        tokenString = tokenString.concat(Character.toString(c));
                    }
                    break;
                    
                case INID:
                    // check ASCII codes
                    if((c < 65 || c > 90) && (c < 97 || c > 122)){
                        state = State.DONE;
                        unGetNextChar();
                        currentToken = new token(Token_type.IDENTIFIER_TOKEN, tokenString);
                        tokenString = "";
                    }
                    else{
                        tokenString = tokenString.concat(Character.toString(c));
                    }
                    break;
                
                case INEQUALS:
                    currentToken = new token(Token_type.ERROR_TOKEN, "ERROR");
                    break;

                case INLESSTHAN:
                    currentToken = new token(Token_type.ERROR_TOKEN, "ERROR");
                    break;
                
                case INGREATERTHAN:
                    currentToken = new token(Token_type.ERROR_TOKEN, "ERROR");
                    break;
                
                case INNOTEQUALS:
                    currentToken = new token(Token_type.ERROR_TOKEN, "ERROR");
                    break;

                case INCOMMENT:
                    currentToken = new token(Token_type.ERROR_TOKEN, "ERROR");
                    if(c == '*'){
                        state = State.INCOMMENTEND;
                    }
                    break;

                case DONE:
                    break;
                
                default:
                    currentToken = new token(Token_type.ERROR_TOKEN, "ERROR");
                    break;
            }
        }
        return currentToken;
    }
}
