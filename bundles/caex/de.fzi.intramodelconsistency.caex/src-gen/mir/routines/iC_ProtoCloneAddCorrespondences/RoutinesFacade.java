package mir.routines.iC_ProtoCloneAddCorrespondences;

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
    mir.routines.iC_ProtoCloneAddCorrespondences.NewCorrespondenceCloneProtoRoutine effect = new mir.routines.iC_ProtoCloneAddCorrespondences.NewCorrespondenceCloneProtoRoutine(this.executionState, calledBy, ie, sucStr);
    return effect.applyRoutine();
  }
  
  public boolean addCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    mir.routines.iC_ProtoCloneAddCorrespondences.AddCAEXCorrespondenceRoutine effect = new mir.routines.iC_ProtoCloneAddCorrespondences.AddCAEXCorrespondenceRoutine(this.executionState, calledBy, a, b);
    return effect.applyRoutine();
  }
}
