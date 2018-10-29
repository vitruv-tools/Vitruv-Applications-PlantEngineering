package tools.vitruv.applications.automationml.utils.aml;

public class AMLBasicStrings {
	private static final String automationMLInterfaceBaseLibName = "AutomationMLInterfaceClassLib";
	private static final String automationMLBaseInterfaceName = "AutomationMLBaseInterface";
	private static final String automationMLExternalBaseInterfaceName = "ExternalDataConnector";
	private static final String externalColladaBaseInterfaceName = "COLLADAInterface";
	private static final String externalPLCopenBaseInterfaceName = "PLCopenXMLInterface";
	
	public static String getAutomationMLInterfaceBaseLibName() {
		return automationMLInterfaceBaseLibName;
	}
	
	public static String getAutomationMLBaseInterfaceName() {
		return automationMLBaseInterfaceName;
	}
	
	public static String getAutomationMLExternalBaseInterfaceName() {
		return automationMLExternalBaseInterfaceName;
	}
	
	public static String getExternalColladaBaseInterfaceName() {
		return externalColladaBaseInterfaceName;
	}
	
	public static String getExternalPLCopenBaseInterfaceName() {
		return externalPLCopenBaseInterfaceName;
	}
	
	public static String getCompleteColladaBaseInterfacePath() {
		return automationMLInterfaceBaseLibName + "/" + automationMLBaseInterfaceName + "/" + automationMLExternalBaseInterfaceName + "/" + externalColladaBaseInterfaceName;
	}
	
	public static String getCompletePLCopenBaseInterfacePath() {
		return automationMLInterfaceBaseLibName + "/" + automationMLBaseInterfaceName + "/" + automationMLExternalBaseInterfaceName + "/" + externalPLCopenBaseInterfaceName;
	}
}
