package tools.vitruv.applications.automationml.utils.caexresolver;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.ExternalInterface;
import caex.caex30.caex.InterfaceClass;
import caex.caex30.caex.InterfaceClassLib;
import caex.caex30.caex.SystemUnitClass;
import caex.caex30.caex.SystemUnitClassLib;


public class CAEXStringResolver {
	public static String getCompletePath(InterfaceClass interfaceClass) {
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
	
	public static String getCompletePath(SystemUnitClass systemClass) {
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
		
		for(int i = 1; i < partsOfPath.length; i++) {
			for(int j = 0; j < currInterfaces.size(); j++) {
				if(currInterfaces.get(j).getName().equals(partsOfPath[i])) {
					nextInterfaces = currInterfaces.get(j).getInterfaceClass();
					break;
				}
			}
			if(nextInterfaces == null) {
				return null;
			} else {
				currInterfaces = nextInterfaces;
				nextInterfaces = null;
			}
		}
		
		if(currInterfaces.size() == 1) {
			return currInterfaces.get(0);
		} else {
			return null;
		}
	}
	
	// löst einen Pfad auf, und liefert die zugehörige Klasse (aus den Klassenbibliotheken) zurück
	public static SystemUnitClass resolveSystemClassPath(String path, CAEXFile caexFile) {
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
}
