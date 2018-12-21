package mir.routines.caex_import;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutinesFacade;
import tools.vitruv.extensions.dslsruntime.reactions.RoutinesFacadeExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.RoutinesFacadesProvider;
import tools.vitruv.extensions.dslsruntime.reactions.structure.ReactionsImportPath;

@SuppressWarnings("all")
public class RoutinesFacade extends AbstractRepairRoutinesFacade {
  public RoutinesFacade(final RoutinesFacadesProvider routinesFacadesProvider, final ReactionsImportPath reactionsImportPath, final RoutinesFacadeExecutionState executionState) {
    super(routinesFacadesProvider, reactionsImportPath, executionState);
    this.iC_ReactToAttributeChanges = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_ReactToAttributeChanges")));
    this.iC_ReactToInternalElementChanges = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_ReactToInternalElementChanges")));
    this.iC_ReactToSystemUnitClassChanges = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges")));
    this.iC_ReactToSystemUnitClassLibChanges = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassLibChanges")));
    this.iC_ReactToConstraintChanges = this._getRoutinesFacadesProvider().getRoutinesFacade(this._getReactionsImportPath().append(ReactionsImportPath.fromPathString("iC_ReactToConstraintChanges")));
  }
  
  public final mir.routines.iC_ReactToAttributeChanges.RoutinesFacade iC_ReactToAttributeChanges;
  
  public final mir.routines.iC_ReactToInternalElementChanges.RoutinesFacade iC_ReactToInternalElementChanges;
  
  public final mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade iC_ReactToSystemUnitClassChanges;
  
  public final mir.routines.iC_ReactToSystemUnitClassLibChanges.RoutinesFacade iC_ReactToSystemUnitClassLibChanges;
  
  public final mir.routines.iC_ReactToConstraintChanges.RoutinesFacade iC_ReactToConstraintChanges;
}
