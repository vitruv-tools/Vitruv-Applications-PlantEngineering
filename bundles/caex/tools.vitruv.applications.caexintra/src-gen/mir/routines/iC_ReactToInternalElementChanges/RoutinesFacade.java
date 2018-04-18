package mir.routines.iC_ReactToInternalElementChanges;

import caex.caex30.caex.CAEXObject;
import caex.caex30.caex.InternalElement;
import mir.routines.iC_ReactToInternalElementChanges.NewCorrespondenceCloneProtoRoutine;
import mir.routines.iC_ReactToInternalElementChanges.RemoveCorrespondencesofSystemUnitClassesRoutine;
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
  
  public boolean newCorrespondenceCloneProto(final InternalElement ie, final String sucStr) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    NewCorrespondenceCloneProtoRoutine routine = new NewCorrespondenceCloneProtoRoutine(_routinesFacade, _reactionExecutionState, _caller, ie, sucStr);
    return routine.applyRoutine();
  }
  
  public boolean removeCorrespondencesofSystemUnitClasses(final InternalElement affectedElement) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    RemoveCorrespondencesofSystemUnitClassesRoutine routine = new RemoveCorrespondencesofSystemUnitClassesRoutine(_routinesFacade, _reactionExecutionState, _caller, affectedElement);
    return routine.applyRoutine();
  }
  
  public boolean addCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    mir.routines.iC_Utilities.RoutinesFacade _routinesFacade = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_Utilities")));
    return _routinesFacade.addCAEXCorrespondence(a, b);
  }
  
  public boolean removeCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    mir.routines.iC_Utilities.RoutinesFacade _routinesFacade = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_Utilities")));
    return _routinesFacade.removeCAEXCorrespondence(a, b);
  }
  
  public boolean deleteElement(final CAEXObject o) {
    mir.routines.iC_Utilities.RoutinesFacade _routinesFacade = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_Utilities")));
    return _routinesFacade.deleteElement(o);
  }
  
  public boolean updateFeature(final EObject obj, final EStructuralFeature feature, final Object newValue) {
    mir.routines.iC_Utilities.RoutinesFacade _routinesFacade = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_Utilities")));
    return _routinesFacade.updateFeature(obj, feature, newValue);
  }
  
  public boolean setFeatureValue(final EObject e, final EStructuralFeature f, final Object o) {
    mir.routines.iC_Utilities.RoutinesFacade _routinesFacade = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_Utilities")));
    return _routinesFacade.setFeatureValue(e, f, o);
  }
}
