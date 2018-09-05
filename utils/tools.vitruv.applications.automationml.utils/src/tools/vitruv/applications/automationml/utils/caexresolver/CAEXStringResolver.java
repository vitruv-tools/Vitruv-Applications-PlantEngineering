package tools.vitruv.applications.automationml.utils.caexresolver;

import org.eclipse.emf.common.util.EList;
import org.automationml.caex.caex.CAEXFile;
import org.automationml.caex.caex.ExternalInterface;
import org.automationml.caex.caex.InterfaceClass;
import org.automationml.caex.caex.InterfaceClassLib;
import org.automationml.caex.caex.SystemUnitClass;
import org.automationml.caex.caex.SystemUnitClassLib;

public class CAEXStringResolver {
	//TODO allgemeiner Resolver
	
	// löst einen Pfad auf, und liefert das zugehörige Interface zurück
	public static ExternalInterface resolveInterfacePath(String path, CAEXFile caexFile) {
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
		
		if(currInterfaces.size() == 1 && currInterfaces.get(0) instanceof ExternalInterface) {
			return (ExternalInterface)currInterfaces.get(0);
		} else {
			return null;
		}
	}
	
	// löst einen Pfad auf, und liefert die zugehörige Klasse zurück
	public static SystemUnitClass resolveSystemClassPath(String path, CAEXFile caexFile) {
		if(path == null || path.isEmpty() || caexFile == null) {
			return null;
		}
		
		String[] partsOfPath = path.split("/");
		EList<SystemUnitClassLib> classLibs = caexFile.getSystemUnitClassLib();
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
		
		if(currClasses.size() == 1 && currClasses.get(0) instanceof ExternalInterface) {
			return (SystemUnitClass)currClasses.get(0);
		} else {
			return null;
		}
	}
}
