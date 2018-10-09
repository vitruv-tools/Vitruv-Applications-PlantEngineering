package tools.vitruv.applications.automationml.caexreactions.tests

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import caex.caex30.caex.SystemUnitClass

class CAEXReactionsSimpleTests extends AbstractCAEXReactionsTest {
	
	@Test
	public def testAddClassToInternalElement() {
		System.out.println("TestA")
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
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		newInternalElement.refBaseSystemUnitPath = "BspLib/BspClass"
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(newInternalElement)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		val third = correspondenceModel.getCorrespondences(first)
		
		if (second.get(0).size != 1) {
			assertTrue(false)
		} else {
			//assertEquals((second.get(0).get(0) == newSystemClass)
			assertEquals((second.get(0).get(0) as SystemUnitClass).name, newSystemClass.name)
		}
	}
	
	@Test
	public def testRemoveClassFromInternalElement() {
		System.out.println("TestB")
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
		
		newInternalElement.refBaseSystemUnitPath = "BspLib/BspClass"
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		//newInternalElement.refBaseSystemUnitPath = null
		newInternalElement.refBaseSystemUnitPath = ""
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(newInternalElement)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		val third = correspondenceModel.getCorrespondences(first)
		
		assertTrue(second.get(0).size == 0)
	}
	
	@Test
	public def testChangeClassInInternalElement() {
		System.out.println("TestC")
		val caexFactory = caexFactory
		val newSystemClassLib = caexFactory.createSystemUnitClassLib
		val newSystemClassA = caexFactory.createSystemUnitClass
		val newSystemClassB = caexFactory.createSystemUnitClass
		val newHierarchy = caexFactory.createInstanceHierarchy
		val newInternalElement = caexFactory.createInternalElement
		
		newSystemClassLib.name = "BspLib"
		newSystemClassA.name = "BspClass"
		newSystemClassB.name = "AnotherBspClass"
		newHierarchy.name = "BspHier"
		newInternalElement.name = "BspElem"
		
		newSystemClassLib.systemUnitClass.add(newSystemClassA)
		newSystemClassLib.systemUnitClass.add(newSystemClassB)
		CAEXRootElement.systemUnitClassLib.add(newSystemClassLib)
		
		newHierarchy.internalElement.add(newInternalElement)
		CAEXRootElement.instanceHierarchy.add(newHierarchy)
		
		newInternalElement.refBaseSystemUnitPath = "BspLib/BspClass"
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		newInternalElement.refBaseSystemUnitPath = "BspLib/AnotherBspClass"
		
		CAEXRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(newInternalElement)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		val third = correspondenceModel.getCorrespondences(first)
		
		if (second.get(0).size != 1) {
			assertTrue(false)
		} else {
			//assertEquals((second.get(0).get(0) == newSystemClass)
			assertEquals((second.get(0).get(0) as SystemUnitClass).name, newSystemClassB.name)
		}
	}
}
