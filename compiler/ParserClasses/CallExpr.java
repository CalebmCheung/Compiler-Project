package compiler.ParserClasses;

import java.util.ArrayList;

public class CallExpr {
    public String ID;
    public Expr expr;
    public ArrayList<Expr> args;
    public BinaryExpr simpleExpr;
}
