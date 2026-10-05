package Parse;
import ErrorMsg.ErrorMsg;

%% 

%implements Lexer
%function nextToken
%type java_cup.runtime.Symbol
%char
%state COMMENT 
%state STRING

%{
private StringBuffer stringBuffer;

private void newline() {
  errorMsg.newline(yychar);
}

private void err(int pos, String s) {
  errorMsg.error(pos,s);
}

private void err(String s) {
  err(yychar,s);
}

private java_cup.runtime.Symbol tok(int kind) {
    return tok(kind, null);
}

private java_cup.runtime.Symbol tok(int kind, Object value) {
    return new java_cup.runtime.Symbol(kind, yychar, yychar+yylength(), value);
}
private Integer convertInteger(String text) {
    // Remove C integer suffixes such as U, L, UL, or LU.
    String number = text.replaceAll("[uUlL]+$", "");

    if (number.startsWith("0x") || number.startsWith("0X")) {
        return Integer.valueOf(number.substring(2), 16);
    }

    if (number.startsWith("0") && number.length() > 1) {
        return Integer.valueOf(number, 8);
    }

    return Integer.valueOf(number, 10);
}

private String decodeSimpleEscape(String text) {
    char c = text.charAt(2);

    switch (c) {
        case '\'': return "'";
        case '"':  return "\"";
        case '?':  return "?";
        case '\\': return "\\";
        case 'a':  return "\u0007";
        case 'b':  return "\b";
        case 'f':  return "\f";
        case 'n':  return "\n";
        case 'r':  return "\r";
        case 't':  return "\t";
        case 'v':  return "\u000B";
        default:   return "";
    }
}
private String decodeOctalEscape(String text) {
    String digits = text.substring(2, text.length() - 1);
    int value = Integer.parseInt(digits, 8);
    return String.valueOf((char) value);
}
private String decodeHexEscape(String text) {
    String digits = text.substring(3, text.length() - 1);
    int value = Integer.parseInt(digits, 16);
    return String.valueOf((char) value);
}

private ErrorMsg errorMsg;

Yylex(java.io.InputStream s, ErrorMsg e) {
  this(s);
  errorMsg=e;
}

%}

%eofval{
	{
	 if (yy_lexical_state == COMMENT) {
	   err("Unterminated comment at end of file");
	 }
	 else if (yy_lexical_state == STRING) {
	   err("Unterminated string at end of file");
	 }
	 return tok(sym.EOF, null);
        }
%eofval}       

%%

<YYINITIAL> "\""    { stringBuffer = new StringBuffer(); yybegin(STRING); }
<STRING> "\""    { yybegin(YYINITIAL); return tok(sym.STRING_LITERAL, stringBuffer.toString()); }

<STRING> \\[\'\"\?\\abfnrtv]    {
    char escape = yytext().charAt(1);
    switch (escape) {
        case '\'': stringBuffer.append('\''); break;
        case '"':  stringBuffer.append('"'); break;
        case '?':  stringBuffer.append('?'); break;
        case '\\': stringBuffer.append('\\'); break;
        case 'a':  stringBuffer.append('\u0007'); break;
        case 'b':  stringBuffer.append('\b'); break;
        case 'f':  stringBuffer.append('\f'); break;
        case 'n':  stringBuffer.append('\n'); break;
        case 'r':  stringBuffer.append('\r'); break;
        case 't':  stringBuffer.append('\t'); break;
        case 'v':  stringBuffer.append('\u000B'); break;
    }
}

<STRING> \\[0-7][0-7]?[0-7]?    {
    String digits = yytext().substring(1);
    int value = Integer.parseInt(digits, 8);
    stringBuffer.append((char) value);
}

<STRING> \\x[0-9a-fA-F]+    {
    String digits = yytext().substring(2);
    int value = Integer.parseInt(digits, 16);
    stringBuffer.append((char) value);
}

