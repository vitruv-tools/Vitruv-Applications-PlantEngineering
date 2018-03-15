package de.fzi.intramodelconsistency.caex.demo.tests

import org.junit.Test
import org.junit.Rule

import static org.junit.Assert.fail
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull

import org.automationml.caex.caex.InternalElement
import org.automationml.caex.caex.SystemUnitClass
import org.automationml.caex.caex.SystemUnitClassLib
import org.automationml.caex.caex.ChangeMode
import java.util.NoSuchElementException
import org.junit.rules.ExpectedException


class CAEXSystemUnitClassChangesTest extends AbstractCAEXIntraModelConsistencyTest {
	
	@Rule
	public ExpectedException expected = ExpectedException.none
	
	@Test
	public def testCreateCorrespondences() {
		var sysLib = factory.createSystemUnitClassLib
		var sysClass = factory.createSystemUnitClass
		var instHier = factory.createInstanceHierarchy
		var intElem = factory.createInternalElement
		
		sysLib.name="SUCL"
		sysClass.name = "SUC"
		instHier.name = "instanceHierarchy1"
		intElem.name = "internalElem1"
		
		sysLib.systemUnitClass.add(sysClass)
		instHier.internalElement.add(intElem)
		
		rootElement.systemUnitClassLib.add(sysLib)
		rootElement.instanceHierarchy.add(instHier)
		
		rootElement.saveAndSynchronizeChanges
		instHier.internalElement.get(0).refBaseSystemUnitPath = "SUCL/SUC"
		rootElement.saveAndSynchronizeChanges
		
		val correspondences = correspondenceModel.allCorrespondences
		val equ1 = correspondences.get(0).ATuids.get(0).equals(correspondenceModel.calculateTuidFromEObject(intElem))
		val equ2 = correspondences.get(0).BTuids.get(0).equals(correspondenceModel.calculateTuidFromEObject(sysClass))
		
		assertEquals(1,correspondences.length)
		assertTrue(equ1 && equ2)
	}
	
	@Test
	public def testPrototypeSystemUnitClassNameChanged() {
		
		var targetElem = rootElement.findByPath("InstanceHierarchy_1/InternalElement_1") as InternalElement
		targetElem.refBaseSystemUnitPath = "SysUCL/SysUClass_1"
		rootElement.saveAndSynchronizeChanges
		var sysClass = rootElement.findByPath("SysUCL/SysUClass_1") as SystemUnitClass
		sysClass.name = "SysUClassNameChanged"
		rootElement.saveAndSynchronizeChanges			
		
		targetElem = rootElementVirtualModel.findByPath("InstanceHierarchy_1/InternalElement_1") as InternalElement
									
		assertEquals("SysUCL/SysUClassNameChanged", targetElem.refBaseSystemUnitPath)
	}
	
	@Test
	public def void testRemovePrototypeSystemUnitClass() {
		//Create correspondence
		var intElem = rootElement.findByPath("InstanceHierarchy_1/InternalElement_1") as InternalElement
		intElem.refBaseSystemUnitPath = "SysUCL/SysUClass_1"
		(rootElement.findByPath("SysUCL") as SystemUnitClassLib).changeMode = ChangeMode.DELETE
		rootElement.saveAndSynchronizeChanges
		
		testUserInteractor.addNextSelections(0)
		
		//Delete Prototype
		var sysUCL = rootElement.findByPath("SysUCL") as SystemUnitClassLib
		sysUCL.systemUnitClass.remove(sysUCL.findByPath("SysUClass_1"))
		rootElement.saveAndSynchronizeChanges
		
		//Assert that Clone has been deleted
		expected.expect(NoSuchElementException)
		rootElementVirtualModel.findByPath("InstanceHierarchy_1/InternalElement_1")
		//Else fail
		fail("InternalElement_1 should be deleted after Prototype removal!")		
	}
	
	@Test
	public def testOnePrototypeToNClonesCorrespondence() {
		//Add additional InternalElement
		var intElem = factory.createInternalElement
		intElem.name = "internalElement_2"
		rootElement.instanceHierarchy.findFirst[it.name=="InstanceHierarchy_1"].internalElement.add(intElem)
		
		//Create correspondences
		rootElement.instanceHierarchy.findFirst[it.name=="InstanceHierarchy_1"].internalElement.forEach[refBaseSystemUnitPath = "SysUCL/SysUClass_1"]
		rootElement.saveAndSynchronizeChanges
		
		//Change Name of SystemUnitClass
		rootElement.systemUnitClassLib.findFirst[true].systemUnitClass.findFirst[true].name="TestChange"
		rootElement.saveAndSynchronizeChanges
		
		//Check if Reactions were executed correctly
		rootElementVirtualModel.instanceHierarchy.findFirst[true].internalElement
									.forEach[assertEquals("SysUCL/TestChange",it.refBaseSystemUnitPath)]
	}
}