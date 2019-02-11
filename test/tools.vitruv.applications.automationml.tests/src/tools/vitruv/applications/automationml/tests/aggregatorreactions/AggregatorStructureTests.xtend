package tools.vitruv.applications.automationml.tests.aggregatorreactions

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import org.plcopen.xml.tc60201.ProjectType
import org.khronos.collada.COLLADAType
import edu.kit.sdq.aml_aggregator.CAEXContainer
import tools.vitruv.applications.automationml.tests.amlutils.AggregatorModelFactory

class AggregatorStructureTests extends AbstractAggregatorReactionsTest {
	
	//@Test
	def testCreatePLCopenContainer() {		
		aggregatorRootElement.caexcontainer = AggregatorModelFactory.createCAEXContainer
		aggregatorRootElement.saveAndSynchronizeChanges
		
		val newContainer = AggregatorModelFactory.createPLCopenContainer
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
		aggregatorRootElement.caexcontainer = AggregatorModelFactory.createCAEXContainer
		aggregatorRootElement.saveAndSynchronizeChanges
		
		val newContainer = AggregatorModelFactory.createColladaContainer
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
		val caexContainer = AggregatorModelFactory.createCAEXContainer
		aggregatorRootElement.caexcontainer = caexContainer
		aggregatorRootElement.saveAndSynchronizeChanges
		
		val newCaexContainer = AggregatorModelFactory.createCAEXContainer
		aggregatorRootElement.caexcontainer = newCaexContainer
		aggregatorRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(caexContainer)
		val second = correspondenceModel.getCorrespondingEObjects(first)
		
		if(second.size == 0) {
			assertTrue(false)
		} else if (second.get(0).size != 1) {
			assertTrue(false)
		} else {
			assertEquals(caexContainer, second.get(0).get(0) as CAEXContainer)
		}
	}
	
	//@Test
	def testRemovePLCopenContainer() {
		val caexContainer = AggregatorModelFactory.createCAEXContainer
		aggregatorRootElement.caexcontainer = caexContainer
		
		val plcContainer = AggregatorModelFactory.createPLCopenContainer
		aggregatorRootElement.plcopencontainer.add(plcContainer)
		
		//TODO CAEX-File aufbauen
		
		//TODO der Rest
	}
	
	//@Test
	def testRemoveColladaContainer() {
		val caexContainer = AggregatorModelFactory.createCAEXContainer
		aggregatorRootElement.caexcontainer = caexContainer
		
		val colladaContainer = AggregatorModelFactory.createColladaContainer
		aggregatorRootElement.colladacontainer.add(colladaContainer)
		
		//TODO CAEX-File aufbauen
		
		//TODO der Rest
	}
}