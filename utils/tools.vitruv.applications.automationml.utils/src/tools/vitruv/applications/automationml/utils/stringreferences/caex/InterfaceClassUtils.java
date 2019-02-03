package tools.vitruv.applications.automationml.utils.stringreferences.caex;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.InterfaceClass;
import caex.caex30.caex.InterfaceClassLib;

/**
 * class with some methods to search for an InterfaceClass in a caex file
 * @author A.Lang
 */
public class InterfaceClassUtils {
	/**
	 * method to search for an InterfaceClass in a caex files
	 */
	public static InterfaceClass getInterfaceClassByPath(String path, CAEXFile caexFile) {
		if(path == null || path.isEmpty() || caexFile == null) {
			return null;
		}
		
		String[] partsOfPath = path.split("/");
		EList<InterfaceClassLib> classLibs = caexFile.getInterfaceClassLib();
		InterfaceClass lastClass = null;
		EList<InterfaceClass> currClasses = null;
		EList<InterfaceClass> nextClasses = null;
		
		for(int k = 0; k < classLibs.size(); k++) {
			if(classLibs.get(k).getName().equals(partsOfPath[0])) {
				currClasses = classLibs.get(k).getInterfaceClass();
				break;
			}
		}
		if(currClasses == null) {
			return null;
		}
		
		for(int i = 1; i < partsOfPath.length; i++) {
			for(int j = 0; j < currClasses.size(); j++) {
				if(currClasses.get(j).getName().equals(partsOfPath[i])) {
					nextClasses = currClasses.get(j).getInterfaceClass();
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
	 * method to resolve the complete path of an InterfaceClass, given by its path
	 */
	public static String getCompletePathOfInterfaceClass(String path, CAEXFile root) {
		InterfaceClass interfaceClass = getInterfaceClassByPath(path, root);
		return getCompletePathOfInterfaceClass(interfaceClass);
	}
	
	/**
	 * method to resolve the complete path of an InterfaceClass
	 */
	public static String getCompletePathOfInterfaceClass(InterfaceClass interfaceClass) {
		if(interfaceClass == null) {
			return "";
		}
		
		InterfaceClass currClass = interfaceClass;
		EObject helpingObject;
		String[] currStringParts = new String[100];		//Anzahl utopisch
		int currPart = 0;
		
			while(true) {
				currStringParts[currPart] = currClass.getName();
				currPart ++;
				helpingObject = currClass.eContainer();
				if(helpingObject instanceof InterfaceClass) {
					currClass = (InterfaceClass) helpingObject;
				} else {
					break;
				}
			}

		String finalPath = ((InterfaceClassLib)helpingObject).getName();
		for(int i = currPart - 1; i >= 0; i--) {
			finalPath = finalPath + "/" + currStringParts[i];
		}
		
		return finalPath;
	}
}
