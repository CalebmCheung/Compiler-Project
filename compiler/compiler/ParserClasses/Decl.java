package compiler.compiler.ParserClasses;

import compiler.lowlevel.CodeItem;

public abstract class Decl {

    public int regNum;
    public abstract void print(String indent);

    public abstract CodeItem genLLCode();
}