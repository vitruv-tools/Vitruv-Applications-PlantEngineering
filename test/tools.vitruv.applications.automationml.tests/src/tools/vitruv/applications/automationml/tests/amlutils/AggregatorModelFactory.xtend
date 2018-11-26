package tools.vitruv.applications.automationml.tests.amlutils

import edu.kit.sdq.aml_aggregator.Aml_aggregatorFactory
import edu.kit.sdq.aml_aggregator.CAEXContainer
import edu.kit.sdq.aml_aggregator.ColladaContainer
import edu.kit.sdq.aml_aggregator.PLCopenContainer

class AggregatorModelFactory {
	private static int numberOfPLCopenContainer = 0
	private static int numberOfColladaContainer = 0
	
	protected static def Aml_aggregatorFactory amlFactory() {
		return Aml_aggregatorFactory.eINSTANCE
	}
	
	//TODO entstehenden Containern Namen geben
	
	static def CAEXContainer createCAEXContainer() {
		val container = amlFactory.createCAEXContainer
		container.path = "BspCAEXPath"
		return container
	}
	
	static def ColladaContainer createColladaContainer() {
		val container = amlFactory.createColladaContainer
		numberOfColladaContainer ++
		container.path = "BspColladaPath" + numberOfColladaContainer
		return container
	}
	
	static def PLCopenContainer createPLCopenContainer() {
		val container = amlFactory.createPLCopenContainer
		numberOfPLCopenContainer ++
		container.path = "BspPLCopenPath" + numberOfPLCopenContainer
		return container
	}
}