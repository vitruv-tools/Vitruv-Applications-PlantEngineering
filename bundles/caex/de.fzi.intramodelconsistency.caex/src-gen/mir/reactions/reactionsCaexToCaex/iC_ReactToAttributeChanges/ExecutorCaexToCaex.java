package mir.reactions.reactionsCaexToCaex.iC_ReactToAttributeChanges;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;

@SuppressWarnings("all")
public class ExecutorCaexToCaex extends AbstractReactionsExecutor {
  public ExecutorCaexToCaex() {
    super(new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain(), 
    	new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain());
  }
  
  protected void setup() {
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToAttributeChanges.AttributeNameChangeReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToAttributeChanges.AttributeDataTypeChangeReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToAttributeChanges.AttributeChangeModeChangeReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToAttributeChanges.AttributeDefaultValueChangeReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToAttributeChanges.AttributeIDChangeReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToAttributeChanges.AttributeUnitChangeReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToAttributeChanges.AttributeValueChangeReaction());
  }
}
