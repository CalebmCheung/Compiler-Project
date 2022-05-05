package compiler.compiler.ParserClasses;

import compiler.lowlevel.Function;

public class iterationStatement extends Statement {
    public Expr expr;
    public Statement stmt;
    public iterationStatement(Expr exp, Statement stm) {
        expr = exp;
        stmt = stm;
    }
    
    public void print(String indent){
        System.out.print(indent + "While (" );
        expr.print("");
        System.out.println(") ");
        stmt.print(indent + "    ");
    }

    @Override
    public void genLLCode(Function func) {
        // TODO Auto-generated method stub
        
    }
}
