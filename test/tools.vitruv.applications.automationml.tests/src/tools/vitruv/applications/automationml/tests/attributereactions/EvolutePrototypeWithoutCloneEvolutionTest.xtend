package tools.vitruv.applications.automationml.tests.attributereactions

import org.junit.Test
import static org.junit.Assert.assertEquals
import tools.vitruv.applications.automationml.utils.propertybasedtags.PropertyTags
import tools.vitruv.applications.automationml.tests.amlutils.CAEXAttributeModelFactory

class EvolutePrototypeWithoutCloneEvolutionTest extends AbstractCAEXAttributeReactionsTest {
	//cloneType 3: descriptive
	def createBasicModel(String tag, int tagNumber, int cloneType) {
		if(cloneType == 3) {
			createModelWithDescriptiveObject(tag, tagNumber)
			return
		}
		
		val systemLib = CAEXAttributeModelFactory.createSystemUnitClassLib(false, tag)
		CAEXRootElement.systemUnitClassLib.add(systemLib)
		val instanceHierarchy = CAEXAttributeModelFactory.createInstanceHierarchy
		CAEXRootElement.instanceHierarchy.add(instanceHierarchy)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(tagNumber)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		userInteractor.addNextSingleSelection(cloneType)
		CAEXRootElement.saveAndSynchronizeChanges
	}
	
	def createModelWithDescriptiveObject(String tag, int tagNumber) {
		val systemLib = CAEXAttributeModelFactory.createSystemUnitClassLibWithInternalElement(tag)
		CAEXRootElement.systemUnitClassLib.add(systemLib)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(2)
		userInteractor.addNextSingleSelection(tagNumber)
		CAEXRootElement.saveAndSynchronizeChanges
		
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/AnotherSystemClass"
		CAEXRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def evoluteSaftyPropertyInPrototypeWithDescriptiveClone() {
		createBasicModel(PropertyTags.SAFTY, 1, 3)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(1).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteSaftyPropertyInPrototypeWithAbstractClone() {
		createBasicModel(PropertyTags.SAFTY, 1, 1)
		val oldValue = CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = "OtherValue"

		userInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(oldValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteSaftyPropertyInPrototypeWithRealClone() {
		createBasicModel(PropertyTags.SAFTY, 1, 0)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = newValue
		
		userInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteSaftyPropertyInPrototypeWithExemplaryClone() {
		createBasicModel(PropertyTags.SAFTY, 1, 2)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = newValue
		
		userInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteCompositionPropertyInPrototypeWithDescriptiveClone() {
		createBasicModel(PropertyTags.COMPOSITION, 2, 3)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(1).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteCompositionPropertyInPrototypeWithAbstractClone() {
		createBasicModel(PropertyTags.COMPOSITION, 2, 1)
		val oldValue = CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = "otherValue"
		
		userInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(oldValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteCompositionPropertyInPrototypeWithRealClone() {
		createBasicModel(PropertyTags.COMPOSITION, 2, 0)
		val oldValue = CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = "OtherValue"
		
		userInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(oldValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteCompositionPropertyInPrototypeWithExemplaryClone() {
		createBasicModel(PropertyTags.COMPOSITION, 2, 2)
		val oldValue = CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = "OtherValue"
		CAEXRootElement.saveAndSynchronizeChanges

		assertEquals(oldValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteMaterialPropertyInPrototypeWithDescriptiveClone() {
		createBasicModel(PropertyTags.MATERIAL, 3, 3)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(1).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		assertEquals(newValue, CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteMaterialPropertyInPrototypeWithAbstractClone() {
		createBasicModel(PropertyTags.MATERIAL, 3, 1)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		assertEquals(newValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteMaterialPropertyInPrototypeWithRealClone() {
		createBasicModel(PropertyTags.MATERIAL, 3, 0)
		val oldValue = CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = "OtherValue"
		
		userInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(oldValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteMaterialPropertyInPrototypeWithExemplaryClone() {
		createBasicModel(PropertyTags.MATERIAL, 3, 2)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = newValue
		
		userInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteTechnicalPropertyInPrototypeWithDescriptiveClone() {
		createBasicModel(PropertyTags.TECHNOLOGY, 4, 3)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(1).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteTechnicalPropertyInPrototypeWithAbstractClone() {
		createBasicModel(PropertyTags.TECHNOLOGY, 4, 1)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteTechnicalPropertyInPrototypeWithRealClone() {
		createBasicModel(PropertyTags.TECHNOLOGY, 4, 0)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = newValue
		
		userInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
		
	}
	
	@Test
	def evoluteTechnicalPropertyInPrototypeWithExemplaryClone() {
		createBasicModel(PropertyTags.TECHNOLOGY, 4, 2)
		val oldValue = CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = "OtherValue"
		
		userInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(oldValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteRulePropertyInPrototypeWithDescriptiveClone() {
		createBasicModel(PropertyTags.RULE, 5, 3)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(1).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteRulePropertyInPrototypeWithAbstractClone() {
		createBasicModel(PropertyTags.RULE, 5, 1)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteRulePropertyInPrototypeWithRealClone() {
		createBasicModel(PropertyTags.RULE, 5, 0)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def evoluteRulePropertyInPrototypeWithExemplaryClone() {
		createBasicModel(PropertyTags.RULE, 5, 2)
		val newValue = "OtherValue"
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0).value = newValue
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(newValue, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
}