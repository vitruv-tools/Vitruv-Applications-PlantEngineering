package tools.vitruv.applications.automationml.tests.attributereactions

import tools.vitruv.applications.automationml.attributechanges.CombinedAttributeChangePropagationSpecification
import tools.vitruv.applications.automationml.tests.amlutils.AbstractAMLTest

class AbstractCAEXAttributeReactionsTest extends AbstractAMLTest {
	override protected cleanup() {
		// nothing to cleanup
	}

	override protected createChangePropagationSpecifications() {
		return #[new CombinedAttributeChangePropagationSpecification]
	}
	
	override protected setup() {
		val docRootCAEX = preloadExistingCAEXModel("resources/bsp_caex_empty.caex")	
		createAndSynchronizeModel(CAEX_MODEL_NAME.projectCAEXModelPath, docRootCAEX)
	}
}