<STRING> \n    { err("Newline in string literal"); newline(); yybegin(YYINITIAL); }
<STRING> \\[^\n]   { err("Illegal escape sequence: " + yytext()); stringBuffer.append(yytext()); }
<STRING> .     { stringBuffer.append(yytext()); }

"/*"              { yybegin(COMMENT); } 
<COMMENT> "*/"    { yybegin(YYINITIAL); }
<COMMENT> \n      { newline(); }
<COMMENT> .       { /* just consume, do nothing */ }

<YYINITIAL> " "	{}
<YYINITIAL> \n	{newline();}
<YYINITIAL> \r  {}
<YYINITIAL> \t  {}
<YYINITIAL> \f  {}
<YYINITIAL> \x0B  {}

<YYINITIAL> ","	{return tok(sym.COMMA, null);}
<YYINITIAL> "auto"      { return tok(sym.AUTO); }
<YYINITIAL> "double"    { return tok(sym.DOUBLE); }
<YYINITIAL> "int"       { return tok(sym.INT); }
<YYINITIAL> "struct"    { return tok(sym.STRUCT); }

<YYINITIAL> "break"     { return tok(sym.BREAK); }
<YYINITIAL> "else"      { return tok(sym.ELSE); }
<YYINITIAL> "long"      { return tok(sym.LONG); }
<YYINITIAL> "switch"    { return tok(sym.SWITCH); }

<YYINITIAL> "case"      { return tok(sym.CASE); }
<YYINITIAL> "enum"      { return tok(sym.ENUM); }
<YYINITIAL> "register"  { return tok(sym.REGISTER); }
<YYINITIAL> "typedef"   { return tok(sym.TYPEDEF); }

<YYINITIAL> "char"      { return tok(sym.CHAR); }
<YYINITIAL> "extern"    { return tok(sym.EXTERN); }
<YYINITIAL> "return"    { return tok(sym.RETURN); }
<YYINITIAL> "union"     { return tok(sym.UNION); }

<YYINITIAL> "const"     { return tok(sym.CONST); }
<YYINITIAL> "float"     { return tok(sym.FLOAT); }
<YYINITIAL> "short"     { return tok(sym.SHORT); }
<YYINITIAL> "unsigned"  { return tok(sym.UNSIGNED); }

<YYINITIAL> "continue"  { return tok(sym.CONTINUE); }
<YYINITIAL> "for"       { return tok(sym.FOR); }
<YYINITIAL> "signed"    { return tok(sym.SIGNED); }
<YYINITIAL> "void"      { return tok(sym.VOID); }

<YYINITIAL> "default"   { return tok(sym.DEFAULT); }
<YYINITIAL> "goto"      { return tok(sym.GOTO); }
<YYINITIAL> "sizeof"    { return tok(sym.SIZEOF); }
<YYINITIAL> "volatile"  { return tok(sym.VOLATILE); }

<YYINITIAL> "do"        { return tok(sym.DO); }
<YYINITIAL> "if"        { return tok(sym.IF); }
<YYINITIAL> "static"    { return tok(sym.STATIC); }
<YYINITIAL> "while"     { return tok(sym.WHILE); }

<YYINITIAL> "var"       { return tok(sym.VAR); }
<YYINITIAL> "fun"       { return tok(sym.FUN); }

<YYINITIAL> [a-zA-Z_][a-zA-Z0-9_]*    { return tok(sym.ID, yytext()); }
<YYINITIAL> "++"  { return tok(sym.INCREMENT); }
<YYINITIAL> "--"  { return tok(sym.DECREMENT); }
<YYINITIAL> "->"  { return tok(sym.ARROW); }

<YYINITIAL> "<<=" { return tok(sym.LSHIFTASSIGN); }
<YYINITIAL> ">>=" { return tok(sym.RSHIFTASSIGN); }

