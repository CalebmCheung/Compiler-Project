package compiler.ParserClasses;

public class iterationStatement extends Statement {
    public Expr expr;
    public Statement stmt;
    public iterationStatement(Expr exp, Statement stm) {
        expr = exp;
        stmt = stm;
    }
    
    public void print(){
        System.out.print("While (" );
        expr.print();
        System.out.print(") ");
        stmt.print();
    }
}
