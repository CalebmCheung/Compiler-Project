package compiler.ParserClasses;

import java.beans.Statement;

import compiler.token.State;

public class selectionStatement extends Statement {
    public selectionStatement(Object target, String methodName, Object[] arguments) {
		super(target, methodName, arguments);
		//TODO Auto-generated constructor stub
	}
	public Expr expr;
    public Statement stmt;
    public Statement else_stmt;
}
