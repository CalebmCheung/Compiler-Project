package compiler;

import compiler.token;

class Main {
    public static void main(String []args) {
        String file = "./test.txt";

        token.CMinusScanner(file);
    }
}