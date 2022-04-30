package compiler.ParserClasses;

import compiler.compiler.CMinusCompiler;
import compiler.lowlevel.CodeItem;
import compiler.token.Token_type;

public class VarDecl extends Decl {
    public Token_type type;
    public String ID;
    public boolean has_brackets;
    public int number;

    public VarDecl(Token_type t, String s, boolean b, int n){
        type = t;
        ID = s;
        has_brackets = b;
        number = n;
    }

    public void print(String indent){
        System.out.print(indent + type + " " + ID);
        if(has_brackets){
            System.out.print( "[" + number + "]");
        }
        System.out.println();
    }

    public void genLLCode(){
        // check to see if var is a global var
        if(CMinusCompiler.globalHash.containsKey(ID)){
            
        }
    }
}