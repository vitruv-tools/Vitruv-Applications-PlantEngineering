package mir.routines.iC_ReactToSystemUnitClassChanges;

import CAEX.CAEXFactory;
import CAEX.SystemUnitClass;
import de.fzi.intramodelconsistency.caex.CAEXIntraConsistencyTools;
import java.io.IOException;
import java.util.function.Consumer;
import mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

/**
 * *
 * nRoutine that recursively corrects the references in refBaseClassPaths of a SystemUnitClass and all it's children SystemUnitClasses
 *  
 */
@SuppressWarnings("all")
public class CorrectPathsSystemUnitClassClonesRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private CorrectPathsSystemUnitClassClonesRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final SystemUnitClass affectedEObject, @Extension final RoutinesFacade _routinesFacade) {
      final String newRefBasePath = CAEXIntraConsistencyTools.generatePathFromTuid(this.correspondenceModel, affectedEObject, this.correspondenceModel.calculateTuidFromEObject(affectedEObject));
      final EStructuralFeature feature = CAEXFactory.eINSTANCE.createInternalElement().eClass().getEStructuralFeature("refBaseSystemUnitPath");
      _routinesFacade.correctSystemUnitClassClones(affectedEObject, feature, newRefBasePath);
      boolean _isEmpty = affectedEObject.getSystemUnitClass().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        final Consumer<SystemUnitClass> _function = (SystemUnitClass it) -> {
          _routinesFacade.correctPathsSystemUnitClassClones(it);
        };
        affectedEObject.getSystemUnitClass().forEach(_function);
      }
    }
  }
  
  public CorrectPathsSystemUnitClassClonesRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final SystemUnitClass affectedEObject) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_ReactToSystemUnitClassChanges.CorrectPathsSystemUnitClassClonesRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade(getExecutionState(), this);
    this.affectedEObject = affectedEObject;
  }
  
  private SystemUnitClass affectedEObject;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine CorrectPathsSystemUnitClassClonesRoutine with input:");
    getLogger().debug("   affectedEObject: " + this.affectedEObject);
    
    userExecution.callRoutine1(affectedEObject, actionsFacade);
    
    postprocessElements();
    
    return true;
  }
}
