package compiler.compiler.ParserClasses;

import compiler.compiler.CMinusCompiler;
import compiler.lowlevel.*;
import compiler.lowlevel.Operand.OperandType;
import compiler.lowlevel.Operation.OperationType;

public class AssignExpr extends Expr{
    VarExpr var;
    Expr rhs;

    public AssignExpr(VarExpr v, Expr e){
        var = v;
        rhs = e;
    }

    public void print(String indent){
        var.print(indent + "    ");
        System.out.print(" = ");
        rhs.print("");
        System.out.println();
    }

    public void genLLCode(Function func){
        if(CMinusCompiler.globalHash.containsKey(var.ID)){
            //
        }
        
        if(func.getTable().containsKey(var.ID)) {
            rhs.genLLCode(func);

            // mov into register
            Operation op = new Operation(OperationType.ASSIGN, func.getCurrBlock());

            Operand src = new Operand(OperandType.REGISTER, rhs.regNum);
            Operand dest = new Operand(OperandType.REGISTER, var.regNum);

            op.setSrcOperand(0, src);
            op.setDestOperand(0, dest);

            func.getCurrBlock().appendOper(op);
            regNum = var.regNum;
        }
    }
}
