package mir.reactions.reactionsCaexToCaex.iC_ProtoCloneAddCorrespondences;

import CAEX.SystemUnitClass;
import CAEX.SystemUnitClassLib;
import mir.routines.iC_ProtoCloneAddCorrespondences.RoutinesFacade;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.feature.reference.RemoveEReference;

/**
 * * On deletion of the prototype also remove all clones 
 */
@SuppressWarnings("all")
class ProtoDeletedReaction extends AbstractReactionRealization {
  private RemoveEReference<SystemUnitClassLib, SystemUnitClass> removeChange;
  
  private int currentlyMatchedChange;
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    CAEX.SystemUnitClassLib affectedEObject = removeChange.getAffectedEObject();
    EReference affectedFeature = removeChange.getAffectedFeature();
    CAEX.SystemUnitClass oldValue = removeChange.getOldValue();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.routines.iC_ProtoCloneAddCorrespondences.RoutinesFacade routinesFacade = new mir.routines.iC_ProtoCloneAddCorrespondences.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsCaexToCaex.iC_ProtoCloneAddCorrespondences.ProtoDeletedReaction.ActionUserExecution userExecution = new mir.reactions.reactionsCaexToCaex.iC_ProtoCloneAddCorrespondences.ProtoDeletedReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(affectedEObject, affectedFeature, oldValue, routinesFacade);
    
    resetChanges();
  }
  
  private void resetChanges() {
    removeChange = null;
    currentlyMatchedChange = 0;
  }
  
  private boolean matchRemoveChange(final EChange change) {
    if (change instanceof RemoveEReference<?, ?>) {
    	RemoveEReference<CAEX.SystemUnitClassLib, CAEX.SystemUnitClass> _localTypedChange = (RemoveEReference<CAEX.SystemUnitClassLib, CAEX.SystemUnitClass>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof CAEX.SystemUnitClassLib)) {
    		return false;
    	}
    	if (!_localTypedChange.getAffectedFeature().getName().equals("systemUnitClass")) {
    		return false;
    	}
    	if (!(_localTypedChange.getOldValue() instanceof CAEX.SystemUnitClass)) {
    		return false;
    	}
    	this.removeChange = (RemoveEReference<CAEX.SystemUnitClassLib, CAEX.SystemUnitClass>) change;
    	return true;
    }
    
    return false;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (currentlyMatchedChange == 0) {
    	if (!matchRemoveChange(change)) {
    		resetChanges();
    		return false;
    	} else {
    		currentlyMatchedChange++;
    	}
    }
    
    return true;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final SystemUnitClassLib affectedEObject, final EReference affectedFeature, final SystemUnitClass oldValue, @Extension final RoutinesFacade _routinesFacade) {
      _routinesFacade.removeAllClonesAndCorrespondences(oldValue);
    }
  }
}
