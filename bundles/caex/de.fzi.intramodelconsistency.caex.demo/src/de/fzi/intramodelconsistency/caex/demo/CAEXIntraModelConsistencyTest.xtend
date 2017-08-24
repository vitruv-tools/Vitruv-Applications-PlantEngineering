package de.fzi.intramodelconsistency.caex.demo

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

class CAEXIntraModelConsistencyTest extends AbstractCAEXIntraModelConsistencyTest {
	
	@Test 
	public def testTrue() {
		assertTrue(true)
	}
	
	@Test
	public def testChangingFileNameTwice() {
		rootElement.fileName="Change1"
		rootElement.saveAndSynchronizeChanges
		rootElement.fileName="Change2"
		rootElement.saveAndSynchronizeChanges
		
		assertEquals(rootElement.fileName,"Change2")
	}
	
	@Test
	public def testCreateCorrespondences() {
		var sysLib = factory.createSystemUnitClassLib
		var sysClass = factory.createSystemUnitClass
		var instHier = factory.createInstanceHierarchy
		var intElem = factory.createInternalElement
		
		sysLib.name="SUCL"
		sysClass.name = "SUC"
		instHier.name = "instanceHierarchy#1"
		intElem.name = "internalElem#1"
		
		sysLib.systemUnitClass.add(sysClass)
		instHier.internalElement.add(intElem)
		
		rootElement.systemUnitClassLib.add(sysLib)
		rootElement.instanceHierarchy.add(instHier)
		
		rootElement.saveAndSynchronizeChanges
		instHier.internalElement.get(0).refBaseSystemUnitPath = "SUCL/SUC"
		rootElement.saveAndSynchronizeChanges
		
		val correspondences = correspondenceModel.allCorrespondences
		var equ1 = correspondences.get(0).ATuids.get(0).equals(correspondenceModel.calculateTuidFromEObject(intElem))
		var equ2 = correspondences.get(0).BTuids.get(0).equals(correspondenceModel.calculateTuidFromEObject(sysClass))
		
		assertEquals(1,correspondences.length)
		assertTrue(equ1 && equ2)
	}
	
	@Test
	public def testExistingModel() {
		
		var targetElem = rootElement.instanceHierarchy.findFirst[it.name=="InstanceHierarchy_1"]
									.internalElement.findFirst[it.name=="InternalElement_1"]
		targetElem.refBaseSystemUnitPath = "SysUCL/SysUClass_3"
		rootElement.saveAndSynchronizeChanges
		
		rootElement.systemUnitClassLib.findFirst[name=="SysUCL"]
					.systemUnitClass.findFirst[name=="SysUClass_3"].name = "SysUClassNameChanged"
		rootElement.saveAndSynchronizeChanges			
		
		rootElement.instanceHierarchy.findFirst[it.name=="InstanceHierarchy_1"]
									.internalElement.findFirst[it.name=="InternalElement_1"].refBaseSystemUnitPath = "SysUClassNameChanged"
		
		assertEquals("SysUClassNameChanged", targetElem.refBaseSystemUnitPath)
	}
}