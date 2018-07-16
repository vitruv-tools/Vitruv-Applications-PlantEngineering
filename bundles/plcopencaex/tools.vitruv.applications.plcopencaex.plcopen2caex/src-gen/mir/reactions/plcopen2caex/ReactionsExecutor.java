package mir.reactions.plcopen2caex;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;
import tools.vitruv.extensions.dslsruntime.reactions.RoutinesFacadesProvider;
import tools.vitruv.extensions.dslsruntime.reactions.structure.ReactionsImportPath;

@SuppressWarnings("all")
class ReactionsExecutor extends AbstractReactionsExecutor {
  public ReactionsExecutor() {
    super(new tools.vitruv.domains.plcopen.PLCOpenDomainProvider().getDomain(), 
    	new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain());
  }
  
  protected RoutinesFacadesProvider createRoutinesFacadesProvider() {
    return new mir.routines.plcopen2caex.RoutinesFacadesProvider();
  }
  
  protected void setup() {
    this.addReaction(new mir.reactions.plcopen2caex.AnyReactionTrackingReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("plcopen2caex"))));
    this.addReaction(new mir.reactions.plcopen2caex.CreatedRootReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("plcopen2caex"))));
    this.addReaction(new mir.reactions.plcopen2caex.RenameProductVersionReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("plcopen2caex"))));
    this.addReaction(new mir.reactions.plcopen2caex.CreatedFileHeaderTypeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("plcopen2caex"))));
    this.addReaction(new mir.reactions.plcopen2caex.ChangeGlobalIDReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("plcopen2caex"))));
  }
}
