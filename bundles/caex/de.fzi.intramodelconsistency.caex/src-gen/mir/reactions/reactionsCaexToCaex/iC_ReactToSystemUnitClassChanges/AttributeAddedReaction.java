package mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges;

import CAEX.Attribute;
import CAEX.CAEXFactory;
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

/**
 * * On new attribute in Prototype, also add the same attribute to clones 
 */
@SuppressWarnings("all")
class AttributeAddedReaction extends AbstractReactionRealization {
  private InsertEReference<SystemUnitClass, Attribute> insertChange;
  
  private int currentlyMatchedChange;
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    CAEX.SystemUnitClass affectedEObject = insertChange.getAffectedEObject();
    EReference affectedFeature = insertChange.getAffectedFeature();
    CAEX.Attribute newValue = insertChange.getNewValue();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade routinesFacade = new mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.AttributeAddedReaction.ActionUserExecution userExecution = new mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.AttributeAddedReaction.ActionUserExecution(this.executionState, this);
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
    	InsertEReference<CAEX.SystemUnitClass, CAEX.Attribute> _localTypedChange = (InsertEReference<CAEX.SystemUnitClass, CAEX.Attribute>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof CAEX.SystemUnitClass)) {
    		return false;
    	}
    	if (!_localTypedChange.getAffectedFeature().getName().equals("attribute")) {
    		return false;
    	}
    	if (!(_localTypedChange.getNewValue() instanceof CAEX.Attribute)) {
    		return false;
    	}
    	this.insertChange = (InsertEReference<CAEX.SystemUnitClass, CAEX.Attribute>) change;
    	return true;
    }
    
    return false;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final SystemUnitClass affectedEObject, final EReference affectedFeature, final Attribute newValue, @Extension final RoutinesFacade _routinesFacade) {
      final EStructuralFeature feature = CAEXFactory.eINSTANCE.createInternalElement().eClass().getEStructuralFeature("attribute");
      _routinesFacade.correctSystemUnitClassClones(affectedEObject, feature, newValue);
    }
  }
}
