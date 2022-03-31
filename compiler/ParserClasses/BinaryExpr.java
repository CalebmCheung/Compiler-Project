package compiler.ParserClasses;

public class BinaryExpr {
    public operator type; 
    public Expr lhs;
    public Expr rhs;

    public enum operator{
        PLUS,
        MINUS,
        DIVIDE,
        MULTIPLY,
    }
}
