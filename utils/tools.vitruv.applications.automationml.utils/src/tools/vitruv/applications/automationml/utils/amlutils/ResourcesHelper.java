package tools.vitruv.applications.automationml.utils.amlutils;

public class ResourcesHelper {
	private static int numberOfPLCopenFiles = 0;
	private static int numberOfColladaFiles = 0;
	private static int numberOfCAEXFiles = 0;
	
	public static int getNewNumberOfCAEXFiles() {
		numberOfCAEXFiles ++;
		return numberOfCAEXFiles;
	}
	
	public static int getNewNumberOfPLCopenFiles() {
		numberOfPLCopenFiles ++;
		return numberOfPLCopenFiles;
	}
	
	public static int getNewNumberOfColladaFiles() {
		numberOfColladaFiles ++;
		return numberOfColladaFiles;
	}
}
