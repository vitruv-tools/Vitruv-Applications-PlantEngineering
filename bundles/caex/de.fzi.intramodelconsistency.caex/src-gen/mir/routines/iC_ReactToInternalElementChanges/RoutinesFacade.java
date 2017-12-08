package mir.routines.iC_ReactToInternalElementChanges;

import CAEX.CAEXObject;
import CAEX.InternalElement;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutinesFacade;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RoutinesFacade extends AbstractRepairRoutinesFacade {
  public RoutinesFacade(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
    super(reactionExecutionState, calledBy);
  }
  
  public boolean newCorrespondenceCloneProto(final InternalElement ie, final String sucStr) {
    mir.routines.iC_ReactToInternalElementChanges.NewCorrespondenceCloneProtoRoutine effect = new mir.routines.iC_ReactToInternalElementChanges.NewCorrespondenceCloneProtoRoutine(this.executionState, calledBy, ie, sucStr);
    return effect.applyRoutine();
  }
  
  public boolean removeCorrespondencesofSystemUnitClasses(final InternalElement affectedElement) {
    mir.routines.iC_ReactToInternalElementChanges.RemoveCorrespondencesofSystemUnitClassesRoutine effect = new mir.routines.iC_ReactToInternalElementChanges.RemoveCorrespondencesofSystemUnitClassesRoutine(this.executionState, calledBy, affectedElement);
    return effect.applyRoutine();
  }
  
  public boolean removeCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    mir.routines.iC_ReactToInternalElementChanges.RemoveCAEXCorrespondenceRoutine effect = new mir.routines.iC_ReactToInternalElementChanges.RemoveCAEXCorrespondenceRoutine(this.executionState, calledBy, a, b);
    return effect.applyRoutine();
  }
  
  public boolean addCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    mir.routines.iC_ReactToInternalElementChanges.AddCAEXCorrespondenceRoutine effect = new mir.routines.iC_ReactToInternalElementChanges.AddCAEXCorrespondenceRoutine(this.executionState, calledBy, a, b);
    return effect.applyRoutine();
  }
}
