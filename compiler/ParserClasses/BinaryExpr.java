package compiler.ParserClasses;

import compiler.token.Token_type;
import compiler.lowlevel.*;

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

        // create oper (lhs.regNum, rhs.regNum, dest.regNum)
    }
}
