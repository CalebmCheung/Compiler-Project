package compiler.ParserClasses;

public class returnStatement extends Statement {
    Expr expr;

    public returnStatement(Expr e){
        expr = e;
    }

    public void print(String indent){
        System.out.print(indent + "return ");
        expr.print("");
        System.out.println(";");
    }
}
