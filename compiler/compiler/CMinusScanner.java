package compiler.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class CMinusScanner {

    private static InputStream is = null;
    private static InputStreamReader isr = null;
    private static BufferedReader br = null;
    private token nextToken;
    private token tokenToPrint;
    private static File outFileFile;
    private static FileWriter writer;

    public static void main(String []args) {
        // test non flex scanner in progress
        //String inFile = "./compilerTests/test_2.txt";
        //String outFile = "./compilerTests/out_test_2.txt";

        //CMinusScanner myScanner = new CMinusScanner(inFile, outFile);

        // use to write to file
        // myScanner.Scan();
        // myScanner.Close();
    }

    public CMinusScanner (String inFile, String outFile) {
        try{
            is = new FileInputStream(inFile);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            outFileFile = new File(outFile);
            writer = new FileWriter(outFileFile);
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }
        catch(IOException e){
            System.out.println("IO Exception Raised From FileWriter");
        }
    }

    public CMinusScanner (String inFile) {
        this(inFile, (inFile + ".out.txt"));
    }

    public void Close(){
        try{
            is.close();
            isr.close();
            br.close();
            writer.close();
        }
        catch(IOException e){
            System.out.println("IO Exception Raised From Closing");
        }
    }

    public void Scan(){
        try{
            nextToken = token.getToken();
            if(nextToken.getType() != token.Token_type.DEFAULT_TOKEN){
                writer.write(nextToken.getType() + "\t");
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
                    if(tokenToPrint.getType() == token.Token_type.NUMBER_TOKEN) { writer.write("\t"); }
                    if(tokenToPrint.getData() != null){
                        writer.write(tokenToPrint.getData() + "\n");
                    }
                    else{
                        writer.write("\n");
                    }
                }
            }
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

    public token viewNextToken () {
        return nextToken;
    }

    public token getNextToken () {
        token returnToken = nextToken;
        if (nextToken.getType() != token.Token_type.EOF_TOKEN)
            nextToken = token.getToken();
        return returnToken;
    }
}
