package mir.reactions.reactionsPLCOpenToCaex.plcopen2caex;

import mir.routines.plcopen2caex.RoutinesFacade;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;

@SuppressWarnings("all")
class AnyReactionTrackingReaction extends AbstractReactionRealization {
  private EChange change;
  
  private int currentlyMatchedChange;
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.routines.plcopen2caex.RoutinesFacade routinesFacade = new mir.routines.plcopen2caex.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.AnyReactionTrackingReaction.ActionUserExecution userExecution = new mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.AnyReactionTrackingReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(change, routinesFacade);
    
    resetChanges();
  }
  
  private void resetChanges() {
    change = null;
    currentlyMatchedChange = 0;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (currentlyMatchedChange == 0) {
    	if (!matchChange(change)) {
    		resetChanges();
    		return false;
    	} else {
    		currentlyMatchedChange++;
    	}
    }
    
    return true;
  }
  
  private boolean matchChange(final EChange change) {
    if (change instanceof EChange) {
    	EChange _localTypedChange = (EChange) change;
    	this.change = (EChange) change;
    	return true;
    }
    
    return false;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final EChange change, @Extension final RoutinesFacade _routinesFacade) {
      System.out.println("reacting to a change ...");
    }
  }
}
