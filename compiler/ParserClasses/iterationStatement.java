package compiler.ParserClasses;

public class iterationStatement extends Statement {
    public Expr expr;
    public Statement stmt;
    public iterationStatement(Expr exp, Statement stm) {
        expr = exp;
        stmt = stm;
    }
    
}
