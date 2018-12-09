package tools.vitruv.applications.automationml.tests.attributereactions

import org.junit.Test
import static org.junit.Assert.assertEquals
import tools.vitruv.applications.automationml.tests.amlutils.CAEXAttributeModelFactory
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import tools.vitruv.applications.automationml.utils.propertybasedtags.PrototypTags
import caex.caex30.caex.SystemUnitClassLib

class CreatePrototypeTest extends AbstractCAEXAttributeReactionsTest {
	@Test
	def addAlwaysPrototypeToClass() {
		val systemUnitClassLib = CAEXAttributeModelFactory.createSystemUnitClassLib(true, null)
		CAEXRootElement.systemUnitClassLib.add(systemUnitClassLib)
		userInteractor.addNextSingleSelection(0)
		userInteractor.addNextSingleSelection(0)			
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).systemUnitClass.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, PrototypTags.ALWAYS)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemLib", (second.get(0).get(0) as SystemUnitClassLib).name)
	}
	
	@Test
	def addNeverPrototypeToClass() {
		val systemUnitClassLib = CAEXAttributeModelFactory.createSystemUnitClassLib(true, null)
		CAEXRootElement.systemUnitClassLib.add(systemUnitClassLib)
		userInteractor.addNextSingleSelection(1)	
		userInteractor.addNextSingleSelection(1)		
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).systemUnitClass.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, PrototypTags.NEVER)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemLib", (second.get(0).get(0) as SystemUnitClassLib).name)
	}
	
	@Test
	def addOtherPrototypeToClass() {
		val systemUnitClassLib = CAEXAttributeModelFactory.createSystemUnitClassLib(true, null)
		CAEXRootElement.systemUnitClassLib.add(systemUnitClassLib)
		userInteractor.addNextSingleSelection(2)	
		userInteractor.addNextSingleSelection(2)		
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).systemUnitClass.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, PrototypTags.ALWAYS)
		
		val third = new LinkedList<EObject>
		third.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0).systemUnitClass.get(0))
		val four = correspondenceModel.getCorrespondingEObjects(third, PrototypTags.NEVER)
		
		assertEquals(0, second.size)
		assertEquals(0, four.size)
	}
	
	@Test
	def addAlwaysPrototypeToLib() {
		val systemUnitClassLib = CAEXAttributeModelFactory.createSystemUnitClassLib(false, null)
		CAEXRootElement.systemUnitClassLib.add(systemUnitClassLib)
		userInteractor.addNextSingleSelection(0)		
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, PrototypTags.ALWAYS)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemLib", (second.get(0).get(0) as SystemUnitClassLib).name)
	}
	
	@Test
	def addNeverPrototypeToLib() {
		val systemUnitClassLib = CAEXAttributeModelFactory.createSystemUnitClassLib(false, null)
		CAEXRootElement.systemUnitClassLib.add(systemUnitClassLib)
		userInteractor.addNextSingleSelection(1)		
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, PrototypTags.NEVER)
		
		assertEquals(1, second.size)
		assertEquals(1, second.get(0).size)
		assertEquals("BspSystemLib", (second.get(0).get(0) as SystemUnitClassLib).name)
	}
	
	@Test
	def addOtherPrototypeToLib() {
		val systemUnitClassLib = CAEXAttributeModelFactory.createSystemUnitClassLib(false, null)
		CAEXRootElement.systemUnitClassLib.add(systemUnitClassLib)
		userInteractor.addNextSingleSelection(2)		
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0))
		val second = correspondenceModel.getCorrespondingEObjects(first, PrototypTags.ALWAYS)
		
		val third = new LinkedList<EObject>
		third.add(CAEXRootElementVirtualModel.systemUnitClassLib.get(0).systemUnitClass.get(0))
		val four = correspondenceModel.getCorrespondingEObjects(third, PrototypTags.ALWAYS)
		
		assertEquals(0, second.size)
		assertEquals(0, four.size)
		
	}	
}