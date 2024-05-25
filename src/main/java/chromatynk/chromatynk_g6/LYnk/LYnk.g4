grammar LYnk;


program : (statement)* EOF;
//Rules

statement : ifStatement
            | whileStatement
            | forStatement
            | mimicStatement
            | mirrorStatement
            | cursorStatement
            | selectStatement
            | removeStatement
            | hideStatement
            | showStatement
            | pressStatement
            | thickStatement
            | colorStatement
            | lookAtStatement
            | forwardStatement
            | backwardStatement
            | rotationStatement
            | moveStatement
            | positionStatement
            | boolDeclaration
            | numberDeclaration
            | stringDeclaration
            | deleteDeclaration
            ;

booleanExpression: '(' booleanExpression ')'                                                       #parenthesisVar
                    | left=booleanExpression op=('AND' | 'OR') right=booleanExpression             #andOrExpression
                    | 'NOT' booleanExpression                                                      #notExpression
                    | left=IDENTIFICATION op=arithmeticOperator right=IDENTIFICATION               #varComparison
                    | (left=arithmeticExpression | leftLiteral=LITERAL) op=arithmeticOperator (right=arithmeticExpression | rightLiteral=LITERAL)  #arithmeticLiteralComparison
                    | left=booleanExpression op=boolOperator right=booleanExpression               #booleanComparison
                    | IDENTIFICATION                                                               #identificationVar
                    | 'TRUE'                                                                       #trueVar
                    | 'FALSE'                                                                      #falseVar
                    ;

arithmeticExpression : '(' arithmeticExpression ')'                                                         #parenthesisExpression
                        | left=arithmeticExpression op=(MULTIPLICATION|DIVISION) right=arithmeticExpression #mulDivExpression
                        | left=arithmeticExpression op=(PLUS|MINUS) right=arithmeticExpression              #plusMinusExpression
                        | left=arithmeticExpression numOperator right=arithmeticExpression                  #compExpression
                        | NUMBER                                                                            #numberExpression
                        | LONG                                                                              #longExpression
                        | DOUBLE                                                                            #doubleExpression
                        | IDENTIFICATION                                                                    #identificationExpression
                        ;

// Operators and parameters
arithmeticOperator : op=(EQUAL | GREATER | LESS | GREATER_OR_EQUAL | LESS_OR_EQUAL | NOT_EQUAL);
boolOperator : op=( EQUAL | NOT_EQUAL );
numOperator : op=(PLUS | MINUS | MULTIPLICATION | DIVISION);
numStatementParameterX : (arithmeticExpression | PERCENTAGE);
numStatementParameterY : (arithmeticExpression | PERCENTAGE);

// Statements with block
ifStatement : 'IF'  booleanExpression blockStatement;
forStatement : 'FOR' IDENTIFICATION ('FROM' from=(NUMBER|LONG))? 'TO' to=(NUMBER|LONG) ('STEP' step=(NUMBER|LONG))? blockStatement;
whileStatement : 'WHILE' booleanExpression blockStatement;
mimicStatement : 'MIMIC' LONG blockStatement;
mirrorStatement : 'MIRROR' (x1=numStatementParameterX y1=numStatementParameterY
                           | x1=numStatementParameterX y1=numStatementParameterY x2=numStatementParameterX y2=numStatementParameterY)
                           blockStatement; //MIRROR takes 2 or 4 values or %

forwardStatement : 'FWD' numStatementParameterX; // FWD need an expression with a value or %
backwardStatement : 'BWD' numStatementParameterX ; // BWD need an expression with a value or %

moveStatement : 'MOV' x=numStatementParameterX y=numStatementParameterY; // MOV need an expression with 2 values or %
positionStatement : 'POS' x=numStatementParameterX y=numStatementParameterY; // POS need an expression with 2 values or %


blockStatement : '{' (statement)* '}' ;
colorStatement : 'COLOR' (HEXCODE | arithmeticExpression arithmeticExpression arithmeticExpression ); // COLOR need a value in hexa or 3 expression
cursorStatement : 'CURSOR' LONG;
selectStatement : 'SELECT' LONG;
removeStatement : 'REMOVE' LONG;
pressStatement : 'PRESS' (arithmeticExpression | PERCENTAGE); // PRESS need an expression with a value or %
thickStatement : 'THICK' arithmeticExpression; // THICK need an expression with a value but not %
lookAtStatement : 'LOOKAT' (LONG | x=numStatementParameterX y=numStatementParameterY); // LOOKAT needs a cursor id or 2 values or %
hideStatement : 'HIDE';
showStatement : 'SHOW';
rotationStatement : 'TURN' arithmeticExpression; // TURN need an expression with a value but not %
stringDeclaration : 'STR' IDENTIFICATION ('=' LITERAL)?;
boolDeclaration : 'BOOL' IDENTIFICATION ('=' booleanExpression)?;
numberDeclaration : 'NUM' IDENTIFICATION ('=' arithmeticExpression)?;
deleteDeclaration : 'DEL' IDENTIFICATION;


//Tokens

IDENTIFICATION : [a-zA-Z] [a-zA-Z_0-9]*;
LONG : [0-9]+;
NUMBER : '-'? [0-9]+;
DOUBLE : NUMBER '.' LONG;
LITERAL : '"' (~["])* '"' ;
PERCENTAGE : '-'? (NUMBER | DOUBLE) '%';
HEXCODE : '#' [0-9A-F] [0-9A-F] [0-9A-F] [0-9A-F] [0-9A-F] [0-9A-F] ;

MULTIPLICATION : '*';
DIVISION : '/';
PLUS : '+';
MINUS : '-';

ASSIGN              : '=' ;
EQUAL               : '==' ;
NOT_EQUAL           : '!=' ;
LESS                : '<' ;
LESS_OR_EQUAL       : '<=' ;
GREATER             : '>' ;
GREATER_OR_EQUAL    : '>=' ;


SPACE : [ \r\n\t]+ -> skip;