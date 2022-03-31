package compiler.ParserClasses;

import java.util.ArrayList;

public class FunDecl extends Decl{
    public String ID;
    public String type;
    public ArrayList<Param> params;
    public compoundStatement cmpd_stmt;

    public FunDecl(String id, String t, ArrayList<Param> p, compoundStatement c){
        ID = id;
        type = t;
        params = p;
        cmpd_stmt = c;
    }
}
