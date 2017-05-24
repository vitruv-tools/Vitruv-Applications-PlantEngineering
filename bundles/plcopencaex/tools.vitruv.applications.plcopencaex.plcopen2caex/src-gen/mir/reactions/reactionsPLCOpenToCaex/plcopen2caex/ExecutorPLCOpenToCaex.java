package mir.reactions.reactionsPLCOpenToCaex.plcopen2caex;

import tools.vitruv.domains.caex.CAEXDomainProvider;
import tools.vitruv.domains.plcopen.PLCOpenDomainProvider;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;

@SuppressWarnings("all")
public class ExecutorPLCOpenToCaex extends AbstractReactionsExecutor {
  public ExecutorPLCOpenToCaex() {
    super(new PLCOpenDomainProvider().getDomain(), 
    	new CAEXDomainProvider().getDomain());
  }
  
  protected void setup() {
    tools.vitruv.framework.userinteraction.UserInteracting userInteracting = getUserInteracting();
    this.addReaction(mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.AnyReactionTrackingReaction.getExpectedChangeType(), new mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.AnyReactionTrackingReaction(userInteracting));
    this.addReaction(mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.CreatedRootReaction.getExpectedChangeType(), new mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.CreatedRootReaction(userInteracting));
    this.addReaction(mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.CreatedFileHeaderTypeReaction.getExpectedChangeType(), new mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.CreatedFileHeaderTypeReaction(userInteracting));
    this.addReaction(mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.RenameProductVersionReaction.getExpectedChangeType(), new mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.RenameProductVersionReaction(userInteracting));
  }
}
