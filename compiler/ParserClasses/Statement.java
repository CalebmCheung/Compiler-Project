package compiler.ParserClasses;

import compiler.lowlevel.*;

public abstract class Statement {
    public abstract void print(String indent);

    public abstract void genLLCode(Function func);
}


