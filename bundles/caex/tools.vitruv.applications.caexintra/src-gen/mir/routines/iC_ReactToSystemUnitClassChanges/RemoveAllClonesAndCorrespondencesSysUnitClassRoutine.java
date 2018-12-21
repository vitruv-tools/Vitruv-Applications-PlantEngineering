package mir.routines.iC_ReactToSystemUnitClassChanges;

import caex.caex30.caex.InternalElement;
import caex.caex30.caex.SystemUnitClass;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RemoveAllClonesAndCorrespondencesSysUnitClassRoutine extends AbstractRepairRoutineRealization {
  private RemoveAllClonesAndCorrespondencesSysUnitClassRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getCorrepondenceSourceClones(final SystemUnitClass affectedClass) {
      return affectedClass;
    }
    
    public void callRoutine1(final SystemUnitClass affectedClass, final List<InternalElement> clones, @Extension final RoutinesFacade _routinesFacade) {
      boolean _isEmpty = clones.isEmpty();
      if (_isEmpty) {
        return;
      }
      Resource _eResource = clones.get(0).eResource();
      _eResource.setModified(true);
      final Consumer<InternalElement> _function = (InternalElement it) -> {
        _routinesFacade.removeCAEXCorrespondence(it, affectedClass);
      };
      clones.forEach(_function);
      final Boolean result = this.userInteractor.getConfirmationDialogBuilder().message("Should all InternalElements that correspond to the deleted SystemUnitClass also be removed?").title("Remove corresponding elements?").positiveButtonText("Yes").cancelButtonText("No").startInteraction();
      if ((result).booleanValue()) {
        final Consumer<InternalElement> _function_1 = (InternalElement it) -> {
          _routinesFacade.deleteElement(it);
        };
        clones.forEach(_function_1);
      } else {
        final Consumer<InternalElement> _function_2 = (InternalElement it) -> {
          it.setRefBaseSystemUnitPath("");
        };
        clones.forEach(_function_2);
      }
    }
  }
  
  public RemoveAllClonesAndCorrespondencesSysUnitClassRoutine(final RoutinesFacade routinesFacade, final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final SystemUnitClass affectedClass) {
    super(routinesFacade, reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_ReactToSystemUnitClassChanges.RemoveAllClonesAndCorrespondencesSysUnitClassRoutine.ActionUserExecution(getExecutionState(), this);
    this.affectedClass = affectedClass;
  }
  
  private SystemUnitClass affectedClass;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine RemoveAllClonesAndCorrespondencesSysUnitClassRoutine with input:");
    getLogger().debug("   affectedClass: " + this.affectedClass);
    
    List<caex.caex30.caex.InternalElement> clones = getCorrespondingElements(
    	userExecution.getCorrepondenceSourceClones(affectedClass), // correspondence source supplier
    	caex.caex30.caex.InternalElement.class,
    	(caex.caex30.caex.InternalElement _element) -> true, // correspondence precondition checker
    	null
    );
    for (EObject _element : clones) {	
    	registerObjectUnderModification(_element);
    }
    userExecution.callRoutine1(affectedClass, clones, this.getRoutinesFacade());
    
    postprocessElements();
    
    return true;
  }
}
