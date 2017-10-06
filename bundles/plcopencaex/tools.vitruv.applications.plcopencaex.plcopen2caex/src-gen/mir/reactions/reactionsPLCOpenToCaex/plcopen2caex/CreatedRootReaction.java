package mir.reactions.reactionsPLCOpenToCaex.plcopen2caex;

import mir.routines.plcopen2caex.RoutinesFacade;
import org.eclipse.xtext.xbase.lib.Extension;
import org.plcopen.xml.tc60201.DocumentRoot;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.eobject.CreateEObject;
import tools.vitruv.framework.change.echange.root.InsertRootEObject;

@SuppressWarnings("all")
class CreatedRootReaction extends AbstractReactionRealization {
  private CreateEObject<DocumentRoot> createChange;
  
  private InsertRootEObject<DocumentRoot> insertChange;
  
  private int currentlyMatchedChange;
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    org.plcopen.xml.tc60201.DocumentRoot newValue = insertChange.getNewValue();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.routines.plcopen2caex.RoutinesFacade routinesFacade = new mir.routines.plcopen2caex.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.CreatedRootReaction.ActionUserExecution userExecution = new mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.CreatedRootReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(newValue, routinesFacade);
    
    resetChanges();
  }
  
  private void resetChanges() {
    createChange = null;
    insertChange = null;
    currentlyMatchedChange = 0;
  }
  
  private boolean matchCreateChange(final EChange change) {
    if (change instanceof CreateEObject<?>) {
    	CreateEObject<org.plcopen.xml.tc60201.DocumentRoot> _localTypedChange = (CreateEObject<org.plcopen.xml.tc60201.DocumentRoot>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof org.plcopen.xml.tc60201.DocumentRoot)) {
    		return false;
    	}
    	this.createChange = (CreateEObject<org.plcopen.xml.tc60201.DocumentRoot>) change;
    	return true;
    }
    
    return false;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (currentlyMatchedChange == 0) {
    	if (!matchCreateChange(change)) {
    		resetChanges();
    		return false;
    	} else {
    		currentlyMatchedChange++;
    	}
    	return false; // Only proceed on the last of the expected changes
    }
    if (currentlyMatchedChange == 1) {
    	if (!matchInsertChange(change)) {
    		resetChanges();
    		checkPrecondition(change); // Reexecute to potentially register this as first change
    		return false;
    	} else {
    		currentlyMatchedChange++;
    	}
    }
    
    return true;
  }
  
  private boolean matchInsertChange(final EChange change) {
    if (change instanceof InsertRootEObject<?>) {
    	InsertRootEObject<org.plcopen.xml.tc60201.DocumentRoot> _localTypedChange = (InsertRootEObject<org.plcopen.xml.tc60201.DocumentRoot>) change;
    	if (!(_localTypedChange.getNewValue() instanceof org.plcopen.xml.tc60201.DocumentRoot)) {
    		return false;
    	}
    	this.insertChange = (InsertRootEObject<org.plcopen.xml.tc60201.DocumentRoot>) change;
    	return true;
    }
    
    return false;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final DocumentRoot newValue, @Extension final RoutinesFacade _routinesFacade) {
      final DocumentRoot plcopenRoot = newValue;
      _routinesFacade.createCaexRoot(plcopenRoot);
    }
  }
}
