package com.example.demo.LogicModel.grammar;

import com.example.demo.LogicModel.grammar.TheoryParser.LiteralContext;

public class MyTheoryVisitor extends TheoryBaseVisitor<String> {
    @Override
    public String visitLiteral(LiteralContext ctx) {
        String s = ctx.getText();
        System.out.println("=" + s);
        return s;
    }
    
}
