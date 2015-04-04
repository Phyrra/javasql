// Generated from C:\Dev\Dropbox\Workspace\JavaSQL2\src\main\java\ch\sama\sql\dialect\tsql\grammar\antlr\Sql.g4 by ANTLR 4.3
package ch.sama.sql.dialect.tsql.grammar.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SqlParser}.
 */
public interface SqlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SqlParser#primaryValue}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryValue(@NotNull SqlParser.PrimaryValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#primaryValue}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryValue(@NotNull SqlParser.PrimaryValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#equalCondition}.
	 * @param ctx the parse tree
	 */
	void enterEqualCondition(@NotNull SqlParser.EqualConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#equalCondition}.
	 * @param ctx the parse tree
	 */
	void exitEqualCondition(@NotNull SqlParser.EqualConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(@NotNull SqlParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(@NotNull SqlParser.ArgumentContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#lessThanCondition}.
	 * @param ctx the parse tree
	 */
	void enterLessThanCondition(@NotNull SqlParser.LessThanConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#lessThanCondition}.
	 * @param ctx the parse tree
	 */
	void exitLessThanCondition(@NotNull SqlParser.LessThanConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#isNullCondition}.
	 * @param ctx the parse tree
	 */
	void enterIsNullCondition(@NotNull SqlParser.IsNullConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#isNullCondition}.
	 * @param ctx the parse tree
	 */
	void exitIsNullCondition(@NotNull SqlParser.IsNullConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#varcharTypeNoMax}.
	 * @param ctx the parse tree
	 */
	void enterVarcharTypeNoMax(@NotNull SqlParser.VarcharTypeNoMaxContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#varcharTypeNoMax}.
	 * @param ctx the parse tree
	 */
	void exitVarcharTypeNoMax(@NotNull SqlParser.VarcharTypeNoMaxContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#unionStatement}.
	 * @param ctx the parse tree
	 */
	void enterUnionStatement(@NotNull SqlParser.UnionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#unionStatement}.
	 * @param ctx the parse tree
	 */
	void exitUnionStatement(@NotNull SqlParser.UnionStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#logicalAndCondition}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndCondition(@NotNull SqlParser.LogicalAndConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#logicalAndCondition}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndCondition(@NotNull SqlParser.LogicalAndConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#source}.
	 * @param ctx the parse tree
	 */
	void enterSource(@NotNull SqlParser.SourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#source}.
	 * @param ctx the parse tree
	 */
	void exitSource(@NotNull SqlParser.SourceContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#likeCondition}.
	 * @param ctx the parse tree
	 */
	void enterLikeCondition(@NotNull SqlParser.LikeConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#likeCondition}.
	 * @param ctx the parse tree
	 */
	void exitLikeCondition(@NotNull SqlParser.LikeConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#sqlIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterSqlIdentifier(@NotNull SqlParser.SqlIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#sqlIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitSqlIdentifier(@NotNull SqlParser.SqlIdentifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#fromStatement}.
	 * @param ctx the parse tree
	 */
	void enterFromStatement(@NotNull SqlParser.FromStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#fromStatement}.
	 * @param ctx the parse tree
	 */
	void exitFromStatement(@NotNull SqlParser.FromStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#multiplicativeOperator}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeOperator(@NotNull SqlParser.MultiplicativeOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#multiplicativeOperator}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeOperator(@NotNull SqlParser.MultiplicativeOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#logicalOrCondition}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrCondition(@NotNull SqlParser.LogicalOrConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#logicalOrCondition}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrCondition(@NotNull SqlParser.LogicalOrConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#floatingValue}.
	 * @param ctx the parse tree
	 */
	void enterFloatingValue(@NotNull SqlParser.FloatingValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#floatingValue}.
	 * @param ctx the parse tree
	 */
	void exitFloatingValue(@NotNull SqlParser.FloatingValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(@NotNull SqlParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(@NotNull SqlParser.ArgumentListContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#floatType}.
	 * @param ctx the parse tree
	 */
	void enterFloatType(@NotNull SqlParser.FloatTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#floatType}.
	 * @param ctx the parse tree
	 */
	void exitFloatType(@NotNull SqlParser.FloatTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#stringValue}.
	 * @param ctx the parse tree
	 */
	void enterStringValue(@NotNull SqlParser.StringValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#stringValue}.
	 * @param ctx the parse tree
	 */
	void exitStringValue(@NotNull SqlParser.StringValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#aliasedStatement}.
	 * @param ctx the parse tree
	 */
	void enterAliasedStatement(@NotNull SqlParser.AliasedStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#aliasedStatement}.
	 * @param ctx the parse tree
	 */
	void exitAliasedStatement(@NotNull SqlParser.AliasedStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#additiveOperator}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveOperator(@NotNull SqlParser.AdditiveOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#additiveOperator}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveOperator(@NotNull SqlParser.AdditiveOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#cteStatementHead}.
	 * @param ctx the parse tree
	 */
	void enterCteStatementHead(@NotNull SqlParser.CteStatementHeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#cteStatementHead}.
	 * @param ctx the parse tree
	 */
	void exitCteStatementHead(@NotNull SqlParser.CteStatementHeadContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#unequalCondition}.
	 * @param ctx the parse tree
	 */
	void enterUnequalCondition(@NotNull SqlParser.UnequalConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#unequalCondition}.
	 * @param ctx the parse tree
	 */
	void exitUnequalCondition(@NotNull SqlParser.UnequalConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#functionValue}.
	 * @param ctx the parse tree
	 */
	void enterFunctionValue(@NotNull SqlParser.FunctionValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#functionValue}.
	 * @param ctx the parse tree
	 */
	void exitFunctionValue(@NotNull SqlParser.FunctionValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(@NotNull SqlParser.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#integerValue}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(@NotNull SqlParser.IntegerValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStatement(@NotNull SqlParser.SelectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStatement(@NotNull SqlParser.SelectStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#negateExpression}.
	 * @param ctx the parse tree
	 */
	void enterNegateExpression(@NotNull SqlParser.NegateExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#negateExpression}.
	 * @param ctx the parse tree
	 */
	void exitNegateExpression(@NotNull SqlParser.NegateExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#varcharType}.
	 * @param ctx the parse tree
	 */
	void enterVarcharType(@NotNull SqlParser.VarcharTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#varcharType}.
	 * @param ctx the parse tree
	 */
	void exitVarcharType(@NotNull SqlParser.VarcharTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#varcharTypeIntMax}.
	 * @param ctx the parse tree
	 */
	void enterVarcharTypeIntMax(@NotNull SqlParser.VarcharTypeIntMaxContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#varcharTypeIntMax}.
	 * @param ctx the parse tree
	 */
	void exitVarcharTypeIntMax(@NotNull SqlParser.VarcharTypeIntMaxContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#aliasedValue}.
	 * @param ctx the parse tree
	 */
	void enterAliasedValue(@NotNull SqlParser.AliasedValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#aliasedValue}.
	 * @param ctx the parse tree
	 */
	void exitAliasedValue(@NotNull SqlParser.AliasedValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#tableField}.
	 * @param ctx the parse tree
	 */
	void enterTableField(@NotNull SqlParser.TableFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#tableField}.
	 * @param ctx the parse tree
	 */
	void exitTableField(@NotNull SqlParser.TableFieldContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#fullStatement}.
	 * @param ctx the parse tree
	 */
	void enterFullStatement(@NotNull SqlParser.FullStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#fullStatement}.
	 * @param ctx the parse tree
	 */
	void exitFullStatement(@NotNull SqlParser.FullStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDataType(@NotNull SqlParser.DataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDataType(@NotNull SqlParser.DataTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#cteStatementBlock}.
	 * @param ctx the parse tree
	 */
	void enterCteStatementBlock(@NotNull SqlParser.CteStatementBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#cteStatementBlock}.
	 * @param ctx the parse tree
	 */
	void exitCteStatementBlock(@NotNull SqlParser.CteStatementBlockContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#joinStatement}.
	 * @param ctx the parse tree
	 */
	void enterJoinStatement(@NotNull SqlParser.JoinStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#joinStatement}.
	 * @param ctx the parse tree
	 */
	void exitJoinStatement(@NotNull SqlParser.JoinStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#orderList}.
	 * @param ctx the parse tree
	 */
	void enterOrderList(@NotNull SqlParser.OrderListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#orderList}.
	 * @param ctx the parse tree
	 */
	void exitOrderList(@NotNull SqlParser.OrderListContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#whereStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhereStatement(@NotNull SqlParser.WhereStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#whereStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhereStatement(@NotNull SqlParser.WhereStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#negateOperator}.
	 * @param ctx the parse tree
	 */
	void enterNegateOperator(@NotNull SqlParser.NegateOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#negateOperator}.
	 * @param ctx the parse tree
	 */
	void exitNegateOperator(@NotNull SqlParser.NegateOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#lessCondition}.
	 * @param ctx the parse tree
	 */
	void enterLessCondition(@NotNull SqlParser.LessConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#lessCondition}.
	 * @param ctx the parse tree
	 */
	void exitLessCondition(@NotNull SqlParser.LessConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(@NotNull SqlParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(@NotNull SqlParser.ConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(@NotNull SqlParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(@NotNull SqlParser.FieldContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#orderStatement}.
	 * @param ctx the parse tree
	 */
	void enterOrderStatement(@NotNull SqlParser.OrderStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#orderStatement}.
	 * @param ctx the parse tree
	 */
	void exitOrderStatement(@NotNull SqlParser.OrderStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#allFields}.
	 * @param ctx the parse tree
	 */
	void enterAllFields(@NotNull SqlParser.AllFieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#allFields}.
	 * @param ctx the parse tree
	 */
	void exitAllFields(@NotNull SqlParser.AllFieldsContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(@NotNull SqlParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(@NotNull SqlParser.AdditiveExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#descOrderValue}.
	 * @param ctx the parse tree
	 */
	void enterDescOrderValue(@NotNull SqlParser.DescOrderValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#descOrderValue}.
	 * @param ctx the parse tree
	 */
	void exitDescOrderValue(@NotNull SqlParser.DescOrderValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#numericValue}.
	 * @param ctx the parse tree
	 */
	void enterNumericValue(@NotNull SqlParser.NumericValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#numericValue}.
	 * @param ctx the parse tree
	 */
	void exitNumericValue(@NotNull SqlParser.NumericValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#cteStatement}.
	 * @param ctx the parse tree
	 */
	void enterCteStatement(@NotNull SqlParser.CteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#cteStatement}.
	 * @param ctx the parse tree
	 */
	void exitCteStatement(@NotNull SqlParser.CteStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#orderValue}.
	 * @param ctx the parse tree
	 */
	void enterOrderValue(@NotNull SqlParser.OrderValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#orderValue}.
	 * @param ctx the parse tree
	 */
	void exitOrderValue(@NotNull SqlParser.OrderValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#ascOrderValue}.
	 * @param ctx the parse tree
	 */
	void enterAscOrderValue(@NotNull SqlParser.AscOrderValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#ascOrderValue}.
	 * @param ctx the parse tree
	 */
	void exitAscOrderValue(@NotNull SqlParser.AscOrderValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#datetimeType}.
	 * @param ctx the parse tree
	 */
	void enterDatetimeType(@NotNull SqlParser.DatetimeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#datetimeType}.
	 * @param ctx the parse tree
	 */
	void exitDatetimeType(@NotNull SqlParser.DatetimeTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#greaterCondition}.
	 * @param ctx the parse tree
	 */
	void enterGreaterCondition(@NotNull SqlParser.GreaterConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#greaterCondition}.
	 * @param ctx the parse tree
	 */
	void exitGreaterCondition(@NotNull SqlParser.GreaterConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#defaultOrderValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultOrderValue(@NotNull SqlParser.DefaultOrderValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#defaultOrderValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultOrderValue(@NotNull SqlParser.DefaultOrderValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#allTableFields}.
	 * @param ctx the parse tree
	 */
	void enterAllTableFields(@NotNull SqlParser.AllTableFieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#allTableFields}.
	 * @param ctx the parse tree
	 */
	void exitAllTableFields(@NotNull SqlParser.AllTableFieldsContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull SqlParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull SqlParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#aliasedTable}.
	 * @param ctx the parse tree
	 */
	void enterAliasedTable(@NotNull SqlParser.AliasedTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#aliasedTable}.
	 * @param ctx the parse tree
	 */
	void exitAliasedTable(@NotNull SqlParser.AliasedTableContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#varcharTypeMax}.
	 * @param ctx the parse tree
	 */
	void enterVarcharTypeMax(@NotNull SqlParser.VarcharTypeMaxContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#varcharTypeMax}.
	 * @param ctx the parse tree
	 */
	void exitVarcharTypeMax(@NotNull SqlParser.VarcharTypeMaxContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#aliasedSource}.
	 * @param ctx the parse tree
	 */
	void enterAliasedSource(@NotNull SqlParser.AliasedSourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#aliasedSource}.
	 * @param ctx the parse tree
	 */
	void exitAliasedSource(@NotNull SqlParser.AliasedSourceContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull SqlParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull SqlParser.ValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(@NotNull SqlParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(@NotNull SqlParser.TableContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void enterParExpression(@NotNull SqlParser.ParExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void exitParExpression(@NotNull SqlParser.ParExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(@NotNull SqlParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(@NotNull SqlParser.PrimaryExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull SqlParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull SqlParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#sourceList}.
	 * @param ctx the parse tree
	 */
	void enterSourceList(@NotNull SqlParser.SourceListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#sourceList}.
	 * @param ctx the parse tree
	 */
	void exitSourceList(@NotNull SqlParser.SourceListContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#intType}.
	 * @param ctx the parse tree
	 */
	void enterIntType(@NotNull SqlParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#intType}.
	 * @param ctx the parse tree
	 */
	void exitIntType(@NotNull SqlParser.IntTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#notCondition}.
	 * @param ctx the parse tree
	 */
	void enterNotCondition(@NotNull SqlParser.NotConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#notCondition}.
	 * @param ctx the parse tree
	 */
	void exitNotCondition(@NotNull SqlParser.NotConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(@NotNull SqlParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(@NotNull SqlParser.MultiplicativeExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#greaterThanCondition}.
	 * @param ctx the parse tree
	 */
	void enterGreaterThanCondition(@NotNull SqlParser.GreaterThanConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#greaterThanCondition}.
	 * @param ctx the parse tree
	 */
	void exitGreaterThanCondition(@NotNull SqlParser.GreaterThanConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#comparativeCondition}.
	 * @param ctx the parse tree
	 */
	void enterComparativeCondition(@NotNull SqlParser.ComparativeConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#comparativeCondition}.
	 * @param ctx the parse tree
	 */
	void exitComparativeCondition(@NotNull SqlParser.ComparativeConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#joinType}.
	 * @param ctx the parse tree
	 */
	void enterJoinType(@NotNull SqlParser.JoinTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#joinType}.
	 * @param ctx the parse tree
	 */
	void exitJoinType(@NotNull SqlParser.JoinTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#valueList}.
	 * @param ctx the parse tree
	 */
	void enterValueList(@NotNull SqlParser.ValueListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#valueList}.
	 * @param ctx the parse tree
	 */
	void exitValueList(@NotNull SqlParser.ValueListContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#dataStatement}.
	 * @param ctx the parse tree
	 */
	void enterDataStatement(@NotNull SqlParser.DataStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#dataStatement}.
	 * @param ctx the parse tree
	 */
	void exitDataStatement(@NotNull SqlParser.DataStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableSource(@NotNull SqlParser.TableSourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableSource(@NotNull SqlParser.TableSourceContext ctx);

	/**
	 * Enter a parse tree produced by {@link SqlParser#queryValue}.
	 * @param ctx the parse tree
	 */
	void enterQueryValue(@NotNull SqlParser.QueryValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#queryValue}.
	 * @param ctx the parse tree
	 */
	void exitQueryValue(@NotNull SqlParser.QueryValueContext ctx);
}