package tools.vitruv.applications.automationml.utils.stringreferences.caex;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.SystemUnitClass;
import caex.caex30.caex.SystemUnitClassLib;

public class SystemUnitClassUtils {
	// löst einen Pfad auf, und liefert die zugehörige Klasse (aus den Klassenbibliotheken) zurück
	public static SystemUnitClass getSystemClassByPath(String path, CAEXFile caexFile) {
		if(path == null || path.isEmpty() || caexFile == null) {
			return null;
		}
		
		String[] partsOfPath = path.split("/");
		EList<SystemUnitClassLib> classLibs = caexFile.getSystemUnitClassLib();
		SystemUnitClass lastClass = null;
		EList<SystemUnitClass> currClasses = null;
		EList<SystemUnitClass> nextClasses = null;
		
		for(int k = 0; k < classLibs.size(); k++) {
			if(classLibs.get(k).getName().equals(partsOfPath[0])) {
				currClasses = classLibs.get(k).getSystemUnitClass();
				break;
			}
		}
		if(currClasses == null) {
			return null;
		}
		
		for(int i = 1; i < partsOfPath.length; i++) {
			for(int j = 0; j < currClasses.size(); j++) {
				if(currClasses.get(j).getName().equals(partsOfPath[i])) {
					nextClasses = currClasses.get(j).getSystemUnitClass();
					lastClass = currClasses.get(j);
					break;
				}
			}
			if(nextClasses == null) {
				return null;
			} else {
				currClasses = nextClasses;
				nextClasses = null;
			}
		}
			
		return lastClass;
	}
	
	public static String getCompletePathOfSystemUnitClass(String path, CAEXFile root) {
		SystemUnitClass systemClass = getSystemClassByPath(path, root);
		return getCompletePathOfSystemUnitClass(systemClass);
	}
	
	public static String getCompletePathOfSystemUnitClass(SystemUnitClass systemClass) {
		if(systemClass == null) {
			return "";
		}
		
		SystemUnitClass currClass = systemClass;
		EObject helpingObject;
		String[] currStringParts = new String[100];		//Anzahl utopisch
		int currPart = 0;
		
			while(true) {
				currStringParts[currPart] = currClass.getName();
				currPart ++;
				helpingObject = currClass.eContainer();
				if(helpingObject instanceof SystemUnitClass) {
					currClass = (SystemUnitClass) helpingObject;
				} else {
					break;
				}
			}

		String finalPath = ((SystemUnitClassLib)helpingObject).getName();
		for(int i = currPart - 1; i >= 0; i--) {
			finalPath = finalPath + "/" + currStringParts[i];
		}
		
		return finalPath;
	}
}
