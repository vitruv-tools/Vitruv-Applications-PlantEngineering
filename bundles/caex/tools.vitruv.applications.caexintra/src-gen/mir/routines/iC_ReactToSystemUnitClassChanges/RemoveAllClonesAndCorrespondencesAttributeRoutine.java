package mir.routines.iC_ReactToSystemUnitClassChanges;

import caex.caex30.caex.Attribute;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RemoveAllClonesAndCorrespondencesAttributeRoutine extends AbstractRepairRoutineRealization {
  private RemoveAllClonesAndCorrespondencesAttributeRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getCorrepondenceSourceClones(final Attribute affectedAttribute) {
      return affectedAttribute;
    }
    
    public void callRoutine1(final Attribute affectedAttribute, final List<Attribute> clones, @Extension final RoutinesFacade _routinesFacade) {
      boolean _isEmpty = clones.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        Resource _eResource = clones.get(0).eResource();
        _eResource.setModified(true);
      }
      final Consumer<Attribute> _function = (Attribute it) -> {
        _routinesFacade.removeCAEXCorrespondence(it, affectedAttribute);
      };
      clones.forEach(_function);
      final Consumer<Attribute> _function_1 = (Attribute it) -> {
        _routinesFacade.deleteElement(it);
      };
      clones.forEach(_function_1);
    }
  }
  
  public RemoveAllClonesAndCorrespondencesAttributeRoutine(final RoutinesFacade routinesFacade, final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final Attribute affectedAttribute) {
    super(routinesFacade, reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_ReactToSystemUnitClassChanges.RemoveAllClonesAndCorrespondencesAttributeRoutine.ActionUserExecution(getExecutionState(), this);
    this.affectedAttribute = affectedAttribute;
  }
  
  private Attribute affectedAttribute;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine RemoveAllClonesAndCorrespondencesAttributeRoutine with input:");
    getLogger().debug("   affectedAttribute: " + this.affectedAttribute);
    
    List<caex.caex30.caex.Attribute> clones = getCorrespondingElements(
    	userExecution.getCorrepondenceSourceClones(affectedAttribute), // correspondence source supplier
    	caex.caex30.caex.Attribute.class,
    	(caex.caex30.caex.Attribute _element) -> true, // correspondence precondition checker
    	null
    );
    for (EObject _element : clones) {	
    	registerObjectUnderModification(_element);
    }
    userExecution.callRoutine1(affectedAttribute, clones, this.getRoutinesFacade());
    
    postprocessElements();
    
    return true;
  }
}
