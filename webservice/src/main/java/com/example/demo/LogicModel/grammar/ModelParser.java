package com.example.demo.LogicModel.grammar;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;


public class ModelParser {
    public static void parse(String string) throws Exception {
        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromString(string);
        // create a lexer that feeds off of input CharStream
        TheoryLexer lexer = new TheoryLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        TheoryParser parser = new TheoryParser(tokens);
        ParseTree tree = parser.theory(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
    }
}
