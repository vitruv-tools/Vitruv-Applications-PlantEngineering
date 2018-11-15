package tools.vitruv.applications.automationml.utils.caexresolver;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.ExternalInterface;
import caex.caex30.caex.InterfaceClass;
import caex.caex30.caex.InterfaceClassLib;

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
}
