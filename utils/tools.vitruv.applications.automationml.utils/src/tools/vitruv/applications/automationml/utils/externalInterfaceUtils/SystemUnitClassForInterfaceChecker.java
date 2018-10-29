package tools.vitruv.applications.automationml.utils.externalInterfaceUtils;

import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.ExternalInterface;
import caex.caex30.caex.InterfaceClass;
import caex.caex30.caex.InternalElement;
import caex.caex30.caex.SystemUnitClass;
import tools.vitruv.applications.automationml.utils.caexresolver.CAEXInheritanceResolver;

public class SystemUnitClassForInterfaceChecker {
	
	// Prüft, ob ein bestimmtes Interface in einer Klasse enthalten ist (true: ist enthalten)
	// Auch Vererbung wird berücksichtigt
	public static boolean checkForExternalInterface(SystemUnitClass systemClass, ExternalInterfaceType interfaceType, String refBaseClassPath, CAEXFile root) {
		for(InterfaceClass currInterface : systemClass.getExternalInterface()) {
			if(currInterface instanceof ExternalInterface) {
				switch (interfaceType) {
					case PLCOPEN:
						if(CAEXInheritanceResolver.isInstanceOfPLCopenInterface((ExternalInterface)currInterface, root)) {
							return true;
						}
						break;
					case COLLADA:
						if(CAEXInheritanceResolver.isInstanceOfColladaInterface((ExternalInterface)currInterface, root)) {
							return true;
						}
						break;
					case DEFAULT:
						if(CAEXInheritanceResolver.isInstanceOfSpecificInterface((ExternalInterface)currInterface, refBaseClassPath, root)) {
							return true;
						}
						break;
				}
			}
		}
		return false;
	}
	
	public static boolean checkForExternalInterface(SystemUnitClass systemClass, ExternalInterfaceType interfaceType, CAEXFile root) {
		return checkForExternalInterface(systemClass, interfaceType, "", root);
	}
	
	public static boolean checkForExternalInterface(SystemUnitClass systemClass, String refBaseClassPath, CAEXFile root) {
		return checkForExternalInterface(systemClass, ExternalInterfaceType.DEFAULT, refBaseClassPath, root);
	}
	
	// Liefert ein bestimmtes Interface zurück, falls vorhanden
	// Auch Vererbung wird berücksichtigt
	public static ExternalInterface getExternalInterface(SystemUnitClass systemClass, ExternalInterfaceType interfaceType, String refBaseClassPath, CAEXFile root) {
		for(InterfaceClass currInterface : systemClass.getExternalInterface()) {
			if(currInterface instanceof ExternalInterface) {
				switch (interfaceType) {
					case PLCOPEN:
						if(CAEXInheritanceResolver.isInstanceOfPLCopenInterface((ExternalInterface)currInterface, root)) {
							return (ExternalInterface)currInterface;
						}
						break;
					case COLLADA:
						if(CAEXInheritanceResolver.isInstanceOfColladaInterface((ExternalInterface)currInterface, root)) {
							return (ExternalInterface)currInterface;
						}
						break;
					case DEFAULT:
						if(CAEXInheritanceResolver.isInstanceOfSpecificInterface((ExternalInterface)currInterface, refBaseClassPath, root)) {
							return (ExternalInterface)currInterface;
						}
						break;
				}
			}
		}
		return null;
	}
		
	public static ExternalInterface getExternalInterface(SystemUnitClass systemClass, ExternalInterfaceType interfaceType, CAEXFile root) {
		return getExternalInterface(systemClass, interfaceType, "", root);
	}
		
	public static ExternalInterface getExternalInterface(SystemUnitClass systemClass, String refBaseClassPath, CAEXFile root) {
		return getExternalInterface(systemClass, ExternalInterfaceType.DEFAULT, refBaseClassPath, root);
	}
	
	// Methode, um ein internalElement zu durchsuchen, ob ein ExternalInterface enthalten ist
	public static ExternalInterface getExternalInterfaceFromElement(InternalElement internalElement, String externalInterfaceName) {
		for(InterfaceClass currInterface : internalElement.getExternalInterface()) {
			if(currInterface instanceof ExternalInterface) {
				if(currInterface.getName().equals(externalInterfaceName)) {
					return (ExternalInterface)currInterface;
				}
			}
		}
		return null;
	}
}
