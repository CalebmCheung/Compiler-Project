package compiler;

// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: compiler/Java_flex.flex

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


// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
public class CMinusJFlexScanner_backup {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;
  public static final int STRING = 2;
  public static final int CHARLITERAL = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1,  1,  1, 1
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\1\u0100\1\u0200\1\u0300\1\u0400\1\u0500\1\u0600\1\u0700"+
    "\1\u0800\1\u0900\1\u0a00\1\u0b00\1\u0c00\1\u0d00\1\u0e00\1\u0f00"+
    "\1\u1000\1\u0100\1\u1100\1\u1200\1\u1300\1\u0100\1\u1400\1\u1500"+
    "\1\u1600\1\u1700\1\u1800\1\u1900\1\u1a00\1\u1b00\1\u0100\1\u1c00"+
    "\1\u1d00\1\u1e00\12\u1f00\1\u2000\1\u2100\1\u2200\1\u1f00\1\u2300"+
    "\1\u2400\2\u1f00\31\u0100\1\u2500\121\u0100\1\u2600\4\u0100\1\u2700"+
    "\1\u0100\1\u2800\1\u2900\1\u2a00\1\u2b00\1\u2c00\1\u2d00\53\u0100"+
    "\1\u2e00\41\u1f00\1\u0100\1\u2f00\1\u3000\1\u0100\1\u3100\1\u3200"+
    "\1\u3300\1\u3400\1\u3500\1\u3600\1\u3700\1\u3800\3\u1f00\1\u3900"+
    "\1\u3a00\1\u3b00\1\u3c00\1\u3d00\3\u1f00\1\u3e00\1\u3f00\4\u1f00"+
    "\1\u4000\11\u1f00\3\u0100\1\u4100\1\u4200\13\u1f00\4\u0100\1\u4300"+
    "\63\u1f00\2\u0100\1\u4400\4\u1f00\1\u4500\100\u1f00\1\u4600\40\u1f00"+
    "\1\u4700\1\u4800\1\u1f00\1\u4900\1\u4a00\1\u4b00\1\u4c00\26\u1f00"+
    "\1\u4d00\21\u1f00\246\u0100\1\u4e00\20\u0100\1\u4f00\1\u5000\77\u1f00"+
    "\2\u0100\1\u5000\u0b05\u1f00\1\u5100\1\u5200\u02fe\u1f00";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\3\1\1\1\4\16\0\4\3"+
    "\1\1\1\5\1\6\1\3\1\7\2\3\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\26\1\3\1\32\1\33\1\34\1\35\2\3\32\7"+
    "\1\36\1\3\1\37\1\3\1\7\1\3\3\7\1\40"+
    "\1\41\1\42\1\7\1\43\1\44\2\7\1\45\1\7"+
    "\1\46\1\47\2\7\1\50\1\51\1\52\1\53\1\54"+
    "\1\55\3\7\1\56\1\3\1\57\1\3\41\0\2\3"+
    "\4\7\4\3\1\7\2\3\1\0\7\3\1\7\4\3"+
    "\1\7\5\3\27\7\1\3\37\7\1\3\u01ca\7\4\3"+
    "\14\7\16\3\5\7\7\3\1\7\1\3\1\7\21\3"+
    "\160\0\5\7\1\3\2\7\2\3\4\7\10\3\1\7"+
    "\1\3\3\7\1\3\1\7\1\3\24\7\1\3\123\7"+
    "\1\3\213\7\1\3\5\0\2\3\236\7\11\3\46\7"+
    "\2\3\1\7\7\3\47\7\7\3\1\7\1\3\55\0"+
    "\1\3\1\0\1\3\2\0\1\3\2\0\1\3\1\0"+
    "\10\3\33\7\5\3\3\7\15\3\5\0\6\3\1\7"+
    "\4\3\13\0\5\3\53\7\37\0\4\3\2\7\1\0"+
    "\143\7\1\3\1\7\10\0\1\3\6\0\2\7\2\0"+
    "\1\3\4\0\2\7\12\0\3\7\2\3\1\7\17\3"+
    "\1\0\1\7\1\0\36\7\33\0\2\3\131\7\13\0"+
    "\1\7\16\3\12\0\41\7\11\0\2\7\4\3\1\7"+
    "\5\3\26\7\4\0\1\7\11\0\1\7\3\0\1\7"+
    "\5\0\22\3\31\7\3\0\104\3\1\7\1\3\13\7"+
    "\67\3\33\0\1\3\4\0\66\7\3\0\1\7\22\0"+
    "\1\7\7\0\12\7\2\0\2\3\12\0\1\3\7\7"+
    "\1\3\7\7\1\3\3\0\1\3\10\7\2\3\2\7"+
    "\2\3\26\7\1\3\7\7\1\3\1\7\3\3\4\7"+
    "\2\3\1\0\1\7\7\0\2\3\2\0\2\3\3\0"+
    "\1\7\10\3\1\0\4\3\2\7\1\3\3\7\2\0"+
    "\2\3\12\0\4\7\7\3\1\7\5\3\3\0\1\3"+
    "\6\7\4\3\2\7\2\3\26\7\1\3\7\7\1\3"+
    "\2\7\1\3\2\7\1\3\2\7\2\3\1\0\1\3"+
    "\5\0\4\3\2\0\2\3\3\0\3\3\1\0\7\3"+
    "\4\7\1\3\1\7\7\3\14\0\3\7\1\0\13\3"+
    "\3\0\1\3\11\7\1\3\3\7\1\3\26\7\1\3"+
    "\7\7\1\3\2\7\1\3\5\7\2\3\1\0\1\7"+
    "\10\0\1\3\3\0\1\3\3\0\2\3\1\7\17\3"+
    "\2\7\2\0\2\3\12\0\1\3\1\7\17\3\3\0"+
    "\1\3\10\7\2\3\2\7\2\3\26\7\1\3\7\7"+
    "\1\3\2\7\1\3\5\7\2\3\1\0\1\7\7\0"+
    "\2\3\2\0\2\3\3\0\10\3\2\0\4\3\2\7"+
    "\1\3\3\7\2\0\2\3\12\0\1\3\1\7\20\3"+
    "\1\0\1\7\1\3\6\7\3\3\3\7\1\3\4\7"+
    "\3\3\2\7\1\3\1\7\1\3\2\7\3\3\2\7"+
    "\3\3\3\7\3\3\14\7\4\3\5\0\3\3\3\0"+
    "\1\3\4\0\2\3\1\7\6\3\1\0\16\3\12\0"+
    "\11\3\1\7\7\3\3\0\1\3\10\7\1\3\3\7"+
    "\1\3\27\7\1\3\12\7\1\3\5\7\3\3\1\7"+
    "\7\0\1\3\3\0\1\3\4\0\7\3\2\0\1\3"+
    "\2\7\6\3\2\7\2\0\2\3\12\0\22\3\2\0"+
    "\1\3\10\7\1\3\3\7\1\3\27\7\1\3\12\7"+
    "\1\3\5\7\2\3\1\0\1\7\7\0\1\3\3\0"+
    "\1\3\4\0\7\3\2\0\7\3\1\7\1\3\2\7"+
    "\2\0\2\3\12\0\1\3\2\7\17\3\2\0\1\3"+
    "\10\7\1\3\3\7\1\3\51\7\2\3\1\7\7\0"+
    "\1\3\3\0\1\3\4\0\1\7\10\3\1\0\10\3"+
    "\2\7\2\0\2\3\12\0\12\3\6\7\2\3\2\0"+
    "\1\3\22\7\3\3\30\7\1\3\11\7\1\3\1\7"+
    "\2\3\7\7\3\3\1\0\4\3\6\0\1\3\1\0"+
    "\1\3\10\0\22\3\2\0\15\3\60\7\1\0\2\7"+
    "\7\0\4\3\10\7\10\0\1\3\12\0\47\3\2\7"+
    "\1\3\1\7\2\3\2\7\1\3\1\7\2\3\1\7"+
    "\6\3\4\7\1\3\7\7\1\3\3\7\1\3\1\7"+
    "\1\3\1\7\2\3\2\7\1\3\4\7\1\0\2\7"+
    "\6\0\1\3\2\0\1\7\2\3\5\7\1\3\1\7"+
    "\1\3\6\0\2\3\12\0\2\3\4\7\40\3\1\7"+
    "\27\3\2\0\6\3\12\0\13\3\1\0\1\3\1\0"+
    "\1\3\1\0\4\3\2\0\10\7\1\3\44\7\4\3"+
    "\24\0\1\3\2\0\5\7\13\0\1\3\44\0\11\3"+
    "\1\0\71\3\53\7\24\0\1\7\12\0\6\3\6\7"+
    "\4\0\4\7\3\0\1\7\3\0\2\7\7\0\3\7"+
    "\4\0\15\7\14\0\1\7\17\0\2\3\46\7\1\3"+
    "\1\7\5\3\1\7\2\3\53\7\1\3\115\7\1\3"+
    "\4\7\2\3\7\7\1\3\1\7\1\3\4\7\2\3"+
    "\51\7\1\3\4\7\2\3\41\7\1\3\4\7\2\3"+
    "\7\7\1\3\1\7\1\3\4\7\2\3\17\7\1\3"+
    "\71\7\1\3\4\7\2\3\103\7\2\3\3\0\40\3"+
    "\20\7\20\3\125\7\14\3\u016c\7\2\3\21\7\1\3"+
    "\32\7\5\3\113\7\3\3\3\7\17\3\15\7\1\3"+
    "\4\7\3\0\13\3\22\7\3\0\13\3\22\7\2\0"+
    "\14\3\15\7\1\3\3\7\1\3\2\0\14\3\64\7"+
    "\40\0\3\3\1\7\3\3\2\7\1\0\2\3\12\0"+
    "\41\3\3\0\2\3\12\0\6\3\130\7\10\3\51\7"+
    "\1\0\1\7\5\3\106\7\12\3\35\7\3\3\14\0"+
    "\4\3\14\0\12\3\12\0\36\7\2\3\5\7\13\3"+
    "\54\7\4\3\21\0\7\7\2\0\6\3\12\0\46\3"+
    "\27\7\5\0\4\3\65\7\12\0\1\3\35\0\2\3"+
    "\13\0\6\3\12\0\15\3\1\7\130\3\5\0\57\7"+
    "\21\0\7\7\4\3\12\0\21\3\11\0\14\3\3\0"+
    "\36\7\15\0\2\7\12\0\54\7\16\0\14\3\44\7"+
    "\24\0\10\3\12\0\3\3\3\7\12\0\44\7\122\3"+
    "\3\0\1\3\25\0\4\7\1\0\4\7\3\0\2\7"+
    "\11\3\300\7\47\0\25\3\4\0\26\7\2\3\6\7"+
    "\2\3\46\7\2\3\6\7\2\3\10\7\1\3\1\7"+
    "\1\3\1\7\1\3\1\7\1\3\37\7\2\3\65\7"+
    "\1\3\7\7\1\3\1\7\3\3\3\7\1\3\7\7"+
    "\3\3\4\7\2\3\6\7\4\3\15\7\5\3\3\7"+
    "\1\3\7\7\16\3\5\0\32\3\5\0\20\3\2\7"+
    "\23\3\1\7\13\3\5\0\5\3\6\0\1\3\1\7"+
    "\15\3\1\7\20\3\15\7\3\3\33\7\25\3\15\0"+
    "\4\3\1\0\3\3\14\0\21\3\1\7\4\3\1\7"+
    "\2\3\12\7\1\3\1\7\3\3\5\7\6\3\1\7"+
    "\1\3\1\7\1\3\1\7\1\3\4\7\1\3\13\7"+
    "\2\3\4\7\5\3\5\7\4\3\1\7\21\3\51\7"+
    "\u0177\3\57\7\1\3\57\7\1\3\205\7\6\3\4\7"+
    "\3\0\2\7\14\3\46\7\1\3\1\7\5\3\1\7"+
    "\2\3\70\7\7\3\1\7\17\3\1\0\27\7\11\3"+
    "\7\7\1\3\7\7\1\3\7\7\1\3\7\7\1\3"+
    "\7\7\1\3\7\7\1\3\7\7\1\3\7\7\1\3"+
    "\40\0\57\3\1\7\325\3\3\7\31\3\11\7\6\0"+
    "\1\3\5\7\2\3\5\7\4\3\126\7\2\3\2\0"+
    "\2\3\3\7\1\3\132\7\1\3\4\7\5\3\51\7"+
    "\3\3\136\7\21\3\33\7\65\3\306\7\112\3\315\7"+
    "\63\3\215\7\103\3\56\7\2\3\15\7\3\3\20\7"+
    "\12\0\2\7\24\3\57\7\1\0\4\3\12\0\1\3"+
    "\31\7\7\3\1\0\120\7\2\0\45\3\11\7\2\3"+
    "\147\7\2\3\4\7\1\3\4\7\14\3\13\7\115\3"+
    "\12\7\1\0\3\7\1\0\4\7\1\0\27\7\5\0"+
    "\20\3\1\7\7\3\64\7\14\3\2\0\62\7\21\0"+
    "\13\3\12\0\6\3\22\0\6\7\3\3\1\7\4\3"+
    "\12\0\34\7\10\0\2\3\27\7\15\0\14\3\35\7"+
    "\3\3\4\0\57\7\16\0\16\3\1\7\12\0\46\3"+
    "\51\7\16\0\11\3\3\7\1\0\10\7\2\0\2\3"+
    "\12\0\6\3\27\7\3\3\1\7\1\0\4\3\60\7"+
    "\1\0\1\7\3\0\2\7\2\0\5\7\2\0\1\7"+
    "\1\0\1\7\30\3\3\7\2\3\13\7\5\0\2\3"+
    "\3\7\2\0\12\3\6\7\2\3\6\7\2\3\6\7"+
    "\11\3\7\7\1\3\7\7\221\3\43\7\10\0\1\3"+
    "\2\0\2\3\12\0\6\3\244\7\14\3\27\7\4\3"+
    "\61\7\4\3\156\7\2\3\152\7\46\3\7\7\14\3"+
    "\5\7\5\3\1\7\1\0\12\7\1\3\15\7\1\3"+
    "\5\7\1\3\1\7\1\3\2\7\1\3\2\7\1\3"+
    "\154\7\41\3\153\7\22\3\100\7\2\3\66\7\50\3"+
    "\15\7\3\3\20\0\20\3\7\0\14\3\2\7\30\3"+
    "\3\7\31\3\1\7\6\3\5\7\1\3\207\7\2\3"+
    "\1\0\4\3\1\7\13\3\12\0\7\3\32\7\4\3"+
    "\1\7\1\3\32\7\13\3\131\7\3\3\6\7\2\3"+
    "\6\7\2\3\6\7\2\3\3\7\3\3\2\7\3\3"+
    "\2\7\22\3\3\0\4\3\14\7\1\3\32\7\1\3"+
    "\23\7\1\3\2\7\1\3\17\7\2\3\16\7\42\3"+
    "\173\7\105\3\65\7\210\3\1\0\202\3\35\7\3\3"+
    "\61\7\57\3\37\7\21\3\33\7\65\3\36\7\2\3"+
    "\44\7\4\3\10\7\1\3\5\7\52\3\236\7\2\3"+
    "\12\0\126\3\6\7\2\3\1\7\1\3\54\7\1\3"+
    "\2\7\3\3\1\7\2\3\27\7\252\3\26\7\12\3"+
    "\32\7\106\3\70\7\6\3\2\7\100\3\1\7\3\0"+
    "\1\3\2\0\5\3\4\0\4\7\1\3\3\7\1\3"+
    "\33\7\4\3\3\0\4\3\1\0\40\3\35\7\203\3"+
    "\66\7\12\3\26\7\12\3\23\7\215\3\111\7\267\3"+
    "\3\0\65\7\17\0\37\3\12\0\20\3\3\0\55\7"+
    "\13\0\2\3\1\0\22\3\31\7\7\3\12\0\6\3"+
    "\3\0\44\7\16\0\1\3\12\0\100\3\3\0\60\7"+
    "\16\0\4\7\13\3\12\0\246\3\53\7\15\0\10\3"+
    "\12\0\66\3\157\7\221\3\143\7\235\3\57\7\321\3"+
    "\71\7\307\3\105\7\13\3\1\7\56\0\20\3\4\0"+
    "\15\7\140\3\2\7\u0163\3\5\0\3\3\26\0\2\3"+
    "\7\0\36\3\4\0\224\3\3\0\273\3\125\7\1\3"+
    "\107\7\1\3\2\7\2\3\1\7\2\3\2\7\2\3"+
    "\4\7\1\3\14\7\1\3\1\7\1\3\7\7\1\3"+
    "\101\7\1\3\4\7\2\3\10\7\1\3\7\7\1\3"+
    "\34\7\1\3\4\7\1\3\5\7\1\3\1\7\3\3"+
    "\7\7\1\3\u0154\7\2\3\31\7\1\3\31\7\1\3"+
    "\37\7\1\3\31\7\1\3\37\7\1\3\31\7\1\3"+
    "\37\7\1\3\31\7\1\3\37\7\1\3\31\7\1\3"+
    "\10\7\2\3\62\0\4\7\1\3\33\7\1\3\2\7"+
    "\1\3\1\7\2\3\1\7\1\3\12\7\1\3\4\7"+
    "\1\3\1\7\1\3\1\7\6\3\1\7\4\3\1\7"+
    "\1\3\1\7\1\3\1\7\1\3\3\7\1\3\2\7"+
    "\1\3\1\7\2\3\1\7\1\3\1\7\1\3\1\7"+
    "\1\3\1\7\1\3\1\7\1\3\2\7\1\3\1\7"+
    "\2\3\4\7\1\3\7\7\1\3\4\7\1\3\4\7"+
    "\1\3\1\7\1\3\12\7\1\3\21\7\5\3\3\7"+
    "\1\3\5\7\1\3\21\7\104\3\327\7\51\3\65\7"+
    "\13\3\336\7\343\3\1\0\36\3\140\0\200\3\360\0"+
    "\20\3";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[21248];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\2\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\2\17"+
    "\1\20\1\21\1\22\1\23\1\24\1\25\6\5\1\26"+
    "\1\27\1\30\2\0\1\2\1\31\1\32\1\33\1\5"+
    "\1\34\5\5\2\0\1\5\1\35\4\5\1\0\1\36"+
    "\1\37\1\5\1\40\1\5\1\0\1\5\1\41\1\0"+
    "\1\42\4\0\1\43";

