package tools.vitruv.applications.automationml.aggregatorreactions.tests

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import org.plcopen.xml.tc60201.ProjectType
import org.khronos.collada.COLLADAType

class AggregatorStructureTests extends AbstractAggregatorReactionsTest {
	
	@Test
	public def testCreatePLCopenContainer() {
		System.out.println("Test11A")
		
		aggregatorRootElement.caexcontainer = aggregatorFactory.createCAEXContainer
		aggregatorRootElement.saveAndSynchronizeChanges
		
		val newContainer = aggregatorFactory.createPLCopenContainer
		aggregatorRootElement.plcopencontainer.add(newContainer)
		aggregatorRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(newContainer)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		if(second.get(0).size != 1) {
			assertTrue(false)
		}
		
		val project = second.get(0).get(0) as ProjectType
		assertNotNull(project.types)
		assertNotNull(project.types.pous)
		assertEquals(1, project.types.pous.pou.size)
	}
	
	//@Test
	public def testCreateColladaContainer() {
		System.out.println("Test11B")
		
		aggregatorRootElement.caexcontainer = aggregatorFactory.createCAEXContainer
		aggregatorRootElement.saveAndSynchronizeChanges
		
		val newContainer = aggregatorFactory.createColladaContainer
		aggregatorRootElement.colladacontainer.add(newContainer)
		aggregatorRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(newContainer)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		if(second.get(0).size != 1) {
			assertTrue(false)
		}
		
		val collada = second.get(0).get(0) as COLLADAType
	}
	
	//@Test
	public def testRemoveCAEXContainer() {
		System.out.println("Test11C")
		
		val caexContainer = aggregatorFactory.createCAEXContainer
		aggregatorRootElement.caexcontainer = caexContainer
		aggregatorRootElement.saveAndSynchronizeChanges
		
		
		
		val first = new LinkedList<EObject>
		first.add(caexContainer)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		
	}
	
	//@Test
	public def testRemovePLCopenContainer() {
		System.out.println("Test11D")
	}
	
	//@Test
	public def testRemoveColladaContainer() {
		System.out.println("Test11E")
	}
}