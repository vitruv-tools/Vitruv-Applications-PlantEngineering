package mir.routines.iC_ReactToProtoChanges;

import java.io.IOException;
import mir.routines.iC_ReactToProtoChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class UpdateFeatureRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private UpdateFeatureRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final EObject obj, final EStructuralFeature feature, final Object newValue) {
      return obj;
    }
    
    public void update0Element(final EObject obj, final EStructuralFeature feature, final Object newValue) {
      boolean _eIsSet = obj.eIsSet(feature);
      if (_eIsSet) {
        obj.eSet(feature, newValue);
      }
    }
  }
  
  public UpdateFeatureRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final EObject obj, final EStructuralFeature feature, final Object newValue) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_ReactToProtoChanges.UpdateFeatureRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.iC_ReactToProtoChanges.RoutinesFacade(getExecutionState(), this);
    this.obj = obj;this.feature = feature;this.newValue = newValue;
  }
  
  private EObject obj;
  
  private EStructuralFeature feature;
  
  private Object newValue;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine UpdateFeatureRoutine with input:");
    getLogger().debug("   obj: " + this.obj);
    getLogger().debug("   feature: " + this.feature);
    getLogger().debug("   newValue: " + this.newValue);
    
    // val updatedElement userExecution.getElement1(obj, feature, newValue);
    userExecution.update0Element(obj, feature, newValue);
    
    postprocessElements();
    
    return true;
  }
}
