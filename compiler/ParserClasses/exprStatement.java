package compiler.ParserClasses;

public class exprStatement extends Statement {
    public Expr expr;

    public exprStatement(Expr e){
        expr = e;
    }

    public void print(){
        expr.print();
        System.out.println(";");
    }
}

    
