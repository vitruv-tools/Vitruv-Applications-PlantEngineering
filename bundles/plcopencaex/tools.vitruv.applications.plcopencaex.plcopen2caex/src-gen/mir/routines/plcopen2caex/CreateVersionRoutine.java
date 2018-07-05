package mir.routines.plcopen2caex;

import java.io.IOException;
import mir.routines.plcopen2caex.RoutinesFacade;
import org.automationml.caex.caex.Version;
import org.eclipse.emf.ecore.EObject;
import org.plcopen.xml.tc60201.FileHeaderType;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class CreateVersionRoutine extends AbstractRepairRoutineRealization {
  private CreateVersionRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final FileHeaderType fileHeaderType, final Version version) {
      return fileHeaderType;
    }
    
    public EObject getElement2(final FileHeaderType fileHeaderType, final Version version) {
      return version;
    }
  }
  
  public CreateVersionRoutine(final RoutinesFacade routinesFacade, final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final FileHeaderType fileHeaderType) {
    super(routinesFacade, reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.plcopen2caex.CreateVersionRoutine.ActionUserExecution(getExecutionState(), this);
    this.fileHeaderType = fileHeaderType;
  }
  
  private FileHeaderType fileHeaderType;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine CreateVersionRoutine with input:");
    getLogger().debug("   fileHeaderType: " + this.fileHeaderType);
    
    org.automationml.caex.caex.Version version = org.automationml.caex.caex.impl.CaexFactoryImpl.eINSTANCE.createVersion();
    notifyObjectCreated(version);
    
    addCorrespondenceBetween(userExecution.getElement1(fileHeaderType, version), userExecution.getElement2(fileHeaderType, version), "");
    
    postprocessElements();
    
    return true;
  }
}
