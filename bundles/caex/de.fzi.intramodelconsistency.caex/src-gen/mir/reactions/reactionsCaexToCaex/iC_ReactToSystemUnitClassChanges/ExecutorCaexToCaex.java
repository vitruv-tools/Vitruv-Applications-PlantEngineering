package mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;

@SuppressWarnings("all")
public class ExecutorCaexToCaex extends AbstractReactionsExecutor {
  public ExecutorCaexToCaex() {
    super(new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain(), 
    	new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain());
  }
  
  protected void setup() {
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.NameChangedReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.ChangeModeChangedReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.AttributeAddedReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.InternalElementAddedReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.SystemUnitClassDeletedReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.AdditionalInformationAddedReaction());
  }
}
