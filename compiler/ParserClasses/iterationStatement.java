package compiler.ParserClasses;

import java.beans.Statement;

public class iterationStatement extends Statement {
    public iterationStatement(Object target, String methodName, Object[] arguments) {
        super(target, methodName, arguments);
        //TODO Auto-generated constructor stub
    }
    Expr expr;
    Statement stmt;
}
