package tools.vitruv.applications.automationml.tests.colladareactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertNotNull

class GeometryLibTests extends AbstractColladaReactionsTest {
	def createBasicModel() {
		val geoLib = colladaFactory.createLibraryGeometriesType
		val geo = colladaFactory.createGeometryType
		val visualSceneLib = colladaFactory.createLibraryVisualScenesType
		val visualScene = colladaFactory.createVisualSceneType
		val node = colladaFactory.createNodeType1
		val geoInstance = colladaFactory.createInstanceGeometryType
		val collada = colladaFactory.createCOLLADAType
		
		geoLib.name = "geoLib"
		geo.name = "geo"
		visualSceneLib.name = "visualLib"
		visualScene.name = "visual"
		node.name = "node"
		geoInstance.name = "geoInst"
		
		geo.id = "newGeo123"
		
		geoLib.geometry.add(geo)
		collada.libraryGeometries.add(geoLib)
		node.instanceGeometry.add(geoInstance)
		visualScene.node.add(node)
		visualSceneLib.visualScene.add(visualScene)
		collada.libraryVisualScenes.add(visualSceneLib)
		//colladaRootElement.COLLADA = collada

		geoInstance.url = "newGeo123"
		
		colladaRootElement.saveAndSynchronizeChanges
		//collada.saveAndSynchronizeChanges
	}
	
	@Test
	def testRemoveGeometry() {
		createBasicModel
		
		colladaRootElement.libraryGeometries.get(0).geometry.remove(0)
		
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, colladaRootElementVirtualModel.libraryGeometries.get(0).geometry.size)
		assertEquals("", colladaRootElementVirtualModel.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url)
	}
	
	@Test
	def testRemoveGeometryLib() {
		createBasicModel
		
		colladaRootElement.libraryGeometries.remove(0)
		
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, colladaRootElementVirtualModel.libraryGeometries.size)
		assertEquals("", colladaRootElementVirtualModel.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url)
	}
	
	@Test
	def testChangeGeometryID() {
		createBasicModel
		
		colladaRootElement.libraryGeometries.get(0).geometry.get(0).id = "NewId1234"
		
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals("NewId1234", colladaRootElementVirtualModel.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url)
	}
}
