package mir.routines.iC_ReactToSystemUnitClassChanges;

import caex.caex30.caex.Attribute;
import caex.caex30.caex.CAEXObject;
import caex.caex30.caex.SystemUnitClass;
import mir.routines.iC_ReactToSystemUnitClassChanges.CorrectPathsSystemUnitClassClonesRoutine;
import mir.routines.iC_ReactToSystemUnitClassChanges.CorrectSystemUnitClassClonesRoutine;
import mir.routines.iC_ReactToSystemUnitClassChanges.RemoveAllClonesAndCorrespondencesAttributeRoutine;
import mir.routines.iC_ReactToSystemUnitClassChanges.RemoveAllClonesAndCorrespondencesSysUnitClassRoutine;
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
  
  public boolean correctPathsSystemUnitClassClones(final SystemUnitClass affectedEObject) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    CorrectPathsSystemUnitClassClonesRoutine routine = new CorrectPathsSystemUnitClassClonesRoutine(_routinesFacade, _reactionExecutionState, _caller, affectedEObject);
    return routine.applyRoutine();
  }
  
  public boolean correctSystemUnitClassClones(final SystemUnitClass changedPrototype, final EStructuralFeature feature, final Object newValue) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    CorrectSystemUnitClassClonesRoutine routine = new CorrectSystemUnitClassClonesRoutine(_routinesFacade, _reactionExecutionState, _caller, changedPrototype, feature, newValue);
    return routine.applyRoutine();
  }
  
  public boolean removeAllClonesAndCorrespondencesSysUnitClass(final SystemUnitClass affectedClass) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    RemoveAllClonesAndCorrespondencesSysUnitClassRoutine routine = new RemoveAllClonesAndCorrespondencesSysUnitClassRoutine(_routinesFacade, _reactionExecutionState, _caller, affectedClass);
    return routine.applyRoutine();
  }
  
  public boolean removeAllClonesAndCorrespondencesAttribute(final Attribute affectedAttribute) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    RemoveAllClonesAndCorrespondencesAttributeRoutine routine = new RemoveAllClonesAndCorrespondencesAttributeRoutine(_routinesFacade, _reactionExecutionState, _caller, affectedAttribute);
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
