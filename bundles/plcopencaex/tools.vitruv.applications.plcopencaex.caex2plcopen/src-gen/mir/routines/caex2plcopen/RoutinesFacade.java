package mir.routines.caex2plcopen;

import mir.routines.caex2plcopen.AddCorrespondenceToPLCopenRoutine;
import mir.routines.caex2plcopen.AddPLCopenFileWithStructureRoutine;
import mir.routines.caex2plcopen.RemoveCorrespondenceFromPLCopenRoutine;
import org.automationml.caex.caex.CAEXFile;
import org.automationml.caex.caex.DocumentRoot;
import org.automationml.caex.caex.ExternalInterface;
import org.automationml.caex.caex.SystemUnitClass;
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
  
  public boolean addCorrespondenceToPLCopen(final ExternalInterface externalInterface, final CAEXFile caexFile) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    AddCorrespondenceToPLCopenRoutine routine = new AddCorrespondenceToPLCopenRoutine(_routinesFacade, _reactionExecutionState, _caller, externalInterface, caexFile);
    return routine.applyRoutine();
  }
  
  public boolean removeCorrespondenceFromPLCopen(final ExternalInterface externalInterface) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    RemoveCorrespondenceFromPLCopenRoutine routine = new RemoveCorrespondenceFromPLCopenRoutine(_routinesFacade, _reactionExecutionState, _caller, externalInterface);
    return routine.applyRoutine();
  }
  
  public boolean addPLCopenFileWithStructure(final SystemUnitClass newClass, final DocumentRoot caexRoot) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    AddPLCopenFileWithStructureRoutine routine = new AddPLCopenFileWithStructureRoutine(_routinesFacade, _reactionExecutionState, _caller, newClass, caexRoot);
    return routine.applyRoutine();
  }
}
