package compiler.compiler.ParserClasses;

import compiler.lowlevel.*;

public abstract class Expr {
    public abstract void print(String indent);

    public abstract void genLLCode(Function func);

    int regNum;
}
