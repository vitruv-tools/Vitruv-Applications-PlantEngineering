package tools.vitruv.applications.automationml.tests.caexreactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import caex.caex30.caex.SystemUnitClass
import tools.vitruv.applications.automationml.tests.amlutils.CAEXModelFactory

class PrototypeInInternalElementTests extends AbstractCAEXReactionsTest {
	def createBasicModel(boolean extended) {
		val newSystemClassLib = CAEXModelFactory.createSystemUnitClassLib(extended, false)
		val newHierarchy = CAEXModelFactory.createInstanceHierarchy(false, false)
		
		CAEXRootElement.systemUnitClassLib.add(newSystemClassLib)
		CAEXRootElement.instanceHierarchy.add(newHierarchy)
		
		CAEXRootElement.saveAndSynchronizeChanges
	}	
	
	@Test
	def testAddClassToInternalElement() {	
		createBasicModel(false)
			
		val internalElement = CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0)
		internalElement.refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(internalElement)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		if(second.size == 0) {
			assertTrue(false)
		} else if (second.get(0).size != 1) {
			assertTrue(false)
		} else {
			assertEquals(CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).name, (second.get(0).get(0) as SystemUnitClass).name)
		}
	}
	
	@Test
	def testRemoveClassFromInternalElement() {
		createBasicModel(false)
		
		val internalElement = CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0)
		internalElement.refBaseSystemUnitPath = "BspLib/BspClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		internalElement.refBaseSystemUnitPath = ""
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(internalElement)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(0, second.size)
	}
	
	@Test
	def testChangeClassInInternalElement() {
		createBasicModel(true)
		
		val internalElement = CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0)
		internalElement.refBaseSystemUnitPath = "BspLib/BspClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		internalElement.refBaseSystemUnitPath = "BspLib/AnotherBspClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(internalElement)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		if(second.size == 0) {
			assertTrue(false)
		} else if (second.get(0).size != 1) {
			assertTrue(false)
		} else {
			assertEquals(CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(1).name, (second.get(0).get(0) as SystemUnitClass).name)
		}
	}
	
	
	@Test
	def changeNameToNotExistentClass() {
		createBasicModel(false)
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "SomeNotExistentClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(null, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath)
	}
	
	@Test
	def addPrototypeToInternalElementWithColladaCopy() {
		createBasicModel(false)
		val interfaceLib = CAEXModelFactory.createInterfaceClassLib(true)
		val externalInterface = CAEXModelFactory.createColladaExternalInterface
		
		CAEXRootElement.interfaceClassLib.add(interfaceLib)
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.add(externalInterface)
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		
		testUserInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals("refUri", CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.get(0).attribute.get(0).name)
		assertEquals(externalInterface.refBaseClassPath, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).refBaseClassPath)
		assertEquals("refUri", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.get(0).name)
	}
	
	@Test
	def addPrototypeToInternalElementWithPLCopenCopy() {
		createBasicModel(false)
		val interfaceLib = CAEXModelFactory.createInterfaceClassLib(true)
		val externalInterface = CAEXModelFactory.createPLCopenExternalInterface
		
		CAEXRootElement.interfaceClassLib.add(interfaceLib)
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.add(externalInterface)
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		
		testUserInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals("refUri", CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.get(0).attribute.get(0).name)
		assertEquals(externalInterface.refBaseClassPath, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).refBaseClassPath)
		assertEquals("refUri", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.get(0).name)
	}
}