package tools.vitruv.applications.automationml.utils.stringreferences.caex;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.RoleClass;
import caex.caex30.caex.RoleClassLib;

/**
 * class with some methods to search for a RoleClass in a caex file
 * @author A.Lang
 */
public class RoleClassUtils {
	/**
	 * method to search for a RoleClass in a caex files
	 */
	public static RoleClass getRoleClassByPath(String path, CAEXFile caexFile) {
		if(path == null || path.isEmpty() || caexFile == null) {
			return null;
		}
		
		String[] partsOfPath = path.split("/");
		EList<RoleClassLib> classLibs = caexFile.getRoleClassLib();
		RoleClass lastClass = null;
		EList<RoleClass> currClasses = null;
		EList<RoleClass> nextClasses = null;
		
		for(int k = 0; k < classLibs.size(); k++) {
			if(classLibs.get(k).getName().equals(partsOfPath[0])) {
				currClasses = classLibs.get(k).getRoleClass();
				break;
			}
		}
		if(currClasses == null) {
			return null;
		}
		
		for(int i = 1; i < partsOfPath.length; i++) {
			for(int j = 0; j < currClasses.size(); j++) {
				if(currClasses.get(j).getName().equals(partsOfPath[i])) {
					nextClasses = currClasses.get(j).getRoleClass();
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
	 * method to resolve the complete path of a RoleClass, given by its path
	 */
	public static String getCompletePathOfRoleClass(String path, CAEXFile root) {
		RoleClass systemClass = getRoleClassByPath(path, root);
		return getCompletePathOfRoleClass(systemClass);
	}
	
	/**
	 * method to resolve the complete path of a RoleClass
	 */
	public static String getCompletePathOfRoleClass(RoleClass roleClass) {
		if(roleClass == null) {
			return "";
		}
		
		RoleClass currClass = roleClass;
		EObject helpingObject;
		String[] currStringParts = new String[100];		//Anzahl utopisch
		int currPart = 0;
		
			while(true) {
				currStringParts[currPart] = currClass.getName();
				currPart ++;
				helpingObject = currClass.eContainer();
				if(helpingObject instanceof RoleClass) {
					currClass = (RoleClass) helpingObject;
				} else {
					break;
				}
			}

		String finalPath = ((RoleClassLib)helpingObject).getName();
		for(int i = currPart - 1; i >= 0; i--) {
			finalPath = finalPath + "/" + currStringParts[i];
		}
		
		return finalPath;
	}
}
