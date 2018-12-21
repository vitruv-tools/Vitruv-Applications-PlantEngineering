package mir.routines.caex_import;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutinesFacade;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRoutinesFacadesProvider;
import tools.vitruv.extensions.dslsruntime.reactions.RoutinesFacadeExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.ReactionsImportPath;

@SuppressWarnings("all")
public class RoutinesFacadesProvider extends AbstractRoutinesFacadesProvider {
  public AbstractRepairRoutinesFacade createRoutinesFacade(final ReactionsImportPath reactionsImportPath, final RoutinesFacadeExecutionState sharedExecutionState) {
    switch(reactionsImportPath.getPathString()) {
    	case "caex_import": {
    		return new mir.routines.caex_import.RoutinesFacade(this, reactionsImportPath, sharedExecutionState);
    	}
    	case "caex_import.iC_ReactToAttributeChanges": {
    		return new mir.routines.iC_ReactToAttributeChanges.RoutinesFacade(this, reactionsImportPath, sharedExecutionState);
    	}
    	case "caex_import.iC_ReactToAttributeChanges.iC_Utilities": {
    		return new mir.routines.iC_Utilities.RoutinesFacade(this, reactionsImportPath, sharedExecutionState);
    	}
    	case "caex_import.iC_ReactToInternalElementChanges": {
    		return new mir.routines.iC_ReactToInternalElementChanges.RoutinesFacade(this, reactionsImportPath, sharedExecutionState);
    	}
    	case "caex_import.iC_ReactToInternalElementChanges.iC_Utilities": {
    		return new mir.routines.iC_Utilities.RoutinesFacade(this, reactionsImportPath, sharedExecutionState);
    	}
    	case "caex_import.iC_ReactToSystemUnitClassChanges": {
    		return new mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade(this, reactionsImportPath, sharedExecutionState);
    	}
    	case "caex_import.iC_ReactToSystemUnitClassChanges.iC_Utilities": {
    		return new mir.routines.iC_Utilities.RoutinesFacade(this, reactionsImportPath, sharedExecutionState);
    	}
    	case "caex_import.iC_ReactToSystemUnitClassLibChanges": {
    		return new mir.routines.iC_ReactToSystemUnitClassLibChanges.RoutinesFacade(this, reactionsImportPath, sharedExecutionState);
    	}
    	case "caex_import.iC_ReactToSystemUnitClassLibChanges.iC_ReactToSystemUnitClassChanges": {
    		return new mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacade(this, reactionsImportPath, sharedExecutionState);
    	}
    	case "caex_import.iC_ReactToSystemUnitClassLibChanges.iC_ReactToSystemUnitClassChanges.iC_Utilities": {
    		return new mir.routines.iC_Utilities.RoutinesFacade(this, reactionsImportPath, sharedExecutionState);
    	}
    	case "caex_import.iC_ReactToConstraintChanges": {
    		return new mir.routines.iC_ReactToConstraintChanges.RoutinesFacade(this, reactionsImportPath, sharedExecutionState);
    	}
    	default: {
    		throw new IllegalArgumentException("Unexpected import path: " + reactionsImportPath.getPathString());
    	}
    }
  }
}
