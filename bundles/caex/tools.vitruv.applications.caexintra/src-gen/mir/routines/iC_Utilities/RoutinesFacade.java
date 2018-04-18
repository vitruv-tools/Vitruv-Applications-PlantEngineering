package mir.routines.iC_Utilities;

import caex.caex30.caex.CAEXObject;
import mir.routines.iC_Utilities.AddCAEXCorrespondenceRoutine;
import mir.routines.iC_Utilities.DeleteElementRoutine;
import mir.routines.iC_Utilities.RemoveCAEXCorrespondenceRoutine;
import mir.routines.iC_Utilities.SetFeatureValueRoutine;
import mir.routines.iC_Utilities.UpdateFeatureRoutine;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutinesFacade;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.RoutinesFacadeExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.RoutinesFacadesProvider;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.extensions.dslsruntime.reactions.structure.ReactionsImportPath;

@SuppressWarnings("all")
public class RoutinesFacade extends AbstractRepairRoutinesFacade {
  public RoutinesFacade(final RoutinesFacadesProvider routinesFacadesProvider, final ReactionsImportPath reactionsImportPath, final RoutinesFacadeExecutionState executionState) {
    super(routinesFacadesProvider, reactionsImportPath, executionState);
  }
  
  public boolean addCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    AddCAEXCorrespondenceRoutine routine = new AddCAEXCorrespondenceRoutine(_routinesFacade, _reactionExecutionState, _caller, a, b);
    return routine.applyRoutine();
  }
  
  public boolean removeCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    RemoveCAEXCorrespondenceRoutine routine = new RemoveCAEXCorrespondenceRoutine(_routinesFacade, _reactionExecutionState, _caller, a, b);
    return routine.applyRoutine();
  }
  
  public boolean deleteElement(final CAEXObject o) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    DeleteElementRoutine routine = new DeleteElementRoutine(_routinesFacade, _reactionExecutionState, _caller, o);
    return routine.applyRoutine();
  }
  
  public boolean updateFeature(final EObject obj, final EStructuralFeature feature, final Object newValue) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    UpdateFeatureRoutine routine = new UpdateFeatureRoutine(_routinesFacade, _reactionExecutionState, _caller, obj, feature, newValue);
    return routine.applyRoutine();
  }
  
  public boolean setFeatureValue(final EObject e, final EStructuralFeature f, final Object o) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    SetFeatureValueRoutine routine = new SetFeatureValueRoutine(_routinesFacade, _reactionExecutionState, _caller, e, f, o);
    return routine.applyRoutine();
  }
}
