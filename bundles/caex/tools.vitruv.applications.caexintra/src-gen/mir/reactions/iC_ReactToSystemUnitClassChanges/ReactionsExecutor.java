package mir.reactions.iC_ReactToSystemUnitClassChanges;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;
import tools.vitruv.extensions.dslsruntime.reactions.RoutinesFacadesProvider;
import tools.vitruv.extensions.dslsruntime.reactions.structure.ReactionsImportPath;

@SuppressWarnings("all")
class ReactionsExecutor extends AbstractReactionsExecutor {
  public ReactionsExecutor() {
    super(new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain(), 
    	new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain());
  }
  
  protected RoutinesFacadesProvider createRoutinesFacadesProvider() {
    return new mir.routines.iC_ReactToSystemUnitClassChanges.RoutinesFacadesProvider();
  }
  
  protected void setup() {
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.NameChangedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.ChangeModeChangedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.AttributeAddedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.AttributeRemovedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.InternalElementAddedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.SystemUnitClassDeletedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.AdditionalInformationAddedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToSystemUnitClassChanges"))));
  }
}
