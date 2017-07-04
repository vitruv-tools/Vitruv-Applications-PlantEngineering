package mir.routines.caexintra;

import CAEX.CAEXObject;
import CAEX.InternalElement;
import com.google.common.base.Objects;
import de.fzi.intramodelconsistency.caex.CAEXIntraConsistencyTools;
import java.io.IOException;
import mir.routines.caexintra.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.tuid.Tuid;

@SuppressWarnings("all")
public class NewCorrespondenceCloneProtoRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private NewCorrespondenceCloneProtoRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final InternalElement ie, final String sucStr, @Extension final RoutinesFacade _routinesFacade) {
      InputOutput.<String>println("addingCorrespondance");
      String affectedTuid = CAEXIntraConsistencyTools.GenTuidFromPath(this.correspondenceModel, ie, sucStr);
      Tuid _instance = Tuid.getInstance(affectedTuid);
      EObject _resolveEObjectFromTuid = this.correspondenceModel.resolveEObjectFromTuid(_instance);
      CAEXObject suc = ((CAEXObject) _resolveEObjectFromTuid);
      boolean _notEquals = (!Objects.equal(suc, null));
      if (_notEquals) {
        _routinesFacade.addCAEXCorrespondence(ie, suc);
      }
    }
  }
  
  public NewCorrespondenceCloneProtoRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final InternalElement ie, final String sucStr) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.caexintra.NewCorrespondenceCloneProtoRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.caexintra.RoutinesFacade(getExecutionState(), this);
    this.ie = ie;this.sucStr = sucStr;
  }
  
  private InternalElement ie;
  
  private String sucStr;
  
  protected void executeRoutine() throws IOException {
    getLogger().debug("Called routine NewCorrespondenceCloneProtoRoutine with input:");
    getLogger().debug("   InternalElement: " + this.ie);
    getLogger().debug("   String: " + this.sucStr);
    
    userExecution.callRoutine1(ie, sucStr, actionsFacade);
    
    postprocessElements();
  }
}
