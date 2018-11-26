package tools.vitruv.applications.automationml.tests.caexreactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import tools.vitruv.applications.automationml.tests.amlutils.AggregatorModelFactory
import tools.vitruv.applications.automationml.tests.amlutils.CAEXModelFactory
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject

class ChangeExternalInterfacePathTests extends AbstractCAEXReactionsTest {
	// falls nicht Collada: PLCopen
	def createBasicModel(boolean collada) {
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
		
		CAEXRootElement.interfaceClassLib.add(interfaceLib)
		CAEXRootElement.instanceHierarchy.add(instanceHierarchy)
		CAEXRootElement.systemUnitClassLib.add(systemUnitClassLib)
		
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
				
		instanceHierarchy.internalElement.get(0).refBaseSystemUnitPath = "BspSystemClassLib/BspSystemClass"
		
		aggregatorRootElement.saveAndSynchronizeChanges
		CAEXRootElement.saveAndSynchronizeChanges
		
		//TODO Collada/PLCopen synchronisieren
		if(collada) {
			
		} else {
			
		}
	}
	
	//@Test
	def testAddColladaInterfacePathInInternalElement() {
		createBasicModel(true)
		
		val externalInterface = CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0)
		externalInterface.attribute.get(0).value = "BspColladaPath1"
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(externalInterface)
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		if(two.size == 0) {
			assertTrue(false)
		} else {
			assertEquals(1, two.get(0).size)
			//TODO Namensabgleich mit DokumentWurzel 
			//assertEquals(newUrl, (two.get(0).get(0) as GeometryType).id)
		}
	}
	
	//@Test
	def testChangeColladaInterfacePathInInternalElement() {
		createBasicModel(true)
		
		val externalInterface = CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0)
		externalInterface.attribute.get(0).value = "BspColladaPath1"
		CAEXRootElement.saveAndSynchronizeChanges
		
		externalInterface.attribute.get(0).value = "BspColladaPath2"
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(externalInterface)
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		if(two.size == 0) {
			assertTrue(false)
		} else {
			assertEquals(1, two.get(0).size)
			//TODO Namensabgleich mit DokumentWurzel 
			//assertEquals(newUrl, (two.get(0).get(0) as GeometryType).id)
		}
	}
	
	//@Test
	def testChangeColladaInterfacePathInSystemUnitClassWithoutCloneChanges() {
		createBasicModel(true)
		
		val externalInterface = CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.get(0)
		externalInterface.attribute.get(0).value = "BspColladaPath1"
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(externalInterface)
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		if(two.size == 0) {
			assertTrue(false)
		} else {
			assertEquals(1, two.get(0).size)
			assertEquals(0, CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.size)
		}
	}
	
	//@Test
	def testChangeColladaInterfacePathInSystemUnitClassWithCloneChanges() {
		createBasicModel(true)
		
		val externalInterface = CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.get(0)
		externalInterface.attribute.get(0).value = "BspColladaPath1"
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(externalInterface)
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		if(two.size == 0) {
			assertTrue(false)
		} else {
			assertEquals(2, two.get(0).size)
			assertEquals(1, CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.size)
		}
	}
	
	//@Test
	def testAddPLCopenInterfacePathInInternalElement() {
		createBasicModel(false)
		
		val externalInterface = CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0)
		externalInterface.attribute.get(0).value = "BspPLCopenPath1"
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(externalInterface)
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		if(two.size == 0) {
			assertTrue(false)
		} else {
			assertEquals(1, two.get(0).size)
			//TODO Namensabgleich mit DokumentWurzel 
			//assertEquals(newUrl, (two.get(0).get(0) as GeometryType).id)
		}
	}
	
	//@Test
	def testChangePLCopenInterfacePathInInternalElement() {
		createBasicModel(false)
		
		val externalInterface = CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0)
		externalInterface.attribute.get(0).value = "BspPLCopenPath1"
		CAEXRootElement.saveAndSynchronizeChanges
		
		externalInterface.attribute.get(0).value = "BspPLCopenPath2"
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(externalInterface)
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		if(two.size == 0) {
			assertTrue(false)
		} else {
			assertEquals(1, two.get(0).size)
			//TODO Namensabgleich mit DokumentWurzel 
			//assertEquals(newUrl, (two.get(0).get(0) as GeometryType).id)
		}
	}
	
	//@Test
	def testChangePLCopenInterfacePathInSystemUnitClassWithoutCloneChanges() {
		createBasicModel(false)
		
		val externalInterface = CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.get(0)
		externalInterface.attribute.get(0).value = "BspPLCopenPath1"
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(externalInterface)
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		if(two.size == 0) {
			assertTrue(false)
		} else {
			assertEquals(1, two.get(0).size)
			assertEquals(0, CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.size)
		}
	}
	
	//@Test
	def testChangePLCopenInterfacePathInSystemUnitClassWithCloneChanges() {
		createBasicModel(false)
		
		val externalInterface = CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.get(0)
		externalInterface.attribute.get(0).value = "BspPLCopenPath1"
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(externalInterface)
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		if(two.size == 0) {
			assertTrue(false)
		} else {
			assertEquals(2, two.get(0).size)
			assertEquals(1, CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).externalInterface.size)
		}
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