package mir.routines.iC_Utilities;

import java.io.IOException;
import mir.routines.iC_Utilities.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class SetFeatureValueRoutine extends AbstractRepairRoutineRealization {
  private SetFeatureValueRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final EObject e, final EStructuralFeature f, final Object o) {
      return e;
    }
    
    public void update0Element(final EObject e, final EStructuralFeature f, final Object o) {
      e.eSet(f, o);
      Resource _eResource = e.eResource();
      _eResource.setModified(true);
    }
  }
  
  public SetFeatureValueRoutine(final RoutinesFacade routinesFacade, final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final EObject e, final EStructuralFeature f, final Object o) {
    super(routinesFacade, reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_Utilities.SetFeatureValueRoutine.ActionUserExecution(getExecutionState(), this);
    this.e = e;this.f = f;this.o = o;
  }
  
  private EObject e;
  
  private EStructuralFeature f;
  
  private Object o;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine SetFeatureValueRoutine with input:");
    getLogger().debug("   e: " + this.e);
    getLogger().debug("   f: " + this.f);
    getLogger().debug("   o: " + this.o);
    
    // val updatedElement userExecution.getElement1(e, f, o);
    userExecution.update0Element(e, f, o);
    
    postprocessElements();
    
    return true;
  }
}
