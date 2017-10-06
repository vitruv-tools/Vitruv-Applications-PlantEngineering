package mir.reactions.reactionsPLCOpenToCaex.plcopen2caex;

import mir.routines.plcopen2caex.RoutinesFacade;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.lib.Extension;
import org.plcopen.xml.tc60201.FileHeaderType;
import org.plcopen.xml.tc60201.ProjectType;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.eobject.CreateEObject;
import tools.vitruv.framework.change.echange.feature.reference.InsertEReference;

@SuppressWarnings("all")
class CreatedFileHeaderTypeReaction extends AbstractReactionRealization {
  private CreateEObject<FileHeaderType> createChange;
  
  private InsertEReference<ProjectType, FileHeaderType> insertChange;
  
  private int currentlyMatchedChange;
  
  public void executeReaction(final EChange change) {
    if (!checkPrecondition(change)) {
    	return;
    }
    org.plcopen.xml.tc60201.ProjectType affectedEObject = insertChange.getAffectedEObject();
    EReference affectedFeature = insertChange.getAffectedFeature();
    org.plcopen.xml.tc60201.FileHeaderType newValue = insertChange.getNewValue();
    				
    getLogger().trace("Passed complete precondition check of Reaction " + this.getClass().getName());
    				
    mir.routines.plcopen2caex.RoutinesFacade routinesFacade = new mir.routines.plcopen2caex.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.CreatedFileHeaderTypeReaction.ActionUserExecution userExecution = new mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.CreatedFileHeaderTypeReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(affectedEObject, affectedFeature, newValue, routinesFacade);
    
    resetChanges();
  }
  
  private void resetChanges() {
    createChange = null;
    insertChange = null;
    currentlyMatchedChange = 0;
  }
  
  private boolean matchCreateChange(final EChange change) {
    if (change instanceof CreateEObject<?>) {
    	CreateEObject<org.plcopen.xml.tc60201.FileHeaderType> _localTypedChange = (CreateEObject<org.plcopen.xml.tc60201.FileHeaderType>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof org.plcopen.xml.tc60201.FileHeaderType)) {
    		return false;
    	}
    	this.createChange = (CreateEObject<org.plcopen.xml.tc60201.FileHeaderType>) change;
    	return true;
    }
    
    return false;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (currentlyMatchedChange == 0) {
    	if (!matchCreateChange(change)) {
    		resetChanges();
    		return false;
    	} else {
    		currentlyMatchedChange++;
    	}
    	return false; // Only proceed on the last of the expected changes
    }
    if (currentlyMatchedChange == 1) {
    	if (!matchInsertChange(change)) {
    		resetChanges();
    		checkPrecondition(change); // Reexecute to potentially register this as first change
    		return false;
    	} else {
    		currentlyMatchedChange++;
    	}
    }
    
    return true;
  }
  
  private boolean matchInsertChange(final EChange change) {
    if (change instanceof InsertEReference<?, ?>) {
    	InsertEReference<org.plcopen.xml.tc60201.ProjectType, org.plcopen.xml.tc60201.FileHeaderType> _localTypedChange = (InsertEReference<org.plcopen.xml.tc60201.ProjectType, org.plcopen.xml.tc60201.FileHeaderType>) change;
    	if (!(_localTypedChange.getAffectedEObject() instanceof org.plcopen.xml.tc60201.ProjectType)) {
    		return false;
    	}
    	if (!_localTypedChange.getAffectedFeature().getName().equals("fileHeader")) {
    		return false;
    	}
    	if (!(_localTypedChange.getNewValue() instanceof org.plcopen.xml.tc60201.FileHeaderType)) {
    		return false;
    	}
    	this.insertChange = (InsertEReference<org.plcopen.xml.tc60201.ProjectType, org.plcopen.xml.tc60201.FileHeaderType>) change;
    	return true;
    }
    
    return false;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final ProjectType affectedEObject, final EReference affectedFeature, final FileHeaderType newValue, @Extension final RoutinesFacade _routinesFacade) {
      final FileHeaderType fileHeaderType = newValue;
      _routinesFacade.createVersion(fileHeaderType);
    }
  }
}
