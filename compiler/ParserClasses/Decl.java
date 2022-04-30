package compiler.ParserClasses;

public abstract class Decl {
    public abstract void print(String indent);

    public abstract void genLLCode();
}