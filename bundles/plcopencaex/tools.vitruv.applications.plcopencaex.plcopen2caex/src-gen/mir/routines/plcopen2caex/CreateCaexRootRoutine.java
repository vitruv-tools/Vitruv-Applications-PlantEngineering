package mir.routines.plcopen2caex;

import CAEX.CAEXFile;
import com.google.common.base.Objects;
import java.io.IOException;
import mir.routines.plcopen2caex.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.plcopen.xml.tc60201.DocumentRoot;
import org.plcopen.xml.tc60201.ProjectType;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class CreateCaexRootRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private CreateCaexRootRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final DocumentRoot plcopenRoot, final CAEXFile caexFile) {
      ProjectType _project = plcopenRoot.getProject();
      return _project;
    }
    
    public EObject getElement4(final DocumentRoot plcopenRoot, final CAEXFile caexFile, final CAEX.DocumentRoot caexRoot) {
      return plcopenRoot;
    }
    
    public EObject getElement2(final DocumentRoot plcopenRoot, final CAEXFile caexFile) {
      return caexFile;
    }
    
    public void updateCaexRootElement(final DocumentRoot plcopenRoot, final CAEXFile caexFile, final CAEX.DocumentRoot caexRoot) {
      caexRoot.setCAEXFile(caexFile);
      this.persistProjectRelative(plcopenRoot, caexRoot, "caexModel.caex");
    }
    
    public EObject getElement3(final DocumentRoot plcopenRoot, final CAEXFile caexFile, final CAEX.DocumentRoot caexRoot) {
      return caexRoot;
    }
    
    public boolean checkMatcherPrecondition1(final DocumentRoot plcopenRoot) {
      ProjectType _project = plcopenRoot.getProject();
      boolean _notEquals = (!Objects.equal(_project, null));
      return _notEquals;
    }
  }
  
  public CreateCaexRootRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final DocumentRoot plcopenRoot) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.plcopen2caex.CreateCaexRootRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.plcopen2caex.RoutinesFacade(getExecutionState(), this);
    this.plcopenRoot = plcopenRoot;
  }
  
  private DocumentRoot plcopenRoot;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine CreateCaexRootRoutine with input:");
    getLogger().debug("   plcopenRoot: " + this.plcopenRoot);
    
    if (!userExecution.checkMatcherPrecondition1(plcopenRoot)) {
    	return false;
    }
    CAEX.CAEXFile caexFile = CAEX.impl.CAEXFactoryImpl.eINSTANCE.createCAEXFile();
    notifyObjectCreated(caexFile);
    
    addCorrespondenceBetween(userExecution.getElement1(plcopenRoot, caexFile), userExecution.getElement2(plcopenRoot, caexFile), "");
    
    CAEX.DocumentRoot caexRoot = CAEX.impl.CAEXFactoryImpl.eINSTANCE.createDocumentRoot();
    notifyObjectCreated(caexRoot);
    userExecution.updateCaexRootElement(plcopenRoot, caexFile, caexRoot);
    
    addCorrespondenceBetween(userExecution.getElement3(plcopenRoot, caexFile, caexRoot), userExecution.getElement4(plcopenRoot, caexFile, caexRoot), "");
    
    postprocessElements();
    
    return true;
  }
}
