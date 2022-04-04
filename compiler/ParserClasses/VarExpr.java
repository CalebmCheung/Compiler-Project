package compiler.ParserClasses;

public class VarExpr extends Expr {
    public String ID;
    Expr bracketExpr;

    public VarExpr(String i, Expr e){
        ID = i;
        bracketExpr = e;
    }

    public void print(){
        System.out.print(ID);
        if(bracketExpr != null){
            System.out.print("[ ");
            bracketExpr.print();
            System.out.println(" ]");
        }
        System.out.println();
    }
}