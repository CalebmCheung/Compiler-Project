package compiler.ParserClasses;

import java.beans.Statement;

import compiler.token.State;

public class selectionStatement extends Statement {
    public Expr expr;
    public Statement stmt;
    public Statement else_stmt;
}
