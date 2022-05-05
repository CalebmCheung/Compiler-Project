package compiler.compiler.ParserClasses;

import java.util.ArrayList;

import compiler.lowlevel.*;

public class compoundStatement extends Statement {
    public ArrayList<VarDecl> local_decls;
    public ArrayList<Statement> stmt_list;

    public compoundStatement(ArrayList<VarDecl> v, ArrayList<Statement> s){
        local_decls = v;
        stmt_list = s;
    }

    public void print(String indent){
        System.out.println(indent + "{");
        if(local_decls.size() > 0){
            for(int i = 0; i < local_decls.size(); i++){
                local_decls.get(i).print(indent + "    ");
            }
        }
        if(stmt_list.size() > 0){
            for(int i = 0; i < stmt_list.size(); i++){
                stmt_list.get(i).print(indent + "    ");
            }
        }
        System.out.println(indent + "}");
    }

    public void genLLCode(Function func){
        
        /*

        while localdecls
            call vardecl?
        */

        for(int i = 0; i < stmt_list.size(); i++){
            stmt_list.get(i).genLLCode(func);
        }
    }
}