<YYINITIAL> "+="  { return tok(sym.ADDASSIGN); }
<YYINITIAL> "-="  { return tok(sym.SUBASSIGN); }
<YYINITIAL> "*="  { return tok(sym.MULASSIGN); }
<YYINITIAL> "/="  { return tok(sym.DIVASSIGN); }
<YYINITIAL> "%="  { return tok(sym.MODASSIGN); }

<YYINITIAL> "&="  { return tok(sym.BWISEANDASSIGN); }
<YYINITIAL> "^="  { return tok(sym.BWISEXORASSIGN); }
<YYINITIAL> "|="  { return tok(sym.BWISEORASSIGN); }

<YYINITIAL> "<<"  { return tok(sym.LSHIFT); }
<YYINITIAL> ">>"  { return tok(sym.RSHIFT); }

<YYINITIAL> "<="  { return tok(sym.LE); }
<YYINITIAL> ">="  { return tok(sym.GE); }
<YYINITIAL> "=="  { return tok(sym.EQ); }
<YYINITIAL> "!="  { return tok(sym.NEQ); }

<YYINITIAL> "&&"  { return tok(sym.AND); }
<YYINITIAL> "||"  { return tok(sym.OR); }

<YYINITIAL> "+"   { return tok(sym.PLUS); }
<YYINITIAL> "-"   { return tok(sym.MINUS); }
<YYINITIAL> "*"   { return tok(sym.TIMES); }
<YYINITIAL> "/"   { return tok(sym.DIVIDE); }
<YYINITIAL> "%"   { return tok(sym.MODULUS); }

<YYINITIAL> "="   { return tok(sym.ASSIGN); }

<YYINITIAL> "<"   { return tok(sym.LT); }
<YYINITIAL> ">"   { return tok(sym.GT); }

<YYINITIAL> "&"   { return tok(sym.BITWISEAND); }
<YYINITIAL> "|"   { return tok(sym.BWISEOR); }
<YYINITIAL> "^"   { return tok(sym.BWISEXOR); }

<YYINITIAL> "~"   { return tok(sym.TILDE); }
<YYINITIAL> "!"   { return tok(sym.NOT); }
<YYINITIAL> "?"   { return tok(sym.QUESTION); }


<YYINITIAL> \[    { return tok(sym.LBRACK); }
<YYINITIAL> \]    { return tok(sym.RBRACK); }

<YYINITIAL> \(    { return tok(sym.LPAREN); }
<YYINITIAL> \)    { return tok(sym.RPAREN); }

<YYINITIAL> \{    { return tok(sym.LBRACE); }
<YYINITIAL> \}    { return tok(sym.RBRACE); }

<YYINITIAL> ";"   { return tok(sym.SEMICOLON); }
<YYINITIAL> ":"   { return tok(sym.COLON); }

<YYINITIAL> \.\.\. { return tok(sym.ELIPSES); }
<YYINITIAL> \.      { return tok(sym.PERIOD); }



<YYINITIAL> 0[xX][0-9a-fA-F]+([uU][lL]?|[lL][uU]?)?    { return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
<YYINITIAL> 0[0-7]*([uU][lL]?|[lL][uU]?)?              { return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
<YYINITIAL> [1-9][0-9]*([uU][lL]?|[lL][uU]?)?          { return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }


<YYINITIAL> '[^'\\\n]'    { return tok(sym.CHAR_LITERAL, yytext().substring(1, 2)); }
<YYINITIAL> '\\[\'\"\?\\abfnrtv]'    { return tok(sym.CHAR_LITERAL, decodeSimpleEscape(yytext())); }

<YYINITIAL> '\\[0-7][0-7]?[0-7]?'    { return tok(sym.CHAR_LITERAL, decodeOctalEscape(yytext())); }
<YYINITIAL> '\\x[0-9a-fA-F]+'    { return tok(sym.CHAR_LITERAL, decodeHexEscape(yytext())); }
<YYINITIAL> '\\[^\n]'   { err("Illegal escape sequence in character literal: " + yytext()); }
<YYINITIAL> .   { err("Illegal character: " + yytext()); }