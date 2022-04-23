package compiler.ParserClasses;

public class exprStatement extends Statement {
    public Expr expr;

    public exprStatement(Expr e){
        expr = e;
    }

    public void print(String indent){
        expr.print(indent + "    ");
        //System.out.println(";");
    }
}

    
