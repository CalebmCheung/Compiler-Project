package compiler.ParserClasses;

import compiler.token.State;

public class selectionStatement extends Statement {
	public Expr expr;
    public Statement stmt;
    public Statement else_stmt;

	public selectionStatement(Expr e, Statement s, Statement es){
		expr = e;
		stmt = s;
		else_stmt = es;
	}
}
