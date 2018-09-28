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
		val caexFactory = new CAEXFactoryImpl
		val newSystemClassLib = caexFactory.createSystemUnitClassLib
		val newSystemClass = caexFactory.createSystemUnitClass
		val newHierarchy = caexFactory.createInstanceHierarchy
		val newInternalElement = caexFactory.createInternalElement
		
		newSystemClassLib.name = "BspLib"
		newSystemClass.name = "BspClass"
		newHierarchy.name = "BspHier"
		newInternalElement.name = "BspElem"
		
		newSystemClassLib.systemUnitClass.add(newSystemClass)
		rootElementVirtualCAEXModel.systemUnitClassLib.add(newSystemClassLib)
		
		newHierarchy.internalElement.add(newInternalElement)
		rootElementVirtualCAEXModel.instanceHierarchy.add(newHierarchy)
		
		newInternalElement.refBaseSystemUnitPath = "BspLib/BspClass"
		
		val first = new LinkedList<EObject>
		first.add(newInternalElement)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		assertTrue(second.get(0).get(0) == newSystemClass)
	}

}
