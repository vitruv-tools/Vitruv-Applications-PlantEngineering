package mir.reactions.reactionsCaexToCaex.iC_ReactOnAttributeChanges;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;

@SuppressWarnings("all")
public class ExecutorCaexToCaex extends AbstractReactionsExecutor {
  public ExecutorCaexToCaex() {
    super(new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain(), 
    	new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain());
  }
  
  protected void setup() {
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactOnAttributeChanges.AttributeNameChangeReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactOnAttributeChanges.AttributeDataTypeChangeReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactOnAttributeChanges.AttributeChangeModeChangeReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactOnAttributeChanges.AttributeDefaultValueChangeReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactOnAttributeChanges.AttributeIDChangeReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactOnAttributeChanges.AttributeUnitChangeReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactOnAttributeChanges.AttributeValueChangeReaction());
  }
}
