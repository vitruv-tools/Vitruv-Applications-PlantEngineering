package tools.vitruv.applications.automationml.tests.colladareactions

import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import tools.vitruv.applications.automationml.tests.amlutils.ColladaModelFactory

class GeometryLibTests extends AbstractColladaReactionsTest {
	def createBasicModel() {
		val geoLib = ColladaModelFactory.createGeometryLib(false)
		val visualSceneLib = ColladaModelFactory.createVisualSceneLib(false)
		
		colladaRootElement.libraryGeometries.add(geoLib)
		colladaRootElement.libraryVisualScenes.add(visualSceneLib)

		visualSceneLib.visualScene.get(0).node.get(0).instanceGeometry.get(0).url = "BspGeoID"
		
		colladaRootElement.saveAndSynchronizeChanges
	}
	
	//@Test
	def testRemoveGeometry() {
		createBasicModel
		
		colladaRootElement.libraryGeometries.get(0).geometry.remove(0)
		
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, colladaRootElementVirtualModel.libraryGeometries.get(0).geometry.size)
		assertEquals("", colladaRootElementVirtualModel.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url)
	}
	
	//@Test
	def testRemoveGeometryLib() {
		createBasicModel
		
		colladaRootElement.libraryGeometries.remove(0)
		
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals(0, colladaRootElementVirtualModel.libraryGeometries.size)
		assertEquals("", colladaRootElementVirtualModel.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url)
	}
	
	//@Test
	def testChangeGeometryID() {
		createBasicModel
		
		colladaRootElement.libraryGeometries.get(0).geometry.get(0).id = "NewId1234"
		
		colladaRootElement.saveAndSynchronizeChanges
		
		assertEquals("NewId1234", colladaRootElementVirtualModel.libraryVisualScenes.get(0).visualScene.get(0).node.get(0).instanceGeometry.get(0).url)
	}
}
