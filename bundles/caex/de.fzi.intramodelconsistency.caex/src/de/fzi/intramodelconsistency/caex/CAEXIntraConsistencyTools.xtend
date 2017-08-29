package de.fzi.intramodelconsistency.caex

import CAEX.DocumentRoot
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import tools.vitruv.framework.correspondence.CorrespondenceModel
import tools.vitruv.framework.tuid.AttributeTuidCalculatorAndResolver
import tools.vitruv.framework.util.VitruviusConstants
import tools.vitruv.framework.tuid.Tuid
import CAEX.CAEXObject

class CAEXIntraConsistencyTools {	

	private static val TUID_SEPARATOR = AttributeTuidCalculatorAndResolver.SUBDIVIDER
	private static val TUID_SEGMENT_SEPARATOR = VitruviusConstants.tuidSegmentSeperator
	
	static def String generateTuidFromPath(CorrespondenceModel cm, EObject anyElem, String path) {

		
		if(path === null || path == "") return null		
		var root = EcoreUtil.getRootContainer(anyElem) as DocumentRoot
		var fileTuid = cm.calculateTuidFromEObject(root.CAEXFile)
		
		var split = path.split("/")
		
		var finalStr = '''«fileTuid»«TUID_SEGMENT_SEPARATOR»systemUnitClassLib-_-SystemUnitClassLib-_-name=«split.get(0)»'''
		for(var i=1; i<split.length;i++)
			finalStr += '''«TUID_SEGMENT_SEPARATOR»systemUnitClass«TUID_SEPARATOR»SystemUnitClass«TUID_SEPARATOR»name=«split.get(i)»'''
		return finalStr
	}

	static def String generatePathFromTuid(CorrespondenceModel cm, EObject anyElem, Tuid tuid) {		 
		generatePathFromTuid(cm,anyElem,tuid.toString)
	}
	
	static def String generatePathFromTuid(CorrespondenceModel cm, EObject anyElem, String tuid) {		 
		 var root = cm.calculateTuidFromEObject((EcoreUtil.getRootContainer(anyElem) as DocumentRoot).CAEXFile).toString
		 if(tuid === null || !tuid.startsWith(root)) return null
		 var splitPath = tuid.substring(root.length).split(TUID_SEGMENT_SEPARATOR)
		 var path = ""
		 var temp_tuid = root
		 for(item : splitPath.filter[it!=""]){
		 	//get said element
		 	temp_tuid += '''«TUID_SEGMENT_SEPARATOR»«item»'''
		 	path += (cm.resolveEObjectFromTuid(Tuid.getInstance(temp_tuid)) as	CAEXObject).name + "/"	 	
		 }		 
		 path.substring(0,path.length-1)
	}
}