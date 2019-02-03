package tools.vitruv.applications.automationml.utils.stringreferences.collada;

import org.eclipse.emf.common.util.EList;
import org.khronos.collada.COLLADAType;
import org.khronos.collada.GeometryType;
import org.khronos.collada.LibraryGeometriesType;
import org.khronos.collada.LibraryVisualScenesType;
import org.khronos.collada.VisualSceneType;

/**
 * class with some methods to deal with implicite references in collada files
 * @author A.Lang
 */
public class ColladaStringUtils {
	/**
	 * method to search for a geometry definition in a collada file
	 */
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
	
	/**
	 * method to search for a VisualScene definition in a collada file
	 * @param id
	 * @param root
	 * @return
	 */
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