package mir.reactions.reactionsTc60201ToCAEX.plcopen2caex;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;
import tools.vitruv.framework.userinteraction.UserInteracting;

@SuppressWarnings("all")
public class ExecutorTc60201ToCAEX extends AbstractReactionsExecutor {
  public ExecutorTc60201ToCAEX(final UserInteracting userInteracting) {
    super(userInteracting, new tools.vitruv.framework.util.datatypes.MetamodelPair(org.plcopen.xml.tc60201.impl.Tc60201PackageImpl.eNS_URI, CAEX.impl.CAEXPackageImpl.eNS_URI));
  }
  
  protected void setup() {
    tools.vitruv.framework.userinteraction.UserInteracting userInteracting = getUserInteracting();
    this.addReaction(mir.reactions.reactionsTc60201ToCAEX.plcopen2caex.AnyReactionTrackingReaction.getExpectedChangeType(), new mir.reactions.reactionsTc60201ToCAEX.plcopen2caex.AnyReactionTrackingReaction(userInteracting));
    this.addReaction(mir.reactions.reactionsTc60201ToCAEX.plcopen2caex.CreatedRootReaction.getExpectedChangeType(), new mir.reactions.reactionsTc60201ToCAEX.plcopen2caex.CreatedRootReaction(userInteracting));
    this.addReaction(mir.reactions.reactionsTc60201ToCAEX.plcopen2caex.CreatedFileHeaderTypeReaction.getExpectedChangeType(), new mir.reactions.reactionsTc60201ToCAEX.plcopen2caex.CreatedFileHeaderTypeReaction(userInteracting));
    this.addReaction(mir.reactions.reactionsTc60201ToCAEX.plcopen2caex.RenameProductVersionReaction.getExpectedChangeType(), new mir.reactions.reactionsTc60201ToCAEX.plcopen2caex.RenameProductVersionReaction(userInteracting));
  }
}
