package tools.vitruv.applications.automationml.utils.caexresolver;

import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.InterfaceClass;
import tools.vitruv.applications.automationml.utils.aml.AMLBasicStrings;

public class CAEXInheritanceResolver {
	
	// überprüft, ob ein Interface aus der InterfaceBibliothek (externalInterface) im Vererbungsbaum unter einem anderen Interface (interfaceClass) steht
	public static boolean isInstanceOfPLCopenInterface(InterfaceClass interfaceChild, CAEXFile root) {
		return isInstanceOfSpecificInterface(interfaceChild, AMLBasicStrings.getCompletePLCopenBaseInterfacePath(), root);
	}
	
	public static boolean isInstanceOfColladaInterface(InterfaceClass interfaceChild, CAEXFile root) {
		return isInstanceOfSpecificInterface(interfaceChild, AMLBasicStrings.getCompleteColladaBaseInterfacePath(), root);
	}
	
	public static boolean isInstanceOfSpecificInterface(InterfaceClass interfaceChild, String interfaceParent, CAEXFile root) {
		InterfaceClass currInterface = interfaceChild;
		while(currInterface != null) {
			if(interfaceParent.contentEquals(CAEXStringResolver.getCompletePath(currInterface))) {
				return true;
			}
			currInterface = CAEXStringResolver.resolveInterfacePath(currInterface.getRefBaseClassPath(), root);
			if(!(currInterface instanceof InterfaceClass)) {
				return false;
			}
		}
		return false;
	}
}
