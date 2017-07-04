package mir.reactions;

import tools.vitruv.domains.caex.CAEXDomainProvider;
import tools.vitruv.framework.change.processing.impl.CompositeChangePropagationSpecification;

/**
 * The {@link class tools.vitruv.framework.change.processing.impl.CompositeChangePropagationSpecification} for transformations between the metamodels CAEX and CAEX.
 * To add further change processors overwrite the setup method.
 */
public abstract class AbstractChangePropagationSpecificationCaexToCaex extends CompositeChangePropagationSpecification {
	public AbstractChangePropagationSpecificationCaexToCaex() {
		super(new CAEXDomainProvider().getDomain(), 
			new CAEXDomainProvider().getDomain());
		setup();
	}
	
	/**
	 * Adds the reactions change processors to this {@link AbstractChangePropagationSpecificationCaexToCaex}.
	 * For adding further change processors overwrite this method and call the super method at the right place.
	 */
	protected void setup() {
		this.addChangeMainprocessor(new mir.reactions.reactionsCaexToCaex.caexintra.ExecutorCaexToCaex());
	}
	
}
