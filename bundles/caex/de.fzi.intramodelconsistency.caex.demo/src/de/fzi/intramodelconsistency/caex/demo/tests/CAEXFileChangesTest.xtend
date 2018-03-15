package de.fzi.intramodelconsistency.caex.demo.tests

import org.automationml.caex.caex.InternalElement
import org.automationml.caex.caex.SystemUnitClass
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

class CAEXFileChangesTest extends AbstractCAEXIntraModelConsistencyTest {
	

	
	@Test
	public def testChangingFileNameTwice() {
		rootElement.fileName="1st Change"
		rootElement.saveAndSynchronizeChanges
		rootElement.fileName="2nd Change"
		rootElement.saveAndSynchronizeChanges
		
		assertEquals("2nd Change",rootElementVirtualModel.fileName)
	}
	
	@Test
	public def void testCreateAttributeInSystemUnitClass() {
		//Create correspondence
		var intElem = rootElement.findByPath("InstanceHierarchy_1/InternalElement_1") as InternalElement
		intElem.refBaseSystemUnitPath = "SysUCL/SysUClass_1"
		rootElement.saveAndSynchronizeChanges
		
		//Create & insert 1st attribute
		var attr = factory.createAttribute		
		var sysCl = rootElement.findByPath("SysUCL/SysUClass_1") as SystemUnitClass
		sysCl.attribute.add(attr)
		rootElement.saveAndSynchronizeChanges
		attr.name="NewAttribute"
		rootElement.saveAndSynchronizeChanges
		
		//Create & insert 2nd attribute
		attr = factory.createAttribute		
		sysCl = rootElement.findByPath("SysUCL/SysUClass_1") as SystemUnitClass
		sysCl.attribute.add(attr)
		rootElement.saveAndSynchronizeChanges
		attr.name="NewAttribute2"
		rootElement.saveAndSynchronizeChanges
		
		//Assert that attributes have been copied to InternalElement
		assertNotNull(rootElementVirtualModel.findByPath("InstanceHierarchy_1/InternalElement_1/NewAttribute"))
		assertNotNull(rootElementVirtualModel.findByPath("InstanceHierarchy_1/InternalElement_1/NewAttribute2"))
	}
}
