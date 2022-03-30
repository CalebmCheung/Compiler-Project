package compiler.ParserClasses;

import java.util.ArrayList;

public class FunDecl extends Decl{
    public String type;
    public ArrayList<Param> params;
    public compoundStatement cmpd_stmt;
}
