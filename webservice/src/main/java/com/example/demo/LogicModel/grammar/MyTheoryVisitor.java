package com.example.demo.LogicModel.grammar;

import java.util.Set;
import java.util.TreeSet;

import com.example.demo.LogicModel.Literal;
import com.example.demo.LogicModel.grammar.TheoryParser.LiteralContext;

public class MyTheoryVisitor extends TheoryBaseVisitor<String> {
    private Set<Literal> literals = new TreeSet<>();
    
    public Set<Literal> getLiterals() {
        return this.literals;
    }
    
    @Override
    public String visitLiteral(LiteralContext ctx) {
        String s = ctx.getText();
        this.literals.add(new Literal(s));
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
