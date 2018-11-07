package tools.vitruv.applications.automationml.tests.colladareactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import org.khronos.collada.GeometryType

class VisualSceneLibraryPointerTests extends AbstractColladaReactionsTest {
	def createBasicModel(boolean extended) {
		val geometryLib = colladaFactory.createLibraryGeometriesType
		geometryLib.name = "geoLib"
		val geometry = colladaFactory.createGeometryType
		geometry.id = "geo1ID"
		
		val visualSceneLib = colladaFactory.createLibraryVisualScenesType
		visualSceneLib.name = "visualLib"
		val visualScene = colladaFactory.createVisualSceneType
		visualScene.id = "visualSceneID"
		val node = colladaFactory.createNodeType1
		node.name = "bspNode"
		val instanceGeometry = colladaFactory.createInstanceGeometryType
		instanceGeometry.name = "geoInstance"
		
		val collada = colladaFactory.createCOLLADAType
		val scene = colladaFactory.createSceneType
		val instanceVisualScene = colladaFactory.createInstanceWithExtra
		
		geometryLib.geometry.add(geometry)
		collada.libraryGeometries.add(geometryLib)
		node.instanceGeometry.add(instanceGeometry)
		visualScene.node.add(node)
		visualSceneLib.visualScene.add(visualScene)
		collada.libraryVisualScenes.add(visualSceneLib)
		scene.instanceVisualScene = instanceVisualScene
		collada.scene = scene
		colladaRootElement.COLLADA = collada
		
		instanceVisualScene.url = "visualSceneID"
		
		if(extended) {
			val geo2 = colladaFactory.createGeometryType
			geo2.id = "geo2ID"
			geometryLib.geometry.add(geo2)
		}
		
		colladaRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def testChangeVisualSceneID() {
		createBasicModel(false)
		
		val newString = "NewId5678"
		colladaRootElement.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).id = newString
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(newString, colladaRootElementVirtualModel.COLLADA.scene.instanceVisualScene.url)
	}
	
	@Test
	def testAddReferenceToGeometry() {
		createBasicModel(false)
		
		val newUrl = "geo1ID"
		colladaRootElement.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url = newUrl
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(colladaRootElementVirtualModel.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0))
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		if(two.size == 0) {
			assertTrue(false)
		} else if (two.get(0).size != 1) {
			assertTrue(false)
		} else {
			assertEquals(newUrl, (two.get(0).get(0) as GeometryType).id)
		}
	}
	
	@Test
	def testChangeReferenceToGeometry() {
		createBasicModel(true)
		
		val newUrlA = "geo1ID"
		colladaRootElement.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url = newUrlA
		colladaRootElement.saveAndSynchronizeChanges
		
		val newUrlB = "geo1ID"
		colladaRootElement.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url = newUrlB
		colladaRootElement.saveAndSynchronizeChanges
		
		val first = new LinkedList<EObject>
		first.add(colladaRootElementVirtualModel.COLLADA.scene.instanceVisualScene)
		val two = correspondenceModel.getCorrespondingEObjects(first)
		
		if(two.size == 0) {
			assertTrue(false)
		} else if (two.get(0).size != 1) {
			assertTrue(false)
		} else {
			assertEquals(newUrlB, (two.get(0).get(0) as GeometryType).id)
		}
	}
	
	@Test
	def testChangeReferenceToGeoemtryWithRollback() {
		createBasicModel(false)
		
		colladaRootElement.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url = "anotherUrl"
		userInteractor.addNextConfirmationInput(false)
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(null, colladaRootElementVirtualModel.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url)
	}
	
	@Test
	def testChangeReferenceToGeometryWithAimCreation() {
		createBasicModel(false)
		
		val newString = "anotherUrl"
		colladaRootElement.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url = newString
		userInteractor.addNextConfirmationInput(true)
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(2, colladaRootElementVirtualModel.COLLADA.libraryGeometries.size)
		assertEquals(newString, colladaRootElementVirtualModel.COLLADA.libraryGeometries.get(1).geometry.get(0).id)
	}
}