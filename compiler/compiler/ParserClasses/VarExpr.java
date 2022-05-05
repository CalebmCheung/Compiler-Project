package compiler.compiler.ParserClasses;
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
        regNum = func.getNewRegNum();
        func.getTable().put(ID, ID);
    }
}