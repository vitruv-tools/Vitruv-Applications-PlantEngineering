package mir.routines.iC_Utilities;

import caex.caex30.caex.CAEXObject;
import java.io.IOException;
import mir.routines.iC_Utilities.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RemoveCAEXCorrespondenceRoutine extends AbstractRepairRoutineRealization {
  private RemoveCAEXCorrespondenceRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final CAEXObject a, final CAEXObject b) {
      return a;
    }
    
    public EObject getElement2(final CAEXObject a, final CAEXObject b) {
      return b;
    }
  }
  
  public RemoveCAEXCorrespondenceRoutine(final RoutinesFacade routinesFacade, final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final CAEXObject a, final CAEXObject b) {
    super(routinesFacade, reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_Utilities.RemoveCAEXCorrespondenceRoutine.ActionUserExecution(getExecutionState(), this);
    this.a = a;this.b = b;
  }
  
  private CAEXObject a;
  
  private CAEXObject b;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine RemoveCAEXCorrespondenceRoutine with input:");
    getLogger().debug("   a: " + this.a);
    getLogger().debug("   b: " + this.b);
    
    removeCorrespondenceBetween(userExecution.getElement1(a, b), userExecution.getElement2(a, b), "");
    
    postprocessElements();
    
    return true;
  }
}
