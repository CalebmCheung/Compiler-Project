package compiler.compiler.ParserClasses;

import compiler.lowlevel.Function;

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

    @Override
    public void genLLCode(Function func) {
        // TODO Auto-generated method stub
        
    }
}
