package mir.reactions.iC_ReactToSystemUnitClassChanges;

import caex.caex30.caex.CAEXFactory;
import caex.caex30.caex.InternalElement;
import caex.caex30.caex.SystemUnitClass;
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
public class InternalElementAddedReaction extends AbstractReactionRealization {
  private InsertEReference<SystemUnitClass, InternalElement> insertChange;
  
  private int currentlyMatchedChange;
  
  public InternalElementAddedReaction(final RoutinesFacade routinesFacade) {
    super(routinesFacade);
  }
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    caex.caex30.caex.SystemUnitClass affectedEObject = insertChange.getAffectedEObject();
    EReference affectedFeature = insertChange.getAffectedFeature();
    caex.caex30.caex.InternalElement newValue = insertChange.getNewValue();
    int index = insertChange.getIndex();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.reactions.iC_ReactToSystemUnitClassChanges.InternalElementAddedReaction.ActionUserExecution userExecution = new mir.reactions.iC_ReactToSystemUnitClassChanges.InternalElementAddedReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(insertChange, affectedEObject, affectedFeature, newValue, index, this.getRoutinesFacade());
    
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
    	InsertEReference<caex.caex30.caex.SystemUnitClass, caex.caex30.caex.InternalElement> _localTypedChange = (InsertEReference<caex.caex30.caex.SystemUnitClass, caex.caex30.caex.InternalElement>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof caex.caex30.caex.SystemUnitClass)) {
    		return false;
    	}
    	if (!_localTypedChange.getAffectedFeature().getName().equals("internalElement")) {
    		return false;
    	}
    	if (!(_localTypedChange.getNewValue() instanceof caex.caex30.caex.InternalElement)) {
    		return false;
    	}
    	this.insertChange = (InsertEReference<caex.caex30.caex.SystemUnitClass, caex.caex30.caex.InternalElement>) change;
    	return true;
    }
    
    return false;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final InsertEReference insertChange, final SystemUnitClass affectedEObject, final EReference affectedFeature, final InternalElement newValue, final int index, @Extension final RoutinesFacade _routinesFacade) {
      final EStructuralFeature feature = CAEXFactory.eINSTANCE.createInternalElement().eClass().getEStructuralFeature("internalElement");
      _routinesFacade.correctSystemUnitClassClones(affectedEObject, feature, newValue);
    }
  }
}
