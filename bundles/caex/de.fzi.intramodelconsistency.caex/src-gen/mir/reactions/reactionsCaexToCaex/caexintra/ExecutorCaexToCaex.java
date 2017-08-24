package mir.reactions.reactionsCaexToCaex.caexintra;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;

@SuppressWarnings("all")
public class ExecutorCaexToCaex extends AbstractReactionsExecutor {
  public ExecutorCaexToCaex() {
    super(new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain(), 
    	new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain());
  }
  
  protected void setup() {
    this.addReaction(mir.reactions.reactionsCaexToCaex.caexintra.CloneRefAddedReaction.getExpectedChangeType(), new mir.reactions.reactionsCaexToCaex.caexintra.CloneRefAddedReaction());
    this.addReaction(mir.reactions.reactionsCaexToCaex.caexintra.PrototypeChangedReaction.getExpectedChangeType(), new mir.reactions.reactionsCaexToCaex.caexintra.PrototypeChangedReaction());
    this.addReaction(mir.reactions.reactionsCaexToCaex.caexintra.DisplayFileNameChangeReaction.getExpectedChangeType(), new mir.reactions.reactionsCaexToCaex.caexintra.DisplayFileNameChangeReaction());
  }
}
