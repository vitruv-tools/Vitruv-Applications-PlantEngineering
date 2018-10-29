package tools.vitruv.applications.automationml.tests.caexreactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import caex.caex30.caex.SystemUnitClass

class PrototypeInInternalElementTests extends AbstractCAEXReactionsTest {
	
	@Test
	def testAddClassToInternalElement() {
		System.out.println("Test21A")
		val caexFactory = caexFactory
		val newSystemClassLib = caexFactory.createSystemUnitClassLib
		val newSystemClass = caexFactory.createSystemUnitClass
		val newHierarchy = caexFactory.createInstanceHierarchy
		val newInternalElement = caexFactory.createInternalElement
		
		newSystemClassLib.name = "BspLib"
		newSystemClass.name = "BspClass"
		newHierarchy.name = "BspHier"
		newInternalElement.name = "BspElem"
		
		newSystemClassLib.systemUnitClass.add(newSystemClass)
		CAEXRootElement.systemUnitClassLib.add(newSystemClassLib)
		
		newHierarchy.internalElement.add(newInternalElement)
		CAEXRootElement.instanceHierarchy.add(newHierarchy)
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		newInternalElement.refBaseSystemUnitPath = "BspLib/BspClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(newInternalElement)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		if(second.size == 0) {
			assertTrue(false)
		} else if (second.get(0).size != 1) {
			assertTrue(false)
		} else {
			assertEquals(newSystemClass.name, (second.get(0).get(0) as SystemUnitClass).name)
		}
	}
	
	@Test
	def testRemoveClassFromInternalElement() {
		System.out.println("Test21B")
		val caexFactory = caexFactory
		val newSystemClassLib = caexFactory.createSystemUnitClassLib
		val newSystemClass = caexFactory.createSystemUnitClass
		val newHierarchy = caexFactory.createInstanceHierarchy
		val newInternalElement = caexFactory.createInternalElement
		
		newSystemClassLib.name = "BspLib"
		newSystemClass.name = "BspClass"
		newHierarchy.name = "BspHier"
		newInternalElement.name = "BspElem"
		
		newSystemClassLib.systemUnitClass.add(newSystemClass)
		CAEXRootElement.systemUnitClassLib.add(newSystemClassLib)
		
		newHierarchy.internalElement.add(newInternalElement)
		CAEXRootElement.instanceHierarchy.add(newHierarchy)
		
		newInternalElement.refBaseSystemUnitPath = "BspLib/BspClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		newInternalElement.refBaseSystemUnitPath = ""
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(newInternalElement)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(0, second.size)
	}
	
	@Test
	def testChangeClassInInternalElement() {
		System.out.println("Test21C")
		val caexFactory = caexFactory
		val newSystemClassLib = caexFactory.createSystemUnitClassLib
		val newSystemClassA = caexFactory.createSystemUnitClass
		val newSystemClassB = caexFactory.createSystemUnitClass
		val newHierarchy = caexFactory.createInstanceHierarchy
		val newInternalElement = caexFactory.createInternalElement
		
		newSystemClassLib.name = "BspLib"
		newSystemClassA.name = "BspClass"
		newSystemClassB.name = "AnotherBspClass"
		newHierarchy.name = "BspHier"
		newInternalElement.name = "BspElem"
		
		newSystemClassLib.systemUnitClass.add(newSystemClassA)
		newSystemClassLib.systemUnitClass.add(newSystemClassB)
		CAEXRootElement.systemUnitClassLib.add(newSystemClassLib)
		
		newHierarchy.internalElement.add(newInternalElement)
		CAEXRootElement.instanceHierarchy.add(newHierarchy)
		
		newInternalElement.refBaseSystemUnitPath = "BspLib/BspClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		newInternalElement.refBaseSystemUnitPath = "BspLib/AnotherBspClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(newInternalElement)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		if(second.size == 0) {
			assertTrue(false)
		} else if (second.get(0).size != 1) {
			assertTrue(false)
		} else {
			assertEquals(newSystemClassB.name, (second.get(0).get(0) as SystemUnitClass).name)
		}
	}
	
	//Änderung zurückrollen bei nicht vorhandener Klasse
	
	@Test
	def changeNameToNotExistentClass() {
		System.out.println("Test21D")
		val caexFactory = caexFactory
		val newSystemClassLib = caexFactory.createSystemUnitClassLib
		val newHierarchy = caexFactory.createInstanceHierarchy
		val newInternalElement = caexFactory.createInternalElement
		
		newSystemClassLib.name = "BspLib"
		newHierarchy.name = "BspHier"
		newInternalElement.name = "BspElem"
		
		newHierarchy.internalElement.add(newInternalElement)
		CAEXRootElement.instanceHierarchy.add(newHierarchy)
		CAEXRootElement.systemUnitClassLib.add(newSystemClassLib)
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		newInternalElement.refBaseSystemUnitPath = "BspLib/BspClass"
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(null, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath)
	}
	
	@Test
	def addPrototypeToInternalElementWithColladaCopy() {
		System.out.println("Test21E")
		val caexFactory = caexFactory
		val newSystemClassLib = caexFactory.createSystemUnitClassLib
		val newSystemClass = caexFactory.createSystemUnitClass
		val newHierarchy = caexFactory.createInstanceHierarchy
		val newInternalElement = caexFactory.createInternalElement
		val newInterfaceLib = caexFactory.createInterfaceClassLib
		val newInterfaceA = caexFactory.createInterfaceClass
		val newInterfaceB = caexFactory.createInterfaceClass
		val newInterfaceC = caexFactory.createInterfaceClass
		val newExternalInterface = caexFactory.createExternalInterface
		
		newSystemClassLib.name = "BspLib"
		newSystemClass.name = "BspClass"
		newHierarchy.name = "BspHier"
		newInternalElement.name = "BspElem"
		newInterfaceLib.name = "AutomationMLInterfaceClassLib"
		newInterfaceA.name = "AutomationMLBaseInterface"
		newInterfaceB.name = "ExternalDataConnector"
		newInterfaceC.name = "COLLADAInterface"
		newExternalInterface.refBaseClassPath = "AutomationMLInterfaceClassLib/AutomationMLBaseInterface/ExternalDataConnector/COLLADAInterface"
		
		newInterfaceB.interfaceClass.add(newInterfaceC)
		newInterfaceA.interfaceClass.add(newInterfaceB)
		newInterfaceLib.interfaceClass.add(newInterfaceA)
		CAEXRootElement.interfaceClassLib.add(newInterfaceLib)
		
		newSystemClassLib.systemUnitClass.add(newSystemClass)
		CAEXRootElement.systemUnitClassLib.add(newSystemClassLib)
		
		newHierarchy.internalElement.add(newInternalElement)
		CAEXRootElement.instanceHierarchy.add(newHierarchy)
		
		newSystemClass.externalInterface.add(caexFactory.createExternalInterface)
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		newInternalElement.refBaseSystemUnitPath = "BspLib/BspClass"
		
		testUserInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals("refUri", CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.get(0).attribute.get(0).name)
		assertEquals(newExternalInterface.refBaseClassPath, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).refBaseClassPath)
		assertEquals("refUri", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.get(0).name)
	}
	
	@Test
	def addPrototypeToInternalElementWithPLCopenCopy() {
		System.out.println("Test21F")
		val caexFactory = caexFactory
		val newSystemClassLib = caexFactory.createSystemUnitClassLib
		val newSystemClass = caexFactory.createSystemUnitClass
		val newHierarchy = caexFactory.createInstanceHierarchy
		val newInternalElement = caexFactory.createInternalElement
		val newInterfaceLib = caexFactory.createInterfaceClassLib
		val newInterfaceA = caexFactory.createInterfaceClass
		val newInterfaceB = caexFactory.createInterfaceClass
		val newInterfaceC = caexFactory.createInterfaceClass
		val newExternalInterface = caexFactory.createExternalInterface
		
		newSystemClassLib.name = "BspLib"
		newSystemClass.name = "BspClass"
		newHierarchy.name = "BspHier"
		newInternalElement.name = "BspElem"
		newInterfaceLib.name = "AutomationMLInterfaceClassLib"
		newInterfaceA.name = "AutomationMLBaseInterface"
		newInterfaceB.name = "ExternalDataConnector"
		newInterfaceC.name = "PLCopenXMLInterface"
		newExternalInterface.refBaseClassPath = "AutomationMLInterfaceClassLib/AutomationMLBaseInterface/ExternalDataConnector/PLCopenXMLInterface"
		
		newInterfaceB.interfaceClass.add(newInterfaceC)
		newInterfaceA.interfaceClass.add(newInterfaceB)
		newInterfaceLib.interfaceClass.add(newInterfaceA)
		CAEXRootElement.interfaceClassLib.add(newInterfaceLib)
		
		newSystemClassLib.systemUnitClass.add(newSystemClass)
		CAEXRootElement.systemUnitClassLib.add(newSystemClassLib)
		
		newHierarchy.internalElement.add(newInternalElement)
		CAEXRootElement.instanceHierarchy.add(newHierarchy)
		
		newSystemClass.externalInterface.add(caexFactory.createExternalInterface)
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		newInternalElement.refBaseSystemUnitPath = "BspLib/BspClass"
		
		testUserInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals("refUri", CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).externalInterface.get(0).attribute.get(0).name)
		assertEquals(newExternalInterface.refBaseClassPath, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).refBaseClassPath)
		assertEquals("refUri", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).externalInterface.get(0).attribute.get(0).name)
	}
}