package mir.routines.iC_ProtoCloneAddCorrespondences;

import CAEX.InternalElement;
import CAEX.SystemUnitClass;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import mir.routines.iC_ProtoCloneAddCorrespondences.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RemoveAllClonesAndCorrespondencesRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private RemoveAllClonesAndCorrespondencesRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getCorrepondenceSourceClones(final SystemUnitClass affectedClass) {
      return affectedClass;
    }
    
    public void callRoutine1(final SystemUnitClass affectedClass, final List<InternalElement> clones, @Extension final RoutinesFacade _routinesFacade) {
      final Consumer<InternalElement> _function = (InternalElement it) -> {
        _routinesFacade.removeCAEXCorrespondence(it, affectedClass);
      };
      clones.forEach(_function);
      final Consumer<InternalElement> _function_1 = (InternalElement it) -> {
        _routinesFacade.deleteElement(it);
      };
      clones.forEach(_function_1);
    }
  }
  
  public RemoveAllClonesAndCorrespondencesRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final SystemUnitClass affectedClass) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_ProtoCloneAddCorrespondences.RemoveAllClonesAndCorrespondencesRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.iC_ProtoCloneAddCorrespondences.RoutinesFacade(getExecutionState(), this);
    this.affectedClass = affectedClass;
  }
  
  private SystemUnitClass affectedClass;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine RemoveAllClonesAndCorrespondencesRoutine with input:");
    getLogger().debug("   affectedClass: " + this.affectedClass);
    
    List<CAEX.InternalElement> clones = getCorrespondingElements(
    	userExecution.getCorrepondenceSourceClones(affectedClass), // correspondence source supplier
    	CAEX.InternalElement.class,
    	(CAEX.InternalElement _element) -> true, // correspondence precondition checker
    	null
    );
    for (EObject _element : clones) {	
    	registerObjectUnderModification(_element);
    }
    userExecution.callRoutine1(affectedClass, clones, actionsFacade);
    
    postprocessElements();
    
    return true;
  }
}
