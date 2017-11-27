package mir.routines.iC_ReactOnAttributeChanges;

import CAEX.Attribute;
import CAEX.CAEXObject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutinesFacade;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RoutinesFacade extends AbstractRepairRoutinesFacade {
  public RoutinesFacade(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
    super(reactionExecutionState, calledBy);
  }
  
  public boolean correctAttributeCloneFeatures(final Attribute of, final EStructuralFeature feature, final Object newValue) {
    mir.routines.iC_ReactOnAttributeChanges.CorrectAttributeCloneFeaturesRoutine effect = new mir.routines.iC_ReactOnAttributeChanges.CorrectAttributeCloneFeaturesRoutine(this.executionState, calledBy, of, feature, newValue);
    return effect.applyRoutine();
  }
  
  public boolean updateFeature(final EObject obj, final EStructuralFeature feature, final Object newValue) {
    mir.routines.iC_ReactOnAttributeChanges.UpdateFeatureRoutine effect = new mir.routines.iC_ReactOnAttributeChanges.UpdateFeatureRoutine(this.executionState, calledBy, obj, feature, newValue);
    return effect.applyRoutine();
  }
  
  public boolean setFeatureValue(final EObject e, final EStructuralFeature f, final Object o) {
    mir.routines.iC_ReactOnAttributeChanges.SetFeatureValueRoutine effect = new mir.routines.iC_ReactOnAttributeChanges.SetFeatureValueRoutine(this.executionState, calledBy, e, f, o);
    return effect.applyRoutine();
  }
  
  public boolean addCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    mir.routines.iC_ReactOnAttributeChanges.AddCAEXCorrespondenceRoutine effect = new mir.routines.iC_ReactOnAttributeChanges.AddCAEXCorrespondenceRoutine(this.executionState, calledBy, a, b);
    return effect.applyRoutine();
  }
}
