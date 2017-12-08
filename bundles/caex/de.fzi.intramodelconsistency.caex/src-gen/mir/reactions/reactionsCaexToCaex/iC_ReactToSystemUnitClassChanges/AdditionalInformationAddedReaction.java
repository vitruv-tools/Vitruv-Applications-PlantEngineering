package mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges;

import CAEX.CAEXFactory;
import CAEX.SystemUnitClass;
import mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.feature.reference.InsertEReference;

/**
 * 
 *   	On new additionalInformation in Prototype, add same object to clones 
 * 	===== Not working due to the AdditionalInformation Tuid bug ===== 
 * 
 */
@SuppressWarnings("all")
class AdditionalInformationAddedReaction extends AbstractReactionRealization {
  private InsertEReference<SystemUnitClass, EObject> insertChange;
  
  private int currentlyMatchedChange;
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    CAEX.SystemUnitClass affectedEObject = insertChange.getAffectedEObject();
    EReference affectedFeature = insertChange.getAffectedFeature();
    org.eclipse.emf.ecore.EObject newValue = insertChange.getNewValue();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade routinesFacade = new mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.AdditionalInformationAddedReaction.ActionUserExecution userExecution = new mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.AdditionalInformationAddedReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(affectedEObject, affectedFeature, newValue, routinesFacade);
    
    resetChanges();
  }
  
  private void resetChanges() {
    insertChange = null;
    currentlyMatchedChange = 0;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (currentlyMatchedChange == 0) {
    	if (!matchInsertChange(change)) {
    		resetChanges();
    		return false;
    	} else {
    		currentlyMatchedChange++;
    	}
    }
    
    return true;
  }
  
  private boolean matchInsertChange(final EChange change) {
    if (change instanceof InsertEReference<?, ?>) {
    	InsertEReference<CAEX.SystemUnitClass, org.eclipse.emf.ecore.EObject> _localTypedChange = (InsertEReference<CAEX.SystemUnitClass, org.eclipse.emf.ecore.EObject>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof CAEX.SystemUnitClass)) {
    		return false;
    	}
    	if (!_localTypedChange.getAffectedFeature().getName().equals("additionalInformation")) {
    		return false;
    	}
    	if (!(_localTypedChange.getNewValue() instanceof org.eclipse.emf.ecore.EObject)) {
    		return false;
    	}
    	this.insertChange = (InsertEReference<CAEX.SystemUnitClass, org.eclipse.emf.ecore.EObject>) change;
    	return true;
    }
    
    return false;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final SystemUnitClass affectedEObject, final EReference affectedFeature, final EObject newValue, @Extension final RoutinesFacade _routinesFacade) {
      final EStructuralFeature feature = CAEXFactory.eINSTANCE.createInternalElement().eClass().getEStructuralFeature("additionalInformation");
      _routinesFacade.correctSystemUnitClassClones(affectedEObject, feature, newValue);
    }
  }
}
