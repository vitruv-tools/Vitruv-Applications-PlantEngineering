package de.fzi.intramodelconsistency.caex.demo

import java.util.NoSuchElementException
import org.automationml.caex.caex.ChangeMode
import org.automationml.caex.caex.InternalElement
import org.automationml.caex.caex.SystemUnitClassLib
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.fail

class CAEXIntraModelConsistencyTest extends AbstractCAEXIntraModelConsistencyTest {
	
	@Test 
	public def testTrue() {
		assertTrue(true)
	}
	
	@Test
	public def testChangingFileNameTwice() {
		rootElement.fileName="1st Change"
		rootElement.saveAndSynchronizeChanges
		rootElement.fileName="2nd Change"
		rootElement.saveAndSynchronizeChanges
		
		assertEquals("2nd Change",rootElementVirtualModel.fileName)
	}
	
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
	public def testExistingModel() {
		
		var targetElem = rootElement.instanceHierarchy.findFirst[it.name=="InstanceHierarchy_1"]
									.internalElement.findFirst[it.name=="InternalElement_1"]
		targetElem.refBaseSystemUnitPath = "SysUCL/SysUClass_1"
		rootElement.saveAndSynchronizeChanges
		
		rootElement.systemUnitClassLib.findFirst[name=="SysUCL"]
					.systemUnitClass.findFirst[name=="SysUClass_1"].name = "SysUClassNameChanged"
		rootElement.saveAndSynchronizeChanges			
		
		targetElem = rootElementVirtualModel.instanceHierarchy.findFirst[it.name=="InstanceHierarchy_1"]
									.internalElement.findFirst[it.name=="InternalElement_1"]
									
		assertEquals("SysUCL/SysUClassNameChanged", targetElem.refBaseSystemUnitPath)
	}
	
		@Rule
	public ExpectedException expected = ExpectedException.none
	
	@Test
	public def void testRemovePrototype() {
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
	public def testOneToNCorrespondence() {
		//Add additional InternalElement
		var intElem = factory.createInternalElement
		intElem.name = "internalElement_2"
		rootElement.instanceHierarchy.findFirst[it.name=="InstanceHierarchy_1"].internalElement.add(intElem)
		
		//Create correspondences
		rootElement.instanceHierarchy.findFirst[it.name=="InstanceHierarchy_1"].internalElement.forEach[it.refBaseSystemUnitPath = "SysUCL/SysUClass_1"]
		rootElement.saveAndSynchronizeChanges
		
		//Change Name of SystemUnitClass
		rootElement.systemUnitClassLib.findFirst[true].systemUnitClass.findFirst[true].name="TestChange"
		rootElement.saveAndSynchronizeChanges
		
		//Check if Reactions were executed correctly
		rootElementVirtualModel.instanceHierarchy.findFirst[true].internalElement
									.forEach[assertEquals("SysUCL/TestChange",it.refBaseSystemUnitPath)]

	}
}