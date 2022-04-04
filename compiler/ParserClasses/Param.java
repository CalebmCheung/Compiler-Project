package compiler.ParserClasses;

public class Param {
    public String ID;
    public boolean has_brackets;

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
}
