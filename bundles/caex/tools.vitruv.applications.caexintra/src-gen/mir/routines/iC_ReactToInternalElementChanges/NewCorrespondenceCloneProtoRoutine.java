package mir.routines.iC_ReactToInternalElementChanges;

import caex.caex30.caex.CAEXObject;
import caex.caex30.caex.InternalElement;
import java.io.IOException;
import mir.routines.iC_ReactToInternalElementChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.applications.caexintra.CAEXIntraConsistencyTools;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.tuid.Tuid;

@SuppressWarnings("all")
public class NewCorrespondenceCloneProtoRoutine extends AbstractRepairRoutineRealization {
  private NewCorrespondenceCloneProtoRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final InternalElement ie, final String sucStr, @Extension final RoutinesFacade _routinesFacade) {
      if (((sucStr == null) || (sucStr == ""))) {
        return;
      }
      String affectedTuid = CAEXIntraConsistencyTools.generateTuidFromPath(this.correspondenceModel, ie, sucStr);
      EObject _resolveEObjectFromTuid = this.correspondenceModel.resolveEObjectFromTuid(Tuid.getInstance(affectedTuid));
      CAEXObject suc = ((CAEXObject) _resolveEObjectFromTuid);
      if ((suc != null)) {
        _routinesFacade.addCAEXCorrespondence(ie, suc);
      }
    }
  }
  
  public NewCorrespondenceCloneProtoRoutine(final RoutinesFacade routinesFacade, final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final InternalElement ie, final String sucStr) {
    super(routinesFacade, reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_ReactToInternalElementChanges.NewCorrespondenceCloneProtoRoutine.ActionUserExecution(getExecutionState(), this);
    this.ie = ie;this.sucStr = sucStr;
  }
  
  private InternalElement ie;
  
  private String sucStr;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine NewCorrespondenceCloneProtoRoutine with input:");
    getLogger().debug("   ie: " + this.ie);
    getLogger().debug("   sucStr: " + this.sucStr);
    
    userExecution.callRoutine1(ie, sucStr, this.getRoutinesFacade());
    
    postprocessElements();
    
    return true;
  }
}
