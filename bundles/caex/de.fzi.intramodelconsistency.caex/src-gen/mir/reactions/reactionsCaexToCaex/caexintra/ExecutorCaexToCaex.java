package mir.reactions.reactionsCaexToCaex.caexintra;

import tools.vitruv.domains.caex.CAEXDomainProvider;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;

@SuppressWarnings("all")
public class ExecutorCaexToCaex extends AbstractReactionsExecutor {
  public ExecutorCaexToCaex() {
    super(new CAEXDomainProvider().getDomain(), 
    	new CAEXDomainProvider().getDomain());
  }
  
  protected void setup() {
    tools.vitruv.framework.userinteraction.UserInteracting userInteracting = getUserInteracting();
    this.addReaction(mir.reactions.reactionsCaexToCaex.caexintra.CloneRefAddedReaction.getExpectedChangeType(), new mir.reactions.reactionsCaexToCaex.caexintra.CloneRefAddedReaction(userInteracting));
    this.addReaction(mir.reactions.reactionsCaexToCaex.caexintra.PrototypeChangedReaction.getExpectedChangeType(), new mir.reactions.reactionsCaexToCaex.caexintra.PrototypeChangedReaction(userInteracting));
    this.addReaction(mir.reactions.reactionsCaexToCaex.caexintra.DisplayFileNameChangeReaction.getExpectedChangeType(), new mir.reactions.reactionsCaexToCaex.caexintra.DisplayFileNameChangeReaction(userInteracting));
  }
}
