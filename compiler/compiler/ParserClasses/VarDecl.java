package compiler.compiler.ParserClasses;

import compiler.lowlevel.*;
import compiler.lowlevel.Operation.OperationType;
import compiler.compiler.*;
import compiler.compiler.token.Token_type;

public class VarDecl extends Decl {
    public Token_type type;
    public String ID;
    public boolean has_brackets;
    public int number;

    public VarDecl(Token_type t, String s, boolean b, int n){
        type = t;
        ID = s;
        has_brackets = b;
        number = n;
    }

    public void print(String indent){
        System.out.print(indent + type + " " + ID);
        if(has_brackets){
            System.out.print( "[" + number + "]");
        }
        System.out.println();
    }

    public void genLLCode(Function func){
        regNum = func.getNewRegNum();
        func.getTable().put(ID, ID);
    }

    public Data genLLCode(){
        /*
        if(type == Token_type.VOID_TOKEN){
            Data data = new Data(0, ID);
        }
        else{
            Data data = new Data(1, ID);
        }

        if(!CMinusCompiler.globalHash.containsKey(ID)){
            tempSize = CMinusCompiler.globalHash.size() + 1;
            CMinusCompiler.globalHash.put(ID, tempSize);
        }*/
        return null;
    }
}