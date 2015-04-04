// Generated from C:\Dev\Dropbox\Workspace\JavaSQL2\src\main\java\ch\sama\sql\dialect\tsql\grammar\antlr\Sql.g4 by ANTLR 4.3
package ch.sama.sql.dialect.tsql.grammar.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__16=1, T__15=2, T__14=3, T__13=4, T__12=5, T__11=6, T__10=7, T__9=8, 
		T__8=9, T__7=10, T__6=11, T__5=12, T__4=13, T__3=14, T__2=15, T__1=16, 
		T__0=17, Whitespace=18, Newline=19, LineComment=20, Select=21, From=22, 
		Where=23, Join=24, Order=25, As=26, On=27, By=28, Asc=29, Desc=30, And=31, 
		Or=32, Null=33, Left=34, Right=35, Inner=36, Full=37, Cross=38, Not=39, 
		Like=40, Is=41, With=42, Union=43, All=44, Int=45, Float=46, Datetime=47, 
		Varchar=48, Max=49, Identifier=50, IntegerConstant=51, FloatingConstant=52, 
		StringLiteral=53;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'.*'", "'>='", "'['", "'<'", "'='", "']'", "'>'", 
		"'<='", "'<>'", "'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "Whitespace", 
		"Newline", "LineComment", "Select", "From", "Where", "Join", "Order", 
		"As", "On", "By", "Asc", "Desc", "And", "Or", "Null", "Left", "Right", 
		"Inner", "Full", "Cross", "Not", "Like", "Is", "With", "Union", "All", 
		"Int", "Float", "Datetime", "Varchar", "Max", "Identifier", "IntegerConstant", 
		"FloatingConstant", "StringLiteral"
	};
	public static final int
		RULE_varcharType = 0, RULE_varcharTypeIntMax = 1, RULE_varcharTypeMax = 2, 
		RULE_varcharTypeNoMax = 3, RULE_intType = 4, RULE_floatType = 5, RULE_datetimeType = 6, 
		RULE_dataType = 7, RULE_sqlIdentifier = 8, RULE_fullStatement = 9, RULE_dataStatement = 10, 
		RULE_unionStatement = 11, RULE_cteStatementHead = 12, RULE_cteStatementBlock = 13, 
		RULE_cteStatement = 14, RULE_statement = 15, RULE_selectStatement = 16, 
		RULE_fromStatement = 17, RULE_joinType = 18, RULE_joinStatement = 19, 
		RULE_whereStatement = 20, RULE_orderStatement = 21, RULE_primaryValue = 22, 
		RULE_stringValue = 23, RULE_numericValue = 24, RULE_integerValue = 25, 
		RULE_floatingValue = 26, RULE_functionValue = 27, RULE_queryValue = 28, 
		RULE_argument = 29, RULE_argumentList = 30, RULE_aliasedValue = 31, RULE_value = 32, 
		RULE_allFields = 33, RULE_allTableFields = 34, RULE_field = 35, RULE_table = 36, 
		RULE_tableField = 37, RULE_valueList = 38, RULE_source = 39, RULE_tableSource = 40, 
		RULE_aliasedTable = 41, RULE_aliasedStatement = 42, RULE_aliasedSource = 43, 
		RULE_sourceList = 44, RULE_defaultOrderValue = 45, RULE_ascOrderValue = 46, 
		RULE_descOrderValue = 47, RULE_orderValue = 48, RULE_orderList = 49, RULE_condition = 50, 
		RULE_logicalOrCondition = 51, RULE_logicalAndCondition = 52, RULE_notCondition = 53, 
		RULE_comparativeCondition = 54, RULE_equalCondition = 55, RULE_unequalCondition = 56, 
		RULE_lessCondition = 57, RULE_lessThanCondition = 58, RULE_greaterCondition = 59, 
		RULE_greaterThanCondition = 60, RULE_likeCondition = 61, RULE_isNullCondition = 62, 
		RULE_expression = 63, RULE_additiveExpression = 64, RULE_additiveOperator = 65, 
		RULE_multiplicativeExpression = 66, RULE_multiplicativeOperator = 67, 
		RULE_negateExpression = 68, RULE_negateOperator = 69, RULE_primaryExpression = 70, 
		RULE_parExpression = 71;
	public static final String[] ruleNames = {
		"varcharType", "varcharTypeIntMax", "varcharTypeMax", "varcharTypeNoMax", 
		"intType", "floatType", "datetimeType", "dataType", "sqlIdentifier", "fullStatement", 
		"dataStatement", "unionStatement", "cteStatementHead", "cteStatementBlock", 
		"cteStatement", "statement", "selectStatement", "fromStatement", "joinType", 
		"joinStatement", "whereStatement", "orderStatement", "primaryValue", "stringValue", 
		"numericValue", "integerValue", "floatingValue", "functionValue", "queryValue", 
		"argument", "argumentList", "aliasedValue", "value", "allFields", "allTableFields", 
		"field", "table", "tableField", "valueList", "source", "tableSource", 
		"aliasedTable", "aliasedStatement", "aliasedSource", "sourceList", "defaultOrderValue", 
		"ascOrderValue", "descOrderValue", "orderValue", "orderList", "condition", 
		"logicalOrCondition", "logicalAndCondition", "notCondition", "comparativeCondition", 
		"equalCondition", "unequalCondition", "lessCondition", "lessThanCondition", 
		"greaterCondition", "greaterThanCondition", "likeCondition", "isNullCondition", 
		"expression", "additiveExpression", "additiveOperator", "multiplicativeExpression", 
		"multiplicativeOperator", "negateExpression", "negateOperator", "primaryExpression", 
		"parExpression"
	};

	@Override
	public String getGrammarFileName() { return "Sql.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class VarcharTypeContext extends ParserRuleContext {
		public VarcharTypeNoMaxContext varcharTypeNoMax() {
			return getRuleContext(VarcharTypeNoMaxContext.class,0);
		}
		public VarcharTypeMaxContext varcharTypeMax() {
			return getRuleContext(VarcharTypeMaxContext.class,0);
		}
		public VarcharTypeIntMaxContext varcharTypeIntMax() {
			return getRuleContext(VarcharTypeIntMaxContext.class,0);
		}
		public VarcharTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varcharType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterVarcharType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitVarcharType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitVarcharType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarcharTypeContext varcharType() throws RecognitionException {
		VarcharTypeContext _localctx = new VarcharTypeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_varcharType);
		try {
			setState(147);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(144); varcharTypeIntMax();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(145); varcharTypeMax();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(146); varcharTypeNoMax();
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

	public static class VarcharTypeIntMaxContext extends ParserRuleContext {
		public TerminalNode Varchar() { return getToken(SqlParser.Varchar, 0); }
		public TerminalNode IntegerConstant() { return getToken(SqlParser.IntegerConstant, 0); }
		public VarcharTypeIntMaxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varcharTypeIntMax; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterVarcharTypeIntMax(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitVarcharTypeIntMax(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitVarcharTypeIntMax(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarcharTypeIntMaxContext varcharTypeIntMax() throws RecognitionException {
		VarcharTypeIntMaxContext _localctx = new VarcharTypeIntMaxContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_varcharTypeIntMax);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149); match(Varchar);
			setState(150); match(T__6);
			setState(151); match(IntegerConstant);
			setState(152); match(T__5);
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

	public static class VarcharTypeMaxContext extends ParserRuleContext {
		public TerminalNode Max() { return getToken(SqlParser.Max, 0); }
		public TerminalNode Varchar() { return getToken(SqlParser.Varchar, 0); }
		public VarcharTypeMaxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varcharTypeMax; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterVarcharTypeMax(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitVarcharTypeMax(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitVarcharTypeMax(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarcharTypeMaxContext varcharTypeMax() throws RecognitionException {
		VarcharTypeMaxContext _localctx = new VarcharTypeMaxContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varcharTypeMax);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); match(Varchar);
			setState(155); match(T__6);
			setState(156); match(Max);
			setState(157); match(T__5);
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

	public static class VarcharTypeNoMaxContext extends ParserRuleContext {
		public TerminalNode Varchar() { return getToken(SqlParser.Varchar, 0); }
		public VarcharTypeNoMaxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varcharTypeNoMax; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterVarcharTypeNoMax(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitVarcharTypeNoMax(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitVarcharTypeNoMax(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarcharTypeNoMaxContext varcharTypeNoMax() throws RecognitionException {
		VarcharTypeNoMaxContext _localctx = new VarcharTypeNoMaxContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varcharTypeNoMax);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159); match(Varchar);
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

	public static class IntTypeContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(SqlParser.Int, 0); }
		public IntTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterIntType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitIntType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitIntType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntTypeContext intType() throws RecognitionException {
		IntTypeContext _localctx = new IntTypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_intType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161); match(Int);
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

	public static class FloatTypeContext extends ParserRuleContext {
		public TerminalNode Float() { return getToken(SqlParser.Float, 0); }
		public FloatTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterFloatType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitFloatType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitFloatType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatTypeContext floatType() throws RecognitionException {
		FloatTypeContext _localctx = new FloatTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_floatType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163); match(Float);
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

	public static class DatetimeTypeContext extends ParserRuleContext {
		public TerminalNode Datetime() { return getToken(SqlParser.Datetime, 0); }
		public DatetimeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_datetimeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterDatetimeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitDatetimeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitDatetimeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DatetimeTypeContext datetimeType() throws RecognitionException {
		DatetimeTypeContext _localctx = new DatetimeTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_datetimeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165); match(Datetime);
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

	public static class DataTypeContext extends ParserRuleContext {
		public IntTypeContext intType() {
			return getRuleContext(IntTypeContext.class,0);
		}
		public FloatTypeContext floatType() {
			return getRuleContext(FloatTypeContext.class,0);
		}
		public DatetimeTypeContext datetimeType() {
			return getRuleContext(DatetimeTypeContext.class,0);
		}
		public VarcharTypeContext varcharType() {
			return getRuleContext(VarcharTypeContext.class,0);
		}
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitDataType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitDataType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_dataType);
		try {
			setState(171);
			switch (_input.LA(1)) {
			case Varchar:
				enterOuterAlt(_localctx, 1);
				{
				setState(167); varcharType();
				}
				break;
			case Int:
				enterOuterAlt(_localctx, 2);
				{
				setState(168); intType();
				}
				break;
			case Float:
				enterOuterAlt(_localctx, 3);
				{
				setState(169); floatType();
				}
				break;
			case Datetime:
				enterOuterAlt(_localctx, 4);
				{
				setState(170); datetimeType();
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

	public static class SqlIdentifierContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SqlParser.Identifier, 0); }
		public SqlIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterSqlIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitSqlIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitSqlIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlIdentifierContext sqlIdentifier() throws RecognitionException {
		SqlIdentifierContext _localctx = new SqlIdentifierContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_sqlIdentifier);
		try {
			setState(177);
			switch (_input.LA(1)) {
			case T__13:
				enterOuterAlt(_localctx, 1);
				{
				setState(173); match(T__13);
				setState(174); match(Identifier);
				setState(175); match(T__10);
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(176); match(Identifier);
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

	public static class FullStatementContext extends ParserRuleContext {
		public OrderStatementContext orderStatement() {
			return getRuleContext(OrderStatementContext.class,0);
		}
		public DataStatementContext dataStatement() {
			return getRuleContext(DataStatementContext.class,0);
		}
		public FullStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fullStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterFullStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitFullStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitFullStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FullStatementContext fullStatement() throws RecognitionException {
		FullStatementContext _localctx = new FullStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_fullStatement);
		try {
			setState(183);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(179); dataStatement();
				setState(180); orderStatement();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(182); dataStatement();
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

	public static class DataStatementContext extends ParserRuleContext {
		public CteStatementHeadContext cteStatementHead() {
			return getRuleContext(CteStatementHeadContext.class,0);
		}
		public UnionStatementContext unionStatement() {
			return getRuleContext(UnionStatementContext.class,0);
		}
		public DataStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterDataStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitDataStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitDataStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataStatementContext dataStatement() throws RecognitionException {
		DataStatementContext _localctx = new DataStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_dataStatement);
		try {
			setState(189);
			switch (_input.LA(1)) {
			case With:
				enterOuterAlt(_localctx, 1);
				{
				setState(185); cteStatementHead();
				setState(186); unionStatement(0);
				}
				break;
			case Select:
				enterOuterAlt(_localctx, 2);
				{
				setState(188); unionStatement(0);
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

	public static class UnionStatementContext extends ParserRuleContext {
		public TerminalNode Union() { return getToken(SqlParser.Union, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public UnionStatementContext unionStatement() {
			return getRuleContext(UnionStatementContext.class,0);
		}
		public TerminalNode All() { return getToken(SqlParser.All, 0); }
		public UnionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterUnionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitUnionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitUnionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionStatementContext unionStatement() throws RecognitionException {
		return unionStatement(0);
	}

	private UnionStatementContext unionStatement(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		UnionStatementContext _localctx = new UnionStatementContext(_ctx, _parentState);
		UnionStatementContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_unionStatement, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(192); statement();
			}
			_ctx.stop = _input.LT(-1);
			setState(200);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new UnionStatementContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_unionStatement);
					setState(194);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(195); match(Union);
					setState(196); match(All);
					setState(197); statement();
					}
					} 
				}
				setState(202);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CteStatementHeadContext extends ParserRuleContext {
		public CteStatementBlockContext cteStatementBlock() {
			return getRuleContext(CteStatementBlockContext.class,0);
		}
		public TerminalNode With() { return getToken(SqlParser.With, 0); }
		public CteStatementHeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cteStatementHead; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterCteStatementHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitCteStatementHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitCteStatementHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CteStatementHeadContext cteStatementHead() throws RecognitionException {
		CteStatementHeadContext _localctx = new CteStatementHeadContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_cteStatementHead);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203); match(With);
			setState(204); cteStatementBlock(0);
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

	public static class CteStatementBlockContext extends ParserRuleContext {
		public CteStatementContext cteStatement() {
			return getRuleContext(CteStatementContext.class,0);
		}
		public CteStatementBlockContext cteStatementBlock() {
			return getRuleContext(CteStatementBlockContext.class,0);
		}
		public CteStatementBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cteStatementBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterCteStatementBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitCteStatementBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitCteStatementBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CteStatementBlockContext cteStatementBlock() throws RecognitionException {
		return cteStatementBlock(0);
	}

	private CteStatementBlockContext cteStatementBlock(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CteStatementBlockContext _localctx = new CteStatementBlockContext(_ctx, _parentState);
		CteStatementBlockContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_cteStatementBlock, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(207); cteStatement();
			}
			_ctx.stop = _input.LT(-1);
			setState(214);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CteStatementBlockContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_cteStatementBlock);
					setState(209);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(210); match(T__2);
					setState(211); cteStatement();
					}
					} 
				}
				setState(216);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CteStatementContext extends ParserRuleContext {
		public SqlIdentifierContext sqlIdentifier() {
			return getRuleContext(SqlIdentifierContext.class,0);
		}
		public TerminalNode As() { return getToken(SqlParser.As, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public CteStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cteStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterCteStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitCteStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitCteStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CteStatementContext cteStatement() throws RecognitionException {
		CteStatementContext _localctx = new CteStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_cteStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217); sqlIdentifier();
			setState(218); match(As);
			setState(219); match(T__6);
			setState(220); statement();
			setState(221); match(T__5);
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

	public static class StatementContext extends ParserRuleContext {
		public JoinStatementContext joinStatement(int i) {
			return getRuleContext(JoinStatementContext.class,i);
		}
		public WhereStatementContext whereStatement() {
			return getRuleContext(WhereStatementContext.class,0);
		}
		public FromStatementContext fromStatement() {
			return getRuleContext(FromStatementContext.class,0);
		}
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public List<JoinStatementContext> joinStatement() {
			return getRuleContexts(JoinStatementContext.class);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_statement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(223); selectStatement();
			setState(231);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(224); fromStatement();
				setState(228);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(225); joinStatement();
						}
						} 
					}
					setState(230);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				}
				}
				break;
			}
			setState(234);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(233); whereStatement();
				}
				break;
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

	public static class SelectStatementContext extends ParserRuleContext {
		public ValueListContext valueList() {
			return getRuleContext(ValueListContext.class,0);
		}
		public TerminalNode Select() { return getToken(SqlParser.Select, 0); }
		public SelectStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterSelectStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitSelectStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitSelectStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectStatementContext selectStatement() throws RecognitionException {
		SelectStatementContext _localctx = new SelectStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_selectStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236); match(Select);
			setState(237); valueList();
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

	public static class FromStatementContext extends ParserRuleContext {
		public SourceListContext sourceList() {
			return getRuleContext(SourceListContext.class,0);
		}
		public TerminalNode From() { return getToken(SqlParser.From, 0); }
		public FromStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterFromStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitFromStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitFromStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FromStatementContext fromStatement() throws RecognitionException {
		FromStatementContext _localctx = new FromStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_fromStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239); match(From);
			setState(240); sourceList();
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

	public static class JoinTypeContext extends ParserRuleContext {
		public TerminalNode Left() { return getToken(SqlParser.Left, 0); }
		public TerminalNode Inner() { return getToken(SqlParser.Inner, 0); }
		public TerminalNode Full() { return getToken(SqlParser.Full, 0); }
		public TerminalNode Cross() { return getToken(SqlParser.Cross, 0); }
		public TerminalNode Right() { return getToken(SqlParser.Right, 0); }
		public JoinTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterJoinType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitJoinType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitJoinType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinTypeContext joinType() throws RecognitionException {
		JoinTypeContext _localctx = new JoinTypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_joinType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Left) | (1L << Right) | (1L << Inner) | (1L << Full) | (1L << Cross))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class JoinStatementContext extends ParserRuleContext {
		public SourceContext source() {
			return getRuleContext(SourceContext.class,0);
		}
		public TerminalNode Join() { return getToken(SqlParser.Join, 0); }
		public TerminalNode On() { return getToken(SqlParser.On, 0); }
		public JoinTypeContext joinType() {
			return getRuleContext(JoinTypeContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public JoinStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterJoinStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitJoinStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitJoinStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinStatementContext joinStatement() throws RecognitionException {
		JoinStatementContext _localctx = new JoinStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_joinStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Left) | (1L << Right) | (1L << Inner) | (1L << Full) | (1L << Cross))) != 0)) {
				{
				setState(244); joinType();
				}
			}

			setState(247); match(Join);
			setState(248); source();
			setState(249); match(On);
			setState(250); condition();
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

	public static class WhereStatementContext extends ParserRuleContext {
		public TerminalNode Where() { return getToken(SqlParser.Where, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public WhereStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterWhereStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitWhereStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitWhereStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereStatementContext whereStatement() throws RecognitionException {
		WhereStatementContext _localctx = new WhereStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_whereStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252); match(Where);
			setState(253); condition();
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

	public static class OrderStatementContext extends ParserRuleContext {
		public TerminalNode Order() { return getToken(SqlParser.Order, 0); }
		public TerminalNode By() { return getToken(SqlParser.By, 0); }
		public OrderListContext orderList() {
			return getRuleContext(OrderListContext.class,0);
		}
		public OrderStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterOrderStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitOrderStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitOrderStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderStatementContext orderStatement() throws RecognitionException {
		OrderStatementContext _localctx = new OrderStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_orderStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255); match(Order);
			setState(256); match(By);
			setState(257); orderList();
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

	public static class PrimaryValueContext extends ParserRuleContext {
		public FunctionValueContext functionValue() {
			return getRuleContext(FunctionValueContext.class,0);
		}
		public TableFieldContext tableField() {
			return getRuleContext(TableFieldContext.class,0);
		}
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public NumericValueContext numericValue() {
			return getRuleContext(NumericValueContext.class,0);
		}
		public QueryValueContext queryValue() {
			return getRuleContext(QueryValueContext.class,0);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public PrimaryValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterPrimaryValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitPrimaryValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitPrimaryValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryValueContext primaryValue() throws RecognitionException {
		PrimaryValueContext _localctx = new PrimaryValueContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_primaryValue);
		try {
			setState(265);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(259); field();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(260); tableField();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(261); stringValue();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(262); numericValue();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(263); functionValue();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(264); queryValue();
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

	public static class StringValueContext extends ParserRuleContext {
		public TerminalNode StringLiteral() { return getToken(SqlParser.StringLiteral, 0); }
		public StringValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterStringValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitStringValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitStringValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringValueContext stringValue() throws RecognitionException {
		StringValueContext _localctx = new StringValueContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_stringValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267); match(StringLiteral);
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

	public static class NumericValueContext extends ParserRuleContext {
		public FloatingValueContext floatingValue() {
			return getRuleContext(FloatingValueContext.class,0);
		}
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public NumericValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterNumericValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitNumericValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitNumericValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericValueContext numericValue() throws RecognitionException {
		NumericValueContext _localctx = new NumericValueContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_numericValue);
		try {
			setState(271);
			switch (_input.LA(1)) {
			case IntegerConstant:
				enterOuterAlt(_localctx, 1);
				{
				setState(269); integerValue();
				}
				break;
			case FloatingConstant:
				enterOuterAlt(_localctx, 2);
				{
				setState(270); floatingValue();
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

	public static class IntegerValueContext extends ParserRuleContext {
		public TerminalNode IntegerConstant() { return getToken(SqlParser.IntegerConstant, 0); }
		public IntegerValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitIntegerValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitIntegerValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerValueContext integerValue() throws RecognitionException {
		IntegerValueContext _localctx = new IntegerValueContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_integerValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273); match(IntegerConstant);
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

	public static class FloatingValueContext extends ParserRuleContext {
		public TerminalNode FloatingConstant() { return getToken(SqlParser.FloatingConstant, 0); }
		public FloatingValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatingValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterFloatingValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitFloatingValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitFloatingValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatingValueContext floatingValue() throws RecognitionException {
		FloatingValueContext _localctx = new FloatingValueContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_floatingValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275); match(FloatingConstant);
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

	public static class FunctionValueContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(SqlParser.Identifier, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public FunctionValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterFunctionValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitFunctionValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitFunctionValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionValueContext functionValue() throws RecognitionException {
		FunctionValueContext _localctx = new FunctionValueContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_functionValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277); match(Identifier);
			setState(278); match(T__6);
			setState(280);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__6) | (1L << T__1) | (1L << Int) | (1L << Float) | (1L << Datetime) | (1L << Varchar) | (1L << Identifier) | (1L << IntegerConstant) | (1L << FloatingConstant) | (1L << StringLiteral))) != 0)) {
				{
				setState(279); argumentList();
				}
			}

			setState(282); match(T__5);
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

	public static class QueryValueContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public QueryValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queryValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterQueryValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitQueryValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitQueryValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryValueContext queryValue() throws RecognitionException {
		QueryValueContext _localctx = new QueryValueContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_queryValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284); match(T__6);
			setState(285); statement();
			setState(286); match(T__5);
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

	public static class ArgumentContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_argument);
		try {
			setState(290);
			switch (_input.LA(1)) {
			case Int:
			case Float:
			case Datetime:
			case Varchar:
				enterOuterAlt(_localctx, 1);
				{
				setState(288); dataType();
				}
				break;
			case T__13:
			case T__6:
			case T__1:
			case Identifier:
			case IntegerConstant:
			case FloatingConstant:
			case StringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(289); expression();
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

	public static class ArgumentListContext extends ParserRuleContext {
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitArgumentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitArgumentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_argumentList);
		try {
			setState(297);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(292); argument();
				setState(293); match(T__2);
				setState(294); argumentList();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(296); argument();
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

	public static class AliasedValueContext extends ParserRuleContext {
		public SqlIdentifierContext sqlIdentifier() {
			return getRuleContext(SqlIdentifierContext.class,0);
		}
		public TerminalNode As() { return getToken(SqlParser.As, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AliasedValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliasedValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterAliasedValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitAliasedValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAliasedValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AliasedValueContext aliasedValue() throws RecognitionException {
		AliasedValueContext _localctx = new AliasedValueContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_aliasedValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299); expression();
			setState(300); match(As);
			setState(301); sqlIdentifier();
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

	public static class ValueContext extends ParserRuleContext {
		public AllFieldsContext allFields() {
			return getRuleContext(AllFieldsContext.class,0);
		}
		public AliasedValueContext aliasedValue() {
			return getRuleContext(AliasedValueContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AllTableFieldsContext allTableFields() {
			return getRuleContext(AllTableFieldsContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_value);
		try {
			setState(307);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(303); expression();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(304); aliasedValue();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(305); allFields();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(306); allTableFields();
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

	public static class AllFieldsContext extends ParserRuleContext {
		public AllFieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allFields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterAllFields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitAllFields(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAllFields(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllFieldsContext allFields() throws RecognitionException {
		AllFieldsContext _localctx = new AllFieldsContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_allFields);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309); match(T__4);
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

	public static class AllTableFieldsContext extends ParserRuleContext {
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public AllTableFieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allTableFields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterAllTableFields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitAllTableFields(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAllTableFields(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllTableFieldsContext allTableFields() throws RecognitionException {
		AllTableFieldsContext _localctx = new AllTableFieldsContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_allTableFields);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311); table();
			setState(312); match(T__15);
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

	public static class FieldContext extends ParserRuleContext {
		public SqlIdentifierContext sqlIdentifier() {
			return getRuleContext(SqlIdentifierContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314); sqlIdentifier();
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

	public static class TableContext extends ParserRuleContext {
		public List<SqlIdentifierContext> sqlIdentifier() {
			return getRuleContexts(SqlIdentifierContext.class);
		}
		public SqlIdentifierContext sqlIdentifier(int i) {
			return getRuleContext(SqlIdentifierContext.class,i);
		}
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_table);
		try {
			setState(321);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(316); sqlIdentifier();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(317); sqlIdentifier();
				setState(318); match(T__0);
				setState(319); sqlIdentifier();
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

	public static class TableFieldContext extends ParserRuleContext {
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public SqlIdentifierContext sqlIdentifier() {
			return getRuleContext(SqlIdentifierContext.class,0);
		}
		public TableFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterTableField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitTableField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitTableField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableFieldContext tableField() throws RecognitionException {
		TableFieldContext _localctx = new TableFieldContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_tableField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323); table();
			setState(324); match(T__0);
			setState(325); sqlIdentifier();
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

	public static class ValueListContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ValueListContext valueList() {
			return getRuleContext(ValueListContext.class,0);
		}
		public ValueListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterValueList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitValueList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitValueList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueListContext valueList() throws RecognitionException {
		ValueListContext _localctx = new ValueListContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_valueList);
		try {
			setState(332);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(327); value();
				setState(328); match(T__2);
				setState(329); valueList();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(331); value();
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

	public static class SourceContext extends ParserRuleContext {
		public AliasedSourceContext aliasedSource() {
			return getRuleContext(AliasedSourceContext.class,0);
		}
		public TableSourceContext tableSource() {
			return getRuleContext(TableSourceContext.class,0);
		}
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitSource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitSource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_source);
		try {
			setState(336);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(334); tableSource();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(335); aliasedSource();
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

	public static class TableSourceContext extends ParserRuleContext {
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public TableSourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableSource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterTableSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitTableSource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitTableSource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableSourceContext tableSource() throws RecognitionException {
		TableSourceContext _localctx = new TableSourceContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_tableSource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338); table();
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

	public static class AliasedTableContext extends ParserRuleContext {
		public SqlIdentifierContext sqlIdentifier() {
			return getRuleContext(SqlIdentifierContext.class,0);
		}
		public TerminalNode As() { return getToken(SqlParser.As, 0); }
		public TableSourceContext tableSource() {
			return getRuleContext(TableSourceContext.class,0);
		}
		public AliasedTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliasedTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterAliasedTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitAliasedTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAliasedTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AliasedTableContext aliasedTable() throws RecognitionException {
		AliasedTableContext _localctx = new AliasedTableContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_aliasedTable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340); tableSource();
			setState(341); match(As);
			setState(342); sqlIdentifier();
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

	public static class AliasedStatementContext extends ParserRuleContext {
		public SqlIdentifierContext sqlIdentifier() {
			return getRuleContext(SqlIdentifierContext.class,0);
		}
		public TerminalNode As() { return getToken(SqlParser.As, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public AliasedStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliasedStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterAliasedStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitAliasedStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAliasedStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AliasedStatementContext aliasedStatement() throws RecognitionException {
		AliasedStatementContext _localctx = new AliasedStatementContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_aliasedStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344); match(T__6);
			setState(345); statement();
			setState(346); match(T__5);
			setState(347); match(As);
			setState(348); sqlIdentifier();
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

	public static class AliasedSourceContext extends ParserRuleContext {
		public AliasedTableContext aliasedTable() {
			return getRuleContext(AliasedTableContext.class,0);
		}
		public AliasedStatementContext aliasedStatement() {
			return getRuleContext(AliasedStatementContext.class,0);
		}
		public AliasedSourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliasedSource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterAliasedSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitAliasedSource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAliasedSource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AliasedSourceContext aliasedSource() throws RecognitionException {
		AliasedSourceContext _localctx = new AliasedSourceContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_aliasedSource);
		try {
			setState(352);
			switch (_input.LA(1)) {
			case T__13:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(350); aliasedTable();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(351); aliasedStatement();
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

	public static class SourceListContext extends ParserRuleContext {
		public SourceContext source() {
			return getRuleContext(SourceContext.class,0);
		}
		public SourceListContext sourceList() {
			return getRuleContext(SourceListContext.class,0);
		}
		public SourceListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sourceList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterSourceList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitSourceList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitSourceList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourceListContext sourceList() throws RecognitionException {
		SourceListContext _localctx = new SourceListContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_sourceList);
		try {
			setState(359);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(354); source();
				setState(355); match(T__2);
				setState(356); sourceList();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(358); source();
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

	public static class DefaultOrderValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DefaultOrderValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultOrderValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterDefaultOrderValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitDefaultOrderValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitDefaultOrderValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultOrderValueContext defaultOrderValue() throws RecognitionException {
		DefaultOrderValueContext _localctx = new DefaultOrderValueContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_defaultOrderValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361); expression();
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

	public static class AscOrderValueContext extends ParserRuleContext {
		public TerminalNode Asc() { return getToken(SqlParser.Asc, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AscOrderValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ascOrderValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterAscOrderValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitAscOrderValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAscOrderValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AscOrderValueContext ascOrderValue() throws RecognitionException {
		AscOrderValueContext _localctx = new AscOrderValueContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_ascOrderValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363); expression();
			setState(364); match(Asc);
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

	public static class DescOrderValueContext extends ParserRuleContext {
		public TerminalNode Desc() { return getToken(SqlParser.Desc, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DescOrderValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_descOrderValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterDescOrderValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitDescOrderValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitDescOrderValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescOrderValueContext descOrderValue() throws RecognitionException {
		DescOrderValueContext _localctx = new DescOrderValueContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_descOrderValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366); expression();
			setState(367); match(Desc);
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

	public static class OrderValueContext extends ParserRuleContext {
		public AscOrderValueContext ascOrderValue() {
			return getRuleContext(AscOrderValueContext.class,0);
		}
		public DescOrderValueContext descOrderValue() {
			return getRuleContext(DescOrderValueContext.class,0);
		}
		public DefaultOrderValueContext defaultOrderValue() {
			return getRuleContext(DefaultOrderValueContext.class,0);
		}
		public OrderValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterOrderValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitOrderValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitOrderValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderValueContext orderValue() throws RecognitionException {
		OrderValueContext _localctx = new OrderValueContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_orderValue);
		try {
			setState(372);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(369); defaultOrderValue();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(370); ascOrderValue();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(371); descOrderValue();
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

	public static class OrderListContext extends ParserRuleContext {
		public OrderListContext orderList() {
			return getRuleContext(OrderListContext.class,0);
		}
		public OrderValueContext orderValue() {
			return getRuleContext(OrderValueContext.class,0);
		}
		public OrderListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterOrderList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitOrderList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitOrderList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderListContext orderList() throws RecognitionException {
		OrderListContext _localctx = new OrderListContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_orderList);
		try {
			setState(379);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(374); orderValue();
				setState(375); match(T__2);
				setState(376); orderList();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(378); orderValue();
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

	public static class ConditionContext extends ParserRuleContext {
		public LogicalOrConditionContext logicalOrCondition() {
			return getRuleContext(LogicalOrConditionContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381); logicalOrCondition(0);
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

	public static class LogicalOrConditionContext extends ParserRuleContext {
		public LogicalAndConditionContext logicalAndCondition() {
			return getRuleContext(LogicalAndConditionContext.class,0);
		}
		public TerminalNode Or() { return getToken(SqlParser.Or, 0); }
		public LogicalOrConditionContext logicalOrCondition() {
			return getRuleContext(LogicalOrConditionContext.class,0);
		}
		public LogicalOrConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOrCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterLogicalOrCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitLogicalOrCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitLogicalOrCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOrConditionContext logicalOrCondition() throws RecognitionException {
		return logicalOrCondition(0);
	}

	private LogicalOrConditionContext logicalOrCondition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LogicalOrConditionContext _localctx = new LogicalOrConditionContext(_ctx, _parentState);
		LogicalOrConditionContext _prevctx = _localctx;
		int _startState = 102;
		enterRecursionRule(_localctx, 102, RULE_logicalOrCondition, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(384); logicalAndCondition(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(391);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogicalOrConditionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_logicalOrCondition);
					setState(386);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(387); match(Or);
					setState(388); logicalAndCondition(0);
					}
					} 
				}
				setState(393);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LogicalAndConditionContext extends ParserRuleContext {
		public NotConditionContext notCondition() {
			return getRuleContext(NotConditionContext.class,0);
		}
		public LogicalAndConditionContext logicalAndCondition() {
			return getRuleContext(LogicalAndConditionContext.class,0);
		}
		public TerminalNode And() { return getToken(SqlParser.And, 0); }
		public LogicalAndConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAndCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterLogicalAndCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitLogicalAndCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitLogicalAndCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalAndConditionContext logicalAndCondition() throws RecognitionException {
		return logicalAndCondition(0);
	}

	private LogicalAndConditionContext logicalAndCondition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LogicalAndConditionContext _localctx = new LogicalAndConditionContext(_ctx, _parentState);
		LogicalAndConditionContext _prevctx = _localctx;
		int _startState = 104;
		enterRecursionRule(_localctx, 104, RULE_logicalAndCondition, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(395); notCondition();
			}
			_ctx.stop = _input.LT(-1);
			setState(402);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogicalAndConditionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_logicalAndCondition);
					setState(397);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(398); match(And);
					setState(399); notCondition();
					}
					} 
				}
				setState(404);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NotConditionContext extends ParserRuleContext {
		public ComparativeConditionContext comparativeCondition() {
			return getRuleContext(ComparativeConditionContext.class,0);
		}
		public TerminalNode Not() { return getToken(SqlParser.Not, 0); }
		public NotConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterNotCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitNotCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitNotCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotConditionContext notCondition() throws RecognitionException {
		NotConditionContext _localctx = new NotConditionContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_notCondition);
		try {
			setState(408);
			switch (_input.LA(1)) {
			case T__13:
			case T__6:
			case T__1:
			case Identifier:
			case IntegerConstant:
			case FloatingConstant:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(405); comparativeCondition();
				}
				break;
			case Not:
				enterOuterAlt(_localctx, 2);
				{
				setState(406); match(Not);
				setState(407); comparativeCondition();
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

	public static class ComparativeConditionContext extends ParserRuleContext {
		public UnequalConditionContext unequalCondition() {
			return getRuleContext(UnequalConditionContext.class,0);
		}
		public GreaterThanConditionContext greaterThanCondition() {
			return getRuleContext(GreaterThanConditionContext.class,0);
		}
		public LessThanConditionContext lessThanCondition() {
			return getRuleContext(LessThanConditionContext.class,0);
		}
		public GreaterConditionContext greaterCondition() {
			return getRuleContext(GreaterConditionContext.class,0);
		}
		public LessConditionContext lessCondition() {
			return getRuleContext(LessConditionContext.class,0);
		}
		public IsNullConditionContext isNullCondition() {
			return getRuleContext(IsNullConditionContext.class,0);
		}
		public LikeConditionContext likeCondition() {
			return getRuleContext(LikeConditionContext.class,0);
		}
		public EqualConditionContext equalCondition() {
			return getRuleContext(EqualConditionContext.class,0);
		}
		public ComparativeConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparativeCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterComparativeCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitComparativeCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitComparativeCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparativeConditionContext comparativeCondition() throws RecognitionException {
		ComparativeConditionContext _localctx = new ComparativeConditionContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_comparativeCondition);
		try {
			setState(418);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(410); equalCondition();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(411); unequalCondition();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(412); lessCondition();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(413); lessThanCondition();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(414); greaterCondition();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(415); greaterThanCondition();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(416); likeCondition();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(417); isNullCondition();
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

	public static class EqualConditionContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public EqualConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterEqualCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitEqualCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitEqualCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualConditionContext equalCondition() throws RecognitionException {
		EqualConditionContext _localctx = new EqualConditionContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_equalCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(420); expression();
			setState(421); match(T__11);
			setState(422); expression();
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

	public static class UnequalConditionContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public UnequalConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unequalCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterUnequalCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitUnequalCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitUnequalCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnequalConditionContext unequalCondition() throws RecognitionException {
		UnequalConditionContext _localctx = new UnequalConditionContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_unequalCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(424); expression();
			setState(425); match(T__7);
			setState(426); expression();
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

	public static class LessConditionContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public LessConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lessCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterLessCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitLessCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitLessCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LessConditionContext lessCondition() throws RecognitionException {
		LessConditionContext _localctx = new LessConditionContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_lessCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(428); expression();
			setState(429); match(T__12);
			setState(430); expression();
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

	public static class LessThanConditionContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public LessThanConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lessThanCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterLessThanCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitLessThanCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitLessThanCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LessThanConditionContext lessThanCondition() throws RecognitionException {
		LessThanConditionContext _localctx = new LessThanConditionContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_lessThanCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(432); expression();
			setState(433); match(T__8);
			setState(434); expression();
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

	public static class GreaterConditionContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public GreaterConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_greaterCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterGreaterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitGreaterCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitGreaterCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GreaterConditionContext greaterCondition() throws RecognitionException {
		GreaterConditionContext _localctx = new GreaterConditionContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_greaterCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436); expression();
			setState(437); match(T__9);
			setState(438); expression();
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

	public static class GreaterThanConditionContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public GreaterThanConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_greaterThanCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterGreaterThanCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitGreaterThanCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitGreaterThanCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GreaterThanConditionContext greaterThanCondition() throws RecognitionException {
		GreaterThanConditionContext _localctx = new GreaterThanConditionContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_greaterThanCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440); expression();
			setState(441); match(T__14);
			setState(442); expression();
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

	public static class LikeConditionContext extends ParserRuleContext {
		public TerminalNode Like() { return getToken(SqlParser.Like, 0); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public LikeConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_likeCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterLikeCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitLikeCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitLikeCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LikeConditionContext likeCondition() throws RecognitionException {
		LikeConditionContext _localctx = new LikeConditionContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_likeCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444); expression();
			setState(445); match(Like);
			setState(446); expression();
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

	public static class IsNullConditionContext extends ParserRuleContext {
		public TerminalNode Is() { return getToken(SqlParser.Is, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Null() { return getToken(SqlParser.Null, 0); }
		public IsNullConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isNullCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterIsNullCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitIsNullCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitIsNullCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IsNullConditionContext isNullCondition() throws RecognitionException {
		IsNullConditionContext _localctx = new IsNullConditionContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_isNullCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(448); expression();
			setState(449); match(Is);
			setState(450); match(Null);
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

	public static class ExpressionContext extends ParserRuleContext {
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(452); additiveExpression(0);
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

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public AdditiveOperatorContext additiveOperator() {
			return getRuleContext(AdditiveOperatorContext.class,0);
		}
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitAdditiveExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		return additiveExpression(0);
	}

	private AdditiveExpressionContext additiveExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, _parentState);
		AdditiveExpressionContext _prevctx = _localctx;
		int _startState = 128;
		enterRecursionRule(_localctx, 128, RULE_additiveExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(455); multiplicativeExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(463);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AdditiveExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
					setState(457);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(458); additiveOperator();
					setState(459); multiplicativeExpression(0);
					}
					} 
				}
				setState(465);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AdditiveOperatorContext extends ParserRuleContext {
		public AdditiveOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterAdditiveOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitAdditiveOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAdditiveOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveOperatorContext additiveOperator() throws RecognitionException {
		AdditiveOperatorContext _localctx = new AdditiveOperatorContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_additiveOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__1) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public NegateExpressionContext negateExpression() {
			return getRuleContext(NegateExpressionContext.class,0);
		}
		public MultiplicativeOperatorContext multiplicativeOperator() {
			return getRuleContext(MultiplicativeOperatorContext.class,0);
		}
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitMultiplicativeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		return multiplicativeExpression(0);
	}

	private MultiplicativeExpressionContext multiplicativeExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, _parentState);
		MultiplicativeExpressionContext _prevctx = _localctx;
		int _startState = 132;
		enterRecursionRule(_localctx, 132, RULE_multiplicativeExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(469); negateExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(477);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MultiplicativeExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
					setState(471);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(472); multiplicativeOperator();
					setState(473); negateExpression();
					}
					} 
				}
				setState(479);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MultiplicativeOperatorContext extends ParserRuleContext {
		public MultiplicativeOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterMultiplicativeOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitMultiplicativeOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitMultiplicativeOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeOperatorContext multiplicativeOperator() throws RecognitionException {
		MultiplicativeOperatorContext _localctx = new MultiplicativeOperatorContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_multiplicativeOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
			_la = _input.LA(1);
			if ( !(_la==T__16 || _la==T__4) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class NegateExpressionContext extends ParserRuleContext {
		public NegateOperatorContext negateOperator() {
			return getRuleContext(NegateOperatorContext.class,0);
		}
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public NegateExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negateExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterNegateExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitNegateExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitNegateExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NegateExpressionContext negateExpression() throws RecognitionException {
		NegateExpressionContext _localctx = new NegateExpressionContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_negateExpression);
		try {
			setState(486);
			switch (_input.LA(1)) {
			case T__13:
			case T__6:
			case Identifier:
			case IntegerConstant:
			case FloatingConstant:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(482); primaryExpression();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(483); negateOperator();
				setState(484); primaryExpression();
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

	public static class NegateOperatorContext extends ParserRuleContext {
		public NegateOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negateOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterNegateOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitNegateOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitNegateOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NegateOperatorContext negateOperator() throws RecognitionException {
		NegateOperatorContext _localctx = new NegateOperatorContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_negateOperator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(488); match(T__1);
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

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public PrimaryValueContext primaryValue() {
			return getRuleContext(PrimaryValueContext.class,0);
		}
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitPrimaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitPrimaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_primaryExpression);
		try {
			setState(492);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(490); primaryValue();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(491); parExpression();
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

	public static class ParExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).enterParExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlListener ) ((SqlListener)listener).exitParExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitParExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParExpressionContext parExpression() throws RecognitionException {
		ParExpressionContext _localctx = new ParExpressionContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_parExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494); match(T__6);
			setState(495); expression();
			setState(496); match(T__5);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11: return unionStatement_sempred((UnionStatementContext)_localctx, predIndex);

		case 13: return cteStatementBlock_sempred((CteStatementBlockContext)_localctx, predIndex);

		case 51: return logicalOrCondition_sempred((LogicalOrConditionContext)_localctx, predIndex);

		case 52: return logicalAndCondition_sempred((LogicalAndConditionContext)_localctx, predIndex);

		case 64: return additiveExpression_sempred((AdditiveExpressionContext)_localctx, predIndex);

		case 66: return multiplicativeExpression_sempred((MultiplicativeExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean additiveExpression_sempred(AdditiveExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean unionStatement_sempred(UnionStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean cteStatementBlock_sempred(CteStatementBlockContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean logicalAndCondition_sempred(LogicalAndConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean multiplicativeExpression_sempred(MultiplicativeExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean logicalOrCondition_sempred(LogicalOrConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\67\u01f5\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\3\2\3\2\3\2\5\2\u0096\n\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\5\t\u00ae\n\t\3\n\3\n"+
		"\3\n\3\n\5\n\u00b4\n\n\3\13\3\13\3\13\3\13\5\13\u00ba\n\13\3\f\3\f\3\f"+
		"\3\f\5\f\u00c0\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00c9\n\r\f\r\16\r"+
		"\u00cc\13\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u00d7\n"+
		"\17\f\17\16\17\u00da\13\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\7\21\u00e5\n\21\f\21\16\21\u00e8\13\21\5\21\u00ea\n\21\3\21\5\21\u00ed"+
		"\n\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\25\5\25\u00f8\n\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\5\30\u010c\n\30\3\31\3\31\3\32\3\32\5\32\u0112\n\32\3"+
		"\33\3\33\3\34\3\34\3\35\3\35\3\35\5\35\u011b\n\35\3\35\3\35\3\36\3\36"+
		"\3\36\3\36\3\37\3\37\5\37\u0125\n\37\3 \3 \3 \3 \3 \5 \u012c\n \3!\3!"+
		"\3!\3!\3\"\3\"\3\"\3\"\5\"\u0136\n\"\3#\3#\3$\3$\3$\3%\3%\3&\3&\3&\3&"+
		"\3&\5&\u0144\n&\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\5(\u014f\n(\3)\3)\5)\u0153"+
		"\n)\3*\3*\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3-\3-\5-\u0163\n-\3.\3.\3.\3."+
		"\3.\5.\u016a\n.\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3\62\3\62\3\62\5\62"+
		"\u0177\n\62\3\63\3\63\3\63\3\63\3\63\5\63\u017e\n\63\3\64\3\64\3\65\3"+
		"\65\3\65\3\65\3\65\3\65\7\65\u0188\n\65\f\65\16\65\u018b\13\65\3\66\3"+
		"\66\3\66\3\66\3\66\3\66\7\66\u0193\n\66\f\66\16\66\u0196\13\66\3\67\3"+
		"\67\3\67\5\67\u019b\n\67\38\38\38\38\38\38\38\38\58\u01a5\n8\39\39\39"+
		"\39\3:\3:\3:\3:\3;\3;\3;\3;\3<\3<\3<\3<\3=\3=\3=\3=\3>\3>\3>\3>\3?\3?"+
		"\3?\3?\3@\3@\3@\3@\3A\3A\3B\3B\3B\3B\3B\3B\3B\7B\u01d0\nB\fB\16B\u01d3"+
		"\13B\3C\3C\3D\3D\3D\3D\3D\3D\3D\7D\u01de\nD\fD\16D\u01e1\13D\3E\3E\3F"+
		"\3F\3F\3F\5F\u01e9\nF\3G\3G\3H\3H\5H\u01ef\nH\3I\3I\3I\3I\3I\2\b\30\34"+
		"hj\u0082\u0086J\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088"+
		"\u008a\u008c\u008e\u0090\2\5\3\2$(\4\2\20\20\22\22\4\2\3\3\17\17\u01dc"+
		"\2\u0095\3\2\2\2\4\u0097\3\2\2\2\6\u009c\3\2\2\2\b\u00a1\3\2\2\2\n\u00a3"+
		"\3\2\2\2\f\u00a5\3\2\2\2\16\u00a7\3\2\2\2\20\u00ad\3\2\2\2\22\u00b3\3"+
		"\2\2\2\24\u00b9\3\2\2\2\26\u00bf\3\2\2\2\30\u00c1\3\2\2\2\32\u00cd\3\2"+
		"\2\2\34\u00d0\3\2\2\2\36\u00db\3\2\2\2 \u00e1\3\2\2\2\"\u00ee\3\2\2\2"+
		"$\u00f1\3\2\2\2&\u00f4\3\2\2\2(\u00f7\3\2\2\2*\u00fe\3\2\2\2,\u0101\3"+
		"\2\2\2.\u010b\3\2\2\2\60\u010d\3\2\2\2\62\u0111\3\2\2\2\64\u0113\3\2\2"+
		"\2\66\u0115\3\2\2\28\u0117\3\2\2\2:\u011e\3\2\2\2<\u0124\3\2\2\2>\u012b"+
		"\3\2\2\2@\u012d\3\2\2\2B\u0135\3\2\2\2D\u0137\3\2\2\2F\u0139\3\2\2\2H"+
		"\u013c\3\2\2\2J\u0143\3\2\2\2L\u0145\3\2\2\2N\u014e\3\2\2\2P\u0152\3\2"+
		"\2\2R\u0154\3\2\2\2T\u0156\3\2\2\2V\u015a\3\2\2\2X\u0162\3\2\2\2Z\u0169"+
		"\3\2\2\2\\\u016b\3\2\2\2^\u016d\3\2\2\2`\u0170\3\2\2\2b\u0176\3\2\2\2"+
		"d\u017d\3\2\2\2f\u017f\3\2\2\2h\u0181\3\2\2\2j\u018c\3\2\2\2l\u019a\3"+
		"\2\2\2n\u01a4\3\2\2\2p\u01a6\3\2\2\2r\u01aa\3\2\2\2t\u01ae\3\2\2\2v\u01b2"+
		"\3\2\2\2x\u01b6\3\2\2\2z\u01ba\3\2\2\2|\u01be\3\2\2\2~\u01c2\3\2\2\2\u0080"+
		"\u01c6\3\2\2\2\u0082\u01c8\3\2\2\2\u0084\u01d4\3\2\2\2\u0086\u01d6\3\2"+
		"\2\2\u0088\u01e2\3\2\2\2\u008a\u01e8\3\2\2\2\u008c\u01ea\3\2\2\2\u008e"+
		"\u01ee\3\2\2\2\u0090\u01f0\3\2\2\2\u0092\u0096\5\4\3\2\u0093\u0096\5\6"+
		"\4\2\u0094\u0096\5\b\5\2\u0095\u0092\3\2\2\2\u0095\u0093\3\2\2\2\u0095"+
		"\u0094\3\2\2\2\u0096\3\3\2\2\2\u0097\u0098\7\62\2\2\u0098\u0099\7\r\2"+
		"\2\u0099\u009a\7\65\2\2\u009a\u009b\7\16\2\2\u009b\5\3\2\2\2\u009c\u009d"+
		"\7\62\2\2\u009d\u009e\7\r\2\2\u009e\u009f\7\63\2\2\u009f\u00a0\7\16\2"+
		"\2\u00a0\7\3\2\2\2\u00a1\u00a2\7\62\2\2\u00a2\t\3\2\2\2\u00a3\u00a4\7"+
		"/\2\2\u00a4\13\3\2\2\2\u00a5\u00a6\7\60\2\2\u00a6\r\3\2\2\2\u00a7\u00a8"+
		"\7\61\2\2\u00a8\17\3\2\2\2\u00a9\u00ae\5\2\2\2\u00aa\u00ae\5\n\6\2\u00ab"+
		"\u00ae\5\f\7\2\u00ac\u00ae\5\16\b\2\u00ad\u00a9\3\2\2\2\u00ad\u00aa\3"+
		"\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae\21\3\2\2\2\u00af"+
		"\u00b0\7\6\2\2\u00b0\u00b1\7\64\2\2\u00b1\u00b4\7\t\2\2\u00b2\u00b4\7"+
		"\64\2\2\u00b3\u00af\3\2\2\2\u00b3\u00b2\3\2\2\2\u00b4\23\3\2\2\2\u00b5"+
		"\u00b6\5\26\f\2\u00b6\u00b7\5,\27\2\u00b7\u00ba\3\2\2\2\u00b8\u00ba\5"+
		"\26\f\2\u00b9\u00b5\3\2\2\2\u00b9\u00b8\3\2\2\2\u00ba\25\3\2\2\2\u00bb"+
		"\u00bc\5\32\16\2\u00bc\u00bd\5\30\r\2\u00bd\u00c0\3\2\2\2\u00be\u00c0"+
		"\5\30\r\2\u00bf\u00bb\3\2\2\2\u00bf\u00be\3\2\2\2\u00c0\27\3\2\2\2\u00c1"+
		"\u00c2\b\r\1\2\u00c2\u00c3\5 \21\2\u00c3\u00ca\3\2\2\2\u00c4\u00c5\f\4"+
		"\2\2\u00c5\u00c6\7-\2\2\u00c6\u00c7\7.\2\2\u00c7\u00c9\5 \21\2\u00c8\u00c4"+
		"\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb"+
		"\31\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd\u00ce\7,\2\2\u00ce\u00cf\5\34\17"+
		"\2\u00cf\33\3\2\2\2\u00d0\u00d1\b\17\1\2\u00d1\u00d2\5\36\20\2\u00d2\u00d8"+
		"\3\2\2\2\u00d3\u00d4\f\4\2\2\u00d4\u00d5\7\21\2\2\u00d5\u00d7\5\36\20"+
		"\2\u00d6\u00d3\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9"+
		"\3\2\2\2\u00d9\35\3\2\2\2\u00da\u00d8\3\2\2\2\u00db\u00dc\5\22\n\2\u00dc"+
		"\u00dd\7\34\2\2\u00dd\u00de\7\r\2\2\u00de\u00df\5 \21\2\u00df\u00e0\7"+
		"\16\2\2\u00e0\37\3\2\2\2\u00e1\u00e9\5\"\22\2\u00e2\u00e6\5$\23\2\u00e3"+
		"\u00e5\5(\25\2\u00e4\u00e3\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2"+
		"\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9"+
		"\u00e2\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ec\3\2\2\2\u00eb\u00ed\5*"+
		"\26\2\u00ec\u00eb\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed!\3\2\2\2\u00ee\u00ef"+
		"\7\27\2\2\u00ef\u00f0\5N(\2\u00f0#\3\2\2\2\u00f1\u00f2\7\30\2\2\u00f2"+
		"\u00f3\5Z.\2\u00f3%\3\2\2\2\u00f4\u00f5\t\2\2\2\u00f5\'\3\2\2\2\u00f6"+
		"\u00f8\5&\24\2\u00f7\u00f6\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\3\2"+
		"\2\2\u00f9\u00fa\7\32\2\2\u00fa\u00fb\5P)\2\u00fb\u00fc\7\35\2\2\u00fc"+
		"\u00fd\5f\64\2\u00fd)\3\2\2\2\u00fe\u00ff\7\31\2\2\u00ff\u0100\5f\64\2"+
		"\u0100+\3\2\2\2\u0101\u0102\7\33\2\2\u0102\u0103\7\36\2\2\u0103\u0104"+
		"\5d\63\2\u0104-\3\2\2\2\u0105\u010c\5H%\2\u0106\u010c\5L\'\2\u0107\u010c"+
		"\5\60\31\2\u0108\u010c\5\62\32\2\u0109\u010c\58\35\2\u010a\u010c\5:\36"+
		"\2\u010b\u0105\3\2\2\2\u010b\u0106\3\2\2\2\u010b\u0107\3\2\2\2\u010b\u0108"+
		"\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010a\3\2\2\2\u010c/\3\2\2\2\u010d"+
		"\u010e\7\67\2\2\u010e\61\3\2\2\2\u010f\u0112\5\64\33\2\u0110\u0112\5\66"+
		"\34\2\u0111\u010f\3\2\2\2\u0111\u0110\3\2\2\2\u0112\63\3\2\2\2\u0113\u0114"+
		"\7\65\2\2\u0114\65\3\2\2\2\u0115\u0116\7\66\2\2\u0116\67\3\2\2\2\u0117"+
		"\u0118\7\64\2\2\u0118\u011a\7\r\2\2\u0119\u011b\5> \2\u011a\u0119\3\2"+
		"\2\2\u011a\u011b\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\7\16\2\2\u011d"+
		"9\3\2\2\2\u011e\u011f\7\r\2\2\u011f\u0120\5 \21\2\u0120\u0121\7\16\2\2"+
		"\u0121;\3\2\2\2\u0122\u0125\5\20\t\2\u0123\u0125\5\u0080A\2\u0124\u0122"+
		"\3\2\2\2\u0124\u0123\3\2\2\2\u0125=\3\2\2\2\u0126\u0127\5<\37\2\u0127"+
		"\u0128\7\21\2\2\u0128\u0129\5> \2\u0129\u012c\3\2\2\2\u012a\u012c\5<\37"+
		"\2\u012b\u0126\3\2\2\2\u012b\u012a\3\2\2\2\u012c?\3\2\2\2\u012d\u012e"+
		"\5\u0080A\2\u012e\u012f\7\34\2\2\u012f\u0130\5\22\n\2\u0130A\3\2\2\2\u0131"+
		"\u0136\5\u0080A\2\u0132\u0136\5@!\2\u0133\u0136\5D#\2\u0134\u0136\5F$"+
		"\2\u0135\u0131\3\2\2\2\u0135\u0132\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0134"+
		"\3\2\2\2\u0136C\3\2\2\2\u0137\u0138\7\17\2\2\u0138E\3\2\2\2\u0139\u013a"+
		"\5J&\2\u013a\u013b\7\4\2\2\u013bG\3\2\2\2\u013c\u013d\5\22\n\2\u013dI"+
		"\3\2\2\2\u013e\u0144\5\22\n\2\u013f\u0140\5\22\n\2\u0140\u0141\7\23\2"+
		"\2\u0141\u0142\5\22\n\2\u0142\u0144\3\2\2\2\u0143\u013e\3\2\2\2\u0143"+
		"\u013f\3\2\2\2\u0144K\3\2\2\2\u0145\u0146\5J&\2\u0146\u0147\7\23\2\2\u0147"+
		"\u0148\5\22\n\2\u0148M\3\2\2\2\u0149\u014a\5B\"\2\u014a\u014b\7\21\2\2"+
		"\u014b\u014c\5N(\2\u014c\u014f\3\2\2\2\u014d\u014f\5B\"\2\u014e\u0149"+
		"\3\2\2\2\u014e\u014d\3\2\2\2\u014fO\3\2\2\2\u0150\u0153\5R*\2\u0151\u0153"+
		"\5X-\2\u0152\u0150\3\2\2\2\u0152\u0151\3\2\2\2\u0153Q\3\2\2\2\u0154\u0155"+
		"\5J&\2\u0155S\3\2\2\2\u0156\u0157\5R*\2\u0157\u0158\7\34\2\2\u0158\u0159"+
		"\5\22\n\2\u0159U\3\2\2\2\u015a\u015b\7\r\2\2\u015b\u015c\5 \21\2\u015c"+
		"\u015d\7\16\2\2\u015d\u015e\7\34\2\2\u015e\u015f\5\22\n\2\u015fW\3\2\2"+
		"\2\u0160\u0163\5T+\2\u0161\u0163\5V,\2\u0162\u0160\3\2\2\2\u0162\u0161"+
		"\3\2\2\2\u0163Y\3\2\2\2\u0164\u0165\5P)\2\u0165\u0166\7\21\2\2\u0166\u0167"+
		"\5Z.\2\u0167\u016a\3\2\2\2\u0168\u016a\5P)\2\u0169\u0164\3\2\2\2\u0169"+
		"\u0168\3\2\2\2\u016a[\3\2\2\2\u016b\u016c\5\u0080A\2\u016c]\3\2\2\2\u016d"+
		"\u016e\5\u0080A\2\u016e\u016f\7\37\2\2\u016f_\3\2\2\2\u0170\u0171\5\u0080"+
		"A\2\u0171\u0172\7 \2\2\u0172a\3\2\2\2\u0173\u0177\5\\/\2\u0174\u0177\5"+
		"^\60\2\u0175\u0177\5`\61\2\u0176\u0173\3\2\2\2\u0176\u0174\3\2\2\2\u0176"+
		"\u0175\3\2\2\2\u0177c\3\2\2\2\u0178\u0179\5b\62\2\u0179\u017a\7\21\2\2"+
		"\u017a\u017b\5d\63\2\u017b\u017e\3\2\2\2\u017c\u017e\5b\62\2\u017d\u0178"+
		"\3\2\2\2\u017d\u017c\3\2\2\2\u017ee\3\2\2\2\u017f\u0180\5h\65\2\u0180"+
		"g\3\2\2\2\u0181\u0182\b\65\1\2\u0182\u0183\5j\66\2\u0183\u0189\3\2\2\2"+
		"\u0184\u0185\f\3\2\2\u0185\u0186\7\"\2\2\u0186\u0188\5j\66\2\u0187\u0184"+
		"\3\2\2\2\u0188\u018b\3\2\2\2\u0189\u0187\3\2\2\2\u0189\u018a\3\2\2\2\u018a"+
		"i\3\2\2\2\u018b\u0189\3\2\2\2\u018c\u018d\b\66\1\2\u018d\u018e\5l\67\2"+
		"\u018e\u0194\3\2\2\2\u018f\u0190\f\3\2\2\u0190\u0191\7!\2\2\u0191\u0193"+
		"\5l\67\2\u0192\u018f\3\2\2\2\u0193\u0196\3\2\2\2\u0194\u0192\3\2\2\2\u0194"+
		"\u0195\3\2\2\2\u0195k\3\2\2\2\u0196\u0194\3\2\2\2\u0197\u019b\5n8\2\u0198"+
		"\u0199\7)\2\2\u0199\u019b\5n8\2\u019a\u0197\3\2\2\2\u019a\u0198\3\2\2"+
		"\2\u019bm\3\2\2\2\u019c\u01a5\5p9\2\u019d\u01a5\5r:\2\u019e\u01a5\5t;"+
		"\2\u019f\u01a5\5v<\2\u01a0\u01a5\5x=\2\u01a1\u01a5\5z>\2\u01a2\u01a5\5"+
		"|?\2\u01a3\u01a5\5~@\2\u01a4\u019c\3\2\2\2\u01a4\u019d\3\2\2\2\u01a4\u019e"+
		"\3\2\2\2\u01a4\u019f\3\2\2\2\u01a4\u01a0\3\2\2\2\u01a4\u01a1\3\2\2\2\u01a4"+
		"\u01a2\3\2\2\2\u01a4\u01a3\3\2\2\2\u01a5o\3\2\2\2\u01a6\u01a7\5\u0080"+
		"A\2\u01a7\u01a8\7\b\2\2\u01a8\u01a9\5\u0080A\2\u01a9q\3\2\2\2\u01aa\u01ab"+
		"\5\u0080A\2\u01ab\u01ac\7\f\2\2\u01ac\u01ad\5\u0080A\2\u01ads\3\2\2\2"+
		"\u01ae\u01af\5\u0080A\2\u01af\u01b0\7\7\2\2\u01b0\u01b1\5\u0080A\2\u01b1"+
		"u\3\2\2\2\u01b2\u01b3\5\u0080A\2\u01b3\u01b4\7\13\2\2\u01b4\u01b5\5\u0080"+
		"A\2\u01b5w\3\2\2\2\u01b6\u01b7\5\u0080A\2\u01b7\u01b8\7\n\2\2\u01b8\u01b9"+
		"\5\u0080A\2\u01b9y\3\2\2\2\u01ba\u01bb\5\u0080A\2\u01bb\u01bc\7\5\2\2"+
		"\u01bc\u01bd\5\u0080A\2\u01bd{\3\2\2\2\u01be\u01bf\5\u0080A\2\u01bf\u01c0"+
		"\7*\2\2\u01c0\u01c1\5\u0080A\2\u01c1}\3\2\2\2\u01c2\u01c3\5\u0080A\2\u01c3"+
		"\u01c4\7+\2\2\u01c4\u01c5\7#\2\2\u01c5\177\3\2\2\2\u01c6\u01c7\5\u0082"+
		"B\2\u01c7\u0081\3\2\2\2\u01c8\u01c9\bB\1\2\u01c9\u01ca\5\u0086D\2\u01ca"+
		"\u01d1\3\2\2\2\u01cb\u01cc\f\3\2\2\u01cc\u01cd\5\u0084C\2\u01cd\u01ce"+
		"\5\u0086D\2\u01ce\u01d0\3\2\2\2\u01cf\u01cb\3\2\2\2\u01d0\u01d3\3\2\2"+
		"\2\u01d1\u01cf\3\2\2\2\u01d1\u01d2\3\2\2\2\u01d2\u0083\3\2\2\2\u01d3\u01d1"+
		"\3\2\2\2\u01d4\u01d5\t\3\2\2\u01d5\u0085\3\2\2\2\u01d6\u01d7\bD\1\2\u01d7"+
		"\u01d8\5\u008aF\2\u01d8\u01df\3\2\2\2\u01d9\u01da\f\3\2\2\u01da\u01db"+
		"\5\u0088E\2\u01db\u01dc\5\u008aF\2\u01dc\u01de\3\2\2\2\u01dd\u01d9\3\2"+
		"\2\2\u01de\u01e1\3\2\2\2\u01df\u01dd\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0"+
		"\u0087\3\2\2\2\u01e1\u01df\3\2\2\2\u01e2\u01e3\t\4\2\2\u01e3\u0089\3\2"+
		"\2\2\u01e4\u01e9\5\u008eH\2\u01e5\u01e6\5\u008cG\2\u01e6\u01e7\5\u008e"+
		"H\2\u01e7\u01e9\3\2\2\2\u01e8\u01e4\3\2\2\2\u01e8\u01e5\3\2\2\2\u01e9"+
		"\u008b\3\2\2\2\u01ea\u01eb\7\22\2\2\u01eb\u008d\3\2\2\2\u01ec\u01ef\5"+
		".\30\2\u01ed\u01ef\5\u0090I\2\u01ee\u01ec\3\2\2\2\u01ee\u01ed\3\2\2\2"+
		"\u01ef\u008f\3\2\2\2\u01f0\u01f1\7\r\2\2\u01f1\u01f2\5\u0080A\2\u01f2"+
		"\u01f3\7\16\2\2\u01f3\u0091\3\2\2\2\"\u0095\u00ad\u00b3\u00b9\u00bf\u00ca"+
		"\u00d8\u00e6\u00e9\u00ec\u00f7\u010b\u0111\u011a\u0124\u012b\u0135\u0143"+
		"\u014e\u0152\u0162\u0169\u0176\u017d\u0189\u0194\u019a\u01a4\u01d1\u01df"+
		"\u01e8\u01ee";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}