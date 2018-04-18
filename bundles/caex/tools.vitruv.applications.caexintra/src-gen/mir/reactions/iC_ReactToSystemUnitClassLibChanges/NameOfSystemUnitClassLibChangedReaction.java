package mir.reactions.iC_ReactToSystemUnitClassLibChanges;

import caex.caex30.caex.SystemUnitClass;
import caex.caex30.caex.SystemUnitClassLib;
import java.util.function.Consumer;
import mir.routines.iC_ReactToSystemUnitClassLibChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.feature.attribute.ReplaceSingleValuedEAttribute;

@SuppressWarnings("all")
public class NameOfSystemUnitClassLibChangedReaction extends AbstractReactionRealization {
  private ReplaceSingleValuedEAttribute<SystemUnitClassLib, String> replaceChange;
  
  private int currentlyMatchedChange;
  
  public NameOfSystemUnitClassLibChangedReaction(final RoutinesFacade routinesFacade) {
    super(routinesFacade);
  }
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    caex.caex30.caex.SystemUnitClassLib affectedEObject = replaceChange.getAffectedEObject();
    EAttribute affectedFeature = replaceChange.getAffectedFeature();
    java.lang.String oldValue = replaceChange.getOldValue();
    java.lang.String newValue = replaceChange.getNewValue();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.reactions.iC_ReactToSystemUnitClassLibChanges.NameOfSystemUnitClassLibChangedReaction.ActionUserExecution userExecution = new mir.reactions.iC_ReactToSystemUnitClassLibChanges.NameOfSystemUnitClassLibChangedReaction.ActionUserExecution(this.executionState, this);
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
    	ReplaceSingleValuedEAttribute<caex.caex30.caex.SystemUnitClassLib, java.lang.String> _localTypedChange = (ReplaceSingleValuedEAttribute<caex.caex30.caex.SystemUnitClassLib, java.lang.String>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof caex.caex30.caex.SystemUnitClassLib)) {
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
    	this.replaceChange = (ReplaceSingleValuedEAttribute<caex.caex30.caex.SystemUnitClassLib, java.lang.String>) change;
    	return true;
    }
    
    return false;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final ReplaceSingleValuedEAttribute replaceChange, final SystemUnitClassLib affectedEObject, final EAttribute affectedFeature, final String oldValue, final String newValue, @Extension final RoutinesFacade _routinesFacade) {
      final Consumer<SystemUnitClass> _function = (SystemUnitClass it) -> {
        _routinesFacade.correctPathsSystemUnitClassClones(it);
      };
      affectedEObject.getSystemUnitClass().forEach(_function);
      if (((affectedEObject != null) && (affectedEObject.eResource() != null))) {
        Resource _eResource = affectedEObject.eResource();
        _eResource.setModified(true);
      }
    }
  }
}
