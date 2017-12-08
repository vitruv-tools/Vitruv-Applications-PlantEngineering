package mir.routines.iC_ReactToAttributeChanges;

import CAEX.Attribute;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import mir.routines.iC_ReactToAttributeChanges.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class CorrectAttributeCloneFeaturesRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private CorrectAttributeCloneFeaturesRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getCorrepondenceSourceCorres(final Attribute of, final EStructuralFeature feature, final Object newValue) {
      return of;
    }
    
    public void callRoutine1(final Attribute of, final EStructuralFeature feature, final Object newValue, final List<Attribute> corres, @Extension final RoutinesFacade _routinesFacade) {
      final Consumer<Attribute> _function = (Attribute it) -> {
        _routinesFacade.updateFeature(it, feature, newValue);
      };
      corres.forEach(_function);
      final Consumer<Attribute> _function_1 = (Attribute it) -> {
        Resource _eResource = it.eResource();
        _eResource.setModified(true);
      };
      corres.forEach(_function_1);
    }
  }
  
  public CorrectAttributeCloneFeaturesRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final Attribute of, final EStructuralFeature feature, final Object newValue) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_ReactToAttributeChanges.CorrectAttributeCloneFeaturesRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.iC_ReactToAttributeChanges.RoutinesFacade(getExecutionState(), this);
    this.of = of;this.feature = feature;this.newValue = newValue;
  }
  
  private Attribute of;
  
  private EStructuralFeature feature;
  
  private Object newValue;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine CorrectAttributeCloneFeaturesRoutine with input:");
    getLogger().debug("   of: " + this.of);
    getLogger().debug("   feature: " + this.feature);
    getLogger().debug("   newValue: " + this.newValue);
    
    List<CAEX.Attribute> corres = getCorrespondingElements(
    	userExecution.getCorrepondenceSourceCorres(of, feature, newValue), // correspondence source supplier
    	CAEX.Attribute.class,
    	(CAEX.Attribute _element) -> true, // correspondence precondition checker
    	null
    );
    for (EObject _element : corres) {	
    	registerObjectUnderModification(_element);
    }
    userExecution.callRoutine1(of, feature, newValue, corres, actionsFacade);
    
    postprocessElements();
    
    return true;
  }
}
