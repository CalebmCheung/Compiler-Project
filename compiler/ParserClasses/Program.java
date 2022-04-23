package compiler.ParserClasses;

import java.util.ArrayList;

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
}