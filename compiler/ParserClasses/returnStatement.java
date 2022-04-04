package compiler.ParserClasses;

public class returnStatement extends Statement {
    Expr expr;

    public returnStatement(Expr e){
        expr = e;
    }

    public void print(){
        System.out.print("return ");
        expr.print();
        System.out.println(";");
    }
}
