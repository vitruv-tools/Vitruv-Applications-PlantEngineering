package tools.vitruv.applications.automationml.tests.caexreactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import tools.vitruv.applications.automationml.tests.amlutils.CAEXModelFactory

class ClassLibraryTests extends AbstractCAEXReactionsTest {
	
	private def createBasicModel(boolean extended) {
		val newSystemClassLib = CAEXModelFactory.createSystemUnitClassLib(extended, true)
		val newHierarchy = CAEXModelFactory.createInstanceHierarchy(false, false)
		
		CAEXRootElement.systemUnitClassLib.add(newSystemClassLib)
		CAEXRootElement.instanceHierarchy.add(newHierarchy)
		
		if(extended) {
			newHierarchy.internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass/AnotherSystemClass"
		} else {
			newHierarchy.internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		}
		
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def testLibDeletionWithoutCloneDeletion() {
		createBasicModel(false)
		
		CAEXRootElement.systemUnitClassLib.remove(0)
		testUserInteractor.addNextConfirmationInput(false)
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElementVirtualModel.systemUnitClassLib.size)
		assertEquals("", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath)
	}
	
	@Test
	def testLibDeletionWithCloneDeletion() {
		createBasicModel(false)
		
		CAEXRootElement.systemUnitClassLib.remove(0)
		testUserInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElementVirtualModel.systemUnitClassLib.size)
		assertEquals(0, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.size)
	}
	
	@Test
	def testClassDeletionWithoutCloneDeletion() {
		createBasicModel(false)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.remove(0)
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.size)
		assertEquals("", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath)
	}
	
	@Test
	def testClassDeletionWithCloneDeletion() {
		createBasicModel(false)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.remove(0)
		testUserInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.size)
		assertEquals(0, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.size)
	}
	
	@Test
	def testClassInClassDeletionWithoutCloneDeletion() {
		createBasicModel(true)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).systemUnitClass.remove(0)
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).systemUnitClass.size)
		assertEquals("", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath)
	}
	
	@Test
	def testClassInClassDeletionWithCloneDeletion() {
		createBasicModel(true)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).systemUnitClass.remove(0)
		
		testUserInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).systemUnitClass.size)
		assertEquals(0, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.size)
	}
	
	@Test
	def testClassWithSubclassDeletionWithoutCloneDeletion() {
		createBasicModel(true)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.remove(0)
		testUserInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.size)
		assertEquals("", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath)
	}
	
	@Test
	def testClassWithSubclassDeletionWithCloneDeletion() {
		createBasicModel(true)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.remove(0)
		
		testUserInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.size)
		assertEquals(0, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.size)
	}
	
	@Test 
	def testClassLibNameChange() {
		createBasicModel(false)
		
		CAEXRootElement.systemUnitClassLib.get(0).name = "NewLibName"
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals("NewLibName/BspClass",  CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath)
	}
	
	@Test
	def testClassNameChange() {
		createBasicModel(false)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).name = "NewClassName"
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals("BspLib/NewClassName",  CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath)
	}
	
	@Test
	def testClasWithSubClassNameChange() {
		createBasicModel(true)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).name = "NewClassName"
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals("BspLib/NewClassName/AnotherClass",  CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath)
	}
}