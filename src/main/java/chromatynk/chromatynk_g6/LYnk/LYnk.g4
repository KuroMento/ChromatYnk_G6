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

booleanExpression: '(' booleanExpression ')'                                           #parenthesisVar
                    | left=booleanExpression op=('AND' | 'OR') right=booleanExpression #andOrExpression
                    | 'NOT' booleanExpression                                          #notExpression
                    | comparison                                                       #comparisonExpression
                    | BOOLEAN                                                          #booleanVar
                    | IDENTIFICATION                                                   #identificationVar
                    ;

arithmeticExpression : '(' arithmeticExpression ')'                                                         #parenthesisExpression
                        | left=arithmeticExpression op=(MULTIPLICATION|DIVISION) right=arithmeticExpression #mulDivExpression
                        | left=arithmeticExpression op=(PLUS|MINUS) right=arithmeticExpression              #plusMinusExpression
                        | left=arithmeticExpression numOperator right=arithmeticExpression                  #compExpression
                        | IDENTIFICATION                                                                    #identificationExpression
                        | DOUBLE                                                                            #doubleExpression
                        ;


blockStatement : '{' (statement)* '}' ;
comparison : (IDENTIFICATION | BOOLEAN) boolOperator (IDENTIFICATION | BOOLEAN);
boolOperator : EQUAL | GREATER | LESS | GREATER_OR_EQUAL | LESS_OR_EQUAL | NOT_EQUAL;
numOperator : PLUS | MINUS | MULTIPLICATION | DIVISION;
ifStatement : 'IF' booleanExpression blockStatement;
whileStatement : 'WHILE' booleanExpression blockStatement;
forStatement : 'FOR' IDENTIFICATION ('FROM' NUMERAL)? 'TO' NUMERAL ('STEP' NUMERAL)?  blockStatement;
mimicStatement : 'MIMIC' IDENTIFICATION;
percentage : NUMERAL PERCENT | DOUBLE PERCENT;
numParameter : NUMERAL | DOUBLE | percentage;
mirrorStatement : 'MIRROR' (numParameter{2} | numParameter{4}) blockStatement;
cursorStatement : 'CURSOR' NUMERAL;
selectStatement : 'SELECT' NUMERAL;
removeStatement : 'REMOVE' NUMERAL;
hideStatement : 'HIDE' NUMERAL;
showStatement : 'SHOW' NUMERAL;
pressStatement : 'PRESS' percentage;
thickStatement : 'THICK' NUMERAL;
colorStatement : 'COLOR' HEXCODE | numParameter{3};
lookAtStatement : 'LOOKAT' NUMERAL;
forwardStatement : 'FWD' numParameter;
backwardStatement : 'BWD' numParameter;
rotationStatement : 'TURN' NUMERAL | 'TURN' DOUBLE;
moveStatement : 'MOV' numParameter{2};
positionStatement : 'POS' numParameter{2};
boolDeclaration : 'BOOL' IDENTIFICATION ('=' BOOLEAN)?;
stringDeclaration : 'STR' IDENTIFICATION ('=' LITERAL)?;
numberDeclaration : 'NUM' IDENTIFICATION ('=' DOUBLE)?;





//Tokens

LETTER : [a-zA-Z];
NUMBER : '-'? [0-9];
NUMERAL : '-'? [0-9]+;
IDENTIFICATION : (LETTER)+ ;
LITERAL : '"' (~["])* '"' ;
DOUBLE : (NUMERAL '.' NUMERAL) | NUMERAL;
fragment BOOLEAN : 'TRUE' | 'FALSE';
HEXLETTER : NUMBER | [A-F];
HEXCODE : '#' HEXLETTER{6};

MULTIPLICATION : '*';
DIVISION : '/';
PLUS : '+';
MINUS : '-';
PERCENT : '%';


ASSIGN              : '=' ;
EQUAL               : '==' ;
NOT_EQUAL           : '!=' ;
LESS                : '<' ;
LESS_OR_EQUAL       : '<=' ;
GREATER             : '>' ;
GREATER_OR_EQUAL    : '>=' ;

SPACE : [ \r\n\t]+ -> skip;