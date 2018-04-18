package tools.vitruv.applications.caexintra.tests


import org.junit.Test
import org.junit.Rule

import static org.junit.Assert.fail
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull

import caex.caex30.caex.InternalElement
import caex.caex30.caex.SystemUnitClass
import caex.caex30.caex.SystemUnitClassLib
import caex.caex30.caex.ChangeMode
import java.util.NoSuchElementException
import org.junit.rules.ExpectedException
import org.junit.Ignore

class CAEXSystemUnitClassChangesTest extends tools.vitruv.applications.caexintra.tests.AbstractCAEXIntraModelConsistencyTest {
	
	@Rule
	public ExpectedException expected = ExpectedException.none
	
	@Test
	public def testCreateCorrespondences() {
		var sysLib = factory.createSystemUnitClassLib
		var sysClass = factory.createSystemUnitClass
		var instHier = factory.createInstanceHierarchy
		var intElem = factory.createInternalElement


		rootElement.systemUnitClassLib.add(sysLib)
		rootElement.instanceHierarchy.add(instHier)		
		rootElement.saveAndSynchronizeChanges
		
		sysLib.name="SUCL"
		sysClass.name = "SUC"		
		rootElement.saveAndSynchronizeChanges
		
		sysLib.systemUnitClass.add(sysClass)
		instHier.internalElement.add(intElem)		
		rootElement.saveAndSynchronizeChanges
		
		instHier.name = "instanceHierarchy1"
		intElem.name = "internalElem1"
		
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
	
	@Ignore
	@Test
	public def testSystemUnitClassLibNameChanged() {
		//TODO: This Test should run without complications but fails for unknown reasons while testing.
		//Different behavior in the monitor.
		
		var targetElem = rootElement.findByPath("InstanceHierarchy_1/InternalElement_1") as InternalElement
		targetElem.refBaseSystemUnitPath = "SysUCL/SysUClass_1"
		rootElement.saveAndSynchronizeChanges
		var sysClassLib = rootElement.findByPath("SysUCL") as SystemUnitClassLib
		sysClassLib.name = "SysUClassLibNameChange"
		rootElement.saveAndSynchronizeChanges			
		
		targetElem = rootElementVirtualModel.findByPath("InstanceHierarchy_1/InternalElement_1") as InternalElement
									
		assertEquals("SysUClassLibNameChange/SysUClass_1", targetElem.refBaseSystemUnitPath)
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
	public def testRemovePrototypeSystemUnitClassKeepClone() {
		//Create correspondence
		var intElem = rootElement.findByPath("InstanceHierarchy_1/InternalElement_1") as InternalElement
		intElem.refBaseSystemUnitPath = "SysUCL/SysUClass_1"
		(rootElement.findByPath("SysUCL") as SystemUnitClassLib).changeMode = ChangeMode.DELETE
		rootElement.saveAndSynchronizeChanges
		
		testUserInteractor.addNextSelections(1)
		
		//Delete Prototype
		var sysUCL = rootElement.findByPath("SysUCL") as SystemUnitClassLib
		sysUCL.systemUnitClass.remove(sysUCL.findByPath("SysUClass_1"))
		rootElement.saveAndSynchronizeChanges
		
		//Assert that Clone has NOT been deleted
		assertNotNull(rootElementVirtualModel.findByPath("InstanceHierarchy_1/InternalElement_1"));
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
	
	@Test
	public def testRemoveAttributeFromPrototypeSystemUnitClass() {
		//Create correspondence
		var intElem = rootElement.findByPath("InstanceHierarchy_1/InternalElement_1") as InternalElement
		intElem.refBaseSystemUnitPath = "SysUCL/SysUClass_1"
		rootElement.saveAndSynchronizeChanges
		
		//Create & insert attribute
		var attr = factory.createAttribute		
		var sysCl = rootElement.findByPath("SysUCL/SysUClass_1") as SystemUnitClass
		sysCl.attribute.add(attr)
		rootElement.saveAndSynchronizeChanges
		attr.name="NewAttribute"
		rootElement.saveAndSynchronizeChanges
		
		//Remove Attribute from SystemUnitClass
		sysCl.attribute.remove(attr)
		rootElement.saveAndSynchronizeChanges
		
		expected.expect(NoSuchElementException)
		rootElementVirtualModel.findByPath("InstanceHierarchy_1/InternalElement_1/NewAttribute")
		fail("InstanceHierarchy_1/InternalElement_1/NewAttribute should be deleted after removal of the prototype attribute!")
	}
}
