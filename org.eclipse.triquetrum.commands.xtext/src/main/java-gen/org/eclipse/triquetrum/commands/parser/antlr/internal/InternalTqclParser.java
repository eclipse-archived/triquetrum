/*******************************************************************************
 * Copyright (c)  2017 Totaro Rodolfo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Rodolfo Totaro - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.triquetrum.commands.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.triquetrum.commands.services.TqclGrammarAccess;



import java.util.Stack;
import org.antlr.runtime.*;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTqclParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_ENTITY_CLASS", "RULE_PARAMETER_NAME", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "';'", "'include'", "'library'", "'insert'", "'as'", "'with'", "','", "'set'", "'connect'", "'to'", "'.'", "'go'", "'into'", "'out'", "'top'", "'='", "'actor'", "'parameter'", "'port'", "'director'"
    };
    public static final int RULE_STRING=4;
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
    public static final int RULE_ID=5;
    public static final int RULE_PARAMETER_NAME=7;
    public static final int RULE_WS=11;
    public static final int RULE_ANY_OTHER=12;
    public static final int RULE_ENTITY_CLASS=6;
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


        public InternalTqclParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalTqclParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalTqclParser.tokenNames; }
    public String getGrammarFileName() { return "InternalTqcl.g"; }



     	private TqclGrammarAccess grammarAccess;

        public InternalTqclParser(TokenStream input, TqclGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "TriquetrumScript";
       	}

       	@Override
       	protected TqclGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleTriquetrumScript"
    // InternalTqcl.g:74:1: entryRuleTriquetrumScript returns [EObject current=null] : iv_ruleTriquetrumScript= ruleTriquetrumScript EOF ;
    public final EObject entryRuleTriquetrumScript() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTriquetrumScript = null;


        try {
            // InternalTqcl.g:74:57: (iv_ruleTriquetrumScript= ruleTriquetrumScript EOF )
            // InternalTqcl.g:75:2: iv_ruleTriquetrumScript= ruleTriquetrumScript EOF
            {
             newCompositeNode(grammarAccess.getTriquetrumScriptRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTriquetrumScript=ruleTriquetrumScript();

            state._fsp--;

             current =iv_ruleTriquetrumScript; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTriquetrumScript"


    // $ANTLR start "ruleTriquetrumScript"
    // InternalTqcl.g:81:1: ruleTriquetrumScript returns [EObject current=null] : ( ( (lv_libraries_0_0= ruleLibrary ) )* ( (lv_commands_1_0= ruleCommand ) )* ) ;
    public final EObject ruleTriquetrumScript() throws RecognitionException {
        EObject current = null;

        EObject lv_libraries_0_0 = null;

        EObject lv_commands_1_0 = null;



        	enterRule();

        try {
            // InternalTqcl.g:87:2: ( ( ( (lv_libraries_0_0= ruleLibrary ) )* ( (lv_commands_1_0= ruleCommand ) )* ) )
            // InternalTqcl.g:88:2: ( ( (lv_libraries_0_0= ruleLibrary ) )* ( (lv_commands_1_0= ruleCommand ) )* )
            {
            // InternalTqcl.g:88:2: ( ( (lv_libraries_0_0= ruleLibrary ) )* ( (lv_commands_1_0= ruleCommand ) )* )
            // InternalTqcl.g:89:3: ( (lv_libraries_0_0= ruleLibrary ) )* ( (lv_commands_1_0= ruleCommand ) )*
            {
            // InternalTqcl.g:89:3: ( (lv_libraries_0_0= ruleLibrary ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==15) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalTqcl.g:90:4: (lv_libraries_0_0= ruleLibrary )
            	    {
            	    // InternalTqcl.g:90:4: (lv_libraries_0_0= ruleLibrary )
            	    // InternalTqcl.g:91:5: lv_libraries_0_0= ruleLibrary
            	    {

            	    					newCompositeNode(grammarAccess.getTriquetrumScriptAccess().getLibrariesLibraryParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_libraries_0_0=ruleLibrary();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getTriquetrumScriptRule());
            	    					}
            	    					add(
            	    						current,
            	    						"libraries",
            	    						lv_libraries_0_0,
            	    						"org.eclipse.triquetrum.commands.Tqcl.Library");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalTqcl.g:108:3: ( (lv_commands_1_0= ruleCommand ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==14||LA2_0==16||(LA2_0>=20 && LA2_0<=21)||LA2_0==24) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalTqcl.g:109:4: (lv_commands_1_0= ruleCommand )
            	    {
            	    // InternalTqcl.g:109:4: (lv_commands_1_0= ruleCommand )
            	    // InternalTqcl.g:110:5: lv_commands_1_0= ruleCommand
            	    {

            	    					newCompositeNode(grammarAccess.getTriquetrumScriptAccess().getCommandsCommandParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_4);
            	    lv_commands_1_0=ruleCommand();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getTriquetrumScriptRule());
            	    					}
            	    					add(
            	    						current,
            	    						"commands",
            	    						lv_commands_1_0,
            	    						"org.eclipse.triquetrum.commands.Tqcl.Command");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTriquetrumScript"


    // $ANTLR start "entryRuleCompositeCommand"
    // InternalTqcl.g:131:1: entryRuleCompositeCommand returns [EObject current=null] : iv_ruleCompositeCommand= ruleCompositeCommand EOF ;
    public final EObject entryRuleCompositeCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCompositeCommand = null;


        try {
            // InternalTqcl.g:131:57: (iv_ruleCompositeCommand= ruleCompositeCommand EOF )
            // InternalTqcl.g:132:2: iv_ruleCompositeCommand= ruleCompositeCommand EOF
            {
             newCompositeNode(grammarAccess.getCompositeCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCompositeCommand=ruleCompositeCommand();

            state._fsp--;

             current =iv_ruleCompositeCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCompositeCommand"


    // $ANTLR start "ruleCompositeCommand"
    // InternalTqcl.g:138:1: ruleCompositeCommand returns [EObject current=null] : ( ( (lv_start_0_0= ruleGoInto ) ) ( (lv_commands_1_0= ruleCommand ) )* ( ( (lv_end_2_1= ruleGoOut | lv_end_2_2= ruleGoTop ) ) ) ) ;
    public final EObject ruleCompositeCommand() throws RecognitionException {
        EObject current = null;

        EObject lv_start_0_0 = null;

        EObject lv_commands_1_0 = null;

        EObject lv_end_2_1 = null;

        EObject lv_end_2_2 = null;



        	enterRule();

        try {
            // InternalTqcl.g:144:2: ( ( ( (lv_start_0_0= ruleGoInto ) ) ( (lv_commands_1_0= ruleCommand ) )* ( ( (lv_end_2_1= ruleGoOut | lv_end_2_2= ruleGoTop ) ) ) ) )
            // InternalTqcl.g:145:2: ( ( (lv_start_0_0= ruleGoInto ) ) ( (lv_commands_1_0= ruleCommand ) )* ( ( (lv_end_2_1= ruleGoOut | lv_end_2_2= ruleGoTop ) ) ) )
            {
            // InternalTqcl.g:145:2: ( ( (lv_start_0_0= ruleGoInto ) ) ( (lv_commands_1_0= ruleCommand ) )* ( ( (lv_end_2_1= ruleGoOut | lv_end_2_2= ruleGoTop ) ) ) )
            // InternalTqcl.g:146:3: ( (lv_start_0_0= ruleGoInto ) ) ( (lv_commands_1_0= ruleCommand ) )* ( ( (lv_end_2_1= ruleGoOut | lv_end_2_2= ruleGoTop ) ) )
            {
            // InternalTqcl.g:146:3: ( (lv_start_0_0= ruleGoInto ) )
            // InternalTqcl.g:147:4: (lv_start_0_0= ruleGoInto )
            {
            // InternalTqcl.g:147:4: (lv_start_0_0= ruleGoInto )
            // InternalTqcl.g:148:5: lv_start_0_0= ruleGoInto
            {

            					newCompositeNode(grammarAccess.getCompositeCommandAccess().getStartGoIntoParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_5);
            lv_start_0_0=ruleGoInto();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCompositeCommandRule());
            					}
            					set(
            						current,
            						"start",
            						lv_start_0_0,
            						"org.eclipse.triquetrum.commands.Tqcl.GoInto");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalTqcl.g:165:3: ( (lv_commands_1_0= ruleCommand ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==24) ) {
                    int LA3_1 = input.LA(2);

                    if ( (LA3_1==25) ) {
                        alt3=1;
                    }


                }
                else if ( (LA3_0==14||LA3_0==16||(LA3_0>=20 && LA3_0<=21)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalTqcl.g:166:4: (lv_commands_1_0= ruleCommand )
            	    {
            	    // InternalTqcl.g:166:4: (lv_commands_1_0= ruleCommand )
            	    // InternalTqcl.g:167:5: lv_commands_1_0= ruleCommand
            	    {

            	    					newCompositeNode(grammarAccess.getCompositeCommandAccess().getCommandsCommandParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_5);
            	    lv_commands_1_0=ruleCommand();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getCompositeCommandRule());
            	    					}
            	    					add(
            	    						current,
            	    						"commands",
            	    						lv_commands_1_0,
            	    						"org.eclipse.triquetrum.commands.Tqcl.Command");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // InternalTqcl.g:184:3: ( ( (lv_end_2_1= ruleGoOut | lv_end_2_2= ruleGoTop ) ) )
            // InternalTqcl.g:185:4: ( (lv_end_2_1= ruleGoOut | lv_end_2_2= ruleGoTop ) )
            {
            // InternalTqcl.g:185:4: ( (lv_end_2_1= ruleGoOut | lv_end_2_2= ruleGoTop ) )
            // InternalTqcl.g:186:5: (lv_end_2_1= ruleGoOut | lv_end_2_2= ruleGoTop )
            {
            // InternalTqcl.g:186:5: (lv_end_2_1= ruleGoOut | lv_end_2_2= ruleGoTop )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==24) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==27) ) {
                    alt4=2;
                }
                else if ( (LA4_1==26) ) {
                    alt4=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalTqcl.g:187:6: lv_end_2_1= ruleGoOut
                    {

                    						newCompositeNode(grammarAccess.getCompositeCommandAccess().getEndGoOutParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_end_2_1=ruleGoOut();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getCompositeCommandRule());
                    						}
                    						set(
                    							current,
                    							"end",
                    							lv_end_2_1,
                    							"org.eclipse.triquetrum.commands.Tqcl.GoOut");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 2 :
                    // InternalTqcl.g:203:6: lv_end_2_2= ruleGoTop
                    {

                    						newCompositeNode(grammarAccess.getCompositeCommandAccess().getEndGoTopParserRuleCall_2_0_1());
                    					
                    pushFollow(FOLLOW_2);
                    lv_end_2_2=ruleGoTop();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getCompositeCommandRule());
                    						}
                    						set(
                    							current,
                    							"end",
                    							lv_end_2_2,
                    							"org.eclipse.triquetrum.commands.Tqcl.GoTop");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;

            }


            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCompositeCommand"


    // $ANTLR start "entryRuleSimpleCommand"
    // InternalTqcl.g:225:1: entryRuleSimpleCommand returns [EObject current=null] : iv_ruleSimpleCommand= ruleSimpleCommand EOF ;
    public final EObject entryRuleSimpleCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleCommand = null;


        try {
            // InternalTqcl.g:225:54: (iv_ruleSimpleCommand= ruleSimpleCommand EOF )
            // InternalTqcl.g:226:2: iv_ruleSimpleCommand= ruleSimpleCommand EOF
            {
             newCompositeNode(grammarAccess.getSimpleCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSimpleCommand=ruleSimpleCommand();

            state._fsp--;

             current =iv_ruleSimpleCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleCommand"


    // $ANTLR start "ruleSimpleCommand"
    // InternalTqcl.g:232:1: ruleSimpleCommand returns [EObject current=null] : ( (this_Insert_0= ruleInsert | this_Connect_1= ruleConnect | this_Set_2= ruleSet | this_Include_3= ruleInclude ) otherlv_4= ';' ) ;
    public final EObject ruleSimpleCommand() throws RecognitionException {
        EObject current = null;

        Token otherlv_4=null;
        EObject this_Insert_0 = null;

        EObject this_Connect_1 = null;

        EObject this_Set_2 = null;

        EObject this_Include_3 = null;



        	enterRule();

        try {
            // InternalTqcl.g:238:2: ( ( (this_Insert_0= ruleInsert | this_Connect_1= ruleConnect | this_Set_2= ruleSet | this_Include_3= ruleInclude ) otherlv_4= ';' ) )
            // InternalTqcl.g:239:2: ( (this_Insert_0= ruleInsert | this_Connect_1= ruleConnect | this_Set_2= ruleSet | this_Include_3= ruleInclude ) otherlv_4= ';' )
            {
            // InternalTqcl.g:239:2: ( (this_Insert_0= ruleInsert | this_Connect_1= ruleConnect | this_Set_2= ruleSet | this_Include_3= ruleInclude ) otherlv_4= ';' )
            // InternalTqcl.g:240:3: (this_Insert_0= ruleInsert | this_Connect_1= ruleConnect | this_Set_2= ruleSet | this_Include_3= ruleInclude ) otherlv_4= ';'
            {
            // InternalTqcl.g:240:3: (this_Insert_0= ruleInsert | this_Connect_1= ruleConnect | this_Set_2= ruleSet | this_Include_3= ruleInclude )
            int alt5=4;
            switch ( input.LA(1) ) {
            case 16:
                {
                alt5=1;
                }
                break;
            case 21:
                {
                alt5=2;
                }
                break;
            case 20:
                {
                alt5=3;
                }
                break;
            case 14:
                {
                alt5=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalTqcl.g:241:4: this_Insert_0= ruleInsert
                    {

                    				newCompositeNode(grammarAccess.getSimpleCommandAccess().getInsertParserRuleCall_0_0());
                    			
                    pushFollow(FOLLOW_6);
                    this_Insert_0=ruleInsert();

                    state._fsp--;


                    				current = this_Insert_0;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 2 :
                    // InternalTqcl.g:250:4: this_Connect_1= ruleConnect
                    {

                    				newCompositeNode(grammarAccess.getSimpleCommandAccess().getConnectParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_6);
                    this_Connect_1=ruleConnect();

                    state._fsp--;


                    				current = this_Connect_1;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 3 :
                    // InternalTqcl.g:259:4: this_Set_2= ruleSet
                    {

                    				newCompositeNode(grammarAccess.getSimpleCommandAccess().getSetParserRuleCall_0_2());
                    			
                    pushFollow(FOLLOW_6);
                    this_Set_2=ruleSet();

                    state._fsp--;


                    				current = this_Set_2;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 4 :
                    // InternalTqcl.g:268:4: this_Include_3= ruleInclude
                    {

                    				newCompositeNode(grammarAccess.getSimpleCommandAccess().getIncludeParserRuleCall_0_3());
                    			
                    pushFollow(FOLLOW_6);
                    this_Include_3=ruleInclude();

                    state._fsp--;


                    				current = this_Include_3;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            otherlv_4=(Token)match(input,13,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getSimpleCommandAccess().getSemicolonKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleCommand"


    // $ANTLR start "entryRuleCommand"
    // InternalTqcl.g:285:1: entryRuleCommand returns [EObject current=null] : iv_ruleCommand= ruleCommand EOF ;
    public final EObject entryRuleCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCommand = null;


        try {
            // InternalTqcl.g:285:48: (iv_ruleCommand= ruleCommand EOF )
            // InternalTqcl.g:286:2: iv_ruleCommand= ruleCommand EOF
            {
             newCompositeNode(grammarAccess.getCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCommand=ruleCommand();

            state._fsp--;

             current =iv_ruleCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCommand"


    // $ANTLR start "ruleCommand"
    // InternalTqcl.g:292:1: ruleCommand returns [EObject current=null] : (this_SimpleCommand_0= ruleSimpleCommand | this_CompositeCommand_1= ruleCompositeCommand ) ;
    public final EObject ruleCommand() throws RecognitionException {
        EObject current = null;

        EObject this_SimpleCommand_0 = null;

        EObject this_CompositeCommand_1 = null;



        	enterRule();

        try {
            // InternalTqcl.g:298:2: ( (this_SimpleCommand_0= ruleSimpleCommand | this_CompositeCommand_1= ruleCompositeCommand ) )
            // InternalTqcl.g:299:2: (this_SimpleCommand_0= ruleSimpleCommand | this_CompositeCommand_1= ruleCompositeCommand )
            {
            // InternalTqcl.g:299:2: (this_SimpleCommand_0= ruleSimpleCommand | this_CompositeCommand_1= ruleCompositeCommand )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==14||LA6_0==16||(LA6_0>=20 && LA6_0<=21)) ) {
                alt6=1;
            }
            else if ( (LA6_0==24) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalTqcl.g:300:3: this_SimpleCommand_0= ruleSimpleCommand
                    {

                    			newCompositeNode(grammarAccess.getCommandAccess().getSimpleCommandParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_SimpleCommand_0=ruleSimpleCommand();

                    state._fsp--;


                    			current = this_SimpleCommand_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalTqcl.g:309:3: this_CompositeCommand_1= ruleCompositeCommand
                    {

                    			newCompositeNode(grammarAccess.getCommandAccess().getCompositeCommandParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_CompositeCommand_1=ruleCompositeCommand();

                    state._fsp--;


                    			current = this_CompositeCommand_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCommand"


    // $ANTLR start "entryRuleInclude"
    // InternalTqcl.g:321:1: entryRuleInclude returns [EObject current=null] : iv_ruleInclude= ruleInclude EOF ;
    public final EObject entryRuleInclude() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInclude = null;


        try {
            // InternalTqcl.g:321:48: (iv_ruleInclude= ruleInclude EOF )
            // InternalTqcl.g:322:2: iv_ruleInclude= ruleInclude EOF
            {
             newCompositeNode(grammarAccess.getIncludeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInclude=ruleInclude();

            state._fsp--;

             current =iv_ruleInclude; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInclude"


    // $ANTLR start "ruleInclude"
    // InternalTqcl.g:328:1: ruleInclude returns [EObject current=null] : (otherlv_0= 'include' ( (lv_filename_1_0= RULE_STRING ) ) otherlv_2= ';' ) ;
    public final EObject ruleInclude() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_filename_1_0=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalTqcl.g:334:2: ( (otherlv_0= 'include' ( (lv_filename_1_0= RULE_STRING ) ) otherlv_2= ';' ) )
            // InternalTqcl.g:335:2: (otherlv_0= 'include' ( (lv_filename_1_0= RULE_STRING ) ) otherlv_2= ';' )
            {
            // InternalTqcl.g:335:2: (otherlv_0= 'include' ( (lv_filename_1_0= RULE_STRING ) ) otherlv_2= ';' )
            // InternalTqcl.g:336:3: otherlv_0= 'include' ( (lv_filename_1_0= RULE_STRING ) ) otherlv_2= ';'
            {
            otherlv_0=(Token)match(input,14,FOLLOW_7); 

            			newLeafNode(otherlv_0, grammarAccess.getIncludeAccess().getIncludeKeyword_0());
            		
            // InternalTqcl.g:340:3: ( (lv_filename_1_0= RULE_STRING ) )
            // InternalTqcl.g:341:4: (lv_filename_1_0= RULE_STRING )
            {
            // InternalTqcl.g:341:4: (lv_filename_1_0= RULE_STRING )
            // InternalTqcl.g:342:5: lv_filename_1_0= RULE_STRING
            {
            lv_filename_1_0=(Token)match(input,RULE_STRING,FOLLOW_6); 

            					newLeafNode(lv_filename_1_0, grammarAccess.getIncludeAccess().getFilenameSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getIncludeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"filename",
            						lv_filename_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_2); 

            			newLeafNode(otherlv_2, grammarAccess.getIncludeAccess().getSemicolonKeyword_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInclude"


    // $ANTLR start "entryRuleLibrary"
    // InternalTqcl.g:366:1: entryRuleLibrary returns [EObject current=null] : iv_ruleLibrary= ruleLibrary EOF ;
    public final EObject entryRuleLibrary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLibrary = null;


        try {
            // InternalTqcl.g:366:48: (iv_ruleLibrary= ruleLibrary EOF )
            // InternalTqcl.g:367:2: iv_ruleLibrary= ruleLibrary EOF
            {
             newCompositeNode(grammarAccess.getLibraryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLibrary=ruleLibrary();

            state._fsp--;

             current =iv_ruleLibrary; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLibrary"


    // $ANTLR start "ruleLibrary"
    // InternalTqcl.g:373:1: ruleLibrary returns [EObject current=null] : (otherlv_0= 'library' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) ;
    public final EObject ruleLibrary() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalTqcl.g:379:2: ( (otherlv_0= 'library' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) )
            // InternalTqcl.g:380:2: (otherlv_0= 'library' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' )
            {
            // InternalTqcl.g:380:2: (otherlv_0= 'library' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' )
            // InternalTqcl.g:381:3: otherlv_0= 'library' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_8); 

            			newLeafNode(otherlv_0, grammarAccess.getLibraryAccess().getLibraryKeyword_0());
            		
            // InternalTqcl.g:385:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalTqcl.g:386:4: (lv_name_1_0= RULE_ID )
            {
            // InternalTqcl.g:386:4: (lv_name_1_0= RULE_ID )
            // InternalTqcl.g:387:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            					newLeafNode(lv_name_1_0, grammarAccess.getLibraryAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getLibraryRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_2); 

            			newLeafNode(otherlv_2, grammarAccess.getLibraryAccess().getSemicolonKeyword_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLibrary"


    // $ANTLR start "entryRuleInsert"
    // InternalTqcl.g:411:1: entryRuleInsert returns [EObject current=null] : iv_ruleInsert= ruleInsert EOF ;
    public final EObject entryRuleInsert() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInsert = null;


        try {
            // InternalTqcl.g:411:47: (iv_ruleInsert= ruleInsert EOF )
            // InternalTqcl.g:412:2: iv_ruleInsert= ruleInsert EOF
            {
             newCompositeNode(grammarAccess.getInsertRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInsert=ruleInsert();

            state._fsp--;

             current =iv_ruleInsert; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInsert"


    // $ANTLR start "ruleInsert"
    // InternalTqcl.g:418:1: ruleInsert returns [EObject current=null] : (otherlv_0= 'insert' ( (lv_category_1_0= ruleCategory ) )? ( (lv_entityClass_2_0= RULE_ENTITY_CLASS ) ) otherlv_3= 'as' ( (lv_name_4_0= RULE_STRING ) ) (otherlv_5= 'with' ( (lv_parameters_6_0= ruleParameter ) ) (otherlv_7= ',' ( (lv_parameters_8_0= ruleParameter ) ) )* )? ) ;
    public final EObject ruleInsert() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_entityClass_2_0=null;
        Token otherlv_3=null;
        Token lv_name_4_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Enumerator lv_category_1_0 = null;

        EObject lv_parameters_6_0 = null;

        EObject lv_parameters_8_0 = null;



        	enterRule();

        try {
            // InternalTqcl.g:424:2: ( (otherlv_0= 'insert' ( (lv_category_1_0= ruleCategory ) )? ( (lv_entityClass_2_0= RULE_ENTITY_CLASS ) ) otherlv_3= 'as' ( (lv_name_4_0= RULE_STRING ) ) (otherlv_5= 'with' ( (lv_parameters_6_0= ruleParameter ) ) (otherlv_7= ',' ( (lv_parameters_8_0= ruleParameter ) ) )* )? ) )
            // InternalTqcl.g:425:2: (otherlv_0= 'insert' ( (lv_category_1_0= ruleCategory ) )? ( (lv_entityClass_2_0= RULE_ENTITY_CLASS ) ) otherlv_3= 'as' ( (lv_name_4_0= RULE_STRING ) ) (otherlv_5= 'with' ( (lv_parameters_6_0= ruleParameter ) ) (otherlv_7= ',' ( (lv_parameters_8_0= ruleParameter ) ) )* )? )
            {
            // InternalTqcl.g:425:2: (otherlv_0= 'insert' ( (lv_category_1_0= ruleCategory ) )? ( (lv_entityClass_2_0= RULE_ENTITY_CLASS ) ) otherlv_3= 'as' ( (lv_name_4_0= RULE_STRING ) ) (otherlv_5= 'with' ( (lv_parameters_6_0= ruleParameter ) ) (otherlv_7= ',' ( (lv_parameters_8_0= ruleParameter ) ) )* )? )
            // InternalTqcl.g:426:3: otherlv_0= 'insert' ( (lv_category_1_0= ruleCategory ) )? ( (lv_entityClass_2_0= RULE_ENTITY_CLASS ) ) otherlv_3= 'as' ( (lv_name_4_0= RULE_STRING ) ) (otherlv_5= 'with' ( (lv_parameters_6_0= ruleParameter ) ) (otherlv_7= ',' ( (lv_parameters_8_0= ruleParameter ) ) )* )?
            {
            otherlv_0=(Token)match(input,16,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getInsertAccess().getInsertKeyword_0());
            		
            // InternalTqcl.g:430:3: ( (lv_category_1_0= ruleCategory ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=29 && LA7_0<=32)) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalTqcl.g:431:4: (lv_category_1_0= ruleCategory )
                    {
                    // InternalTqcl.g:431:4: (lv_category_1_0= ruleCategory )
                    // InternalTqcl.g:432:5: lv_category_1_0= ruleCategory
                    {

                    					newCompositeNode(grammarAccess.getInsertAccess().getCategoryCategoryEnumRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_10);
                    lv_category_1_0=ruleCategory();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getInsertRule());
                    					}
                    					set(
                    						current,
                    						"category",
                    						lv_category_1_0,
                    						"org.eclipse.triquetrum.commands.Tqcl.Category");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalTqcl.g:449:3: ( (lv_entityClass_2_0= RULE_ENTITY_CLASS ) )
            // InternalTqcl.g:450:4: (lv_entityClass_2_0= RULE_ENTITY_CLASS )
            {
            // InternalTqcl.g:450:4: (lv_entityClass_2_0= RULE_ENTITY_CLASS )
            // InternalTqcl.g:451:5: lv_entityClass_2_0= RULE_ENTITY_CLASS
            {
            lv_entityClass_2_0=(Token)match(input,RULE_ENTITY_CLASS,FOLLOW_11); 

            					newLeafNode(lv_entityClass_2_0, grammarAccess.getInsertAccess().getEntityClassENTITY_CLASSTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getInsertRule());
            					}
            					setWithLastConsumed(
            						current,
            						"entityClass",
            						lv_entityClass_2_0,
            						"org.eclipse.triquetrum.commands.Tqcl.ENTITY_CLASS");
            				

            }


            }

            otherlv_3=(Token)match(input,17,FOLLOW_7); 

            			newLeafNode(otherlv_3, grammarAccess.getInsertAccess().getAsKeyword_3());
            		
            // InternalTqcl.g:471:3: ( (lv_name_4_0= RULE_STRING ) )
            // InternalTqcl.g:472:4: (lv_name_4_0= RULE_STRING )
            {
            // InternalTqcl.g:472:4: (lv_name_4_0= RULE_STRING )
            // InternalTqcl.g:473:5: lv_name_4_0= RULE_STRING
            {
            lv_name_4_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

            					newLeafNode(lv_name_4_0, grammarAccess.getInsertAccess().getNameSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getInsertRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalTqcl.g:489:3: (otherlv_5= 'with' ( (lv_parameters_6_0= ruleParameter ) ) (otherlv_7= ',' ( (lv_parameters_8_0= ruleParameter ) ) )* )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==18) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalTqcl.g:490:4: otherlv_5= 'with' ( (lv_parameters_6_0= ruleParameter ) ) (otherlv_7= ',' ( (lv_parameters_8_0= ruleParameter ) ) )*
                    {
                    otherlv_5=(Token)match(input,18,FOLLOW_13); 

                    				newLeafNode(otherlv_5, grammarAccess.getInsertAccess().getWithKeyword_5_0());
                    			
                    // InternalTqcl.g:494:4: ( (lv_parameters_6_0= ruleParameter ) )
                    // InternalTqcl.g:495:5: (lv_parameters_6_0= ruleParameter )
                    {
                    // InternalTqcl.g:495:5: (lv_parameters_6_0= ruleParameter )
                    // InternalTqcl.g:496:6: lv_parameters_6_0= ruleParameter
                    {

                    						newCompositeNode(grammarAccess.getInsertAccess().getParametersParameterParserRuleCall_5_1_0());
                    					
                    pushFollow(FOLLOW_14);
                    lv_parameters_6_0=ruleParameter();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getInsertRule());
                    						}
                    						add(
                    							current,
                    							"parameters",
                    							lv_parameters_6_0,
                    							"org.eclipse.triquetrum.commands.Tqcl.Parameter");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalTqcl.g:513:4: (otherlv_7= ',' ( (lv_parameters_8_0= ruleParameter ) ) )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==19) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // InternalTqcl.g:514:5: otherlv_7= ',' ( (lv_parameters_8_0= ruleParameter ) )
                    	    {
                    	    otherlv_7=(Token)match(input,19,FOLLOW_13); 

                    	    					newLeafNode(otherlv_7, grammarAccess.getInsertAccess().getCommaKeyword_5_2_0());
                    	    				
                    	    // InternalTqcl.g:518:5: ( (lv_parameters_8_0= ruleParameter ) )
                    	    // InternalTqcl.g:519:6: (lv_parameters_8_0= ruleParameter )
                    	    {
                    	    // InternalTqcl.g:519:6: (lv_parameters_8_0= ruleParameter )
                    	    // InternalTqcl.g:520:7: lv_parameters_8_0= ruleParameter
                    	    {

                    	    							newCompositeNode(grammarAccess.getInsertAccess().getParametersParameterParserRuleCall_5_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_14);
                    	    lv_parameters_8_0=ruleParameter();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getInsertRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"parameters",
                    	    								lv_parameters_8_0,
                    	    								"org.eclipse.triquetrum.commands.Tqcl.Parameter");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInsert"


    // $ANTLR start "entryRuleSet"
    // InternalTqcl.g:543:1: entryRuleSet returns [EObject current=null] : iv_ruleSet= ruleSet EOF ;
    public final EObject entryRuleSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSet = null;


        try {
            // InternalTqcl.g:543:44: (iv_ruleSet= ruleSet EOF )
            // InternalTqcl.g:544:2: iv_ruleSet= ruleSet EOF
            {
             newCompositeNode(grammarAccess.getSetRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSet=ruleSet();

            state._fsp--;

             current =iv_ruleSet; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSet"


    // $ANTLR start "ruleSet"
    // InternalTqcl.g:550:1: ruleSet returns [EObject current=null] : (otherlv_0= 'set' ( (lv_param_1_0= ruleParameter ) ) ) ;
    public final EObject ruleSet() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_param_1_0 = null;



        	enterRule();

        try {
            // InternalTqcl.g:556:2: ( (otherlv_0= 'set' ( (lv_param_1_0= ruleParameter ) ) ) )
            // InternalTqcl.g:557:2: (otherlv_0= 'set' ( (lv_param_1_0= ruleParameter ) ) )
            {
            // InternalTqcl.g:557:2: (otherlv_0= 'set' ( (lv_param_1_0= ruleParameter ) ) )
            // InternalTqcl.g:558:3: otherlv_0= 'set' ( (lv_param_1_0= ruleParameter ) )
            {
            otherlv_0=(Token)match(input,20,FOLLOW_13); 

            			newLeafNode(otherlv_0, grammarAccess.getSetAccess().getSetKeyword_0());
            		
            // InternalTqcl.g:562:3: ( (lv_param_1_0= ruleParameter ) )
            // InternalTqcl.g:563:4: (lv_param_1_0= ruleParameter )
            {
            // InternalTqcl.g:563:4: (lv_param_1_0= ruleParameter )
            // InternalTqcl.g:564:5: lv_param_1_0= ruleParameter
            {

            					newCompositeNode(grammarAccess.getSetAccess().getParamParameterParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_param_1_0=ruleParameter();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSetRule());
            					}
            					set(
            						current,
            						"param",
            						lv_param_1_0,
            						"org.eclipse.triquetrum.commands.Tqcl.Parameter");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSet"


    // $ANTLR start "entryRuleConnect"
    // InternalTqcl.g:585:1: entryRuleConnect returns [EObject current=null] : iv_ruleConnect= ruleConnect EOF ;
    public final EObject entryRuleConnect() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConnect = null;


        try {
            // InternalTqcl.g:585:48: (iv_ruleConnect= ruleConnect EOF )
            // InternalTqcl.g:586:2: iv_ruleConnect= ruleConnect EOF
            {
             newCompositeNode(grammarAccess.getConnectRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConnect=ruleConnect();

            state._fsp--;

             current =iv_ruleConnect; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConnect"


    // $ANTLR start "ruleConnect"
    // InternalTqcl.g:592:1: ruleConnect returns [EObject current=null] : (otherlv_0= 'connect' ( (lv_from_1_0= ruleConnectionPort ) ) (otherlv_2= ',' ( (lv_from_3_0= ruleConnectionPort ) ) )* otherlv_4= 'to' ( (lv_to_5_0= ruleConnectionPort ) ) (otherlv_6= ',' ( (lv_to_7_0= ruleConnectionPort ) ) )* ) ;
    public final EObject ruleConnect() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_from_1_0 = null;

        EObject lv_from_3_0 = null;

        EObject lv_to_5_0 = null;

        EObject lv_to_7_0 = null;



        	enterRule();

        try {
            // InternalTqcl.g:598:2: ( (otherlv_0= 'connect' ( (lv_from_1_0= ruleConnectionPort ) ) (otherlv_2= ',' ( (lv_from_3_0= ruleConnectionPort ) ) )* otherlv_4= 'to' ( (lv_to_5_0= ruleConnectionPort ) ) (otherlv_6= ',' ( (lv_to_7_0= ruleConnectionPort ) ) )* ) )
            // InternalTqcl.g:599:2: (otherlv_0= 'connect' ( (lv_from_1_0= ruleConnectionPort ) ) (otherlv_2= ',' ( (lv_from_3_0= ruleConnectionPort ) ) )* otherlv_4= 'to' ( (lv_to_5_0= ruleConnectionPort ) ) (otherlv_6= ',' ( (lv_to_7_0= ruleConnectionPort ) ) )* )
            {
            // InternalTqcl.g:599:2: (otherlv_0= 'connect' ( (lv_from_1_0= ruleConnectionPort ) ) (otherlv_2= ',' ( (lv_from_3_0= ruleConnectionPort ) ) )* otherlv_4= 'to' ( (lv_to_5_0= ruleConnectionPort ) ) (otherlv_6= ',' ( (lv_to_7_0= ruleConnectionPort ) ) )* )
            // InternalTqcl.g:600:3: otherlv_0= 'connect' ( (lv_from_1_0= ruleConnectionPort ) ) (otherlv_2= ',' ( (lv_from_3_0= ruleConnectionPort ) ) )* otherlv_4= 'to' ( (lv_to_5_0= ruleConnectionPort ) ) (otherlv_6= ',' ( (lv_to_7_0= ruleConnectionPort ) ) )*
            {
            otherlv_0=(Token)match(input,21,FOLLOW_8); 

            			newLeafNode(otherlv_0, grammarAccess.getConnectAccess().getConnectKeyword_0());
            		
            // InternalTqcl.g:604:3: ( (lv_from_1_0= ruleConnectionPort ) )
            // InternalTqcl.g:605:4: (lv_from_1_0= ruleConnectionPort )
            {
            // InternalTqcl.g:605:4: (lv_from_1_0= ruleConnectionPort )
            // InternalTqcl.g:606:5: lv_from_1_0= ruleConnectionPort
            {

            					newCompositeNode(grammarAccess.getConnectAccess().getFromConnectionPortParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_15);
            lv_from_1_0=ruleConnectionPort();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConnectRule());
            					}
            					add(
            						current,
            						"from",
            						lv_from_1_0,
            						"org.eclipse.triquetrum.commands.Tqcl.ConnectionPort");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalTqcl.g:623:3: (otherlv_2= ',' ( (lv_from_3_0= ruleConnectionPort ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==19) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalTqcl.g:624:4: otherlv_2= ',' ( (lv_from_3_0= ruleConnectionPort ) )
            	    {
            	    otherlv_2=(Token)match(input,19,FOLLOW_8); 

            	    				newLeafNode(otherlv_2, grammarAccess.getConnectAccess().getCommaKeyword_2_0());
            	    			
            	    // InternalTqcl.g:628:4: ( (lv_from_3_0= ruleConnectionPort ) )
            	    // InternalTqcl.g:629:5: (lv_from_3_0= ruleConnectionPort )
            	    {
            	    // InternalTqcl.g:629:5: (lv_from_3_0= ruleConnectionPort )
            	    // InternalTqcl.g:630:6: lv_from_3_0= ruleConnectionPort
            	    {

            	    						newCompositeNode(grammarAccess.getConnectAccess().getFromConnectionPortParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_15);
            	    lv_from_3_0=ruleConnectionPort();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getConnectRule());
            	    						}
            	    						add(
            	    							current,
            	    							"from",
            	    							lv_from_3_0,
            	    							"org.eclipse.triquetrum.commands.Tqcl.ConnectionPort");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            otherlv_4=(Token)match(input,22,FOLLOW_8); 

            			newLeafNode(otherlv_4, grammarAccess.getConnectAccess().getToKeyword_3());
            		
            // InternalTqcl.g:652:3: ( (lv_to_5_0= ruleConnectionPort ) )
            // InternalTqcl.g:653:4: (lv_to_5_0= ruleConnectionPort )
            {
            // InternalTqcl.g:653:4: (lv_to_5_0= ruleConnectionPort )
            // InternalTqcl.g:654:5: lv_to_5_0= ruleConnectionPort
            {

            					newCompositeNode(grammarAccess.getConnectAccess().getToConnectionPortParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_14);
            lv_to_5_0=ruleConnectionPort();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConnectRule());
            					}
            					add(
            						current,
            						"to",
            						lv_to_5_0,
            						"org.eclipse.triquetrum.commands.Tqcl.ConnectionPort");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalTqcl.g:671:3: (otherlv_6= ',' ( (lv_to_7_0= ruleConnectionPort ) ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==19) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalTqcl.g:672:4: otherlv_6= ',' ( (lv_to_7_0= ruleConnectionPort ) )
            	    {
            	    otherlv_6=(Token)match(input,19,FOLLOW_8); 

            	    				newLeafNode(otherlv_6, grammarAccess.getConnectAccess().getCommaKeyword_5_0());
            	    			
            	    // InternalTqcl.g:676:4: ( (lv_to_7_0= ruleConnectionPort ) )
            	    // InternalTqcl.g:677:5: (lv_to_7_0= ruleConnectionPort )
            	    {
            	    // InternalTqcl.g:677:5: (lv_to_7_0= ruleConnectionPort )
            	    // InternalTqcl.g:678:6: lv_to_7_0= ruleConnectionPort
            	    {

            	    						newCompositeNode(grammarAccess.getConnectAccess().getToConnectionPortParserRuleCall_5_1_0());
            	    					
            	    pushFollow(FOLLOW_14);
            	    lv_to_7_0=ruleConnectionPort();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getConnectRule());
            	    						}
            	    						add(
            	    							current,
            	    							"to",
            	    							lv_to_7_0,
            	    							"org.eclipse.triquetrum.commands.Tqcl.ConnectionPort");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConnect"


    // $ANTLR start "entryRuleConnectionPort"
    // InternalTqcl.g:700:1: entryRuleConnectionPort returns [EObject current=null] : iv_ruleConnectionPort= ruleConnectionPort EOF ;
    public final EObject entryRuleConnectionPort() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConnectionPort = null;


        try {
            // InternalTqcl.g:700:55: (iv_ruleConnectionPort= ruleConnectionPort EOF )
            // InternalTqcl.g:701:2: iv_ruleConnectionPort= ruleConnectionPort EOF
            {
             newCompositeNode(grammarAccess.getConnectionPortRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConnectionPort=ruleConnectionPort();

            state._fsp--;

             current =iv_ruleConnectionPort; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConnectionPort"


    // $ANTLR start "ruleConnectionPort"
    // InternalTqcl.g:707:1: ruleConnectionPort returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' ( (lv_port_2_0= ruleNamedObj ) ) ) ;
    public final EObject ruleConnectionPort() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_port_2_0 = null;



        	enterRule();

        try {
            // InternalTqcl.g:713:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' ( (lv_port_2_0= ruleNamedObj ) ) ) )
            // InternalTqcl.g:714:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' ( (lv_port_2_0= ruleNamedObj ) ) )
            {
            // InternalTqcl.g:714:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' ( (lv_port_2_0= ruleNamedObj ) ) )
            // InternalTqcl.g:715:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' ( (lv_port_2_0= ruleNamedObj ) )
            {
            // InternalTqcl.g:715:3: ( (otherlv_0= RULE_ID ) )
            // InternalTqcl.g:716:4: (otherlv_0= RULE_ID )
            {
            // InternalTqcl.g:716:4: (otherlv_0= RULE_ID )
            // InternalTqcl.g:717:5: otherlv_0= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getConnectionPortRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_16); 

            					newLeafNode(otherlv_0, grammarAccess.getConnectionPortAccess().getActorInsertCrossReference_0_0());
            				

            }


            }

            otherlv_1=(Token)match(input,23,FOLLOW_17); 

            			newLeafNode(otherlv_1, grammarAccess.getConnectionPortAccess().getFullStopKeyword_1());
            		
            // InternalTqcl.g:732:3: ( (lv_port_2_0= ruleNamedObj ) )
            // InternalTqcl.g:733:4: (lv_port_2_0= ruleNamedObj )
            {
            // InternalTqcl.g:733:4: (lv_port_2_0= ruleNamedObj )
            // InternalTqcl.g:734:5: lv_port_2_0= ruleNamedObj
            {

            					newCompositeNode(grammarAccess.getConnectionPortAccess().getPortNamedObjParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_port_2_0=ruleNamedObj();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConnectionPortRule());
            					}
            					set(
            						current,
            						"port",
            						lv_port_2_0,
            						"org.eclipse.triquetrum.commands.Tqcl.NamedObj");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConnectionPort"


    // $ANTLR start "entryRuleGoInto"
    // InternalTqcl.g:755:1: entryRuleGoInto returns [EObject current=null] : iv_ruleGoInto= ruleGoInto EOF ;
    public final EObject entryRuleGoInto() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGoInto = null;


        try {
            // InternalTqcl.g:755:47: (iv_ruleGoInto= ruleGoInto EOF )
            // InternalTqcl.g:756:2: iv_ruleGoInto= ruleGoInto EOF
            {
             newCompositeNode(grammarAccess.getGoIntoRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGoInto=ruleGoInto();

            state._fsp--;

             current =iv_ruleGoInto; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGoInto"


    // $ANTLR start "ruleGoInto"
    // InternalTqcl.g:762:1: ruleGoInto returns [EObject current=null] : (otherlv_0= 'go' ( (lv_direction_1_0= 'into' ) ) ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject ruleGoInto() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_direction_1_0=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalTqcl.g:768:2: ( (otherlv_0= 'go' ( (lv_direction_1_0= 'into' ) ) ( (otherlv_2= RULE_ID ) ) ) )
            // InternalTqcl.g:769:2: (otherlv_0= 'go' ( (lv_direction_1_0= 'into' ) ) ( (otherlv_2= RULE_ID ) ) )
            {
            // InternalTqcl.g:769:2: (otherlv_0= 'go' ( (lv_direction_1_0= 'into' ) ) ( (otherlv_2= RULE_ID ) ) )
            // InternalTqcl.g:770:3: otherlv_0= 'go' ( (lv_direction_1_0= 'into' ) ) ( (otherlv_2= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_0, grammarAccess.getGoIntoAccess().getGoKeyword_0());
            		
            // InternalTqcl.g:774:3: ( (lv_direction_1_0= 'into' ) )
            // InternalTqcl.g:775:4: (lv_direction_1_0= 'into' )
            {
            // InternalTqcl.g:775:4: (lv_direction_1_0= 'into' )
            // InternalTqcl.g:776:5: lv_direction_1_0= 'into'
            {
            lv_direction_1_0=(Token)match(input,25,FOLLOW_8); 

            					newLeafNode(lv_direction_1_0, grammarAccess.getGoIntoAccess().getDirectionIntoKeyword_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGoIntoRule());
            					}
            					setWithLastConsumed(current, "direction", lv_direction_1_0, "into");
            				

            }


            }

            // InternalTqcl.g:788:3: ( (otherlv_2= RULE_ID ) )
            // InternalTqcl.g:789:4: (otherlv_2= RULE_ID )
            {
            // InternalTqcl.g:789:4: (otherlv_2= RULE_ID )
            // InternalTqcl.g:790:5: otherlv_2= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGoIntoRule());
            					}
            				
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(otherlv_2, grammarAccess.getGoIntoAccess().getActorInsertCrossReference_2_0());
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGoInto"


    // $ANTLR start "entryRuleGoOut"
    // InternalTqcl.g:805:1: entryRuleGoOut returns [EObject current=null] : iv_ruleGoOut= ruleGoOut EOF ;
    public final EObject entryRuleGoOut() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGoOut = null;


        try {
            // InternalTqcl.g:805:46: (iv_ruleGoOut= ruleGoOut EOF )
            // InternalTqcl.g:806:2: iv_ruleGoOut= ruleGoOut EOF
            {
             newCompositeNode(grammarAccess.getGoOutRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGoOut=ruleGoOut();

            state._fsp--;

             current =iv_ruleGoOut; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGoOut"


    // $ANTLR start "ruleGoOut"
    // InternalTqcl.g:812:1: ruleGoOut returns [EObject current=null] : (otherlv_0= 'go' ( (lv_direction_1_0= 'out' ) ) ) ;
    public final EObject ruleGoOut() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_direction_1_0=null;


        	enterRule();

        try {
            // InternalTqcl.g:818:2: ( (otherlv_0= 'go' ( (lv_direction_1_0= 'out' ) ) ) )
            // InternalTqcl.g:819:2: (otherlv_0= 'go' ( (lv_direction_1_0= 'out' ) ) )
            {
            // InternalTqcl.g:819:2: (otherlv_0= 'go' ( (lv_direction_1_0= 'out' ) ) )
            // InternalTqcl.g:820:3: otherlv_0= 'go' ( (lv_direction_1_0= 'out' ) )
            {
            otherlv_0=(Token)match(input,24,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getGoOutAccess().getGoKeyword_0());
            		
            // InternalTqcl.g:824:3: ( (lv_direction_1_0= 'out' ) )
            // InternalTqcl.g:825:4: (lv_direction_1_0= 'out' )
            {
            // InternalTqcl.g:825:4: (lv_direction_1_0= 'out' )
            // InternalTqcl.g:826:5: lv_direction_1_0= 'out'
            {
            lv_direction_1_0=(Token)match(input,26,FOLLOW_2); 

            					newLeafNode(lv_direction_1_0, grammarAccess.getGoOutAccess().getDirectionOutKeyword_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGoOutRule());
            					}
            					setWithLastConsumed(current, "direction", lv_direction_1_0, "out");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGoOut"


    // $ANTLR start "entryRuleGoTop"
    // InternalTqcl.g:842:1: entryRuleGoTop returns [EObject current=null] : iv_ruleGoTop= ruleGoTop EOF ;
    public final EObject entryRuleGoTop() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGoTop = null;


        try {
            // InternalTqcl.g:842:46: (iv_ruleGoTop= ruleGoTop EOF )
            // InternalTqcl.g:843:2: iv_ruleGoTop= ruleGoTop EOF
            {
             newCompositeNode(grammarAccess.getGoTopRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGoTop=ruleGoTop();

            state._fsp--;

             current =iv_ruleGoTop; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGoTop"


    // $ANTLR start "ruleGoTop"
    // InternalTqcl.g:849:1: ruleGoTop returns [EObject current=null] : (otherlv_0= 'go' ( (lv_direction_1_0= 'top' ) ) ) ;
    public final EObject ruleGoTop() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_direction_1_0=null;


        	enterRule();

        try {
            // InternalTqcl.g:855:2: ( (otherlv_0= 'go' ( (lv_direction_1_0= 'top' ) ) ) )
            // InternalTqcl.g:856:2: (otherlv_0= 'go' ( (lv_direction_1_0= 'top' ) ) )
            {
            // InternalTqcl.g:856:2: (otherlv_0= 'go' ( (lv_direction_1_0= 'top' ) ) )
            // InternalTqcl.g:857:3: otherlv_0= 'go' ( (lv_direction_1_0= 'top' ) )
            {
            otherlv_0=(Token)match(input,24,FOLLOW_20); 

            			newLeafNode(otherlv_0, grammarAccess.getGoTopAccess().getGoKeyword_0());
            		
            // InternalTqcl.g:861:3: ( (lv_direction_1_0= 'top' ) )
            // InternalTqcl.g:862:4: (lv_direction_1_0= 'top' )
            {
            // InternalTqcl.g:862:4: (lv_direction_1_0= 'top' )
            // InternalTqcl.g:863:5: lv_direction_1_0= 'top'
            {
            lv_direction_1_0=(Token)match(input,27,FOLLOW_2); 

            					newLeafNode(lv_direction_1_0, grammarAccess.getGoTopAccess().getDirectionTopKeyword_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGoTopRule());
            					}
            					setWithLastConsumed(current, "direction", lv_direction_1_0, "top");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGoTop"


    // $ANTLR start "entryRuleParameter"
    // InternalTqcl.g:879:1: entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;


        try {
            // InternalTqcl.g:879:50: (iv_ruleParameter= ruleParameter EOF )
            // InternalTqcl.g:880:2: iv_ruleParameter= ruleParameter EOF
            {
             newCompositeNode(grammarAccess.getParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameter=ruleParameter();

            state._fsp--;

             current =iv_ruleParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // InternalTqcl.g:886:1: ruleParameter returns [EObject current=null] : ( ( (lv_id_0_0= ruleParameter_id ) ) otherlv_1= '=' ( (lv_value_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_value_2_0=null;
        AntlrDatatypeRuleToken lv_id_0_0 = null;



        	enterRule();

        try {
            // InternalTqcl.g:892:2: ( ( ( (lv_id_0_0= ruleParameter_id ) ) otherlv_1= '=' ( (lv_value_2_0= RULE_STRING ) ) ) )
            // InternalTqcl.g:893:2: ( ( (lv_id_0_0= ruleParameter_id ) ) otherlv_1= '=' ( (lv_value_2_0= RULE_STRING ) ) )
            {
            // InternalTqcl.g:893:2: ( ( (lv_id_0_0= ruleParameter_id ) ) otherlv_1= '=' ( (lv_value_2_0= RULE_STRING ) ) )
            // InternalTqcl.g:894:3: ( (lv_id_0_0= ruleParameter_id ) ) otherlv_1= '=' ( (lv_value_2_0= RULE_STRING ) )
            {
            // InternalTqcl.g:894:3: ( (lv_id_0_0= ruleParameter_id ) )
            // InternalTqcl.g:895:4: (lv_id_0_0= ruleParameter_id )
            {
            // InternalTqcl.g:895:4: (lv_id_0_0= ruleParameter_id )
            // InternalTqcl.g:896:5: lv_id_0_0= ruleParameter_id
            {

            					newCompositeNode(grammarAccess.getParameterAccess().getIdParameter_idParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_21);
            lv_id_0_0=ruleParameter_id();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getParameterRule());
            					}
            					set(
            						current,
            						"id",
            						lv_id_0_0,
            						"org.eclipse.triquetrum.commands.Tqcl.Parameter_id");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,28,FOLLOW_7); 

            			newLeafNode(otherlv_1, grammarAccess.getParameterAccess().getEqualsSignKeyword_1());
            		
            // InternalTqcl.g:917:3: ( (lv_value_2_0= RULE_STRING ) )
            // InternalTqcl.g:918:4: (lv_value_2_0= RULE_STRING )
            {
            // InternalTqcl.g:918:4: (lv_value_2_0= RULE_STRING )
            // InternalTqcl.g:919:5: lv_value_2_0= RULE_STRING
            {
            lv_value_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_value_2_0, grammarAccess.getParameterAccess().getValueSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getParameterRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "entryRuleParameter_id"
    // InternalTqcl.g:939:1: entryRuleParameter_id returns [String current=null] : iv_ruleParameter_id= ruleParameter_id EOF ;
    public final String entryRuleParameter_id() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleParameter_id = null;


        try {
            // InternalTqcl.g:939:52: (iv_ruleParameter_id= ruleParameter_id EOF )
            // InternalTqcl.g:940:2: iv_ruleParameter_id= ruleParameter_id EOF
            {
             newCompositeNode(grammarAccess.getParameter_idRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameter_id=ruleParameter_id();

            state._fsp--;

             current =iv_ruleParameter_id.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameter_id"


    // $ANTLR start "ruleParameter_id"
    // InternalTqcl.g:946:1: ruleParameter_id returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_PARAMETER_NAME_0= RULE_PARAMETER_NAME | this_STRING_1= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleParameter_id() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_PARAMETER_NAME_0=null;
        Token this_STRING_1=null;


        	enterRule();

        try {
            // InternalTqcl.g:952:2: ( (this_PARAMETER_NAME_0= RULE_PARAMETER_NAME | this_STRING_1= RULE_STRING ) )
            // InternalTqcl.g:953:2: (this_PARAMETER_NAME_0= RULE_PARAMETER_NAME | this_STRING_1= RULE_STRING )
            {
            // InternalTqcl.g:953:2: (this_PARAMETER_NAME_0= RULE_PARAMETER_NAME | this_STRING_1= RULE_STRING )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_PARAMETER_NAME) ) {
                alt12=1;
            }
            else if ( (LA12_0==RULE_STRING) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // InternalTqcl.g:954:3: this_PARAMETER_NAME_0= RULE_PARAMETER_NAME
                    {
                    this_PARAMETER_NAME_0=(Token)match(input,RULE_PARAMETER_NAME,FOLLOW_2); 

                    			current.merge(this_PARAMETER_NAME_0);
                    		

                    			newLeafNode(this_PARAMETER_NAME_0, grammarAccess.getParameter_idAccess().getPARAMETER_NAMETerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalTqcl.g:962:3: this_STRING_1= RULE_STRING
                    {
                    this_STRING_1=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    			current.merge(this_STRING_1);
                    		

                    			newLeafNode(this_STRING_1, grammarAccess.getParameter_idAccess().getSTRINGTerminalRuleCall_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameter_id"


    // $ANTLR start "entryRuleNamedObj"
    // InternalTqcl.g:973:1: entryRuleNamedObj returns [String current=null] : iv_ruleNamedObj= ruleNamedObj EOF ;
    public final String entryRuleNamedObj() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNamedObj = null;


        try {
            // InternalTqcl.g:973:48: (iv_ruleNamedObj= ruleNamedObj EOF )
            // InternalTqcl.g:974:2: iv_ruleNamedObj= ruleNamedObj EOF
            {
             newCompositeNode(grammarAccess.getNamedObjRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNamedObj=ruleNamedObj();

            state._fsp--;

             current =iv_ruleNamedObj.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNamedObj"


    // $ANTLR start "ruleNamedObj"
    // InternalTqcl.g:980:1: ruleNamedObj returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | this_STRING_1= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleNamedObj() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_STRING_1=null;


        	enterRule();

        try {
            // InternalTqcl.g:986:2: ( (this_ID_0= RULE_ID | this_STRING_1= RULE_STRING ) )
            // InternalTqcl.g:987:2: (this_ID_0= RULE_ID | this_STRING_1= RULE_STRING )
            {
            // InternalTqcl.g:987:2: (this_ID_0= RULE_ID | this_STRING_1= RULE_STRING )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_ID) ) {
                alt13=1;
            }
            else if ( (LA13_0==RULE_STRING) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // InternalTqcl.g:988:3: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                    			current.merge(this_ID_0);
                    		

                    			newLeafNode(this_ID_0, grammarAccess.getNamedObjAccess().getIDTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalTqcl.g:996:3: this_STRING_1= RULE_STRING
                    {
                    this_STRING_1=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    			current.merge(this_STRING_1);
                    		

                    			newLeafNode(this_STRING_1, grammarAccess.getNamedObjAccess().getSTRINGTerminalRuleCall_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNamedObj"


    // $ANTLR start "ruleCategory"
    // InternalTqcl.g:1007:1: ruleCategory returns [Enumerator current=null] : ( (enumLiteral_0= 'actor' ) | (enumLiteral_1= 'parameter' ) | (enumLiteral_2= 'port' ) | (enumLiteral_3= 'director' ) ) ;
    public final Enumerator ruleCategory() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;


        	enterRule();

        try {
            // InternalTqcl.g:1013:2: ( ( (enumLiteral_0= 'actor' ) | (enumLiteral_1= 'parameter' ) | (enumLiteral_2= 'port' ) | (enumLiteral_3= 'director' ) ) )
            // InternalTqcl.g:1014:2: ( (enumLiteral_0= 'actor' ) | (enumLiteral_1= 'parameter' ) | (enumLiteral_2= 'port' ) | (enumLiteral_3= 'director' ) )
            {
            // InternalTqcl.g:1014:2: ( (enumLiteral_0= 'actor' ) | (enumLiteral_1= 'parameter' ) | (enumLiteral_2= 'port' ) | (enumLiteral_3= 'director' ) )
            int alt14=4;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt14=1;
                }
                break;
            case 30:
                {
                alt14=2;
                }
                break;
            case 31:
                {
                alt14=3;
                }
                break;
            case 32:
                {
                alt14=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // InternalTqcl.g:1015:3: (enumLiteral_0= 'actor' )
                    {
                    // InternalTqcl.g:1015:3: (enumLiteral_0= 'actor' )
                    // InternalTqcl.g:1016:4: enumLiteral_0= 'actor'
                    {
                    enumLiteral_0=(Token)match(input,29,FOLLOW_2); 

                    				current = grammarAccess.getCategoryAccess().getActorEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getCategoryAccess().getActorEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalTqcl.g:1023:3: (enumLiteral_1= 'parameter' )
                    {
                    // InternalTqcl.g:1023:3: (enumLiteral_1= 'parameter' )
                    // InternalTqcl.g:1024:4: enumLiteral_1= 'parameter'
                    {
                    enumLiteral_1=(Token)match(input,30,FOLLOW_2); 

                    				current = grammarAccess.getCategoryAccess().getParameterEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getCategoryAccess().getParameterEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalTqcl.g:1031:3: (enumLiteral_2= 'port' )
                    {
                    // InternalTqcl.g:1031:3: (enumLiteral_2= 'port' )
                    // InternalTqcl.g:1032:4: enumLiteral_2= 'port'
                    {
                    enumLiteral_2=(Token)match(input,31,FOLLOW_2); 

                    				current = grammarAccess.getCategoryAccess().getPortEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getCategoryAccess().getPortEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalTqcl.g:1039:3: (enumLiteral_3= 'director' )
                    {
                    // InternalTqcl.g:1039:3: (enumLiteral_3= 'director' )
                    // InternalTqcl.g:1040:4: enumLiteral_3= 'director'
                    {
                    enumLiteral_3=(Token)match(input,32,FOLLOW_2); 

                    				current = grammarAccess.getCategoryAccess().getDirectorEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getCategoryAccess().getDirectorEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCategory"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x000000000131C002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000001314002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000001314000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x00000001E0000040L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000480000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000010000000L});

}