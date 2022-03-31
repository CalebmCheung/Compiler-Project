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
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import compiler.token.Token_type;

public class CMinusScanner {

    private static InputStream is = null;
    private static InputStreamReader isr = null;
    private static BufferedReader br = null;
    private static token nextToken;
    private static token tokenToPrint;

    public static void main(String []args) {
        // test non flex scanner in progress
        String inFile = "./compilerTests/test_2.txt";
        String outFile = "./compilerTests/out_test_2.txt";

        ArrayList<token> program = CMinusScanner(inFile, outFile);
        System.out.println(program.size());
        System.out.println("------------------------------------------------------------------");
        for(int i = 0; i < program.size(); i++){
            System.out.println(program.get(i).getType());
        }
    }

    public static ArrayList<token> CMinusScanner (String inFile, String outFile) {
        //delcare buffered reader to take input from files
        ArrayList<token> ret = new ArrayList<token>();
        try{
            is = new FileInputStream(inFile);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            File outFileFile = new File(outFile);
            FileWriter writer = new FileWriter(outFileFile);

            nextToken = token.getToken();
            if(nextToken.getType() != token.Token_type.DEFAULT_TOKEN){
                writer.write(nextToken.getType() + "\t");
                ret.add(new token(nextToken.getType(), nextToken.getData()));
                if(nextToken.getData() != null){
                    writer.write(nextToken.getData() + "\n");
                }
                else{
                    writer.write("\n");
                }
            }

            //print tokens until end of file reached
            while(nextToken.getType() != token.Token_type.EOF_TOKEN){
                tokenToPrint = getNextToken();
                if(tokenToPrint.getType() != token.Token_type.DEFAULT_TOKEN){
                    writer.write(tokenToPrint.getType() + "\t");
                    System.out.println(tokenToPrint.getType());
                    Token_type temptype = tokenToPrint.getType();
                    String tempdata = tokenToPrint.getData();
                    ret.add(new token(temptype, tempdata));
                    if(tokenToPrint.getType() == token.Token_type.NUMBER_TOKEN) { writer.write("\t"); }
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
        
        System.out.println("------------------------------------------------------------------");
        for(int i = 0; i < ret.size(); i++){
            System.out.println(ret.get(i).getType());
        }
        return ret;
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

    public static token viewNextToken () {
        return nextToken;
    }

    public static token getNextToken () {
        token returnToken = nextToken;
        if (nextToken.getType() != token.Token_type.EOF_TOKEN)
            nextToken = token.getToken();
        return returnToken;
    }
}
