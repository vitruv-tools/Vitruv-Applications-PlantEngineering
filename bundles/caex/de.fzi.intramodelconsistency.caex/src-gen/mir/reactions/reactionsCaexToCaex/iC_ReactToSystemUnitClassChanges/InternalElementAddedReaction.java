package mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges;

import CAEX.CAEXFactory;
import CAEX.InternalElement;
import CAEX.SystemUnitClass;
import mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.feature.reference.InsertEReference;

@SuppressWarnings("all")
class InternalElementAddedReaction extends AbstractReactionRealization {
  private InsertEReference<SystemUnitClass, InternalElement> insertChange;
  
  private int currentlyMatchedChange;
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    CAEX.SystemUnitClass affectedEObject = insertChange.getAffectedEObject();
    EReference affectedFeature = insertChange.getAffectedFeature();
    CAEX.InternalElement newValue = insertChange.getNewValue();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade routinesFacade = new mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.InternalElementAddedReaction.ActionUserExecution userExecution = new mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.InternalElementAddedReaction.ActionUserExecution(this.executionState, this);
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
    	InsertEReference<CAEX.SystemUnitClass, CAEX.InternalElement> _localTypedChange = (InsertEReference<CAEX.SystemUnitClass, CAEX.InternalElement>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof CAEX.SystemUnitClass)) {
    		return false;
    	}
    	if (!_localTypedChange.getAffectedFeature().getName().equals("internalElement")) {
    		return false;
    	}
    	if (!(_localTypedChange.getNewValue() instanceof CAEX.InternalElement)) {
    		return false;
    	}
    	this.insertChange = (InsertEReference<CAEX.SystemUnitClass, CAEX.InternalElement>) change;
    	return true;
    }
    
    return false;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final SystemUnitClass affectedEObject, final EReference affectedFeature, final InternalElement newValue, @Extension final RoutinesFacade _routinesFacade) {
      final EStructuralFeature feature = CAEXFactory.eINSTANCE.createInternalElement().eClass().getEStructuralFeature("internalElement");
      _routinesFacade.correctSystemUnitClassClones(affectedEObject, feature, newValue);
    }
  }
}
