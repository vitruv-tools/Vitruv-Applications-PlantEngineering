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
import tools.vitruv.framework.change.echange.compound.CreateAndInsertNonRoot;
import tools.vitruv.framework.change.echange.feature.reference.InsertEReference;
import tools.vitruv.framework.userinteraction.UserInteracting;

@SuppressWarnings("all")
class CreatedFileHeaderTypeReaction extends AbstractReactionRealization {
  public CreatedFileHeaderTypeReaction(final UserInteracting userInteracting) {
    super(userInteracting);
  }
  
  public void executeReaction(final EChange change) {
    InsertEReference<ProjectType, FileHeaderType> typedChange = ((CreateAndInsertNonRoot<ProjectType, FileHeaderType>)change).getInsertChange();
    ProjectType affectedEObject = typedChange.getAffectedEObject();
    EReference affectedFeature = typedChange.getAffectedFeature();
    FileHeaderType newValue = typedChange.getNewValue();
    mir.routines.plcopen2caex.RoutinesFacade routinesFacade = new mir.routines.plcopen2caex.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.CreatedFileHeaderTypeReaction.ActionUserExecution userExecution = new mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.CreatedFileHeaderTypeReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(affectedEObject, affectedFeature, newValue, routinesFacade);
  }
  
  public static Class<? extends EChange> getExpectedChangeType() {
    return CreateAndInsertNonRoot.class;
  }
  
  private boolean checkChangeProperties(final EChange change) {
    InsertEReference<ProjectType, FileHeaderType> relevantChange = ((CreateAndInsertNonRoot<ProjectType, FileHeaderType>)change).getInsertChange();
    if (!(relevantChange.getAffectedEObject() instanceof ProjectType)) {
    	return false;
    }
    if (!relevantChange.getAffectedFeature().getName().equals("fileHeader")) {
    	return false;
    }
    if (!(relevantChange.getNewValue() instanceof FileHeaderType)) {
    	return false;
    }
    return true;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (!(change instanceof CreateAndInsertNonRoot)) {
    	return false;
    }
    getLogger().debug("Passed change type check of reaction " + this.getClass().getName());
    if (!checkChangeProperties(change)) {
    	return false;
    }
    getLogger().debug("Passed change properties check of reaction " + this.getClass().getName());
    getLogger().debug("Passed complete precondition check of reaction " + this.getClass().getName());
    return true;
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
