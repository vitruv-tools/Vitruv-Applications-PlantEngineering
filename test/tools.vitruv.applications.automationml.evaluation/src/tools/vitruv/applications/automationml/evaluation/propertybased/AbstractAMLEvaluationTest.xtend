package tools.vitruv.applications.automationml.evaluation.propertybased

import tools.vitruv.applications.automationml.caexchanges.CombinedCAEXChangePropagationSpecification
import tools.vitruv.applications.automationml.tests.amlutils.AbstractAMLTest

class AbstractAMLEvaluationTest extends AbstractAMLTest {
	override protected cleanup() {
		// nothing to cleanup
	}

	override protected createChangePropagationSpecifications() {
		return #[new CombinedCAEXChangePropagationSpecification]
	}
	
	override protected setup() {
		/*val docRootAggregator = preloadExistingAggregatorModel("resources/bsp_aml_empty.aml_aggregator")
		createAndSynchronizeModel(AGGREGATOR_MODEL_NAME.projectAggregatorModelPath, docRootAggregator)*/
		val docRootCAEX = preloadExistingCAEXModel("resources/bsp_caex_empty.caex")	
		createAndSynchronizeModel(CAEX_MODEL_NAME.projectCAEXModelPath, docRootCAEX)
		/*val docRootPLCopen = preloadExistingPLCopenModel("resources/bsp_plcopen_empty.tc60201")
		createAndSynchronizeModel(PLCOPEN_MODEL_NAME.projectPLCopenModelPath, docRootPLCopen)*/
		/*val docRootCollada = preloadExistingColladaModel("resources/bsp_collada_empty.dae")
		createAndSynchronizeModel(COLLADA_MODEL_NAME.projectColladaModelPath, docRootCollada)*/
	}
}