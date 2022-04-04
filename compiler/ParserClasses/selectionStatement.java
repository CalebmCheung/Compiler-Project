package compiler.ParserClasses;

public class selectionStatement extends Statement {
	public Expr expr;
    public Statement stmt;
    public Statement else_stmt;

	public selectionStatement(Expr e, Statement s, Statement es){
		expr = e;
		stmt = s;
		else_stmt = es;
	}

	public void print(){
		System.out.print("if ( ");
		expr.print();
		System.out.print(" )\n\t");
		stmt.print();
		if(else_stmt != null){
			System.out.print("else\n\t");
			else_stmt.print();
		}
	}
}
