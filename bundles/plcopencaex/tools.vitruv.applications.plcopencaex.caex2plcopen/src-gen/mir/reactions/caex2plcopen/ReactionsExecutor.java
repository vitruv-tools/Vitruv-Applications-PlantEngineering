package mir.reactions.caex2plcopen;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;
import tools.vitruv.extensions.dslsruntime.reactions.RoutinesFacadesProvider;
import tools.vitruv.extensions.dslsruntime.reactions.structure.ReactionsImportPath;

@SuppressWarnings("all")
class ReactionsExecutor extends AbstractReactionsExecutor {
  public ReactionsExecutor() {
    super(new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain(), 
    	new tools.vitruv.domains.plcopen.PLCOpenDomainProvider().getDomain());
  }
  
  protected RoutinesFacadesProvider createRoutinesFacadesProvider() {
    return new mir.routines.caex2plcopen.RoutinesFacadesProvider();
  }
  
  protected void setup() {
    this.addReaction(new mir.reactions.caex2plcopen.LogEachChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex2plcopen"))));
    this.addReaction(new mir.reactions.caex2plcopen.AddExternalInterfaceReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex2plcopen"))));
    this.addReaction(new mir.reactions.caex2plcopen.AddExternalInterfaceTypeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex2plcopen"))));
    this.addReaction(new mir.reactions.caex2plcopen.ChangeExternalInterfaceTypeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex2plcopen"))));
    this.addReaction(new mir.reactions.caex2plcopen.RemoveExternalInterfaceTypeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex2plcopen"))));
    this.addReaction(new mir.reactions.caex2plcopen.ChangeExternalInterfacePathReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex2plcopen"))));
    this.addReaction(new mir.reactions.caex2plcopen.SystemUnitClassAddedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex2plcopen"))));
  }
}
