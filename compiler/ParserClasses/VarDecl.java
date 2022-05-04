package compiler.ParserClasses;

import compiler.lowlevel.*;
import compiler.lowlevel.Operation.OperationType;
import compiler.compiler.*;
import compiler.token.Token_type;

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
        if(CMinusCompiler.globalHash.containsKey(ID)){
            regNum = CMinusCompiler.globalHash.get(ID);
            // create load
            Operation op = new Operation(OperationType.LOAD_I, func.getCurrBlock());
            func.getCurrBlock().appendOper(op);
        }
        else {
            // mov into register
            Operation op = new Operation(OperationType.ASSIGN, func.getCurrBlock());
            func.getCurrBlock().appendOper(op);
            number = func.getNewRegNum();
        }
    }

    public void genLLCode(){
        
        if(type == Token_type.VOID_TOKEN){
            Data data = new Data(0, ID);
        }
        else{
            Data data = new Data(0, ID);
        }

        if(!CMinusCompiler.globalHash.containsKey(ID)){
            tempSize = CMinusCompiler.globalHash.size() + 1;
            CMinusCompiler.globalHash.put(ID, tempSize);
        }
    }
}