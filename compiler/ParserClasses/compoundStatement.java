package compiler.ParserClasses;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class compoundStatement extends Statement {
    public ArrayList<VarDecl> local_decls;
    public ArrayList<Statement> stmt_list;
}
