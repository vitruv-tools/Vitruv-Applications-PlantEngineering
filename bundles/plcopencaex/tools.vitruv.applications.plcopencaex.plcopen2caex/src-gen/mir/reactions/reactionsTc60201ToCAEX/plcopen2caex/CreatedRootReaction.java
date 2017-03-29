package mir.reactions.reactionsTc60201ToCAEX.plcopen2caex;

import mir.routines.plcopen2caex.RoutinesFacade;
import org.eclipse.xtext.xbase.lib.Extension;
import org.plcopen.xml.tc60201.DocumentRoot;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.compound.CreateAndInsertRoot;
import tools.vitruv.framework.change.echange.root.InsertRootEObject;
import tools.vitruv.framework.userinteraction.UserInteracting;

@SuppressWarnings("all")
class CreatedRootReaction extends AbstractReactionRealization {
  public CreatedRootReaction(final UserInteracting userInteracting) {
    super(userInteracting);
  }
  
  public void executeReaction(final EChange change) {
    InsertRootEObject<DocumentRoot> typedChange = ((CreateAndInsertRoot<DocumentRoot>)change).getInsertChange();
    DocumentRoot newValue = typedChange.getNewValue();
    mir.routines.plcopen2caex.RoutinesFacade routinesFacade = new mir.routines.plcopen2caex.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsTc60201ToCAEX.plcopen2caex.CreatedRootReaction.ActionUserExecution userExecution = new mir.reactions.reactionsTc60201ToCAEX.plcopen2caex.CreatedRootReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(newValue, routinesFacade);
  }
  
  public static Class<? extends EChange> getExpectedChangeType() {
    return CreateAndInsertRoot.class;
  }
  
  private boolean checkChangeProperties(final EChange change) {
    InsertRootEObject<DocumentRoot> relevantChange = ((CreateAndInsertRoot<DocumentRoot>)change).getInsertChange();
    if (!(relevantChange.getNewValue() instanceof DocumentRoot)) {
    	return false;
    }
    return true;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (!(change instanceof CreateAndInsertRoot)) {
    	return false;
    }
    getLogger().debug("Passed change type check of reaction " + this.getClass().getName());
    if (!checkChangeProperties(change)) {
    	return false;
    }
    getLogger().debug("Passed change properties check of reaction " + this.getClass().getName());
    getLogger().debug("Passed complete precondition check of reaction " + this.getClass().getName());
    return true;
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
