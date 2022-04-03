package compiler.ParserClasses;

public class returnStatement extends Statement {
    Expr expr;

    public returnStatement(Expr e){
        expr = e;
    }
}
