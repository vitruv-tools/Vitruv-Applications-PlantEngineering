package tools.vitruv.applications.automationml.utils.stringreferences.collada;

import org.eclipse.emf.common.util.EList;
import org.khronos.collada.COLLADAType;
import org.khronos.collada.GeometryType;
import org.khronos.collada.LibraryGeometriesType;
import org.khronos.collada.LibraryVisualScenesType;
import org.khronos.collada.VisualSceneType;

public class ColladaStringUtils {
	public static GeometryType getGeometryByID(String id, COLLADAType root) {
		if(id == null || id.isEmpty() || root == null) {
			return null;
		}
		
		EList<LibraryGeometriesType> libs = root.getLibraryGeometries();
		EList<GeometryType> currGeos;
		
		for(LibraryGeometriesType currLib : libs) {
			currGeos = currLib.getGeometry();
			for(GeometryType currGeo : currGeos) {
				if(currGeo.getId().equals(id)) {
					return currGeo;
				}
			}
		}
		
		return null;
	}
	
	public static VisualSceneType getVisualSceneByID(String id, COLLADAType root) {
		if(id == null || id.isEmpty() || root == null) {
			return null;
		}
		
		EList<LibraryVisualScenesType> libs = root.getLibraryVisualScenes();
		EList<VisualSceneType> currVisualScenes;
		
		for(LibraryVisualScenesType currLib : libs) {
			currVisualScenes = currLib.getVisualScene();
			for(VisualSceneType currVisualScene : currVisualScenes) {
				if(currVisualScene.getId().equals(id)) {
					return currVisualScene;
				}
			}
		}
		
		return null;
	}
}