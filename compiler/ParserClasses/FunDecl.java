package compiler.ParserClasses;

import java.util.ArrayList;

import compiler.token.Token_type;

public class FunDecl extends Decl{
    public String ID;
    public Token_type type;
    public ArrayList<Param> params;
    public ArrayList<compoundStatement> cmpd_stmt;

    public FunDecl(String id, Token_type t, ArrayList<Param> p, ArrayList<compoundStatement> c){
        ID = id;
        type = t;
        params = p;
        cmpd_stmt = c;
    }
}
