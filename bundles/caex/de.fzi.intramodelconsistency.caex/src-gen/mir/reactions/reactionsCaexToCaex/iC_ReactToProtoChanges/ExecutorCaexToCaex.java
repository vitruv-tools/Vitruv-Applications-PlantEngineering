package mir.reactions.reactionsCaexToCaex.iC_ReactToProtoChanges;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;

@SuppressWarnings("all")
public class ExecutorCaexToCaex extends AbstractReactionsExecutor {
  public ExecutorCaexToCaex() {
    super(new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain(), 
    	new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain());
  }
  
  protected void setup() {
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToProtoChanges.PrototypeNameChangedReaction());
    this.addReaction(new mir.reactions.reactionsCaexToCaex.iC_ReactToProtoChanges.PrototypeChangeModeChangedReaction());
  }
}
