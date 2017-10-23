package mir.reactions.reactionsCaexToCaex.iC_ProtoCloneAddCorrespondences;

import CAEX.CAEXFile;
import mir.routines.iC_ProtoCloneAddCorrespondences.RoutinesFacade;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.feature.attribute.ReplaceSingleValuedEAttribute;

@SuppressWarnings("all")
class DisplayFileNameChangeReaction extends AbstractReactionRealization {
  private ReplaceSingleValuedEAttribute<CAEXFile, String> replaceChange;
  
  private int currentlyMatchedChange;
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    CAEX.CAEXFile affectedEObject = replaceChange.getAffectedEObject();
    EAttribute affectedFeature = replaceChange.getAffectedFeature();
    java.lang.String oldValue = replaceChange.getOldValue();
    java.lang.String newValue = replaceChange.getNewValue();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.routines.iC_ProtoCloneAddCorrespondences.RoutinesFacade routinesFacade = new mir.routines.iC_ProtoCloneAddCorrespondences.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsCaexToCaex.iC_ProtoCloneAddCorrespondences.DisplayFileNameChangeReaction.ActionUserExecution userExecution = new mir.reactions.reactionsCaexToCaex.iC_ProtoCloneAddCorrespondences.DisplayFileNameChangeReaction.ActionUserExecution(this.executionState, this);
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
    	ReplaceSingleValuedEAttribute<CAEX.CAEXFile, java.lang.String> _localTypedChange = (ReplaceSingleValuedEAttribute<CAEX.CAEXFile, java.lang.String>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof CAEX.CAEXFile)) {
    		return false;
    	}
    	if (!_localTypedChange.getAffectedFeature().getName().equals("fileName")) {
    		return false;
    	}
    	if (_localTypedChange.isFromNonDefaultValue() && !(_localTypedChange.getOldValue() instanceof java.lang.String)) {
    		return false;
    	}
    	if (_localTypedChange.isToNonDefaultValue() && !(_localTypedChange.getNewValue() instanceof java.lang.String)) {
    		return false;
    	}
    	this.replaceChange = (ReplaceSingleValuedEAttribute<CAEX.CAEXFile, java.lang.String>) change;
    	return true;
    }
    
    return false;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final CAEXFile affectedEObject, final EAttribute affectedFeature, final String oldValue, final String newValue, @Extension final RoutinesFacade _routinesFacade) {
      System.out.println((((("FileNameChanged from \'" + oldValue) + "\' to  \'") + newValue) + "\'"));
    }
  }
}
