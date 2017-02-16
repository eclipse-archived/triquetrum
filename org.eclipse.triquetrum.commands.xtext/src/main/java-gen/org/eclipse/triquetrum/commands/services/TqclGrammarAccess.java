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
package org.eclipse.triquetrum.commands.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EnumLiteralDeclaration;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.common.services.TerminalsGrammarAccess;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractEnumRuleElementFinder;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class TqclGrammarAccess extends AbstractGrammarElementFinder {
	
	public class TriquetrumScriptElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.TriquetrumScript");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cLibrariesAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cLibrariesLibraryParserRuleCall_0_0 = (RuleCall)cLibrariesAssignment_0.eContents().get(0);
		private final Assignment cCommandsAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cCommandsCommandParserRuleCall_1_0 = (RuleCall)cCommandsAssignment_1.eContents().get(0);
		
		//TriquetrumScript:
		//	libraries+=Library*
		//	commands+=Command*;
		@Override public ParserRule getRule() { return rule; }
		
		//libraries+=Library* commands+=Command*
		public Group getGroup() { return cGroup; }
		
		//libraries+=Library*
		public Assignment getLibrariesAssignment_0() { return cLibrariesAssignment_0; }
		
		//Library
		public RuleCall getLibrariesLibraryParserRuleCall_0_0() { return cLibrariesLibraryParserRuleCall_0_0; }
		
		//commands+=Command*
		public Assignment getCommandsAssignment_1() { return cCommandsAssignment_1; }
		
		//Command
		public RuleCall getCommandsCommandParserRuleCall_1_0() { return cCommandsCommandParserRuleCall_1_0; }
	}
	public class CompositeCommandElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.CompositeCommand");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cStartAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cStartGoIntoParserRuleCall_0_0 = (RuleCall)cStartAssignment_0.eContents().get(0);
		private final Assignment cCommandsAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cCommandsCommandParserRuleCall_1_0 = (RuleCall)cCommandsAssignment_1.eContents().get(0);
		private final Assignment cEndAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final Alternatives cEndAlternatives_2_0 = (Alternatives)cEndAssignment_2.eContents().get(0);
		private final RuleCall cEndGoOutParserRuleCall_2_0_0 = (RuleCall)cEndAlternatives_2_0.eContents().get(0);
		private final RuleCall cEndGoTopParserRuleCall_2_0_1 = (RuleCall)cEndAlternatives_2_0.eContents().get(1);
		
		//CompositeCommand:
		//	start=GoInto commands+=Command* end=(GoOut | GoTop);
		@Override public ParserRule getRule() { return rule; }
		
		//start=GoInto commands+=Command* end=(GoOut | GoTop)
		public Group getGroup() { return cGroup; }
		
		//start=GoInto
		public Assignment getStartAssignment_0() { return cStartAssignment_0; }
		
		//GoInto
		public RuleCall getStartGoIntoParserRuleCall_0_0() { return cStartGoIntoParserRuleCall_0_0; }
		
		//commands+=Command*
		public Assignment getCommandsAssignment_1() { return cCommandsAssignment_1; }
		
		//Command
		public RuleCall getCommandsCommandParserRuleCall_1_0() { return cCommandsCommandParserRuleCall_1_0; }
		
		//end=(GoOut | GoTop)
		public Assignment getEndAssignment_2() { return cEndAssignment_2; }
		
		//(GoOut | GoTop)
		public Alternatives getEndAlternatives_2_0() { return cEndAlternatives_2_0; }
		
		//GoOut
		public RuleCall getEndGoOutParserRuleCall_2_0_0() { return cEndGoOutParserRuleCall_2_0_0; }
		
		//GoTop
		public RuleCall getEndGoTopParserRuleCall_2_0_1() { return cEndGoTopParserRuleCall_2_0_1; }
	}
	public class SimpleCommandElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.SimpleCommand");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Alternatives cAlternatives_0 = (Alternatives)cGroup.eContents().get(0);
		private final RuleCall cInsertParserRuleCall_0_0 = (RuleCall)cAlternatives_0.eContents().get(0);
		private final RuleCall cConnectParserRuleCall_0_1 = (RuleCall)cAlternatives_0.eContents().get(1);
		private final RuleCall cSetParserRuleCall_0_2 = (RuleCall)cAlternatives_0.eContents().get(2);
		private final RuleCall cIncludeParserRuleCall_0_3 = (RuleCall)cAlternatives_0.eContents().get(3);
		private final Keyword cSemicolonKeyword_1 = (Keyword)cGroup.eContents().get(1);
		
		//SimpleCommand Command:
		//	(Insert | Connect | Set | Include) ";"
		@Override public ParserRule getRule() { return rule; }
		
		//(Insert | Connect | Set | Include) ";"
		public Group getGroup() { return cGroup; }
		
		//(Insert | Connect | Set | Include)
		public Alternatives getAlternatives_0() { return cAlternatives_0; }
		
		//Insert
		public RuleCall getInsertParserRuleCall_0_0() { return cInsertParserRuleCall_0_0; }
		
		//Connect
		public RuleCall getConnectParserRuleCall_0_1() { return cConnectParserRuleCall_0_1; }
		
		//Set
		public RuleCall getSetParserRuleCall_0_2() { return cSetParserRuleCall_0_2; }
		
		//Include
		public RuleCall getIncludeParserRuleCall_0_3() { return cIncludeParserRuleCall_0_3; }
		
		//";"
		public Keyword getSemicolonKeyword_1() { return cSemicolonKeyword_1; }
	}
	public class CommandElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.Command");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cSimpleCommandParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cCompositeCommandParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//Command:
		//	SimpleCommand | CompositeCommand;
		@Override public ParserRule getRule() { return rule; }
		
		//SimpleCommand | CompositeCommand
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//SimpleCommand
		public RuleCall getSimpleCommandParserRuleCall_0() { return cSimpleCommandParserRuleCall_0; }
		
		//CompositeCommand
		public RuleCall getCompositeCommandParserRuleCall_1() { return cCompositeCommandParserRuleCall_1; }
	}
	public class IncludeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.Include");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cIncludeKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cFilenameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cFilenameSTRINGTerminalRuleCall_1_0 = (RuleCall)cFilenameAssignment_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_2 = (Keyword)cGroup.eContents().get(2);
		
		//Include:
		//	"include" filename=STRING ";";
		@Override public ParserRule getRule() { return rule; }
		
		//"include" filename=STRING ";"
		public Group getGroup() { return cGroup; }
		
		//"include"
		public Keyword getIncludeKeyword_0() { return cIncludeKeyword_0; }
		
		//filename=STRING
		public Assignment getFilenameAssignment_1() { return cFilenameAssignment_1; }
		
		//STRING
		public RuleCall getFilenameSTRINGTerminalRuleCall_1_0() { return cFilenameSTRINGTerminalRuleCall_1_0; }
		
		//";"
		public Keyword getSemicolonKeyword_2() { return cSemicolonKeyword_2; }
	}
	public class LibraryElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.Library");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cLibraryKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_2 = (Keyword)cGroup.eContents().get(2);
		
		//Library:
		//	"library" name=ID ";";
		@Override public ParserRule getRule() { return rule; }
		
		//"library" name=ID ";"
		public Group getGroup() { return cGroup; }
		
		//"library"
		public Keyword getLibraryKeyword_0() { return cLibraryKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//";"
		public Keyword getSemicolonKeyword_2() { return cSemicolonKeyword_2; }
	}
	public class InsertElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.Insert");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cInsertKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cCategoryAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cCategoryCategoryEnumRuleCall_1_0 = (RuleCall)cCategoryAssignment_1.eContents().get(0);
		private final Assignment cEntityClassAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cEntityClassENTITY_CLASSTerminalRuleCall_2_0 = (RuleCall)cEntityClassAssignment_2.eContents().get(0);
		private final Keyword cAsKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cNameAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cNameSTRINGTerminalRuleCall_4_0 = (RuleCall)cNameAssignment_4.eContents().get(0);
		private final Group cGroup_5 = (Group)cGroup.eContents().get(5);
		private final Keyword cWithKeyword_5_0 = (Keyword)cGroup_5.eContents().get(0);
		private final Assignment cParametersAssignment_5_1 = (Assignment)cGroup_5.eContents().get(1);
		private final RuleCall cParametersParameterParserRuleCall_5_1_0 = (RuleCall)cParametersAssignment_5_1.eContents().get(0);
		private final Group cGroup_5_2 = (Group)cGroup_5.eContents().get(2);
		private final Keyword cCommaKeyword_5_2_0 = (Keyword)cGroup_5_2.eContents().get(0);
		private final Assignment cParametersAssignment_5_2_1 = (Assignment)cGroup_5_2.eContents().get(1);
		private final RuleCall cParametersParameterParserRuleCall_5_2_1_0 = (RuleCall)cParametersAssignment_5_2_1.eContents().get(0);
		
		//Insert:
		//	"insert" category=Category? entityClass=ENTITY_CLASS 'as' name=STRING ("with" parameters+=Parameter (","
		//	parameters+=Parameter)*)?;
		@Override public ParserRule getRule() { return rule; }
		
		//"insert" category=Category? entityClass=ENTITY_CLASS 'as' name=STRING ("with" parameters+=Parameter (","
		//parameters+=Parameter)*)?
		public Group getGroup() { return cGroup; }
		
		//"insert"
		public Keyword getInsertKeyword_0() { return cInsertKeyword_0; }
		
		//category=Category?
		public Assignment getCategoryAssignment_1() { return cCategoryAssignment_1; }
		
		//Category
		public RuleCall getCategoryCategoryEnumRuleCall_1_0() { return cCategoryCategoryEnumRuleCall_1_0; }
		
		//entityClass=ENTITY_CLASS
		public Assignment getEntityClassAssignment_2() { return cEntityClassAssignment_2; }
		
		//ENTITY_CLASS
		public RuleCall getEntityClassENTITY_CLASSTerminalRuleCall_2_0() { return cEntityClassENTITY_CLASSTerminalRuleCall_2_0; }
		
		//'as'
		public Keyword getAsKeyword_3() { return cAsKeyword_3; }
		
		//name=STRING
		public Assignment getNameAssignment_4() { return cNameAssignment_4; }
		
		//STRING
		public RuleCall getNameSTRINGTerminalRuleCall_4_0() { return cNameSTRINGTerminalRuleCall_4_0; }
		
		//("with" parameters+=Parameter ("," parameters+=Parameter)*)?
		public Group getGroup_5() { return cGroup_5; }
		
		//"with"
		public Keyword getWithKeyword_5_0() { return cWithKeyword_5_0; }
		
		//parameters+=Parameter
		public Assignment getParametersAssignment_5_1() { return cParametersAssignment_5_1; }
		
		//Parameter
		public RuleCall getParametersParameterParserRuleCall_5_1_0() { return cParametersParameterParserRuleCall_5_1_0; }
		
		//("," parameters+=Parameter)*
		public Group getGroup_5_2() { return cGroup_5_2; }
		
		//","
		public Keyword getCommaKeyword_5_2_0() { return cCommaKeyword_5_2_0; }
		
		//parameters+=Parameter
		public Assignment getParametersAssignment_5_2_1() { return cParametersAssignment_5_2_1; }
		
		//Parameter
		public RuleCall getParametersParameterParserRuleCall_5_2_1_0() { return cParametersParameterParserRuleCall_5_2_1_0; }
	}
	public class SetElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.Set");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cSetKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cParamAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cParamParameterParserRuleCall_1_0 = (RuleCall)cParamAssignment_1.eContents().get(0);
		
		//Set:
		//	"set" param=Parameter;
		@Override public ParserRule getRule() { return rule; }
		
		//"set" param=Parameter
		public Group getGroup() { return cGroup; }
		
		//"set"
		public Keyword getSetKeyword_0() { return cSetKeyword_0; }
		
		//param=Parameter
		public Assignment getParamAssignment_1() { return cParamAssignment_1; }
		
		//Parameter
		public RuleCall getParamParameterParserRuleCall_1_0() { return cParamParameterParserRuleCall_1_0; }
	}
	public class ConnectElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.Connect");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cConnectKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cFromAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cFromConnectionPortParserRuleCall_1_0 = (RuleCall)cFromAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cCommaKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cFromAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cFromConnectionPortParserRuleCall_2_1_0 = (RuleCall)cFromAssignment_2_1.eContents().get(0);
		private final Keyword cToKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cToAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cToConnectionPortParserRuleCall_4_0 = (RuleCall)cToAssignment_4.eContents().get(0);
		private final Group cGroup_5 = (Group)cGroup.eContents().get(5);
		private final Keyword cCommaKeyword_5_0 = (Keyword)cGroup_5.eContents().get(0);
		private final Assignment cToAssignment_5_1 = (Assignment)cGroup_5.eContents().get(1);
		private final RuleCall cToConnectionPortParserRuleCall_5_1_0 = (RuleCall)cToAssignment_5_1.eContents().get(0);
		
		//Connect:
		//	"connect" from+=ConnectionPort (',' from+=ConnectionPort)* "to" to+=ConnectionPort (',' to+=ConnectionPort)*;
		@Override public ParserRule getRule() { return rule; }
		
		//"connect" from+=ConnectionPort (',' from+=ConnectionPort)* "to" to+=ConnectionPort (',' to+=ConnectionPort)*
		public Group getGroup() { return cGroup; }
		
		//"connect"
		public Keyword getConnectKeyword_0() { return cConnectKeyword_0; }
		
		//from+=ConnectionPort
		public Assignment getFromAssignment_1() { return cFromAssignment_1; }
		
		//ConnectionPort
		public RuleCall getFromConnectionPortParserRuleCall_1_0() { return cFromConnectionPortParserRuleCall_1_0; }
		
		//(',' from+=ConnectionPort)*
		public Group getGroup_2() { return cGroup_2; }
		
		//','
		public Keyword getCommaKeyword_2_0() { return cCommaKeyword_2_0; }
		
		//from+=ConnectionPort
		public Assignment getFromAssignment_2_1() { return cFromAssignment_2_1; }
		
		//ConnectionPort
		public RuleCall getFromConnectionPortParserRuleCall_2_1_0() { return cFromConnectionPortParserRuleCall_2_1_0; }
		
		//"to"
		public Keyword getToKeyword_3() { return cToKeyword_3; }
		
		//to+=ConnectionPort
		public Assignment getToAssignment_4() { return cToAssignment_4; }
		
		//ConnectionPort
		public RuleCall getToConnectionPortParserRuleCall_4_0() { return cToConnectionPortParserRuleCall_4_0; }
		
		//(',' to+=ConnectionPort)*
		public Group getGroup_5() { return cGroup_5; }
		
		//','
		public Keyword getCommaKeyword_5_0() { return cCommaKeyword_5_0; }
		
		//to+=ConnectionPort
		public Assignment getToAssignment_5_1() { return cToAssignment_5_1; }
		
		//ConnectionPort
		public RuleCall getToConnectionPortParserRuleCall_5_1_0() { return cToConnectionPortParserRuleCall_5_1_0; }
	}
	public class ConnectionPortElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.ConnectionPort");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cActorAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final CrossReference cActorInsertCrossReference_0_0 = (CrossReference)cActorAssignment_0.eContents().get(0);
		private final RuleCall cActorInsertIDTerminalRuleCall_0_0_1 = (RuleCall)cActorInsertCrossReference_0_0.eContents().get(1);
		private final Keyword cFullStopKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cPortAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cPortNamedObjParserRuleCall_2_0 = (RuleCall)cPortAssignment_2.eContents().get(0);
		
		//ConnectionPort:
		//	actor=[Insert] '.' port=NamedObj;
		@Override public ParserRule getRule() { return rule; }
		
		//actor=[Insert] '.' port=NamedObj
		public Group getGroup() { return cGroup; }
		
		//actor=[Insert]
		public Assignment getActorAssignment_0() { return cActorAssignment_0; }
		
		//[Insert]
		public CrossReference getActorInsertCrossReference_0_0() { return cActorInsertCrossReference_0_0; }
		
		//ID
		public RuleCall getActorInsertIDTerminalRuleCall_0_0_1() { return cActorInsertIDTerminalRuleCall_0_0_1; }
		
		//'.'
		public Keyword getFullStopKeyword_1() { return cFullStopKeyword_1; }
		
		//port=NamedObj
		public Assignment getPortAssignment_2() { return cPortAssignment_2; }
		
		//NamedObj
		public RuleCall getPortNamedObjParserRuleCall_2_0() { return cPortNamedObjParserRuleCall_2_0; }
	}
	public class GoIntoElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.GoInto");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cGoKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cDirectionAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final Keyword cDirectionIntoKeyword_1_0 = (Keyword)cDirectionAssignment_1.eContents().get(0);
		private final Assignment cActorAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final CrossReference cActorInsertCrossReference_2_0 = (CrossReference)cActorAssignment_2.eContents().get(0);
		private final RuleCall cActorInsertIDTerminalRuleCall_2_0_1 = (RuleCall)cActorInsertCrossReference_2_0.eContents().get(1);
		
		//GoInto Go:
		//	"go" direction="into" actor=[Insert]
		@Override public ParserRule getRule() { return rule; }
		
		//"go" direction="into" actor=[Insert]
		public Group getGroup() { return cGroup; }
		
		//"go"
		public Keyword getGoKeyword_0() { return cGoKeyword_0; }
		
		//direction="into"
		public Assignment getDirectionAssignment_1() { return cDirectionAssignment_1; }
		
		//"into"
		public Keyword getDirectionIntoKeyword_1_0() { return cDirectionIntoKeyword_1_0; }
		
		//actor=[Insert]
		public Assignment getActorAssignment_2() { return cActorAssignment_2; }
		
		//[Insert]
		public CrossReference getActorInsertCrossReference_2_0() { return cActorInsertCrossReference_2_0; }
		
		//ID
		public RuleCall getActorInsertIDTerminalRuleCall_2_0_1() { return cActorInsertIDTerminalRuleCall_2_0_1; }
	}
	public class GoOutElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.GoOut");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cGoKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cDirectionAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final Keyword cDirectionOutKeyword_1_0 = (Keyword)cDirectionAssignment_1.eContents().get(0);
		
		//GoOut Go:
		//	"go" direction="out"
		@Override public ParserRule getRule() { return rule; }
		
		//"go" direction="out"
		public Group getGroup() { return cGroup; }
		
		//"go"
		public Keyword getGoKeyword_0() { return cGoKeyword_0; }
		
		//direction="out"
		public Assignment getDirectionAssignment_1() { return cDirectionAssignment_1; }
		
		//"out"
		public Keyword getDirectionOutKeyword_1_0() { return cDirectionOutKeyword_1_0; }
	}
	public class GoTopElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.GoTop");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cGoKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cDirectionAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final Keyword cDirectionTopKeyword_1_0 = (Keyword)cDirectionAssignment_1.eContents().get(0);
		
		//GoTop Go:
		//	"go" direction="top"
		@Override public ParserRule getRule() { return rule; }
		
		//"go" direction="top"
		public Group getGroup() { return cGroup; }
		
		//"go"
		public Keyword getGoKeyword_0() { return cGoKeyword_0; }
		
		//direction="top"
		public Assignment getDirectionAssignment_1() { return cDirectionAssignment_1; }
		
		//"top"
		public Keyword getDirectionTopKeyword_1_0() { return cDirectionTopKeyword_1_0; }
	}
	public class ParameterElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.Parameter");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cIdAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cIdParameter_idParserRuleCall_0_0 = (RuleCall)cIdAssignment_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cValueAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cValueSTRINGTerminalRuleCall_2_0 = (RuleCall)cValueAssignment_2.eContents().get(0);
		
		//Parameter:
		//	id=Parameter_id "=" value=STRING;
		@Override public ParserRule getRule() { return rule; }
		
		//id=Parameter_id "=" value=STRING
		public Group getGroup() { return cGroup; }
		
		//id=Parameter_id
		public Assignment getIdAssignment_0() { return cIdAssignment_0; }
		
		//Parameter_id
		public RuleCall getIdParameter_idParserRuleCall_0_0() { return cIdParameter_idParserRuleCall_0_0; }
		
		//"="
		public Keyword getEqualsSignKeyword_1() { return cEqualsSignKeyword_1; }
		
		//value=STRING
		public Assignment getValueAssignment_2() { return cValueAssignment_2; }
		
		//STRING
		public RuleCall getValueSTRINGTerminalRuleCall_2_0() { return cValueSTRINGTerminalRuleCall_2_0; }
	}
	public class Parameter_idElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.Parameter_id");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cPARAMETER_NAMETerminalRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cSTRINGTerminalRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//Parameter_id:
		//	PARAMETER_NAME | STRING;
		@Override public ParserRule getRule() { return rule; }
		
		//PARAMETER_NAME | STRING
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//PARAMETER_NAME
		public RuleCall getPARAMETER_NAMETerminalRuleCall_0() { return cPARAMETER_NAMETerminalRuleCall_0; }
		
		//STRING
		public RuleCall getSTRINGTerminalRuleCall_1() { return cSTRINGTerminalRuleCall_1; }
	}
	public class NamedObjElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.NamedObj");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cIDTerminalRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cSTRINGTerminalRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//NamedObj:
		//	ID | STRING;
		@Override public ParserRule getRule() { return rule; }
		
		//ID | STRING
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//ID
		public RuleCall getIDTerminalRuleCall_0() { return cIDTerminalRuleCall_0; }
		
		//STRING
		public RuleCall getSTRINGTerminalRuleCall_1() { return cSTRINGTerminalRuleCall_1; }
	}
	
	public class CategoryElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.Category");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final EnumLiteralDeclaration cActorEnumLiteralDeclaration_0 = (EnumLiteralDeclaration)cAlternatives.eContents().get(0);
		private final Keyword cActorActorKeyword_0_0 = (Keyword)cActorEnumLiteralDeclaration_0.eContents().get(0);
		private final EnumLiteralDeclaration cParameterEnumLiteralDeclaration_1 = (EnumLiteralDeclaration)cAlternatives.eContents().get(1);
		private final Keyword cParameterParameterKeyword_1_0 = (Keyword)cParameterEnumLiteralDeclaration_1.eContents().get(0);
		private final EnumLiteralDeclaration cPortEnumLiteralDeclaration_2 = (EnumLiteralDeclaration)cAlternatives.eContents().get(2);
		private final Keyword cPortPortKeyword_2_0 = (Keyword)cPortEnumLiteralDeclaration_2.eContents().get(0);
		private final EnumLiteralDeclaration cDirectorEnumLiteralDeclaration_3 = (EnumLiteralDeclaration)cAlternatives.eContents().get(3);
		private final Keyword cDirectorDirectorKeyword_3_0 = (Keyword)cDirectorEnumLiteralDeclaration_3.eContents().get(0);
		
		//enum Category:
		//	actor | parameter | port | director;
		public EnumRule getRule() { return rule; }
		
		//actor | parameter | port | director
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//actor
		public EnumLiteralDeclaration getActorEnumLiteralDeclaration_0() { return cActorEnumLiteralDeclaration_0; }
		
		//"actor"
		public Keyword getActorActorKeyword_0_0() { return cActorActorKeyword_0_0; }
		
		//parameter
		public EnumLiteralDeclaration getParameterEnumLiteralDeclaration_1() { return cParameterEnumLiteralDeclaration_1; }
		
		//"parameter"
		public Keyword getParameterParameterKeyword_1_0() { return cParameterParameterKeyword_1_0; }
		
		//port
		public EnumLiteralDeclaration getPortEnumLiteralDeclaration_2() { return cPortEnumLiteralDeclaration_2; }
		
		//"port"
		public Keyword getPortPortKeyword_2_0() { return cPortPortKeyword_2_0; }
		
		//director
		public EnumLiteralDeclaration getDirectorEnumLiteralDeclaration_3() { return cDirectorEnumLiteralDeclaration_3; }
		
		//"director"
		public Keyword getDirectorDirectorKeyword_3_0() { return cDirectorDirectorKeyword_3_0; }
	}
	
	private final TriquetrumScriptElements pTriquetrumScript;
	private final CompositeCommandElements pCompositeCommand;
	private final SimpleCommandElements pSimpleCommand;
	private final CommandElements pCommand;
	private final IncludeElements pInclude;
	private final LibraryElements pLibrary;
	private final InsertElements pInsert;
	private final SetElements pSet;
	private final ConnectElements pConnect;
	private final ConnectionPortElements pConnectionPort;
	private final GoIntoElements pGoInto;
	private final GoOutElements pGoOut;
	private final GoTopElements pGoTop;
	private final CategoryElements eCategory;
	private final ParameterElements pParameter;
	private final Parameter_idElements pParameter_id;
	private final TerminalRule tPARAMETER_NAME;
	private final NamedObjElements pNamedObj;
	private final TerminalRule tENTITY_CLASS;
	
	private final Grammar grammar;
	
	private final TerminalsGrammarAccess gaTerminals;

	@Inject
	public TqclGrammarAccess(GrammarProvider grammarProvider,
			TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
		this.pTriquetrumScript = new TriquetrumScriptElements();
		this.pCompositeCommand = new CompositeCommandElements();
		this.pSimpleCommand = new SimpleCommandElements();
		this.pCommand = new CommandElements();
		this.pInclude = new IncludeElements();
		this.pLibrary = new LibraryElements();
		this.pInsert = new InsertElements();
		this.pSet = new SetElements();
		this.pConnect = new ConnectElements();
		this.pConnectionPort = new ConnectionPortElements();
		this.pGoInto = new GoIntoElements();
		this.pGoOut = new GoOutElements();
		this.pGoTop = new GoTopElements();
		this.eCategory = new CategoryElements();
		this.pParameter = new ParameterElements();
		this.pParameter_id = new Parameter_idElements();
		this.tPARAMETER_NAME = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.PARAMETER_NAME");
		this.pNamedObj = new NamedObjElements();
		this.tENTITY_CLASS = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.triquetrum.commands.Tqcl.ENTITY_CLASS");
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("org.eclipse.triquetrum.commands.Tqcl".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	
	
	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//TriquetrumScript:
	//	libraries+=Library*
	//	commands+=Command*;
	public TriquetrumScriptElements getTriquetrumScriptAccess() {
		return pTriquetrumScript;
	}
	
	public ParserRule getTriquetrumScriptRule() {
		return getTriquetrumScriptAccess().getRule();
	}
	
	//CompositeCommand:
	//	start=GoInto commands+=Command* end=(GoOut | GoTop);
	public CompositeCommandElements getCompositeCommandAccess() {
		return pCompositeCommand;
	}
	
	public ParserRule getCompositeCommandRule() {
		return getCompositeCommandAccess().getRule();
	}
	
	//SimpleCommand Command:
	//	(Insert | Connect | Set | Include) ";"
	public SimpleCommandElements getSimpleCommandAccess() {
		return pSimpleCommand;
	}
	
	public ParserRule getSimpleCommandRule() {
		return getSimpleCommandAccess().getRule();
	}
	
	//Command:
	//	SimpleCommand | CompositeCommand;
	public CommandElements getCommandAccess() {
		return pCommand;
	}
	
	public ParserRule getCommandRule() {
		return getCommandAccess().getRule();
	}
	
	//Include:
	//	"include" filename=STRING ";";
	public IncludeElements getIncludeAccess() {
		return pInclude;
	}
	
	public ParserRule getIncludeRule() {
		return getIncludeAccess().getRule();
	}
	
	//Library:
	//	"library" name=ID ";";
	public LibraryElements getLibraryAccess() {
		return pLibrary;
	}
	
	public ParserRule getLibraryRule() {
		return getLibraryAccess().getRule();
	}
	
	//Insert:
	//	"insert" category=Category? entityClass=ENTITY_CLASS 'as' name=STRING ("with" parameters+=Parameter (","
	//	parameters+=Parameter)*)?;
	public InsertElements getInsertAccess() {
		return pInsert;
	}
	
	public ParserRule getInsertRule() {
		return getInsertAccess().getRule();
	}
	
	//Set:
	//	"set" param=Parameter;
	public SetElements getSetAccess() {
		return pSet;
	}
	
	public ParserRule getSetRule() {
		return getSetAccess().getRule();
	}
	
	//Connect:
	//	"connect" from+=ConnectionPort (',' from+=ConnectionPort)* "to" to+=ConnectionPort (',' to+=ConnectionPort)*;
	public ConnectElements getConnectAccess() {
		return pConnect;
	}
	
	public ParserRule getConnectRule() {
		return getConnectAccess().getRule();
	}
	
	//ConnectionPort:
	//	actor=[Insert] '.' port=NamedObj;
	public ConnectionPortElements getConnectionPortAccess() {
		return pConnectionPort;
	}
	
	public ParserRule getConnectionPortRule() {
		return getConnectionPortAccess().getRule();
	}
	
	//GoInto Go:
	//	"go" direction="into" actor=[Insert]
	public GoIntoElements getGoIntoAccess() {
		return pGoInto;
	}
	
	public ParserRule getGoIntoRule() {
		return getGoIntoAccess().getRule();
	}
	
	//GoOut Go:
	//	"go" direction="out"
	public GoOutElements getGoOutAccess() {
		return pGoOut;
	}
	
	public ParserRule getGoOutRule() {
		return getGoOutAccess().getRule();
	}
	
	//GoTop Go:
	//	"go" direction="top"
	public GoTopElements getGoTopAccess() {
		return pGoTop;
	}
	
	public ParserRule getGoTopRule() {
		return getGoTopAccess().getRule();
	}
	
	//enum Category:
	//	actor | parameter | port | director;
	public CategoryElements getCategoryAccess() {
		return eCategory;
	}
	
	public EnumRule getCategoryRule() {
		return getCategoryAccess().getRule();
	}
	
	//Parameter:
	//	id=Parameter_id "=" value=STRING;
	public ParameterElements getParameterAccess() {
		return pParameter;
	}
	
	public ParserRule getParameterRule() {
		return getParameterAccess().getRule();
	}
	
	//Parameter_id:
	//	PARAMETER_NAME | STRING;
	public Parameter_idElements getParameter_idAccess() {
		return pParameter_id;
	}
	
	public ParserRule getParameter_idRule() {
		return getParameter_idAccess().getRule();
	}
	
	//terminal PARAMETER_NAME:
	//	'$' ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;
	public TerminalRule getPARAMETER_NAMERule() {
		return tPARAMETER_NAME;
	}
	
	//NamedObj:
	//	ID | STRING;
	public NamedObjElements getNamedObjAccess() {
		return pNamedObj;
	}
	
	public ParserRule getNamedObjRule() {
		return getNamedObjAccess().getRule();
	}
	
	//terminal ENTITY_CLASS:
	//	'<'->'>';
	public TerminalRule getENTITY_CLASSRule() {
		return tENTITY_CLASS;
	}
	
	//terminal ID:
	//	'^'? ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	}
	
	//terminal INT returns ecore::EInt:
	//	'0'..'9'+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	}
	
	//terminal STRING:
	//	'"' ('\\' . | !('\\' | '"'))* '"' |
	//	"'" ('\\' . | !('\\' | "'"))* "'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	}
	
	//terminal ML_COMMENT:
	//	'/ *'->'* /';
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	}
	
	//terminal SL_COMMENT:
	//	'//' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	}
	
	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	}
	
	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	}
}
