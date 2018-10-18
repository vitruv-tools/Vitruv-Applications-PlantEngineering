package tools.vitruv.applications.automationml.caexreactions.tests

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject

class CAEXClassLibTests extends AbstractCAEXReactionsTest {
	
	private def createBasicModel(boolean extended) {
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
		
		if(extended) {
			val anotherSystemClass = caexFactory.createSystemUnitClass
			anotherSystemClass.name = "AnotherClass"
			newSystemClass.systemUnitClass.add(anotherSystemClass)
			newInternalElement.refBaseSystemUnitPath = "BspLib/BspClass/AnotherClass"
		} else {
			newInternalElement.refBaseSystemUnitPath = "BspLib/BspClass"
		}
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	public def testLibDeletionWithoutCloneDeletion() {
		System.out.println("Test22A")
		
		createBasicModel(false)
		
		CAEXRootElement.systemUnitClassLib.remove(0)
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElement.systemUnitClassLib.size)
		assertEquals("", CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath)
	}
	
	@Test
	public def testLibDeletionWithCloneDeletion() {
		System.out.println("Test2B")
		
		createBasicModel(false)
		
		CAEXRootElement.systemUnitClassLib.remove(0)
		testUserInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElement.systemUnitClassLib.size)
		assertEquals(0, CAEXRootElement.instanceHierarchy.get(0).internalElement.size)
	}
	
	@Test
	public def testClassDeletionWithoutCloneDeletion() {
		System.out.println("Test22C")
		
		createBasicModel(false)
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		val third = new LinkedList<EObject>
		third.add(CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0))
		val fourth = correspondenceModel.getCorrespondingEObjects(third)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.remove(0)
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.size)
		assertEquals("", CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath)
	}
	
	@Test
	public def testClassDeletionWithCloneDeletion() {
		System.out.println("Test22D")
		
		createBasicModel(false)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.remove(0)
		testUserInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.size)
		assertEquals(0, CAEXRootElement.instanceHierarchy.get(0).internalElement.size)
	}
	
	@Test
	public def testClassInClassDeletionWithoutCloneDeletion() {
		System.out.println("Test22E")
		
		createBasicModel(true)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).systemUnitClass.remove(0)
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).systemUnitClass.size)
		assertEquals("", CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath)
	}
	
	@Test
	public def testClassInClassDeletionWithCloneDeletion() {
		System.out.println("Test22F")
		
		createBasicModel(true)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).systemUnitClass.remove(0)
		
		testUserInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElement.systemUnitClassLib.size)
		assertEquals(0, CAEXRootElement.instanceHierarchy.get(0).internalElement.size)
	}
}