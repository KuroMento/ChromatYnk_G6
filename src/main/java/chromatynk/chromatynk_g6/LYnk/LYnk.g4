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

ifStatement : 'IF' booleanExpression blockStatement;
forStatement : 'FOR' IDENTIFICATION ('FROM' from=(NUMBER|LONG))? 'TO' to=(NUMBER|LONG) ('STEP' step=(NUMBER|LONG))?  blockStatement;
whileStatement : 'WHILE' booleanExpression blockStatement;

numParameter : LONG | NUMBER | DOUBLE | PERCENTAGE | IDENTIFICATION; //Identification added to use variable in parameters
mimicStatement : 'MIMIC' LONG blockStatement;
mirrorStatement : 'MIRROR' (( arithmeticExpression | PERCENTAGE ) ( arithmeticExpression | PERCENTAGE )
                           | ( arithmeticExpression | PERCENTAGE ) ( arithmeticExpression | PERCENTAGE ) ( arithmeticExpression | PERCENTAGE ) ( arithmeticExpression | PERCENTAGE ))
                           blockStatement; //MIRROR takes 2 or 4 values or %

forwardStatement : 'FWD' ( arithmeticExpression | PERCENTAGE ); // FWD need an expression with a value or %
backwardStatement : 'BWD' ( arithmeticExpression | PERCENTAGE ); // BWD need an expression with a value or %

moveStatement : 'MOV' ( arithmeticExpression | PERCENTAGE ) ( arithmeticExpression | PERCENTAGE ); // MOV need an expression with 2 values or %
positionStatement : 'POS' ( arithmeticExpression | PERCENTAGE ) ( arithmeticExpression | PERCENTAGE ); // POS need an expression with 2 values or %


blockStatement : '{' (statement)* '}' ;
colorStatement : 'COLOR' (HEXCODE | arithmeticExpression arithmeticExpression arithmeticExpression ); // COLOR need a value in hexa or 3 expression
cursorStatement : 'CURSOR' LONG;
selectStatement : 'SELECT' LONG;
removeStatement : 'REMOVE' LONG;
pressStatement : 'PRESS' ( arithmeticExpression | PERCENTAGE ); // PRESS need an expression with a value or %
thickStatement : 'THICK' arithmeticExpression; // THICK need an expression with a value but not %
lookAtStatement : 'LOOKAT' ((id=LONG) | ( arithmeticExpression | PERCENTAGE )  ( arithmeticExpression | PERCENTAGE )); // LOOKAT needs a cursor id or 2 values or %
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