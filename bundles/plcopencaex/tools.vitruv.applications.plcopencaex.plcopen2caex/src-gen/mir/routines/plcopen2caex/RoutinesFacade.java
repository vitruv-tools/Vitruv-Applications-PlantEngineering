package mir.routines.plcopen2caex;

import mir.routines.plcopen2caex.ChangeIDInCAEXRoutine;
import mir.routines.plcopen2caex.CreateCaexRootRoutine;
import mir.routines.plcopen2caex.CreateVersionRoutine;
import mir.routines.plcopen2caex.RenameVersionForProductVersionRoutine;
import org.plcopen.xml.tc60201.DocumentRoot;
import org.plcopen.xml.tc60201.FileHeaderType;
import org.plcopen.xml.tc60201.PouType1;
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
  
  public boolean createCaexRoot(final DocumentRoot plcopenRoot) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    CreateCaexRootRoutine routine = new CreateCaexRootRoutine(_routinesFacade, _reactionExecutionState, _caller, plcopenRoot);
    return routine.applyRoutine();
  }
  
  public boolean renameVersionForProductVersion(final FileHeaderType fileHeaderType) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    RenameVersionForProductVersionRoutine routine = new RenameVersionForProductVersionRoutine(_routinesFacade, _reactionExecutionState, _caller, fileHeaderType);
    return routine.applyRoutine();
  }
  
  public boolean createVersion(final FileHeaderType fileHeaderType) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    CreateVersionRoutine routine = new CreateVersionRoutine(_routinesFacade, _reactionExecutionState, _caller, fileHeaderType);
    return routine.applyRoutine();
  }
  
  public boolean changeIDInCAEX(final PouType1 pouType) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    ChangeIDInCAEXRoutine routine = new ChangeIDInCAEXRoutine(_routinesFacade, _reactionExecutionState, _caller, pouType);
    return routine.applyRoutine();
  }
}
