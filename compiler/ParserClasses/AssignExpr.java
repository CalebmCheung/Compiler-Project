package compiler.ParserClasses;

public class AssignExpr extends Expr{
    VarExpr var;
    Expr rhs;

    public AssignExpr(VarExpr v, Expr e){
        var = v;
        rhs = e;
    }

    public void print(){
        System.out.print(var + " = ");
        rhs.print();
    }
}
