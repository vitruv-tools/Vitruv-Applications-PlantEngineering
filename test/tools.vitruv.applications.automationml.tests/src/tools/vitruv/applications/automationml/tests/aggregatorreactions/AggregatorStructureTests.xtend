package tools.vitruv.applications.automationml.tests.aggregatorreactions

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
	def testCreatePLCopenContainer() {		
		aggregatorRootElement.caexcontainer = aggregatorFactory.createCAEXContainer
		aggregatorRootElement.saveAndSynchronizeChanges
		
		val newContainer = aggregatorFactory.createPLCopenContainer
		aggregatorRootElement.plcopencontainer.add(newContainer)
		aggregatorRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(newContainer)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		if(second.size == 0) {
			assertTrue(false)
		} else if(second.get(0).size != 1) {
			assertTrue(false)
		} else if(!(second.get(0).get(0) instanceof ProjectType)) {
			assertTrue(false)
		}
		
		val project = second.get(0).get(0) as ProjectType
		assertNotNull(project.types)
		assertNotNull(project.types.pous)
		assertEquals(1, project.types.pous.pou.size)
	}
	
	//@Test
	def testCreateColladaContainer() {		
		aggregatorRootElement.caexcontainer = aggregatorFactory.createCAEXContainer
		aggregatorRootElement.saveAndSynchronizeChanges
		
		val newContainer = aggregatorFactory.createColladaContainer
		aggregatorRootElement.colladacontainer.add(newContainer)
		aggregatorRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(newContainer)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		if(second.size == 0) {
			assertTrue(false)
		} else if(second.get(0).size != 1) {
			assertTrue(false)
		} else if(!(second.get(0).get(0) instanceof COLLADAType)) {
			assertTrue(false)
		}
		
		val collada = second.get(0).get(0) as COLLADAType
		assertNotNull(collada.scene)
		assertNotNull(collada.scene.instanceVisualScene)
	}
	
	//@Test
	def testRemoveCAEXContainer() {
		val caexContainer = aggregatorFactory.createCAEXContainer
		aggregatorRootElement.caexcontainer = caexContainer
		aggregatorRootElement.saveAndSynchronizeChanges
		
		
		
		val first = new LinkedList<EObject>
		first.add(caexContainer)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		
	}
	
	//@Test
	def testRemovePLCopenContainer() {
		
	}
	
	//@Test
	def testRemoveColladaContainer() {
		
	}
}