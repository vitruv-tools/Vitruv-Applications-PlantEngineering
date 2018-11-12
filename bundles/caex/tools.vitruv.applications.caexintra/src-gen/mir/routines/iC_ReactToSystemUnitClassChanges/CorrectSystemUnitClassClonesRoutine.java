package mir.routines.iC_ReactToSystemUnitClassChanges;

import caex.caex30.caex.InternalElement;
import caex.caex30.caex.SystemUnitClass;
import java.io.IOException;
import java.util.List;
import mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class CorrectSystemUnitClassClonesRoutine extends AbstractRepairRoutineRealization {
  private CorrectSystemUnitClassClonesRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getCorrepondenceSourceClones(final SystemUnitClass changedPrototype, final EStructuralFeature feature, final Object newValue) {
      return changedPrototype;
    }
    
    public void callRoutine1(final SystemUnitClass changedPrototype, final EStructuralFeature feature, final Object newValue, final List<InternalElement> clones, @Extension final RoutinesFacade _routinesFacade) {
      for (final InternalElement clon : clones) {
        {
          _routinesFacade.updateFeature(clon, feature, newValue);
          Resource _eResource = clon.eResource();
          _eResource.setModified(true);
        }
      }
    }
  }
  
  public CorrectSystemUnitClassClonesRoutine(final RoutinesFacade routinesFacade, final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final SystemUnitClass changedPrototype, final EStructuralFeature feature, final Object newValue) {
    super(routinesFacade, reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_ReactToSystemUnitClassChanges.CorrectSystemUnitClassClonesRoutine.ActionUserExecution(getExecutionState(), this);
    this.changedPrototype = changedPrototype;this.feature = feature;this.newValue = newValue;
  }
  
  private SystemUnitClass changedPrototype;
  
  private EStructuralFeature feature;
  
  private Object newValue;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine CorrectSystemUnitClassClonesRoutine with input:");
    getLogger().debug("   changedPrototype: " + this.changedPrototype);
    getLogger().debug("   feature: " + this.feature);
    getLogger().debug("   newValue: " + this.newValue);
    
    List<caex.caex30.caex.InternalElement> clones = getCorrespondingElements(
    	userExecution.getCorrepondenceSourceClones(changedPrototype, feature, newValue), // correspondence source supplier
    	caex.caex30.caex.InternalElement.class,
    	(caex.caex30.caex.InternalElement _element) -> true, // correspondence precondition checker
    	null
    );
    for (EObject _element : clones) {	
    	registerObjectUnderModification(_element);
    }
    userExecution.callRoutine1(changedPrototype, feature, newValue, clones, this.getRoutinesFacade());
    
    postprocessElements();
    
    return true;
  }
}
