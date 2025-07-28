package com.lld.models;

public class Symbol {
    private char aChar;

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "aChar=" + aChar +
                '}';
    }

    public Symbol(char aChar) {
        this.aChar = aChar;
    }
}
