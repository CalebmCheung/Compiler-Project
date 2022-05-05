package compiler.compiler.ParserClasses;

import compiler.lowlevel.Function;

public class NumExpr extends Expr{
    public int val;

    public NumExpr(int n){
        val = n;
    }

    public void print(String indent){
        System.out.print(val);
    }

    public void genLLCode(Function func){
        regNum = func.getNewRegNum();
        func.getTable().put(val, val);
    }
}
