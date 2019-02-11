package tools.vitruv.applications.automationml.utils.stringreferences.caex;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.SystemUnitClass;
import caex.caex30.caex.SystemUnitClassLib;

/**
 * class with some methods to search for a SystemUnitClass in a caex file
 * @author A.Lang
 */
public class SystemUnitClassUtils {
	/**
	 * method to search for a SystemUnitClass in a caex files
	 */
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
	
	/**
	 * method to resolve the complete path of a SystemUnitClass, given by its path
	 */
	public static String getCompletePathOfSystemUnitClass(String path, CAEXFile root) {
		SystemUnitClass systemClass = getSystemClassByPath(path, root);
		return getCompletePathOfSystemUnitClass(systemClass);
	}
	
	/**
	 * method to resolve the complete path of a SystemUnitClass
	 */
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
