package compiler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.Character;

public class token {
    public enum Token_type {
        ASSIGN_TOKEN,
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
        DEFAULT_TOKEN,
        NOT_TOKEN,
        INTEGER_TOKEN;
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
        INNOT,
        INSLASH,
        INCOMMENTEND,
        DONE,
        INSTAR

    }

    //
    private Token_type tokenType;
    private String tokenData;
    
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

    public String getData(){
        return this.tokenData;
    }

    public static token getToken(){
        boolean save = true;
        int tokenStringIndex = 0;
        String tokenString = "";
        token currentToken = new token(Token_type.DEFAULT_TOKEN);
        State state = State.START;

        while (state != State.DONE){
            char c = CMinusScanner.getNextChar();
            switch (state) {
                case START:
                    //Multi-character tokens
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
                        state = State.INNOT;
                        tokenString = tokenString.concat(Character.toString(c));
                    }
                    else if(c == '/'){
                        state = State.INSLASH;
                        tokenString = tokenString.concat(Character.toString(c));
                    }
                    else if(c == '*'){
                        state = State.INSTAR;
                        tokenString = tokenString.concat(Character.toString(c));
                    }
                    else if(c == ' ' || c == '\t' || c == '\r' || c == '\n'){
                        state = State.DONE;
                        break;
                    }
                    
                    else{
                        //Single special symbols
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
                                currentToken = new token(Token_type.ERROR_TOKEN, Character.toString(c));
                                break;
                        }
                    }
                    break;
                    
                case INNUM:
                    if(!Character.isDigit(c)){
                        state = State.DONE;
                        CMinusScanner.unGetNextChar();
                        currentToken = new token(Token_type.NUMBER_TOKEN, tokenString);
                        tokenString = "";
                    }
                    else{
                        tokenString = tokenString.concat(Character.toString(c));
                    }
                    break;
                    
                case INID:
                    // check ASCII codes to see if digit or not
                    if((c < 65 || c > 90) && (c <97 || c > 122)){
                        state = State.DONE;
                        CMinusScanner.unGetNextChar();
                        //Check for keyword
                        if(tokenString.equals("if")){
                            currentToken = new token(Token_type.IF_TOKEN);
                        }
                        else if(tokenString.equals("else")){
                            currentToken = new token(Token_type.ELSE_TOKEN);
                        }
                        else if(tokenString.equals("return")){
                            currentToken = new token(Token_type.RETURN_TOKEN);
                        }
                        else if(tokenString.equals("void")){
                            currentToken = new token(Token_type.VOID_TOKEN);
                        }
                        else if(tokenString.equals("while")){
                            currentToken = new token(Token_type.WHILE_TOKEN);
                        }
                        else if(tokenString.equals("int")){
                            currentToken = new token(Token_type.INTEGER_TOKEN);
                        }
                        else {
                            currentToken = new token(Token_type.IDENTIFIER_TOKEN, tokenString);
                        }
                        tokenString = "";
                    }
                    else{
                        tokenString = tokenString.concat(Character.toString(c));
                    }
                    break;
                
                case INEQUALS:
                    if(c != '='){
                        state = State.DONE;
                        CMinusScanner.unGetNextChar();
                        currentToken = new token(Token_type.ASSIGN_TOKEN);
                    }
                    else{
                        state = State.DONE;
                        currentToken = new token(Token_type.EQUALITY_TOKEN);
                    }
                    break;

                case INLESSTHAN:
                    if(c != '='){
                        state = State.DONE;
                        CMinusScanner.unGetNextChar();
                        currentToken = new token(Token_type.LESS_THAN_TOKEN);
                    }
                    else{
                        state = State.DONE;
                        currentToken = new token(Token_type.LESS_THAN_OR_EQUAL_TOKEN);
                    }
                    break;
                
                case INGREATERTHAN:
                    if(c != '='){
                        state = State.DONE;
                        CMinusScanner.unGetNextChar();
                        currentToken = new token(Token_type.GREATER_THAN_TOKEN);
                    }
                    else{
                        state = State.DONE;
                        currentToken = new token(Token_type.GREATER_THAN_OR_EQUAL_TOKEN);
                    }
                    break;
                
                case INNOT:
                    if(c == '='){
                        state = State.DONE;
                        CMinusScanner.unGetNextChar();
                        currentToken = new token(Token_type.NONEQUALITY_TOKEN);
                    }
                    else{
                        state = State.DONE;
                        currentToken = new token(Token_type.ERROR_TOKEN);
                    }
                    break;





                case INSLASH:
                    if(c != '*'){
                        state = State.DONE;
                        CMinusScanner.unGetNextChar();
                        currentToken = new token(Token_type.DIVIDE_TOKEN);
                        tokenString = "";
                    }
                    else {
                        state = State.INCOMMENT;
                        currentToken = new token(Token_type.DEFAULT_TOKEN);
                    }
                    break;





                case INSTAR:
                    if(c != '/'){
                        state = State.DONE;
                        CMinusScanner.unGetNextChar();
                        currentToken = new token(Token_type.MULTIPLY_TOKEN);
                        tokenString = "";
                    }
                    else{
                        save = true;
                        state = State.DONE;
                        currentToken = new token(Token_type.DEFAULT_TOKEN);
                    }
                    break;

                case INCOMMENT:
                    if(c == '*'){
                        state = State.INSTAR;
                        currentToken = new token(Token_type.DEFAULT_TOKEN);
                    }

                case DONE:
                    break;
                
                default:
                    currentToken = new token(Token_type.ERROR_TOKEN, "ERROR");
                    break;
            }
        }
        if(save){
            if(currentToken.getType() == Token_type.START_COMMENT_TOKEN){
                save = false;
            }
            return currentToken;
        }
        else {
            return new token(Token_type.DEFAULT_TOKEN);
        }
    }
}
