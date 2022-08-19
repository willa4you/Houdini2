package com.example.demo.LogicModel.grammar;
// Generated from Theory.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TheoryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VERSION=1, LOGICMODEL=2, FACTS=3, RULES=4, SUPREL=5, NATURAL=6, DECIMAL=7, 
		ALPHANUMERIC=8, ARROW=9, RULETAG=10, CLN=11, LBRACE=12, RBRACE=13, LSQUARE=14, 
		RSQUARE=15, GT=16, LT=17, QT=18, CM=19, WS=20;
	public static final int
		RULE_theory = 0, RULE_version = 1, RULE_logicmodel = 2, RULE_literal = 3, 
		RULE_facts = 4, RULE_unquotedfacts = 5, RULE_rules = 6, RULE_suprel = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"theory", "version", "logicmodel", "literal", "facts", "unquotedfacts", 
			"rules", "suprel"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'version'", "'logicModel'", "'facts'", "'rules'", "'supRules'", 
			null, null, null, null, null, "':'", "'{'", "'}'", "'['", "']'", "'>'", 
			"'<'", "'\"'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "VERSION", "LOGICMODEL", "FACTS", "RULES", "SUPREL", "NATURAL", 
			"DECIMAL", "ALPHANUMERIC", "ARROW", "RULETAG", "CLN", "LBRACE", "RBRACE", 
			"LSQUARE", "RSQUARE", "GT", "LT", "QT", "CM", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Theory.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TheoryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class TheoryContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(TheoryParser.LBRACE, 0); }
		public VersionContext version() {
			return getRuleContext(VersionContext.class,0);
		}
		public TerminalNode CM() { return getToken(TheoryParser.CM, 0); }
		public LogicmodelContext logicmodel() {
			return getRuleContext(LogicmodelContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(TheoryParser.RBRACE, 0); }
		public TerminalNode EOF() { return getToken(TheoryParser.EOF, 0); }
		public TheoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_theory; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitTheory(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TheoryContext theory() throws RecognitionException {
		TheoryContext _localctx = new TheoryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_theory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(LBRACE);
			setState(17);
			version();
			setState(18);
			match(CM);
			setState(19);
			logicmodel();
			setState(20);
			match(RBRACE);
			setState(21);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VersionContext extends ParserRuleContext {
		public List<TerminalNode> QT() { return getTokens(TheoryParser.QT); }
		public TerminalNode QT(int i) {
			return getToken(TheoryParser.QT, i);
		}
		public TerminalNode VERSION() { return getToken(TheoryParser.VERSION, 0); }
		public TerminalNode CLN() { return getToken(TheoryParser.CLN, 0); }
		public TerminalNode DECIMAL() { return getToken(TheoryParser.DECIMAL, 0); }
		public VersionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_version; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitVersion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VersionContext version() throws RecognitionException {
		VersionContext _localctx = new VersionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_version);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			match(QT);
			setState(24);
			match(VERSION);
			setState(25);
			match(QT);
			setState(26);
			match(CLN);
			setState(27);
			match(QT);
			setState(28);
			match(DECIMAL);
			setState(29);
			match(QT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicmodelContext extends ParserRuleContext {
		public List<TerminalNode> QT() { return getTokens(TheoryParser.QT); }
		public TerminalNode QT(int i) {
			return getToken(TheoryParser.QT, i);
		}
		public TerminalNode LOGICMODEL() { return getToken(TheoryParser.LOGICMODEL, 0); }
		public List<TerminalNode> CLN() { return getTokens(TheoryParser.CLN); }
		public TerminalNode CLN(int i) {
			return getToken(TheoryParser.CLN, i);
		}
		public TerminalNode LBRACE() { return getToken(TheoryParser.LBRACE, 0); }
		public TerminalNode FACTS() { return getToken(TheoryParser.FACTS, 0); }
		public List<TerminalNode> LSQUARE() { return getTokens(TheoryParser.LSQUARE); }
		public TerminalNode LSQUARE(int i) {
			return getToken(TheoryParser.LSQUARE, i);
		}
		public FactsContext facts() {
			return getRuleContext(FactsContext.class,0);
		}
		public List<TerminalNode> RSQUARE() { return getTokens(TheoryParser.RSQUARE); }
		public TerminalNode RSQUARE(int i) {
			return getToken(TheoryParser.RSQUARE, i);
		}
		public List<TerminalNode> CM() { return getTokens(TheoryParser.CM); }
		public TerminalNode CM(int i) {
			return getToken(TheoryParser.CM, i);
		}
		public TerminalNode RULES() { return getToken(TheoryParser.RULES, 0); }
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public TerminalNode SUPREL() { return getToken(TheoryParser.SUPREL, 0); }
		public SuprelContext suprel() {
			return getRuleContext(SuprelContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(TheoryParser.RBRACE, 0); }
		public LogicmodelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicmodel; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitLogicmodel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicmodelContext logicmodel() throws RecognitionException {
		LogicmodelContext _localctx = new LogicmodelContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_logicmodel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			match(QT);
			setState(32);
			match(LOGICMODEL);
			setState(33);
			match(QT);
			setState(34);
			match(CLN);
			setState(35);
			match(LBRACE);
			setState(36);
			match(QT);
			setState(37);
			match(FACTS);
			setState(38);
			match(QT);
			setState(39);
			match(CLN);
			setState(40);
			match(LSQUARE);
			setState(41);
			facts();
			setState(42);
			match(RSQUARE);
			setState(43);
			match(CM);
			setState(44);
			match(QT);
			setState(45);
			match(RULES);
			setState(46);
			match(QT);
			setState(47);
			match(CLN);
			setState(48);
			match(LSQUARE);
			setState(49);
			rules();
			setState(50);
			match(RSQUARE);
			setState(51);
			match(CM);
			setState(52);
			match(QT);
			setState(53);
			match(SUPREL);
			setState(54);
			match(QT);
			setState(55);
			match(CLN);
			setState(56);
			match(LSQUARE);
			setState(57);
			suprel();
			setState(58);
			match(RSQUARE);
			setState(59);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode ALPHANUMERIC() { return getToken(TheoryParser.ALPHANUMERIC, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(ALPHANUMERIC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactsContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(TheoryParser.WS, 0); }
		public List<TerminalNode> QT() { return getTokens(TheoryParser.QT); }
		public TerminalNode QT(int i) {
			return getToken(TheoryParser.QT, i);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode CM() { return getToken(TheoryParser.CM, 0); }
		public FactsContext facts() {
			return getRuleContext(FactsContext.class,0);
		}
		public FactsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_facts; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitFacts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactsContext facts() throws RecognitionException {
		FactsContext _localctx = new FactsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_facts);
		try {
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				match(WS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				match(QT);
				setState(65);
				literal();
				setState(66);
				match(QT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				match(QT);
				setState(69);
				literal();
				setState(70);
				match(QT);
				setState(71);
				match(CM);
				setState(72);
				facts();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnquotedfactsContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(TheoryParser.WS, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode CM() { return getToken(TheoryParser.CM, 0); }
		public UnquotedfactsContext unquotedfacts() {
			return getRuleContext(UnquotedfactsContext.class,0);
		}
		public UnquotedfactsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unquotedfacts; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitUnquotedfacts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnquotedfactsContext unquotedfacts() throws RecognitionException {
		UnquotedfactsContext _localctx = new UnquotedfactsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_unquotedfacts);
		try {
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				match(WS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				literal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				literal();
				setState(79);
				match(CM);
				setState(80);
				unquotedfacts();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RulesContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(TheoryParser.WS, 0); }
		public List<TerminalNode> QT() { return getTokens(TheoryParser.QT); }
		public TerminalNode QT(int i) {
			return getToken(TheoryParser.QT, i);
		}
		public TerminalNode ARROW() { return getToken(TheoryParser.ARROW, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public UnquotedfactsContext unquotedfacts() {
			return getRuleContext(UnquotedfactsContext.class,0);
		}
		public TerminalNode CM() { return getToken(TheoryParser.CM, 0); }
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public RulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_rules);
		try {
			setState(105);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				match(WS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				match(QT);
				setState(88);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(86);
					match(WS);
					}
					break;
				case 2:
					{
					setState(87);
					unquotedfacts();
					}
					break;
				}
				setState(90);
				match(ARROW);
				setState(91);
				literal();
				setState(92);
				match(QT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(94);
				match(QT);
				setState(97);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(95);
					match(WS);
					}
					break;
				case 2:
					{
					setState(96);
					unquotedfacts();
					}
					break;
				}
				setState(99);
				match(ARROW);
				setState(100);
				literal();
				setState(101);
				match(QT);
				setState(102);
				match(CM);
				setState(103);
				rules();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuprelContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(TheoryParser.WS, 0); }
		public List<TerminalNode> QT() { return getTokens(TheoryParser.QT); }
		public TerminalNode QT(int i) {
			return getToken(TheoryParser.QT, i);
		}
		public List<TerminalNode> RULETAG() { return getTokens(TheoryParser.RULETAG); }
		public TerminalNode RULETAG(int i) {
			return getToken(TheoryParser.RULETAG, i);
		}
		public TerminalNode GT() { return getToken(TheoryParser.GT, 0); }
		public TerminalNode LT() { return getToken(TheoryParser.LT, 0); }
		public TerminalNode CM() { return getToken(TheoryParser.CM, 0); }
		public SuprelContext suprel() {
			return getRuleContext(SuprelContext.class,0);
		}
		public SuprelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suprel; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitSuprel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuprelContext suprel() throws RecognitionException {
		SuprelContext _localctx = new SuprelContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_suprel);
		int _la;
		try {
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				match(WS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				match(QT);
				setState(109);
				match(RULETAG);
				setState(110);
				_la = _input.LA(1);
				if ( !(_la==GT || _la==LT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(111);
				match(RULETAG);
				setState(112);
				match(QT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(113);
				match(QT);
				setState(114);
				match(RULETAG);
				setState(115);
				_la = _input.LA(1);
				if ( !(_la==GT || _la==LT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(116);
				match(RULETAG);
				setState(117);
				match(QT);
				setState(118);
				match(CM);
				setState(119);
				suprel();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0014{\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004K\b\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003"+
		"\u0005S\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006Y\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006b\b\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006j\b"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007y\b\u0007\u0001\u0007\u0000\u0000\b\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0000\u0001\u0001\u0000\u0010\u0011|\u0000"+
		"\u0010\u0001\u0000\u0000\u0000\u0002\u0017\u0001\u0000\u0000\u0000\u0004"+
		"\u001f\u0001\u0000\u0000\u0000\u0006=\u0001\u0000\u0000\u0000\bJ\u0001"+
		"\u0000\u0000\u0000\nR\u0001\u0000\u0000\u0000\fi\u0001\u0000\u0000\u0000"+
		"\u000ex\u0001\u0000\u0000\u0000\u0010\u0011\u0005\f\u0000\u0000\u0011"+
		"\u0012\u0003\u0002\u0001\u0000\u0012\u0013\u0005\u0013\u0000\u0000\u0013"+
		"\u0014\u0003\u0004\u0002\u0000\u0014\u0015\u0005\r\u0000\u0000\u0015\u0016"+
		"\u0005\u0000\u0000\u0001\u0016\u0001\u0001\u0000\u0000\u0000\u0017\u0018"+
		"\u0005\u0012\u0000\u0000\u0018\u0019\u0005\u0001\u0000\u0000\u0019\u001a"+
		"\u0005\u0012\u0000\u0000\u001a\u001b\u0005\u000b\u0000\u0000\u001b\u001c"+
		"\u0005\u0012\u0000\u0000\u001c\u001d\u0005\u0007\u0000\u0000\u001d\u001e"+
		"\u0005\u0012\u0000\u0000\u001e\u0003\u0001\u0000\u0000\u0000\u001f \u0005"+
		"\u0012\u0000\u0000 !\u0005\u0002\u0000\u0000!\"\u0005\u0012\u0000\u0000"+
		"\"#\u0005\u000b\u0000\u0000#$\u0005\f\u0000\u0000$%\u0005\u0012\u0000"+
		"\u0000%&\u0005\u0003\u0000\u0000&\'\u0005\u0012\u0000\u0000\'(\u0005\u000b"+
		"\u0000\u0000()\u0005\u000e\u0000\u0000)*\u0003\b\u0004\u0000*+\u0005\u000f"+
		"\u0000\u0000+,\u0005\u0013\u0000\u0000,-\u0005\u0012\u0000\u0000-.\u0005"+
		"\u0004\u0000\u0000./\u0005\u0012\u0000\u0000/0\u0005\u000b\u0000\u0000"+
		"01\u0005\u000e\u0000\u000012\u0003\f\u0006\u000023\u0005\u000f\u0000\u0000"+
		"34\u0005\u0013\u0000\u000045\u0005\u0012\u0000\u000056\u0005\u0005\u0000"+
		"\u000067\u0005\u0012\u0000\u000078\u0005\u000b\u0000\u000089\u0005\u000e"+
		"\u0000\u00009:\u0003\u000e\u0007\u0000:;\u0005\u000f\u0000\u0000;<\u0005"+
		"\r\u0000\u0000<\u0005\u0001\u0000\u0000\u0000=>\u0005\b\u0000\u0000>\u0007"+
		"\u0001\u0000\u0000\u0000?K\u0005\u0014\u0000\u0000@A\u0005\u0012\u0000"+
		"\u0000AB\u0003\u0006\u0003\u0000BC\u0005\u0012\u0000\u0000CK\u0001\u0000"+
		"\u0000\u0000DE\u0005\u0012\u0000\u0000EF\u0003\u0006\u0003\u0000FG\u0005"+
		"\u0012\u0000\u0000GH\u0005\u0013\u0000\u0000HI\u0003\b\u0004\u0000IK\u0001"+
		"\u0000\u0000\u0000J?\u0001\u0000\u0000\u0000J@\u0001\u0000\u0000\u0000"+
		"JD\u0001\u0000\u0000\u0000K\t\u0001\u0000\u0000\u0000LS\u0005\u0014\u0000"+
		"\u0000MS\u0003\u0006\u0003\u0000NO\u0003\u0006\u0003\u0000OP\u0005\u0013"+
		"\u0000\u0000PQ\u0003\n\u0005\u0000QS\u0001\u0000\u0000\u0000RL\u0001\u0000"+
		"\u0000\u0000RM\u0001\u0000\u0000\u0000RN\u0001\u0000\u0000\u0000S\u000b"+
		"\u0001\u0000\u0000\u0000Tj\u0005\u0014\u0000\u0000UX\u0005\u0012\u0000"+
		"\u0000VY\u0005\u0014\u0000\u0000WY\u0003\n\u0005\u0000XV\u0001\u0000\u0000"+
		"\u0000XW\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z[\u0005\t\u0000"+
		"\u0000[\\\u0003\u0006\u0003\u0000\\]\u0005\u0012\u0000\u0000]j\u0001\u0000"+
		"\u0000\u0000^a\u0005\u0012\u0000\u0000_b\u0005\u0014\u0000\u0000`b\u0003"+
		"\n\u0005\u0000a_\u0001\u0000\u0000\u0000a`\u0001\u0000\u0000\u0000bc\u0001"+
		"\u0000\u0000\u0000cd\u0005\t\u0000\u0000de\u0003\u0006\u0003\u0000ef\u0005"+
		"\u0012\u0000\u0000fg\u0005\u0013\u0000\u0000gh\u0003\f\u0006\u0000hj\u0001"+
		"\u0000\u0000\u0000iT\u0001\u0000\u0000\u0000iU\u0001\u0000\u0000\u0000"+
		"i^\u0001\u0000\u0000\u0000j\r\u0001\u0000\u0000\u0000ky\u0005\u0014\u0000"+
		"\u0000lm\u0005\u0012\u0000\u0000mn\u0005\n\u0000\u0000no\u0007\u0000\u0000"+
		"\u0000op\u0005\n\u0000\u0000py\u0005\u0012\u0000\u0000qr\u0005\u0012\u0000"+
		"\u0000rs\u0005\n\u0000\u0000st\u0007\u0000\u0000\u0000tu\u0005\n\u0000"+
		"\u0000uv\u0005\u0012\u0000\u0000vw\u0005\u0013\u0000\u0000wy\u0003\u000e"+
		"\u0007\u0000xk\u0001\u0000\u0000\u0000xl\u0001\u0000\u0000\u0000xq\u0001"+
		"\u0000\u0000\u0000y\u000f\u0001\u0000\u0000\u0000\u0006JRXaix";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}