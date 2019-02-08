package mir.routines.iC_Utilities;

import caex.caex30.caex.CAEXObject;
import java.io.IOException;
import java.util.Collection;
import mir.routines.iC_Utilities.RoutinesFacade;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class UpdateFeatureRoutine extends AbstractRepairRoutineRealization {
  private UpdateFeatureRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final EObject obj, final EStructuralFeature feature, final Object newValue, @Extension final RoutinesFacade _routinesFacade) {
      final Object featureValue = obj.eGet(feature);
      if ((featureValue instanceof EList)) {
        final EObject clone = EcoreUtil.<EObject>copy(((EObject) newValue));
        ((EList)featureValue).add(clone);
        Object[] _clone = ((Object[])Conversions.unwrapArray(featureValue, Object.class)).clone();
        BasicEList<Object> _basicEList = new BasicEList<Object>((Collection<?>)Conversions.doWrapArray(_clone));
        _routinesFacade.setFeatureValue(obj, feature, _basicEList);
        _routinesFacade.addCAEXCorrespondence(((CAEXObject) clone), ((CAEXObject) newValue));
      } else {
        _routinesFacade.setFeatureValue(obj, feature, newValue);
      }
    }
  }
  
  public UpdateFeatureRoutine(final RoutinesFacade routinesFacade, final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final EObject obj, final EStructuralFeature feature, final Object newValue) {
    super(routinesFacade, reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.iC_Utilities.UpdateFeatureRoutine.ActionUserExecution(getExecutionState(), this);
    this.obj = obj;this.feature = feature;this.newValue = newValue;
  }
  
  private EObject obj;
  
  private EStructuralFeature feature;
  
  private Object newValue;
  
  protected boolean executeRoutine() throws IOException {
    getLogger().debug("Called routine UpdateFeatureRoutine with input:");
    getLogger().debug("   obj: " + this.obj);
    getLogger().debug("   feature: " + this.feature);
    getLogger().debug("   newValue: " + this.newValue);
    
    userExecution.callRoutine1(obj, feature, newValue, this.getRoutinesFacade());
    
    postprocessElements();
    
    return true;
  }
}
