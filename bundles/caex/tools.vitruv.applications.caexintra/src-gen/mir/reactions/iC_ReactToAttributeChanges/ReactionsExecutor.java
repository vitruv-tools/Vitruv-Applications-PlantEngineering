package mir.reactions.iC_ReactToAttributeChanges;

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
    return new mir.routines.iC_ReactToAttributeChanges.RoutinesFacadesProvider();
  }
  
  protected void setup() {
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeNameChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToAttributeChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeDataTypeChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToAttributeChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeChangeModeChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToAttributeChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeDefaultValueChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToAttributeChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeIDChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToAttributeChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeUnitChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToAttributeChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeValueChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("iC_ReactToAttributeChanges"))));
  }
}
