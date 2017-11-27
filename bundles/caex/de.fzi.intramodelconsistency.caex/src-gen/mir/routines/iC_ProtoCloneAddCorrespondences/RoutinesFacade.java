package mir.routines.iC_ProtoCloneAddCorrespondences;

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
  
  public boolean newCorrespondenceCloneProto(final InternalElement ie, final String sucStr) {
    mir.routines.iC_ProtoCloneAddCorrespondences.NewCorrespondenceCloneProtoRoutine effect = new mir.routines.iC_ProtoCloneAddCorrespondences.NewCorrespondenceCloneProtoRoutine(this.executionState, calledBy, ie, sucStr);
    return effect.applyRoutine();
  }
  
  public boolean removeCorrespondencesofSystemUnitClasses(final InternalElement affectedElement) {
    mir.routines.iC_ProtoCloneAddCorrespondences.RemoveCorrespondencesofSystemUnitClassesRoutine effect = new mir.routines.iC_ProtoCloneAddCorrespondences.RemoveCorrespondencesofSystemUnitClassesRoutine(this.executionState, calledBy, affectedElement);
    return effect.applyRoutine();
  }
  
  public boolean removeAllClonesAndCorrespondences(final SystemUnitClass affectedClass) {
    mir.routines.iC_ProtoCloneAddCorrespondences.RemoveAllClonesAndCorrespondencesRoutine effect = new mir.routines.iC_ProtoCloneAddCorrespondences.RemoveAllClonesAndCorrespondencesRoutine(this.executionState, calledBy, affectedClass);
    return effect.applyRoutine();
  }
  
  public boolean removeCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    mir.routines.iC_ProtoCloneAddCorrespondences.RemoveCAEXCorrespondenceRoutine effect = new mir.routines.iC_ProtoCloneAddCorrespondences.RemoveCAEXCorrespondenceRoutine(this.executionState, calledBy, a, b);
    return effect.applyRoutine();
  }
  
  public boolean addCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    mir.routines.iC_ProtoCloneAddCorrespondences.AddCAEXCorrespondenceRoutine effect = new mir.routines.iC_ProtoCloneAddCorrespondences.AddCAEXCorrespondenceRoutine(this.executionState, calledBy, a, b);
    return effect.applyRoutine();
  }
  
  public boolean deleteElement(final CAEXObject o) {
    mir.routines.iC_ProtoCloneAddCorrespondences.DeleteElementRoutine effect = new mir.routines.iC_ProtoCloneAddCorrespondences.DeleteElementRoutine(this.executionState, calledBy, o);
    return effect.applyRoutine();
  }
}
