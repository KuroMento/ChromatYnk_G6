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
            | blockStatement
            ;

booleanExpression: '(' booleanExpression ')'                                                       #parenthesisVar
                    | left=booleanExpression op=('AND' | 'OR') right=booleanExpression             #andOrExpression
                    | 'NOT' booleanExpression                                                      #notExpression
                    | left=arithmeticExpression op=arithmeticOperator right=arithmeticExpression   #arithmeticComparison
                    | left=booleanExpression op=boolOperator right=booleanExpression               #booleanComparison
                    | left=(LITERAL | IDENTIFICATION) op=arithmeticOperator right=(LITERAL | IDENTIFICATION) #literalComparison
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

arithmeticOperator : op=(EQUAL | GREATER | LESS | GREATER_OR_EQUAL | LESS_OR_EQUAL | NOT_EQUAL);
boolOperator : op=( EQUAL | NOT_EQUAL );
numOperator : op=(PLUS | MINUS | MULTIPLICATION | DIVISION);

// ok
ifStatement : 'IF' booleanExpression blockStatement;
whileStatement : 'WHILE' booleanExpression blockStatement;

numParameter : LONG | NUMBER | DOUBLE | PERCENTAGE;
colorParameter : LONG | DOUBLE ;
mimicStatement : 'MIMIC' LONG blockStatement;
mirrorStatement : 'MIRROR' (numParameter numParameter | numParameter numParameter numParameter numParameter) blockStatement;




// ok

forwardStatement : 'FWD' numParameter;
backwardStatement : 'BWD' numParameter;

moveStatement : 'MOV' numParameter numParameter;
positionStatement : 'POS' numParameter numParameter;
boolDeclaration : 'BOOL' IDENTIFICATION ('=' booleanExpression)?;

blockStatement : '{' (statement)* '}' ;
forStatement : 'FOR' IDENTIFICATION ('FROM' (NUMBER|LONG))? 'TO' (NUMBER|LONG) ('STEP' (NUMBER|LONG))?  blockStatement;
colorStatement : 'COLOR' (HEXCODE | (colorParameter colorParameter colorParameter) );
cursorStatement : 'CURSOR' LONG;
selectStatement : 'SELECT' LONG;
removeStatement : 'REMOVE' LONG;
pressStatement : 'PRESS' (PERCENTAGE | DOUBLE);
thickStatement : 'THICK' LONG;
lookAtStatement : 'LOOKAT' LONG;
hideStatement : 'HIDE';
showStatement : 'SHOW';
rotationStatement : 'TURN' arithmeticExpression;
stringDeclaration : 'STR' IDENTIFICATION ('=' LITERAL)?;
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