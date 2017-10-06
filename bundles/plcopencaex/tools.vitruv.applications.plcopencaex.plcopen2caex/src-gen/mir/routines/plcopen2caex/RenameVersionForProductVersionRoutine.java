package mir.routines.plcopen2caex;

import CAEX.Version;
import java.io.IOException;
import mir.routines.plcopen2caex.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.plcopen.xml.tc60201.FileHeaderType;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RenameVersionForProductVersionRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private RenameVersionForProductVersionRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final FileHeaderType fileHeaderType, final Version version) {
      return version;
    }
    
    public void update0Element(final FileHeaderType fileHeaderType, final Version version) {
      version.setValue(fileHeaderType.getProductVersion());
    }
    
    public EObject getCorrepondenceSourceVersion(final FileHeaderType fileHeaderType) {
      return fileHeaderType;
    }
  }
  
  public RenameVersionForProductVersionRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final FileHeaderType fileHeaderType) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.plcopen2caex.RenameVersionForProductVersionRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.plcopen2caex.RoutinesFacade(getExecutionState(), this);
    this.fileHeaderType = fileHeaderType;
  }
  
  private FileHeaderType fileHeaderType;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine RenameVersionForProductVersionRoutine with input:");
    getLogger().debug("   fileHeaderType: " + this.fileHeaderType);
    
    CAEX.Version version = getCorrespondingElement(
    	userExecution.getCorrepondenceSourceVersion(fileHeaderType), // correspondence source supplier
    	CAEX.Version.class,
    	(CAEX.Version _element) -> true, // correspondence precondition checker
    	null, 
    	false // asserted
    	);
    if (version == null) {
    	return false;
    }
    registerObjectUnderModification(version);
    // val updatedElement userExecution.getElement1(fileHeaderType, version);
    userExecution.update0Element(fileHeaderType, version);
    
    postprocessElements();
    
    return true;
  }
}
