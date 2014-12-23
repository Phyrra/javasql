grammar Sql;

Whitespace
    : [ \t]+
    -> skip
    ;

Newline
    : ( '\r' '\n'? | '\n' )
    -> skip
    ;

LineComment
    : '--' ~[\r\n]*
    -> skip
    ;

Select : [sS][eE][lL][eE][cC][tT] ;
From : [fF][rR][oO][mM] ;
Where : [wW][hH][eE][rR][eE] ;
Join : [jJ][oO][iI][nN] ;
Order : [oO][rR][dD][eE][rR] ;
As : [aA][sS] ;
On : [oO][nN] ;
By : [bB][yY] ;
Asc : [aA][sS][cC] ;
Desc : [dD][eE][sS][cC] ;
And : [aA][nN][dD] ;
Or : [oO][rR] ;
Null : [nN][uU][lL][lL] ;
Left : [lL][eE][fF][tT] ;
Right : [rR][iI][gG][hH][tT] ;
Not : [nN][oO][tT] ;
Like : [lL][iI][kK][eE] ;
Is : [iI][sS] ;
With : [wW][iI][tT][hH] ;
Union : [uU][nN][iI][oO][nN] ;
All : [aA][lL][lL] ;

Identifier
    : IdentifierNondigit ( IdentifierNondigit | Digit )*
    ;

fragment
IdentifierNondigit
    : Nondigit
    ;

fragment
Nondigit
    : [a-zA-Z_]
    ;

fragment
Digit
    : [0-9]
    ;

IntegerConstant
    : DecimalConstant
    ;

fragment
DecimalConstant
    : NonzeroDigit Digit*
    | '0'
    ;

fragment
NonzeroDigit
    : [1-9]
    ;

FloatingConstant
    : FractionalConstant
    ;

fragment
FractionalConstant
    : DigitSequence? '.' DigitSequence
    | DigitSequence '.'
    ;

fragment
DigitSequence
    : Digit+
    ;

fragment
EscapeSequence
    : '\'\''
    ;

StringLiteral
    : '\'' SCharSequence? '\''
    ;

fragment
SCharSequence
    : SChar+
    ;

fragment
SChar
    : ~['\r\n]
    | EscapeSequence
    ;

sqlIdentifier
    : '[' Identifier ']'
    | Identifier
    ;

fullStatement
    : cteStatementHead unionStatement
    | unionStatement
    ;

// TODO: Cannot do "select order union select"
unionStatement
    : unionStatement Union All statement
    | statement
    ;

cteStatementHead
    : With cteStatementBlock
    ;

cteStatementBlock
    : cteStatementBlock ',' cteStatement
    | cteStatement
    ;

cteStatement
    : sqlIdentifier As '(' statement ')'
    ;

// TODO: This should be split up to prevent "select order"
// this would -however- result in about 50 lines -.- (which, conicidentally is, why I'm not doing it)
statement
    : selectStatement (fromStatement joinStatement*)? whereStatement? orderStatement?
    ;

selectStatement
    : Select valueList
    ;

fromStatement
    : From sourceList
    ;

joinType
    : Left
    | Right
    ;

joinStatement
    : joinType? Join source (As sqlIdentifier)? On condition
    ;

whereStatement
    : Where condition
    ;

orderStatement
    : Order By orderList
    ;

primaryValue
    : field
    | tableField
    | stringValue
    | numericValue
    | functionValue
    | queryValue
    ;

stringValue
    : StringLiteral
    ;

numericValue
    : IntegerConstant
    | FloatingConstant
    ;

functionValue
    : Identifier '(' argumentList? ')'
    ;

queryValue
    : '(' statement ')'
    ;

argumentList
    : expression ',' argumentList
    | expression
    ;

aliasedValue
    : expression As sqlIdentifier
    ;

value
    : expression
    | aliasedValue
    | allFields
    | allTableFields
    ;

allFields
    : '*'
    ;

allTableFields
    : sqlIdentifier '.*'
    ;

field
    : sqlIdentifier
    ;

tableField
    : sqlIdentifier '.' sqlIdentifier
    ;

valueList
    : value ',' valueList
    | value
    ;

source
    : primarySource
    | aliasedSource
    ;

primarySource
    : sqlIdentifier
    ;

aliasedSource
    : primarySource As sqlIdentifier
    | '(' statement ')' As sqlIdentifier
    ;

sourceList
    : source ',' sourceList
    | source
    ;

defaultOrderValue
    : expression
    ;

ascOrderValue
    : expression Asc
    ;    

descOrderValue
    : expression Desc
    ;

orderValue
    : defaultOrderValue
    | ascOrderValue
    | descOrderValue
    ;

orderList
    : orderValue ',' orderList
    | orderValue
    ;

condition
    : logicalOrCondition
    ;

logicalOrCondition
    : logicalAndCondition
    | logicalOrCondition Or logicalAndCondition
    ;

logicalAndCondition
    : notCondition
    | logicalAndCondition And notCondition
    ;

notCondition
    : comparativeCondition
    | Not comparativeCondition
    ;

comparativeCondition
    : equalCondition
    | unequalCondition
    | lessCondition
    | lessThanCondition
    | greaterCondition
    | greaterThanCondition
    | likeCondition
    | isNullCondition
    ;

equalCondition
    : expression '=' expression
    ;

unequalCondition
    : expression '<>' expression
    ;

lessCondition
    : expression '<' expression
    ;

lessThanCondition
    : expression '<=' expression
    ;

greaterCondition
    : expression '>' expression
    ;

greaterThanCondition
    : expression '>=' expression
    ;

likeCondition
    : expression Like expression
    ;

isNullCondition
    : expression Is Null
    ;

expression
    : additiveExpression
    ;

additiveExpression
    : multiplicativeExpression
    | additiveExpression additiveOperator multiplicativeExpression
    ;

additiveOperator
    : '+' | '-'
    ;

multiplicativeExpression
    : negateExpression
    | multiplicativeExpression multiplicativeOperator negateExpression
    ;

multiplicativeOperator
    : '*' | '/'
    ;

negateExpression
    : primaryExpression
    | negateOperator primaryExpression
    ;

negateOperator
    : '-'
    ;

primaryExpression
    : primaryValue
    | parExpression
    ;

parExpression
    : '(' expression ')'
    ;