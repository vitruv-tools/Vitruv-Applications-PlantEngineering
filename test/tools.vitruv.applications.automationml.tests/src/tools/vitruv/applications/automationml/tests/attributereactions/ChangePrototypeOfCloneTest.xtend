package tools.vitruv.applications.automationml.tests.attributereactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import tools.vitruv.applications.automationml.tests.amlutils.CAEXAttributeModelFactory

class ChangePrototypeOfCloneTest extends AbstractCAEXAttributeReactionsTest {
	//@Test
	def changePrototypeOfEvolutedDescriptivClone() {
		//TODO
	}
	
	//@Test
	def changePrototypeOfNotEvolutedDescriptiveClone() {
		//TODO
	}
	
	@Test
	def changePrototypeOfCloneWithPropertyTwice() {
		val systemLib = CAEXAttributeModelFactory.createSystemUnitClassLibWithMoreClasses(true)
		CAEXRootElement.systemUnitClassLib.add(systemLib)
		val instanceHierarchy = CAEXAttributeModelFactory.createInstanceHierarchy
		CAEXRootElement.instanceHierarchy.add(instanceHierarchy)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(3)
		userInteractor.addNextSingleSelection(4)
		userInteractor.addNextSingleSelection(2)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		userInteractor.addNextSingleSelection(2)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/AnotherSystemClass"
		userInteractor.addNextSingleSelection(2)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(3, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.size)
		assertEquals("Blech", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
		assertEquals("VielVerbrauch", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(1).value)
		assertEquals("VierBauteile", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(2).value)
	}
	
	@Test
	def changePrototypeOfOtherClone() {
		val systemLib = CAEXAttributeModelFactory.createSystemUnitClassLibWithMoreClasses(false)
		CAEXRootElement.systemUnitClassLib.add(systemLib)
		val instanceHierarchy = CAEXAttributeModelFactory.createInstanceHierarchy
		CAEXRootElement.instanceHierarchy.add(instanceHierarchy)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(3)
		userInteractor.addNextSingleSelection(4)
		userInteractor.addNextSingleSelection(0)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		userInteractor.addNextSingleSelection(2)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/AnotherSystemClass"
		userInteractor.addNextSingleSelection(2)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(4, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.size)
		assertEquals("DreiBauteile", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
		assertEquals("Blech", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(1).value)
		assertEquals("VielVerbrauch", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(2).value)
		assertEquals("DreiGradDrehen", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(3).value)
	}
}