package tools.vitruv.applications.automationml.utils.caexresolver;

import caex.caex30.caex.ExternalInterface;
import caex.caex30.caex.InterfaceClass;

public class CAEXInheritanceResolver {
	private static final String automationMLInterfaceBaseLib = "AutomationMLInterfaceClassLib";
	private static final String automationMLBaseInterface = "AutomationMLBaseInterface";
	private static final String automationMLExternalBaseInterface = "ExternalDataConnector";
	private static final String externalColladaBaseInterface = "COLLADAInterface";
	private static final String externalPLCopenBaseInterface = "PLCopenXMLInterface";
	
	// überprüft, ob ein Interface aus der InterfaceBibliothek (externalInterface) im Vererbungsbaum unter einem anderen Interface (interfaceClass) steht
	public static boolean isInstanceOfPLCopenInterface(InterfaceClass interfaceChild) {
		return isInstanceOfSpecificInterface(interfaceChild, automationMLInterfaceBaseLib + "/" + automationMLBaseInterface + "/" + automationMLExternalBaseInterface + "/" + externalPLCopenBaseInterface);
	}
	
	public static boolean isInstanceOfColladaInterface(InterfaceClass interfaceChild) {
		return isInstanceOfSpecificInterface(interfaceChild, automationMLInterfaceBaseLib + "/" + automationMLBaseInterface + "/" + automationMLExternalBaseInterface + "/" + externalPLCopenBaseInterface);
	}
	
	public static boolean isInstanceOfSpecificInterface(InterfaceClass interfaceChild, String interfaceParent) {
		InterfaceClass currInterface = interfaceChild;
		while(currInterface != null) {
			if(currInterface.getName().equals(interfaceParent)) {
				return true;
			}
			if(currInterface.getBaseClass() instanceof InterfaceClass) {
				currInterface = (ExternalInterface)currInterface.getBaseClass();
			} else {
				return false;
			}
		}
		return false;
	}
	
	public static String getAutomationMLInterfaceBaseLib() {
		return automationMLInterfaceBaseLib;
	}
	
	public static String getAutomationMLBaseInterface() {
		return automationMLBaseInterface;
	}
	
	public static String getAutomationMLExternalBaseInterface() {
		return automationMLExternalBaseInterface;
	}
	
	public static String getExternalColladaBaseInterface() {
		return externalColladaBaseInterface;
	}
	
	public static String getExternalPLCopenBaseInterface() {
		return externalPLCopenBaseInterface;
	}
}
