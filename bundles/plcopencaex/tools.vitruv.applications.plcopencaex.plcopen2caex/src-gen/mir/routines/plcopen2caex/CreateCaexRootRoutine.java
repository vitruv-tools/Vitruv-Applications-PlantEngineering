package mir.routines.plcopen2caex;

import java.io.IOException;
import mir.routines.plcopen2caex.RoutinesFacade;
import org.automationml.caex.caex.CAEXFile;
import org.eclipse.emf.ecore.EObject;
import org.plcopen.xml.tc60201.DocumentRoot;
import org.plcopen.xml.tc60201.ProjectType;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class CreateCaexRootRoutine extends AbstractRepairRoutineRealization {
  private CreateCaexRootRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final DocumentRoot plcopenRoot, final CAEXFile caexFile) {
      ProjectType _project = plcopenRoot.getProject();
      return _project;
    }
    
    public EObject getElement4(final DocumentRoot plcopenRoot, final CAEXFile caexFile, final org.automationml.caex.caex.DocumentRoot caexRoot) {
      return plcopenRoot;
    }
    
    public EObject getElement2(final DocumentRoot plcopenRoot, final CAEXFile caexFile) {
      return caexFile;
    }
    
    public void updateCaexRootElement(final DocumentRoot plcopenRoot, final CAEXFile caexFile, final org.automationml.caex.caex.DocumentRoot caexRoot) {
      caexRoot.setCAEXFile(caexFile);
      this.persistProjectRelative(plcopenRoot, caexRoot, "caexModel.caex");
    }
    
    public EObject getElement3(final DocumentRoot plcopenRoot, final CAEXFile caexFile, final org.automationml.caex.caex.DocumentRoot caexRoot) {
      return caexRoot;
    }
    
    public boolean checkMatcherPrecondition1(final DocumentRoot plcopenRoot) {
      ProjectType _project = plcopenRoot.getProject();
      boolean _tripleNotEquals = (_project != null);
      return _tripleNotEquals;
    }
  }
  
  public CreateCaexRootRoutine(final RoutinesFacade routinesFacade, final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final DocumentRoot plcopenRoot) {
    super(routinesFacade, reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.plcopen2caex.CreateCaexRootRoutine.ActionUserExecution(getExecutionState(), this);
    this.plcopenRoot = plcopenRoot;
  }
  
  private DocumentRoot plcopenRoot;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine CreateCaexRootRoutine with input:");
    getLogger().debug("   plcopenRoot: " + this.plcopenRoot);
    
    if (!userExecution.checkMatcherPrecondition1(plcopenRoot)) {
    	return false;
    }
    org.automationml.caex.caex.CAEXFile caexFile = org.automationml.caex.caex.impl.CaexFactoryImpl.eINSTANCE.createCAEXFile();
    notifyObjectCreated(caexFile);
    
    addCorrespondenceBetween(userExecution.getElement1(plcopenRoot, caexFile), userExecution.getElement2(plcopenRoot, caexFile), "");
    
    org.automationml.caex.caex.DocumentRoot caexRoot = org.automationml.caex.caex.impl.CaexFactoryImpl.eINSTANCE.createDocumentRoot();
    notifyObjectCreated(caexRoot);
    userExecution.updateCaexRootElement(plcopenRoot, caexFile, caexRoot);
    
    addCorrespondenceBetween(userExecution.getElement3(plcopenRoot, caexFile, caexRoot), userExecution.getElement4(plcopenRoot, caexFile, caexRoot), "");
    
    postprocessElements();
    
    return true;
  }
}
