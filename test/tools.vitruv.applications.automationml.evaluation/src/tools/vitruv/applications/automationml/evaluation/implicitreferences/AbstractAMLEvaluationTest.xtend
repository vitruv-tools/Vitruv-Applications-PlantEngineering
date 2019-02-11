package tools.vitruv.applications.automationml.evaluation.implicitreferences

import tools.vitruv.applications.automationml.tests.amlutils.AbstractAMLTest
import tools.vitruv.applications.automationml.attributechanges.CombinedAttributeChangePropagationSpecification
import tools.vitruv.applications.automationml.caexchanges.CombinedCAEXChangePropagationSpecification

class AbstractAMLEvaluationTest extends AbstractAMLTest {
	override protected cleanup() {
		// nothing to cleanup
	}

	override protected createChangePropagationSpecifications() {
		return #[new CombinedCAEXChangePropagationSpecification]
	}
	
	override protected setup() {
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)
		userInteractor.addNextConfirmationInput(false)

		val docRootCAEX = preloadExistingCAEXModel("resources/mechanicalPlantModelFlexMan_v12_string_references.caex")	
		createAndSynchronizeModel(CAEX_MODEL_NAME.projectCAEXModelPath, docRootCAEX)
		/*val docRootPLCopen = preloadExistingPLCopenModel("resources/bsp_plcopen_empty.tc60201")
		createAndSynchronizeModel(PLCOPEN_MODEL_NAME.projectPLCopenModelPath, docRootPLCopen)*/
		/*val docRootCollada = preloadExistingColladaModel("resources/bsp_collada_empty.dae")
		createAndSynchronizeModel(COLLADA_MODEL_NAME.projectColladaModelPath, docRootCollada)*/
	}
}