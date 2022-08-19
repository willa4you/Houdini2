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
		RULE_theory = 0, RULE_version = 1, RULE_logicmodel = 2, RULE_facts = 3, 
		RULE_unquotedfacts = 4, RULE_rules = 5, RULE_suprel = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"theory", "version", "logicmodel", "facts", "unquotedfacts", "rules", 
			"suprel"
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).enterTheory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).exitTheory(this);
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
			version();
			setState(16);
			match(CM);
			setState(17);
			logicmodel();
			setState(18);
			match(RBRACE);
			setState(19);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).enterVersion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).exitVersion(this);
		}
	}

	public final VersionContext version() throws RecognitionException {
		VersionContext _localctx = new VersionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_version);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			match(QT);
			setState(22);
			match(VERSION);
			setState(23);
			match(QT);
			setState(24);
			match(CLN);
			setState(25);
			match(QT);
			setState(26);
			match(DECIMAL);
			setState(27);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).enterLogicmodel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).exitLogicmodel(this);
		}
	}

	public final LogicmodelContext logicmodel() throws RecognitionException {
		LogicmodelContext _localctx = new LogicmodelContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_logicmodel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(QT);
			setState(30);
			match(LOGICMODEL);
			setState(31);
			match(QT);
			setState(32);
			match(CLN);
			setState(33);
			match(LBRACE);
			setState(34);
			match(QT);
			setState(35);
			match(FACTS);
			setState(36);
			match(QT);
			setState(37);
			match(CLN);
			setState(38);
			match(LSQUARE);
			setState(39);
			facts();
			setState(40);
			match(RSQUARE);
			setState(41);
			match(CM);
			setState(42);
			match(QT);
			setState(43);
			match(RULES);
			setState(44);
			match(QT);
			setState(45);
			match(CLN);
			setState(46);
			match(LSQUARE);
			setState(47);
			rules();
			setState(48);
			match(RSQUARE);
			setState(49);
			match(CM);
			setState(50);
			match(QT);
			setState(51);
			match(SUPREL);
			setState(52);
			match(QT);
			setState(53);
			match(CLN);
			setState(54);
			match(LSQUARE);
			setState(55);
			suprel();
			setState(56);
			match(RSQUARE);
			setState(57);
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

	public static class FactsContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(TheoryParser.WS, 0); }
		public List<TerminalNode> QT() { return getTokens(TheoryParser.QT); }
		public TerminalNode QT(int i) {
			return getToken(TheoryParser.QT, i);
		}
		public TerminalNode ALPHANUMERIC() { return getToken(TheoryParser.ALPHANUMERIC, 0); }
		public TerminalNode CM() { return getToken(TheoryParser.CM, 0); }
		public FactsContext facts() {
			return getRuleContext(FactsContext.class,0);
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
	}

	public final FactsContext facts() throws RecognitionException {
		FactsContext _localctx = new FactsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_facts);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				match(WS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				match(QT);
				setState(61);
				match(ALPHANUMERIC);
				setState(62);
				match(QT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				match(QT);
				setState(64);
				match(ALPHANUMERIC);
				setState(65);
				match(QT);
				setState(66);
				match(CM);
				setState(67);
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
		public TerminalNode ALPHANUMERIC() { return getToken(TheoryParser.ALPHANUMERIC, 0); }
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
	}

	public final UnquotedfactsContext unquotedfacts() throws RecognitionException {
		UnquotedfactsContext _localctx = new UnquotedfactsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_unquotedfacts);
		try {
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
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
				match(ALPHANUMERIC);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				match(ALPHANUMERIC);
				setState(73);
				match(CM);
				setState(74);
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
		public TerminalNode ALPHANUMERIC() { return getToken(TheoryParser.ALPHANUMERIC, 0); }
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).exitRules(this);
		}
	}

	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_rules);
		try {
			setState(96);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				match(WS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				match(QT);
				setState(81);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(79);
					match(WS);
					}
					break;
				case 2:
					{
					setState(80);
					unquotedfacts();
					}
					break;
				}
				setState(83);
				match(ARROW);
				setState(84);
				match(ALPHANUMERIC);
				setState(85);
				match(QT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
				match(QT);
				setState(89);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(87);
					match(WS);
					}
					break;
				case 2:
					{
					setState(88);
					unquotedfacts();
					}
					break;
				}
				setState(91);
				match(ARROW);
				setState(92);
				match(ALPHANUMERIC);
				setState(93);
				match(QT);
				setState(94);
				match(CM);
				setState(95);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).enterSuprel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TheoryListener ) ((TheoryListener)listener).exitSuprel(this);
		}
	}

	public final SuprelContext suprel() throws RecognitionException {
		SuprelContext _localctx = new SuprelContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_suprel);
		int _la;
		try {
			setState(111);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				match(WS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				match(QT);
				setState(100);
				match(RULETAG);
				setState(101);
				_la = _input.LA(1);
				if ( !(_la==GT || _la==LT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(102);
				match(RULETAG);
				setState(103);
				match(QT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(104);
				match(QT);
				setState(105);
				match(RULETAG);
				setState(106);
				_la = _input.LA(1);
				if ( !(_la==GT || _la==LT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(107);
				match(RULETAG);
				setState(108);
				match(QT);
				setState(109);
				match(CM);
				setState(110);
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
		"\u0004\u0001\u0014r\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003E\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004L\b"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005R\b"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005Z\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005a\b\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006p\b"+
		"\u0006\u0001\u0006\u0000\u0000\u0007\u0000\u0002\u0004\u0006\b\n\f\u0000"+
		"\u0001\u0001\u0000\u0010\u0011t\u0000\u000e\u0001\u0000\u0000\u0000\u0002"+
		"\u0015\u0001\u0000\u0000\u0000\u0004\u001d\u0001\u0000\u0000\u0000\u0006"+
		"D\u0001\u0000\u0000\u0000\bK\u0001\u0000\u0000\u0000\n`\u0001\u0000\u0000"+
		"\u0000\fo\u0001\u0000\u0000\u0000\u000e\u000f\u0005\f\u0000\u0000\u000f"+
		"\u0010\u0003\u0002\u0001\u0000\u0010\u0011\u0005\u0013\u0000\u0000\u0011"+
		"\u0012\u0003\u0004\u0002\u0000\u0012\u0013\u0005\r\u0000\u0000\u0013\u0014"+
		"\u0005\u0000\u0000\u0001\u0014\u0001\u0001\u0000\u0000\u0000\u0015\u0016"+
		"\u0005\u0012\u0000\u0000\u0016\u0017\u0005\u0001\u0000\u0000\u0017\u0018"+
		"\u0005\u0012\u0000\u0000\u0018\u0019\u0005\u000b\u0000\u0000\u0019\u001a"+
		"\u0005\u0012\u0000\u0000\u001a\u001b\u0005\u0007\u0000\u0000\u001b\u001c"+
		"\u0005\u0012\u0000\u0000\u001c\u0003\u0001\u0000\u0000\u0000\u001d\u001e"+
		"\u0005\u0012\u0000\u0000\u001e\u001f\u0005\u0002\u0000\u0000\u001f \u0005"+
		"\u0012\u0000\u0000 !\u0005\u000b\u0000\u0000!\"\u0005\f\u0000\u0000\""+
		"#\u0005\u0012\u0000\u0000#$\u0005\u0003\u0000\u0000$%\u0005\u0012\u0000"+
		"\u0000%&\u0005\u000b\u0000\u0000&\'\u0005\u000e\u0000\u0000\'(\u0003\u0006"+
		"\u0003\u0000()\u0005\u000f\u0000\u0000)*\u0005\u0013\u0000\u0000*+\u0005"+
		"\u0012\u0000\u0000+,\u0005\u0004\u0000\u0000,-\u0005\u0012\u0000\u0000"+
		"-.\u0005\u000b\u0000\u0000./\u0005\u000e\u0000\u0000/0\u0003\n\u0005\u0000"+
		"01\u0005\u000f\u0000\u000012\u0005\u0013\u0000\u000023\u0005\u0012\u0000"+
		"\u000034\u0005\u0005\u0000\u000045\u0005\u0012\u0000\u000056\u0005\u000b"+
		"\u0000\u000067\u0005\u000e\u0000\u000078\u0003\f\u0006\u000089\u0005\u000f"+
		"\u0000\u00009:\u0005\r\u0000\u0000:\u0005\u0001\u0000\u0000\u0000;E\u0005"+
		"\u0014\u0000\u0000<=\u0005\u0012\u0000\u0000=>\u0005\b\u0000\u0000>E\u0005"+
		"\u0012\u0000\u0000?@\u0005\u0012\u0000\u0000@A\u0005\b\u0000\u0000AB\u0005"+
		"\u0012\u0000\u0000BC\u0005\u0013\u0000\u0000CE\u0003\u0006\u0003\u0000"+
		"D;\u0001\u0000\u0000\u0000D<\u0001\u0000\u0000\u0000D?\u0001\u0000\u0000"+
		"\u0000E\u0007\u0001\u0000\u0000\u0000FL\u0005\u0014\u0000\u0000GL\u0005"+
		"\b\u0000\u0000HI\u0005\b\u0000\u0000IJ\u0005\u0013\u0000\u0000JL\u0003"+
		"\b\u0004\u0000KF\u0001\u0000\u0000\u0000KG\u0001\u0000\u0000\u0000KH\u0001"+
		"\u0000\u0000\u0000L\t\u0001\u0000\u0000\u0000Ma\u0005\u0014\u0000\u0000"+
		"NQ\u0005\u0012\u0000\u0000OR\u0005\u0014\u0000\u0000PR\u0003\b\u0004\u0000"+
		"QO\u0001\u0000\u0000\u0000QP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000"+
		"\u0000ST\u0005\t\u0000\u0000TU\u0005\b\u0000\u0000Ua\u0005\u0012\u0000"+
		"\u0000VY\u0005\u0012\u0000\u0000WZ\u0005\u0014\u0000\u0000XZ\u0003\b\u0004"+
		"\u0000YW\u0001\u0000\u0000\u0000YX\u0001\u0000\u0000\u0000Z[\u0001\u0000"+
		"\u0000\u0000[\\\u0005\t\u0000\u0000\\]\u0005\b\u0000\u0000]^\u0005\u0012"+
		"\u0000\u0000^_\u0005\u0013\u0000\u0000_a\u0003\n\u0005\u0000`M\u0001\u0000"+
		"\u0000\u0000`N\u0001\u0000\u0000\u0000`V\u0001\u0000\u0000\u0000a\u000b"+
		"\u0001\u0000\u0000\u0000bp\u0005\u0014\u0000\u0000cd\u0005\u0012\u0000"+
		"\u0000de\u0005\n\u0000\u0000ef\u0007\u0000\u0000\u0000fg\u0005\n\u0000"+
		"\u0000gp\u0005\u0012\u0000\u0000hi\u0005\u0012\u0000\u0000ij\u0005\n\u0000"+
		"\u0000jk\u0007\u0000\u0000\u0000kl\u0005\n\u0000\u0000lm\u0005\u0012\u0000"+
		"\u0000mn\u0005\u0013\u0000\u0000np\u0003\f\u0006\u0000ob\u0001\u0000\u0000"+
		"\u0000oc\u0001\u0000\u0000\u0000oh\u0001\u0000\u0000\u0000p\r\u0001\u0000"+
		"\u0000\u0000\u0006DKQY`o";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}