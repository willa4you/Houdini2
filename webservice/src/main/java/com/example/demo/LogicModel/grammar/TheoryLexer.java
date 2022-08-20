package com.example.demo.LogicModel.grammar;
// Generated from Theory.g4 by ANTLR 4.10.1
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
		VERSION=1, LOGICMODEL=2, FACTS=3, RULES=4, SUPREL=5, NATURAL=6, DECIMAL=7, 
		ALPHANUMERIC=8, ARROW=9, RULETAG=10, CLN=11, LBRACE=12, RBRACE=13, LSQUARE=14, 
		RSQUARE=15, GT=16, LT=17, QT=18, CM=19, WS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"VERSION", "LOGICMODEL", "FACTS", "RULES", "SUPREL", "NATURAL", "DECIMAL", 
			"ALPHANUMERIC", "ARROW", "RULETAG", "CLN", "LBRACE", "RBRACE", "LSQUARE", 
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
		"\u0004\u0000\u0014\u009d\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
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
		"U\b\u0005\n\u0005\f\u0005X\t\u0005\u0003\u0005Z\b\u0005\u0001\u0006\u0004"+
		"\u0006]\b\u0006\u000b\u0006\f\u0006^\u0001\u0006\u0005\u0006b\b\u0006"+
		"\n\u0006\f\u0006e\t\u0006\u0001\u0006\u0001\u0006\u0004\u0006i\b\u0006"+
		"\u000b\u0006\f\u0006j\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0004\u0006q\b\u0006\u000b\u0006\f\u0006r\u0003\u0006u\b\u0006\u0001"+
		"\u0007\u0003\u0007x\b\u0007\u0001\u0007\u0004\u0007{\b\u0007\u000b\u0007"+
		"\f\u0007|\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0004\u0013\u0098\b\u0013"+
		"\u000b\u0013\f\u0013\u0099\u0001\u0013\u0001\u0013\u0000\u0000\u0014\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d"+
		"\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014\u0001\u0000\u0006\u0001"+
		"\u000019\u0001\u000009\u0001\u0000~~\u0003\u0000AZ__az\u0003\u0000--="+
		"=~~\u0003\u0000\t\n\r\r  \u00a6\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0001)\u0001\u0000\u0000\u0000\u00031\u0001\u0000\u0000\u0000\u0005<"+
		"\u0001\u0000\u0000\u0000\u0007B\u0001\u0000\u0000\u0000\tH\u0001\u0000"+
		"\u0000\u0000\u000bY\u0001\u0000\u0000\u0000\rt\u0001\u0000\u0000\u0000"+
		"\u000fw\u0001\u0000\u0000\u0000\u0011~\u0001\u0000\u0000\u0000\u0013\u0081"+
		"\u0001\u0000\u0000\u0000\u0015\u0084\u0001\u0000\u0000\u0000\u0017\u0086"+
		"\u0001\u0000\u0000\u0000\u0019\u0088\u0001\u0000\u0000\u0000\u001b\u008a"+
		"\u0001\u0000\u0000\u0000\u001d\u008c\u0001\u0000\u0000\u0000\u001f\u008e"+
		"\u0001\u0000\u0000\u0000!\u0090\u0001\u0000\u0000\u0000#\u0092\u0001\u0000"+
		"\u0000\u0000%\u0094\u0001\u0000\u0000\u0000\'\u0097\u0001\u0000\u0000"+
		"\u0000)*\u0005v\u0000\u0000*+\u0005e\u0000\u0000+,\u0005r\u0000\u0000"+
		",-\u0005s\u0000\u0000-.\u0005i\u0000\u0000./\u0005o\u0000\u0000/0\u0005"+
		"n\u0000\u00000\u0002\u0001\u0000\u0000\u000012\u0005l\u0000\u000023\u0005"+
		"o\u0000\u000034\u0005g\u0000\u000045\u0005i\u0000\u000056\u0005c\u0000"+
		"\u000067\u0005M\u0000\u000078\u0005o\u0000\u000089\u0005d\u0000\u0000"+
		"9:\u0005e\u0000\u0000:;\u0005l\u0000\u0000;\u0004\u0001\u0000\u0000\u0000"+
		"<=\u0005f\u0000\u0000=>\u0005a\u0000\u0000>?\u0005c\u0000\u0000?@\u0005"+
		"t\u0000\u0000@A\u0005s\u0000\u0000A\u0006\u0001\u0000\u0000\u0000BC\u0005"+
		"r\u0000\u0000CD\u0005u\u0000\u0000DE\u0005l\u0000\u0000EF\u0005e\u0000"+
		"\u0000FG\u0005s\u0000\u0000G\b\u0001\u0000\u0000\u0000HI\u0005s\u0000"+
		"\u0000IJ\u0005u\u0000\u0000JK\u0005p\u0000\u0000KL\u0005R\u0000\u0000"+
		"LM\u0005u\u0000\u0000MN\u0005l\u0000\u0000NO\u0005e\u0000\u0000OP\u0005"+
		"s\u0000\u0000P\n\u0001\u0000\u0000\u0000QZ\u00050\u0000\u0000RV\u0007"+
		"\u0000\u0000\u0000SU\u0007\u0001\u0000\u0000TS\u0001\u0000\u0000\u0000"+
		"UX\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000"+
		"\u0000WZ\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000YQ\u0001\u0000"+
		"\u0000\u0000YR\u0001\u0000\u0000\u0000Z\f\u0001\u0000\u0000\u0000[]\u0007"+
		"\u0000\u0000\u0000\\[\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000"+
		"^\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_c\u0001\u0000\u0000"+
		"\u0000`b\u0007\u0001\u0000\u0000a`\u0001\u0000\u0000\u0000be\u0001\u0000"+
		"\u0000\u0000ca\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000df\u0001"+
		"\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000fh\u0005.\u0000\u0000gi\u0007"+
		"\u0001\u0000\u0000hg\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000"+
		"jh\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000ku\u0001\u0000\u0000"+
		"\u0000lm\u00050\u0000\u0000mn\u0005.\u0000\u0000np\u0001\u0000\u0000\u0000"+
		"oq\u0007\u0001\u0000\u0000po\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000"+
		"\u0000rp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000su\u0001\u0000"+
		"\u0000\u0000t\\\u0001\u0000\u0000\u0000tl\u0001\u0000\u0000\u0000u\u000e"+
		"\u0001\u0000\u0000\u0000vx\u0007\u0002\u0000\u0000wv\u0001\u0000\u0000"+
		"\u0000wx\u0001\u0000\u0000\u0000xz\u0001\u0000\u0000\u0000y{\u0007\u0003"+
		"\u0000\u0000zy\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|z\u0001"+
		"\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}\u0010\u0001\u0000\u0000"+
		"\u0000~\u007f\u0007\u0004\u0000\u0000\u007f\u0080\u0005>\u0000\u0000\u0080"+
		"\u0012\u0001\u0000\u0000\u0000\u0081\u0082\u0005r\u0000\u0000\u0082\u0083"+
		"\u0003\u000b\u0005\u0000\u0083\u0014\u0001\u0000\u0000\u0000\u0084\u0085"+
		"\u0005:\u0000\u0000\u0085\u0016\u0001\u0000\u0000\u0000\u0086\u0087\u0005"+
		"{\u0000\u0000\u0087\u0018\u0001\u0000\u0000\u0000\u0088\u0089\u0005}\u0000"+
		"\u0000\u0089\u001a\u0001\u0000\u0000\u0000\u008a\u008b\u0005[\u0000\u0000"+
		"\u008b\u001c\u0001\u0000\u0000\u0000\u008c\u008d\u0005]\u0000\u0000\u008d"+
		"\u001e\u0001\u0000\u0000\u0000\u008e\u008f\u0005>\u0000\u0000\u008f \u0001"+
		"\u0000\u0000\u0000\u0090\u0091\u0005<\u0000\u0000\u0091\"\u0001\u0000"+
		"\u0000\u0000\u0092\u0093\u0005\"\u0000\u0000\u0093$\u0001\u0000\u0000"+
		"\u0000\u0094\u0095\u0005,\u0000\u0000\u0095&\u0001\u0000\u0000\u0000\u0096"+
		"\u0098\u0007\u0005\u0000\u0000\u0097\u0096\u0001\u0000\u0000\u0000\u0098"+
		"\u0099\u0001\u0000\u0000\u0000\u0099\u0097\u0001\u0000\u0000\u0000\u0099"+
		"\u009a\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b"+
		"\u009c\u0006\u0013\u0000\u0000\u009c(\u0001\u0000\u0000\u0000\u000b\u0000"+
		"VY^cjrtw|\u0099\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}