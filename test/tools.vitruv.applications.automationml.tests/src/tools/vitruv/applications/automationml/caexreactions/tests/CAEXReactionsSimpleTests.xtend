package tools.vitruv.applications.automationml.caexreactions.tests

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import caex.caex30.caex.impl.CAEXFactoryImpl
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject

class CAEXReactionsSimpleTests extends AbstractCAEXReactionsTest {
	
	@Test
	public def testAddClassToInternalElement() {
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
		
		if(second.size == 0) {
			assertTrue(false)
		} else if (second.get(0).size == 0) {
			assertTrue(false)
		} else {
			assertTrue(second.get(0).get(0) == newSystemClass)
		}
	}

}
