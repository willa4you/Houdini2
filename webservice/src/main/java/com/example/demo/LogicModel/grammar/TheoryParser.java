package com.example.demo.LogicModel.grammar;
// Generated from ./Theory.g4 by ANTLR 4.10.1
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
		VERSION=1, LOGICMODEL=2, FACTS=3, RULES=4, SUPREL=5, RULETAG=6, NATURAL=7, 
		DECIMAL=8, ALPHANUMERIC=9, ARROW=10, CLN=11, LBRACE=12, RBRACE=13, LSQUARE=14, 
		RSQUARE=15, GT=16, LT=17, QT=18, CM=19, WS=20;
	public static final int
		RULE_theory = 0, RULE_logicmodel = 1, RULE_unquotedfacts = 2, RULE_facts = 3, 
		RULE_rules = 4, RULE_suprels = 5, RULE_literal = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"theory", "logicmodel", "unquotedfacts", "facts", "rules", "suprels", 
			"literal"
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
			null, "VERSION", "LOGICMODEL", "FACTS", "RULES", "SUPREL", "RULETAG", 
			"NATURAL", "DECIMAL", "ALPHANUMERIC", "ARROW", "CLN", "LBRACE", "RBRACE", 
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).enterTheory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).exitTheory(this);
		}
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
			setState(14);
			match(LBRACE);
			setState(15);
			logicmodel();
			setState(16);
			match(RBRACE);
			setState(17);
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

	public static class LogicmodelContext extends ParserRuleContext {
		public List<TerminalNode> QT() { return getTokens(TheoryParser.QT); }
		public TerminalNode QT(int i) {
			return getToken(TheoryParser.QT, i);
		}
		public TerminalNode FACTS() { return getToken(TheoryParser.FACTS, 0); }
		public List<TerminalNode> CLN() { return getTokens(TheoryParser.CLN); }
		public TerminalNode CLN(int i) {
			return getToken(TheoryParser.CLN, i);
		}
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
		public SuprelsContext suprels() {
			return getRuleContext(SuprelsContext.class,0);
		}
		public LogicmodelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicmodel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).enterLogicmodel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).exitLogicmodel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitLogicmodel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicmodelContext logicmodel() throws RecognitionException {
		LogicmodelContext _localctx = new LogicmodelContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_logicmodel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			match(QT);
			setState(20);
			match(FACTS);
			setState(21);
			match(QT);
			setState(22);
			match(CLN);
			setState(23);
			match(LSQUARE);
			setState(24);
			facts();
			setState(25);
			match(RSQUARE);
			setState(26);
			match(CM);
			setState(27);
			match(QT);
			setState(28);
			match(RULES);
			setState(29);
			match(QT);
			setState(30);
			match(CLN);
			setState(31);
			match(LSQUARE);
			setState(32);
			rules();
			setState(33);
			match(RSQUARE);
			setState(34);
			match(CM);
			setState(35);
			match(QT);
			setState(36);
			match(SUPREL);
			setState(37);
			match(QT);
			setState(38);
			match(CLN);
			setState(39);
			match(LSQUARE);
			setState(40);
			suprels();
			setState(41);
			match(RSQUARE);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).enterUnquotedfacts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).exitUnquotedfacts(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitUnquotedfacts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnquotedfactsContext unquotedfacts() throws RecognitionException {
		UnquotedfactsContext _localctx = new UnquotedfactsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_unquotedfacts);
		try {
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				match(WS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				literal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(45);
				literal();
				setState(46);
				match(CM);
				setState(47);
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

	public static class FactsContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(TheoryParser.WS, 0); }
		public List<TerminalNode> QT() { return getTokens(TheoryParser.QT); }
		public TerminalNode QT(int i) {
			return getToken(TheoryParser.QT, i);
		}
		public TerminalNode CM() { return getToken(TheoryParser.CM, 0); }
		public FactsContext facts() {
			return getRuleContext(FactsContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public FactsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_facts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).enterFacts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).exitFacts(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitFacts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactsContext facts() throws RecognitionException {
		FactsContext _localctx = new FactsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_facts);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				match(WS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				match(QT);
				setState(53);
				match(QT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(54);
				match(QT);
				setState(55);
				match(QT);
				setState(56);
				match(CM);
				setState(57);
				facts();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				match(QT);
				setState(59);
				literal();
				setState(60);
				match(QT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(62);
				match(QT);
				setState(63);
				literal();
				setState(64);
				match(QT);
				setState(65);
				match(CM);
				setState(66);
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

	public static class RulesContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(TheoryParser.WS, 0); }
		public List<TerminalNode> QT() { return getTokens(TheoryParser.QT); }
		public TerminalNode QT(int i) {
			return getToken(TheoryParser.QT, i);
		}
		public List<TerminalNode> CM() { return getTokens(TheoryParser.CM); }
		public TerminalNode CM(int i) {
			return getToken(TheoryParser.CM, i);
		}
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public List<UnquotedfactsContext> unquotedfacts() {
			return getRuleContexts(UnquotedfactsContext.class);
		}
		public UnquotedfactsContext unquotedfacts(int i) {
			return getRuleContext(UnquotedfactsContext.class,i);
		}
		public List<TerminalNode> ARROW() { return getTokens(TheoryParser.ARROW); }
		public TerminalNode ARROW(int i) {
			return getToken(TheoryParser.ARROW, i);
		}
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public RulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).exitRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_rules);
		try {
			int _alt;
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(WS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				match(QT);
				setState(72);
				match(QT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				match(QT);
				setState(74);
				match(QT);
				setState(75);
				match(CM);
				setState(76);
				rules();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(90); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						setState(90);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
						case 1:
							{
							{
							setState(77);
							match(QT);
							setState(78);
							unquotedfacts();
							setState(79);
							match(ARROW);
							setState(80);
							literal();
							setState(81);
							match(QT);
							setState(82);
							match(CM);
							}
							}
							break;
						case 2:
							{
							{
							setState(84);
							match(QT);
							setState(85);
							match(ARROW);
							setState(86);
							literal();
							setState(87);
							match(QT);
							setState(88);
							match(CM);
							}
							}
							break;
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(92); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(107);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					{
					setState(94);
					match(QT);
					setState(95);
					match(QT);
					}
					}
					break;
				case 2:
					{
					{
					setState(96);
					match(QT);
					setState(97);
					unquotedfacts();
					setState(98);
					match(ARROW);
					setState(99);
					literal();
					setState(100);
					match(QT);
					}
					}
					break;
				case 3:
					{
					{
					setState(102);
					match(QT);
					setState(103);
					match(ARROW);
					setState(104);
					literal();
					setState(105);
					match(QT);
					}
					}
					break;
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(109);
				match(QT);
				setState(110);
				unquotedfacts();
				setState(111);
				match(ARROW);
				setState(112);
				literal();
				setState(113);
				match(QT);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(115);
				match(QT);
				setState(116);
				match(ARROW);
				setState(117);
				literal();
				setState(118);
				match(QT);
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

	public static class SuprelsContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(TheoryParser.WS, 0); }
		public List<TerminalNode> QT() { return getTokens(TheoryParser.QT); }
		public TerminalNode QT(int i) {
			return getToken(TheoryParser.QT, i);
		}
		public TerminalNode CM() { return getToken(TheoryParser.CM, 0); }
		public SuprelsContext suprels() {
			return getRuleContext(SuprelsContext.class,0);
		}
		public List<TerminalNode> RULETAG() { return getTokens(TheoryParser.RULETAG); }
		public TerminalNode RULETAG(int i) {
			return getToken(TheoryParser.RULETAG, i);
		}
		public TerminalNode GT() { return getToken(TheoryParser.GT, 0); }
		public TerminalNode LT() { return getToken(TheoryParser.LT, 0); }
		public SuprelsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suprels; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).enterSuprels(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).exitSuprels(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitSuprels(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuprelsContext suprels() throws RecognitionException {
		SuprelsContext _localctx = new SuprelsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_suprels);
		int _la;
		try {
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				match(WS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				match(QT);
				setState(124);
				match(QT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(125);
				match(QT);
				setState(126);
				match(QT);
				setState(127);
				match(CM);
				setState(128);
				suprels();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(129);
				match(QT);
				setState(130);
				match(RULETAG);
				setState(131);
				_la = _input.LA(1);
				if ( !(_la==GT || _la==LT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(132);
				match(RULETAG);
				setState(133);
				match(QT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(134);
				match(QT);
				setState(135);
				match(RULETAG);
				setState(136);
				_la = _input.LA(1);
				if ( !(_la==GT || _la==LT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(137);
				match(RULETAG);
				setState(138);
				match(QT);
				setState(139);
				match(CM);
				setState(140);
				suprels();
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

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(TheoryParser.WS, 0); }
		public TerminalNode ALPHANUMERIC() { return getToken(TheoryParser.ALPHANUMERIC, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TheoryVisitor ) return ((TheoryVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			_la = _input.LA(1);
			if ( !(_la==ALPHANUMERIC || _la==WS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
		"\u0004\u0001\u0014\u0092\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00022\b\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003E\b\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0004\u0004[\b\u0004\u000b\u0004\f\u0004\\\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004l\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0003\u0004y\b\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u008e"+
		"\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0000\u0000\u0007\u0000\u0002"+
		"\u0004\u0006\b\n\f\u0000\u0002\u0001\u0000\u0010\u0011\u0002\u0000\t\t"+
		"\u0014\u0014\u009d\u0000\u000e\u0001\u0000\u0000\u0000\u0002\u0013\u0001"+
		"\u0000\u0000\u0000\u00041\u0001\u0000\u0000\u0000\u0006D\u0001\u0000\u0000"+
		"\u0000\bx\u0001\u0000\u0000\u0000\n\u008d\u0001\u0000\u0000\u0000\f\u008f"+
		"\u0001\u0000\u0000\u0000\u000e\u000f\u0005\f\u0000\u0000\u000f\u0010\u0003"+
		"\u0002\u0001\u0000\u0010\u0011\u0005\r\u0000\u0000\u0011\u0012\u0005\u0000"+
		"\u0000\u0001\u0012\u0001\u0001\u0000\u0000\u0000\u0013\u0014\u0005\u0012"+
		"\u0000\u0000\u0014\u0015\u0005\u0003\u0000\u0000\u0015\u0016\u0005\u0012"+
		"\u0000\u0000\u0016\u0017\u0005\u000b\u0000\u0000\u0017\u0018\u0005\u000e"+
		"\u0000\u0000\u0018\u0019\u0003\u0006\u0003\u0000\u0019\u001a\u0005\u000f"+
		"\u0000\u0000\u001a\u001b\u0005\u0013\u0000\u0000\u001b\u001c\u0005\u0012"+
		"\u0000\u0000\u001c\u001d\u0005\u0004\u0000\u0000\u001d\u001e\u0005\u0012"+
		"\u0000\u0000\u001e\u001f\u0005\u000b\u0000\u0000\u001f \u0005\u000e\u0000"+
		"\u0000 !\u0003\b\u0004\u0000!\"\u0005\u000f\u0000\u0000\"#\u0005\u0013"+
		"\u0000\u0000#$\u0005\u0012\u0000\u0000$%\u0005\u0005\u0000\u0000%&\u0005"+
		"\u0012\u0000\u0000&\'\u0005\u000b\u0000\u0000\'(\u0005\u000e\u0000\u0000"+
		"()\u0003\n\u0005\u0000)*\u0005\u000f\u0000\u0000*\u0003\u0001\u0000\u0000"+
		"\u0000+2\u0005\u0014\u0000\u0000,2\u0003\f\u0006\u0000-.\u0003\f\u0006"+
		"\u0000./\u0005\u0013\u0000\u0000/0\u0003\u0004\u0002\u000002\u0001\u0000"+
		"\u0000\u00001+\u0001\u0000\u0000\u00001,\u0001\u0000\u0000\u00001-\u0001"+
		"\u0000\u0000\u00002\u0005\u0001\u0000\u0000\u00003E\u0005\u0014\u0000"+
		"\u000045\u0005\u0012\u0000\u00005E\u0005\u0012\u0000\u000067\u0005\u0012"+
		"\u0000\u000078\u0005\u0012\u0000\u000089\u0005\u0013\u0000\u00009E\u0003"+
		"\u0006\u0003\u0000:;\u0005\u0012\u0000\u0000;<\u0003\f\u0006\u0000<=\u0005"+
		"\u0012\u0000\u0000=E\u0001\u0000\u0000\u0000>?\u0005\u0012\u0000\u0000"+
		"?@\u0003\f\u0006\u0000@A\u0005\u0012\u0000\u0000AB\u0005\u0013\u0000\u0000"+
		"BC\u0003\u0006\u0003\u0000CE\u0001\u0000\u0000\u0000D3\u0001\u0000\u0000"+
		"\u0000D4\u0001\u0000\u0000\u0000D6\u0001\u0000\u0000\u0000D:\u0001\u0000"+
		"\u0000\u0000D>\u0001\u0000\u0000\u0000E\u0007\u0001\u0000\u0000\u0000"+
		"Fy\u0005\u0014\u0000\u0000GH\u0005\u0012\u0000\u0000Hy\u0005\u0012\u0000"+
		"\u0000IJ\u0005\u0012\u0000\u0000JK\u0005\u0012\u0000\u0000KL\u0005\u0013"+
		"\u0000\u0000Ly\u0003\b\u0004\u0000MN\u0005\u0012\u0000\u0000NO\u0003\u0004"+
		"\u0002\u0000OP\u0005\n\u0000\u0000PQ\u0003\f\u0006\u0000QR\u0005\u0012"+
		"\u0000\u0000RS\u0005\u0013\u0000\u0000S[\u0001\u0000\u0000\u0000TU\u0005"+
		"\u0012\u0000\u0000UV\u0005\n\u0000\u0000VW\u0003\f\u0006\u0000WX\u0005"+
		"\u0012\u0000\u0000XY\u0005\u0013\u0000\u0000Y[\u0001\u0000\u0000\u0000"+
		"ZM\u0001\u0000\u0000\u0000ZT\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000"+
		"\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]k\u0001\u0000"+
		"\u0000\u0000^_\u0005\u0012\u0000\u0000_l\u0005\u0012\u0000\u0000`a\u0005"+
		"\u0012\u0000\u0000ab\u0003\u0004\u0002\u0000bc\u0005\n\u0000\u0000cd\u0003"+
		"\f\u0006\u0000de\u0005\u0012\u0000\u0000el\u0001\u0000\u0000\u0000fg\u0005"+
		"\u0012\u0000\u0000gh\u0005\n\u0000\u0000hi\u0003\f\u0006\u0000ij\u0005"+
		"\u0012\u0000\u0000jl\u0001\u0000\u0000\u0000k^\u0001\u0000\u0000\u0000"+
		"k`\u0001\u0000\u0000\u0000kf\u0001\u0000\u0000\u0000ly\u0001\u0000\u0000"+
		"\u0000mn\u0005\u0012\u0000\u0000no\u0003\u0004\u0002\u0000op\u0005\n\u0000"+
		"\u0000pq\u0003\f\u0006\u0000qr\u0005\u0012\u0000\u0000ry\u0001\u0000\u0000"+
		"\u0000st\u0005\u0012\u0000\u0000tu\u0005\n\u0000\u0000uv\u0003\f\u0006"+
		"\u0000vw\u0005\u0012\u0000\u0000wy\u0001\u0000\u0000\u0000xF\u0001\u0000"+
		"\u0000\u0000xG\u0001\u0000\u0000\u0000xI\u0001\u0000\u0000\u0000xZ\u0001"+
		"\u0000\u0000\u0000xm\u0001\u0000\u0000\u0000xs\u0001\u0000\u0000\u0000"+
		"y\t\u0001\u0000\u0000\u0000z\u008e\u0005\u0014\u0000\u0000{|\u0005\u0012"+
		"\u0000\u0000|\u008e\u0005\u0012\u0000\u0000}~\u0005\u0012\u0000\u0000"+
		"~\u007f\u0005\u0012\u0000\u0000\u007f\u0080\u0005\u0013\u0000\u0000\u0080"+
		"\u008e\u0003\n\u0005\u0000\u0081\u0082\u0005\u0012\u0000\u0000\u0082\u0083"+
		"\u0005\u0006\u0000\u0000\u0083\u0084\u0007\u0000\u0000\u0000\u0084\u0085"+
		"\u0005\u0006\u0000\u0000\u0085\u008e\u0005\u0012\u0000\u0000\u0086\u0087"+
		"\u0005\u0012\u0000\u0000\u0087\u0088\u0005\u0006\u0000\u0000\u0088\u0089"+
		"\u0007\u0000\u0000\u0000\u0089\u008a\u0005\u0006\u0000\u0000\u008a\u008b"+
		"\u0005\u0012\u0000\u0000\u008b\u008c\u0005\u0013\u0000\u0000\u008c\u008e"+
		"\u0003\n\u0005\u0000\u008dz\u0001\u0000\u0000\u0000\u008d{\u0001\u0000"+
		"\u0000\u0000\u008d}\u0001\u0000\u0000\u0000\u008d\u0081\u0001\u0000\u0000"+
		"\u0000\u008d\u0086\u0001\u0000\u0000\u0000\u008e\u000b\u0001\u0000\u0000"+
		"\u0000\u008f\u0090\u0007\u0001\u0000\u0000\u0090\r\u0001\u0000\u0000\u0000"+
		"\u00071DZ\\kx\u008d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}