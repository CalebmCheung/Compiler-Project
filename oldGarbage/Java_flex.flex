/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 1998-2018  Gerwin Klein <lsf@jflex.de>                    *
 * All rights reserved.                                                    *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* Java 1.2 language lexer specification */

/* Use together with unicode.flex for Unicode preprocesssing */
/* and java12.cup for a Java 1.2 parser                      */

/* Note that this lexer specification is not tuned for speed.
   It is in fact quite slow on integer and floating point literals, 
   because the input is read twice and the methods used to parse
   the numbers are not very fast. 
   For a production quality application (e.g. a Java compiler) 
   this could be optimized */

%%

%public
%class CMinusJFlexScanner

%unicode

%line
%column

%{
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | 
          {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*

/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]

/* errors */
errorDecIdent = {DecIntegerLiteral}+{Identifier}
errorNotSym = "! ";

%state STRING, CHARLITERAL

%%token

<YYINITIAL> {

  /* keywords */

  "else"                         { return new token(token.Token_type.ELSE_TOKEN); }
  "int"                          { return new token(token.Token_type.INTEGER_TOKEN); }
  "if"                           { return new token(token.Token_type.IF_TOKEN); }
  "return"                       { return new token(token.Token_type.RETURN_TOKEN); }
  "void"                         { return new token(token.Token_type.VOID_TOKEN); }
  "while"                        { return new token(token.Token_type.WHILE_TOKEN); }
  
  /* separators */
  "("                            { return new token(token.Token_type.OPEN_PAREN_TOKEN); }
  ")"                            { return new token(token.Token_type.CLOSE_PAREN_TOKEN); }
  "{"                            { return new token(token.Token_type.OPEN_CURLY_TOKEN); }
  "}"                            { return new token(token.Token_type.CLOSE_CURLY_TOKEN); }
  "["                            { return new token(token.Token_type.OPEN_BRACKET_TOKEN); }
  "]"                            { return new token(token.Token_type.CLOSE_BRACKET_TOKEN); }
  ";"                            { return new token(token.Token_type.SEMI_COLON_TOKEN); }
  ","                            { return new token(token.Token_type.COMMA_TOKEN); }
  
  /* operators */
  "="                            { return new token(token.Token_type.ASSIGN_TOKEN); }
  ">"                            { return new token(token.Token_type.GREATER_THAN_TOKEN); }
  "<"                            { return new token(token.Token_type.LESS_THAN_TOKEN); }
  "=="                           { return new token(token.Token_type.EQUALITY_TOKEN); }
  "<="                           { return new token(token.Token_type.LESS_THAN_OR_EQUAL_TOKEN); }
  ">="                           { return new token(token.Token_type.GREATER_THAN_OR_EQUAL_TOKEN); }
  "!="                           { return new token(token.Token_type.NONEQUALITY_TOKEN); }
  "!"                            { return new token(token.Token_type.ERROR_TOKEN); }
  "+"                            { return new token(token.Token_type.PLUS_TOKEN); }
  "-"                            { return new token(token.Token_type.MINUS_TOKEN); }
  "*"                            { return new token(token.Token_type.MULTIPLY_TOKEN); }
  "/"                            { return new token(token.Token_type.DIVIDE_TOKEN); }
  
  /* string literal */
  \"                             { yybegin(STRING); }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); }

  /* numeric literals */
  
  {DecIntegerLiteral}            { return new token(token.Token_type.NUMBER_TOKEN, yytext()); }
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return new token(token.Token_type.IDENTIFIER_TOKEN, yytext()); }  

  /* error token */
  {errorDecIdent}                { return new token(token.Token_type.ERROR_TOKEN); }
  {errorNotSym}                  { return new token(token.Token_type.ERROR_TOKEN); }
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return new token(token.Token_type.EOF_TOKEN); }