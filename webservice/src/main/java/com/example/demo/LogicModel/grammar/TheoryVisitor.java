// Generated from Theory.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TheoryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TheoryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TheoryParser#theory}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTheory(TheoryParser.TheoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TheoryParser#logicmodel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicmodel(TheoryParser.LogicmodelContext ctx);
	/**
	 * Visit a parse tree produced by {@link TheoryParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(TheoryParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link TheoryParser#unquotedfacts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnquotedfacts(TheoryParser.UnquotedfactsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TheoryParser#facts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFacts(TheoryParser.FactsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TheoryParser#rules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRules(TheoryParser.RulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link TheoryParser#suprel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuprel(TheoryParser.SuprelContext ctx);
}