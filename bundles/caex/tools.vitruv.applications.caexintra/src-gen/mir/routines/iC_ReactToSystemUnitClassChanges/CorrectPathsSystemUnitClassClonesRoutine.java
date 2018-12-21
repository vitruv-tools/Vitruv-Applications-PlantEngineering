package mir.routines.iC_ReactToSystemUnitClassChanges;

import caex.caex30.caex.CAEXFactory;
import caex.caex30.caex.SystemUnitClass;
import java.io.IOException;
import java.util.Collections;
import java.util.function.Consumer;
import mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.applications.caexintra.CAEXIntraConsistencyTools;
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
  private CorrectPathsSystemUnitClassClonesRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final SystemUnitClass affectedEObject, @Extension final RoutinesFacade _routinesFacade) {
      boolean _isEmpty = this.correspondenceModel.getCorrespondingEObjects(Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList(affectedEObject))).isEmpty();
      if (_isEmpty) {
        return;
      }
      String newRefBasePath = CAEXIntraConsistencyTools.generatePath(affectedEObject);
      if ((newRefBasePath == null)) {
        return;
      }
      final EStructuralFeature feature = CAEXFactory.eINSTANCE.createInternalElement().eClass().getEStructuralFeature("refBaseSystemUnitPath");
      _routinesFacade.correctSystemUnitClassClones(affectedEObject, feature, newRefBasePath);
      boolean _isEmpty_1 = affectedEObject.getSystemUnitClass().isEmpty();
      boolean _not = (!_isEmpty_1);
      if (_not) {
        final Consumer<SystemUnitClass> _function = (SystemUnitClass it) -> {
          _routinesFacade.correctPathsSystemUnitClassClones(it);
        };
        affectedEObject.getSystemUnitClass().forEach(_function);
      }
    }
  }
  
  public CorrectPathsSystemUnitClassClonesRoutine(final RoutinesFacade routinesFacade, final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final SystemUnitClass affectedEObject) {
    super(routinesFacade, reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_ReactToSystemUnitClassChanges.CorrectPathsSystemUnitClassClonesRoutine.ActionUserExecution(getExecutionState(), this);
    this.affectedEObject = affectedEObject;
  }
  
  private SystemUnitClass affectedEObject;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine CorrectPathsSystemUnitClassClonesRoutine with input:");
    getLogger().debug("   affectedEObject: " + this.affectedEObject);
    
    userExecution.callRoutine1(affectedEObject, this.getRoutinesFacade());
    
    postprocessElements();
    
    return true;
  }
}
