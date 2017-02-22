package org.eclipse.triquetrum.commands.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTqclLexer extends Lexer {
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_PARAMETER_NAME=4;
    public static final int RULE_ID=6;
    public static final int RULE_WS=11;
    public static final int RULE_ANY_OTHER=12;
    public static final int RULE_ENTITY_CLASS=7;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=8;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators

    public InternalTqclLexer() {;} 
    public InternalTqclLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalTqclLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalTqcl.g"; }

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:11:7: ( 'actor' )
            // InternalTqcl.g:11:9: 'actor'
            {
            match("actor"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:12:7: ( 'parameter' )
            // InternalTqcl.g:12:9: 'parameter'
            {
            match("parameter"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:13:7: ( 'port' )
            // InternalTqcl.g:13:9: 'port'
            {
            match("port"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:14:7: ( 'director' )
            // InternalTqcl.g:14:9: 'director'
            {
            match("director"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:15:7: ( ';' )
            // InternalTqcl.g:15:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:16:7: ( 'include' )
            // InternalTqcl.g:16:9: 'include'
            {
            match("include"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:17:7: ( 'library' )
            // InternalTqcl.g:17:9: 'library'
            {
            match("library"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:18:7: ( 'insert' )
            // InternalTqcl.g:18:9: 'insert'
            {
            match("insert"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:19:7: ( 'as' )
            // InternalTqcl.g:19:9: 'as'
            {
            match("as"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:20:7: ( 'with' )
            // InternalTqcl.g:20:9: 'with'
            {
            match("with"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:21:7: ( ',' )
            // InternalTqcl.g:21:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:22:7: ( 'set' )
            // InternalTqcl.g:22:9: 'set'
            {
            match("set"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:23:7: ( 'connect' )
            // InternalTqcl.g:23:9: 'connect'
            {
            match("connect"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:24:7: ( 'to' )
            // InternalTqcl.g:24:9: 'to'
            {
            match("to"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:25:7: ( '.' )
            // InternalTqcl.g:25:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:26:7: ( 'go' )
            // InternalTqcl.g:26:9: 'go'
            {
            match("go"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:27:7: ( '=' )
            // InternalTqcl.g:27:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:28:7: ( 'into' )
            // InternalTqcl.g:28:9: 'into'
            {
            match("into"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:29:7: ( 'out' )
            // InternalTqcl.g:29:9: 'out'
            {
            match("out"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:30:7: ( 'top' )
            // InternalTqcl.g:30:9: 'top'
            {
            match("top"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "RULE_PARAMETER_NAME"
    public final void mRULE_PARAMETER_NAME() throws RecognitionException {
        try {
            int _type = RULE_PARAMETER_NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:2345:21: ( '$' ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalTqcl.g:2345:23: '$' ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            match('$'); 
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalTqcl.g:2345:51: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalTqcl.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_PARAMETER_NAME"

    // $ANTLR start "RULE_ENTITY_CLASS"
    public final void mRULE_ENTITY_CLASS() throws RecognitionException {
        try {
            int _type = RULE_ENTITY_CLASS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:2347:19: ( '<' ( options {greedy=false; } : . )* '>' )
            // InternalTqcl.g:2347:21: '<' ( options {greedy=false; } : . )* '>'
            {
            match('<'); 
            // InternalTqcl.g:2347:25: ( options {greedy=false; } : . )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='>') ) {
                    alt2=2;
                }
                else if ( ((LA2_0>='\u0000' && LA2_0<='=')||(LA2_0>='?' && LA2_0<='\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalTqcl.g:2347:53: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ENTITY_CLASS"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:2349:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalTqcl.g:2349:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // InternalTqcl.g:2349:11: ( '^' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='^') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalTqcl.g:2349:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalTqcl.g:2349:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalTqcl.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:2351:10: ( ( '0' .. '9' )+ )
            // InternalTqcl.g:2351:12: ( '0' .. '9' )+
            {
            // InternalTqcl.g:2351:12: ( '0' .. '9' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalTqcl.g:2351:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:2353:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // InternalTqcl.g:2353:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // InternalTqcl.g:2353:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='\"') ) {
                alt8=1;
            }
            else if ( (LA8_0=='\'') ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalTqcl.g:2353:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalTqcl.g:2353:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop6:
                    do {
                        int alt6=3;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0=='\\') ) {
                            alt6=1;
                        }
                        else if ( ((LA6_0>='\u0000' && LA6_0<='!')||(LA6_0>='#' && LA6_0<='[')||(LA6_0>=']' && LA6_0<='\uFFFF')) ) {
                            alt6=2;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // InternalTqcl.g:2353:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalTqcl.g:2353:28: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalTqcl.g:2353:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // InternalTqcl.g:2353:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop7:
                    do {
                        int alt7=3;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0=='\\') ) {
                            alt7=1;
                        }
                        else if ( ((LA7_0>='\u0000' && LA7_0<='&')||(LA7_0>='(' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFF')) ) {
                            alt7=2;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalTqcl.g:2353:54: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalTqcl.g:2353:61: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:2355:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalTqcl.g:2355:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalTqcl.g:2355:24: ( options {greedy=false; } : . )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='*') ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1=='/') ) {
                        alt9=2;
                    }
                    else if ( ((LA9_1>='\u0000' && LA9_1<='.')||(LA9_1>='0' && LA9_1<='\uFFFF')) ) {
                        alt9=1;
                    }


                }
                else if ( ((LA9_0>='\u0000' && LA9_0<=')')||(LA9_0>='+' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalTqcl.g:2355:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:2357:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalTqcl.g:2357:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalTqcl.g:2357:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\uFFFF')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalTqcl.g:2357:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // InternalTqcl.g:2357:40: ( ( '\\r' )? '\\n' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='\n'||LA12_0=='\r') ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalTqcl.g:2357:41: ( '\\r' )? '\\n'
                    {
                    // InternalTqcl.g:2357:41: ( '\\r' )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='\r') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // InternalTqcl.g:2357:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:2359:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalTqcl.g:2359:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalTqcl.g:2359:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='\t' && LA13_0<='\n')||LA13_0=='\r'||LA13_0==' ') ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalTqcl.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTqcl.g:2361:16: ( . )
            // InternalTqcl.g:2361:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // InternalTqcl.g:1:8: ( T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | RULE_PARAMETER_NAME | RULE_ENTITY_CLASS | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt14=29;
        alt14 = dfa14.predict(input);
        switch (alt14) {
            case 1 :
                // InternalTqcl.g:1:10: T__13
                {
                mT__13(); 

                }
                break;
            case 2 :
                // InternalTqcl.g:1:16: T__14
                {
                mT__14(); 

                }
                break;
            case 3 :
                // InternalTqcl.g:1:22: T__15
                {
                mT__15(); 

                }
                break;
            case 4 :
                // InternalTqcl.g:1:28: T__16
                {
                mT__16(); 

                }
                break;
            case 5 :
                // InternalTqcl.g:1:34: T__17
                {
                mT__17(); 

                }
                break;
            case 6 :
                // InternalTqcl.g:1:40: T__18
                {
                mT__18(); 

                }
                break;
            case 7 :
                // InternalTqcl.g:1:46: T__19
                {
                mT__19(); 

                }
                break;
            case 8 :
                // InternalTqcl.g:1:52: T__20
                {
                mT__20(); 

                }
                break;
            case 9 :
                // InternalTqcl.g:1:58: T__21
                {
                mT__21(); 

                }
                break;
            case 10 :
                // InternalTqcl.g:1:64: T__22
                {
                mT__22(); 

                }
                break;
            case 11 :
                // InternalTqcl.g:1:70: T__23
                {
                mT__23(); 

                }
                break;
            case 12 :
                // InternalTqcl.g:1:76: T__24
                {
                mT__24(); 

                }
                break;
            case 13 :
                // InternalTqcl.g:1:82: T__25
                {
                mT__25(); 

                }
                break;
            case 14 :
                // InternalTqcl.g:1:88: T__26
                {
                mT__26(); 

                }
                break;
            case 15 :
                // InternalTqcl.g:1:94: T__27
                {
                mT__27(); 

                }
                break;
            case 16 :
                // InternalTqcl.g:1:100: T__28
                {
                mT__28(); 

                }
                break;
            case 17 :
                // InternalTqcl.g:1:106: T__29
                {
                mT__29(); 

                }
                break;
            case 18 :
                // InternalTqcl.g:1:112: T__30
                {
                mT__30(); 

                }
                break;
            case 19 :
                // InternalTqcl.g:1:118: T__31
                {
                mT__31(); 

                }
                break;
            case 20 :
                // InternalTqcl.g:1:124: T__32
                {
                mT__32(); 

                }
                break;
            case 21 :
                // InternalTqcl.g:1:130: RULE_PARAMETER_NAME
                {
                mRULE_PARAMETER_NAME(); 

                }
                break;
            case 22 :
                // InternalTqcl.g:1:150: RULE_ENTITY_CLASS
                {
                mRULE_ENTITY_CLASS(); 

                }
                break;
            case 23 :
                // InternalTqcl.g:1:168: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 24 :
                // InternalTqcl.g:1:176: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 25 :
                // InternalTqcl.g:1:185: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 26 :
                // InternalTqcl.g:1:197: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 27 :
                // InternalTqcl.g:1:213: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 28 :
                // InternalTqcl.g:1:229: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 29 :
                // InternalTqcl.g:1:237: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA14 dfa14 = new DFA14(this);
    static final String DFA14_eotS =
        "\1\uffff\3\34\1\uffff\3\34\1\uffff\3\34\1\uffff\1\34\1\uffff\1\34\3\31\2\uffff\3\31\2\uffff\1\34\1\64\1\uffff\3\34\1\uffff\3\34\1\uffff\2\34\1\100\1\uffff\1\101\1\uffff\1\34\7\uffff\1\34\1\uffff\10\34\1\114\1\34\1\116\2\uffff\1\117\2\34\1\122\3\34\1\126\1\34\1\130\1\uffff\1\34\2\uffff\1\132\1\34\1\uffff\3\34\1\uffff\1\34\1\uffff\1\34\1\uffff\3\34\1\144\4\34\1\151\1\uffff\1\152\1\153\1\34\1\155\3\uffff\1\156\2\uffff";
    static final String DFA14_eofS =
        "\157\uffff";
    static final String DFA14_minS =
        "\1\0\1\143\1\141\1\151\1\uffff\1\156\2\151\1\uffff\1\145\2\157\1\uffff\1\157\1\uffff\1\165\1\101\1\0\1\101\2\uffff\2\0\1\52\2\uffff\1\164\1\60\1\uffff\3\162\1\uffff\1\143\1\142\1\164\1\uffff\1\164\1\156\1\60\1\uffff\1\60\1\uffff\1\164\7\uffff\1\157\1\uffff\1\141\1\164\1\145\1\154\1\145\1\157\1\162\1\150\1\60\1\156\1\60\2\uffff\1\60\1\162\1\155\1\60\1\143\1\165\1\162\1\60\1\141\1\60\1\uffff\1\145\2\uffff\1\60\1\145\1\uffff\1\164\1\144\1\164\1\uffff\1\162\1\uffff\1\143\1\uffff\1\164\1\157\1\145\1\60\1\171\1\164\1\145\1\162\1\60\1\uffff\2\60\1\162\1\60\3\uffff\1\60\2\uffff";
    static final String DFA14_maxS =
        "\1\uffff\1\163\1\157\1\151\1\uffff\1\156\2\151\1\uffff\1\145\2\157\1\uffff\1\157\1\uffff\1\165\1\172\1\uffff\1\172\2\uffff\2\uffff\1\57\2\uffff\1\164\1\172\1\uffff\3\162\1\uffff\1\164\1\142\1\164\1\uffff\1\164\1\156\1\172\1\uffff\1\172\1\uffff\1\164\7\uffff\1\157\1\uffff\1\141\1\164\1\145\1\154\1\145\1\157\1\162\1\150\1\172\1\156\1\172\2\uffff\1\172\1\162\1\155\1\172\1\143\1\165\1\162\1\172\1\141\1\172\1\uffff\1\145\2\uffff\1\172\1\145\1\uffff\1\164\1\144\1\164\1\uffff\1\162\1\uffff\1\143\1\uffff\1\164\1\157\1\145\1\172\1\171\1\164\1\145\1\162\1\172\1\uffff\2\172\1\162\1\172\3\uffff\1\172\2\uffff";
    static final String DFA14_acceptS =
        "\4\uffff\1\5\3\uffff\1\13\3\uffff\1\17\1\uffff\1\21\4\uffff\1\27\1\30\3\uffff\1\34\1\35\2\uffff\1\27\3\uffff\1\5\3\uffff\1\13\3\uffff\1\17\1\uffff\1\21\1\uffff\1\25\1\26\1\30\1\31\1\32\1\33\1\34\1\uffff\1\11\13\uffff\1\16\1\20\12\uffff\1\14\1\uffff\1\24\1\23\2\uffff\1\3\3\uffff\1\22\1\uffff\1\12\1\uffff\1\1\11\uffff\1\10\4\uffff\1\6\1\7\1\15\1\uffff\1\4\1\2";
    static final String DFA14_specialS =
        "\1\2\20\uffff\1\1\3\uffff\1\0\1\3\130\uffff}>";
    static final String[] DFA14_transitionS = {
            "\11\31\2\30\2\31\1\30\22\31\1\30\1\31\1\25\1\31\1\20\2\31\1\26\4\31\1\10\1\31\1\14\1\27\12\24\1\31\1\4\1\21\1\16\3\31\32\23\3\31\1\22\1\23\1\31\1\1\1\23\1\12\1\3\2\23\1\15\1\23\1\5\2\23\1\6\2\23\1\17\1\2\2\23\1\11\1\13\2\23\1\7\3\23\uff85\31",
            "\1\32\17\uffff\1\33",
            "\1\35\15\uffff\1\36",
            "\1\37",
            "",
            "\1\41",
            "\1\42",
            "\1\43",
            "",
            "\1\45",
            "\1\46",
            "\1\47",
            "",
            "\1\51",
            "",
            "\1\53",
            "\32\54\4\uffff\1\54\1\uffff\32\54",
            "\0\55",
            "\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "",
            "\0\57",
            "\0\57",
            "\1\60\4\uffff\1\61",
            "",
            "",
            "\1\63",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "\1\65",
            "\1\66",
            "\1\67",
            "",
            "\1\70\17\uffff\1\71\1\72",
            "\1\73",
            "\1\74",
            "",
            "\1\75",
            "\1\76",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\17\34\1\77\12\34",
            "",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "\1\102",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\103",
            "",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\115",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\120",
            "\1\121",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\123",
            "\1\124",
            "\1\125",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\127",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "\1\131",
            "",
            "",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\133",
            "",
            "\1\134",
            "\1\135",
            "\1\136",
            "",
            "\1\137",
            "",
            "\1\140",
            "",
            "\1\141",
            "\1\142",
            "\1\143",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\154",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "",
            "",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            ""
    };

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | RULE_PARAMETER_NAME | RULE_ENTITY_CLASS | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA14_21 = input.LA(1);

                        s = -1;
                        if ( ((LA14_21>='\u0000' && LA14_21<='\uFFFF')) ) {s = 47;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA14_17 = input.LA(1);

                        s = -1;
                        if ( ((LA14_17>='\u0000' && LA14_17<='\uFFFF')) ) {s = 45;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA14_0 = input.LA(1);

                        s = -1;
                        if ( (LA14_0=='a') ) {s = 1;}

                        else if ( (LA14_0=='p') ) {s = 2;}

                        else if ( (LA14_0=='d') ) {s = 3;}

                        else if ( (LA14_0==';') ) {s = 4;}

                        else if ( (LA14_0=='i') ) {s = 5;}

                        else if ( (LA14_0=='l') ) {s = 6;}

                        else if ( (LA14_0=='w') ) {s = 7;}

                        else if ( (LA14_0==',') ) {s = 8;}

                        else if ( (LA14_0=='s') ) {s = 9;}

                        else if ( (LA14_0=='c') ) {s = 10;}

                        else if ( (LA14_0=='t') ) {s = 11;}

                        else if ( (LA14_0=='.') ) {s = 12;}

                        else if ( (LA14_0=='g') ) {s = 13;}

                        else if ( (LA14_0=='=') ) {s = 14;}

                        else if ( (LA14_0=='o') ) {s = 15;}

                        else if ( (LA14_0=='$') ) {s = 16;}

                        else if ( (LA14_0=='<') ) {s = 17;}

                        else if ( (LA14_0=='^') ) {s = 18;}

                        else if ( ((LA14_0>='A' && LA14_0<='Z')||LA14_0=='_'||LA14_0=='b'||(LA14_0>='e' && LA14_0<='f')||LA14_0=='h'||(LA14_0>='j' && LA14_0<='k')||(LA14_0>='m' && LA14_0<='n')||(LA14_0>='q' && LA14_0<='r')||(LA14_0>='u' && LA14_0<='v')||(LA14_0>='x' && LA14_0<='z')) ) {s = 19;}

                        else if ( ((LA14_0>='0' && LA14_0<='9')) ) {s = 20;}

                        else if ( (LA14_0=='\"') ) {s = 21;}

                        else if ( (LA14_0=='\'') ) {s = 22;}

                        else if ( (LA14_0=='/') ) {s = 23;}

                        else if ( ((LA14_0>='\t' && LA14_0<='\n')||LA14_0=='\r'||LA14_0==' ') ) {s = 24;}

                        else if ( ((LA14_0>='\u0000' && LA14_0<='\b')||(LA14_0>='\u000B' && LA14_0<='\f')||(LA14_0>='\u000E' && LA14_0<='\u001F')||LA14_0=='!'||LA14_0=='#'||(LA14_0>='%' && LA14_0<='&')||(LA14_0>='(' && LA14_0<='+')||LA14_0=='-'||LA14_0==':'||(LA14_0>='>' && LA14_0<='@')||(LA14_0>='[' && LA14_0<=']')||LA14_0=='`'||(LA14_0>='{' && LA14_0<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA14_22 = input.LA(1);

                        s = -1;
                        if ( ((LA14_22>='\u0000' && LA14_22<='\uFFFF')) ) {s = 47;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 14, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}