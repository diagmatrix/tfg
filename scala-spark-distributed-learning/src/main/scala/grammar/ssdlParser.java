// Generated from ssdl.g4 by ANTLR 4.13.1
package grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ssdlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, OBJECT=22, NET_TYPE=23, COMMENT=24, 
		INT=25, FLOAT=26, ID=27, STRING=28, FILE=29, WS=30;
	public static final int
		RULE_program = 0, RULE_stmt = 1, RULE_action = 2, RULE_parameter = 3, 
		RULE_ann_argument = 4, RULE_dapso_argument = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stmt", "action", "parameter", "ann_argument", "dapso_argument"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'begin'", "'end'", "'fit'", "'out'", "':'", "'set'", "'type'", 
			"'neurons'", "'['", "','", "']'", "'data'", "'algorithm'", "'particles'", 
			"'batch_size'", "'n_rdd'", "'pos_bound'", "'vel_bound'", "'vel_w'", "'vel_c1'", 
			"'vel_c2'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "OBJECT", 
			"NET_TYPE", "COMMENT", "INT", "FLOAT", "ID", "STRING", "FILE", "WS"
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
	public String getGrammarFileName() { return "ssdl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ssdlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<TerminalNode> COMMENT() { return getTokens(ssdlParser.COMMENT); }
		public TerminalNode COMMENT(int i) {
			return getToken(ssdlParser.COMMENT, i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ssdlListener ) ((ssdlListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ssdlListener ) ((ssdlListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ssdlVisitor ) return ((ssdlVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(14);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
				case T__2:
				case T__3:
					{
					setState(12);
					stmt();
					}
					break;
				case COMMENT:
					{
					setState(13);
					match(COMMENT);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(16); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16777242L) != 0) );
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

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public TerminalNode ID() { return getToken(ssdlParser.ID, 0); }
		public TerminalNode INT() { return getToken(ssdlParser.INT, 0); }
		public TerminalNode FILE() { return getToken(ssdlParser.FILE, 0); }
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ssdlListener ) ((ssdlListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ssdlListener ) ((ssdlListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ssdlVisitor ) return ((ssdlVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stmt);
		int _la;
		try {
			setState(30);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				match(T__0);
				setState(19);
				action();
				setState(20);
				match(T__1);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(22);
				match(T__2);
				setState(23);
				match(ID);
				setState(24);
				match(INT);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 3);
				{
				setState(25);
				match(T__3);
				setState(26);
				match(ID);
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FILE) {
					{
					setState(27);
					match(FILE);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ActionContext extends ParserRuleContext {
		public TerminalNode OBJECT() { return getToken(ssdlParser.OBJECT, 0); }
		public TerminalNode ID() { return getToken(ssdlParser.ID, 0); }
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ssdlListener ) ((ssdlListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ssdlListener ) ((ssdlListener)listener).exitAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ssdlVisitor ) return ((ssdlVisitor<? extends T>)visitor).visitAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(OBJECT);
			setState(33);
			match(T__4);
			setState(34);
			match(ID);
			setState(36); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(35);
				parameter();
				}
				}
				setState(38); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__5 );
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

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterContext extends ParserRuleContext {
		public Ann_argumentContext ann_argument() {
			return getRuleContext(Ann_argumentContext.class,0);
		}
		public Dapso_argumentContext dapso_argument() {
			return getRuleContext(Dapso_argumentContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ssdlListener ) ((ssdlListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ssdlListener ) ((ssdlListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ssdlVisitor ) return ((ssdlVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(T__5);
			setState(43);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
			case T__7:
			case T__11:
			case T__12:
				{
				setState(41);
				ann_argument();
				}
				break;
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
				{
				setState(42);
				dapso_argument();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Ann_argumentContext extends ParserRuleContext {
		public TerminalNode NET_TYPE() { return getToken(ssdlParser.NET_TYPE, 0); }
		public List<TerminalNode> INT() { return getTokens(ssdlParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(ssdlParser.INT, i);
		}
		public List<TerminalNode> FILE() { return getTokens(ssdlParser.FILE); }
		public TerminalNode FILE(int i) {
			return getToken(ssdlParser.FILE, i);
		}
		public TerminalNode ID() { return getToken(ssdlParser.ID, 0); }
		public Ann_argumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ann_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ssdlListener ) ((ssdlListener)listener).enterAnn_argument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ssdlListener ) ((ssdlListener)listener).exitAnn_argument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ssdlVisitor ) return ((ssdlVisitor<? extends T>)visitor).visitAnn_argument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ann_argumentContext ann_argument() throws RecognitionException {
		Ann_argumentContext _localctx = new Ann_argumentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ann_argument);
		try {
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(45);
				match(T__6);
				setState(46);
				match(NET_TYPE);
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				match(T__7);
				setState(48);
				match(T__8);
				setState(49);
				match(INT);
				setState(50);
				match(T__9);
				setState(51);
				match(INT);
				setState(52);
				match(T__10);
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 3);
				{
				setState(53);
				match(T__11);
				setState(54);
				match(T__8);
				setState(55);
				match(FILE);
				setState(56);
				match(T__9);
				setState(57);
				match(FILE);
				setState(58);
				match(T__10);
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 4);
				{
				setState(59);
				match(T__12);
				setState(60);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Dapso_argumentContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(ssdlParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(ssdlParser.FLOAT, 0); }
		public Dapso_argumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dapso_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ssdlListener ) ((ssdlListener)listener).enterDapso_argument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ssdlListener ) ((ssdlListener)listener).exitDapso_argument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ssdlVisitor ) return ((ssdlVisitor<? extends T>)visitor).visitDapso_argument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dapso_argumentContext dapso_argument() throws RecognitionException {
		Dapso_argumentContext _localctx = new Dapso_argumentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_dapso_argument);
		try {
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				match(T__13);
				setState(64);
				match(INT);
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				match(T__14);
				setState(66);
				match(INT);
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
				match(T__15);
				setState(68);
				match(INT);
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 4);
				{
				setState(69);
				match(T__16);
				setState(70);
				match(FLOAT);
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 5);
				{
				setState(71);
				match(T__17);
				setState(72);
				match(FLOAT);
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 6);
				{
				setState(73);
				match(T__18);
				setState(74);
				match(FLOAT);
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 7);
				{
				setState(75);
				match(T__19);
				setState(76);
				match(FLOAT);
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 8);
				{
				setState(77);
				match(T__20);
				setState(78);
				match(FLOAT);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		"\u0004\u0001\u001eR\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0001\u0000\u0001\u0000\u0004\u0000\u000f\b\u0000\u000b"+
		"\u0000\f\u0000\u0010\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001\u001d\b\u0001\u0003\u0001\u001f\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0004\u0002%\b\u0002\u000b\u0002\f\u0002&\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0003\u0003,\b\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004>\b\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0003\u0005P\b\u0005\u0001\u0005\u0000"+
		"\u0000\u0006\u0000\u0002\u0004\u0006\b\n\u0000\u0000\\\u0000\u000e\u0001"+
		"\u0000\u0000\u0000\u0002\u001e\u0001\u0000\u0000\u0000\u0004 \u0001\u0000"+
		"\u0000\u0000\u0006(\u0001\u0000\u0000\u0000\b=\u0001\u0000\u0000\u0000"+
		"\nO\u0001\u0000\u0000\u0000\f\u000f\u0003\u0002\u0001\u0000\r\u000f\u0005"+
		"\u0018\u0000\u0000\u000e\f\u0001\u0000\u0000\u0000\u000e\r\u0001\u0000"+
		"\u0000\u0000\u000f\u0010\u0001\u0000\u0000\u0000\u0010\u000e\u0001\u0000"+
		"\u0000\u0000\u0010\u0011\u0001\u0000\u0000\u0000\u0011\u0001\u0001\u0000"+
		"\u0000\u0000\u0012\u0013\u0005\u0001\u0000\u0000\u0013\u0014\u0003\u0004"+
		"\u0002\u0000\u0014\u0015\u0005\u0002\u0000\u0000\u0015\u001f\u0001\u0000"+
		"\u0000\u0000\u0016\u0017\u0005\u0003\u0000\u0000\u0017\u0018\u0005\u001b"+
		"\u0000\u0000\u0018\u001f\u0005\u0019\u0000\u0000\u0019\u001a\u0005\u0004"+
		"\u0000\u0000\u001a\u001c\u0005\u001b\u0000\u0000\u001b\u001d\u0005\u001d"+
		"\u0000\u0000\u001c\u001b\u0001\u0000\u0000\u0000\u001c\u001d\u0001\u0000"+
		"\u0000\u0000\u001d\u001f\u0001\u0000\u0000\u0000\u001e\u0012\u0001\u0000"+
		"\u0000\u0000\u001e\u0016\u0001\u0000\u0000\u0000\u001e\u0019\u0001\u0000"+
		"\u0000\u0000\u001f\u0003\u0001\u0000\u0000\u0000 !\u0005\u0016\u0000\u0000"+
		"!\"\u0005\u0005\u0000\u0000\"$\u0005\u001b\u0000\u0000#%\u0003\u0006\u0003"+
		"\u0000$#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&$\u0001\u0000"+
		"\u0000\u0000&\'\u0001\u0000\u0000\u0000\'\u0005\u0001\u0000\u0000\u0000"+
		"(+\u0005\u0006\u0000\u0000),\u0003\b\u0004\u0000*,\u0003\n\u0005\u0000"+
		"+)\u0001\u0000\u0000\u0000+*\u0001\u0000\u0000\u0000,\u0007\u0001\u0000"+
		"\u0000\u0000-.\u0005\u0007\u0000\u0000.>\u0005\u0017\u0000\u0000/0\u0005"+
		"\b\u0000\u000001\u0005\t\u0000\u000012\u0005\u0019\u0000\u000023\u0005"+
		"\n\u0000\u000034\u0005\u0019\u0000\u00004>\u0005\u000b\u0000\u000056\u0005"+
		"\f\u0000\u000067\u0005\t\u0000\u000078\u0005\u001d\u0000\u000089\u0005"+
		"\n\u0000\u00009:\u0005\u001d\u0000\u0000:>\u0005\u000b\u0000\u0000;<\u0005"+
		"\r\u0000\u0000<>\u0005\u001b\u0000\u0000=-\u0001\u0000\u0000\u0000=/\u0001"+
		"\u0000\u0000\u0000=5\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000"+
		">\t\u0001\u0000\u0000\u0000?@\u0005\u000e\u0000\u0000@P\u0005\u0019\u0000"+
		"\u0000AB\u0005\u000f\u0000\u0000BP\u0005\u0019\u0000\u0000CD\u0005\u0010"+
		"\u0000\u0000DP\u0005\u0019\u0000\u0000EF\u0005\u0011\u0000\u0000FP\u0005"+
		"\u001a\u0000\u0000GH\u0005\u0012\u0000\u0000HP\u0005\u001a\u0000\u0000"+
		"IJ\u0005\u0013\u0000\u0000JP\u0005\u001a\u0000\u0000KL\u0005\u0014\u0000"+
		"\u0000LP\u0005\u001a\u0000\u0000MN\u0005\u0015\u0000\u0000NP\u0005\u001a"+
		"\u0000\u0000O?\u0001\u0000\u0000\u0000OA\u0001\u0000\u0000\u0000OC\u0001"+
		"\u0000\u0000\u0000OE\u0001\u0000\u0000\u0000OG\u0001\u0000\u0000\u0000"+
		"OI\u0001\u0000\u0000\u0000OK\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000"+
		"\u0000P\u000b\u0001\u0000\u0000\u0000\b\u000e\u0010\u001c\u001e&+=O";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}