package compiler.compiler.ParserClasses;

import java.util.ArrayList;

import compiler.lowlevel.*;
import compiler.lowlevel.Operand.OperandType;
import compiler.lowlevel.Operation.OperationType;

public class CallExpr extends Expr{
    public String ID;
    public ArrayList<Expr> args;

    public CallExpr(String i, ArrayList<Expr> a){
        ID = i;
        args = a;
    }

    public void print(String indent){
        System.out.print(indent + ID + "( ");
        if(args != null){
            for(int i = 0; i < args.size(); i++){
                args.get(i).print("");
                if(i < args.size()-1){
                    System.out.print(", ");
                }
            }
        }
        System.out.println(indent + ");");
    }

    public void genLLCode(Function func){
        Operation op = new Operation(OperationType.CALL, func.getCurrBlock());

        for(int i = 0; i < args.size(); i++){
            Operand src = new Operand(OperandType.REGISTER, func.getCurrBlock());
            op.setSrcOperand(i, src);
        }

        Operand dest = new Operand(OperandType.STRING, func.getCurrBlock());
        op.setDestOperand(0, dest);
        
        func.getCurrBlock().appendOper(op);
    }
}
