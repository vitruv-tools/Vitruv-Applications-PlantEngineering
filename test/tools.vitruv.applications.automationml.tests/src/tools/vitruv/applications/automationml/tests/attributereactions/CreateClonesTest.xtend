package tools.vitruv.applications.automationml.tests.attributereactions

import org.junit.Test
import static org.junit.Assert.assertEquals
import tools.vitruv.applications.automationml.tests.amlutils.CAEXAttributeModelFactory
import tools.vitruv.applications.automationml.utils.propertybasedtags.PropertyTags
import org.eclipse.emf.ecore.EObject
import java.util.LinkedList
import tools.vitruv.applications.automationml.utils.propertybasedtags.CloneTags
import caex.caex30.caex.SystemUnitClass
import caex.caex30.caex.InternalElement

class CreateClonesTest extends AbstractCAEXAttributeReactionsTest {
	def createBasicModel(boolean withDescriptionClone, int prototypeTag) {
		if(withDescriptionClone) {
			val systemLib = CAEXAttributeModelFactory.createSystemUnitClassLibWithInternalElement(PropertyTags.BEHAVIOUR)
			CAEXRootElement.systemUnitClassLib.add(systemLib)
		} else {
			val systemLib = CAEXAttributeModelFactory.createSystemUnitClassLib(false, PropertyTags.BEHAVIOUR)
			CAEXRootElement.systemUnitClassLib.add(systemLib)
			val instanceHierarchy = CAEXAttributeModelFactory.createInstanceHierarchy
			CAEXRootElement.instanceHierarchy.add(instanceHierarchy)
		}
		
		if(withDescriptionClone) {
			userInteractor.addNextSingleSelection(prototypeTag)
			userInteractor.addNextSingleSelection(prototypeTag)
		} else {
			userInteractor.addNextSingleSelection(prototypeTag)
		}
		
		userInteractor.addNextSingleSelection(0)
		CAEXRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def createCloneOfNeverPrototype() {
		createBasicModel(false, 1)
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.size)
		assertEquals("someBehaviourProperty", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).name)
		assertEquals("DreiGradDrehen", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def createCloneOfAlwaysPrototype() {
		createBasicModel(false, 0)
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		CAEXRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.size)
	}
	
	@Test
	def createDescriptiveClone() {
		createBasicModel(true, 2)
		CAEXRootElement.systemUnitClassLib.get(0).systemUnitClass.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/AnotherSystemClass"
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).internalElement.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, CloneTags.DESCRIPTIVE)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("AnotherSystemClass", (second.get(0).get(0) as SystemUnitClass).name)
		
		assertEquals(1, CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).internalElement.get(0).attribute.size)
		assertEquals("someBehaviourProperty", CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).internalElement.get(0).attribute.get(0).name)
		assertEquals("DreiGradDrehen", CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def createAbstractClone() {
		createBasicModel(false, 2)
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		userInteractor.addNextSingleSelection(1)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, CloneTags.ABSTRACT)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemClass", (second.get(0).get(0) as SystemUnitClass).name)
		
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.size)
		assertEquals("someBehaviourProperty", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).name)
		assertEquals("DreiGradDrehen", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def createExampleClone() {
		createBasicModel(false, 2)
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		userInteractor.addNextSingleSelection(2)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, CloneTags.EXAMPLARY)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemClass", (second.get(0).get(0) as SystemUnitClass).name)
		
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.size)
		assertEquals("someBehaviourProperty", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).name)
		assertEquals("DreiGradDrehen", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
	}
	
	@Test
	def createRealCloneWithBehaviourNotMaintainable() {
		createBasicModel(false, 2)
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextConfirmationInput(false)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, CloneTags.REAL)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemClass", (second.get(0).get(0) as SystemUnitClass).name)
		
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.size)
		assertEquals("someBehaviourProperty", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).name)
		assertEquals("DreiGradDrehen", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
		
		val third = new LinkedList<EObject>
		third.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0))
		val four = correspondenceModel.getCorrespondingEObjects(third, PropertyTags.MAINTAINABLE)
		
		assertEquals(0, four.size)
	}
	
	@Test
	def createRealCloneWithBehaviourMaintainable() {
		createBasicModel(false, 2)
		CAEXRootElement.instanceHierarchy.get(0).internalElement.get(0).refBaseSystemUnitPath = "BspSystemLib/BspSystemClass"
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextConfirmationInput(true)
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, CloneTags.REAL)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemClass", (second.get(0).get(0) as SystemUnitClass).name)
		
		assertEquals(1, CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.size)
		assertEquals("someBehaviourProperty", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).name)
		assertEquals("DreiGradDrehen", CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0).value)
		
		val third = new LinkedList<EObject>
		third.add(CAEXRootElementVirtualModel.instanceHierarchy.get(0).internalElement.get(0).attribute.get(0))
		val four = correspondenceModel.getCorrespondingEObjects(third, PropertyTags.MAINTAINABLE)
		
		assertEquals(1, four.size)
		assertEquals(1, four.get(0).size)
		assertEquals("BspElem", (four.get(0).get(0) as InternalElement).name)
	}
}