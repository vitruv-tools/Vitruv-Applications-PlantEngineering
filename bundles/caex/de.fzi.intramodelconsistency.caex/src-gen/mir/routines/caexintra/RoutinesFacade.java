package mir.routines.caexintra;

import CAEX.CAEXObject;
import CAEX.InternalElement;
import CAEX.SystemUnitClass;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutinesFacade;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RoutinesFacade extends AbstractRepairRoutinesFacade {
  public RoutinesFacade(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
    super(reactionExecutionState, calledBy);
  }
  
  public void newCorrespondenceCloneProto(final InternalElement ie, final String sucStr) {
    mir.routines.caexintra.NewCorrespondenceCloneProtoRoutine effect = new mir.routines.caexintra.NewCorrespondenceCloneProtoRoutine(this.executionState, calledBy, ie, sucStr);
    effect.applyRoutine();
  }
  
  public void addCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    mir.routines.caexintra.AddCAEXCorrespondenceRoutine effect = new mir.routines.caexintra.AddCAEXCorrespondenceRoutine(this.executionState, calledBy, a, b);
    effect.applyRoutine();
  }
  
  public void correctCloneName(final SystemUnitClass change, final String newValue) {
    mir.routines.caexintra.CorrectCloneNameRoutine effect = new mir.routines.caexintra.CorrectCloneNameRoutine(this.executionState, calledBy, change, newValue);
    effect.applyRoutine();
  }
}
