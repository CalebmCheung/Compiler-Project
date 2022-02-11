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
        DEFAULT_TOKEN, NOT_TOKEN, INTEGER_TOKEN;
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
        DONE

    }

    private static InputStream is = null;
    private static InputStreamReader isr = null;
    private static BufferedReader br = null;
    private static token nextToken;
    private static token tokenToPrint;
    private static Token_type tokenType;
    private static String tokenData;
    public static void main(String []args) {
        String inFile = "./test_3.txt";
        String outFile = "./out_test_3.txt";

        CMinusScanner(inFile, outFile);
    }

    public static void CMinusScanner (String inFile, String outFile) {
        //delcare buffered reader to take input from files
        try{
            is = new FileInputStream(inFile);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            File outFileFile = new File(outFile);
            FileWriter writer = new FileWriter(outFileFile);

            nextToken = getToken();
            writer.write(nextToken.getType() + "\t");
            if(nextToken.getData() != null){
                writer.write(nextToken.getData() + "\n");
            }
            else{
                writer.write("\n");
            }

            //print tokens until end of file reached
            while (nextToken.getType() != Token_type.EOF_TOKEN){
                tokenToPrint = getNextToken();
                if(tokenToPrint.getType() != Token_type.DEFAULT_TOKEN){
                    writer.write(tokenToPrint.getType() + "\t");
                    if (tokenToPrint.getType() == Token_type.NUMBER_TOKEN) { writer.write("\t"); }
                    if(tokenToPrint.getData() != null){
                        writer.write(tokenToPrint.getData() + "\n");
                    }
                    else{
                        writer.write("\n");
                    }
                }
            }

            is.close();
            isr.close();
            br.close();
            writer.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }
        catch(IOException e){
            System.out.println("IO Exception Raised From FileWriter");
        }
    }

    public static char getNextChar(){
        //fucntion to read in characters
        try{
            br.mark(1);
            char ret = (char)br.read();
            return ret;
        }
        catch(IOException e){
            System.out.println("IO Exception Raised From BufferedReader (br.read or br.mark)");
            return (char)-1;
        }
    }

    public static void unGetNextChar(){
        //back up buffered reader to last marked spot
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

    public String getData(){
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
                                currentToken = new token(Token_type.ERROR_TOKEN, Character.toString(c));
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
                    // check ASCII codes to see if digit or not
                    if((c < 65 || c > 90) && (c < 97 || c > 122)){
                        state = State.DONE;
                        unGetNextChar();
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
                        unGetNextChar();
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
                        unGetNextChar();
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
                        unGetNextChar();
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
                        unGetNextChar();
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
                        unGetNextChar();
                        currentToken = new token(Token_type.DIVIDE_TOKEN);
                        tokenString = "";
                    }
                    else {
                        state = State.INCOMMENT;
                        currentToken = new token(Token_type.START_COMMENT_TOKEN);
                        return currentToken;
                    }

                case INCOMMENT:
                    if(c == '*'){
                        state = State.INCOMMENTEND;
                    }
                    break;

                case INCOMMENTEND:
                    if(c == '/'){
                        state = State.DONE;
                        currentToken = new token(Token_type.END_COMMENT_TOKEN);
                    }
                    else{
                        state = State.INCOMMENT;
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
