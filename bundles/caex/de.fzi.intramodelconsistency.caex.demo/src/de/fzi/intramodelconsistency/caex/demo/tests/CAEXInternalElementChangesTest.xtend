package de.fzi.intramodelconsistency.caex.demo.tests

import org.junit.Test
import org.automationml.caex.caex.InternalElement
import org.automationml.caex.caex.SystemUnitClass
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue
import org.automationml.caex.caex.Attribute
import org.junit.Ignore

class CAEXInternalElementChangesTest extends AbstractCAEXIntraModelConsistencyTest {
	
	@Test
	public def testRemoveAttributeFromCloneInternalElement() {
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
		
		//TODO: As currently changes aren't propagated back to the original Model we cannot find&delete the attribute that
		//should have been created by the reactions
		
		//Remove Attribute from SystemUnitClass
		var attr2 = rootElement.findByPath("InstanceHierarchy_1/InternalElement_1/NewAttribute") as Attribute
		(rootElement.findByPath("InstanceHierarchy_1/InternalElement_1") as InternalElement).attribute.remove(attr2)
		rootElement.saveAndSynchronizeChanges
		
		var virtualAttr = rootElementVirtualModel.findByPath("SysUCL/SysUClass_1/NewAttribute")
		assertNotNull(virtualAttr)
		assertTrue(correspondenceModel.getCorrespondences(#[virtualAttr]).empty)
	}
}