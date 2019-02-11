package tools.vitruv.applications.automationml.utils.externalInterfaceUtils;

import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.InterfaceClass;
import tools.vitruv.applications.automationml.utils.aml.AMLBasicStrings;
import tools.vitruv.applications.automationml.utils.stringreferences.caex.InterfaceClassUtils;

/**
 * class with some methods to check if interfaces are instances of a parent-interface
 * @author A.Lang
 */
public class InterfaceTypeUtils {
	
	// überprüft, ob ein Interface aus der InterfaceBibliothek (externalInterface) im Vererbungsbaum unter einem anderen Interface (interfaceClass) steht
	/**
	 * method to check if an interface is a ExternalInterface for collada files
	 */
	public static boolean isInterfaceInstanceOfPLCopenInterface(InterfaceClass interfaceChild, CAEXFile root) {
		return isInterfaceInstanceOfSpecificInterface(interfaceChild, AMLBasicStrings.getCompletePLCopenBaseInterfacePath(), root);
	}
	
	/**
	 * method to check if an interface is a ExternalInterface for plcopen files
	 */
	public static boolean isInterfaceInstanceOfColladaInterface(InterfaceClass interfaceChild, CAEXFile root) {
		return isInterfaceInstanceOfSpecificInterface(interfaceChild, AMLBasicStrings.getCompleteColladaBaseInterfacePath(), root);
	}
	
	/**
	 * method to check, if an interface (interfaceChild) is a instance of a specific other interface (interfaceParent)
	 */
	public static boolean isInterfaceInstanceOfSpecificInterface(InterfaceClass interfaceChild, String interfaceParent, CAEXFile root) {
		InterfaceClass currInterface = interfaceChild;
		while(currInterface != null) {
			if(interfaceParent.equals(InterfaceClassUtils.getCompletePathOfInterfaceClass(currInterface))) {
				return true;
			}
			currInterface = InterfaceClassUtils.getInterfaceClassByPath(currInterface.getRefBaseClassPath(), root);
			if(!(currInterface instanceof InterfaceClass)) {
				return false;
			}
		}
		return false;
	}
}
