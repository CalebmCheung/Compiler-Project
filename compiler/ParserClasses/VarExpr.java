package compiler.ParserClasses;
import compiler.lowlevel.*;
import compiler.lowlevel.Operand.OperandType;
import compiler.lowlevel.Operation.OperationType;
import compiler.compiler.CMinusCompiler;

public class VarExpr extends Expr {
    public String ID;
    Expr bracketExpr;

    public VarExpr(String i, Expr e){
        ID = i;
        bracketExpr = e;
    }

    public void print(String indent){
        System.out.print(indent + ID);
        if(bracketExpr != null){
            System.out.print("[ ");
            bracketExpr.print("");
            System.out.print(" ]");
        }
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
            regNum = func.getNewRegNum();
        }
    }
}