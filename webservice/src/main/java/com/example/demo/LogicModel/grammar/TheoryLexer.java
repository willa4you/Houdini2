package com.example.demo.LogicModel.grammar;
// Generated from ./Theory.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TheoryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VERSION=1, LOGICMODEL=2, FACTS=3, RULES=4, SUPREL=5, RULETAG=6, NATURAL=7, 
		DECIMAL=8, ALPHANUMERIC=9, ARROW=10, CLN=11, LBRACE=12, RBRACE=13, LSQUARE=14, 
		RSQUARE=15, GT=16, LT=17, QT=18, CM=19, WS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"VERSION", "LOGICMODEL", "FACTS", "RULES", "SUPREL", "RULETAG", "NATURAL", 
			"DECIMAL", "ALPHANUMERIC", "ARROW", "CLN", "LBRACE", "RBRACE", "LSQUARE", 
			"RSQUARE", "GT", "LT", "QT", "CM", "WS"
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


	public TheoryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Theory.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0014\u009f\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005"+
		"U\b\u0005\n\u0005\f\u0005X\t\u0005\u0001\u0006\u0001\u0006\u0005\u0006"+
		"\\\b\u0006\n\u0006\f\u0006_\t\u0006\u0001\u0007\u0004\u0007b\b\u0007\u000b"+
		"\u0007\f\u0007c\u0001\u0007\u0005\u0007g\b\u0007\n\u0007\f\u0007j\t\u0007"+
		"\u0001\u0007\u0001\u0007\u0004\u0007n\b\u0007\u000b\u0007\f\u0007o\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0004\u0007v\b\u0007\u000b"+
		"\u0007\f\u0007w\u0003\u0007z\b\u0007\u0001\b\u0003\b}\b\b\u0001\b\u0004"+
		"\b\u0080\b\b\u000b\b\f\b\u0081\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0004\u0013\u009a\b\u0013\u000b"+
		"\u0013\f\u0013\u009b\u0001\u0013\u0001\u0013\u0000\u0000\u0014\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f"+
		"\u001f\u0010!\u0011#\u0012%\u0013\'\u0014\u0001\u0000\u0007\u0001\u0000"+
		"rr\u0001\u000019\u0001\u000009\u0001\u0000~~\t\u000009AZ__az\u00e0\u00e0"+
		"\u00e8\u00e9\u00ec\u00ec\u00f2\u00f2\u00f9\u00f9\u0003\u0000--==~~\u0003"+
		"\u0000\t\n\r\r  \u00a8\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000"+
		"\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000"+
		"\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000"+
		"\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0001"+
		")\u0001\u0000\u0000\u0000\u00031\u0001\u0000\u0000\u0000\u0005<\u0001"+
		"\u0000\u0000\u0000\u0007B\u0001\u0000\u0000\u0000\tH\u0001\u0000\u0000"+
		"\u0000\u000bQ\u0001\u0000\u0000\u0000\rY\u0001\u0000\u0000\u0000\u000f"+
		"y\u0001\u0000\u0000\u0000\u0011|\u0001\u0000\u0000\u0000\u0013\u0083\u0001"+
		"\u0000\u0000\u0000\u0015\u0086\u0001\u0000\u0000\u0000\u0017\u0088\u0001"+
		"\u0000\u0000\u0000\u0019\u008a\u0001\u0000\u0000\u0000\u001b\u008c\u0001"+
		"\u0000\u0000\u0000\u001d\u008e\u0001\u0000\u0000\u0000\u001f\u0090\u0001"+
		"\u0000\u0000\u0000!\u0092\u0001\u0000\u0000\u0000#\u0094\u0001\u0000\u0000"+
		"\u0000%\u0096\u0001\u0000\u0000\u0000\'\u0099\u0001\u0000\u0000\u0000"+
		")*\u0005v\u0000\u0000*+\u0005e\u0000\u0000+,\u0005r\u0000\u0000,-\u0005"+
		"s\u0000\u0000-.\u0005i\u0000\u0000./\u0005o\u0000\u0000/0\u0005n\u0000"+
		"\u00000\u0002\u0001\u0000\u0000\u000012\u0005l\u0000\u000023\u0005o\u0000"+
		"\u000034\u0005g\u0000\u000045\u0005i\u0000\u000056\u0005c\u0000\u0000"+
		"67\u0005M\u0000\u000078\u0005o\u0000\u000089\u0005d\u0000\u00009:\u0005"+
		"e\u0000\u0000:;\u0005l\u0000\u0000;\u0004\u0001\u0000\u0000\u0000<=\u0005"+
		"f\u0000\u0000=>\u0005a\u0000\u0000>?\u0005c\u0000\u0000?@\u0005t\u0000"+
		"\u0000@A\u0005s\u0000\u0000A\u0006\u0001\u0000\u0000\u0000BC\u0005r\u0000"+
		"\u0000CD\u0005u\u0000\u0000DE\u0005l\u0000\u0000EF\u0005e\u0000\u0000"+
		"FG\u0005s\u0000\u0000G\b\u0001\u0000\u0000\u0000HI\u0005s\u0000\u0000"+
		"IJ\u0005u\u0000\u0000JK\u0005p\u0000\u0000KL\u0005R\u0000\u0000LM\u0005"+
		"u\u0000\u0000MN\u0005l\u0000\u0000NO\u0005e\u0000\u0000OP\u0005s\u0000"+
		"\u0000P\n\u0001\u0000\u0000\u0000QR\u0007\u0000\u0000\u0000RV\u0007\u0001"+
		"\u0000\u0000SU\u0007\u0002\u0000\u0000TS\u0001\u0000\u0000\u0000UX\u0001"+
		"\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000"+
		"W\f\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000Y]\u0007\u0001\u0000"+
		"\u0000Z\\\u0007\u0002\u0000\u0000[Z\u0001\u0000\u0000\u0000\\_\u0001\u0000"+
		"\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^\u000e"+
		"\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000`b\u0007\u0001\u0000"+
		"\u0000a`\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000ca\u0001\u0000"+
		"\u0000\u0000cd\u0001\u0000\u0000\u0000dh\u0001\u0000\u0000\u0000eg\u0007"+
		"\u0002\u0000\u0000fe\u0001\u0000\u0000\u0000gj\u0001\u0000\u0000\u0000"+
		"hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000ik\u0001\u0000\u0000"+
		"\u0000jh\u0001\u0000\u0000\u0000km\u0005.\u0000\u0000ln\u0007\u0002\u0000"+
		"\u0000ml\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000om\u0001\u0000"+
		"\u0000\u0000op\u0001\u0000\u0000\u0000pz\u0001\u0000\u0000\u0000qr\u0005"+
		"0\u0000\u0000rs\u0005.\u0000\u0000su\u0001\u0000\u0000\u0000tv\u0007\u0002"+
		"\u0000\u0000ut\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000wu\u0001"+
		"\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xz\u0001\u0000\u0000\u0000"+
		"ya\u0001\u0000\u0000\u0000yq\u0001\u0000\u0000\u0000z\u0010\u0001\u0000"+
		"\u0000\u0000{}\u0007\u0003\u0000\u0000|{\u0001\u0000\u0000\u0000|}\u0001"+
		"\u0000\u0000\u0000}\u007f\u0001\u0000\u0000\u0000~\u0080\u0007\u0004\u0000"+
		"\u0000\u007f~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000"+
		"\u0081\u007f\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000\u0000"+
		"\u0082\u0012\u0001\u0000\u0000\u0000\u0083\u0084\u0007\u0005\u0000\u0000"+
		"\u0084\u0085\u0005>\u0000\u0000\u0085\u0014\u0001\u0000\u0000\u0000\u0086"+
		"\u0087\u0005:\u0000\u0000\u0087\u0016\u0001\u0000\u0000\u0000\u0088\u0089"+
		"\u0005{\u0000\u0000\u0089\u0018\u0001\u0000\u0000\u0000\u008a\u008b\u0005"+
		"}\u0000\u0000\u008b\u001a\u0001\u0000\u0000\u0000\u008c\u008d\u0005[\u0000"+
		"\u0000\u008d\u001c\u0001\u0000\u0000\u0000\u008e\u008f\u0005]\u0000\u0000"+
		"\u008f\u001e\u0001\u0000\u0000\u0000\u0090\u0091\u0005>\u0000\u0000\u0091"+
		" \u0001\u0000\u0000\u0000\u0092\u0093\u0005<\u0000\u0000\u0093\"\u0001"+
		"\u0000\u0000\u0000\u0094\u0095\u0005\"\u0000\u0000\u0095$\u0001\u0000"+
		"\u0000\u0000\u0096\u0097\u0005,\u0000\u0000\u0097&\u0001\u0000\u0000\u0000"+
		"\u0098\u009a\u0007\u0006\u0000\u0000\u0099\u0098\u0001\u0000\u0000\u0000"+
		"\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000\u0000"+
		"\u009b\u009c\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000"+
		"\u009d\u009e\u0006\u0013\u0000\u0000\u009e(\u0001\u0000\u0000\u0000\u000b"+
		"\u0000V]chowy|\u0081\u009b\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}