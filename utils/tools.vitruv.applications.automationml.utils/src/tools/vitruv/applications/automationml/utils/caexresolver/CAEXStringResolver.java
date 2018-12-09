package tools.vitruv.applications.automationml.utils.caexresolver;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.ExternalInterface;
import caex.caex30.caex.InterfaceClass;
import caex.caex30.caex.InterfaceClassLib;
import caex.caex30.caex.RoleClass;
import caex.caex30.caex.RoleClassLib;

public class CAEXStringResolver {
	public static String getCompletePath(InterfaceClass interfaceClass) {
		if(interfaceClass == null || interfaceClass instanceof ExternalInterface) {
			return null;
		}
		
		InterfaceClass currInterface = interfaceClass;
		EObject helpingObject;
		String[] currStringParts = new String[100];		//Anzahl utopisch
		int currPart = 0;
		
		while(true) {
			currStringParts[currPart] = currInterface.getName();
			currPart ++;
			helpingObject = currInterface.eContainer();
			if(helpingObject instanceof InterfaceClass) {
				currInterface = (InterfaceClass) helpingObject;
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
	
	// löst einen Pfad auf, und liefert das zugehörige Interface (aus den Interfacebibliotheken) zurück
	public static InterfaceClass resolveInterfacePath(String path, CAEXFile caexFile) {
		if(path == null || path.isEmpty() || caexFile == null) {
			return null;
		}
		
		String[] partsOfPath = path.split("/");
		EList<InterfaceClassLib> interfaceLibs = caexFile.getInterfaceClassLib();
		EList<InterfaceClass> currInterfaces = null;
		EList<InterfaceClass> nextInterfaces = null;
		
		for(int k = 0; k < interfaceLibs.size(); k++) {
			if(interfaceLibs.get(k).getName().equals(partsOfPath[0])) {
				currInterfaces = interfaceLibs.get(k).getInterfaceClass();
				break;
			}
		}
		if(currInterfaces == null) {
			return null;
		}
		
		int j = 0;
		
		for(int i = 1; i < partsOfPath.length; i++) {
			for(j = 0; j < currInterfaces.size(); j++) {
				if(currInterfaces.get(j).getName().equals(partsOfPath[i])) {
					if(i + 1 == partsOfPath.length) {
						return currInterfaces.get(j);
					}
					nextInterfaces = currInterfaces.get(j).getInterfaceClass();
					break;
				}
			}
			if(nextInterfaces == null || nextInterfaces.size() == 0) {
				return null;
			} else {
				currInterfaces = nextInterfaces;
				nextInterfaces = null;
			}
		}
		
		return null;
	}
	
	public static String getCompletePath(RoleClass roleClass) {
		if(roleClass == null) {
			return null;
		}
		
		RoleClass currRole = roleClass;
		EObject helpingObject;
		String[] currStringParts = new String[100];		//Anzahl utopisch
		int currPart = 0;
		
		while(true) {
			currStringParts[currPart] = currRole.getName();
			currPart ++;
			helpingObject = currRole.eContainer();
			if(helpingObject instanceof RoleClass) {
				currRole = (RoleClass) helpingObject;
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
	
	public static RoleClass resolveRolePath(String path, CAEXFile caexFile) {
		if(path == null || path.isEmpty() || caexFile == null) {
			return null;
		}
		
		String[] partsOfPath = path.split("/");
		EList<RoleClassLib> roleLibs = caexFile.getRoleClassLib();
		EList<RoleClass> currRoles = null;
		EList<RoleClass> nextRoles = null;
		
		for(int k = 0; k < roleLibs.size(); k++) {
			if(roleLibs.get(k).getName().equals(partsOfPath[0])) {
				currRoles = roleLibs.get(k).getRoleClass();
				break;
			}
		}
		if(currRoles == null) {
			return null;
		}
		
		int j = 0;
		
		for(int i = 1; i < partsOfPath.length; i++) {
			for(j = 0; j < currRoles.size(); j++) {
				if(currRoles.get(j).getName().equals(partsOfPath[i])) {
					if(i + 1 == partsOfPath.length) {
						return currRoles.get(j);
					}
					nextRoles = currRoles.get(j).getRoleClass();
					break;
				}
			}
			if(nextRoles == null || nextRoles.size() == 0) {
				return null;
			} else {
				currRoles = nextRoles;
				nextRoles = null;
			}
		}
		
		return null;
	}
}
