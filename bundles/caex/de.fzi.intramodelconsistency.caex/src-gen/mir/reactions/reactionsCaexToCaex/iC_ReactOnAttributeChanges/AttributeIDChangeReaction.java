package mir.reactions.reactionsCaexToCaex.iC_ReactOnAttributeChanges;

import CAEX.Attribute;
import CAEX.CAEXFactory;
import mir.routines.iC_ReactOnAttributeChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.feature.attribute.ReplaceSingleValuedEAttribute;

@SuppressWarnings("all")
class AttributeIDChangeReaction extends AbstractReactionRealization {
  private ReplaceSingleValuedEAttribute<Attribute, String> replaceChange;
  
  private int currentlyMatchedChange;
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    CAEX.Attribute affectedEObject = replaceChange.getAffectedEObject();
    EAttribute affectedFeature = replaceChange.getAffectedFeature();
    java.lang.String oldValue = replaceChange.getOldValue();
    java.lang.String newValue = replaceChange.getNewValue();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.routines.iC_ReactOnAttributeChanges.RoutinesFacade routinesFacade = new mir.routines.iC_ReactOnAttributeChanges.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsCaexToCaex.iC_ReactOnAttributeChanges.AttributeIDChangeReaction.ActionUserExecution userExecution = new mir.reactions.reactionsCaexToCaex.iC_ReactOnAttributeChanges.AttributeIDChangeReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(affectedEObject, affectedFeature, oldValue, newValue, routinesFacade);
    
    resetChanges();
  }
  
  private void resetChanges() {
    replaceChange = null;
    currentlyMatchedChange = 0;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (currentlyMatchedChange == 0) {
    	if (!matchReplaceChange(change)) {
    		resetChanges();
    		return false;
    	} else {
    		currentlyMatchedChange++;
    	}
    }
    
    return true;
  }
  
  private boolean matchReplaceChange(final EChange change) {
    if (change instanceof ReplaceSingleValuedEAttribute<?, ?>) {
    	ReplaceSingleValuedEAttribute<CAEX.Attribute, java.lang.String> _localTypedChange = (ReplaceSingleValuedEAttribute<CAEX.Attribute, java.lang.String>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof CAEX.Attribute)) {
    		return false;
    	}
    	if (!_localTypedChange.getAffectedFeature().getName().equals("iD")) {
    		return false;
    	}
    	if (_localTypedChange.isFromNonDefaultValue() && !(_localTypedChange.getOldValue() instanceof java.lang.String)) {
    		return false;
    	}
    	if (_localTypedChange.isToNonDefaultValue() && !(_localTypedChange.getNewValue() instanceof java.lang.String)) {
    		return false;
    	}
    	this.replaceChange = (ReplaceSingleValuedEAttribute<CAEX.Attribute, java.lang.String>) change;
    	return true;
    }
    
    return false;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final Attribute affectedEObject, final EAttribute affectedFeature, final String oldValue, final String newValue, @Extension final RoutinesFacade _routinesFacade) {
      final EStructuralFeature feature = CAEXFactory.eINSTANCE.createAttribute().eClass().getEStructuralFeature("iD");
      _routinesFacade.correctAttributeCloneFeatures(affectedEObject, feature, newValue);
    }
  }
}
