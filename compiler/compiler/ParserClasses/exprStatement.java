package compiler.compiler.ParserClasses;

import compiler.lowlevel.Function;

public class exprStatement extends Statement {
    public Expr expr;

    public exprStatement(Expr e){
        expr = e;
    }

    public void print(String indent){
        expr.print(indent + "    ");
        //System.out.println(";");
    }

    public void genLLCode(Function func){
        expr.genLLCode(func);
    }
}

    
