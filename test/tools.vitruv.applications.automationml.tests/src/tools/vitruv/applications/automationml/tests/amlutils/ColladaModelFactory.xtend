package tools.vitruv.applications.automationml.tests.amlutils

import org.khronos.collada.LibraryGeometriesType
import org.khronos.collada.LibraryVisualScenesType
import org.khronos.collada.SceneType
import org.khronos.collada.ColladaFactory

class ColladaModelFactory {
	protected static def ColladaFactory colladaFactory() {
		return ColladaFactory.eINSTANCE
	}
	
	static def LibraryGeometriesType createGeometryLib(boolean withSecondGeometry) {
		val geoLib = colladaFactory.createLibraryGeometriesType
		geoLib.name = "BspGeoLib"
		val geo = colladaFactory.createGeometryType
		geo.name = "BspGeo"
		geo.id = "BspGeoID"
		geoLib.geometry.add(geo)
		
		if(withSecondGeometry) {
			val secondGeo = colladaFactory.createGeometryType
			secondGeo.name = "AnotherGeo"
			secondGeo.id = "AnotherGeoID"
			geoLib.geometry.add(secondGeo)
		}
		
		return geoLib
	}
	
	static def LibraryVisualScenesType createVisualSceneLib(boolean withSecondScene) {
		val visualLib = colladaFactory.createLibraryVisualScenesType
		val visualScene = colladaFactory.createVisualSceneType
		val node = colladaFactory.createNodeType1
		val geoInstance = colladaFactory.createInstanceGeometryType
		
		visualLib.name = "BspVisuLib"
		visualScene.name = "BspVisualScene"
		visualScene.id = "BspVisualSceneID"
		node.name = "BspNode"
		geoInstance.name = "BspGeoInstance"
		
		node.instanceGeometry.add(geoInstance)
		visualScene.node.add(node)
		visualLib.visualScene.add(visualScene)
		
		if(withSecondScene) {
			val secondVisualScene = colladaFactory.createVisualSceneType
			val secondNode = colladaFactory.createNodeType1
			val secondGeoInstance = colladaFactory.createInstanceGeometryType
			
			secondVisualScene.name = "AnotherVisualScene"
			secondVisualScene.id = "AnotherVisualSceneID"
			secondNode.name = "AnotherNode"
			secondGeoInstance.name = "AnotherGeoInstance"
			
			secondNode.instanceGeometry.add(secondGeoInstance)
			secondVisualScene.node.add(secondNode)
			visualLib.visualScene.add(secondVisualScene)
		}
		
		return visualLib
	}
	
	static def SceneType createScene() {
		val scene = colladaFactory.createSceneType
		val instance = colladaFactory.createInstanceWithExtra
		instance.name = "BspVisualInstance"
		scene.instanceVisualScene = instance
		return scene
	}
}