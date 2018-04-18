package mir.routines.iC_ReactToAttributeChanges;

import caex.caex30.caex.Attribute;
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
      boolean _isEmpty = corres.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        Resource _eResource = corres.get(0).eResource();
        _eResource.setModified(true);
      }
    }
  }
  
  public CorrectAttributeCloneFeaturesRoutine(final RoutinesFacade routinesFacade, final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final Attribute of, final EStructuralFeature feature, final Object newValue) {
    super(routinesFacade, reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_ReactToAttributeChanges.CorrectAttributeCloneFeaturesRoutine.ActionUserExecution(getExecutionState(), this);
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
    
    List<caex.caex30.caex.Attribute> corres = getCorrespondingElements(
    	userExecution.getCorrepondenceSourceCorres(of, feature, newValue), // correspondence source supplier
    	caex.caex30.caex.Attribute.class,
    	(caex.caex30.caex.Attribute _element) -> true, // correspondence precondition checker
    	null
    );
    for (EObject _element : corres) {	
    	registerObjectUnderModification(_element);
    }
    userExecution.callRoutine1(of, feature, newValue, corres, this.getRoutinesFacade());
    
    postprocessElements();
    
    return true;
  }
}
