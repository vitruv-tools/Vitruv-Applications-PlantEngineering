package tools.vitruv.applications.automationml.utils.colladautils;

import org.eclipse.emf.common.util.EList;
import org.khronos.collada.COLLADAType;
import org.khronos.collada.GeometryType;
import org.khronos.collada.LibraryGeometriesType;
import org.khronos.collada.LibraryVisualScenesType;
import org.khronos.collada.VisualSceneType;

public class StringResolver {
	// löst einen Pfad auf, und liefert das zugehörige GeometryType (aus den Geometry-Bibliotheken) zurück
	/*public static GeometryType resolveGeometryPath(String path, COLLADAType root) {
		if(path == null || path.isEmpty() || root == null) {
			return null;
		}
		
		String[] partsOfPath = path.split("/");
		if(partsOfPath.length != 2) {
			return null;
		}
		
		EList<LibraryGeometriesType> geometrieLibs = root.getLibraryGeometries();
		EList<GeometryType> geometries = null;
		
		for(int k = 0; k < geometrieLibs.size(); k++) {
			if(geometrieLibs.get(k).getName().equals(partsOfPath[0])) {
				geometries = geometrieLibs.get(k).getGeometry();
				break;
			}
		}
		
		if(geometries == null) {
			return null;
		}
		
		for(int i = 0; i < geometries.size(); i++) {
			if(geometries.get(i).getName().equals(partsOfPath[1])) {
				return geometries.get(i);
			}
		}
		
		return null;
	}*/
	
	// löst einen Pfad auf, und liefert die zugehörige visuelle Szene (aus den Visuellen-Szene-Bibliotheken) zurück
	/*public static VisualSceneType resolveVisualScenePath(String path, COLLADAType root) {
		if(path == null || path.isEmpty() || root == null) {
			return null;
		}
		
		String[] partsOfPath = path.split("/");
		if(partsOfPath.length != 2) {
			return null;
		}
		
		EList<LibraryVisualScenesType> visualLibs = root.getLibraryVisualScenes();
		EList<VisualSceneType> visualScenes = null;
		
		for(int k = 0; k < visualLibs.size(); k++) {
			if(visualLibs.get(k).getName().equals(partsOfPath[0])) {
				visualScenes = visualLibs.get(k).getVisualScene();
				break;
			}
		}
		
		if(visualScenes == null) {
			return null;
		}
		
		for(int i = 0; i < visualScenes.size(); i++) {
			if(visualScenes.get(i).getName().equals(partsOfPath[1])) {
				return visualScenes.get(i);
			}
		}
		
		return null;
	}*/
	
	public static GeometryType resolveGeometryID(String id, COLLADAType root) {
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
	
	public static VisualSceneType resolveVisualSceneID(String id, COLLADAType root) {
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