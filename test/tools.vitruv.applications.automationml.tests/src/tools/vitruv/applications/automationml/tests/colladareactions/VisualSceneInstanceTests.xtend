package tools.vitruv.applications.automationml.tests.colladareactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import org.khronos.collada.VisualSceneType

class VisualSceneInstanceTests extends AbstractColladaReactionsTest {
	def createBasicModel(boolean extended) {
		val visualSceneLib = colladaFactory.createLibraryVisualScenesType
		visualSceneLib.name = "visualLib"
		val visualScene = colladaFactory.createVisualSceneType
		visualScene.id = "visualSceneID"
		
		val scene = colladaFactory.createSceneType
		val instanceVisualScene = colladaFactory.createInstanceWithExtra
		
		visualSceneLib.visualScene.add(visualScene)
		colladaRootElement.libraryVisualScenes.add(visualSceneLib)
		scene.instanceVisualScene = instanceVisualScene
		colladaRootElement.scene = scene
		
		if(extended) {
			val visualSceneTwo = colladaFactory.createVisualSceneType
			visualSceneTwo.id = "anotherVisualSceneID"
		}
		
		colladaRootElement.saveAndSynchronizeChanges
	}
	
	//@Test
	def testAddVisualSceneReference() {
		createBasicModel(false)
		
		colladaRootElement.scene.instanceVisualScene.url = "visualSceneID"
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(colladaRootElementVirtualModel.scene.instanceVisualScene)
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		if(two.size == 0) {
			assertTrue(false)
		} else if (two.get(0).size != 1) {
			assertTrue(false)
		} else {
			assertEquals(colladaRootElementVirtualModel.scene.instanceVisualScene.url, (two.get(0).get(0) as VisualSceneType).id)
		}
	}
	
	//@Test
	def testChangeVisualSceneReference() {
		createBasicModel(true)
		
		colladaRootElement.scene.instanceVisualScene.url = "visualSceneID"
		colladaRootElement.saveAndSynchronizeChanges
		
		colladaRootElement.scene.instanceVisualScene.url = "anotherVisualSceneID"
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(colladaRootElementVirtualModel.scene.instanceVisualScene)
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		if(two.size == 0) {
			assertTrue(false)
		} else if (two.get(0).size != 1) {
			assertTrue(false)
		} else {
			assertEquals(colladaRootElementVirtualModel.scene.instanceVisualScene.url, (two.get(0).get(0) as VisualSceneType).id)
		}
	}
	
	//@Test
	def testChangeVisualSceneReferenceWithRollback() {
		createBasicModel(false)
		
		colladaRootElement.scene.instanceVisualScene.url = "anotherVisualSceneID"
		userInteractor.addNextConfirmationInput(false)
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(null, colladaRootElementVirtualModel.scene.instanceVisualScene.url)
	}
	
	//@Test
	def testChangeVisualSceneReferenceWithAimCreation() {
		createBasicModel(false)
		
		val newString = "anotherVisualSceneID"
		colladaRootElement.scene.instanceVisualScene.url = newString
		userInteractor.addNextConfirmationInput(true)
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(2, colladaRootElementVirtualModel.libraryVisualScenes.size)
		assertEquals(newString, colladaRootElementVirtualModel.libraryVisualScenes.get(1).visualScene.get(0).id)
	}
}