package mir.routines.caex2plcopen;

import mir.routines.caex2plcopen.AddPLCopenFileWithStructureRoutine;
import mir.routines.caex2plcopen.ChangeIdCorrespondenceRoutine;
import org.automationml.caex.caex.DocumentRoot;
import org.automationml.caex.caex.ExternalReference;
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
  
  public boolean changeIdCorrespondence(final ExternalReference reference) {
    RoutinesFacade _routinesFacade = this;
    ReactionExecutionState _reactionExecutionState = this._getExecutionState().getReactionExecutionState();
    CallHierarchyHaving _caller = this._getExecutionState().getCaller();
    ChangeIdCorrespondenceRoutine routine = new ChangeIdCorrespondenceRoutine(_routinesFacade, _reactionExecutionState, _caller, reference);
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
