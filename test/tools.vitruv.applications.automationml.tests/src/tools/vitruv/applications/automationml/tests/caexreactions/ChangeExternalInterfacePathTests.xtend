package tools.vitruv.applications.automationml.tests.caexreactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull

class ChangeExternalInterfacePathTests extends AbstractCAEXReactionsTest {
	// falls nicht Collada: PLCopen
	def createBasicModels(boolean collada) {
		val caexContainer = aggregatorFactory.createCAEXContainer
		caexContainer.path = "bspCAEXPath"
		aggregatorRootElement.caexcontainer = caexContainer
		
		if(collada) {
			val colladaContainerA = aggregatorFactory.createColladaContainer
			colladaContainerA.path = "bspColladaPath"
			aggregatorRootElement.colladacontainer.add(colladaContainerA)
			val colladaContainerB = aggregatorFactory.createColladaContainer
			colladaContainerB.path = "anotherColladaPath"
			aggregatorRootElement.colladacontainer.add(colladaContainerA)
			
			//TODO Collada Dokumente aufbauen
		} else {
			val plcContainerA = aggregatorFactory.createPLCopenContainer
			plcContainerA.path = "bspPlcPath"
			aggregatorRootElement.plcopencontainer.add(plcContainerA)
			val plcContainerB = aggregatorFactory.createPLCopenContainer
			plcContainerB.path = "anotherPlcPath"
			aggregatorRootElement.plcopencontainer.add(plcContainerB)
			
			//TODO PLCopen Dokumente aufbauen
		}
		
		//TODO Verknüpfung Container/Dokumente aufbauen????
		
		createCAEXBasicInterfaceLib
		
		val instanceHierarchy = caexFactory.createInstanceHierarchy
		val internalElement = caexFactory.createInternalElement
		val systemUnitClassLib = caexFactory.createSystemUnitClassLib
		val systemUnitClass = caexFactory.createSystemUnitClass
		
		val externalInterfaceA = caexFactory.createExternalInterface
		val externalInterfaceB = caexFactory.createExternalInterface
		
		//TODO Typ setzen
		if(collada) {
			
		} else {
			
		}
		
		internalElement.externalInterface.add(externalInterfaceA)
		instanceHierarchy.internalElement.add(internalElement)
		CAEXRootElement.instanceHierarchy.add(instanceHierarchy)
		
		systemUnitClass.externalInterface.add(externalInterfaceB)
		systemUnitClassLib.systemUnitClass.add(systemUnitClass)
		CAEXRootElement.systemUnitClassLib.add(systemUnitClassLib)
		
		aggregatorRootElement.saveAndSynchronizeChanges
		CAEXRootElement.saveAndSynchronizeChanges
		
		//TODO Collada/PLCopen synchronisieren
		if(collada) {
			
		} else {
			
		}
	}
	
	//@Test
	def testChangeColladaInterfacePathInInternalElement() {
		
	}
	
	//@Test
	def testChangeColladaInterfacePathInSystemUnitClassWithoutCloneChanges() {
		
	}
	
	//@Test
	def testChangeColladaInterfacePathInSystemUnitClassWithCloneChanges() {
		
	}
	
	//@Test
	def testChangePLCopenInterfacePathInInternalElement() {
		
	}
	
	//@Test
	def testChangePLCopenInterfacePathInSystemUnitClassWithoutCloneChanges() {
		
	}
	
	//@Test
	def testChangePLCopenInterfacePathInSystemUnitClassWithCloneChanges() {
		
	}
	
	//@Test
	def testChangeColladaInterfacePathWithDocCreation() {
		
	}
	
	//@Test
	def testChangeColladaInterfacePathWithPathAdaptation() {
		
	}
	
	//@Test
	def testChangePLCopenInterfacePathWithDocCreation() {
		
	}
	
	//@Test
	def testChangePLCopenInterfacePathWithPathAdaptation() {
		
	}
}