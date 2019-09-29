package com.dictionary.interfaces;

/*
    not in use yet, will build my own sql parser
 */
public interface Parser {
    boolean isValidQuery(String query);
    String parse();
}
