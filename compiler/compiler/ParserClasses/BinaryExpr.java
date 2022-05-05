package compiler.compiler.ParserClasses;

import compiler.compiler.token.Token_type;
import compiler.lowlevel.*;
import compiler.lowlevel.Operand.OperandType;
import compiler.lowlevel.Operation.OperationType;

public class BinaryExpr extends Expr{
    public Token_type type; 
    public Expr lhs;
    public Expr rhs;

    public enum operator{
        PLUS,
        MINUS,
        DIVIDE,
        MULTIPLY,
    }

    public BinaryExpr(Expr left, Expr right, Token_type op){
        lhs = left;
        rhs = right;
        type = op;
    }

    public void print(String indent){
        lhs.print(indent);
        System.out.print(" " + type + " ");
        rhs.print("");
    }

    public void genLLCode(Function func){
        lhs.genLLCode(func);
        rhs.genLLCode(func);

        Operation op;

        switch(type){
            case PLUS_TOKEN:
                op = new Operation(OperationType.ASSIGN, func.getCurrBlock());
                break;

            case MINUS_TOKEN:
                op = new Operation(OperationType.ASSIGN, func.getCurrBlock());
                break;

            case MULTIPLY_TOKEN:
                op = new Operation(OperationType.ASSIGN, func.getCurrBlock());
                break;

            case DIVIDE_TOKEN:
                op = new Operation(OperationType.ASSIGN, func.getCurrBlock());
                break;
            
            default:
                op = new Operation(OperationType.UNKNOWN, func.getCurrBlock());
        }

        regNum = func.getNewRegNum();

        Operand src1 = new Operand(OperandType.REGISTER, lhs.regNum);
        Operand src2 = new Operand(OperandType.REGISTER, rhs.regNum);
        Operand dest = new Operand(OperandType.REGISTER, regNum);

        op.setSrcOperand(0, src1);
        op.setSrcOperand(1, src2);
        op.setDestOperand(0, dest);

        func.getCurrBlock().appendOper(op);
    }
}
