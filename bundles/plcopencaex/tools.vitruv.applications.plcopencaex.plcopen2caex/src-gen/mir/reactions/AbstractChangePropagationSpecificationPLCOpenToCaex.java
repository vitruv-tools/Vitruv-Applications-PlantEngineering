package mir.reactions;

import tools.vitruv.domains.caex.CAEXDomainProvider;
import tools.vitruv.framework.change.processing.impl.CompositeChangePropagationSpecification;
import tools.vitruv.domains.plcopen.PLCOpenDomainProvider;

/**
 * The {@link class tools.vitruv.framework.change.processing.impl.CompositeChangePropagationSpecification} for transformations between the metamodels PLCOpen and CAEX.
 * To add further change processors overwrite the setup method.
 */
public abstract class AbstractChangePropagationSpecificationPLCOpenToCaex extends CompositeChangePropagationSpecification {
	public AbstractChangePropagationSpecificationPLCOpenToCaex() {
		super(new tools.vitruv.framework.userinteraction.impl.UserInteractor(),
			new PLCOpenDomainProvider().getDomain(), 
			new CAEXDomainProvider().getDomain());
		setup();
	}
	
	/**
	 * Adds the reactions change processors to this {@link AbstractChangePropagationSpecificationPLCOpenToCaex}.
	 * For adding further change processors overwrite this method and call the super method at the right place.
	 */
	protected void setup() {
		this.addChangeMainprocessor(new mir.reactions.reactionsPLCOpenToCaex.plcopen2caex.ExecutorPLCOpenToCaex(getUserInteracting()));
	}
	
}
