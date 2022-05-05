package compiler.compiler.ParserClasses;

import java.util.ArrayList;

import compiler.lowlevel.*;
import compiler.lowlevel.Operand.OperandType;
import compiler.lowlevel.Operation.OperationType;
import compiler.compiler.token.Token_type;

public class FunDecl extends Decl{
    public String ID;
    public Token_type type;
    public ArrayList<Param> params;
    public compoundStatement cmpd_stmt;

    public FunDecl(String id, Token_type t, ArrayList<Param> p, compoundStatement c){
        ID = id;
        type = t;
        params = p;
        cmpd_stmt = c;
    }

    public void print(String indent){
        System.out.print(indent + type + " " + ID + "(");
        for (int i = 0; i < params.size(); i++){
            params.get(i).print();
            if(i < params.size()-1){
                System.out.print(",");
            }
        }
        System.out.println(")");
        cmpd_stmt.print(indent + "    ");
    }

    public Function genLLCode(){
        int temp;
        if(type == Token_type.VOID_TOKEN){
            temp = 0;
        }
        else{
            temp = 1;
        }
        Function newFun = new Function(temp, ID);
        newFun.createBlock0();
        newFun.setCurrBlock(newFun.getLastBlock());
        
        // create block 1
        BasicBlock block1 = new BasicBlock(newFun);
        block1.setPrevBlock(newFun.getCurrBlock());
        newFun.getCurrBlock().setNextBlock(block1);
        newFun.setCurrBlock(block1);

        for(int i = 0; i < params.size(); i++){
            params.get(i).genLLCode(newFun);
        }

        cmpd_stmt.genLLCode(newFun);

        Operation op = new Operation(OperationType.FUNC_EXIT, newFun.getCurrBlock());

        newFun.getCurrBlock().appendOper(op);
        

        return newFun;
    }
}
