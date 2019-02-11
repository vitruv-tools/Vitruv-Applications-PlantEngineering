package tools.vitruv.applications.automationml.tests.colladareactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import org.khronos.collada.VisualSceneType
import tools.vitruv.applications.automationml.tests.amlutils.ColladaModelFactory

class VisualSceneInstanceTests extends AbstractColladaReactionsTest {
	def createBasicModel(boolean extended) {
		val visualSceneLib = ColladaModelFactory.createVisualSceneLib(extended)
		val scene = ColladaModelFactory.createScene

		colladaRootElement.libraryVisualScenes.add(visualSceneLib)
		colladaRootElement.scene = scene
		
		colladaRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def testAddVisualSceneReference() {
		createBasicModel(false)
		
		colladaRootElement.scene.instanceVisualScene.url = "BspVisualSceneID"
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(colladaRootElementVirtualModel.scene.instanceVisualScene)
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, two.size)
		assertEquals(1, two.get(0).size)
		assertEquals(colladaRootElementVirtualModel.scene.instanceVisualScene.url, (two.get(0).get(0) as VisualSceneType).id)
	}
	
	@Test
	def testChangeVisualSceneReference() {
		createBasicModel(true)
		
		colladaRootElement.scene.instanceVisualScene.url = "BspVisualSceneID"
		colladaRootElement.saveAndSynchronizeChanges
		
		colladaRootElement.scene.instanceVisualScene.url = "AnotherVisualSceneID"
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(colladaRootElementVirtualModel.scene.instanceVisualScene)
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		assertEquals(1, two.size)
		assertEquals(1, two.get(0).size)
		assertEquals(colladaRootElementVirtualModel.scene.instanceVisualScene.url, (two.get(0).get(0) as VisualSceneType).id)
	}
	
	@Test
	def testChangeVisualSceneReferenceWithRollback() {
		createBasicModel(false)
		
		colladaRootElement.scene.instanceVisualScene.url = "NotExistingVisualSceneID"
		userInteractor.addNextConfirmationInput(false)
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(null, colladaRootElementVirtualModel.scene.instanceVisualScene.url)
	}
	
	@Test
	def testChangeVisualSceneReferenceWithAimCreation() {
		createBasicModel(false)
		
		val newString = "NotExistingVisualSceneID"
		colladaRootElement.scene.instanceVisualScene.url = newString
		userInteractor.addNextConfirmationInput(true)
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(2, colladaRootElementVirtualModel.libraryVisualScenes.size)
		assertEquals(newString, colladaRootElementVirtualModel.libraryVisualScenes.get(1).visualScene.get(0).id)
	}
}