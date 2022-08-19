package com.example.demo.LogicModel.grammar;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;


public class ModelParser {
    public static void parse(String string) throws Exception {
        
        CharStream input = CharStreams.fromString(string);
        
        TheoryLexer lexer = new TheoryLexer(input);
        
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        TheoryParser parser = new TheoryParser(tokens);
        ParseTree tree = parser.theory(); 
        System.out.println(tree.toStringTree(parser)); // print tree
    }
}
