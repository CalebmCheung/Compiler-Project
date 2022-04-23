package compiler.ParserClasses;

import java.util.ArrayList;

import compiler.token.Token_type;

public class FunDecl extends Decl{
    public String ID;
    public Token_type type;
    public ArrayList<Param> params;
    public compoundStatement cmpd_stmt;

    public FunDecl(String id, Token_type t, ArrayList<Param> p, compoundStatement c){
        ID = id;
        type = t;
        params = p;
        cmpd_stmt = c;
    }

    public void print(String indent){
        System.out.print(indent + type + " " + ID + "(");
        for (int i = 0; i < params.size(); i++){
            params.get(i).print();
            if(i < params.size()-1){
                System.out.print(",");
            }
        }
        System.out.println(")");
        cmpd_stmt.print(indent + "    ");
    }
}
