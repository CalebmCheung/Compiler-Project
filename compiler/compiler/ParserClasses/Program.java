package compiler.compiler.ParserClasses;

import java.util.ArrayList;

import compiler.lowlevel.CodeItem;

public class Program {
    public ArrayList<Decl> decList;

    public Program(){
        decList = new ArrayList<Decl>();
    }

    public void print(String indent){
        System.out.println(indent + "program");
        for(int i = 0; i < decList.size(); i++){
            decList.get(i).print(indent + "    ");
            System.out.println();
        }
    }

    public CodeItem genLLCode(){
        CodeItem c = decList.get(0).genLLCode();
        if(decList.size() > 1){
            for(int i = 1; i < decList.size(); i++){
                CodeItem prevCodeItem = c;
                c = decList.get(i).genLLCode();
                prevCodeItem.setNextItem(c);
            }
        }
        
        return c;
    }
}