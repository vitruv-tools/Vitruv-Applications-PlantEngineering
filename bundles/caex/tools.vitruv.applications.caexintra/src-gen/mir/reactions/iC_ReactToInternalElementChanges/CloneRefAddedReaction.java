package mir.reactions.iC_ReactToInternalElementChanges;

import caex.caex30.caex.InternalElement;
import mir.routines.iC_ReactToInternalElementChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.feature.attribute.ReplaceSingleValuedEAttribute;

/**
 * * If a refBaseSystemunitPath is edited in InternalElement then remove old correspondences and create new 
 */
@SuppressWarnings("all")
public class CloneRefAddedReaction extends AbstractReactionRealization {
  private ReplaceSingleValuedEAttribute<InternalElement, String> replaceChange;
  
  private int currentlyMatchedChange;
  
  public CloneRefAddedReaction(final RoutinesFacade routinesFacade) {
    super(routinesFacade);
  }
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    caex.caex30.caex.InternalElement affectedEObject = replaceChange.getAffectedEObject();
    EAttribute affectedFeature = replaceChange.getAffectedFeature();
    java.lang.String oldValue = replaceChange.getOldValue();
    java.lang.String newValue = replaceChange.getNewValue();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.reactions.iC_ReactToInternalElementChanges.CloneRefAddedReaction.ActionUserExecution userExecution = new mir.reactions.iC_ReactToInternalElementChanges.CloneRefAddedReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(replaceChange, affectedEObject, affectedFeature, oldValue, newValue, this.getRoutinesFacade());
    
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
    	ReplaceSingleValuedEAttribute<caex.caex30.caex.InternalElement, java.lang.String> _localTypedChange = (ReplaceSingleValuedEAttribute<caex.caex30.caex.InternalElement, java.lang.String>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof caex.caex30.caex.InternalElement)) {
    		return false;
    	}
    	if (!_localTypedChange.getAffectedFeature().getName().equals("refBaseSystemUnitPath")) {
    		return false;
    	}
    	if (_localTypedChange.isFromNonDefaultValue() && !(_localTypedChange.getOldValue() instanceof java.lang.String)) {
    		return false;
    	}
    	if (_localTypedChange.isToNonDefaultValue() && !(_localTypedChange.getNewValue() instanceof java.lang.String)) {
    		return false;
    	}
    	this.replaceChange = (ReplaceSingleValuedEAttribute<caex.caex30.caex.InternalElement, java.lang.String>) change;
    	return true;
    }
    
    return false;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final ReplaceSingleValuedEAttribute replaceChange, final InternalElement affectedEObject, final EAttribute affectedFeature, final String oldValue, final String newValue, @Extension final RoutinesFacade _routinesFacade) {
      _routinesFacade.removeCorrespondencesofSystemUnitClasses(affectedEObject);
      _routinesFacade.newCorrespondenceCloneProto(affectedEObject, newValue);
    }
  }
}
