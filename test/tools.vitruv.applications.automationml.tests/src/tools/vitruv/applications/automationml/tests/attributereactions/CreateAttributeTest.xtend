package tools.vitruv.applications.automationml.tests.attributereactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import tools.vitruv.applications.automationml.tests.amlutils.CAEXAttributeModelFactory
import tools.vitruv.applications.automationml.utils.propertybasedtags.PropertyTags
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import caex.caex30.caex.SystemUnitClass

class CreateAttributeTest extends AbstractCAEXAttributeReactionsTest {
	def createBasicModel(int prototypTag) {
		val systemClassLib = CAEXAttributeModelFactory.createSystemUnitClassLib(false, null)
		CAEXRootElement.systemUnitClassLib.add(systemClassLib)
		userInteractor.addNextSingleSelection(prototypTag)
		CAEXRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def addPropertyToAlwaysPrototype() {
		createBasicModel(0)
		val attribute = CAEXAttributeModelFactory.createAttribute(PropertyTags.COMPOSITION, false)
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.add(attribute)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(0, second.size)
	}
	
	@Test
	def addPropertyToNeverPrototype() {
		createBasicModel(1)
		val attribute = CAEXAttributeModelFactory.createAttribute(PropertyTags.COMPOSITION, false)
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.add(attribute)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(0, second.size)
	}
	
	@Test
	def addBehaviourPropertyToPrototype() {
		createBasicModel(2)
		val attribute = CAEXAttributeModelFactory.createAttribute(PropertyTags.BEHAVIOUR, false)
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.add(attribute)
		userInteractor.addNextSingleSelection(0)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, PropertyTags.BEHAVIOUR)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemClass", (second.get(0).get(0) as SystemUnitClass).name)
	}
	
	@Test
	def addSaftyPropertyToPrototype() {
		createBasicModel(2)
		val attribute = CAEXAttributeModelFactory.createAttribute(PropertyTags.SAFTY, false)
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.add(attribute)
		userInteractor.addNextSingleSelection(1)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, PropertyTags.SAFTY)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemClass", (second.get(0).get(0) as SystemUnitClass).name)
	}
	
	@Test
	def addCompositionPropertyToPrototype() {
		createBasicModel(2)
		val attribute = CAEXAttributeModelFactory.createAttribute(PropertyTags.COMPOSITION, false)
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.add(attribute)
		userInteractor.addNextSingleSelection(2)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, PropertyTags.COMPOSITION)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemClass", (second.get(0).get(0) as SystemUnitClass).name)
	}
	
	@Test
	def addMaterialPropertyToPrototype() {
		createBasicModel(2)
		val attribute = CAEXAttributeModelFactory.createAttribute(PropertyTags.MATERIAL, false)
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.add(attribute)
		userInteractor.addNextSingleSelection(3)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, PropertyTags.MATERIAL)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemClass", (second.get(0).get(0) as SystemUnitClass).name)
	}
	
	@Test
	def addTechnicalPropertyToPrototype() {
		createBasicModel(2)
		val attribute = CAEXAttributeModelFactory.createAttribute(PropertyTags.TECHNOLOGY, false)
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.add(attribute)
		userInteractor.addNextSingleSelection(4)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, PropertyTags.TECHNOLOGY)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemClass", (second.get(0).get(0) as SystemUnitClass).name)
	}
	
	@Test
	def addRulePropertyToPrototype() {
		createBasicModel(2)
		val attribute = CAEXAttributeModelFactory.createAttribute(PropertyTags.RULE, false)
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.add(attribute)
		userInteractor.addNextSingleSelection(5)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).attribute.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, PropertyTags.RULE)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemClass", (second.get(0).get(0) as SystemUnitClass).name)
	}
}