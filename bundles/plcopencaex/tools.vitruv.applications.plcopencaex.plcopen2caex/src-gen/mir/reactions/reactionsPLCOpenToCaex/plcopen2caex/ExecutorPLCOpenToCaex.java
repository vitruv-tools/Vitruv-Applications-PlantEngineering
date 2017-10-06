package mir.reactions.reactionsPLCOpenToCaex.plcopen2caex;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;

@SuppressWarnings("all")
public class ExecutorPLCOpenToCaex extends AbstractReactionsExecutor {
  public ExecutorPLCOpenToCaex() {
    super(new tools.vitruv.domains.plcopen.PLCOpenDomainProvider().getDomain(), 
    	new tools.vitruv.domains.caex.CAEXDomainProvider().getDomain());
  }
  
  protected void setup() {
    this.addReaction(new mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.AnyReactionTrackingReaction());
    this.addReaction(new mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.CreatedRootReaction());
    this.addReaction(new mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.CreatedFileHeaderTypeReaction());
    this.addReaction(new mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.RenameProductVersionReaction());
  }
}
