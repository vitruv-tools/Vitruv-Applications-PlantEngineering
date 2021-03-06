package mir.routines.iC_Utilities;

import caex.caex30.caex.CAEXObject;
import java.io.IOException;
import mir.routines.iC_Utilities.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class DeleteElementRoutine extends AbstractRepairRoutineRealization {
  private DeleteElementRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final CAEXObject o) {
      return o;
    }
  }
  
  public DeleteElementRoutine(final RoutinesFacade routinesFacade, final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final CAEXObject o) {
    super(routinesFacade, reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_Utilities.DeleteElementRoutine.ActionUserExecution(getExecutionState(), this);
    this.o = o;
  }
  
  private CAEXObject o;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine DeleteElementRoutine with input:");
    getLogger().debug("   o: " + this.o);
    
    deleteObject(userExecution.getElement1(o));
    
    postprocessElements();
    
    return true;
  }
}
