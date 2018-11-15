package tools.vitruv.applications.automationml.tests.caexreactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import tools.vitruv.applications.automationml.tests.amlutils.AggregatorModelFactory
import tools.vitruv.applications.automationml.tests.amlutils.CAEXModelFactory

class ChangeExternalInterfacePathTests extends AbstractCAEXReactionsTest {
	// falls nicht Collada: PLCopen
	def createBasicModels(boolean collada) {
		val caexContainer = AggregatorModelFactory.createCAEXContainer
		aggregatorRootElement.caexcontainer = caexContainer
		
		if(collada) {
			val colladaContainerA = AggregatorModelFactory.createColladaContainer
			aggregatorRootElement.colladacontainer.add(colladaContainerA)
			val colladaContainerB = AggregatorModelFactory.createColladaContainer
			aggregatorRootElement.colladacontainer.add(colladaContainerB)
			
			//TODO Collada Dokumente aufbauen
		} else {
			val plcContainerA = AggregatorModelFactory.createPLCopenContainer
			aggregatorRootElement.plcopencontainer.add(plcContainerA)
			val plcContainerB = AggregatorModelFactory.createPLCopenContainer
			aggregatorRootElement.plcopencontainer.add(plcContainerB)
			
			//TODO PLCopen Dokumente aufbauen
		}
		
		//TODO Verknüpfung Container/Dokumente aufbauen????
		
		val interfaceLib = CAEXModelFactory.createInterfaceClassLib(true)
		val instanceHierarchy = CAEXModelFactory.createInstanceHierarchy(false, false)
		val systemUnitClassLib = CAEXModelFactory.createSystemUnitClassLib(false, false)
		
		
		//TODO Typ setzen
		if(collada) {
			val externalInterfaceA = CAEXModelFactory.createColladaExternalInterface
			instanceHierarchy.internalElement.get(0).externalInterface.add(externalInterfaceA)
			
			val externalInterfaceB = CAEXModelFactory.createColladaExternalInterface
			systemUnitClassLib.systemUnitClass.get(0).externalInterface.add(externalInterfaceB)
		} else {
			val externalInterfaceA = CAEXModelFactory.createPLCopenExternalInterface
			instanceHierarchy.internalElement.get(0).externalInterface.add(externalInterfaceA)
			
			val externalInterfaceB = CAEXModelFactory.createPLCopenExternalInterface
			systemUnitClassLib.systemUnitClass.get(0).externalInterface.add(externalInterfaceB)
		}
		
		CAEXRootElement.interfaceClassLib.add(interfaceLib)
		CAEXRootElement.instanceHierarchy.add(instanceHierarchy)
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