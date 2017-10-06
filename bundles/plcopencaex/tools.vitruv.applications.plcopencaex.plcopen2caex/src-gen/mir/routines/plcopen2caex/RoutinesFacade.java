package mir.routines.plcopen2caex;

import org.plcopen.xml.tc60201.DocumentRoot;
import org.plcopen.xml.tc60201.FileHeaderType;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutinesFacade;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RoutinesFacade extends AbstractRepairRoutinesFacade {
  public RoutinesFacade(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
    super(reactionExecutionState, calledBy);
  }
  
  public boolean createCaexRoot(final DocumentRoot plcopenRoot) {
    mir.routines.plcopen2caex.CreateCaexRootRoutine effect = new mir.routines.plcopen2caex.CreateCaexRootRoutine(this.executionState, calledBy, plcopenRoot);
    return effect.applyRoutine();
  }
  
  public boolean createVersion(final FileHeaderType fileHeaderType) {
    mir.routines.plcopen2caex.CreateVersionRoutine effect = new mir.routines.plcopen2caex.CreateVersionRoutine(this.executionState, calledBy, fileHeaderType);
    return effect.applyRoutine();
  }
  
  public boolean renameVersionForProductVersion(final FileHeaderType fileHeaderType) {
    mir.routines.plcopen2caex.RenameVersionForProductVersionRoutine effect = new mir.routines.plcopen2caex.RenameVersionForProductVersionRoutine(this.executionState, calledBy, fileHeaderType);
    return effect.applyRoutine();
  }
}