  private static int [] zzUnpackAction() {
    int [] result = new int[71];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\60\0\140\0\140\0\220\0\300\0\140\0\360"+
    "\0\140\0\140\0\140\0\140\0\140\0\140\0\u0120\0\140"+
    "\0\u0150\0\140\0\u0180\0\140\0\u01b0\0\u01e0\0\u0210\0\140"+
    "\0\140\0\u0240\0\u0270\0\u02a0\0\u02d0\0\u0300\0\u0330\0\140"+
    "\0\140\0\140\0\u0360\0\u0390\0\u03c0\0\140\0\140\0\140"+
    "\0\u03f0\0\360\0\u0420\0\u0450\0\u0480\0\u04b0\0\u04e0\0\u0510"+
    "\0\u0540\0\u0570\0\360\0\u05a0\0\u05d0\0\u0600\0\u0630\0\u0660"+
    "\0\360\0\360\0\u0690\0\360\0\u06c0\0\u06f0\0\u0720\0\360"+
    "\0\u0750\0\360\0\u0780\0\u07b0\0\u07e0\0\u0810\0\140";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[71];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\2\4\1\3\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\10\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\10\1\32\2\10\1\33\1\10\1\34\1\10\1\35"+
    "\3\10\1\36\1\37\1\40\1\41\60\3\62\0\1\4"+
    "\111\0\1\42\23\0\1\10\6\0\1\10\11\0\11\10"+
    "\6\0\16\10\25\0\1\43\47\0\1\44\4\0\1\45"+
    "\60\0\11\23\62\0\1\46\57\0\1\47\57\0\1\50"+
    "\23\0\1\10\6\0\1\10\11\0\11\10\6\0\5\10"+
    "\1\51\10\10\2\0\1\10\6\0\1\10\11\0\11\10"+
    "\6\0\2\10\1\52\3\10\1\53\7\10\2\0\1\10"+
    "\6\0\1\10\11\0\11\10\6\0\13\10\1\54\2\10"+
    "\2\0\1\10\6\0\1\10\11\0\11\10\6\0\1\10"+
    "\1\55\14\10\2\0\1\10\6\0\1\10\11\0\11\10"+
    "\6\0\7\10\1\56\6\10\2\0\1\10\6\0\1\10"+
    "\11\0\11\10\6\0\3\10\1\57\12\10\24\0\1\60"+
    "\35\0\13\44\1\61\44\44\2\45\1\4\1\45\1\5"+
    "\53\45\1\10\6\0\1\10\11\0\11\10\6\0\11\10"+
    "\1\62\4\10\2\0\1\10\6\0\1\10\11\0\11\10"+
    "\6\0\12\10\1\63\3\10\2\0\1\10\6\0\1\10"+
    "\11\0\11\10\6\0\5\10\1\64\10\10\2\0\1\10"+
    "\6\0\1\10\11\0\11\10\6\0\12\10\1\65\3\10"+
    "\2\0\1\10\6\0\1\10\11\0\11\10\6\0\4\10"+
    "\1\66\11\10\2\0\1\10\6\0\1\10\11\0\11\10"+
    "\6\0\4\10\1\67\11\10\27\0\1\70\32\0\13\44"+
    "\1\61\4\44\1\4\37\44\1\10\6\0\1\10\11\0"+
    "\11\10\6\0\1\10\1\71\14\10\2\0\1\10\6\0"+
    "\1\10\11\0\11\10\6\0\5\10\1\72\10\10\2\0"+
    "\1\10\6\0\1\10\11\0\11\10\6\0\13\10\1\73"+
    "\2\10\2\0\1\10\6\0\1\10\11\0\11\10\6\0"+
    "\1\74\15\10\2\0\1\10\6\0\1\10\11\0\11\10"+
    "\6\0\5\10\1\75\10\10\32\0\1\76\27\0\1\10"+
    "\6\0\1\10\11\0\11\10\6\0\10\10\1\77\5\10"+
    "\2\0\1\10\6\0\1\10\11\0\11\10\6\0\1\10"+
    "\1\100\14\10\27\0\1\101\32\0\1\10\6\0\1\10"+
    "\11\0\11\10\6\0\6\10\1\102\7\10\33\0\1\103"+
    "\52\0\1\104\62\0\1\105\55\0\1\106\63\0\1\107"+
    "\26\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2112];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\2\11\2\1\1\11\1\1\6\11\1\1\1\11"+
    "\1\1\1\11\1\1\1\11\3\1\2\11\6\1\3\11"+
    "\2\0\1\1\3\11\7\1\2\0\6\1\1\0\5\1"+
    "\1\0\2\1\1\0\1\1\4\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[71];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;

  /* user code: */
  StringBuilder string = new StringBuilder();
  
  


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public CMinusJFlexScanner_backup(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  public token yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
              {
                return new token(token.Token_type.EOF_TOKEN);
              }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn);
            }
            // fall through
          case 36: break;
          case 2:
            { /* ignore */
            }
            // fall through
          case 37: break;
          case 3:
            { return new token(token.Token_type.NOT_TOKEN);
            }
            // fall through
          case 38: break;
          case 4:
            { yybegin(STRING); string.setLength(0);
            }
            // fall through
          case 39: break;
          case 5:
            { return new token(token.Token_type.IDENTIFIER_TOKEN, yytext());
            }
            // fall through
          case 40: break;
          case 6:
            { yybegin(CHARLITERAL);
            }
            // fall through
          case 41: break;
          case 7:
            { return new token(token.Token_type.OPEN_PAREN_TOKEN);
            }
            // fall through
          case 42: break;
          case 8:
            { return new token(token.Token_type.CLOSE_PAREN_TOKEN);
            }
            // fall through
          case 43: break;
          case 9:
            { return new token(token.Token_type.MULTIPLY_TOKEN);
            }
            // fall through
          case 44: break;
          case 10:
            { return new token(token.Token_type.PLUS_TOKEN);
            }
            // fall through
          case 45: break;
          case 11:
            { return new token(token.Token_type.COMMA_TOKEN);
            }
            // fall through
          case 46: break;
          case 12:
            { return new token(token.Token_type.MINUS_TOKEN);
            }
            // fall through
          case 47: break;
          case 13:
            { return new token(token.Token_type.DEFAULT_TOKEN);
            }
            // fall through
          case 48: break;
          case 14:
            { return new token(token.Token_type.DIVIDE_TOKEN);
            }
            // fall through
          case 49: break;
          case 15:
            { return new token(token.Token_type.NUMBER_TOKEN, yytext());
            }
            // fall through
          case 50: break;
          case 16:
            { return new token(token.Token_type.SEMI_COLON_TOKEN);
            }
            // fall through
          case 51: break;
          case 17:
            { return new token(token.Token_type.LESS_THAN_TOKEN);
            }
            // fall through
          case 52: break;
          case 18:
            { return new token(token.Token_type.EQUALITY_TOKEN);
            }
            // fall through
          case 53: break;
          case 19:
            { return new token(token.Token_type.GREATER_THAN_TOKEN);
            }
            // fall through
          case 54: break;
          case 20:
            { return new token(token.Token_type.OPEN_BRACKET_TOKEN);
            }
            // fall through
          case 55: break;
          case 21:
            { return new token(token.Token_type.CLOSE_BRACKET_TOKEN);
            }
            // fall through
          case 56: break;
          case 22:
            { return new token(token.Token_type.OPEN_CURLY_TOKEN);
            }
            // fall through
          case 57: break;
          case 23:
            { return new token(token.Token_type.CLOSE_CURLY_TOKEN);
            }
            // fall through
          case 58: break;
          case 24:
            { return new token(token.Token_type.NONEQUALITY_TOKEN);
            }
            // fall through
          case 59: break;
          case 25:
            { return new token(token.Token_type.LESS_THAN_OR_EQUAL_TOKEN);
            }
            // fall through
          case 60: break;
          case 26:
            { return new token(token.Token_type.EQUALITY_TOKEN);
            }
            // fall through
          case 61: break;
          case 27:
            { return new token(token.Token_type.GREATER_THAN_OR_EQUAL_TOKEN);
            }
            // fall through
          case 62: break;
          case 28:
            { return new token(token.Token_type.IF_TOKEN);
            }
            // fall through
          case 63: break;
          case 29:
            { return new token(token.Token_type.INTEGER_TOKEN);
            }
            // fall through
          case 64: break;
          case 30:
            { return new token(token.Token_type.ELSE_TOKEN);
            }
            // fall through
          case 65: break;
          case 31:
            { return new token(token.Token_type.DEFAULT_TOKEN);
            }
            // fall through
          case 66: break;
          case 32:
            { return new token(token.Token_type.VOID_TOKEN);
            }
            // fall through
          case 67: break;
          case 33:
            { return new token(token.Token_type.WHILE_TOKEN);
            }
            // fall through
          case 68: break;
          case 34:
            { return new token(token.Token_type.RETURN_TOKEN);
            }
            // fall through
          case 69: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
