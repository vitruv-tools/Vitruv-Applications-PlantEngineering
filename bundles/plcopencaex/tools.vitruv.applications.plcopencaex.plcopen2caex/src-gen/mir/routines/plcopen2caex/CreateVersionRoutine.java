package mir.routines.plcopen2caex;

import CAEX.Version;
import CAEX.impl.CAEXFactoryImpl;
import java.io.IOException;
import mir.routines.plcopen2caex.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.plcopen.xml.tc60201.FileHeaderType;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class CreateVersionRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
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
  
  public CreateVersionRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final FileHeaderType fileHeaderType) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.plcopen2caex.CreateVersionRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.plcopen2caex.RoutinesFacade(getExecutionState(), this);
    this.fileHeaderType = fileHeaderType;
  }
  
  private FileHeaderType fileHeaderType;
  
  protected void executeRoutine() throws IOException {
    getLogger().debug("Called routine CreateVersionRoutine with input:");
    getLogger().debug("   FileHeaderType: " + this.fileHeaderType);
    
    Version version = CAEXFactoryImpl.eINSTANCE.createVersion();
    
    addCorrespondenceBetween(userExecution.getElement1(fileHeaderType, version), userExecution.getElement2(fileHeaderType, version), "");
    
    postprocessElements();
  }
}
