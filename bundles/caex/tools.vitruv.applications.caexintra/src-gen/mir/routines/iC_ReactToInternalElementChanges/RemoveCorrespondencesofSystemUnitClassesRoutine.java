package mir.routines.iC_ReactToInternalElementChanges;

import caex.caex30.caex.InternalElement;
import caex.caex30.caex.SystemUnitClass;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import mir.routines.iC_ReactToInternalElementChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RemoveCorrespondencesofSystemUnitClassesRoutine extends AbstractRepairRoutineRealization {
  private RemoveCorrespondencesofSystemUnitClassesRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getCorrepondenceSourceCorres(final InternalElement affectedElement) {
      return affectedElement;
    }
    
    public void callRoutine1(final InternalElement affectedElement, final List<SystemUnitClass> corres, @Extension final RoutinesFacade _routinesFacade) {
      final Consumer<SystemUnitClass> _function = (SystemUnitClass it) -> {
        _routinesFacade.removeCAEXCorrespondence(it, affectedElement);
      };
      corres.forEach(_function);
    }
  }
  
  public RemoveCorrespondencesofSystemUnitClassesRoutine(final RoutinesFacade routinesFacade, final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final InternalElement affectedElement) {
    super(routinesFacade, reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_ReactToInternalElementChanges.RemoveCorrespondencesofSystemUnitClassesRoutine.ActionUserExecution(getExecutionState(), this);
    this.affectedElement = affectedElement;
  }
  
  private InternalElement affectedElement;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine RemoveCorrespondencesofSystemUnitClassesRoutine with input:");
    getLogger().debug("   affectedElement: " + this.affectedElement);
    
    List<caex.caex30.caex.SystemUnitClass> corres = getCorrespondingElements(
    	userExecution.getCorrepondenceSourceCorres(affectedElement), // correspondence source supplier
    	caex.caex30.caex.SystemUnitClass.class,
    	(caex.caex30.caex.SystemUnitClass _element) -> true, // correspondence precondition checker
    	null
    );
    for (EObject _element : corres) {	
    	registerObjectUnderModification(_element);
    }
    userExecution.callRoutine1(affectedElement, corres, this.getRoutinesFacade());
    
    postprocessElements();
    
    return true;
  }
}
