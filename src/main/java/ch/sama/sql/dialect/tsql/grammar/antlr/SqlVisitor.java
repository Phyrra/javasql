// Generated from C:\Dev\Dropbox\Workspace\JavaSQL2\src\main\java\ch\sama\sql\dialect\tsql\grammar\antlr\Sql.g4 by ANTLR 4.3
package ch.sama.sql.dialect.tsql.grammar.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SqlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SqlParser#primaryValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryValue(@NotNull SqlParser.PrimaryValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#equalCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualCondition(@NotNull SqlParser.EqualConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(@NotNull SqlParser.ArgumentContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#lessThanCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessThanCondition(@NotNull SqlParser.LessThanConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#isNullCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNullCondition(@NotNull SqlParser.IsNullConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#varcharTypeNoMax}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarcharTypeNoMax(@NotNull SqlParser.VarcharTypeNoMaxContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#unionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionStatement(@NotNull SqlParser.UnionStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#logicalAndCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndCondition(@NotNull SqlParser.LogicalAndConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#source}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSource(@NotNull SqlParser.SourceContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#likeCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLikeCondition(@NotNull SqlParser.LikeConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#sqlIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlIdentifier(@NotNull SqlParser.SqlIdentifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#fromStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromStatement(@NotNull SqlParser.FromStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#multiplicativeOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeOperator(@NotNull SqlParser.MultiplicativeOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#logicalOrCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrCondition(@NotNull SqlParser.LogicalOrConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#floatingValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatingValue(@NotNull SqlParser.FloatingValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(@NotNull SqlParser.ArgumentListContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#floatType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatType(@NotNull SqlParser.FloatTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#stringValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringValue(@NotNull SqlParser.StringValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#aliasedStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAliasedStatement(@NotNull SqlParser.AliasedStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#additiveOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveOperator(@NotNull SqlParser.AdditiveOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#cteStatementHead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCteStatementHead(@NotNull SqlParser.CteStatementHeadContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#unequalCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnequalCondition(@NotNull SqlParser.UnequalConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#functionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionValue(@NotNull SqlParser.FunctionValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#integerValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerValue(@NotNull SqlParser.IntegerValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStatement(@NotNull SqlParser.SelectStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#negateExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegateExpression(@NotNull SqlParser.NegateExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#varcharType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarcharType(@NotNull SqlParser.VarcharTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#varcharTypeIntMax}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarcharTypeIntMax(@NotNull SqlParser.VarcharTypeIntMaxContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#aliasedValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAliasedValue(@NotNull SqlParser.AliasedValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#tableField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableField(@NotNull SqlParser.TableFieldContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#fullStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullStatement(@NotNull SqlParser.FullStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataType(@NotNull SqlParser.DataTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#cteStatementBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCteStatementBlock(@NotNull SqlParser.CteStatementBlockContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#joinStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinStatement(@NotNull SqlParser.JoinStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#orderList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderList(@NotNull SqlParser.OrderListContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#whereStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereStatement(@NotNull SqlParser.WhereStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#negateOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegateOperator(@NotNull SqlParser.NegateOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#lessCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessCondition(@NotNull SqlParser.LessConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(@NotNull SqlParser.ConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(@NotNull SqlParser.FieldContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#orderStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderStatement(@NotNull SqlParser.OrderStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#allFields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllFields(@NotNull SqlParser.AllFieldsContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(@NotNull SqlParser.AdditiveExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#descOrderValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescOrderValue(@NotNull SqlParser.DescOrderValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#numericValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericValue(@NotNull SqlParser.NumericValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#cteStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCteStatement(@NotNull SqlParser.CteStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#orderValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderValue(@NotNull SqlParser.OrderValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#ascOrderValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAscOrderValue(@NotNull SqlParser.AscOrderValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#datetimeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatetimeType(@NotNull SqlParser.DatetimeTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#greaterCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterCondition(@NotNull SqlParser.GreaterConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#defaultOrderValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultOrderValue(@NotNull SqlParser.DefaultOrderValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#allTableFields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllTableFields(@NotNull SqlParser.AllTableFieldsContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull SqlParser.StatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#aliasedTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAliasedTable(@NotNull SqlParser.AliasedTableContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#varcharTypeMax}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarcharTypeMax(@NotNull SqlParser.VarcharTypeMaxContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#aliasedSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAliasedSource(@NotNull SqlParser.AliasedSourceContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull SqlParser.ValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable(@NotNull SqlParser.TableContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#parExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpression(@NotNull SqlParser.ParExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(@NotNull SqlParser.PrimaryExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull SqlParser.ExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#sourceList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSourceList(@NotNull SqlParser.SourceListContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#intType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(@NotNull SqlParser.IntTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#notCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotCondition(@NotNull SqlParser.NotConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(@NotNull SqlParser.MultiplicativeExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#greaterThanCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterThanCondition(@NotNull SqlParser.GreaterThanConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#comparativeCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparativeCondition(@NotNull SqlParser.ComparativeConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#joinType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinType(@NotNull SqlParser.JoinTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#valueList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueList(@NotNull SqlParser.ValueListContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#dataStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataStatement(@NotNull SqlParser.DataStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#tableSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSource(@NotNull SqlParser.TableSourceContext ctx);

	/**
	 * Visit a parse tree produced by {@link SqlParser#queryValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryValue(@NotNull SqlParser.QueryValueContext ctx);
}