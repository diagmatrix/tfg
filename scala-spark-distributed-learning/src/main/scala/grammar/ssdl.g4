/**
 * GRAMMAR FOR SCALA-SPARK DISTRIBUTED LEARNING
 */
grammar ssdl;
program: (stmt|COMMENT)+;
// Statements
stmt:
    'begin' action 'end'
    |'fit' ID INT
    |'out' ID FILE?;
// Actions
action: OBJECT ':' ID parameter+;
// Parameters
parameter: 'set' (ann_argument|dapso_argument);
// Arguments
ann_argument:
    'type' NET_TYPE
    |'neurons' '['INT','INT']'
    |'data' '['FILE','FILE']'
    |'algorithm' ID;
dapso_argument:
    'particles' INT
    |'batch_size' INT
    |'n_rdd' INT
    |'pos_bound' FLOAT
    |'vel_bound' FLOAT
    |'vel_w' FLOAT
    |'vel_c1' FLOAT
    |'vel_c2' FLOAT;
// Special types
OBJECT: 'ann' | 'dapso';
NET_TYPE: 'classifier' | 'regressor';
// Comments
COMMENT: '/*' .*? '*/' -> skip;
// Standard types
INT: [0-9]+;
FLOAT: INT '.' INT | INT;
ID: [a-zA-Z0-9]+;
STRING: '"'ID'"';
FILE: '"'.+?'.csv"';
// Whitespaces
WS: [ \t\r\n]+ -> skip;
