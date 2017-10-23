package mir.routines.iC_ReactToProtoChanges;

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
  
  public boolean correctSystemUnitClassClones(final SystemUnitClass changedPrototype, final EStructuralFeature feature, final Object newValue) {
    mir.routines.iC_ReactToProtoChanges.CorrectSystemUnitClassClonesRoutine effect = new mir.routines.iC_ReactToProtoChanges.CorrectSystemUnitClassClonesRoutine(this.executionState, calledBy, changedPrototype, feature, newValue);
    return effect.applyRoutine();
  }
  
  public boolean updateFeature(final EObject obj, final EStructuralFeature feature, final Object newValue) {
    mir.routines.iC_ReactToProtoChanges.UpdateFeatureRoutine effect = new mir.routines.iC_ReactToProtoChanges.UpdateFeatureRoutine(this.executionState, calledBy, obj, feature, newValue);
    return effect.applyRoutine();
  }
}
