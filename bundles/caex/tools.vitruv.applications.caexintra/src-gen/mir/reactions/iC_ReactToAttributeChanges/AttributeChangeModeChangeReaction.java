package mir.reactions.iC_ReactToAttributeChanges;

import caex.caex30.caex.Attribute;
import caex.caex30.caex.CAEXFactory;
import caex.caex30.caex.ChangeMode;
import mir.routines.iC_ReactToAttributeChanges.RoutinesFacade;
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
public class AttributeChangeModeChangeReaction extends AbstractReactionRealization {
  private ReplaceSingleValuedEAttribute<Attribute, ChangeMode> replaceChange;
  
  private int currentlyMatchedChange;
  
  public AttributeChangeModeChangeReaction(final RoutinesFacade routinesFacade) {
    super(routinesFacade);
  }
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    caex.caex30.caex.Attribute affectedEObject = replaceChange.getAffectedEObject();
    EAttribute affectedFeature = replaceChange.getAffectedFeature();
    caex.caex30.caex.ChangeMode oldValue = replaceChange.getOldValue();
    caex.caex30.caex.ChangeMode newValue = replaceChange.getNewValue();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.reactions.iC_ReactToAttributeChanges.AttributeChangeModeChangeReaction.ActionUserExecution userExecution = new mir.reactions.iC_ReactToAttributeChanges.AttributeChangeModeChangeReaction.ActionUserExecution(this.executionState, this);
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
    	ReplaceSingleValuedEAttribute<caex.caex30.caex.Attribute, caex.caex30.caex.ChangeMode> _localTypedChange = (ReplaceSingleValuedEAttribute<caex.caex30.caex.Attribute, caex.caex30.caex.ChangeMode>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof caex.caex30.caex.Attribute)) {
    		return false;
    	}
    	if (!_localTypedChange.getAffectedFeature().getName().equals("changeMode")) {
    		return false;
    	}
    	if (_localTypedChange.isFromNonDefaultValue() && !(_localTypedChange.getOldValue() instanceof caex.caex30.caex.ChangeMode)) {
    		return false;
    	}
    	if (_localTypedChange.isToNonDefaultValue() && !(_localTypedChange.getNewValue() instanceof caex.caex30.caex.ChangeMode)) {
    		return false;
    	}
    	this.replaceChange = (ReplaceSingleValuedEAttribute<caex.caex30.caex.Attribute, caex.caex30.caex.ChangeMode>) change;
    	return true;
    }
    
    return false;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final ReplaceSingleValuedEAttribute replaceChange, final Attribute affectedEObject, final EAttribute affectedFeature, final ChangeMode oldValue, final ChangeMode newValue, @Extension final RoutinesFacade _routinesFacade) {
      final EStructuralFeature feature = CAEXFactory.eINSTANCE.createAttribute().eClass().getEStructuralFeature("changeMode");
      _routinesFacade.correctAttributeCloneFeatures(affectedEObject, feature, newValue);
    }
  }
}
