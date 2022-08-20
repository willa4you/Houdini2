package com.example.demo.LogicModel.grammar;

import java.util.Set;
import java.util.TreeSet;

import com.example.demo.LogicModel.Literal;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;


public class ModelParser {

    private static Set<Literal> literals = new TreeSet<>();

    public static Set<Literal> getLiterals() {
        return literals;
    }
    
    private static class RecognitionExceptionListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
            throws ParseCancellationException {
                throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
        }
    }

    public static void parse(String string) throws ParseCancellationException {
        
        CharStream input = CharStreams.fromString(string);

        TheoryLexer lexer = new TheoryLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new RecognitionExceptionListener());

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        TheoryParser parser = new TheoryParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new RecognitionExceptionListener());

        ParseTree tree = parser.theory();
        
        MyTheoryVisitor visitor = new MyTheoryVisitor();
        visitor.visit(tree);
        
        literals = visitor.getLiterals();
        
    }

    
}
