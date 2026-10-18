package Parse;
import ErrorMsg.ErrorMsg;


class Yylex implements Lexer {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final char YYEOF = '\uFFFF';

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
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int STRING = 2;
	private final int YYINITIAL = 0;
	private final int COMMENT = 1;
	private final int yy_state_dtrans[] = {
		0,
		143,
		145
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private char yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_start () {
		++yychar;
		++yy_buffer_start;
	}
	private void yy_pushback () {
		--yy_buffer_end;
	}
	private void yy_mark_start () {
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
private int [][] unpackFromString(int size1, int size2, String st)
    {
      int colonIndex = -1;
      String lengthString;
      int sequenceLength = 0;
      int sequenceInteger = 0;
      int commaIndex;
      String workString;
      int res[][] = new int[size1][size2];
      for (int i= 0; i < size1; i++)
	for (int j= 0; j < size2; j++)
	  {
	    if (sequenceLength == 0) 
	      {	
		commaIndex = st.indexOf(',');
		if (commaIndex == -1)
		  workString = st;
		else
		  workString = st.substring(0, commaIndex);
		st = st.substring(commaIndex+1);
		colonIndex = workString.indexOf(':');
		if (colonIndex == -1)
		  {
		    res[i][j] = Integer.parseInt(workString);
		  }
		else 
		  {
		    lengthString = workString.substring(colonIndex+1);  
		    sequenceLength = Integer.parseInt(lengthString);
		    workString = workString.substring(0,colonIndex);
		    sequenceInteger = Integer.parseInt(workString);
		    res[i][j] = sequenceInteger;
		    sequenceLength--;
		  }
	      }
	    else 
	      {
		res[i][j] = sequenceInteger;
		sequenceLength--;
	      }
	  }
      return res;
    }
	private int yy_acpt[] = {
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR
	};
	private int yy_cmap[] = {
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 1, 2, 3, 4, 5, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		6, 7, 8, 0, 0, 9, 10, 11,
		12, 13, 14, 15, 16, 17, 18, 19,
		20, 21, 21, 21, 21, 21, 21, 21,
		22, 22, 23, 24, 25, 26, 27, 28,
		0, 29, 29, 29, 29, 29, 29, 30,
		30, 30, 30, 30, 31, 30, 30, 30,
		30, 30, 30, 30, 30, 32, 30, 30,
		33, 30, 30, 34, 35, 36, 37, 30,
		0, 38, 39, 40, 41, 42, 43, 44,
		45, 46, 30, 47, 48, 49, 50, 51,
		52, 30, 53, 54, 55, 56, 57, 58,
		59, 60, 61, 62, 63, 64, 65, 0
		
	};
	private int yy_rmap[] = {
		0, 1, 1, 1, 1, 1, 1, 2,
		1, 3, 4, 1, 1, 5, 6, 1,
		7, 8, 9, 10, 11, 1, 1, 12,
		13, 14, 1, 15, 1, 1, 16, 1,
		17, 1, 1, 1, 1, 1, 1, 1,
		1, 1, 1, 1, 1, 1, 1, 18,
		1, 1, 1, 19, 1, 20, 15, 1,
		1, 1, 1, 21, 1, 1, 15, 15,
		15, 15, 1, 1, 1, 15, 15, 15,
		15, 15, 15, 15, 1, 15, 15, 15,
		15, 15, 15, 15, 15, 15, 15, 15,
		15, 15, 15, 15, 15, 15, 15, 1,
		1, 1, 1, 1, 1, 1, 1, 22,
		23, 24, 25, 26, 27, 28, 29, 30,
		23, 1, 31, 32, 33, 34, 35, 30,
		36, 37, 38, 1, 39, 1, 40, 1,
		41, 42, 43, 44, 45, 46, 47, 48,
		49, 50, 51, 52, 53, 54, 55, 56,
		57, 58, 59, 60, 61, 62, 63, 64,
		65, 66, 67, 68, 69, 70, 71, 72,
		73, 74, 75, 76, 77, 78, 79, 80,
		81, 82, 83, 84, 85, 86, 87, 88,
		89, 90, 91, 92, 93, 94, 95, 96,
		97, 98, 99, 100, 101, 102, 103, 104,
		105, 106, 107, 108, 109, 110, 111, 112,
		113, 114, 115, 116, 117, 118, 119, 120,
		121, 122, 123, 124, 125, 126, 127, 128,
		129, 130, 131, 132, 133, 134, 135, 136,
		137, 138, 139, 140, 141, 142, 143, 144,
		145, 146, 147, 148 
	};
	private int yy_nxt[][] = unpackFromString(149,66,
"-1,1,2,3,4,5,6,7,8,9,10,105,11,12,13,14,15,16,17,18,19,20:2,21,22,23,24,25,26,27:5,28,-1,29,30,194,217,195,108,196,165,197,27,117,27,198,27:4,230,218,235,219,168,220,27:3,31,32,33,34,-1:92,35,-1:65,36,-1:49,37,-1:15,38,-1:65,39,-1:54,40,-1:10,41,-1:56,42,-1:8,43,44,-1:56,126,-1:61,45,-1:11,46,-1:59,106:2,-1:9,115,122,129,-1:14,115,-1:7,122,-1:2,129,-1:26,20:3,-1:8,107,116,-1:15,107,-1:7,116,-1:34,47,48,-1:65,49,-1:65,50,51,-1:58,27:3,-1:6,27:5,-1:4,27:24,-1:30,52,-1:65,55,-1:36,56,-1:28,60,-1:65,61,-1:59,27:3,-1:6,27:5,-1:4,27:18,205,27:5,-1:24,59:3,-1:6,59,-1,109,118,-1:5,59:6,-1:4,109,-1:7,118,-1:29,166:2,-1:64,104:3,-1:6,104,-1:8,104:6,-1:22,114:2,-1,114:8,-1,114:23,121,114:30,-1:20,106:2,-1:9,115,122,-1:15,115,-1:7,122,-1:41,123,-1:23,123,-1:29,27:3,-1:6,27:5,-1:4,27:13,53,27:10,-1:36,125,-1:23,125,-1:28,97,-1:60,45,-1:62,57,-1:86,127,-1:23,127,-1:40,123,-1:16,123,-1:37,27:3,-1:6,27:5,-1:4,27:5,54,27:6,130,27:11,-1:35,125,-1:16,125,-1:17,101:2,-1,101:5,102,101:2,102,101:8,103:2,101:6,102,101:6,102,101:2,102:2,101:3,102,101:6,102,101:2,102,101,102,101,102,101,112,101:6,131:2,-1,131:5,133,131:2,133,131:8,135:2,131:6,133,131:6,133,131:2,133:2,131:3,133,131:6,133,131:2,133,131,133,131,133,131,137,131:6,-1:31,127,-1:16,127,-1:37,27:3,-1:6,27:5,-1:4,27:15,62,27:8,-1:22,58,-1:67,27:3,-1:6,27:5,-1:4,27:12,63,27:11,-1:24,59:3,-1:6,59,-1:8,59:6,-1:42,27:3,-1:6,27:5,-1:4,27:17,64,27:6,-1:15,66,-1:74,27:3,-1:6,27:5,-1:4,27:15,65,27:8,-1:15,67,-1:74,27:3,-1:6,27:5,-1:4,27:13,69,27:10,-1:15,68,-1:8,167:2,-1:64,27:3,-1:6,27:5,-1:4,27:15,70,27:8,-1:15,66,-1:8,139:3,-1:6,139,-1:8,139:6,-1:42,27:3,-1:6,27:5,-1:4,27:4,71,27:19,-1:15,76,-1:8,139:3,-1:6,139,-1:8,139:6,-1:42,27:3,-1:6,27:5,-1:4,27:11,72,27:12,-1:15,68,-1:74,27:3,-1:6,27:5,-1:4,27:13,73,27:10,-1:4,95:2,96,95:11,110,95:4,119,95:46,-1:20,27:3,-1:6,27:5,-1:4,27:6,74,27:17,-1:4,98:2,99,98:5,100,98:10,111,98:15,120,98:30,-1:20,27:3,-1:6,27:5,-1:4,27:3,75,27:20,-1:24,27:3,-1:6,27:5,-1:4,27:9,77,27:14,-1:24,27:3,-1:6,27:5,-1:4,27:17,78,27:6,-1:24,27:3,-1:6,27:5,-1:4,27:17,79,27:6,-1:24,27:3,-1:6,27:5,-1:4,27:17,80,27:6,-1:24,27:3,-1:6,27:5,-1:4,27:12,81,27:11,-1:24,27:3,-1:6,27:5,-1:4,27:4,82,27:19,-1:24,27:3,-1:6,27:5,-1:4,27:4,83,27:19,-1:24,27:3,-1:6,27:5,-1:4,27:12,84,27:11,-1:24,27:3,-1:6,27:5,-1:4,27:12,85,27:11,-1:24,27:3,-1:6,27:5,-1:4,27:3,86,27:20,-1:24,27:3,-1:6,27:5,-1:4,27:5,87,27:18,-1:24,27:3,-1:6,27:5,-1:4,27:2,88,27:21,-1:24,27:3,-1:6,27:5,-1:4,27:17,89,27:6,-1:24,27:3,-1:6,27:5,-1:4,27:5,90,27:18,-1:24,27:3,-1:6,27:5,-1:4,27:4,91,27:19,-1:24,27:3,-1:6,27:5,-1:4,27:15,92,27:8,-1:24,27:3,-1:6,27:5,-1:4,27:3,93,27:20,-1:24,27:3,-1:6,27:5,-1:4,27:4,94,27:19,-1:24,27:3,-1:6,27:5,-1:4,27:10,201,27:2,124,27:4,128,27:5,-1:24,113:2,-1:55,68,-1:8,141:2,-1:64,27:3,-1:6,27:5,-1:4,132,27:12,175,27:10,-1:24,27:3,-1:6,27:5,-1:4,27:17,134,27:6,-1:24,27:3,-1:6,27:5,-1:4,136,27:23,-1:24,27:3,-1:6,27:5,-1:4,27:16,138,27:7,-1:24,27:3,-1:6,27:5,-1:4,27:18,140,27:5,-1:24,27:3,-1:6,27:5,-1:4,27:17,142,27:6,-1:24,27:3,-1:6,27:5,-1:4,27:12,144,27:11,-1:24,27:3,-1:6,27:5,-1:4,27:8,146,27,234,27:13,-1:24,27:3,-1:6,27:5,-1:4,147,27:23,-1:24,27:3,-1:6,27:5,-1:4,27:16,148,226,27:6,-1:24,27:3,-1:6,27:5,-1:4,149,27:23,-1:24,27:3,-1:6,27:5,-1:4,27:15,150,27:8,-1:24,27:3,-1:6,27:5,-1:4,27:13,151,27:10,-1:24,27:3,-1:6,27:5,-1:4,27:10,152,27:13,-1:24,27:3,-1:6,27:5,-1:4,27:10,153,27:13,-1:24,27:3,-1:6,27:5,-1:4,27:15,154,27:8,-1:24,27:3,-1:6,27:5,-1:4,27:15,155,27:8,-1:24,27:3,-1:6,27:5,-1:4,27:4,156,27:19,-1:24,27:3,-1:6,27:5,-1:4,27:13,157,27:10,-1:24,27:3,-1:6,27:5,-1:4,27:8,158,27:15,-1:24,27:3,-1:6,27:5,-1:4,27:2,159,27:21,-1:24,27:3,-1:6,27:5,-1:4,27:4,160,27:19,-1:24,27:3,-1:6,27:5,-1:4,27:18,161,27:5,-1:24,27:3,-1:6,27:5,-1:4,27:4,162,27:19,-1:24,27:3,-1:6,27:5,-1:4,27:4,163,27:19,-1:24,27:3,-1:6,27:5,-1:4,27:10,164,27:13,-1:24,27:3,-1:6,27:5,-1:4,27:18,169,27:5,-1:24,27:3,-1:6,27:5,-1:4,27:7,170,27:5,200,27:10,-1:24,27:3,-1:6,27:5,-1:4,27:10,171,27,172,27:8,221,27:2,-1:24,27:3,-1:6,27:5,-1:4,27:13,173,27:10,-1:24,27:3,-1:6,27:5,-1:4,27:13,174,27:10,-1:24,27:3,-1:6,27:5,-1:4,27:4,176,27:19,-1:24,27:3,-1:6,27:5,-1:4,27:12,177,27:11,-1:24,27:3,-1:6,27:5,-1:4,27:13,178,27:10,-1:24,27:3,-1:6,27:5,-1:4,27:13,179,27:10,-1:24,27:3,-1:6,27:5,-1:4,27:8,180,27:7,233,27:7,-1:24,27:3,-1:6,27:5,-1:4,27:8,181,27:15,-1:24,27:3,-1:6,27:5,-1:4,27,182,27:22,-1:24,27:3,-1:6,27:5,-1:4,27:4,183,27:19,-1:24,27:3,-1:6,27:5,-1:4,27:18,184,27:5,-1:24,27:3,-1:6,27:5,-1:4,27:12,185,27:11,-1:24,27:3,-1:6,27:5,-1:4,27:4,186,27:19,-1:24,27:3,-1:6,27:5,-1:4,27:17,187,27:6,-1:24,27:3,-1:6,27:5,-1:4,27:18,188,27:5,-1:24,27:3,-1:6,27:5,-1:4,27:3,189,27:20,-1:24,27:3,-1:6,27:5,-1:4,27:12,190,27:11,-1:24,27:3,-1:6,27:5,-1:4,27:17,191,27:6,-1:24,27:3,-1:6,27:5,-1:4,27:12,192,27:11,-1:24,27:3,-1:6,27:5,-1:4,27:8,193,27:15,-1:24,27:3,-1:6,27:5,-1:4,27:15,199,27:8,-1:24,27:3,-1:6,27:5,-1:4,27:7,202,223,27:8,224,27:6,-1:24,27:3,-1:6,27:5,-1:4,27:12,203,27:11,-1:24,27:3,-1:6,27:5,-1:4,27:7,204,27:16,-1:24,27:3,-1:6,27:5,-1:4,27:17,206,27:6,-1:24,27:3,-1:6,27:5,-1:4,27:6,232,27:10,207,27:6,-1:24,27:3,-1:6,27:5,-1:4,27:6,208,27:16,209,-1:24,27:3,-1:6,27:5,-1:4,210,27:14,211,27:8,-1:24,27:3,-1:6,27:5,-1:4,27:4,212,27:19,-1:24,27:3,-1:6,27:5,-1:4,27:8,213,27:15,-1:24,27:3,-1:6,27:5,-1:4,27:16,214,27:7,-1:24,27:3,-1:6,27:5,-1:4,27:6,215,27:17,-1:24,27:3,-1:6,27:5,-1:4,27:17,216,27:6,-1:24,27:3,-1:6,27:5,-1:4,27:4,222,27:19,-1:24,27:3,-1:6,27:5,-1:4,27:14,225,27:9,-1:24,27:3,-1:6,27:5,-1:4,27:8,227,27:15,-1:24,27:3,-1:6,27:5,-1:4,27:8,228,27:15,-1:24,27:3,-1:6,27:5,-1:4,229,27:23,-1:24,27:3,-1:6,27:5,-1:4,27:22,231,27,-1:4");
	public java_cup.runtime.Symbol nextToken ()
		throws java.io.IOException {
		char yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			if (YYEOF != yy_lookahead) {
				yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YYEOF == yy_lookahead && true == yy_initial) {

	{
	 if (yy_lexical_state == COMMENT) {
	   err("Unterminated comment at end of file");
	 }
	 else if (yy_lexical_state == STRING) {
	   err("Unterminated string at end of file");
	 }
	 return tok(sym.EOF, null);
        }
				}
				else if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_to_mark();
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_pushback();
					}
					if (0 != (YY_START & yy_anchor)) {
						yy_move_start();
					}
					switch (yy_last_accept_state) {
					case 1:
						{}
					case -2:
						break;
					case 2:
						{newline();}
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{}
					case -5:
						break;
					case 5:
						{}
					case -6:
						break;
					case 6:
						{}
					case -7:
						break;
					case 7:
						{ return tok(sym.BANG); }
					case -8:
						break;
					case 8:
						{ stringBuffer = new StringBuffer(); yybegin(STRING); }
					case -9:
						break;
					case 9:
						{ return tok(sym.MOD); }
					case -10:
						break;
					case 10:
						{ return tok(sym.AMP); }
					case -11:
						break;
					case 11:
						{ return tok(sym.LPAREN); }
					case -12:
						break;
					case 12:
						{ return tok(sym.RPAREN); }
					case -13:
						break;
					case 13:
						{ return tok(sym.TIMES); }
					case -14:
						break;
					case 14:
						{ return tok(sym.PLUS); }
					case -15:
						break;
					case 15:
						{return tok(sym.COMMA, null);}
					case -16:
						break;
					case 16:
						{ return tok(sym.MINUS); }
					case -17:
						break;
					case 17:
						{ return tok(sym.DOT); }
					case -18:
						break;
					case 18:
						{ return tok(sym.DIVIDE); }
					case -19:
						break;
					case 19:
						{ return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
					case -20:
						break;
					case 20:
						{ return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
					case -21:
						break;
					case 21:
						{ return tok(sym.COLON); }
					case -22:
						break;
					case 22:
						{ return tok(sym.SEMICOLON); }
					case -23:
						break;
					case 23:
						{ return tok(sym.LT); }
					case -24:
						break;
					case 24:
						{ return tok(sym.ASSIGN); }
					case -25:
						break;
					case 25:
						{ return tok(sym.GT); }
					case -26:
						break;
					case 26:
						{ return tok(sym.QUESTION); }
					case -27:
						break;
					case 27:
						{ return tok(sym.ID, yytext()); }
					case -28:
						break;
					case 28:
						{ return tok(sym.LBRACK); }
					case -29:
						break;
					case 29:
						{ return tok(sym.RBRACK); }
					case -30:
						break;
					case 30:
						{ return tok(sym.CARET); }
					case -31:
						break;
					case 31:
						{ return tok(sym.LBRACE); }
					case -32:
						break;
					case 32:
						{ return tok(sym.PIPE); }
					case -33:
						break;
					case 33:
						{ return tok(sym.RBRACE); }
					case -34:
						break;
					case 34:
						{ return tok(sym.TILDE); }
					case -35:
						break;
					case 35:
						{ return tok(sym.NEQ); }
					case -36:
						break;
					case 36:
						{ return tok(sym.MOD_ASSIGN); }
					case -37:
						break;
					case 37:
						{ return tok(sym.ANDAND); }
					case -38:
						break;
					case 38:
						{ return tok(sym.AND_ASSIGN); }
					case -39:
						break;
					case 39:
						{ return tok(sym.MUL_ASSIGN); }
					case -40:
						break;
					case 40:
						{ return tok(sym.INCR); }
					case -41:
						break;
					case 41:
						{ return tok(sym.ADD_ASSIGN); }
					case -42:
						break;
					case 42:
						{ return tok(sym.DECR); }
					case -43:
						break;
					case 43:
						{ return tok(sym.SUB_ASSIGN); }
					case -44:
						break;
					case 44:
						{ return tok(sym.ARROW); }
					case -45:
						break;
					case 45:
						{ yybegin(COMMENT); }
					case -46:
						break;
					case 46:
						{ return tok(sym.DIV_ASSIGN); }
					case -47:
						break;
					case 47:
						{ return tok(sym.LSHIFT); }
					case -48:
						break;
					case 48:
						{ return tok(sym.LE); }
					case -49:
						break;
					case 49:
						{ return tok(sym.EQ); }
					case -50:
						break;
					case 50:
						{ return tok(sym.GE); }
					case -51:
						break;
					case 51:
						{ return tok(sym.RSHIFT); }
					case -52:
						break;
					case 52:
						{ return tok(sym.XOR_ASSIGN); }
					case -53:
						break;
					case 53:
						{ return tok(sym.DO); }
					case -54:
						break;
					case 54:
						{ return tok(sym.IF); }
					case -55:
						break;
					case 55:
						{ return tok(sym.OR_ASSIGN); }
					case -56:
						break;
					case 56:
						{ return tok(sym.OROR); }
					case -57:
						break;
					case 57:
						{ return tok(sym.CHAR_LITERAL, yytext().substring(1, 2)); }
					case -58:
						break;
					case 58:
						{ return tok(sym.ELIPSES); }
					case -59:
						break;
					case 59:
						{ return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
					case -60:
						break;
					case 60:
						{ return tok(sym.LSHIFT_ASSIGN); }
					case -61:
						break;
					case 61:
						{ return tok(sym.RSHIFT_ASSIGN); }
					case -62:
						break;
					case 62:
						{ return tok(sym.FOR); }
					case -63:
						break;
					case 63:
						{ return tok(sym.FUN); }
					case -64:
						break;
					case 64:
						{ return tok(sym.INT_KEYWORD); }
					case -65:
						break;
					case 65:
						{ return tok(sym.VAR); }
					case -66:
						break;
					case 66:
						{ err("Illegal escape sequence in character literal: " + yytext()); }
					case -67:
						break;
					case 67:
						{ return tok(sym.CHAR_LITERAL, decodeSimpleEscape(yytext())); }
					case -68:
						break;
					case 68:
						{ return tok(sym.CHAR_LITERAL, decodeOctalEscape(yytext())); }
					case -69:
						break;
					case 69:
						{ return tok(sym.AUTO); }
					case -70:
						break;
					case 70:
						{ return tok(sym.CHAR); }
					case -71:
						break;
					case 71:
						{ return tok(sym.ELSE); }
					case -72:
						break;
					case 72:
						{ return tok(sym.ENUM); }
					case -73:
						break;
					case 73:
						{ return tok(sym.GOTO); }
					case -74:
						break;
					case 74:
						{ return tok(sym.LONG); }
					case -75:
						break;
					case 75:
						{ return tok(sym.VOID); }
					case -76:
						break;
					case 76:
						{ return tok(sym.CHAR_LITERAL, decodeHexEscape(yytext())); }
					case -77:
						break;
					case 77:
						{ return tok(sym.BREAK); }
					case -78:
						break;
					case 78:
						{ return tok(sym.CONST); }
					case -79:
						break;
					case 79:
						{ return tok(sym.FLOAT); }
					case -80:
						break;
					case 80:
						{ return tok(sym.SHORT); }
					case -81:
						break;
					case 81:
						{ return tok(sym.UNION); }
					case -82:
						break;
					case 82:
						{ return tok(sym.WHILE); }
					case -83:
						break;
					case 83:
						{ return tok(sym.DOUBLE); }
					case -84:
						break;
					case 84:
						{ return tok(sym.EXTERN); }
					case -85:
						break;
					case 85:
						{ return tok(sym.RETURN); }
					case -86:
						break;
					case 86:
						{ return tok(sym.SIGNED); }
					case -87:
						break;
					case 87:
						{ return tok(sym.SIZEOF); }
					case -88:
						break;
					case 88:
						{ return tok(sym.STATIC); }
					case -89:
						break;
					case 89:
						{ return tok(sym.STRUCT); }
					case -90:
						break;
					case 90:
						{ return tok(sym.TYPEDEF); }
					case -91:
						break;
					case 91:
						{ return tok(sym.CONTINUE); }
					case -92:
						break;
					case 92:
						{ return tok(sym.REGISTER); }
					case -93:
						break;
					case 93:
						{ return tok(sym.UNSIGNED); }
					case -94:
						break;
					case 94:
						{ return tok(sym.VOLATILE); }
					case -95:
						break;
					case 95:
						{ /* just consume, do nothing */ }
					case -96:
						break;
					case 96:
						{ newline(); }
					case -97:
						break;
					case 97:
						{ yybegin(YYINITIAL); }
					case -98:
						break;
					case 98:
						{ stringBuffer.append(yytext()); }
					case -99:
						break;
					case 99:
						{ err("Newline in string literal"); newline(); yybegin(YYINITIAL); }
					case -100:
						break;
					case 100:
						{ yybegin(YYINITIAL); return tok(sym.STRING_LITERAL, stringBuffer.toString()); }
					case -101:
						break;
					case 101:
						{ err("Illegal escape sequence: " + yytext()); stringBuffer.append(yytext()); }
					case -102:
						break;
					case 102:
						{
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
					case -103:
						break;
					case 103:
						{
    String digits = yytext().substring(1);
    int value = Integer.parseInt(digits, 8);
    stringBuffer.append((char) value);
}
					case -104:
						break;
					case 104:
						{
    String digits = yytext().substring(2);
    int value = Integer.parseInt(digits, 16);
    stringBuffer.append((char) value);
}
					case -105:
						break;
					case 106:
						{ return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
					case -106:
						break;
					case 107:
						{ return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
					case -107:
						break;
					case 108:
						{ return tok(sym.ID, yytext()); }
					case -108:
						break;
					case 109:
						{ return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
					case -109:
						break;
					case 110:
						{ /* just consume, do nothing */ }
					case -110:
						break;
					case 111:
						{ stringBuffer.append(yytext()); }
					case -111:
						break;
					case 112:
						{ err("Illegal escape sequence: " + yytext()); stringBuffer.append(yytext()); }
					case -112:
						break;
					case 113:
						{
    String digits = yytext().substring(1);
    int value = Integer.parseInt(digits, 8);
    stringBuffer.append((char) value);
}
					case -113:
						break;
					case 115:
						{ return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
					case -114:
						break;
					case 116:
						{ return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
					case -115:
						break;
					case 117:
						{ return tok(sym.ID, yytext()); }
					case -116:
						break;
					case 118:
						{ return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
					case -117:
						break;
					case 119:
						{ /* just consume, do nothing */ }
					case -118:
						break;
					case 120:
						{ stringBuffer.append(yytext()); }
					case -119:
						break;
					case 122:
						{ return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
					case -120:
						break;
					case 123:
						{ return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
					case -121:
						break;
					case 124:
						{ return tok(sym.ID, yytext()); }
					case -122:
						break;
					case 125:
						{ return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
					case -123:
						break;
					case 127:
						{ return tok(sym.DECIMAL_LITERAL, convertInteger(yytext())); }
					case -124:
						break;
					case 128:
						{ return tok(sym.ID, yytext()); }
					case -125:
						break;
					case 130:
						{ return tok(sym.ID, yytext()); }
					case -126:
						break;
					case 132:
						{ return tok(sym.ID, yytext()); }
					case -127:
						break;
					case 134:
						{ return tok(sym.ID, yytext()); }
					case -128:
						break;
					case 136:
						{ return tok(sym.ID, yytext()); }
					case -129:
						break;
					case 138:
						{ return tok(sym.ID, yytext()); }
					case -130:
						break;
					case 140:
						{ return tok(sym.ID, yytext()); }
					case -131:
						break;
					case 142:
						{ return tok(sym.ID, yytext()); }
					case -132:
						break;
					case 144:
						{ return tok(sym.ID, yytext()); }
					case -133:
						break;
					case 146:
						{ return tok(sym.ID, yytext()); }
					case -134:
						break;
					case 147:
						{ return tok(sym.ID, yytext()); }
					case -135:
						break;
					case 148:
						{ return tok(sym.ID, yytext()); }
					case -136:
						break;
					case 149:
						{ return tok(sym.ID, yytext()); }
					case -137:
						break;
					case 150:
						{ return tok(sym.ID, yytext()); }
					case -138:
						break;
					case 151:
						{ return tok(sym.ID, yytext()); }
					case -139:
						break;
					case 152:
						{ return tok(sym.ID, yytext()); }
					case -140:
						break;
					case 153:
						{ return tok(sym.ID, yytext()); }
					case -141:
						break;
					case 154:
						{ return tok(sym.ID, yytext()); }
					case -142:
						break;
					case 155:
						{ return tok(sym.ID, yytext()); }
					case -143:
						break;
					case 156:
						{ return tok(sym.ID, yytext()); }
					case -144:
						break;
					case 157:
						{ return tok(sym.ID, yytext()); }
					case -145:
						break;
					case 158:
						{ return tok(sym.ID, yytext()); }
					case -146:
						break;
					case 159:
						{ return tok(sym.ID, yytext()); }
					case -147:
						break;
					case 160:
						{ return tok(sym.ID, yytext()); }
					case -148:
						break;
					case 161:
						{ return tok(sym.ID, yytext()); }
					case -149:
						break;
					case 162:
						{ return tok(sym.ID, yytext()); }
					case -150:
						break;
					case 163:
						{ return tok(sym.ID, yytext()); }
					case -151:
						break;
					case 164:
						{ return tok(sym.ID, yytext()); }
					case -152:
						break;
					case 165:
						{ return tok(sym.ID, yytext()); }
					case -153:
						break;
					case 166:
						{
    String digits = yytext().substring(1);
    int value = Integer.parseInt(digits, 8);
    stringBuffer.append((char) value);
}
					case -154:
						break;
					case 168:
						{ return tok(sym.ID, yytext()); }
					case -155:
						break;
					case 169:
						{ return tok(sym.ID, yytext()); }
					case -156:
						break;
					case 170:
						{ return tok(sym.ID, yytext()); }
					case -157:
						break;
					case 171:
						{ return tok(sym.ID, yytext()); }
					case -158:
						break;
					case 172:
						{ return tok(sym.ID, yytext()); }
					case -159:
						break;
					case 173:
						{ return tok(sym.ID, yytext()); }
					case -160:
						break;
					case 174:
						{ return tok(sym.ID, yytext()); }
					case -161:
						break;
					case 175:
						{ return tok(sym.ID, yytext()); }
					case -162:
						break;
					case 176:
						{ return tok(sym.ID, yytext()); }
					case -163:
						break;
					case 177:
						{ return tok(sym.ID, yytext()); }
					case -164:
						break;
					case 178:
						{ return tok(sym.ID, yytext()); }
					case -165:
						break;
					case 179:
						{ return tok(sym.ID, yytext()); }
					case -166:
						break;
					case 180:
						{ return tok(sym.ID, yytext()); }
					case -167:
						break;
					case 181:
						{ return tok(sym.ID, yytext()); }
					case -168:
						break;
					case 182:
						{ return tok(sym.ID, yytext()); }
					case -169:
						break;
					case 183:
						{ return tok(sym.ID, yytext()); }
					case -170:
						break;
					case 184:
						{ return tok(sym.ID, yytext()); }
					case -171:
						break;
					case 185:
						{ return tok(sym.ID, yytext()); }
					case -172:
						break;
					case 186:
						{ return tok(sym.ID, yytext()); }
					case -173:
						break;
					case 187:
						{ return tok(sym.ID, yytext()); }
					case -174:
						break;
					case 188:
						{ return tok(sym.ID, yytext()); }
					case -175:
						break;
					case 189:
						{ return tok(sym.ID, yytext()); }
					case -176:
						break;
					case 190:
						{ return tok(sym.ID, yytext()); }
					case -177:
						break;
					case 191:
						{ return tok(sym.ID, yytext()); }
					case -178:
						break;
					case 192:
						{ return tok(sym.ID, yytext()); }
					case -179:
						break;
					case 193:
						{ return tok(sym.ID, yytext()); }
					case -180:
						break;
					case 194:
						{ return tok(sym.ID, yytext()); }
					case -181:
						break;
					case 195:
						{ return tok(sym.ID, yytext()); }
					case -182:
						break;
					case 196:
						{ return tok(sym.ID, yytext()); }
					case -183:
						break;
					case 197:
						{ return tok(sym.ID, yytext()); }
					case -184:
						break;
					case 198:
						{ return tok(sym.ID, yytext()); }
					case -185:
						break;
					case 199:
						{ return tok(sym.ID, yytext()); }
					case -186:
						break;
					case 200:
						{ return tok(sym.ID, yytext()); }
					case -187:
						break;
					case 201:
						{ return tok(sym.ID, yytext()); }
					case -188:
						break;
					case 202:
						{ return tok(sym.ID, yytext()); }
					case -189:
						break;
					case 203:
						{ return tok(sym.ID, yytext()); }
					case -190:
						break;
					case 204:
						{ return tok(sym.ID, yytext()); }
					case -191:
						break;
					case 205:
						{ return tok(sym.ID, yytext()); }
					case -192:
						break;
					case 206:
						{ return tok(sym.ID, yytext()); }
					case -193:
						break;
					case 207:
						{ return tok(sym.ID, yytext()); }
					case -194:
						break;
					case 208:
						{ return tok(sym.ID, yytext()); }
					case -195:
						break;
					case 209:
						{ return tok(sym.ID, yytext()); }
					case -196:
						break;
					case 210:
						{ return tok(sym.ID, yytext()); }
					case -197:
						break;
					case 211:
						{ return tok(sym.ID, yytext()); }
					case -198:
						break;
					case 212:
						{ return tok(sym.ID, yytext()); }
					case -199:
						break;
					case 213:
						{ return tok(sym.ID, yytext()); }
					case -200:
						break;
					case 214:
						{ return tok(sym.ID, yytext()); }
					case -201:
						break;
					case 215:
						{ return tok(sym.ID, yytext()); }
					case -202:
						break;
					case 216:
						{ return tok(sym.ID, yytext()); }
					case -203:
						break;
					case 217:
						{ return tok(sym.ID, yytext()); }
					case -204:
						break;
					case 218:
						{ return tok(sym.ID, yytext()); }
					case -205:
						break;
					case 219:
						{ return tok(sym.ID, yytext()); }
					case -206:
						break;
					case 220:
						{ return tok(sym.ID, yytext()); }
					case -207:
						break;
					case 221:
						{ return tok(sym.ID, yytext()); }
					case -208:
						break;
					case 222:
						{ return tok(sym.ID, yytext()); }
					case -209:
						break;
					case 223:
						{ return tok(sym.ID, yytext()); }
					case -210:
						break;
					case 224:
						{ return tok(sym.ID, yytext()); }
					case -211:
						break;
					case 225:
						{ return tok(sym.ID, yytext()); }
					case -212:
						break;
					case 226:
						{ return tok(sym.ID, yytext()); }
					case -213:
						break;
					case 227:
						{ return tok(sym.ID, yytext()); }
					case -214:
						break;
					case 228:
						{ return tok(sym.ID, yytext()); }
					case -215:
						break;
					case 229:
						{ return tok(sym.ID, yytext()); }
					case -216:
						break;
					case 230:
						{ return tok(sym.ID, yytext()); }
					case -217:
						break;
					case 231:
						{ return tok(sym.ID, yytext()); }
					case -218:
						break;
					case 232:
						{ return tok(sym.ID, yytext()); }
					case -219:
						break;
					case 233:
						{ return tok(sym.ID, yytext()); }
					case -220:
						break;
					case 234:
						{ return tok(sym.ID, yytext()); }
					case -221:
						break;
					case 235:
						{ return tok(sym.ID, yytext()); }
					case -222:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
					}
				}
			}
		}
	}
}
