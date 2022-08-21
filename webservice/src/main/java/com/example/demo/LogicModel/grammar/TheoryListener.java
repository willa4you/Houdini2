package com.example.demo.LogicModel.grammar;// Generated from Theory.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TheoryParser}.
 */
public interface TheoryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TheoryParser#theory}.
	 * @param ctx the parse tree
	 */
	void enterTheory(TheoryParser.TheoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TheoryParser#theory}.
	 * @param ctx the parse tree
	 */
	void exitTheory(TheoryParser.TheoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TheoryParser#logicmodel}.
	 * @param ctx the parse tree
	 */
	void enterLogicmodel(TheoryParser.LogicmodelContext ctx);
	/**
	 * Exit a parse tree produced by {@link TheoryParser#logicmodel}.
	 * @param ctx the parse tree
	 */
	void exitLogicmodel(TheoryParser.LogicmodelContext ctx);
	/**
	 * Enter a parse tree produced by {@link TheoryParser#unquotedfacts}.
	 * @param ctx the parse tree
	 */
	void enterUnquotedfacts(TheoryParser.UnquotedfactsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TheoryParser#unquotedfacts}.
	 * @param ctx the parse tree
	 */
	void exitUnquotedfacts(TheoryParser.UnquotedfactsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TheoryParser#facts}.
	 * @param ctx the parse tree
	 */
	void enterFacts(TheoryParser.FactsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TheoryParser#facts}.
	 * @param ctx the parse tree
	 */
	void exitFacts(TheoryParser.FactsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TheoryParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(TheoryParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TheoryParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(TheoryParser.RulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TheoryParser#suprels}.
	 * @param ctx the parse tree
	 */
	void enterSuprels(TheoryParser.SuprelsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TheoryParser#suprels}.
	 * @param ctx the parse tree
	 */
	void exitSuprels(TheoryParser.SuprelsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TheoryParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(TheoryParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link TheoryParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(TheoryParser.LiteralContext ctx);
}