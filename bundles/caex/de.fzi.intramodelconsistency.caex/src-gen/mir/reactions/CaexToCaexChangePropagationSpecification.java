package mir.reactions;

import tools.vitruv.domains.caex.CAEXDomainProvider;
import tools.vitruv.framework.change.processing.impl.CompositeDecomposingChangePropagationSpecification;
import java.util.List;
import java.util.function.Supplier;
import tools.vitruv.framework.change.processing.ChangePropagationSpecification;
import java.util.Arrays;

/**
 * The {@link class tools.vitruv.framework.change.processing.impl.CompositeDecomposingChangePropagationSpecification} for transformations between the metamodels CAEX and CAEX.
 * To add further change processors override the setup method.
 *
 * <p> This file is generated! Do not edit it but extend it by inheriting from it!
 * 
 * <p> Generated from template version 1
 */
public class CaexToCaexChangePropagationSpecification extends CompositeDecomposingChangePropagationSpecification {
	
	private final List<Supplier<? extends ChangePropagationSpecification>> executors = Arrays.asList(
		// begin generated executor list
		mir.reactions.reactionsCaexToCaex.iC_ReactToInternalElementChanges.ExecutorCaexToCaex::new,
		
		mir.reactions.reactionsCaexToCaex.iC_ReactToSystemUnitClassChanges.ExecutorCaexToCaex::new,
		
		mir.reactions.reactionsCaexToCaex.iC_ReactToAttributeChanges.ExecutorCaexToCaex::new
		// end generated executor list
	);
	
	public CaexToCaexChangePropagationSpecification() {
		super(new CAEXDomainProvider().getDomain(), 
			new CAEXDomainProvider().getDomain());
		setup();
	}
	
	/**
	 * Adds the reactions change processors to this {@link CaexToCaexChangePropagationSpecification}.
	 * For adding further change processors overwrite this method and call the super method at the right place.
	 */
	protected void setup() {
		for (final Supplier<? extends ChangePropagationSpecification> executorSupplier : executors) {
			this.addChangeMainprocessor(executorSupplier.get());
		}	
	}
}
