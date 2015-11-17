grammar Jql;

Whitespace
    : [ \t]+
    -> skip
    ;

Newline
    : ( '\r' '\n'? | '\n' )
    -> skip
    ;

And : [aA][nN][dD] ;
Or : [oO][rR] ;
Null : [nN][uU][lL][lL] ;
In : [iI][nN] ;
Not : [nN][oO][tT] ;

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
    : '\\\''
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

valueList
    : value ',' valueList
    | value
    ;

constantValue
    : stringValue
    | numericValue
    | functionValue
    ;

interpolatedValue
    : field
    ;

value
    : interpolatedValue
    | constantValue
    ;

stringValue
    : StringLiteral
    ;

numericValue
    : integerValue
    | floatingValue
    ;
    
integerValue
    : IntegerConstant
    ;
    
floatingValue
    : FloatingConstant
    ;

functionValue
    : Identifier '(' valueList? ')'
    ;

field
    : sqlIdentifier
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

notOperator
    : Not
    ;

notCondition
    : primaryCondition
    | notOperator primaryCondition
    ;

primaryCondition
    : equalCondition
    | unequalCondition
    | lessCondition
    | lessEqualCondition
    | greaterCondition
    | greaterEqualCondition
    | likeCondition
    | isNullCondition
    | isNotNullCondition
    | inCondition
    | notInCondition
    | parCondition
    ;

equalCondition
    : expression '=' expression
    ;

unequalCondition
    : expression '!=' expression
    ;

lessCondition
    : expression '<' expression
    ;

lessEqualCondition
    : expression '<=' expression
    ;

greaterCondition
    : expression '>' expression
    ;

greaterEqualCondition
    : expression '>=' expression
    ;

likeCondition
    : expression '~' expression
    ;

isNullCondition
    : expression '=' Null
    | Null '=' expression
    ;

isNotNullCondition
    : expression '!=' Null
    | Null '!=' expression
    ;

constantValueList
    : constantValue ',' constantValueList
    | constantValue
    ;

inCondition
    : expression In '(' constantValueList ')'
    ;

notInCondition
    : expression Not In '(' constantValueList ')'
    ;

parCondition
    : '(' condition ')'
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
    : value
    | parExpression
    ;

parExpression
    : '(' expression ')'
    ;