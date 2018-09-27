package tools.vitruv.applications.automationml.utils.amlutils;

import edu.kit.sdq.aml_aggregator.AMLProject;
import edu.kit.sdq.aml_aggregator.ColladaContainer;
import edu.kit.sdq.aml_aggregator.PLCopenContainer;

public class AMLAggregatorResolver {
	public static ColladaContainer getColladaContainer(AMLProject project, String path){
		for(ColladaContainer currContainer : project.getColladacontainer()) {
			if(currContainer.getPath().equals(path)) {
				return currContainer;
			}
		}
		return null;
	}
	
	public static PLCopenContainer getPLCopenContainer(AMLProject project, String path) {
		for(PLCopenContainer currContainer : project.getPlcopencontainer()) {
			if(currContainer.getPath().equals(path)) {
				return currContainer;
			}
		}
		return null;
	}
}
