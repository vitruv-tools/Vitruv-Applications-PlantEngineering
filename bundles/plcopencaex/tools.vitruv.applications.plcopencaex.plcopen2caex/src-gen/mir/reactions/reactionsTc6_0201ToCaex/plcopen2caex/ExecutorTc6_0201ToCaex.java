package mir.reactions.reactionsTc6_0201ToCaex.plcopen2caex;

import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionsExecutor;
import tools.vitruv.framework.userinteraction.UserInteracting;

@SuppressWarnings("all")
public class ExecutorTc6_0201ToCaex extends AbstractReactionsExecutor {
  public ExecutorTc6_0201ToCaex(final UserInteracting userInteracting) {
    super(userInteracting, new tools.vitruv.framework.util.datatypes.MetamodelPair(org.plcopen.xml.tc60201.impl.Tc60201PackageImpl.eNS_URI, CAEX.impl.CAEXPackageImpl.eNS_URI));
  }
  
  protected void setup() {
    tools.vitruv.framework.userinteraction.UserInteracting userInteracting = getUserInteracting();
    this.addReaction(mir.reactions.reactionsTc6_0201ToCaex.plcopen2caex.AReaction.getExpectedChangeType(), new mir.reactions.reactionsTc6_0201ToCaex.plcopen2caex.AReaction(userInteracting));
    this.addReaction(mir.reactions.reactionsTc6_0201ToCaex.plcopen2caex.CreatedRootReaction.getExpectedChangeType(), new mir.reactions.reactionsTc6_0201ToCaex.plcopen2caex.CreatedRootReaction(userInteracting));
  }
}
