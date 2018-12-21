package mir.reactions.caex_import;

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
    return new mir.routines.caex_import.RoutinesFacadesProvider();
  }
  
  protected void setup() {
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeNameChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToAttributeChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeDataTypeChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToAttributeChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeChangeModeChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToAttributeChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeDefaultValueChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToAttributeChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeIDChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToAttributeChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeUnitChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToAttributeChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToAttributeChanges.AttributeValueChangeReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToAttributeChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToInternalElementChanges.CloneRefAddedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToInternalElementChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToInternalElementChanges.InternalElementChangeModeChangedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToInternalElementChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToInternalElementChanges.InternalElementeChangedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToInternalElementChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.NameChangedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToSystemUnitClassChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.ChangeModeChangedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToSystemUnitClassChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.AttributeAddedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToSystemUnitClassChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.AttributeRemovedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToSystemUnitClassChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.InternalElementAddedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToSystemUnitClassChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.SystemUnitClassDeletedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToSystemUnitClassChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassChanges.AdditionalInformationAddedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToSystemUnitClassChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToSystemUnitClassLibChanges.NameOfSystemUnitClassLibChangedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToSystemUnitClassLibChanges"))));
    this.addReaction(new mir.reactions.iC_ReactToConstraintChanges.SourceDocumentInformationRemovedReaction(this.getRoutinesFacadesProvider().getRoutinesFacade(ReactionsImportPath.fromPathString("caex_import.iC_ReactToConstraintChanges"))));
  }
}
