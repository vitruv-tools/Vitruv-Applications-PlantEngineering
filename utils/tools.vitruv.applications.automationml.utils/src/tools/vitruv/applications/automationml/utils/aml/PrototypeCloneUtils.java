package tools.vitruv.applications.automationml.utils.aml;

import java.util.LinkedList;
import java.util.List;
import caex.caex30.caex.Attribute;
import caex.caex30.caex.InternalElement;
import caex.caex30.caex.SystemUnitClass;

/**
 * Class with some methods to check, if prototype and clone are corresponding
 * @author A.Lang
 */
public class PrototypeCloneUtils {
	/**
	 * method to check which clones of a prototype are corresponding to the clone
	 * @return list of corresponding clones
	 */
	public static List<InternalElement> checkClones(SystemUnitClass prototype, List<InternalElement> clones) {
		List<InternalElement> filteredClones = new LinkedList<InternalElement>();
		for(InternalElement currClone : clones) {
			if(!checkPrototypAndCloneForDiffs(prototype, currClone)) {
				filteredClones.add(currClone);
			}
		}
		return filteredClones;
	}
	
	/**
	 * method to check if a specific clone is corresponding to its prototype
	 * @return false, if it is corresponding (no differences in the attributes)
	 */
	public static boolean checkPrototypAndCloneForDiffs(SystemUnitClass prototype, InternalElement clone) {
		for(Attribute currAtt : prototype.getAttribute()) {
			Attribute cloneAttribute = CAEXUtils.getAttributeInClone(currAtt.getName(), clone);
			boolean isDifferent = checkTwoAttributesForDiffs(currAtt, cloneAttribute);
			if(isDifferent) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * method to check, if two attributes have the same name and value
	 * @return false, if they have the same name and value
	 */
	public static boolean checkTwoAttributesForDiffs(Attribute newAtt, Attribute oldAtt) {
		if (!newAtt.getName().equals(oldAtt.getName())) {
			return true;
		}
		if (!newAtt.getValue().equals(oldAtt.getValue())) {
			return true;
		}
		return false;
	}
}
