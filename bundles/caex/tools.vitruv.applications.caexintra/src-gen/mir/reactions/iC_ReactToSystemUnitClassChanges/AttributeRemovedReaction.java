package mir.reactions.iC_ReactToSystemUnitClassChanges;

import caex.caex30.caex.Attribute;
import caex.caex30.caex.SystemUnitClass;
import mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.feature.reference.RemoveEReference;

@SuppressWarnings("all")
public class AttributeRemovedReaction extends AbstractReactionRealization {
  private RemoveEReference<SystemUnitClass, Attribute> removeChange;
  
  private int currentlyMatchedChange;
  
  public AttributeRemovedReaction(final RoutinesFacade routinesFacade) {
    super(routinesFacade);
  }
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    caex.caex30.caex.SystemUnitClass affectedEObject = removeChange.getAffectedEObject();
    EReference affectedFeature = removeChange.getAffectedFeature();
    caex.caex30.caex.Attribute oldValue = removeChange.getOldValue();
    int index = removeChange.getIndex();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.reactions.iC_ReactToSystemUnitClassChanges.AttributeRemovedReaction.ActionUserExecution userExecution = new mir.reactions.iC_ReactToSystemUnitClassChanges.AttributeRemovedReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(removeChange, affectedEObject, affectedFeature, oldValue, index, this.getRoutinesFacade());
    
    resetChanges();
  }
  
  private void resetChanges() {
    removeChange = null;
    currentlyMatchedChange = 0;
  }
  
  private boolean matchRemoveChange(final EChange change) {
    if (change instanceof RemoveEReference<?, ?>) {
    	RemoveEReference<caex.caex30.caex.SystemUnitClass, caex.caex30.caex.Attribute> _localTypedChange = (RemoveEReference<caex.caex30.caex.SystemUnitClass, caex.caex30.caex.Attribute>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof caex.caex30.caex.SystemUnitClass)) {
    		return false;
    	}
    	if (!_localTypedChange.getAffectedFeature().getName().equals("attribute")) {
    		return false;
    	}
    	if (!(_localTypedChange.getOldValue() instanceof caex.caex30.caex.Attribute)) {
    		return false;
    	}
    	this.removeChange = (RemoveEReference<caex.caex30.caex.SystemUnitClass, caex.caex30.caex.Attribute>) change;
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
    
    public void callRoutine1(final RemoveEReference removeChange, final SystemUnitClass affectedEObject, final EReference affectedFeature, final Attribute oldValue, final int index, @Extension final RoutinesFacade _routinesFacade) {
      _routinesFacade.removeAllClonesAndCorrespondencesAttribute(oldValue);
    }
  }
}
