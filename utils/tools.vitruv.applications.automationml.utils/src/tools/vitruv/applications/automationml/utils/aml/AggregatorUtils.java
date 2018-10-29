package tools.vitruv.applications.automationml.utils.aml;

import edu.kit.sdq.aml_aggregator.AMLProject;
import edu.kit.sdq.aml_aggregator.ColladaContainer;
import edu.kit.sdq.aml_aggregator.PLCopenContainer;

public class AggregatorUtils {
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
	
	public static ColladaContainer getColladaContainerByPath(AMLProject project, String path){
		for(ColladaContainer currContainer : project.getColladacontainer()) {
			if(currContainer.getPath().equals(path)) {
				return currContainer;
			}
		}
		return null;
	}
	
	public static PLCopenContainer getPLCopenContainerByPath(AMLProject project, String path) {
		for(PLCopenContainer currContainer : project.getPlcopencontainer()) {
			if(currContainer.getPath().equals(path)) {
				return currContainer;
			}
		}
		return null;
	}
}
