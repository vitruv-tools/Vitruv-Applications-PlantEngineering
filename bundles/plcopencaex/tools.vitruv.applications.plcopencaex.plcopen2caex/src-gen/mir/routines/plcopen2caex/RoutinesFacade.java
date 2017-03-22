package mir.routines.plcopen2caex;

import org.plcopen.xml.tc60201.DocumentRoot;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutinesFacade;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class RoutinesFacade extends AbstractRepairRoutinesFacade {
  public RoutinesFacade(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
    super(reactionExecutionState, calledBy);
  }
  
  public void createCaexRoot(final DocumentRoot plcopenRoot) {
    mir.routines.plcopen2caex.CreateCaexRootRoutine effect = new mir.routines.plcopen2caex.CreateCaexRootRoutine(this.executionState, calledBy,
    	plcopenRoot);
    effect.applyRoutine();
  }
}
