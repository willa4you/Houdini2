package com.example.demo.LogicModel.grammar;

import com.example.demo.LogicModel.grammar.TheoryParser.LiteralContext;

public class MyTheoryVisitor extends TheoryBaseVisitor<String> {
    
    @Override
    public String visitLiteral(LiteralContext ctx) {
        String s = ctx.getText();
        return s;
    }
    /*
    @Override
    public String visitFacts(FactsContext ctx) {
        try {
            visit(ctx);
            System.out.println("~~~~~~~>" + ctx.exception.toString());
            
            return "";
        } catch(ParseCancellationException e) {
            System.out.println("We catch this exception in the VISITOR");
            System.out.println("~~~~~~~>" + ctx.exception.toString());
            return super.visitFacts(ctx);
        }
    }
    */
}
