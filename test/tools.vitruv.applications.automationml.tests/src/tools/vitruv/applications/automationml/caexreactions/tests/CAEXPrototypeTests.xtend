package tools.vitruv.applications.automationml.caexreactions.tests

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import caex.caex30.caex.SystemUnitClass
import tools.vitruv.framework.userinteraction.UserInteractor

class CAEXPrototypeTests extends AbstractCAEXReactionsTest {
	
	@Test
	public def testAddClassToInternalElement() {
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
		
		if (second.get(0).size != 1) {
			assertTrue(false)
		} else {
			assertEquals(newSystemClass.name, (second.get(0).get(0) as SystemUnitClass).name)
		}
	}
	
	@Test
	public def testRemoveClassFromInternalElement() {
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
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(newInternalElement)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(0, second.get(0).size)
	}
	
	@Test
	public def testChangeClassInInternalElement() {
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
		
		if (second.get(0).size != 1) {
			assertTrue(false)
		} else {
			assertEquals(newSystemClassB.name, (second.get(0).get(0) as SystemUnitClass).name)
		}
	}
}
