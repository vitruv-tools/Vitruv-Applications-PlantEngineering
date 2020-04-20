package mir.routines.iC_ReactToSystemUnitClassLibChanges;

import caex.caex30.caex.Attribute;
import caex.caex30.caex.CAEXObject;
import caex.caex30.caex.SystemUnitClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutinesFacade;
import tools.vitruv.extensions.dslsruntime.reactions.RoutinesFacadeExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.RoutinesFacadesProvider;
import tools.vitruv.extensions.dslsruntime.reactions.structure.ReactionsImportPath;

@SuppressWarnings("all")
public class RoutinesFacade extends AbstractRepairRoutinesFacade {
  public RoutinesFacade(final RoutinesFacadesProvider routinesFacadesProvider, final ReactionsImportPath reactionsImportPath, final RoutinesFacadeExecutionState executionState) {
    super(routinesFacadesProvider, reactionsImportPath, executionState);
  }
  
  public boolean correctPathsSystemUnitClassClones(final SystemUnitClass affectedEObject) {
    mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade _routinesFacade = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges")));
    return _routinesFacade.correctPathsSystemUnitClassClones(affectedEObject);
  }
  
  public boolean correctSystemUnitClassClones(final SystemUnitClass changedPrototype, final EStructuralFeature feature, final Object newValue) {
    mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade _routinesFacade = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges")));
    return _routinesFacade.correctSystemUnitClassClones(changedPrototype, feature, newValue);
  }
  
  public boolean removeAllClonesAndCorrespondencesSysUnitClass(final SystemUnitClass affectedClass) {
    mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade _routinesFacade = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges")));
    return _routinesFacade.removeAllClonesAndCorrespondencesSysUnitClass(affectedClass);
  }
  
  public boolean removeAllClonesAndCorrespondencesAttribute(final Attribute affectedAttribute) {
    mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade _routinesFacade = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges")));
    return _routinesFacade.removeAllClonesAndCorrespondencesAttribute(affectedAttribute);
  }
  
  public boolean addCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    mir.routines.iC_Utilities.RoutinesFacade _routinesFacade = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges.iC_Utilities")));
    return _routinesFacade.addCAEXCorrespondence(a, b);
  }
  
  public boolean removeCAEXCorrespondence(final CAEXObject a, final CAEXObject b) {
    mir.routines.iC_Utilities.RoutinesFacade _routinesFacade = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges.iC_Utilities")));
    return _routinesFacade.removeCAEXCorrespondence(a, b);
  }
  
  public boolean deleteElement(final CAEXObject o) {
    mir.routines.iC_Utilities.RoutinesFacade _routinesFacade = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges.iC_Utilities")));
    return _routinesFacade.deleteElement(o);
  }
  
  public boolean updateFeature(final EObject obj, final EStructuralFeature feature, final Object newValue) {
    mir.routines.iC_Utilities.RoutinesFacade _routinesFacade = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges.iC_Utilities")));
    return _routinesFacade.updateFeature(obj, feature, newValue);
  }
}
