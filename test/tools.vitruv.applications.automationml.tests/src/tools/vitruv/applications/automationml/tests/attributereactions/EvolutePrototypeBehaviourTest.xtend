package tools.vitruv.applications.automationml.tests.attributereactions

import org.junit.Test
import static org.junit.Assert.assertEquals
import tools.vitruv.applications.automationml.tests.amlutils.CAEXAttributeModelFactory
import tools.vitruv.applications.automationml.utils.propertybasedtags.PropertyTags

class EvolutePrototypeBehaviourTest extends AbstractCAEXAttributeReactionsTest {
	//cloneType 3: descriptive
	def createBasicModel(int cloneType, boolean maintainable) {
		if(cloneType == 3) {
			createModelWithDescriptiveObject()
			return
		}
		
		val systemLib = CAEXAttributeModelFactory.createSystemUnitClassLib(false, PropertyTags.BEHAVIOUR)
		CAEXRootElement.systemUnitClassLib.add(systemLib)
		val instanceHierarchy = CAEXAttributeModelFactory.createInstanceHierarchy
		CAEXRootElement.instanceHierarchy.add(instanceHierarchy)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(0)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		userInteractor.addNextSingleSelection(cloneType)
		if(cloneType == 0) {
			userInteractor.addNextConfirmationInput(maintainable)
		}
		CAEXRootElement.saveAndSynchronizeChanges
	}
	
	def createModelWithDescriptiveObject() {
		val systemLib = CAEXAttributeModelFactory.createSystemUnitClassLibWithInternalElement(PropertyTags.BEHAVIOUR)
		CAEXRootElement.systemUnitClassLib.add(systemLib)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(0)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/AnotherSystemClass"
		CAEXRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def evolutePrototypeWithDescriptiveNotEvolutedClone() {
		createBasicModel(3, false)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(1).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	//@Test
	def evolutePrototypeWithDescriptiveEvolutedClone() {
		//TODO
	}
	
	@Test
	def evolutePrototypeWithAbstractNotEvolutedClone() {
		createBasicModel(1, true)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	//@Test
	def evolutePrototypeWithAbstractEvolutedClone() {
		//TODO
	}
	
	@Test
	def evolutePrototypeWithRealNotEvolutedNotMaintableClone() {
		createBasicModel(0, false)
		val oldValue = CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = "OtherValue"
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(oldValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evolutePrototypeWithRealNotEvolutedMaintableClone() {
		createBasicModel(0, true)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	//@Test
	def evolutePrototypeWithRealEvolutedNotMaintableClone() {
		//TODO
	}
	
	//@Test
	def evolutePrototypeWithRealEvolutedMaintableClone() {
		//TODO
	}
	
	@Test
	def evolutePrototypeWithExemplaryNotEvolutedClone() {
		createBasicModel(2, true)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	//@Test
	def evolutePrototypeWithExemplaryEvolutedClone() {
		//TODO
	}
}