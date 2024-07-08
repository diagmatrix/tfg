/**
 * GRAMMAR FOR SCALA-SPARK DISTRIBUTED LEARNING
 */
grammar ssdl;
program: (stmt|comment)+;
// Statements
stmt:
    'begin' action 'end'
    |'fit' ID INT
    |'out' ID STRING?;
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
    |'vel_c2';
// Comments
comment: '/*' .*? '*/';
// Special types
OBJECT: 'ann' | 'dapso';
NET_TYPE: 'classifier' | 'regressor';
// Standard types
INT: [0-9]+;
FLOAT: INT '.' INT;
ID: [a-zA-Z0-9]+;
STRING: '"'ID'"';
FILE: '"'ID'.csv"';
// Whitespaces
WS: [ \t\r\n]+ -> skip;
