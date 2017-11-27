package mir.routines.iC_ReactToProtoChanges;

import CAEX.CAEXObject;
import CAEX.SystemUnitClass;
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
  
  public boolean correctPathsSystemUnitClassClones(final SystemUnitClass affectedEObject) {
    mir.routines.iC_ReactToProtoChanges.CorrectPathsSystemUnitClassClonesRoutine effect = new mir.routines.iC_ReactToProtoChanges.CorrectPathsSystemUnitClassClonesRoutine(this.executionState, calledBy, affectedEObject);
    return effect.applyRoutine();
  }
  
  public boolean correctSystemUnitClassClones(final SystemUnitClass changedPrototype, final EStructuralFeature feature, final Object newValue) {
    mir.routines.iC_ReactToProtoChanges.CorrectSystemUnitClassClonesRoutine effect = new mir.routines.iC_ReactToProtoChanges.CorrectSystemUnitClassClonesRoutine(this.executionState, calledBy, changedPrototype, feature, newValue);
    return effect.applyRoutine();
  }
  
  public boolean addCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    mir.routines.iC_ReactToProtoChanges.AddCAEXCorrespondenceRoutine effect = new mir.routines.iC_ReactToProtoChanges.AddCAEXCorrespondenceRoutine(this.executionState, calledBy, a, b);
    return effect.applyRoutine();
  }
  
  public boolean updateFeature(final EObject obj, final EStructuralFeature feature, final Object newValue) {
    mir.routines.iC_ReactToProtoChanges.UpdateFeatureRoutine effect = new mir.routines.iC_ReactToProtoChanges.UpdateFeatureRoutine(this.executionState, calledBy, obj, feature, newValue);
    return effect.applyRoutine();
  }
  
  public boolean setFeatureValue(final EObject e, final EStructuralFeature f, final Object o) {
    mir.routines.iC_ReactToProtoChanges.SetFeatureValueRoutine effect = new mir.routines.iC_ReactToProtoChanges.SetFeatureValueRoutine(this.executionState, calledBy, e, f, o);
    return effect.applyRoutine();
  }
}
