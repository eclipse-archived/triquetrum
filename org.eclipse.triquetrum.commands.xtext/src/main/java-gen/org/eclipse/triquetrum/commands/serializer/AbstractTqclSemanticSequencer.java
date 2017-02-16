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
package org.eclipse.triquetrum.commands.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.triquetrum.commands.services.TqclGrammarAccess;
import org.eclipse.triquetrum.commands.tqcl.CompositeCommand;
import org.eclipse.triquetrum.commands.tqcl.Connect;
import org.eclipse.triquetrum.commands.tqcl.ConnectionPort;
import org.eclipse.triquetrum.commands.tqcl.Go;
import org.eclipse.triquetrum.commands.tqcl.Include;
import org.eclipse.triquetrum.commands.tqcl.Insert;
import org.eclipse.triquetrum.commands.tqcl.Library;
import org.eclipse.triquetrum.commands.tqcl.TqclPackage;
import org.eclipse.triquetrum.commands.tqcl.TriquetrumScript;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public abstract class AbstractTqclSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private TqclGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == TqclPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case TqclPackage.COMPOSITE_COMMAND:
				sequence_CompositeCommand(context, (CompositeCommand) semanticObject); 
				return; 
			case TqclPackage.CONNECT:
				sequence_Connect(context, (Connect) semanticObject); 
				return; 
			case TqclPackage.CONNECTION_PORT:
				sequence_ConnectionPort(context, (ConnectionPort) semanticObject); 
				return; 
			case TqclPackage.GO:
				if (rule == grammarAccess.getGoIntoRule()) {
					sequence_GoInto(context, (Go) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getGoOutRule()) {
					sequence_GoOut(context, (Go) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getGoTopRule()) {
					sequence_GoTop(context, (Go) semanticObject); 
					return; 
				}
				else break;
			case TqclPackage.INCLUDE:
				sequence_Include(context, (Include) semanticObject); 
				return; 
			case TqclPackage.INSERT:
				sequence_Insert(context, (Insert) semanticObject); 
				return; 
			case TqclPackage.LIBRARY:
				sequence_Library(context, (Library) semanticObject); 
				return; 
			case TqclPackage.PARAMETER:
				sequence_Parameter(context, (org.eclipse.triquetrum.commands.tqcl.Parameter) semanticObject); 
				return; 
			case TqclPackage.SET:
				sequence_Set(context, (org.eclipse.triquetrum.commands.tqcl.Set) semanticObject); 
				return; 
			case TqclPackage.TRIQUETRUM_SCRIPT:
				sequence_TriquetrumScript(context, (TriquetrumScript) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Contexts:
	 *     CompositeCommand returns CompositeCommand
	 *     Command returns CompositeCommand
	 *
	 * Constraint:
	 *     (start=GoInto commands+=Command* (end=GoOut | end=GoTop))
	 */
	protected void sequence_CompositeCommand(ISerializationContext context, CompositeCommand semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     SimpleCommand returns Connect
	 *     Command returns Connect
	 *     Connect returns Connect
	 *
	 * Constraint:
	 *     (from+=ConnectionPort from+=ConnectionPort* to+=ConnectionPort to+=ConnectionPort*)
	 */
	protected void sequence_Connect(ISerializationContext context, Connect semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ConnectionPort returns ConnectionPort
	 *
	 * Constraint:
	 *     (actor=[Insert|ID] port=NamedObj)
	 */
	protected void sequence_ConnectionPort(ISerializationContext context, ConnectionPort semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, TqclPackage.Literals.CONNECTION_PORT__ACTOR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, TqclPackage.Literals.CONNECTION_PORT__ACTOR));
			if (transientValues.isValueTransient(semanticObject, TqclPackage.Literals.CONNECTION_PORT__PORT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, TqclPackage.Literals.CONNECTION_PORT__PORT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getConnectionPortAccess().getActorInsertIDTerminalRuleCall_0_0_1(), semanticObject.getActor());
		feeder.accept(grammarAccess.getConnectionPortAccess().getPortNamedObjParserRuleCall_2_0(), semanticObject.getPort());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     GoInto returns Go
	 *
	 * Constraint:
	 *     (direction='into' actor=[Insert|ID])
	 */
	protected void sequence_GoInto(ISerializationContext context, Go semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, TqclPackage.Literals.GO__DIRECTION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, TqclPackage.Literals.GO__DIRECTION));
			if (transientValues.isValueTransient(semanticObject, TqclPackage.Literals.GO__ACTOR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, TqclPackage.Literals.GO__ACTOR));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getGoIntoAccess().getDirectionIntoKeyword_1_0(), semanticObject.getDirection());
		feeder.accept(grammarAccess.getGoIntoAccess().getActorInsertIDTerminalRuleCall_2_0_1(), semanticObject.getActor());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     GoOut returns Go
	 *
	 * Constraint:
	 *     direction='out'
	 */
	protected void sequence_GoOut(ISerializationContext context, Go semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, TqclPackage.Literals.GO__DIRECTION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, TqclPackage.Literals.GO__DIRECTION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getGoOutAccess().getDirectionOutKeyword_1_0(), semanticObject.getDirection());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     GoTop returns Go
	 *
	 * Constraint:
	 *     direction='top'
	 */
	protected void sequence_GoTop(ISerializationContext context, Go semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, TqclPackage.Literals.GO__DIRECTION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, TqclPackage.Literals.GO__DIRECTION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getGoTopAccess().getDirectionTopKeyword_1_0(), semanticObject.getDirection());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     SimpleCommand returns Include
	 *     Command returns Include
	 *     Include returns Include
	 *
	 * Constraint:
	 *     filename=STRING
	 */
	protected void sequence_Include(ISerializationContext context, Include semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, TqclPackage.Literals.INCLUDE__FILENAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, TqclPackage.Literals.INCLUDE__FILENAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getIncludeAccess().getFilenameSTRINGTerminalRuleCall_1_0(), semanticObject.getFilename());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     SimpleCommand returns Insert
	 *     Command returns Insert
	 *     Insert returns Insert
	 *
	 * Constraint:
	 *     (category=Category? entityClass=ENTITY_CLASS name=STRING (parameters+=Parameter parameters+=Parameter*)?)
	 */
	protected void sequence_Insert(ISerializationContext context, Insert semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Library returns Library
	 *
	 * Constraint:
	 *     name=ID
	 */
	protected void sequence_Library(ISerializationContext context, Library semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, TqclPackage.Literals.LIBRARY__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, TqclPackage.Literals.LIBRARY__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getLibraryAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Parameter returns Parameter
	 *
	 * Constraint:
	 *     (id=Parameter_id value=STRING)
	 */
	protected void sequence_Parameter(ISerializationContext context, org.eclipse.triquetrum.commands.tqcl.Parameter semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, TqclPackage.Literals.PARAMETER__ID) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, TqclPackage.Literals.PARAMETER__ID));
			if (transientValues.isValueTransient(semanticObject, TqclPackage.Literals.PARAMETER__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, TqclPackage.Literals.PARAMETER__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getParameterAccess().getIdParameter_idParserRuleCall_0_0(), semanticObject.getId());
		feeder.accept(grammarAccess.getParameterAccess().getValueSTRINGTerminalRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     SimpleCommand returns Set
	 *     Command returns Set
	 *     Set returns Set
	 *
	 * Constraint:
	 *     param=Parameter
	 */
	protected void sequence_Set(ISerializationContext context, org.eclipse.triquetrum.commands.tqcl.Set semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, TqclPackage.Literals.SET__PARAM) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, TqclPackage.Literals.SET__PARAM));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getSetAccess().getParamParameterParserRuleCall_1_0(), semanticObject.getParam());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     TriquetrumScript returns TriquetrumScript
	 *
	 * Constraint:
	 *     ((libraries+=Library+ commands+=Command+) | commands+=Command+)?
	 */
	protected void sequence_TriquetrumScript(ISerializationContext context, TriquetrumScript semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}
