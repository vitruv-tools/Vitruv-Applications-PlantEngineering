package mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges;

import CAEX.InternalElement;
import CAEX.SystemUnitClass;
import mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.feature.attribute.ReplaceSingleValuedEAttribute;

/**
 * * On Prototype name change also adjust refBaseSystemUnitClassPath of all correspondents
 */
@SuppressWarnings("all")
class NameChangedReaction extends AbstractReactionRealization {
  private ReplaceSingleValuedEAttribute<SystemUnitClass, String> replaceChange;
  
  private int currentlyMatchedChange;
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    CAEX.SystemUnitClass affectedEObject = replaceChange.getAffectedEObject();
    EAttribute affectedFeature = replaceChange.getAffectedFeature();
    java.lang.String oldValue = replaceChange.getOldValue();
    java.lang.String newValue = replaceChange.getNewValue();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade routinesFacade = new mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.NameChangedReaction.ActionUserExecution userExecution = new mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.NameChangedReaction.ActionUserExecution(this.executionState, this);
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
    	ReplaceSingleValuedEAttribute<CAEX.SystemUnitClass, java.lang.String> _localTypedChange = (ReplaceSingleValuedEAttribute<CAEX.SystemUnitClass, java.lang.String>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof CAEX.SystemUnitClass)) {
    		return false;
    	}
    	if (!_localTypedChange.getAffectedFeature().getName().equals("name")) {
    		return false;
    	}
    	if (_localTypedChange.isFromNonDefaultValue() && !(_localTypedChange.getOldValue() instanceof java.lang.String)) {
    		return false;
    	}
    	if (_localTypedChange.isToNonDefaultValue() && !(_localTypedChange.getNewValue() instanceof java.lang.String)) {
    		return false;
    	}
    	this.replaceChange = (ReplaceSingleValuedEAttribute<CAEX.SystemUnitClass, java.lang.String>) change;
    	return true;
    }
    
    return false;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final SystemUnitClass affectedEObject, final EAttribute affectedFeature, final String oldValue, final String newValue, @Extension final RoutinesFacade _routinesFacade) {
      if ((affectedEObject instanceof InternalElement)) {
        return;
      }
      _routinesFacade.correctPathsSystemUnitClassClones(affectedEObject);
    }
  }
}