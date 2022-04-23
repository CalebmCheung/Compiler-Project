package compiler.ParserClasses;

public class NumExpr extends Expr{
    public int val;

    public NumExpr(int n){
        val = n;
    }

    public void print(String indent){
        System.out.print(val);
    }
}
