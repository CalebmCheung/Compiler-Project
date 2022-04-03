package compiler.ParserClasses;

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
}