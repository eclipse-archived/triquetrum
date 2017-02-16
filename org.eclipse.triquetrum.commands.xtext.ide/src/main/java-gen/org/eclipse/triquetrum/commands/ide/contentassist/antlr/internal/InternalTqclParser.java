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
package org.eclipse.triquetrum.commands.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.eclipse.triquetrum.commands.services.TqclGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTqclParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_PARAMETER_NAME", "RULE_STRING", "RULE_ID", "RULE_ENTITY_CLASS", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'actor'", "'parameter'", "'port'", "'director'", "';'", "'include'", "'library'", "'insert'", "'as'", "'with'", "','", "'set'", "'connect'", "'to'", "'.'", "'go'", "'='", "'into'", "'out'", "'top'"
    };
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


        public InternalTqclParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalTqclParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalTqclParser.tokenNames; }
    public String getGrammarFileName() { return "InternalTqcl.g"; }


    	private TqclGrammarAccess grammarAccess;

    	public void setGrammarAccess(TqclGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleTriquetrumScript"
    // InternalTqcl.g:62:1: entryRuleTriquetrumScript : ruleTriquetrumScript EOF ;
    public final void entryRuleTriquetrumScript() throws RecognitionException {
        try {
            // InternalTqcl.g:63:1: ( ruleTriquetrumScript EOF )
            // InternalTqcl.g:64:1: ruleTriquetrumScript EOF
            {
             before(grammarAccess.getTriquetrumScriptRule()); 
            pushFollow(FOLLOW_1);
            ruleTriquetrumScript();

            state._fsp--;

             after(grammarAccess.getTriquetrumScriptRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTriquetrumScript"


    // $ANTLR start "ruleTriquetrumScript"
    // InternalTqcl.g:71:1: ruleTriquetrumScript : ( ( rule__TriquetrumScript__Group__0 ) ) ;
    public final void ruleTriquetrumScript() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:75:2: ( ( ( rule__TriquetrumScript__Group__0 ) ) )
            // InternalTqcl.g:76:2: ( ( rule__TriquetrumScript__Group__0 ) )
            {
            // InternalTqcl.g:76:2: ( ( rule__TriquetrumScript__Group__0 ) )
            // InternalTqcl.g:77:3: ( rule__TriquetrumScript__Group__0 )
            {
             before(grammarAccess.getTriquetrumScriptAccess().getGroup()); 
            // InternalTqcl.g:78:3: ( rule__TriquetrumScript__Group__0 )
            // InternalTqcl.g:78:4: rule__TriquetrumScript__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__TriquetrumScript__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTriquetrumScriptAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTriquetrumScript"


    // $ANTLR start "entryRuleCompositeCommand"
    // InternalTqcl.g:87:1: entryRuleCompositeCommand : ruleCompositeCommand EOF ;
    public final void entryRuleCompositeCommand() throws RecognitionException {
        try {
            // InternalTqcl.g:88:1: ( ruleCompositeCommand EOF )
            // InternalTqcl.g:89:1: ruleCompositeCommand EOF
            {
             before(grammarAccess.getCompositeCommandRule()); 
            pushFollow(FOLLOW_1);
            ruleCompositeCommand();

            state._fsp--;

             after(grammarAccess.getCompositeCommandRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCompositeCommand"


    // $ANTLR start "ruleCompositeCommand"
    // InternalTqcl.g:96:1: ruleCompositeCommand : ( ( rule__CompositeCommand__Group__0 ) ) ;
    public final void ruleCompositeCommand() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:100:2: ( ( ( rule__CompositeCommand__Group__0 ) ) )
            // InternalTqcl.g:101:2: ( ( rule__CompositeCommand__Group__0 ) )
            {
            // InternalTqcl.g:101:2: ( ( rule__CompositeCommand__Group__0 ) )
            // InternalTqcl.g:102:3: ( rule__CompositeCommand__Group__0 )
            {
             before(grammarAccess.getCompositeCommandAccess().getGroup()); 
            // InternalTqcl.g:103:3: ( rule__CompositeCommand__Group__0 )
            // InternalTqcl.g:103:4: rule__CompositeCommand__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__CompositeCommand__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getCompositeCommandAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCompositeCommand"


    // $ANTLR start "entryRuleSimpleCommand"
    // InternalTqcl.g:112:1: entryRuleSimpleCommand : ruleSimpleCommand EOF ;
    public final void entryRuleSimpleCommand() throws RecognitionException {
        try {
            // InternalTqcl.g:113:1: ( ruleSimpleCommand EOF )
            // InternalTqcl.g:114:1: ruleSimpleCommand EOF
            {
             before(grammarAccess.getSimpleCommandRule()); 
            pushFollow(FOLLOW_1);
            ruleSimpleCommand();

            state._fsp--;

             after(grammarAccess.getSimpleCommandRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSimpleCommand"


    // $ANTLR start "ruleSimpleCommand"
    // InternalTqcl.g:121:1: ruleSimpleCommand : ( ( rule__SimpleCommand__Group__0 ) ) ;
    public final void ruleSimpleCommand() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:125:2: ( ( ( rule__SimpleCommand__Group__0 ) ) )
            // InternalTqcl.g:126:2: ( ( rule__SimpleCommand__Group__0 ) )
            {
            // InternalTqcl.g:126:2: ( ( rule__SimpleCommand__Group__0 ) )
            // InternalTqcl.g:127:3: ( rule__SimpleCommand__Group__0 )
            {
             before(grammarAccess.getSimpleCommandAccess().getGroup()); 
            // InternalTqcl.g:128:3: ( rule__SimpleCommand__Group__0 )
            // InternalTqcl.g:128:4: rule__SimpleCommand__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__SimpleCommand__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSimpleCommandAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSimpleCommand"


    // $ANTLR start "entryRuleCommand"
    // InternalTqcl.g:137:1: entryRuleCommand : ruleCommand EOF ;
    public final void entryRuleCommand() throws RecognitionException {
        try {
            // InternalTqcl.g:138:1: ( ruleCommand EOF )
            // InternalTqcl.g:139:1: ruleCommand EOF
            {
             before(grammarAccess.getCommandRule()); 
            pushFollow(FOLLOW_1);
            ruleCommand();

            state._fsp--;

             after(grammarAccess.getCommandRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCommand"


    // $ANTLR start "ruleCommand"
    // InternalTqcl.g:146:1: ruleCommand : ( ( rule__Command__Alternatives ) ) ;
    public final void ruleCommand() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:150:2: ( ( ( rule__Command__Alternatives ) ) )
            // InternalTqcl.g:151:2: ( ( rule__Command__Alternatives ) )
            {
            // InternalTqcl.g:151:2: ( ( rule__Command__Alternatives ) )
            // InternalTqcl.g:152:3: ( rule__Command__Alternatives )
            {
             before(grammarAccess.getCommandAccess().getAlternatives()); 
            // InternalTqcl.g:153:3: ( rule__Command__Alternatives )
            // InternalTqcl.g:153:4: rule__Command__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Command__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getCommandAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCommand"


    // $ANTLR start "entryRuleInclude"
    // InternalTqcl.g:162:1: entryRuleInclude : ruleInclude EOF ;
    public final void entryRuleInclude() throws RecognitionException {
        try {
            // InternalTqcl.g:163:1: ( ruleInclude EOF )
            // InternalTqcl.g:164:1: ruleInclude EOF
            {
             before(grammarAccess.getIncludeRule()); 
            pushFollow(FOLLOW_1);
            ruleInclude();

            state._fsp--;

             after(grammarAccess.getIncludeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleInclude"


    // $ANTLR start "ruleInclude"
    // InternalTqcl.g:171:1: ruleInclude : ( ( rule__Include__Group__0 ) ) ;
    public final void ruleInclude() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:175:2: ( ( ( rule__Include__Group__0 ) ) )
            // InternalTqcl.g:176:2: ( ( rule__Include__Group__0 ) )
            {
            // InternalTqcl.g:176:2: ( ( rule__Include__Group__0 ) )
            // InternalTqcl.g:177:3: ( rule__Include__Group__0 )
            {
             before(grammarAccess.getIncludeAccess().getGroup()); 
            // InternalTqcl.g:178:3: ( rule__Include__Group__0 )
            // InternalTqcl.g:178:4: rule__Include__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Include__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getIncludeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleInclude"


    // $ANTLR start "entryRuleLibrary"
    // InternalTqcl.g:187:1: entryRuleLibrary : ruleLibrary EOF ;
    public final void entryRuleLibrary() throws RecognitionException {
        try {
            // InternalTqcl.g:188:1: ( ruleLibrary EOF )
            // InternalTqcl.g:189:1: ruleLibrary EOF
            {
             before(grammarAccess.getLibraryRule()); 
            pushFollow(FOLLOW_1);
            ruleLibrary();

            state._fsp--;

             after(grammarAccess.getLibraryRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleLibrary"


    // $ANTLR start "ruleLibrary"
    // InternalTqcl.g:196:1: ruleLibrary : ( ( rule__Library__Group__0 ) ) ;
    public final void ruleLibrary() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:200:2: ( ( ( rule__Library__Group__0 ) ) )
            // InternalTqcl.g:201:2: ( ( rule__Library__Group__0 ) )
            {
            // InternalTqcl.g:201:2: ( ( rule__Library__Group__0 ) )
            // InternalTqcl.g:202:3: ( rule__Library__Group__0 )
            {
             before(grammarAccess.getLibraryAccess().getGroup()); 
            // InternalTqcl.g:203:3: ( rule__Library__Group__0 )
            // InternalTqcl.g:203:4: rule__Library__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Library__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getLibraryAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLibrary"


    // $ANTLR start "entryRuleInsert"
    // InternalTqcl.g:212:1: entryRuleInsert : ruleInsert EOF ;
    public final void entryRuleInsert() throws RecognitionException {
        try {
            // InternalTqcl.g:213:1: ( ruleInsert EOF )
            // InternalTqcl.g:214:1: ruleInsert EOF
            {
             before(grammarAccess.getInsertRule()); 
            pushFollow(FOLLOW_1);
            ruleInsert();

            state._fsp--;

             after(grammarAccess.getInsertRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleInsert"


    // $ANTLR start "ruleInsert"
    // InternalTqcl.g:221:1: ruleInsert : ( ( rule__Insert__Group__0 ) ) ;
    public final void ruleInsert() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:225:2: ( ( ( rule__Insert__Group__0 ) ) )
            // InternalTqcl.g:226:2: ( ( rule__Insert__Group__0 ) )
            {
            // InternalTqcl.g:226:2: ( ( rule__Insert__Group__0 ) )
            // InternalTqcl.g:227:3: ( rule__Insert__Group__0 )
            {
             before(grammarAccess.getInsertAccess().getGroup()); 
            // InternalTqcl.g:228:3: ( rule__Insert__Group__0 )
            // InternalTqcl.g:228:4: rule__Insert__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Insert__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getInsertAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleInsert"


    // $ANTLR start "entryRuleSet"
    // InternalTqcl.g:237:1: entryRuleSet : ruleSet EOF ;
    public final void entryRuleSet() throws RecognitionException {
        try {
            // InternalTqcl.g:238:1: ( ruleSet EOF )
            // InternalTqcl.g:239:1: ruleSet EOF
            {
             before(grammarAccess.getSetRule()); 
            pushFollow(FOLLOW_1);
            ruleSet();

            state._fsp--;

             after(grammarAccess.getSetRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSet"


    // $ANTLR start "ruleSet"
    // InternalTqcl.g:246:1: ruleSet : ( ( rule__Set__Group__0 ) ) ;
    public final void ruleSet() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:250:2: ( ( ( rule__Set__Group__0 ) ) )
            // InternalTqcl.g:251:2: ( ( rule__Set__Group__0 ) )
            {
            // InternalTqcl.g:251:2: ( ( rule__Set__Group__0 ) )
            // InternalTqcl.g:252:3: ( rule__Set__Group__0 )
            {
             before(grammarAccess.getSetAccess().getGroup()); 
            // InternalTqcl.g:253:3: ( rule__Set__Group__0 )
            // InternalTqcl.g:253:4: rule__Set__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Set__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSetAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSet"


    // $ANTLR start "entryRuleConnect"
    // InternalTqcl.g:262:1: entryRuleConnect : ruleConnect EOF ;
    public final void entryRuleConnect() throws RecognitionException {
        try {
            // InternalTqcl.g:263:1: ( ruleConnect EOF )
            // InternalTqcl.g:264:1: ruleConnect EOF
            {
             before(grammarAccess.getConnectRule()); 
            pushFollow(FOLLOW_1);
            ruleConnect();

            state._fsp--;

             after(grammarAccess.getConnectRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleConnect"


    // $ANTLR start "ruleConnect"
    // InternalTqcl.g:271:1: ruleConnect : ( ( rule__Connect__Group__0 ) ) ;
    public final void ruleConnect() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:275:2: ( ( ( rule__Connect__Group__0 ) ) )
            // InternalTqcl.g:276:2: ( ( rule__Connect__Group__0 ) )
            {
            // InternalTqcl.g:276:2: ( ( rule__Connect__Group__0 ) )
            // InternalTqcl.g:277:3: ( rule__Connect__Group__0 )
            {
             before(grammarAccess.getConnectAccess().getGroup()); 
            // InternalTqcl.g:278:3: ( rule__Connect__Group__0 )
            // InternalTqcl.g:278:4: rule__Connect__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Connect__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getConnectAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConnect"


    // $ANTLR start "entryRuleConnectionPort"
    // InternalTqcl.g:287:1: entryRuleConnectionPort : ruleConnectionPort EOF ;
    public final void entryRuleConnectionPort() throws RecognitionException {
        try {
            // InternalTqcl.g:288:1: ( ruleConnectionPort EOF )
            // InternalTqcl.g:289:1: ruleConnectionPort EOF
            {
             before(grammarAccess.getConnectionPortRule()); 
            pushFollow(FOLLOW_1);
            ruleConnectionPort();

            state._fsp--;

             after(grammarAccess.getConnectionPortRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleConnectionPort"


    // $ANTLR start "ruleConnectionPort"
    // InternalTqcl.g:296:1: ruleConnectionPort : ( ( rule__ConnectionPort__Group__0 ) ) ;
    public final void ruleConnectionPort() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:300:2: ( ( ( rule__ConnectionPort__Group__0 ) ) )
            // InternalTqcl.g:301:2: ( ( rule__ConnectionPort__Group__0 ) )
            {
            // InternalTqcl.g:301:2: ( ( rule__ConnectionPort__Group__0 ) )
            // InternalTqcl.g:302:3: ( rule__ConnectionPort__Group__0 )
            {
             before(grammarAccess.getConnectionPortAccess().getGroup()); 
            // InternalTqcl.g:303:3: ( rule__ConnectionPort__Group__0 )
            // InternalTqcl.g:303:4: rule__ConnectionPort__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ConnectionPort__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getConnectionPortAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConnectionPort"


    // $ANTLR start "entryRuleGoInto"
    // InternalTqcl.g:312:1: entryRuleGoInto : ruleGoInto EOF ;
    public final void entryRuleGoInto() throws RecognitionException {
        try {
            // InternalTqcl.g:313:1: ( ruleGoInto EOF )
            // InternalTqcl.g:314:1: ruleGoInto EOF
            {
             before(grammarAccess.getGoIntoRule()); 
            pushFollow(FOLLOW_1);
            ruleGoInto();

            state._fsp--;

             after(grammarAccess.getGoIntoRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleGoInto"


    // $ANTLR start "ruleGoInto"
    // InternalTqcl.g:321:1: ruleGoInto : ( ( rule__GoInto__Group__0 ) ) ;
    public final void ruleGoInto() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:325:2: ( ( ( rule__GoInto__Group__0 ) ) )
            // InternalTqcl.g:326:2: ( ( rule__GoInto__Group__0 ) )
            {
            // InternalTqcl.g:326:2: ( ( rule__GoInto__Group__0 ) )
            // InternalTqcl.g:327:3: ( rule__GoInto__Group__0 )
            {
             before(grammarAccess.getGoIntoAccess().getGroup()); 
            // InternalTqcl.g:328:3: ( rule__GoInto__Group__0 )
            // InternalTqcl.g:328:4: rule__GoInto__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__GoInto__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getGoIntoAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleGoInto"


    // $ANTLR start "entryRuleGoOut"
    // InternalTqcl.g:337:1: entryRuleGoOut : ruleGoOut EOF ;
    public final void entryRuleGoOut() throws RecognitionException {
        try {
            // InternalTqcl.g:338:1: ( ruleGoOut EOF )
            // InternalTqcl.g:339:1: ruleGoOut EOF
            {
             before(grammarAccess.getGoOutRule()); 
            pushFollow(FOLLOW_1);
            ruleGoOut();

            state._fsp--;

             after(grammarAccess.getGoOutRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleGoOut"


    // $ANTLR start "ruleGoOut"
    // InternalTqcl.g:346:1: ruleGoOut : ( ( rule__GoOut__Group__0 ) ) ;
    public final void ruleGoOut() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:350:2: ( ( ( rule__GoOut__Group__0 ) ) )
            // InternalTqcl.g:351:2: ( ( rule__GoOut__Group__0 ) )
            {
            // InternalTqcl.g:351:2: ( ( rule__GoOut__Group__0 ) )
            // InternalTqcl.g:352:3: ( rule__GoOut__Group__0 )
            {
             before(grammarAccess.getGoOutAccess().getGroup()); 
            // InternalTqcl.g:353:3: ( rule__GoOut__Group__0 )
            // InternalTqcl.g:353:4: rule__GoOut__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__GoOut__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getGoOutAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleGoOut"


    // $ANTLR start "entryRuleGoTop"
    // InternalTqcl.g:362:1: entryRuleGoTop : ruleGoTop EOF ;
    public final void entryRuleGoTop() throws RecognitionException {
        try {
            // InternalTqcl.g:363:1: ( ruleGoTop EOF )
            // InternalTqcl.g:364:1: ruleGoTop EOF
            {
             before(grammarAccess.getGoTopRule()); 
            pushFollow(FOLLOW_1);
            ruleGoTop();

            state._fsp--;

             after(grammarAccess.getGoTopRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleGoTop"


    // $ANTLR start "ruleGoTop"
    // InternalTqcl.g:371:1: ruleGoTop : ( ( rule__GoTop__Group__0 ) ) ;
    public final void ruleGoTop() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:375:2: ( ( ( rule__GoTop__Group__0 ) ) )
            // InternalTqcl.g:376:2: ( ( rule__GoTop__Group__0 ) )
            {
            // InternalTqcl.g:376:2: ( ( rule__GoTop__Group__0 ) )
            // InternalTqcl.g:377:3: ( rule__GoTop__Group__0 )
            {
             before(grammarAccess.getGoTopAccess().getGroup()); 
            // InternalTqcl.g:378:3: ( rule__GoTop__Group__0 )
            // InternalTqcl.g:378:4: rule__GoTop__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__GoTop__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getGoTopAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleGoTop"


    // $ANTLR start "entryRuleParameter"
    // InternalTqcl.g:387:1: entryRuleParameter : ruleParameter EOF ;
    public final void entryRuleParameter() throws RecognitionException {
        try {
            // InternalTqcl.g:388:1: ( ruleParameter EOF )
            // InternalTqcl.g:389:1: ruleParameter EOF
            {
             before(grammarAccess.getParameterRule()); 
            pushFollow(FOLLOW_1);
            ruleParameter();

            state._fsp--;

             after(grammarAccess.getParameterRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // InternalTqcl.g:396:1: ruleParameter : ( ( rule__Parameter__Group__0 ) ) ;
    public final void ruleParameter() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:400:2: ( ( ( rule__Parameter__Group__0 ) ) )
            // InternalTqcl.g:401:2: ( ( rule__Parameter__Group__0 ) )
            {
            // InternalTqcl.g:401:2: ( ( rule__Parameter__Group__0 ) )
            // InternalTqcl.g:402:3: ( rule__Parameter__Group__0 )
            {
             before(grammarAccess.getParameterAccess().getGroup()); 
            // InternalTqcl.g:403:3: ( rule__Parameter__Group__0 )
            // InternalTqcl.g:403:4: rule__Parameter__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Parameter__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getParameterAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "entryRuleParameter_id"
    // InternalTqcl.g:412:1: entryRuleParameter_id : ruleParameter_id EOF ;
    public final void entryRuleParameter_id() throws RecognitionException {
        try {
            // InternalTqcl.g:413:1: ( ruleParameter_id EOF )
            // InternalTqcl.g:414:1: ruleParameter_id EOF
            {
             before(grammarAccess.getParameter_idRule()); 
            pushFollow(FOLLOW_1);
            ruleParameter_id();

            state._fsp--;

             after(grammarAccess.getParameter_idRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleParameter_id"


    // $ANTLR start "ruleParameter_id"
    // InternalTqcl.g:421:1: ruleParameter_id : ( ( rule__Parameter_id__Alternatives ) ) ;
    public final void ruleParameter_id() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:425:2: ( ( ( rule__Parameter_id__Alternatives ) ) )
            // InternalTqcl.g:426:2: ( ( rule__Parameter_id__Alternatives ) )
            {
            // InternalTqcl.g:426:2: ( ( rule__Parameter_id__Alternatives ) )
            // InternalTqcl.g:427:3: ( rule__Parameter_id__Alternatives )
            {
             before(grammarAccess.getParameter_idAccess().getAlternatives()); 
            // InternalTqcl.g:428:3: ( rule__Parameter_id__Alternatives )
            // InternalTqcl.g:428:4: rule__Parameter_id__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Parameter_id__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getParameter_idAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleParameter_id"


    // $ANTLR start "entryRuleNamedObj"
    // InternalTqcl.g:437:1: entryRuleNamedObj : ruleNamedObj EOF ;
    public final void entryRuleNamedObj() throws RecognitionException {
        try {
            // InternalTqcl.g:438:1: ( ruleNamedObj EOF )
            // InternalTqcl.g:439:1: ruleNamedObj EOF
            {
             before(grammarAccess.getNamedObjRule()); 
            pushFollow(FOLLOW_1);
            ruleNamedObj();

            state._fsp--;

             after(grammarAccess.getNamedObjRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNamedObj"


    // $ANTLR start "ruleNamedObj"
    // InternalTqcl.g:446:1: ruleNamedObj : ( ( rule__NamedObj__Alternatives ) ) ;
    public final void ruleNamedObj() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:450:2: ( ( ( rule__NamedObj__Alternatives ) ) )
            // InternalTqcl.g:451:2: ( ( rule__NamedObj__Alternatives ) )
            {
            // InternalTqcl.g:451:2: ( ( rule__NamedObj__Alternatives ) )
            // InternalTqcl.g:452:3: ( rule__NamedObj__Alternatives )
            {
             before(grammarAccess.getNamedObjAccess().getAlternatives()); 
            // InternalTqcl.g:453:3: ( rule__NamedObj__Alternatives )
            // InternalTqcl.g:453:4: rule__NamedObj__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__NamedObj__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getNamedObjAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNamedObj"


    // $ANTLR start "ruleCategory"
    // InternalTqcl.g:462:1: ruleCategory : ( ( rule__Category__Alternatives ) ) ;
    public final void ruleCategory() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:466:1: ( ( ( rule__Category__Alternatives ) ) )
            // InternalTqcl.g:467:2: ( ( rule__Category__Alternatives ) )
            {
            // InternalTqcl.g:467:2: ( ( rule__Category__Alternatives ) )
            // InternalTqcl.g:468:3: ( rule__Category__Alternatives )
            {
             before(grammarAccess.getCategoryAccess().getAlternatives()); 
            // InternalTqcl.g:469:3: ( rule__Category__Alternatives )
            // InternalTqcl.g:469:4: rule__Category__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Category__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getCategoryAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCategory"


    // $ANTLR start "rule__CompositeCommand__EndAlternatives_2_0"
    // InternalTqcl.g:477:1: rule__CompositeCommand__EndAlternatives_2_0 : ( ( ruleGoOut ) | ( ruleGoTop ) );
    public final void rule__CompositeCommand__EndAlternatives_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:481:1: ( ( ruleGoOut ) | ( ruleGoTop ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==28) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==32) ) {
                    alt1=2;
                }
                else if ( (LA1_1==31) ) {
                    alt1=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalTqcl.g:482:2: ( ruleGoOut )
                    {
                    // InternalTqcl.g:482:2: ( ruleGoOut )
                    // InternalTqcl.g:483:3: ruleGoOut
                    {
                     before(grammarAccess.getCompositeCommandAccess().getEndGoOutParserRuleCall_2_0_0()); 
                    pushFollow(FOLLOW_2);
                    ruleGoOut();

                    state._fsp--;

                     after(grammarAccess.getCompositeCommandAccess().getEndGoOutParserRuleCall_2_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalTqcl.g:488:2: ( ruleGoTop )
                    {
                    // InternalTqcl.g:488:2: ( ruleGoTop )
                    // InternalTqcl.g:489:3: ruleGoTop
                    {
                     before(grammarAccess.getCompositeCommandAccess().getEndGoTopParserRuleCall_2_0_1()); 
                    pushFollow(FOLLOW_2);
                    ruleGoTop();

                    state._fsp--;

                     after(grammarAccess.getCompositeCommandAccess().getEndGoTopParserRuleCall_2_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompositeCommand__EndAlternatives_2_0"


    // $ANTLR start "rule__SimpleCommand__Alternatives_0"
    // InternalTqcl.g:498:1: rule__SimpleCommand__Alternatives_0 : ( ( ruleInsert ) | ( ruleConnect ) | ( ruleSet ) | ( ruleInclude ) );
    public final void rule__SimpleCommand__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:502:1: ( ( ruleInsert ) | ( ruleConnect ) | ( ruleSet ) | ( ruleInclude ) )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt2=1;
                }
                break;
            case 25:
                {
                alt2=2;
                }
                break;
            case 24:
                {
                alt2=3;
                }
                break;
            case 18:
                {
                alt2=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalTqcl.g:503:2: ( ruleInsert )
                    {
                    // InternalTqcl.g:503:2: ( ruleInsert )
                    // InternalTqcl.g:504:3: ruleInsert
                    {
                     before(grammarAccess.getSimpleCommandAccess().getInsertParserRuleCall_0_0()); 
                    pushFollow(FOLLOW_2);
                    ruleInsert();

                    state._fsp--;

                     after(grammarAccess.getSimpleCommandAccess().getInsertParserRuleCall_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalTqcl.g:509:2: ( ruleConnect )
                    {
                    // InternalTqcl.g:509:2: ( ruleConnect )
                    // InternalTqcl.g:510:3: ruleConnect
                    {
                     before(grammarAccess.getSimpleCommandAccess().getConnectParserRuleCall_0_1()); 
                    pushFollow(FOLLOW_2);
                    ruleConnect();

                    state._fsp--;

                     after(grammarAccess.getSimpleCommandAccess().getConnectParserRuleCall_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalTqcl.g:515:2: ( ruleSet )
                    {
                    // InternalTqcl.g:515:2: ( ruleSet )
                    // InternalTqcl.g:516:3: ruleSet
                    {
                     before(grammarAccess.getSimpleCommandAccess().getSetParserRuleCall_0_2()); 
                    pushFollow(FOLLOW_2);
                    ruleSet();

                    state._fsp--;

                     after(grammarAccess.getSimpleCommandAccess().getSetParserRuleCall_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalTqcl.g:521:2: ( ruleInclude )
                    {
                    // InternalTqcl.g:521:2: ( ruleInclude )
                    // InternalTqcl.g:522:3: ruleInclude
                    {
                     before(grammarAccess.getSimpleCommandAccess().getIncludeParserRuleCall_0_3()); 
                    pushFollow(FOLLOW_2);
                    ruleInclude();

                    state._fsp--;

                     after(grammarAccess.getSimpleCommandAccess().getIncludeParserRuleCall_0_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleCommand__Alternatives_0"


    // $ANTLR start "rule__Command__Alternatives"
    // InternalTqcl.g:531:1: rule__Command__Alternatives : ( ( ruleSimpleCommand ) | ( ruleCompositeCommand ) );
    public final void rule__Command__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:535:1: ( ( ruleSimpleCommand ) | ( ruleCompositeCommand ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==18||LA3_0==20||(LA3_0>=24 && LA3_0<=25)) ) {
                alt3=1;
            }
            else if ( (LA3_0==28) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalTqcl.g:536:2: ( ruleSimpleCommand )
                    {
                    // InternalTqcl.g:536:2: ( ruleSimpleCommand )
                    // InternalTqcl.g:537:3: ruleSimpleCommand
                    {
                     before(grammarAccess.getCommandAccess().getSimpleCommandParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleSimpleCommand();

                    state._fsp--;

                     after(grammarAccess.getCommandAccess().getSimpleCommandParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalTqcl.g:542:2: ( ruleCompositeCommand )
                    {
                    // InternalTqcl.g:542:2: ( ruleCompositeCommand )
                    // InternalTqcl.g:543:3: ruleCompositeCommand
                    {
                     before(grammarAccess.getCommandAccess().getCompositeCommandParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleCompositeCommand();

                    state._fsp--;

                     after(grammarAccess.getCommandAccess().getCompositeCommandParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Command__Alternatives"


    // $ANTLR start "rule__Parameter_id__Alternatives"
    // InternalTqcl.g:552:1: rule__Parameter_id__Alternatives : ( ( RULE_PARAMETER_NAME ) | ( RULE_STRING ) );
    public final void rule__Parameter_id__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:556:1: ( ( RULE_PARAMETER_NAME ) | ( RULE_STRING ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_PARAMETER_NAME) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_STRING) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalTqcl.g:557:2: ( RULE_PARAMETER_NAME )
                    {
                    // InternalTqcl.g:557:2: ( RULE_PARAMETER_NAME )
                    // InternalTqcl.g:558:3: RULE_PARAMETER_NAME
                    {
                     before(grammarAccess.getParameter_idAccess().getPARAMETER_NAMETerminalRuleCall_0()); 
                    match(input,RULE_PARAMETER_NAME,FOLLOW_2); 
                     after(grammarAccess.getParameter_idAccess().getPARAMETER_NAMETerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalTqcl.g:563:2: ( RULE_STRING )
                    {
                    // InternalTqcl.g:563:2: ( RULE_STRING )
                    // InternalTqcl.g:564:3: RULE_STRING
                    {
                     before(grammarAccess.getParameter_idAccess().getSTRINGTerminalRuleCall_1()); 
                    match(input,RULE_STRING,FOLLOW_2); 
                     after(grammarAccess.getParameter_idAccess().getSTRINGTerminalRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter_id__Alternatives"


    // $ANTLR start "rule__NamedObj__Alternatives"
    // InternalTqcl.g:573:1: rule__NamedObj__Alternatives : ( ( RULE_ID ) | ( RULE_STRING ) );
    public final void rule__NamedObj__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:577:1: ( ( RULE_ID ) | ( RULE_STRING ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ID) ) {
                alt5=1;
            }
            else if ( (LA5_0==RULE_STRING) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalTqcl.g:578:2: ( RULE_ID )
                    {
                    // InternalTqcl.g:578:2: ( RULE_ID )
                    // InternalTqcl.g:579:3: RULE_ID
                    {
                     before(grammarAccess.getNamedObjAccess().getIDTerminalRuleCall_0()); 
                    match(input,RULE_ID,FOLLOW_2); 
                     after(grammarAccess.getNamedObjAccess().getIDTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalTqcl.g:584:2: ( RULE_STRING )
                    {
                    // InternalTqcl.g:584:2: ( RULE_STRING )
                    // InternalTqcl.g:585:3: RULE_STRING
                    {
                     before(grammarAccess.getNamedObjAccess().getSTRINGTerminalRuleCall_1()); 
                    match(input,RULE_STRING,FOLLOW_2); 
                     after(grammarAccess.getNamedObjAccess().getSTRINGTerminalRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NamedObj__Alternatives"


    // $ANTLR start "rule__Category__Alternatives"
    // InternalTqcl.g:594:1: rule__Category__Alternatives : ( ( ( 'actor' ) ) | ( ( 'parameter' ) ) | ( ( 'port' ) ) | ( ( 'director' ) ) );
    public final void rule__Category__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:598:1: ( ( ( 'actor' ) ) | ( ( 'parameter' ) ) | ( ( 'port' ) ) | ( ( 'director' ) ) )
            int alt6=4;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt6=1;
                }
                break;
            case 14:
                {
                alt6=2;
                }
                break;
            case 15:
                {
                alt6=3;
                }
                break;
            case 16:
                {
                alt6=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // InternalTqcl.g:599:2: ( ( 'actor' ) )
                    {
                    // InternalTqcl.g:599:2: ( ( 'actor' ) )
                    // InternalTqcl.g:600:3: ( 'actor' )
                    {
                     before(grammarAccess.getCategoryAccess().getActorEnumLiteralDeclaration_0()); 
                    // InternalTqcl.g:601:3: ( 'actor' )
                    // InternalTqcl.g:601:4: 'actor'
                    {
                    match(input,13,FOLLOW_2); 

                    }

                     after(grammarAccess.getCategoryAccess().getActorEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalTqcl.g:605:2: ( ( 'parameter' ) )
                    {
                    // InternalTqcl.g:605:2: ( ( 'parameter' ) )
                    // InternalTqcl.g:606:3: ( 'parameter' )
                    {
                     before(grammarAccess.getCategoryAccess().getParameterEnumLiteralDeclaration_1()); 
                    // InternalTqcl.g:607:3: ( 'parameter' )
                    // InternalTqcl.g:607:4: 'parameter'
                    {
                    match(input,14,FOLLOW_2); 

                    }

                     after(grammarAccess.getCategoryAccess().getParameterEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalTqcl.g:611:2: ( ( 'port' ) )
                    {
                    // InternalTqcl.g:611:2: ( ( 'port' ) )
                    // InternalTqcl.g:612:3: ( 'port' )
                    {
                     before(grammarAccess.getCategoryAccess().getPortEnumLiteralDeclaration_2()); 
                    // InternalTqcl.g:613:3: ( 'port' )
                    // InternalTqcl.g:613:4: 'port'
                    {
                    match(input,15,FOLLOW_2); 

                    }

                     after(grammarAccess.getCategoryAccess().getPortEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalTqcl.g:617:2: ( ( 'director' ) )
                    {
                    // InternalTqcl.g:617:2: ( ( 'director' ) )
                    // InternalTqcl.g:618:3: ( 'director' )
                    {
                     before(grammarAccess.getCategoryAccess().getDirectorEnumLiteralDeclaration_3()); 
                    // InternalTqcl.g:619:3: ( 'director' )
                    // InternalTqcl.g:619:4: 'director'
                    {
                    match(input,16,FOLLOW_2); 

                    }

                     after(grammarAccess.getCategoryAccess().getDirectorEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Category__Alternatives"


    // $ANTLR start "rule__TriquetrumScript__Group__0"
    // InternalTqcl.g:627:1: rule__TriquetrumScript__Group__0 : rule__TriquetrumScript__Group__0__Impl rule__TriquetrumScript__Group__1 ;
    public final void rule__TriquetrumScript__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:631:1: ( rule__TriquetrumScript__Group__0__Impl rule__TriquetrumScript__Group__1 )
            // InternalTqcl.g:632:2: rule__TriquetrumScript__Group__0__Impl rule__TriquetrumScript__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__TriquetrumScript__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TriquetrumScript__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TriquetrumScript__Group__0"


    // $ANTLR start "rule__TriquetrumScript__Group__0__Impl"
    // InternalTqcl.g:639:1: rule__TriquetrumScript__Group__0__Impl : ( ( rule__TriquetrumScript__LibrariesAssignment_0 )* ) ;
    public final void rule__TriquetrumScript__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:643:1: ( ( ( rule__TriquetrumScript__LibrariesAssignment_0 )* ) )
            // InternalTqcl.g:644:1: ( ( rule__TriquetrumScript__LibrariesAssignment_0 )* )
            {
            // InternalTqcl.g:644:1: ( ( rule__TriquetrumScript__LibrariesAssignment_0 )* )
            // InternalTqcl.g:645:2: ( rule__TriquetrumScript__LibrariesAssignment_0 )*
            {
             before(grammarAccess.getTriquetrumScriptAccess().getLibrariesAssignment_0()); 
            // InternalTqcl.g:646:2: ( rule__TriquetrumScript__LibrariesAssignment_0 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==19) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalTqcl.g:646:3: rule__TriquetrumScript__LibrariesAssignment_0
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__TriquetrumScript__LibrariesAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getTriquetrumScriptAccess().getLibrariesAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TriquetrumScript__Group__0__Impl"


    // $ANTLR start "rule__TriquetrumScript__Group__1"
    // InternalTqcl.g:654:1: rule__TriquetrumScript__Group__1 : rule__TriquetrumScript__Group__1__Impl ;
    public final void rule__TriquetrumScript__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:658:1: ( rule__TriquetrumScript__Group__1__Impl )
            // InternalTqcl.g:659:2: rule__TriquetrumScript__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__TriquetrumScript__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TriquetrumScript__Group__1"


    // $ANTLR start "rule__TriquetrumScript__Group__1__Impl"
    // InternalTqcl.g:665:1: rule__TriquetrumScript__Group__1__Impl : ( ( rule__TriquetrumScript__CommandsAssignment_1 )* ) ;
    public final void rule__TriquetrumScript__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:669:1: ( ( ( rule__TriquetrumScript__CommandsAssignment_1 )* ) )
            // InternalTqcl.g:670:1: ( ( rule__TriquetrumScript__CommandsAssignment_1 )* )
            {
            // InternalTqcl.g:670:1: ( ( rule__TriquetrumScript__CommandsAssignment_1 )* )
            // InternalTqcl.g:671:2: ( rule__TriquetrumScript__CommandsAssignment_1 )*
            {
             before(grammarAccess.getTriquetrumScriptAccess().getCommandsAssignment_1()); 
            // InternalTqcl.g:672:2: ( rule__TriquetrumScript__CommandsAssignment_1 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==18||LA8_0==20||(LA8_0>=24 && LA8_0<=25)||LA8_0==28) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalTqcl.g:672:3: rule__TriquetrumScript__CommandsAssignment_1
            	    {
            	    pushFollow(FOLLOW_5);
            	    rule__TriquetrumScript__CommandsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

             after(grammarAccess.getTriquetrumScriptAccess().getCommandsAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TriquetrumScript__Group__1__Impl"


    // $ANTLR start "rule__CompositeCommand__Group__0"
    // InternalTqcl.g:681:1: rule__CompositeCommand__Group__0 : rule__CompositeCommand__Group__0__Impl rule__CompositeCommand__Group__1 ;
    public final void rule__CompositeCommand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:685:1: ( rule__CompositeCommand__Group__0__Impl rule__CompositeCommand__Group__1 )
            // InternalTqcl.g:686:2: rule__CompositeCommand__Group__0__Impl rule__CompositeCommand__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__CompositeCommand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CompositeCommand__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompositeCommand__Group__0"


    // $ANTLR start "rule__CompositeCommand__Group__0__Impl"
    // InternalTqcl.g:693:1: rule__CompositeCommand__Group__0__Impl : ( ( rule__CompositeCommand__StartAssignment_0 ) ) ;
    public final void rule__CompositeCommand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:697:1: ( ( ( rule__CompositeCommand__StartAssignment_0 ) ) )
            // InternalTqcl.g:698:1: ( ( rule__CompositeCommand__StartAssignment_0 ) )
            {
            // InternalTqcl.g:698:1: ( ( rule__CompositeCommand__StartAssignment_0 ) )
            // InternalTqcl.g:699:2: ( rule__CompositeCommand__StartAssignment_0 )
            {
             before(grammarAccess.getCompositeCommandAccess().getStartAssignment_0()); 
            // InternalTqcl.g:700:2: ( rule__CompositeCommand__StartAssignment_0 )
            // InternalTqcl.g:700:3: rule__CompositeCommand__StartAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__CompositeCommand__StartAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getCompositeCommandAccess().getStartAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompositeCommand__Group__0__Impl"


    // $ANTLR start "rule__CompositeCommand__Group__1"
    // InternalTqcl.g:708:1: rule__CompositeCommand__Group__1 : rule__CompositeCommand__Group__1__Impl rule__CompositeCommand__Group__2 ;
    public final void rule__CompositeCommand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:712:1: ( rule__CompositeCommand__Group__1__Impl rule__CompositeCommand__Group__2 )
            // InternalTqcl.g:713:2: rule__CompositeCommand__Group__1__Impl rule__CompositeCommand__Group__2
            {
            pushFollow(FOLLOW_3);
            rule__CompositeCommand__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CompositeCommand__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompositeCommand__Group__1"


    // $ANTLR start "rule__CompositeCommand__Group__1__Impl"
    // InternalTqcl.g:720:1: rule__CompositeCommand__Group__1__Impl : ( ( rule__CompositeCommand__CommandsAssignment_1 )* ) ;
    public final void rule__CompositeCommand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:724:1: ( ( ( rule__CompositeCommand__CommandsAssignment_1 )* ) )
            // InternalTqcl.g:725:1: ( ( rule__CompositeCommand__CommandsAssignment_1 )* )
            {
            // InternalTqcl.g:725:1: ( ( rule__CompositeCommand__CommandsAssignment_1 )* )
            // InternalTqcl.g:726:2: ( rule__CompositeCommand__CommandsAssignment_1 )*
            {
             before(grammarAccess.getCompositeCommandAccess().getCommandsAssignment_1()); 
            // InternalTqcl.g:727:2: ( rule__CompositeCommand__CommandsAssignment_1 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==28) ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1==30) ) {
                        alt9=1;
                    }


                }
                else if ( (LA9_0==18||LA9_0==20||(LA9_0>=24 && LA9_0<=25)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalTqcl.g:727:3: rule__CompositeCommand__CommandsAssignment_1
            	    {
            	    pushFollow(FOLLOW_5);
            	    rule__CompositeCommand__CommandsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

             after(grammarAccess.getCompositeCommandAccess().getCommandsAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompositeCommand__Group__1__Impl"


    // $ANTLR start "rule__CompositeCommand__Group__2"
    // InternalTqcl.g:735:1: rule__CompositeCommand__Group__2 : rule__CompositeCommand__Group__2__Impl ;
    public final void rule__CompositeCommand__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:739:1: ( rule__CompositeCommand__Group__2__Impl )
            // InternalTqcl.g:740:2: rule__CompositeCommand__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CompositeCommand__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompositeCommand__Group__2"


    // $ANTLR start "rule__CompositeCommand__Group__2__Impl"
    // InternalTqcl.g:746:1: rule__CompositeCommand__Group__2__Impl : ( ( rule__CompositeCommand__EndAssignment_2 ) ) ;
    public final void rule__CompositeCommand__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:750:1: ( ( ( rule__CompositeCommand__EndAssignment_2 ) ) )
            // InternalTqcl.g:751:1: ( ( rule__CompositeCommand__EndAssignment_2 ) )
            {
            // InternalTqcl.g:751:1: ( ( rule__CompositeCommand__EndAssignment_2 ) )
            // InternalTqcl.g:752:2: ( rule__CompositeCommand__EndAssignment_2 )
            {
             before(grammarAccess.getCompositeCommandAccess().getEndAssignment_2()); 
            // InternalTqcl.g:753:2: ( rule__CompositeCommand__EndAssignment_2 )
            // InternalTqcl.g:753:3: rule__CompositeCommand__EndAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__CompositeCommand__EndAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getCompositeCommandAccess().getEndAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompositeCommand__Group__2__Impl"


    // $ANTLR start "rule__SimpleCommand__Group__0"
    // InternalTqcl.g:762:1: rule__SimpleCommand__Group__0 : rule__SimpleCommand__Group__0__Impl rule__SimpleCommand__Group__1 ;
    public final void rule__SimpleCommand__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:766:1: ( rule__SimpleCommand__Group__0__Impl rule__SimpleCommand__Group__1 )
            // InternalTqcl.g:767:2: rule__SimpleCommand__Group__0__Impl rule__SimpleCommand__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__SimpleCommand__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SimpleCommand__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleCommand__Group__0"


    // $ANTLR start "rule__SimpleCommand__Group__0__Impl"
    // InternalTqcl.g:774:1: rule__SimpleCommand__Group__0__Impl : ( ( rule__SimpleCommand__Alternatives_0 ) ) ;
    public final void rule__SimpleCommand__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:778:1: ( ( ( rule__SimpleCommand__Alternatives_0 ) ) )
            // InternalTqcl.g:779:1: ( ( rule__SimpleCommand__Alternatives_0 ) )
            {
            // InternalTqcl.g:779:1: ( ( rule__SimpleCommand__Alternatives_0 ) )
            // InternalTqcl.g:780:2: ( rule__SimpleCommand__Alternatives_0 )
            {
             before(grammarAccess.getSimpleCommandAccess().getAlternatives_0()); 
            // InternalTqcl.g:781:2: ( rule__SimpleCommand__Alternatives_0 )
            // InternalTqcl.g:781:3: rule__SimpleCommand__Alternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__SimpleCommand__Alternatives_0();

            state._fsp--;


            }

             after(grammarAccess.getSimpleCommandAccess().getAlternatives_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleCommand__Group__0__Impl"


    // $ANTLR start "rule__SimpleCommand__Group__1"
    // InternalTqcl.g:789:1: rule__SimpleCommand__Group__1 : rule__SimpleCommand__Group__1__Impl ;
    public final void rule__SimpleCommand__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:793:1: ( rule__SimpleCommand__Group__1__Impl )
            // InternalTqcl.g:794:2: rule__SimpleCommand__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SimpleCommand__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleCommand__Group__1"


    // $ANTLR start "rule__SimpleCommand__Group__1__Impl"
    // InternalTqcl.g:800:1: rule__SimpleCommand__Group__1__Impl : ( ';' ) ;
    public final void rule__SimpleCommand__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:804:1: ( ( ';' ) )
            // InternalTqcl.g:805:1: ( ';' )
            {
            // InternalTqcl.g:805:1: ( ';' )
            // InternalTqcl.g:806:2: ';'
            {
             before(grammarAccess.getSimpleCommandAccess().getSemicolonKeyword_1()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getSimpleCommandAccess().getSemicolonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SimpleCommand__Group__1__Impl"


    // $ANTLR start "rule__Include__Group__0"
    // InternalTqcl.g:816:1: rule__Include__Group__0 : rule__Include__Group__0__Impl rule__Include__Group__1 ;
    public final void rule__Include__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:820:1: ( rule__Include__Group__0__Impl rule__Include__Group__1 )
            // InternalTqcl.g:821:2: rule__Include__Group__0__Impl rule__Include__Group__1
            {
            pushFollow(FOLLOW_7);
            rule__Include__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Include__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__Group__0"


    // $ANTLR start "rule__Include__Group__0__Impl"
    // InternalTqcl.g:828:1: rule__Include__Group__0__Impl : ( 'include' ) ;
    public final void rule__Include__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:832:1: ( ( 'include' ) )
            // InternalTqcl.g:833:1: ( 'include' )
            {
            // InternalTqcl.g:833:1: ( 'include' )
            // InternalTqcl.g:834:2: 'include'
            {
             before(grammarAccess.getIncludeAccess().getIncludeKeyword_0()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getIncludeAccess().getIncludeKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__Group__0__Impl"


    // $ANTLR start "rule__Include__Group__1"
    // InternalTqcl.g:843:1: rule__Include__Group__1 : rule__Include__Group__1__Impl rule__Include__Group__2 ;
    public final void rule__Include__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:847:1: ( rule__Include__Group__1__Impl rule__Include__Group__2 )
            // InternalTqcl.g:848:2: rule__Include__Group__1__Impl rule__Include__Group__2
            {
            pushFollow(FOLLOW_6);
            rule__Include__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Include__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__Group__1"


    // $ANTLR start "rule__Include__Group__1__Impl"
    // InternalTqcl.g:855:1: rule__Include__Group__1__Impl : ( ( rule__Include__FilenameAssignment_1 ) ) ;
    public final void rule__Include__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:859:1: ( ( ( rule__Include__FilenameAssignment_1 ) ) )
            // InternalTqcl.g:860:1: ( ( rule__Include__FilenameAssignment_1 ) )
            {
            // InternalTqcl.g:860:1: ( ( rule__Include__FilenameAssignment_1 ) )
            // InternalTqcl.g:861:2: ( rule__Include__FilenameAssignment_1 )
            {
             before(grammarAccess.getIncludeAccess().getFilenameAssignment_1()); 
            // InternalTqcl.g:862:2: ( rule__Include__FilenameAssignment_1 )
            // InternalTqcl.g:862:3: rule__Include__FilenameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Include__FilenameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getIncludeAccess().getFilenameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__Group__1__Impl"


    // $ANTLR start "rule__Include__Group__2"
    // InternalTqcl.g:870:1: rule__Include__Group__2 : rule__Include__Group__2__Impl ;
    public final void rule__Include__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:874:1: ( rule__Include__Group__2__Impl )
            // InternalTqcl.g:875:2: rule__Include__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Include__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__Group__2"


    // $ANTLR start "rule__Include__Group__2__Impl"
    // InternalTqcl.g:881:1: rule__Include__Group__2__Impl : ( ';' ) ;
    public final void rule__Include__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:885:1: ( ( ';' ) )
            // InternalTqcl.g:886:1: ( ';' )
            {
            // InternalTqcl.g:886:1: ( ';' )
            // InternalTqcl.g:887:2: ';'
            {
             before(grammarAccess.getIncludeAccess().getSemicolonKeyword_2()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getIncludeAccess().getSemicolonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__Group__2__Impl"


    // $ANTLR start "rule__Library__Group__0"
    // InternalTqcl.g:897:1: rule__Library__Group__0 : rule__Library__Group__0__Impl rule__Library__Group__1 ;
    public final void rule__Library__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:901:1: ( rule__Library__Group__0__Impl rule__Library__Group__1 )
            // InternalTqcl.g:902:2: rule__Library__Group__0__Impl rule__Library__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__Library__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Library__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Library__Group__0"


    // $ANTLR start "rule__Library__Group__0__Impl"
    // InternalTqcl.g:909:1: rule__Library__Group__0__Impl : ( 'library' ) ;
    public final void rule__Library__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:913:1: ( ( 'library' ) )
            // InternalTqcl.g:914:1: ( 'library' )
            {
            // InternalTqcl.g:914:1: ( 'library' )
            // InternalTqcl.g:915:2: 'library'
            {
             before(grammarAccess.getLibraryAccess().getLibraryKeyword_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getLibraryAccess().getLibraryKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Library__Group__0__Impl"


    // $ANTLR start "rule__Library__Group__1"
    // InternalTqcl.g:924:1: rule__Library__Group__1 : rule__Library__Group__1__Impl rule__Library__Group__2 ;
    public final void rule__Library__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:928:1: ( rule__Library__Group__1__Impl rule__Library__Group__2 )
            // InternalTqcl.g:929:2: rule__Library__Group__1__Impl rule__Library__Group__2
            {
            pushFollow(FOLLOW_6);
            rule__Library__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Library__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Library__Group__1"


    // $ANTLR start "rule__Library__Group__1__Impl"
    // InternalTqcl.g:936:1: rule__Library__Group__1__Impl : ( ( rule__Library__NameAssignment_1 ) ) ;
    public final void rule__Library__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:940:1: ( ( ( rule__Library__NameAssignment_1 ) ) )
            // InternalTqcl.g:941:1: ( ( rule__Library__NameAssignment_1 ) )
            {
            // InternalTqcl.g:941:1: ( ( rule__Library__NameAssignment_1 ) )
            // InternalTqcl.g:942:2: ( rule__Library__NameAssignment_1 )
            {
             before(grammarAccess.getLibraryAccess().getNameAssignment_1()); 
            // InternalTqcl.g:943:2: ( rule__Library__NameAssignment_1 )
            // InternalTqcl.g:943:3: rule__Library__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Library__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getLibraryAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Library__Group__1__Impl"


    // $ANTLR start "rule__Library__Group__2"
    // InternalTqcl.g:951:1: rule__Library__Group__2 : rule__Library__Group__2__Impl ;
    public final void rule__Library__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:955:1: ( rule__Library__Group__2__Impl )
            // InternalTqcl.g:956:2: rule__Library__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Library__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Library__Group__2"


    // $ANTLR start "rule__Library__Group__2__Impl"
    // InternalTqcl.g:962:1: rule__Library__Group__2__Impl : ( ';' ) ;
    public final void rule__Library__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:966:1: ( ( ';' ) )
            // InternalTqcl.g:967:1: ( ';' )
            {
            // InternalTqcl.g:967:1: ( ';' )
            // InternalTqcl.g:968:2: ';'
            {
             before(grammarAccess.getLibraryAccess().getSemicolonKeyword_2()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getLibraryAccess().getSemicolonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Library__Group__2__Impl"


    // $ANTLR start "rule__Insert__Group__0"
    // InternalTqcl.g:978:1: rule__Insert__Group__0 : rule__Insert__Group__0__Impl rule__Insert__Group__1 ;
    public final void rule__Insert__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:982:1: ( rule__Insert__Group__0__Impl rule__Insert__Group__1 )
            // InternalTqcl.g:983:2: rule__Insert__Group__0__Impl rule__Insert__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__Insert__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Insert__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__0"


    // $ANTLR start "rule__Insert__Group__0__Impl"
    // InternalTqcl.g:990:1: rule__Insert__Group__0__Impl : ( 'insert' ) ;
    public final void rule__Insert__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:994:1: ( ( 'insert' ) )
            // InternalTqcl.g:995:1: ( 'insert' )
            {
            // InternalTqcl.g:995:1: ( 'insert' )
            // InternalTqcl.g:996:2: 'insert'
            {
             before(grammarAccess.getInsertAccess().getInsertKeyword_0()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getInsertAccess().getInsertKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__0__Impl"


    // $ANTLR start "rule__Insert__Group__1"
    // InternalTqcl.g:1005:1: rule__Insert__Group__1 : rule__Insert__Group__1__Impl rule__Insert__Group__2 ;
    public final void rule__Insert__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1009:1: ( rule__Insert__Group__1__Impl rule__Insert__Group__2 )
            // InternalTqcl.g:1010:2: rule__Insert__Group__1__Impl rule__Insert__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__Insert__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Insert__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__1"


    // $ANTLR start "rule__Insert__Group__1__Impl"
    // InternalTqcl.g:1017:1: rule__Insert__Group__1__Impl : ( ( rule__Insert__CategoryAssignment_1 )? ) ;
    public final void rule__Insert__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1021:1: ( ( ( rule__Insert__CategoryAssignment_1 )? ) )
            // InternalTqcl.g:1022:1: ( ( rule__Insert__CategoryAssignment_1 )? )
            {
            // InternalTqcl.g:1022:1: ( ( rule__Insert__CategoryAssignment_1 )? )
            // InternalTqcl.g:1023:2: ( rule__Insert__CategoryAssignment_1 )?
            {
             before(grammarAccess.getInsertAccess().getCategoryAssignment_1()); 
            // InternalTqcl.g:1024:2: ( rule__Insert__CategoryAssignment_1 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>=13 && LA10_0<=16)) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalTqcl.g:1024:3: rule__Insert__CategoryAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Insert__CategoryAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getInsertAccess().getCategoryAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__1__Impl"


    // $ANTLR start "rule__Insert__Group__2"
    // InternalTqcl.g:1032:1: rule__Insert__Group__2 : rule__Insert__Group__2__Impl rule__Insert__Group__3 ;
    public final void rule__Insert__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1036:1: ( rule__Insert__Group__2__Impl rule__Insert__Group__3 )
            // InternalTqcl.g:1037:2: rule__Insert__Group__2__Impl rule__Insert__Group__3
            {
            pushFollow(FOLLOW_10);
            rule__Insert__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Insert__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__2"


    // $ANTLR start "rule__Insert__Group__2__Impl"
    // InternalTqcl.g:1044:1: rule__Insert__Group__2__Impl : ( ( rule__Insert__EntityClassAssignment_2 ) ) ;
    public final void rule__Insert__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1048:1: ( ( ( rule__Insert__EntityClassAssignment_2 ) ) )
            // InternalTqcl.g:1049:1: ( ( rule__Insert__EntityClassAssignment_2 ) )
            {
            // InternalTqcl.g:1049:1: ( ( rule__Insert__EntityClassAssignment_2 ) )
            // InternalTqcl.g:1050:2: ( rule__Insert__EntityClassAssignment_2 )
            {
             before(grammarAccess.getInsertAccess().getEntityClassAssignment_2()); 
            // InternalTqcl.g:1051:2: ( rule__Insert__EntityClassAssignment_2 )
            // InternalTqcl.g:1051:3: rule__Insert__EntityClassAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Insert__EntityClassAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getInsertAccess().getEntityClassAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__2__Impl"


    // $ANTLR start "rule__Insert__Group__3"
    // InternalTqcl.g:1059:1: rule__Insert__Group__3 : rule__Insert__Group__3__Impl rule__Insert__Group__4 ;
    public final void rule__Insert__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1063:1: ( rule__Insert__Group__3__Impl rule__Insert__Group__4 )
            // InternalTqcl.g:1064:2: rule__Insert__Group__3__Impl rule__Insert__Group__4
            {
            pushFollow(FOLLOW_7);
            rule__Insert__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Insert__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__3"


    // $ANTLR start "rule__Insert__Group__3__Impl"
    // InternalTqcl.g:1071:1: rule__Insert__Group__3__Impl : ( 'as' ) ;
    public final void rule__Insert__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1075:1: ( ( 'as' ) )
            // InternalTqcl.g:1076:1: ( 'as' )
            {
            // InternalTqcl.g:1076:1: ( 'as' )
            // InternalTqcl.g:1077:2: 'as'
            {
             before(grammarAccess.getInsertAccess().getAsKeyword_3()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getInsertAccess().getAsKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__3__Impl"


    // $ANTLR start "rule__Insert__Group__4"
    // InternalTqcl.g:1086:1: rule__Insert__Group__4 : rule__Insert__Group__4__Impl rule__Insert__Group__5 ;
    public final void rule__Insert__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1090:1: ( rule__Insert__Group__4__Impl rule__Insert__Group__5 )
            // InternalTqcl.g:1091:2: rule__Insert__Group__4__Impl rule__Insert__Group__5
            {
            pushFollow(FOLLOW_11);
            rule__Insert__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Insert__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__4"


    // $ANTLR start "rule__Insert__Group__4__Impl"
    // InternalTqcl.g:1098:1: rule__Insert__Group__4__Impl : ( ( rule__Insert__NameAssignment_4 ) ) ;
    public final void rule__Insert__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1102:1: ( ( ( rule__Insert__NameAssignment_4 ) ) )
            // InternalTqcl.g:1103:1: ( ( rule__Insert__NameAssignment_4 ) )
            {
            // InternalTqcl.g:1103:1: ( ( rule__Insert__NameAssignment_4 ) )
            // InternalTqcl.g:1104:2: ( rule__Insert__NameAssignment_4 )
            {
             before(grammarAccess.getInsertAccess().getNameAssignment_4()); 
            // InternalTqcl.g:1105:2: ( rule__Insert__NameAssignment_4 )
            // InternalTqcl.g:1105:3: rule__Insert__NameAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__Insert__NameAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getInsertAccess().getNameAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__4__Impl"


    // $ANTLR start "rule__Insert__Group__5"
    // InternalTqcl.g:1113:1: rule__Insert__Group__5 : rule__Insert__Group__5__Impl ;
    public final void rule__Insert__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1117:1: ( rule__Insert__Group__5__Impl )
            // InternalTqcl.g:1118:2: rule__Insert__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Insert__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__5"


    // $ANTLR start "rule__Insert__Group__5__Impl"
    // InternalTqcl.g:1124:1: rule__Insert__Group__5__Impl : ( ( rule__Insert__Group_5__0 )? ) ;
    public final void rule__Insert__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1128:1: ( ( ( rule__Insert__Group_5__0 )? ) )
            // InternalTqcl.g:1129:1: ( ( rule__Insert__Group_5__0 )? )
            {
            // InternalTqcl.g:1129:1: ( ( rule__Insert__Group_5__0 )? )
            // InternalTqcl.g:1130:2: ( rule__Insert__Group_5__0 )?
            {
             before(grammarAccess.getInsertAccess().getGroup_5()); 
            // InternalTqcl.g:1131:2: ( rule__Insert__Group_5__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==22) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalTqcl.g:1131:3: rule__Insert__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Insert__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getInsertAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__5__Impl"


    // $ANTLR start "rule__Insert__Group_5__0"
    // InternalTqcl.g:1140:1: rule__Insert__Group_5__0 : rule__Insert__Group_5__0__Impl rule__Insert__Group_5__1 ;
    public final void rule__Insert__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1144:1: ( rule__Insert__Group_5__0__Impl rule__Insert__Group_5__1 )
            // InternalTqcl.g:1145:2: rule__Insert__Group_5__0__Impl rule__Insert__Group_5__1
            {
            pushFollow(FOLLOW_12);
            rule__Insert__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Insert__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_5__0"


    // $ANTLR start "rule__Insert__Group_5__0__Impl"
    // InternalTqcl.g:1152:1: rule__Insert__Group_5__0__Impl : ( 'with' ) ;
    public final void rule__Insert__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1156:1: ( ( 'with' ) )
            // InternalTqcl.g:1157:1: ( 'with' )
            {
            // InternalTqcl.g:1157:1: ( 'with' )
            // InternalTqcl.g:1158:2: 'with'
            {
             before(grammarAccess.getInsertAccess().getWithKeyword_5_0()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getInsertAccess().getWithKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_5__0__Impl"


    // $ANTLR start "rule__Insert__Group_5__1"
    // InternalTqcl.g:1167:1: rule__Insert__Group_5__1 : rule__Insert__Group_5__1__Impl rule__Insert__Group_5__2 ;
    public final void rule__Insert__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1171:1: ( rule__Insert__Group_5__1__Impl rule__Insert__Group_5__2 )
            // InternalTqcl.g:1172:2: rule__Insert__Group_5__1__Impl rule__Insert__Group_5__2
            {
            pushFollow(FOLLOW_13);
            rule__Insert__Group_5__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Insert__Group_5__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_5__1"


    // $ANTLR start "rule__Insert__Group_5__1__Impl"
    // InternalTqcl.g:1179:1: rule__Insert__Group_5__1__Impl : ( ( rule__Insert__ParametersAssignment_5_1 ) ) ;
    public final void rule__Insert__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1183:1: ( ( ( rule__Insert__ParametersAssignment_5_1 ) ) )
            // InternalTqcl.g:1184:1: ( ( rule__Insert__ParametersAssignment_5_1 ) )
            {
            // InternalTqcl.g:1184:1: ( ( rule__Insert__ParametersAssignment_5_1 ) )
            // InternalTqcl.g:1185:2: ( rule__Insert__ParametersAssignment_5_1 )
            {
             before(grammarAccess.getInsertAccess().getParametersAssignment_5_1()); 
            // InternalTqcl.g:1186:2: ( rule__Insert__ParametersAssignment_5_1 )
            // InternalTqcl.g:1186:3: rule__Insert__ParametersAssignment_5_1
            {
            pushFollow(FOLLOW_2);
            rule__Insert__ParametersAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getInsertAccess().getParametersAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_5__1__Impl"


    // $ANTLR start "rule__Insert__Group_5__2"
    // InternalTqcl.g:1194:1: rule__Insert__Group_5__2 : rule__Insert__Group_5__2__Impl ;
    public final void rule__Insert__Group_5__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1198:1: ( rule__Insert__Group_5__2__Impl )
            // InternalTqcl.g:1199:2: rule__Insert__Group_5__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Insert__Group_5__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_5__2"


    // $ANTLR start "rule__Insert__Group_5__2__Impl"
    // InternalTqcl.g:1205:1: rule__Insert__Group_5__2__Impl : ( ( rule__Insert__Group_5_2__0 )* ) ;
    public final void rule__Insert__Group_5__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1209:1: ( ( ( rule__Insert__Group_5_2__0 )* ) )
            // InternalTqcl.g:1210:1: ( ( rule__Insert__Group_5_2__0 )* )
            {
            // InternalTqcl.g:1210:1: ( ( rule__Insert__Group_5_2__0 )* )
            // InternalTqcl.g:1211:2: ( rule__Insert__Group_5_2__0 )*
            {
             before(grammarAccess.getInsertAccess().getGroup_5_2()); 
            // InternalTqcl.g:1212:2: ( rule__Insert__Group_5_2__0 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==23) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalTqcl.g:1212:3: rule__Insert__Group_5_2__0
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__Insert__Group_5_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getInsertAccess().getGroup_5_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_5__2__Impl"


    // $ANTLR start "rule__Insert__Group_5_2__0"
    // InternalTqcl.g:1221:1: rule__Insert__Group_5_2__0 : rule__Insert__Group_5_2__0__Impl rule__Insert__Group_5_2__1 ;
    public final void rule__Insert__Group_5_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1225:1: ( rule__Insert__Group_5_2__0__Impl rule__Insert__Group_5_2__1 )
            // InternalTqcl.g:1226:2: rule__Insert__Group_5_2__0__Impl rule__Insert__Group_5_2__1
            {
            pushFollow(FOLLOW_12);
            rule__Insert__Group_5_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Insert__Group_5_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_5_2__0"


    // $ANTLR start "rule__Insert__Group_5_2__0__Impl"
    // InternalTqcl.g:1233:1: rule__Insert__Group_5_2__0__Impl : ( ',' ) ;
    public final void rule__Insert__Group_5_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1237:1: ( ( ',' ) )
            // InternalTqcl.g:1238:1: ( ',' )
            {
            // InternalTqcl.g:1238:1: ( ',' )
            // InternalTqcl.g:1239:2: ','
            {
             before(grammarAccess.getInsertAccess().getCommaKeyword_5_2_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getInsertAccess().getCommaKeyword_5_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_5_2__0__Impl"


    // $ANTLR start "rule__Insert__Group_5_2__1"
    // InternalTqcl.g:1248:1: rule__Insert__Group_5_2__1 : rule__Insert__Group_5_2__1__Impl ;
    public final void rule__Insert__Group_5_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1252:1: ( rule__Insert__Group_5_2__1__Impl )
            // InternalTqcl.g:1253:2: rule__Insert__Group_5_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Insert__Group_5_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_5_2__1"


    // $ANTLR start "rule__Insert__Group_5_2__1__Impl"
    // InternalTqcl.g:1259:1: rule__Insert__Group_5_2__1__Impl : ( ( rule__Insert__ParametersAssignment_5_2_1 ) ) ;
    public final void rule__Insert__Group_5_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1263:1: ( ( ( rule__Insert__ParametersAssignment_5_2_1 ) ) )
            // InternalTqcl.g:1264:1: ( ( rule__Insert__ParametersAssignment_5_2_1 ) )
            {
            // InternalTqcl.g:1264:1: ( ( rule__Insert__ParametersAssignment_5_2_1 ) )
            // InternalTqcl.g:1265:2: ( rule__Insert__ParametersAssignment_5_2_1 )
            {
             before(grammarAccess.getInsertAccess().getParametersAssignment_5_2_1()); 
            // InternalTqcl.g:1266:2: ( rule__Insert__ParametersAssignment_5_2_1 )
            // InternalTqcl.g:1266:3: rule__Insert__ParametersAssignment_5_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Insert__ParametersAssignment_5_2_1();

            state._fsp--;


            }

             after(grammarAccess.getInsertAccess().getParametersAssignment_5_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_5_2__1__Impl"


    // $ANTLR start "rule__Set__Group__0"
    // InternalTqcl.g:1275:1: rule__Set__Group__0 : rule__Set__Group__0__Impl rule__Set__Group__1 ;
    public final void rule__Set__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1279:1: ( rule__Set__Group__0__Impl rule__Set__Group__1 )
            // InternalTqcl.g:1280:2: rule__Set__Group__0__Impl rule__Set__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__Set__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Set__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Set__Group__0"


    // $ANTLR start "rule__Set__Group__0__Impl"
    // InternalTqcl.g:1287:1: rule__Set__Group__0__Impl : ( 'set' ) ;
    public final void rule__Set__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1291:1: ( ( 'set' ) )
            // InternalTqcl.g:1292:1: ( 'set' )
            {
            // InternalTqcl.g:1292:1: ( 'set' )
            // InternalTqcl.g:1293:2: 'set'
            {
             before(grammarAccess.getSetAccess().getSetKeyword_0()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getSetAccess().getSetKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Set__Group__0__Impl"


    // $ANTLR start "rule__Set__Group__1"
    // InternalTqcl.g:1302:1: rule__Set__Group__1 : rule__Set__Group__1__Impl ;
    public final void rule__Set__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1306:1: ( rule__Set__Group__1__Impl )
            // InternalTqcl.g:1307:2: rule__Set__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Set__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Set__Group__1"


    // $ANTLR start "rule__Set__Group__1__Impl"
    // InternalTqcl.g:1313:1: rule__Set__Group__1__Impl : ( ( rule__Set__ParamAssignment_1 ) ) ;
    public final void rule__Set__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1317:1: ( ( ( rule__Set__ParamAssignment_1 ) ) )
            // InternalTqcl.g:1318:1: ( ( rule__Set__ParamAssignment_1 ) )
            {
            // InternalTqcl.g:1318:1: ( ( rule__Set__ParamAssignment_1 ) )
            // InternalTqcl.g:1319:2: ( rule__Set__ParamAssignment_1 )
            {
             before(grammarAccess.getSetAccess().getParamAssignment_1()); 
            // InternalTqcl.g:1320:2: ( rule__Set__ParamAssignment_1 )
            // InternalTqcl.g:1320:3: rule__Set__ParamAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Set__ParamAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getSetAccess().getParamAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Set__Group__1__Impl"


    // $ANTLR start "rule__Connect__Group__0"
    // InternalTqcl.g:1329:1: rule__Connect__Group__0 : rule__Connect__Group__0__Impl rule__Connect__Group__1 ;
    public final void rule__Connect__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1333:1: ( rule__Connect__Group__0__Impl rule__Connect__Group__1 )
            // InternalTqcl.g:1334:2: rule__Connect__Group__0__Impl rule__Connect__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__Connect__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Connect__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group__0"


    // $ANTLR start "rule__Connect__Group__0__Impl"
    // InternalTqcl.g:1341:1: rule__Connect__Group__0__Impl : ( 'connect' ) ;
    public final void rule__Connect__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1345:1: ( ( 'connect' ) )
            // InternalTqcl.g:1346:1: ( 'connect' )
            {
            // InternalTqcl.g:1346:1: ( 'connect' )
            // InternalTqcl.g:1347:2: 'connect'
            {
             before(grammarAccess.getConnectAccess().getConnectKeyword_0()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getConnectAccess().getConnectKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group__0__Impl"


    // $ANTLR start "rule__Connect__Group__1"
    // InternalTqcl.g:1356:1: rule__Connect__Group__1 : rule__Connect__Group__1__Impl rule__Connect__Group__2 ;
    public final void rule__Connect__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1360:1: ( rule__Connect__Group__1__Impl rule__Connect__Group__2 )
            // InternalTqcl.g:1361:2: rule__Connect__Group__1__Impl rule__Connect__Group__2
            {
            pushFollow(FOLLOW_15);
            rule__Connect__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Connect__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group__1"


    // $ANTLR start "rule__Connect__Group__1__Impl"
    // InternalTqcl.g:1368:1: rule__Connect__Group__1__Impl : ( ( rule__Connect__FromAssignment_1 ) ) ;
    public final void rule__Connect__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1372:1: ( ( ( rule__Connect__FromAssignment_1 ) ) )
            // InternalTqcl.g:1373:1: ( ( rule__Connect__FromAssignment_1 ) )
            {
            // InternalTqcl.g:1373:1: ( ( rule__Connect__FromAssignment_1 ) )
            // InternalTqcl.g:1374:2: ( rule__Connect__FromAssignment_1 )
            {
             before(grammarAccess.getConnectAccess().getFromAssignment_1()); 
            // InternalTqcl.g:1375:2: ( rule__Connect__FromAssignment_1 )
            // InternalTqcl.g:1375:3: rule__Connect__FromAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Connect__FromAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getConnectAccess().getFromAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group__1__Impl"


    // $ANTLR start "rule__Connect__Group__2"
    // InternalTqcl.g:1383:1: rule__Connect__Group__2 : rule__Connect__Group__2__Impl rule__Connect__Group__3 ;
    public final void rule__Connect__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1387:1: ( rule__Connect__Group__2__Impl rule__Connect__Group__3 )
            // InternalTqcl.g:1388:2: rule__Connect__Group__2__Impl rule__Connect__Group__3
            {
            pushFollow(FOLLOW_15);
            rule__Connect__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Connect__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group__2"


    // $ANTLR start "rule__Connect__Group__2__Impl"
    // InternalTqcl.g:1395:1: rule__Connect__Group__2__Impl : ( ( rule__Connect__Group_2__0 )* ) ;
    public final void rule__Connect__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1399:1: ( ( ( rule__Connect__Group_2__0 )* ) )
            // InternalTqcl.g:1400:1: ( ( rule__Connect__Group_2__0 )* )
            {
            // InternalTqcl.g:1400:1: ( ( rule__Connect__Group_2__0 )* )
            // InternalTqcl.g:1401:2: ( rule__Connect__Group_2__0 )*
            {
             before(grammarAccess.getConnectAccess().getGroup_2()); 
            // InternalTqcl.g:1402:2: ( rule__Connect__Group_2__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==23) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalTqcl.g:1402:3: rule__Connect__Group_2__0
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__Connect__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

             after(grammarAccess.getConnectAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group__2__Impl"


    // $ANTLR start "rule__Connect__Group__3"
    // InternalTqcl.g:1410:1: rule__Connect__Group__3 : rule__Connect__Group__3__Impl rule__Connect__Group__4 ;
    public final void rule__Connect__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1414:1: ( rule__Connect__Group__3__Impl rule__Connect__Group__4 )
            // InternalTqcl.g:1415:2: rule__Connect__Group__3__Impl rule__Connect__Group__4
            {
            pushFollow(FOLLOW_8);
            rule__Connect__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Connect__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group__3"


    // $ANTLR start "rule__Connect__Group__3__Impl"
    // InternalTqcl.g:1422:1: rule__Connect__Group__3__Impl : ( 'to' ) ;
    public final void rule__Connect__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1426:1: ( ( 'to' ) )
            // InternalTqcl.g:1427:1: ( 'to' )
            {
            // InternalTqcl.g:1427:1: ( 'to' )
            // InternalTqcl.g:1428:2: 'to'
            {
             before(grammarAccess.getConnectAccess().getToKeyword_3()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getConnectAccess().getToKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group__3__Impl"


    // $ANTLR start "rule__Connect__Group__4"
    // InternalTqcl.g:1437:1: rule__Connect__Group__4 : rule__Connect__Group__4__Impl rule__Connect__Group__5 ;
    public final void rule__Connect__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1441:1: ( rule__Connect__Group__4__Impl rule__Connect__Group__5 )
            // InternalTqcl.g:1442:2: rule__Connect__Group__4__Impl rule__Connect__Group__5
            {
            pushFollow(FOLLOW_13);
            rule__Connect__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Connect__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group__4"


    // $ANTLR start "rule__Connect__Group__4__Impl"
    // InternalTqcl.g:1449:1: rule__Connect__Group__4__Impl : ( ( rule__Connect__ToAssignment_4 ) ) ;
    public final void rule__Connect__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1453:1: ( ( ( rule__Connect__ToAssignment_4 ) ) )
            // InternalTqcl.g:1454:1: ( ( rule__Connect__ToAssignment_4 ) )
            {
            // InternalTqcl.g:1454:1: ( ( rule__Connect__ToAssignment_4 ) )
            // InternalTqcl.g:1455:2: ( rule__Connect__ToAssignment_4 )
            {
             before(grammarAccess.getConnectAccess().getToAssignment_4()); 
            // InternalTqcl.g:1456:2: ( rule__Connect__ToAssignment_4 )
            // InternalTqcl.g:1456:3: rule__Connect__ToAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__Connect__ToAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getConnectAccess().getToAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group__4__Impl"


    // $ANTLR start "rule__Connect__Group__5"
    // InternalTqcl.g:1464:1: rule__Connect__Group__5 : rule__Connect__Group__5__Impl ;
    public final void rule__Connect__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1468:1: ( rule__Connect__Group__5__Impl )
            // InternalTqcl.g:1469:2: rule__Connect__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Connect__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group__5"


    // $ANTLR start "rule__Connect__Group__5__Impl"
    // InternalTqcl.g:1475:1: rule__Connect__Group__5__Impl : ( ( rule__Connect__Group_5__0 )* ) ;
    public final void rule__Connect__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1479:1: ( ( ( rule__Connect__Group_5__0 )* ) )
            // InternalTqcl.g:1480:1: ( ( rule__Connect__Group_5__0 )* )
            {
            // InternalTqcl.g:1480:1: ( ( rule__Connect__Group_5__0 )* )
            // InternalTqcl.g:1481:2: ( rule__Connect__Group_5__0 )*
            {
             before(grammarAccess.getConnectAccess().getGroup_5()); 
            // InternalTqcl.g:1482:2: ( rule__Connect__Group_5__0 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==23) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalTqcl.g:1482:3: rule__Connect__Group_5__0
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__Connect__Group_5__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

             after(grammarAccess.getConnectAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group__5__Impl"


    // $ANTLR start "rule__Connect__Group_2__0"
    // InternalTqcl.g:1491:1: rule__Connect__Group_2__0 : rule__Connect__Group_2__0__Impl rule__Connect__Group_2__1 ;
    public final void rule__Connect__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1495:1: ( rule__Connect__Group_2__0__Impl rule__Connect__Group_2__1 )
            // InternalTqcl.g:1496:2: rule__Connect__Group_2__0__Impl rule__Connect__Group_2__1
            {
            pushFollow(FOLLOW_8);
            rule__Connect__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Connect__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group_2__0"


    // $ANTLR start "rule__Connect__Group_2__0__Impl"
    // InternalTqcl.g:1503:1: rule__Connect__Group_2__0__Impl : ( ',' ) ;
    public final void rule__Connect__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1507:1: ( ( ',' ) )
            // InternalTqcl.g:1508:1: ( ',' )
            {
            // InternalTqcl.g:1508:1: ( ',' )
            // InternalTqcl.g:1509:2: ','
            {
             before(grammarAccess.getConnectAccess().getCommaKeyword_2_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getConnectAccess().getCommaKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group_2__0__Impl"


    // $ANTLR start "rule__Connect__Group_2__1"
    // InternalTqcl.g:1518:1: rule__Connect__Group_2__1 : rule__Connect__Group_2__1__Impl ;
    public final void rule__Connect__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1522:1: ( rule__Connect__Group_2__1__Impl )
            // InternalTqcl.g:1523:2: rule__Connect__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Connect__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group_2__1"


    // $ANTLR start "rule__Connect__Group_2__1__Impl"
    // InternalTqcl.g:1529:1: rule__Connect__Group_2__1__Impl : ( ( rule__Connect__FromAssignment_2_1 ) ) ;
    public final void rule__Connect__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1533:1: ( ( ( rule__Connect__FromAssignment_2_1 ) ) )
            // InternalTqcl.g:1534:1: ( ( rule__Connect__FromAssignment_2_1 ) )
            {
            // InternalTqcl.g:1534:1: ( ( rule__Connect__FromAssignment_2_1 ) )
            // InternalTqcl.g:1535:2: ( rule__Connect__FromAssignment_2_1 )
            {
             before(grammarAccess.getConnectAccess().getFromAssignment_2_1()); 
            // InternalTqcl.g:1536:2: ( rule__Connect__FromAssignment_2_1 )
            // InternalTqcl.g:1536:3: rule__Connect__FromAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Connect__FromAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getConnectAccess().getFromAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group_2__1__Impl"


    // $ANTLR start "rule__Connect__Group_5__0"
    // InternalTqcl.g:1545:1: rule__Connect__Group_5__0 : rule__Connect__Group_5__0__Impl rule__Connect__Group_5__1 ;
    public final void rule__Connect__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1549:1: ( rule__Connect__Group_5__0__Impl rule__Connect__Group_5__1 )
            // InternalTqcl.g:1550:2: rule__Connect__Group_5__0__Impl rule__Connect__Group_5__1
            {
            pushFollow(FOLLOW_8);
            rule__Connect__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Connect__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group_5__0"


    // $ANTLR start "rule__Connect__Group_5__0__Impl"
    // InternalTqcl.g:1557:1: rule__Connect__Group_5__0__Impl : ( ',' ) ;
    public final void rule__Connect__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1561:1: ( ( ',' ) )
            // InternalTqcl.g:1562:1: ( ',' )
            {
            // InternalTqcl.g:1562:1: ( ',' )
            // InternalTqcl.g:1563:2: ','
            {
             before(grammarAccess.getConnectAccess().getCommaKeyword_5_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getConnectAccess().getCommaKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group_5__0__Impl"


    // $ANTLR start "rule__Connect__Group_5__1"
    // InternalTqcl.g:1572:1: rule__Connect__Group_5__1 : rule__Connect__Group_5__1__Impl ;
    public final void rule__Connect__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1576:1: ( rule__Connect__Group_5__1__Impl )
            // InternalTqcl.g:1577:2: rule__Connect__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Connect__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group_5__1"


    // $ANTLR start "rule__Connect__Group_5__1__Impl"
    // InternalTqcl.g:1583:1: rule__Connect__Group_5__1__Impl : ( ( rule__Connect__ToAssignment_5_1 ) ) ;
    public final void rule__Connect__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1587:1: ( ( ( rule__Connect__ToAssignment_5_1 ) ) )
            // InternalTqcl.g:1588:1: ( ( rule__Connect__ToAssignment_5_1 ) )
            {
            // InternalTqcl.g:1588:1: ( ( rule__Connect__ToAssignment_5_1 ) )
            // InternalTqcl.g:1589:2: ( rule__Connect__ToAssignment_5_1 )
            {
             before(grammarAccess.getConnectAccess().getToAssignment_5_1()); 
            // InternalTqcl.g:1590:2: ( rule__Connect__ToAssignment_5_1 )
            // InternalTqcl.g:1590:3: rule__Connect__ToAssignment_5_1
            {
            pushFollow(FOLLOW_2);
            rule__Connect__ToAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getConnectAccess().getToAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__Group_5__1__Impl"


    // $ANTLR start "rule__ConnectionPort__Group__0"
    // InternalTqcl.g:1599:1: rule__ConnectionPort__Group__0 : rule__ConnectionPort__Group__0__Impl rule__ConnectionPort__Group__1 ;
    public final void rule__ConnectionPort__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1603:1: ( rule__ConnectionPort__Group__0__Impl rule__ConnectionPort__Group__1 )
            // InternalTqcl.g:1604:2: rule__ConnectionPort__Group__0__Impl rule__ConnectionPort__Group__1
            {
            pushFollow(FOLLOW_16);
            rule__ConnectionPort__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConnectionPort__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectionPort__Group__0"


    // $ANTLR start "rule__ConnectionPort__Group__0__Impl"
    // InternalTqcl.g:1611:1: rule__ConnectionPort__Group__0__Impl : ( ( rule__ConnectionPort__ActorAssignment_0 ) ) ;
    public final void rule__ConnectionPort__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1615:1: ( ( ( rule__ConnectionPort__ActorAssignment_0 ) ) )
            // InternalTqcl.g:1616:1: ( ( rule__ConnectionPort__ActorAssignment_0 ) )
            {
            // InternalTqcl.g:1616:1: ( ( rule__ConnectionPort__ActorAssignment_0 ) )
            // InternalTqcl.g:1617:2: ( rule__ConnectionPort__ActorAssignment_0 )
            {
             before(grammarAccess.getConnectionPortAccess().getActorAssignment_0()); 
            // InternalTqcl.g:1618:2: ( rule__ConnectionPort__ActorAssignment_0 )
            // InternalTqcl.g:1618:3: rule__ConnectionPort__ActorAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__ConnectionPort__ActorAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getConnectionPortAccess().getActorAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectionPort__Group__0__Impl"


    // $ANTLR start "rule__ConnectionPort__Group__1"
    // InternalTqcl.g:1626:1: rule__ConnectionPort__Group__1 : rule__ConnectionPort__Group__1__Impl rule__ConnectionPort__Group__2 ;
    public final void rule__ConnectionPort__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1630:1: ( rule__ConnectionPort__Group__1__Impl rule__ConnectionPort__Group__2 )
            // InternalTqcl.g:1631:2: rule__ConnectionPort__Group__1__Impl rule__ConnectionPort__Group__2
            {
            pushFollow(FOLLOW_17);
            rule__ConnectionPort__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConnectionPort__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectionPort__Group__1"


    // $ANTLR start "rule__ConnectionPort__Group__1__Impl"
    // InternalTqcl.g:1638:1: rule__ConnectionPort__Group__1__Impl : ( '.' ) ;
    public final void rule__ConnectionPort__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1642:1: ( ( '.' ) )
            // InternalTqcl.g:1643:1: ( '.' )
            {
            // InternalTqcl.g:1643:1: ( '.' )
            // InternalTqcl.g:1644:2: '.'
            {
             before(grammarAccess.getConnectionPortAccess().getFullStopKeyword_1()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getConnectionPortAccess().getFullStopKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectionPort__Group__1__Impl"


    // $ANTLR start "rule__ConnectionPort__Group__2"
    // InternalTqcl.g:1653:1: rule__ConnectionPort__Group__2 : rule__ConnectionPort__Group__2__Impl ;
    public final void rule__ConnectionPort__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1657:1: ( rule__ConnectionPort__Group__2__Impl )
            // InternalTqcl.g:1658:2: rule__ConnectionPort__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ConnectionPort__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectionPort__Group__2"


    // $ANTLR start "rule__ConnectionPort__Group__2__Impl"
    // InternalTqcl.g:1664:1: rule__ConnectionPort__Group__2__Impl : ( ( rule__ConnectionPort__PortAssignment_2 ) ) ;
    public final void rule__ConnectionPort__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1668:1: ( ( ( rule__ConnectionPort__PortAssignment_2 ) ) )
            // InternalTqcl.g:1669:1: ( ( rule__ConnectionPort__PortAssignment_2 ) )
            {
            // InternalTqcl.g:1669:1: ( ( rule__ConnectionPort__PortAssignment_2 ) )
            // InternalTqcl.g:1670:2: ( rule__ConnectionPort__PortAssignment_2 )
            {
             before(grammarAccess.getConnectionPortAccess().getPortAssignment_2()); 
            // InternalTqcl.g:1671:2: ( rule__ConnectionPort__PortAssignment_2 )
            // InternalTqcl.g:1671:3: rule__ConnectionPort__PortAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ConnectionPort__PortAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getConnectionPortAccess().getPortAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectionPort__Group__2__Impl"


    // $ANTLR start "rule__GoInto__Group__0"
    // InternalTqcl.g:1680:1: rule__GoInto__Group__0 : rule__GoInto__Group__0__Impl rule__GoInto__Group__1 ;
    public final void rule__GoInto__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1684:1: ( rule__GoInto__Group__0__Impl rule__GoInto__Group__1 )
            // InternalTqcl.g:1685:2: rule__GoInto__Group__0__Impl rule__GoInto__Group__1
            {
            pushFollow(FOLLOW_18);
            rule__GoInto__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__GoInto__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoInto__Group__0"


    // $ANTLR start "rule__GoInto__Group__0__Impl"
    // InternalTqcl.g:1692:1: rule__GoInto__Group__0__Impl : ( 'go' ) ;
    public final void rule__GoInto__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1696:1: ( ( 'go' ) )
            // InternalTqcl.g:1697:1: ( 'go' )
            {
            // InternalTqcl.g:1697:1: ( 'go' )
            // InternalTqcl.g:1698:2: 'go'
            {
             before(grammarAccess.getGoIntoAccess().getGoKeyword_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getGoIntoAccess().getGoKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoInto__Group__0__Impl"


    // $ANTLR start "rule__GoInto__Group__1"
    // InternalTqcl.g:1707:1: rule__GoInto__Group__1 : rule__GoInto__Group__1__Impl rule__GoInto__Group__2 ;
    public final void rule__GoInto__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1711:1: ( rule__GoInto__Group__1__Impl rule__GoInto__Group__2 )
            // InternalTqcl.g:1712:2: rule__GoInto__Group__1__Impl rule__GoInto__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__GoInto__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__GoInto__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoInto__Group__1"


    // $ANTLR start "rule__GoInto__Group__1__Impl"
    // InternalTqcl.g:1719:1: rule__GoInto__Group__1__Impl : ( ( rule__GoInto__DirectionAssignment_1 ) ) ;
    public final void rule__GoInto__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1723:1: ( ( ( rule__GoInto__DirectionAssignment_1 ) ) )
            // InternalTqcl.g:1724:1: ( ( rule__GoInto__DirectionAssignment_1 ) )
            {
            // InternalTqcl.g:1724:1: ( ( rule__GoInto__DirectionAssignment_1 ) )
            // InternalTqcl.g:1725:2: ( rule__GoInto__DirectionAssignment_1 )
            {
             before(grammarAccess.getGoIntoAccess().getDirectionAssignment_1()); 
            // InternalTqcl.g:1726:2: ( rule__GoInto__DirectionAssignment_1 )
            // InternalTqcl.g:1726:3: rule__GoInto__DirectionAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__GoInto__DirectionAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getGoIntoAccess().getDirectionAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoInto__Group__1__Impl"


    // $ANTLR start "rule__GoInto__Group__2"
    // InternalTqcl.g:1734:1: rule__GoInto__Group__2 : rule__GoInto__Group__2__Impl ;
    public final void rule__GoInto__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1738:1: ( rule__GoInto__Group__2__Impl )
            // InternalTqcl.g:1739:2: rule__GoInto__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__GoInto__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoInto__Group__2"


    // $ANTLR start "rule__GoInto__Group__2__Impl"
    // InternalTqcl.g:1745:1: rule__GoInto__Group__2__Impl : ( ( rule__GoInto__ActorAssignment_2 ) ) ;
    public final void rule__GoInto__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1749:1: ( ( ( rule__GoInto__ActorAssignment_2 ) ) )
            // InternalTqcl.g:1750:1: ( ( rule__GoInto__ActorAssignment_2 ) )
            {
            // InternalTqcl.g:1750:1: ( ( rule__GoInto__ActorAssignment_2 ) )
            // InternalTqcl.g:1751:2: ( rule__GoInto__ActorAssignment_2 )
            {
             before(grammarAccess.getGoIntoAccess().getActorAssignment_2()); 
            // InternalTqcl.g:1752:2: ( rule__GoInto__ActorAssignment_2 )
            // InternalTqcl.g:1752:3: rule__GoInto__ActorAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__GoInto__ActorAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getGoIntoAccess().getActorAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoInto__Group__2__Impl"


    // $ANTLR start "rule__GoOut__Group__0"
    // InternalTqcl.g:1761:1: rule__GoOut__Group__0 : rule__GoOut__Group__0__Impl rule__GoOut__Group__1 ;
    public final void rule__GoOut__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1765:1: ( rule__GoOut__Group__0__Impl rule__GoOut__Group__1 )
            // InternalTqcl.g:1766:2: rule__GoOut__Group__0__Impl rule__GoOut__Group__1
            {
            pushFollow(FOLLOW_19);
            rule__GoOut__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__GoOut__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoOut__Group__0"


    // $ANTLR start "rule__GoOut__Group__0__Impl"
    // InternalTqcl.g:1773:1: rule__GoOut__Group__0__Impl : ( 'go' ) ;
    public final void rule__GoOut__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1777:1: ( ( 'go' ) )
            // InternalTqcl.g:1778:1: ( 'go' )
            {
            // InternalTqcl.g:1778:1: ( 'go' )
            // InternalTqcl.g:1779:2: 'go'
            {
             before(grammarAccess.getGoOutAccess().getGoKeyword_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getGoOutAccess().getGoKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoOut__Group__0__Impl"


    // $ANTLR start "rule__GoOut__Group__1"
    // InternalTqcl.g:1788:1: rule__GoOut__Group__1 : rule__GoOut__Group__1__Impl ;
    public final void rule__GoOut__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1792:1: ( rule__GoOut__Group__1__Impl )
            // InternalTqcl.g:1793:2: rule__GoOut__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__GoOut__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoOut__Group__1"


    // $ANTLR start "rule__GoOut__Group__1__Impl"
    // InternalTqcl.g:1799:1: rule__GoOut__Group__1__Impl : ( ( rule__GoOut__DirectionAssignment_1 ) ) ;
    public final void rule__GoOut__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1803:1: ( ( ( rule__GoOut__DirectionAssignment_1 ) ) )
            // InternalTqcl.g:1804:1: ( ( rule__GoOut__DirectionAssignment_1 ) )
            {
            // InternalTqcl.g:1804:1: ( ( rule__GoOut__DirectionAssignment_1 ) )
            // InternalTqcl.g:1805:2: ( rule__GoOut__DirectionAssignment_1 )
            {
             before(grammarAccess.getGoOutAccess().getDirectionAssignment_1()); 
            // InternalTqcl.g:1806:2: ( rule__GoOut__DirectionAssignment_1 )
            // InternalTqcl.g:1806:3: rule__GoOut__DirectionAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__GoOut__DirectionAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getGoOutAccess().getDirectionAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoOut__Group__1__Impl"


    // $ANTLR start "rule__GoTop__Group__0"
    // InternalTqcl.g:1815:1: rule__GoTop__Group__0 : rule__GoTop__Group__0__Impl rule__GoTop__Group__1 ;
    public final void rule__GoTop__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1819:1: ( rule__GoTop__Group__0__Impl rule__GoTop__Group__1 )
            // InternalTqcl.g:1820:2: rule__GoTop__Group__0__Impl rule__GoTop__Group__1
            {
            pushFollow(FOLLOW_20);
            rule__GoTop__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__GoTop__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoTop__Group__0"


    // $ANTLR start "rule__GoTop__Group__0__Impl"
    // InternalTqcl.g:1827:1: rule__GoTop__Group__0__Impl : ( 'go' ) ;
    public final void rule__GoTop__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1831:1: ( ( 'go' ) )
            // InternalTqcl.g:1832:1: ( 'go' )
            {
            // InternalTqcl.g:1832:1: ( 'go' )
            // InternalTqcl.g:1833:2: 'go'
            {
             before(grammarAccess.getGoTopAccess().getGoKeyword_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getGoTopAccess().getGoKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoTop__Group__0__Impl"


    // $ANTLR start "rule__GoTop__Group__1"
    // InternalTqcl.g:1842:1: rule__GoTop__Group__1 : rule__GoTop__Group__1__Impl ;
    public final void rule__GoTop__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1846:1: ( rule__GoTop__Group__1__Impl )
            // InternalTqcl.g:1847:2: rule__GoTop__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__GoTop__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoTop__Group__1"


    // $ANTLR start "rule__GoTop__Group__1__Impl"
    // InternalTqcl.g:1853:1: rule__GoTop__Group__1__Impl : ( ( rule__GoTop__DirectionAssignment_1 ) ) ;
    public final void rule__GoTop__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1857:1: ( ( ( rule__GoTop__DirectionAssignment_1 ) ) )
            // InternalTqcl.g:1858:1: ( ( rule__GoTop__DirectionAssignment_1 ) )
            {
            // InternalTqcl.g:1858:1: ( ( rule__GoTop__DirectionAssignment_1 ) )
            // InternalTqcl.g:1859:2: ( rule__GoTop__DirectionAssignment_1 )
            {
             before(grammarAccess.getGoTopAccess().getDirectionAssignment_1()); 
            // InternalTqcl.g:1860:2: ( rule__GoTop__DirectionAssignment_1 )
            // InternalTqcl.g:1860:3: rule__GoTop__DirectionAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__GoTop__DirectionAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getGoTopAccess().getDirectionAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoTop__Group__1__Impl"


    // $ANTLR start "rule__Parameter__Group__0"
    // InternalTqcl.g:1869:1: rule__Parameter__Group__0 : rule__Parameter__Group__0__Impl rule__Parameter__Group__1 ;
    public final void rule__Parameter__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1873:1: ( rule__Parameter__Group__0__Impl rule__Parameter__Group__1 )
            // InternalTqcl.g:1874:2: rule__Parameter__Group__0__Impl rule__Parameter__Group__1
            {
            pushFollow(FOLLOW_21);
            rule__Parameter__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Parameter__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group__0"


    // $ANTLR start "rule__Parameter__Group__0__Impl"
    // InternalTqcl.g:1881:1: rule__Parameter__Group__0__Impl : ( ( rule__Parameter__IdAssignment_0 ) ) ;
    public final void rule__Parameter__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1885:1: ( ( ( rule__Parameter__IdAssignment_0 ) ) )
            // InternalTqcl.g:1886:1: ( ( rule__Parameter__IdAssignment_0 ) )
            {
            // InternalTqcl.g:1886:1: ( ( rule__Parameter__IdAssignment_0 ) )
            // InternalTqcl.g:1887:2: ( rule__Parameter__IdAssignment_0 )
            {
             before(grammarAccess.getParameterAccess().getIdAssignment_0()); 
            // InternalTqcl.g:1888:2: ( rule__Parameter__IdAssignment_0 )
            // InternalTqcl.g:1888:3: rule__Parameter__IdAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Parameter__IdAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getParameterAccess().getIdAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group__0__Impl"


    // $ANTLR start "rule__Parameter__Group__1"
    // InternalTqcl.g:1896:1: rule__Parameter__Group__1 : rule__Parameter__Group__1__Impl rule__Parameter__Group__2 ;
    public final void rule__Parameter__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1900:1: ( rule__Parameter__Group__1__Impl rule__Parameter__Group__2 )
            // InternalTqcl.g:1901:2: rule__Parameter__Group__1__Impl rule__Parameter__Group__2
            {
            pushFollow(FOLLOW_7);
            rule__Parameter__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Parameter__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group__1"


    // $ANTLR start "rule__Parameter__Group__1__Impl"
    // InternalTqcl.g:1908:1: rule__Parameter__Group__1__Impl : ( '=' ) ;
    public final void rule__Parameter__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1912:1: ( ( '=' ) )
            // InternalTqcl.g:1913:1: ( '=' )
            {
            // InternalTqcl.g:1913:1: ( '=' )
            // InternalTqcl.g:1914:2: '='
            {
             before(grammarAccess.getParameterAccess().getEqualsSignKeyword_1()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getParameterAccess().getEqualsSignKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group__1__Impl"


    // $ANTLR start "rule__Parameter__Group__2"
    // InternalTqcl.g:1923:1: rule__Parameter__Group__2 : rule__Parameter__Group__2__Impl ;
    public final void rule__Parameter__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1927:1: ( rule__Parameter__Group__2__Impl )
            // InternalTqcl.g:1928:2: rule__Parameter__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Parameter__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group__2"


    // $ANTLR start "rule__Parameter__Group__2__Impl"
    // InternalTqcl.g:1934:1: rule__Parameter__Group__2__Impl : ( ( rule__Parameter__ValueAssignment_2 ) ) ;
    public final void rule__Parameter__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1938:1: ( ( ( rule__Parameter__ValueAssignment_2 ) ) )
            // InternalTqcl.g:1939:1: ( ( rule__Parameter__ValueAssignment_2 ) )
            {
            // InternalTqcl.g:1939:1: ( ( rule__Parameter__ValueAssignment_2 ) )
            // InternalTqcl.g:1940:2: ( rule__Parameter__ValueAssignment_2 )
            {
             before(grammarAccess.getParameterAccess().getValueAssignment_2()); 
            // InternalTqcl.g:1941:2: ( rule__Parameter__ValueAssignment_2 )
            // InternalTqcl.g:1941:3: rule__Parameter__ValueAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Parameter__ValueAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getParameterAccess().getValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group__2__Impl"


    // $ANTLR start "rule__TriquetrumScript__LibrariesAssignment_0"
    // InternalTqcl.g:1950:1: rule__TriquetrumScript__LibrariesAssignment_0 : ( ruleLibrary ) ;
    public final void rule__TriquetrumScript__LibrariesAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1954:1: ( ( ruleLibrary ) )
            // InternalTqcl.g:1955:2: ( ruleLibrary )
            {
            // InternalTqcl.g:1955:2: ( ruleLibrary )
            // InternalTqcl.g:1956:3: ruleLibrary
            {
             before(grammarAccess.getTriquetrumScriptAccess().getLibrariesLibraryParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleLibrary();

            state._fsp--;

             after(grammarAccess.getTriquetrumScriptAccess().getLibrariesLibraryParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TriquetrumScript__LibrariesAssignment_0"


    // $ANTLR start "rule__TriquetrumScript__CommandsAssignment_1"
    // InternalTqcl.g:1965:1: rule__TriquetrumScript__CommandsAssignment_1 : ( ruleCommand ) ;
    public final void rule__TriquetrumScript__CommandsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1969:1: ( ( ruleCommand ) )
            // InternalTqcl.g:1970:2: ( ruleCommand )
            {
            // InternalTqcl.g:1970:2: ( ruleCommand )
            // InternalTqcl.g:1971:3: ruleCommand
            {
             before(grammarAccess.getTriquetrumScriptAccess().getCommandsCommandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCommand();

            state._fsp--;

             after(grammarAccess.getTriquetrumScriptAccess().getCommandsCommandParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TriquetrumScript__CommandsAssignment_1"


    // $ANTLR start "rule__CompositeCommand__StartAssignment_0"
    // InternalTqcl.g:1980:1: rule__CompositeCommand__StartAssignment_0 : ( ruleGoInto ) ;
    public final void rule__CompositeCommand__StartAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1984:1: ( ( ruleGoInto ) )
            // InternalTqcl.g:1985:2: ( ruleGoInto )
            {
            // InternalTqcl.g:1985:2: ( ruleGoInto )
            // InternalTqcl.g:1986:3: ruleGoInto
            {
             before(grammarAccess.getCompositeCommandAccess().getStartGoIntoParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleGoInto();

            state._fsp--;

             after(grammarAccess.getCompositeCommandAccess().getStartGoIntoParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompositeCommand__StartAssignment_0"


    // $ANTLR start "rule__CompositeCommand__CommandsAssignment_1"
    // InternalTqcl.g:1995:1: rule__CompositeCommand__CommandsAssignment_1 : ( ruleCommand ) ;
    public final void rule__CompositeCommand__CommandsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:1999:1: ( ( ruleCommand ) )
            // InternalTqcl.g:2000:2: ( ruleCommand )
            {
            // InternalTqcl.g:2000:2: ( ruleCommand )
            // InternalTqcl.g:2001:3: ruleCommand
            {
             before(grammarAccess.getCompositeCommandAccess().getCommandsCommandParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCommand();

            state._fsp--;

             after(grammarAccess.getCompositeCommandAccess().getCommandsCommandParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompositeCommand__CommandsAssignment_1"


    // $ANTLR start "rule__CompositeCommand__EndAssignment_2"
    // InternalTqcl.g:2010:1: rule__CompositeCommand__EndAssignment_2 : ( ( rule__CompositeCommand__EndAlternatives_2_0 ) ) ;
    public final void rule__CompositeCommand__EndAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2014:1: ( ( ( rule__CompositeCommand__EndAlternatives_2_0 ) ) )
            // InternalTqcl.g:2015:2: ( ( rule__CompositeCommand__EndAlternatives_2_0 ) )
            {
            // InternalTqcl.g:2015:2: ( ( rule__CompositeCommand__EndAlternatives_2_0 ) )
            // InternalTqcl.g:2016:3: ( rule__CompositeCommand__EndAlternatives_2_0 )
            {
             before(grammarAccess.getCompositeCommandAccess().getEndAlternatives_2_0()); 
            // InternalTqcl.g:2017:3: ( rule__CompositeCommand__EndAlternatives_2_0 )
            // InternalTqcl.g:2017:4: rule__CompositeCommand__EndAlternatives_2_0
            {
            pushFollow(FOLLOW_2);
            rule__CompositeCommand__EndAlternatives_2_0();

            state._fsp--;


            }

             after(grammarAccess.getCompositeCommandAccess().getEndAlternatives_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompositeCommand__EndAssignment_2"


    // $ANTLR start "rule__Include__FilenameAssignment_1"
    // InternalTqcl.g:2025:1: rule__Include__FilenameAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Include__FilenameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2029:1: ( ( RULE_STRING ) )
            // InternalTqcl.g:2030:2: ( RULE_STRING )
            {
            // InternalTqcl.g:2030:2: ( RULE_STRING )
            // InternalTqcl.g:2031:3: RULE_STRING
            {
             before(grammarAccess.getIncludeAccess().getFilenameSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getIncludeAccess().getFilenameSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__FilenameAssignment_1"


    // $ANTLR start "rule__Library__NameAssignment_1"
    // InternalTqcl.g:2040:1: rule__Library__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Library__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2044:1: ( ( RULE_ID ) )
            // InternalTqcl.g:2045:2: ( RULE_ID )
            {
            // InternalTqcl.g:2045:2: ( RULE_ID )
            // InternalTqcl.g:2046:3: RULE_ID
            {
             before(grammarAccess.getLibraryAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getLibraryAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Library__NameAssignment_1"


    // $ANTLR start "rule__Insert__CategoryAssignment_1"
    // InternalTqcl.g:2055:1: rule__Insert__CategoryAssignment_1 : ( ruleCategory ) ;
    public final void rule__Insert__CategoryAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2059:1: ( ( ruleCategory ) )
            // InternalTqcl.g:2060:2: ( ruleCategory )
            {
            // InternalTqcl.g:2060:2: ( ruleCategory )
            // InternalTqcl.g:2061:3: ruleCategory
            {
             before(grammarAccess.getInsertAccess().getCategoryCategoryEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCategory();

            state._fsp--;

             after(grammarAccess.getInsertAccess().getCategoryCategoryEnumRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__CategoryAssignment_1"


    // $ANTLR start "rule__Insert__EntityClassAssignment_2"
    // InternalTqcl.g:2070:1: rule__Insert__EntityClassAssignment_2 : ( RULE_ENTITY_CLASS ) ;
    public final void rule__Insert__EntityClassAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2074:1: ( ( RULE_ENTITY_CLASS ) )
            // InternalTqcl.g:2075:2: ( RULE_ENTITY_CLASS )
            {
            // InternalTqcl.g:2075:2: ( RULE_ENTITY_CLASS )
            // InternalTqcl.g:2076:3: RULE_ENTITY_CLASS
            {
             before(grammarAccess.getInsertAccess().getEntityClassENTITY_CLASSTerminalRuleCall_2_0()); 
            match(input,RULE_ENTITY_CLASS,FOLLOW_2); 
             after(grammarAccess.getInsertAccess().getEntityClassENTITY_CLASSTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__EntityClassAssignment_2"


    // $ANTLR start "rule__Insert__NameAssignment_4"
    // InternalTqcl.g:2085:1: rule__Insert__NameAssignment_4 : ( RULE_STRING ) ;
    public final void rule__Insert__NameAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2089:1: ( ( RULE_STRING ) )
            // InternalTqcl.g:2090:2: ( RULE_STRING )
            {
            // InternalTqcl.g:2090:2: ( RULE_STRING )
            // InternalTqcl.g:2091:3: RULE_STRING
            {
             before(grammarAccess.getInsertAccess().getNameSTRINGTerminalRuleCall_4_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getInsertAccess().getNameSTRINGTerminalRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__NameAssignment_4"


    // $ANTLR start "rule__Insert__ParametersAssignment_5_1"
    // InternalTqcl.g:2100:1: rule__Insert__ParametersAssignment_5_1 : ( ruleParameter ) ;
    public final void rule__Insert__ParametersAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2104:1: ( ( ruleParameter ) )
            // InternalTqcl.g:2105:2: ( ruleParameter )
            {
            // InternalTqcl.g:2105:2: ( ruleParameter )
            // InternalTqcl.g:2106:3: ruleParameter
            {
             before(grammarAccess.getInsertAccess().getParametersParameterParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_2);
            ruleParameter();

            state._fsp--;

             after(grammarAccess.getInsertAccess().getParametersParameterParserRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__ParametersAssignment_5_1"


    // $ANTLR start "rule__Insert__ParametersAssignment_5_2_1"
    // InternalTqcl.g:2115:1: rule__Insert__ParametersAssignment_5_2_1 : ( ruleParameter ) ;
    public final void rule__Insert__ParametersAssignment_5_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2119:1: ( ( ruleParameter ) )
            // InternalTqcl.g:2120:2: ( ruleParameter )
            {
            // InternalTqcl.g:2120:2: ( ruleParameter )
            // InternalTqcl.g:2121:3: ruleParameter
            {
             before(grammarAccess.getInsertAccess().getParametersParameterParserRuleCall_5_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleParameter();

            state._fsp--;

             after(grammarAccess.getInsertAccess().getParametersParameterParserRuleCall_5_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__ParametersAssignment_5_2_1"


    // $ANTLR start "rule__Set__ParamAssignment_1"
    // InternalTqcl.g:2130:1: rule__Set__ParamAssignment_1 : ( ruleParameter ) ;
    public final void rule__Set__ParamAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2134:1: ( ( ruleParameter ) )
            // InternalTqcl.g:2135:2: ( ruleParameter )
            {
            // InternalTqcl.g:2135:2: ( ruleParameter )
            // InternalTqcl.g:2136:3: ruleParameter
            {
             before(grammarAccess.getSetAccess().getParamParameterParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleParameter();

            state._fsp--;

             after(grammarAccess.getSetAccess().getParamParameterParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Set__ParamAssignment_1"


    // $ANTLR start "rule__Connect__FromAssignment_1"
    // InternalTqcl.g:2145:1: rule__Connect__FromAssignment_1 : ( ruleConnectionPort ) ;
    public final void rule__Connect__FromAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2149:1: ( ( ruleConnectionPort ) )
            // InternalTqcl.g:2150:2: ( ruleConnectionPort )
            {
            // InternalTqcl.g:2150:2: ( ruleConnectionPort )
            // InternalTqcl.g:2151:3: ruleConnectionPort
            {
             before(grammarAccess.getConnectAccess().getFromConnectionPortParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleConnectionPort();

            state._fsp--;

             after(grammarAccess.getConnectAccess().getFromConnectionPortParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__FromAssignment_1"


    // $ANTLR start "rule__Connect__FromAssignment_2_1"
    // InternalTqcl.g:2160:1: rule__Connect__FromAssignment_2_1 : ( ruleConnectionPort ) ;
    public final void rule__Connect__FromAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2164:1: ( ( ruleConnectionPort ) )
            // InternalTqcl.g:2165:2: ( ruleConnectionPort )
            {
            // InternalTqcl.g:2165:2: ( ruleConnectionPort )
            // InternalTqcl.g:2166:3: ruleConnectionPort
            {
             before(grammarAccess.getConnectAccess().getFromConnectionPortParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleConnectionPort();

            state._fsp--;

             after(grammarAccess.getConnectAccess().getFromConnectionPortParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__FromAssignment_2_1"


    // $ANTLR start "rule__Connect__ToAssignment_4"
    // InternalTqcl.g:2175:1: rule__Connect__ToAssignment_4 : ( ruleConnectionPort ) ;
    public final void rule__Connect__ToAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2179:1: ( ( ruleConnectionPort ) )
            // InternalTqcl.g:2180:2: ( ruleConnectionPort )
            {
            // InternalTqcl.g:2180:2: ( ruleConnectionPort )
            // InternalTqcl.g:2181:3: ruleConnectionPort
            {
             before(grammarAccess.getConnectAccess().getToConnectionPortParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleConnectionPort();

            state._fsp--;

             after(grammarAccess.getConnectAccess().getToConnectionPortParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__ToAssignment_4"


    // $ANTLR start "rule__Connect__ToAssignment_5_1"
    // InternalTqcl.g:2190:1: rule__Connect__ToAssignment_5_1 : ( ruleConnectionPort ) ;
    public final void rule__Connect__ToAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2194:1: ( ( ruleConnectionPort ) )
            // InternalTqcl.g:2195:2: ( ruleConnectionPort )
            {
            // InternalTqcl.g:2195:2: ( ruleConnectionPort )
            // InternalTqcl.g:2196:3: ruleConnectionPort
            {
             before(grammarAccess.getConnectAccess().getToConnectionPortParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_2);
            ruleConnectionPort();

            state._fsp--;

             after(grammarAccess.getConnectAccess().getToConnectionPortParserRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Connect__ToAssignment_5_1"


    // $ANTLR start "rule__ConnectionPort__ActorAssignment_0"
    // InternalTqcl.g:2205:1: rule__ConnectionPort__ActorAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__ConnectionPort__ActorAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2209:1: ( ( ( RULE_ID ) ) )
            // InternalTqcl.g:2210:2: ( ( RULE_ID ) )
            {
            // InternalTqcl.g:2210:2: ( ( RULE_ID ) )
            // InternalTqcl.g:2211:3: ( RULE_ID )
            {
             before(grammarAccess.getConnectionPortAccess().getActorInsertCrossReference_0_0()); 
            // InternalTqcl.g:2212:3: ( RULE_ID )
            // InternalTqcl.g:2213:4: RULE_ID
            {
             before(grammarAccess.getConnectionPortAccess().getActorInsertIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getConnectionPortAccess().getActorInsertIDTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getConnectionPortAccess().getActorInsertCrossReference_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectionPort__ActorAssignment_0"


    // $ANTLR start "rule__ConnectionPort__PortAssignment_2"
    // InternalTqcl.g:2224:1: rule__ConnectionPort__PortAssignment_2 : ( ruleNamedObj ) ;
    public final void rule__ConnectionPort__PortAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2228:1: ( ( ruleNamedObj ) )
            // InternalTqcl.g:2229:2: ( ruleNamedObj )
            {
            // InternalTqcl.g:2229:2: ( ruleNamedObj )
            // InternalTqcl.g:2230:3: ruleNamedObj
            {
             before(grammarAccess.getConnectionPortAccess().getPortNamedObjParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNamedObj();

            state._fsp--;

             after(grammarAccess.getConnectionPortAccess().getPortNamedObjParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectionPort__PortAssignment_2"


    // $ANTLR start "rule__GoInto__DirectionAssignment_1"
    // InternalTqcl.g:2239:1: rule__GoInto__DirectionAssignment_1 : ( ( 'into' ) ) ;
    public final void rule__GoInto__DirectionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2243:1: ( ( ( 'into' ) ) )
            // InternalTqcl.g:2244:2: ( ( 'into' ) )
            {
            // InternalTqcl.g:2244:2: ( ( 'into' ) )
            // InternalTqcl.g:2245:3: ( 'into' )
            {
             before(grammarAccess.getGoIntoAccess().getDirectionIntoKeyword_1_0()); 
            // InternalTqcl.g:2246:3: ( 'into' )
            // InternalTqcl.g:2247:4: 'into'
            {
             before(grammarAccess.getGoIntoAccess().getDirectionIntoKeyword_1_0()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getGoIntoAccess().getDirectionIntoKeyword_1_0()); 

            }

             after(grammarAccess.getGoIntoAccess().getDirectionIntoKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoInto__DirectionAssignment_1"


    // $ANTLR start "rule__GoInto__ActorAssignment_2"
    // InternalTqcl.g:2258:1: rule__GoInto__ActorAssignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__GoInto__ActorAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2262:1: ( ( ( RULE_ID ) ) )
            // InternalTqcl.g:2263:2: ( ( RULE_ID ) )
            {
            // InternalTqcl.g:2263:2: ( ( RULE_ID ) )
            // InternalTqcl.g:2264:3: ( RULE_ID )
            {
             before(grammarAccess.getGoIntoAccess().getActorInsertCrossReference_2_0()); 
            // InternalTqcl.g:2265:3: ( RULE_ID )
            // InternalTqcl.g:2266:4: RULE_ID
            {
             before(grammarAccess.getGoIntoAccess().getActorInsertIDTerminalRuleCall_2_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getGoIntoAccess().getActorInsertIDTerminalRuleCall_2_0_1()); 

            }

             after(grammarAccess.getGoIntoAccess().getActorInsertCrossReference_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoInto__ActorAssignment_2"


    // $ANTLR start "rule__GoOut__DirectionAssignment_1"
    // InternalTqcl.g:2277:1: rule__GoOut__DirectionAssignment_1 : ( ( 'out' ) ) ;
    public final void rule__GoOut__DirectionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2281:1: ( ( ( 'out' ) ) )
            // InternalTqcl.g:2282:2: ( ( 'out' ) )
            {
            // InternalTqcl.g:2282:2: ( ( 'out' ) )
            // InternalTqcl.g:2283:3: ( 'out' )
            {
             before(grammarAccess.getGoOutAccess().getDirectionOutKeyword_1_0()); 
            // InternalTqcl.g:2284:3: ( 'out' )
            // InternalTqcl.g:2285:4: 'out'
            {
             before(grammarAccess.getGoOutAccess().getDirectionOutKeyword_1_0()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getGoOutAccess().getDirectionOutKeyword_1_0()); 

            }

             after(grammarAccess.getGoOutAccess().getDirectionOutKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoOut__DirectionAssignment_1"


    // $ANTLR start "rule__GoTop__DirectionAssignment_1"
    // InternalTqcl.g:2296:1: rule__GoTop__DirectionAssignment_1 : ( ( 'top' ) ) ;
    public final void rule__GoTop__DirectionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2300:1: ( ( ( 'top' ) ) )
            // InternalTqcl.g:2301:2: ( ( 'top' ) )
            {
            // InternalTqcl.g:2301:2: ( ( 'top' ) )
            // InternalTqcl.g:2302:3: ( 'top' )
            {
             before(grammarAccess.getGoTopAccess().getDirectionTopKeyword_1_0()); 
            // InternalTqcl.g:2303:3: ( 'top' )
            // InternalTqcl.g:2304:4: 'top'
            {
             before(grammarAccess.getGoTopAccess().getDirectionTopKeyword_1_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getGoTopAccess().getDirectionTopKeyword_1_0()); 

            }

             after(grammarAccess.getGoTopAccess().getDirectionTopKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GoTop__DirectionAssignment_1"


    // $ANTLR start "rule__Parameter__IdAssignment_0"
    // InternalTqcl.g:2315:1: rule__Parameter__IdAssignment_0 : ( ruleParameter_id ) ;
    public final void rule__Parameter__IdAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2319:1: ( ( ruleParameter_id ) )
            // InternalTqcl.g:2320:2: ( ruleParameter_id )
            {
            // InternalTqcl.g:2320:2: ( ruleParameter_id )
            // InternalTqcl.g:2321:3: ruleParameter_id
            {
             before(grammarAccess.getParameterAccess().getIdParameter_idParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleParameter_id();

            state._fsp--;

             after(grammarAccess.getParameterAccess().getIdParameter_idParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__IdAssignment_0"


    // $ANTLR start "rule__Parameter__ValueAssignment_2"
    // InternalTqcl.g:2330:1: rule__Parameter__ValueAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Parameter__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTqcl.g:2334:1: ( ( RULE_STRING ) )
            // InternalTqcl.g:2335:2: ( RULE_STRING )
            {
            // InternalTqcl.g:2335:2: ( RULE_STRING )
            // InternalTqcl.g:2336:3: RULE_STRING
            {
             before(grammarAccess.getParameterAccess().getValueSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getParameterAccess().getValueSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__ValueAssignment_2"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000013140000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000013140002L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x000000000001E080L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000000060L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000020000000L});

}