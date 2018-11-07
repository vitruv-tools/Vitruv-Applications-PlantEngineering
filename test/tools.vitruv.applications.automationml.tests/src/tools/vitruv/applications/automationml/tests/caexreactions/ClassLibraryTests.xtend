package tools.vitruv.applications.automationml.tests.caexreactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

class ClassLibraryTests extends AbstractCAEXReactionsTest {
	
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