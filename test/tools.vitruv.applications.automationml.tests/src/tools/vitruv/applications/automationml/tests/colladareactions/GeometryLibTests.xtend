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
		colladaRootElement.COLLADA = collada

		geoInstance.url = "newGeo123"
		
		colladaRootElement.saveAndSynchronizeChanges
	}
	
	@Test
	def testRemoveGeometry() {
		System.out.println("42A")
		
		createBasicModel
		
		colladaRootElement.COLLADA.libraryGeometries.get(0).geometry.remove(0)
		
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, colladaRootElementVirtualModel.COLLADA.libraryGeometries.get(0).geometry.size)
		assertEquals("", colladaRootElementVirtualModel.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url)
	}
	
	@Test
	def testRemoveGeometryLib() {
		System.out.println("42B")
		
		createBasicModel
		
		colladaRootElement.COLLADA.libraryGeometries.remove(0)
		
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, colladaRootElementVirtualModel.COLLADA.libraryGeometries.size)
		assertEquals("", colladaRootElementVirtualModel.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url)
	}
	
	@Test
	def testChangeGeometryID() {
		System.out.println("42C")
		
		createBasicModel
		
		colladaRootElement.COLLADA.libraryGeometries.get(0).geometry.get(0).id = "NewId1234"
		
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals("NewId1234", colladaRootElementVirtualModel.COLLADA.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url)
	}
}
