package tools.vitruv.applications.automationml.tests.caexreactions

import org.junit.Test

import static org.junit.Assert.assertEquals
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
		//first.add(internalElement)
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).name, (second.get(0).get(0) as SystemUnitClass).name)
	}
	
	@Test
	def testRemoveClassFromInternalElement() {
		createBasicModel(false)
		
		val internalElement = CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0)
		internalElement.refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		internalElement.refBaseSystemUnitPath = ""
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(0, second.size)
	}
	
	@Test
	def testChangeClassInInternalElement() {
		createBasicModel(true)
		
		val internalElement = CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0)
		internalElement.refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		internalElement.refBaseSystemUnitPath = "BspSystemLib/AnotherSystemClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(1).name, (second.get(0).get(0) as SystemUnitClass).name)
	}
	
	
	@Test
	def changeNameToNotExistentClass() {
		createBasicModel(false)
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "SomeNotExistentClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(null, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath)
	}
	
	//@Test
	def addPrototypeToInternalElementWithColladaCopy() {
		createBasicModel(false)
		
		val interfaceLib = CAEXModelFactory.createInterfaceClassLib(true)
		CAEXRootElement.interfaceClassLib.add(interfaceLib)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val externalInterface = CAEXModelFactory.createColladaExternalInterface			
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.add(externalInterface)
		
		testUserInteractor.addNextConfirmationInput(false)
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		testUserInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals("refUri", CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.get(0).attribute.get(0).name)
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.size)
		assertEquals(externalInterface.refBaseClassPath, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).refBaseClassPath)
		assertEquals("refUri", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.get(0).name)
	}
	
	//@Test
	def addPrototypeToInternalElementWithPLCopenCopy() {
		createBasicModel(false)
		
		val interfaceLib = CAEXModelFactory.createInterfaceClassLib(true)
		CAEXRootElement.interfaceClassLib.add(interfaceLib)
		CAEXRootElement.saveAndSynchronizeChanges	
		
		val externalInterface = CAEXModelFactory.createPLCopenExternalInterface
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.add(externalInterface)
		
		testUserInteractor.addNextConfirmationInput(false)
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		testUserInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals("refUri", CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.get(0).attribute.get(0).name)
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.size)
		assertEquals(externalInterface.refBaseClassPath, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).refBaseClassPath)
		assertEquals("refUri", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.get(0).name)
	}
}