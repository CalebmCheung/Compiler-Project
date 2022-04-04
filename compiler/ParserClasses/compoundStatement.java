package compiler.ParserClasses;

import java.util.ArrayList;

public class compoundStatement extends Statement {
    public ArrayList<VarDecl> local_decls;
    public ArrayList<Statement> stmt_list;

    public compoundStatement(ArrayList<VarDecl> v, ArrayList<Statement> s){
        local_decls = v;
        stmt_list = s;
    }

    public void print(){
        if(local_decls.size() > 0){
            for(int i = 0; i < local_decls.size(); i++){
                local_decls.get(i).print();
            }
        }
        if(stmt_list.size() > 0){
            for(int i = 0; i < stmt_list.size(); i++){
                stmt_list.get(i).print();
            }
        }
    }
}
