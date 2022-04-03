package compiler.ParserClasses;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class compoundStatement extends Statement {
    public ArrayList<Decl> local_decls;
    public ArrayList<Statement> stmt_list;

    public compoundStatement(ArrayList<Decl> v, ArrayList<Statement> s){
        local_decls = v;
        stmt_list = s;
    }
}
