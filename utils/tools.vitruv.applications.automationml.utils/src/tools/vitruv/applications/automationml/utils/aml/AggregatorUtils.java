package tools.vitruv.applications.automationml.utils.aml;

import edu.kit.sdq.aml_aggregator.AMLProject;
import edu.kit.sdq.aml_aggregator.ColladaContainer;
import edu.kit.sdq.aml_aggregator.PLCopenContainer;

/**
 * Class with static methods for working with the AML-Aggregator metamodel
 * @author A.Lang
 */
public class AggregatorUtils {
	
	/**
	 * Number of plcopen files in the project
	 */
	private static int numberOfPLCopenFiles = 0;
	
	/**
	 * Number of collada files in the project
	 */
	private static int numberOfColladaFiles = 0;
	
	/**
	 * Number of caex files in the project
	 */
	private static int numberOfCAEXFiles = 0;
	
	/**
	 * increase number of caex files in the project by 1
	 * @return new number of caex files
	 */
	public static int getNewNumberOfCAEXFiles() {
		numberOfCAEXFiles ++;
		return numberOfCAEXFiles;
	}
	
	/**
	 * increase number of plcopen files in the project by 1
	 * @return new number of plcopen files
	 */
	public static int getNewNumberOfPLCopenFiles() {
		numberOfPLCopenFiles ++;
		return numberOfPLCopenFiles;
	}
	
	/**
	 * increase number of collada files in the project by 1
	 * @return new number of collada files
	 */
	public static int getNewNumberOfColladaFiles() {
		numberOfColladaFiles ++;
		return numberOfColladaFiles;
	}
	
	/**
	 * returns the container for a specific collada files
	 */
	public static ColladaContainer getColladaContainerByPath(AMLProject project, String path){
		for(ColladaContainer currContainer : project.getColladacontainer()) {
			if(currContainer.getPath().equals(path)) {
				return currContainer;
			}
		}
		return null;
	}
	
	/**
	 * return the container for a specific plcopen file
	 */
	public static PLCopenContainer getPLCopenContainerByPath(AMLProject project, String path) {
		for(PLCopenContainer currContainer : project.getPlcopencontainer()) {
			if(currContainer.getPath().equals(path)) {
				return currContainer;
			}
		}
		return null;
	}
}
