package compiler.compiler.ParserClasses;

import compiler.lowlevel.Function;

public class selectionStatement extends Statement {
	public Expr expr;
    public Statement stmt;
    public Statement else_stmt;

	public selectionStatement(Expr e, Statement s, Statement es){
		expr = e;
		stmt = s;
		else_stmt = es;
	}

	public void print(String indent){
		System.out.print(indent + "if ( ");
		expr.print("");
		System.out.println(" )");
		stmt.print(indent + "    ");
		if(else_stmt != null){
			System.out.println(indent + "else");
			else_stmt.print(indent + "    ");
		}
	}

	@Override
	public void genLLCode(Function func) {
		// TODO Auto-generated method stub
		
	}
}
