package mir.reactions.reactionsCaexToCaex.iC_ReactToInternalElementChanges;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;

@SuppressWarnings("all")
public class ExecutorCaexToCaex extends AbstractReactionsExecutor {
  public ExecutorCaexToCaex() {
    super(new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain(), 
    	new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain());
  }
  
  protected void setup() {
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToInternalElementChanges.CloneRefAddedReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToInternalElementChanges.InternalElementChangeModeChangedReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToInternalElementChanges.InternalElementeChangedReaction());
  }
}
