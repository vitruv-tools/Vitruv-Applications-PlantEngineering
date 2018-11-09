package tools.vitruv.applications.automationml.tests.colladareactions

import org.junit.Test

import static org.junit.Assert.assertNotNull

class SceneStructureTests extends AbstractColladaReactionsTest {
	def createBasicModel() {
		val scene = colladaFactory.createSceneType
		val visualInstance = colladaFactory.createInstanceWithExtra
		
		scene.instanceVisualScene = visualInstance
		colladaRootElement.scene = scene
		
		colladaRootElement.saveAndSynchronizeChanges
	}
	
	//@Test
	def testRemoveScene() {
		colladaRootElement.scene = null
		
		colladaRootElement.saveAndSynchronizeChanges
		
		assertNotNull(colladaRootElementVirtualModel.scene)
	}
	
	//@Test
	def testRemoveVisualSceneInstance() {
		colladaRootElement.scene.instanceVisualScene = null
		
		colladaRootElement.saveAndSynchronizeChanges
		
		assertNotNull(colladaRootElementVirtualModel.scene.instanceVisualScene)
	}
}