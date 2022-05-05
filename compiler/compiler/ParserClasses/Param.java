package compiler.compiler.ParserClasses;

import compiler.lowlevel.Function;

public class Param {
    public String ID;
    public boolean has_brackets;
    public int regNum;

    public Param(String s, boolean b){
        ID = s;
        has_brackets = b;
    }

    public void print(){
        System.out.print(ID);
        if(has_brackets){
            System.out.print("[]");
        }
    }

    public void genLLCode(Function func){
        regNum = func.getNewRegNum();
        func.getTable().put(ID, ID);
    }
}
