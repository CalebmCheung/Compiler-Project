package compiler.compiler;

import compiler.compiler.ParserClasses.exprStatement;

public class CodeGenerationException extends Exception{
    public CodeGenerationException (String message){
        super(message);
    }
}
