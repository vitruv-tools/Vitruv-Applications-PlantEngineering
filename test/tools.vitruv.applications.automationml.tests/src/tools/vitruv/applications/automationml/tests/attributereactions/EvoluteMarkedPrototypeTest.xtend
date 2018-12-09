package tools.vitruv.applications.automationml.tests.attributereactions

import org.junit.Test
import static org.junit.Assert.assertEquals
import tools.vitruv.applications.automationml.tests.amlutils.CAEXAttributeModelFactory
import tools.vitruv.applications.automationml.utils.propertybasedtags.PropertyTags

class EvoluteMarkedPrototypeTest extends AbstractCAEXAttributeReactionsTest {
	def createBasicModel(int prototypeMarked) {
		val systemLib = CAEXAttributeModelFactory.createSystemUnitClassLib(false, PropertyTags.COMPOSITION)
		CAEXRootElement.systemUnitClassLib.add(systemLib)
		val instanceHierarchy = CAEXAttributeModelFactory.createInstanceHierarchy
		CAEXRootElement.instanceHierarchy.add(instanceHierarchy)
		userInteractor.addNextSingleSelection(prototypeMarked)
		userInteractor.addNextSingleSelection(2)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		userInteractor.addNextSingleSelection(2)
		CAEXRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def evolutePropertyOfAlwaysMarkedPrototype() {
		createBasicModel(0)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = "AndererWert"
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.size)
	}
	
	@Test
	def evolutePropertyOfNeverMarkedPrototype() {
		createBasicModel(1)
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = "AndererWert"
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.size)
		assertEquals("someCompositionProperty", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).name)
		assertEquals("DreiBauteile", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
}