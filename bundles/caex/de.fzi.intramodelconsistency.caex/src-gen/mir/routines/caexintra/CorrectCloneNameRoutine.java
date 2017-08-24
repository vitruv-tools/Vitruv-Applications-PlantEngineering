package mir.routines.caexintra;

import CAEX.InternalElement;
import CAEX.SystemUnitClass;
import java.io.IOException;
import mir.routines.caexintra.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class CorrectCloneNameRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private CorrectCloneNameRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final SystemUnitClass change, final String newValue, final InternalElement clon) {
      return clon;
    }
    
    public EObject getCorrepondenceSourceClon(final SystemUnitClass change, final String newValue) {
      return change;
    }
    
    public void update0Element(final SystemUnitClass change, final String newValue, final InternalElement clon) {
      clon.setRefBaseSystemUnitPath(newValue);
      System.out.println(clon.getRefBaseSystemUnitPath());
    }
  }
  
  public CorrectCloneNameRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final SystemUnitClass change, final String newValue) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.caexintra.CorrectCloneNameRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.caexintra.RoutinesFacade(getExecutionState(), this);
    this.change = change;this.newValue = newValue;
  }
  
  private SystemUnitClass change;
  
  private String newValue;
  
  protected void executeRoutine() throws IOException {
    getLogger().debug("Called routine CorrectCloneNameRoutine with input:");
    getLogger().debug("   change: " + this.change);
    getLogger().debug("   newValue: " + this.newValue);
    
    CAEX.InternalElement clon = getCorrespondingElement(
    	userExecution.getCorrepondenceSourceClon(change, newValue), // correspondence source supplier
    	CAEX.InternalElement.class,
    	(CAEX.InternalElement _element) -> true, // correspondence precondition checker
    	null);
    if (clon == null) {
    	return;
    }
    registerObjectUnderModification(clon);
    // val updatedElement userExecution.getElement1(change, newValue, clon);
    userExecution.update0Element(change, newValue, clon);
    
    postprocessElements();
  }
}
