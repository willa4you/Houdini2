grammar Theory;

theory: LBRACE version CM logicmodel RBRACE EOF;
version: QT VERSION QT CLN QT DECIMAL QT  ;
logicmodel : QT LOGICMODEL QT CLN LBRACE 
    QT FACTS QT CLN LSQUARE facts RSQUARE CM 
    QT RULES QT CLN LSQUARE rules RSQUARE CM
    QT SUPREL QT CLN LSQUARE suprel RSQUARE 
    RBRACE;

literal: ALPHANUMERIC ;
facts:  WS | QT literal QT | QT literal QT CM facts ;
unquotedfacts : WS | literal | literal CM unquotedfacts ;
rules:  WS | QT (WS|unquotedfacts) ARROW literal QT | QT (WS|unquotedfacts) ARROW literal QT CM rules ;
suprel: WS | QT RULETAG (GT|LT) RULETAG QT | QT RULETAG (GT|LT) RULETAG QT CM suprel ;

VERSION: 'version' ;
LOGICMODEL : 'logicModel' ;
FACTS: 'facts' ;
RULES: 'rules' ;
SUPREL: 'supRules' ;

NATURAL: '0' | [1-9][0-9]* ;
DECIMAL : ([1-9]+[0-9]*'.'[0-9]+|'0.'[0-9]+) ;
ALPHANUMERIC : [~]?[a-zA-Z_]+ ;
ARROW: ('-'|'='|'~')'>' ;
RULETAG: 'r' NATURAL;

CLN: ':';
LBRACE: '{' ;
RBRACE : '}' ;
LSQUARE : '[' ;
RSQUARE : ']' ;
GT: '>' ;
LT: '<' ;
QT: '"' ;
CM: ',' ;
WS : [ \t\r\n]+ -> skip ;


