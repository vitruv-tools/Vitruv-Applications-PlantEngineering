package mir.routines.caexintra;

import CAEX.CAEXObject;
import java.io.IOException;
import mir.routines.caexintra.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class AddCAEXCorrespondenceRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private AddCAEXCorrespondenceRoutine.ActionUserExecution userExecution;
  
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
  
  public AddCAEXCorrespondenceRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final CAEXObject a, final CAEXObject b) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.caexintra.AddCAEXCorrespondenceRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.caexintra.RoutinesFacade(getExecutionState(), this);
    this.a = a;this.b = b;
  }
  
  private CAEXObject a;
  
  private CAEXObject b;
  
  protected void executeRoutine() throws IOException {
    getLogger().debug("Called routine AddCAEXCorrespondenceRoutine with input:");
    getLogger().debug("   a: " + this.a);
    getLogger().debug("   b: " + this.b);
    
    addCorrespondenceBetween(userExecution.getElement1(a, b), userExecution.getElement2(a, b), "");
    
    postprocessElements();
  }
}
