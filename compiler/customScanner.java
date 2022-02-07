package compiler;

import java.io.BufferedReader;

class customScanner {
    public static void main(String []args){
        
    }
    public class CMinusScanner implements Scanner {
    private BufferedReader inFile;
    private token nextToken;
    public CMinusScanner (BufferedReader file) {
        inFile = file;
        nextToken = scanToken();
    }
    public token getNextToken () {
        token returnToken = nextToken;
        if (nextToken.getType() != token.Token.EOF_TOKEN)
            nextToken = scanToken();
        return returnToken;
    }
    public token viewNextToken () {
        return nextToken;
    }
}
}