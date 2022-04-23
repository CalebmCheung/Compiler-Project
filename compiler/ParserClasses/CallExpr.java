package compiler.ParserClasses;

import java.util.ArrayList;

public class CallExpr extends Expr{
    public String ID;
    public ArrayList<Expr> args;

    public CallExpr(String i, ArrayList<Expr> a){
        ID = i;
        args = a;
    }

    public void print(String indent){
        System.out.print(indent + ID + "( ");
        if(args != null){
            for(int i = 0; i < args.size(); i++){
                args.get(i).print("");
                if(i < args.size()-1){
                    System.out.print(", ");
                }
            }
        }
        System.out.println(indent + ");");
    }
}
