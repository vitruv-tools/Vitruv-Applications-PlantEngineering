package tools.vitruv.applications.caexintra

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import tools.vitruv.framework.correspondence.CorrespondenceModel
import tools.vitruv.framework.tuid.AttributeTuidCalculatorAndResolver
import tools.vitruv.framework.util.VitruviusConstants
import tools.vitruv.framework.tuid.Tuid
import caex.caex30.caex.CAEXObject
import caex.caex30.caex.CAEXFile
import caex.caex30.caex.DocumentRoot

class CAEXIntraConsistencyTools {	

	private static val TUID_SEPARATOR = AttributeTuidCalculatorAndResolver.SUBDIVIDER
	private static val TUID_SEGMENT_SEPARATOR = VitruviusConstants.tuidSegmentSeperator
	
	/**
	 * <p>Generates the Tuid of the target element given a path originating from the root
	 * CAEXFile object in the form 'CAEXFile.fileName/someObj.name/../../targetObj.name'</p>
	 * <p><b>Note:</b> The existence of the target element is not guarateed!
	 * Resolving the Tuid may cause Errors {@link tools.vitruv.framework.correspondence.CorrespondenceModel#resolveEObjectFromTuid}</p> 
	 */
	static def String generateTuidFromPath(CorrespondenceModel cm, EObject anyElem, String path) {

		
		if(path === null || path == "") return null		
		var fileTuid = cm.calculateTuidFromEObject(anyElem.rootCAEXFile)
		
		var split = path.split("/")
		
		var finalStr = '''«fileTuid»«TUID_SEGMENT_SEPARATOR»systemUnitClassLib-_-SystemUnitClassLib-_-name=«split.get(0)»'''
		for(var i=1; i<split.length;i++)
			finalStr += '''«TUID_SEGMENT_SEPARATOR»systemUnitClass«TUID_SEPARATOR»SystemUnitClass«TUID_SEPARATOR»name=«split.get(i)»'''
		return finalStr
	}
	
	/**
	 * Given the tuid of the target object a string path of the form 'CAEXFile.fileName/someObj.name/../../targetObj.name' 
	 * is generated, originating from the root CAEXFile object and pointing to the target object 
	 */
	static def String generatePathFromTuid(CorrespondenceModel cm, EObject anyElem, Tuid tuid) {		 
		generatePathFromTuid(cm,anyElem,tuid.toString)
	}
	
	/**
	 * Given the tuid of the target object a string path of the form 'CAEXFile.fileName/someObj.name/../../targetObj.name' 
	 * is generated, originating from the root CAEXFile object and pointing to the target object 
	 */
	static def String generatePathFromTuid(CorrespondenceModel cm, EObject anyElem, String tuid) throws NullPointerException{		 
		 var root = cm.calculateTuidFromEObject(anyElem.rootCAEXFile).toString
		 if(tuid === null || !tuid.startsWith(root)) return null
		 var splitPath = tuid.substring(root.length).split(TUID_SEGMENT_SEPARATOR)
		 var path = ""
		 var temp_tuid = root
		 for(item : splitPath.filter[it!=""]){
		 	try {
		 		//get said element
		 		temp_tuid += '''«TUID_SEGMENT_SEPARATOR»«item»'''
		 		path += (cm.resolveEObjectFromTuid(Tuid.getInstance(temp_tuid)) as	CAEXObject).name + "/"		 		
		 	} catch (NullPointerException e) return null;	 	
		 }		 
		 path.substring(0,path.length-1)
	}
	
	/** 
	 * Returns the root CAEXFile Object of the model regardless of the root that Vitruv is using.
	 * (DocumentRoot in the Editor vs CAEXFile for Tests)
	 * 
	 * @param anyObject Any object that is part of the model
	 */
	private static def CAEXFile getRootCAEXFile(EObject anyObject) {
		//Retrieve root Object
		var rootObj = EcoreUtil.getRootContainer(anyObject)
		//Cast either as DocumentRoot or CAEXFile
		if (rootObj !== null && rootObj instanceof DocumentRoot && ((rootObj as DocumentRoot).CAEXFile !== null))
			return (rootObj as DocumentRoot).CAEXFile
		else if (rootObj !== null && rootObj instanceof CAEXFile)
			return rootObj as CAEXFile
		return null
	}
}
