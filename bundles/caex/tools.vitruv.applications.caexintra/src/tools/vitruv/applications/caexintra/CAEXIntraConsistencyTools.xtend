package tools.vitruv.applications.caexintra

import caex.caex30.caex.CAEXFile
import caex.caex30.caex.DocumentRoot
import java.util.NoSuchElementException
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import caex.caex30.caex.CAEXBasicObject
import caex.caex30.caex.CAEXObject

class CAEXIntraConsistencyTools {
	
	/** 
	 * Returns the root CAEXFile Object of the model regardless of the root that Vitruv is using.
	 * (DocumentRoot in the Editor vs CAEXFile for Tests)
	 * 
	 * @param anyObject Any object that is part of the model
	 */
	private static def CAEXFile getRootCAEXFile(CAEXBasicObject anyObject) {
		//Retrieve root Object
		var rootObj = EcoreUtil.getRootContainer(anyObject)
		//Cast either as DocumentRoot or CAEXFile
		if (rootObj !== null && rootObj instanceof DocumentRoot && ((rootObj as DocumentRoot).CAEXFile !== null))
			return (rootObj as DocumentRoot).CAEXFile
		else if (rootObj !== null && rootObj instanceof CAEXFile)
			return rootObj as CAEXFile
		return null
	}
	
	/**
	 * Generates a CAEX conform path to the given CAEXObject or returns an empty String if no path can be found. 
	 * 
	 * @param target The Object to generate a pat for
	 * 
	 * @returns Valid path from root element to target or empty string. 
	 */
	public static def String generatePath(CAEXObject target) {
		var path = try {
			generatePathRecursive(target)			
		}
		catch(MalformedModelException e) {
			return "";
		}
		
		//remove trailing '/'
		if (path.endsWith('/'))
			return path.substring(0, path.length - 1)
		return path	
	}
	
	private static def String generatePathRecursive(EObject target) {
		if(target instanceof CAEXFile) {
			return ""
		} else if (target instanceof CAEXObject){
				return generatePathRecursive(target.eContainer) + target.name + "/"
		} else throw new MalformedModelException("Model has no Root");
	}
	
	/**
	 * Returns the CAEX element that can be found at the specified path or null if no element was found.
	 * 
	 * @param anyModelElement A random CAEX model element from the same model. Required to resolve the root element
	 * @param path Location where the element can be found
	 * 
	 * @returns The CAEX element at the specified path or null  
	 */
	public static def EObject findByPathFromRoot(CAEXBasicObject anyModelElement ,String path) {
		val start = getRootCAEXFile(anyModelElement)
		if(start === null) return null;
		return findByPath(start, path)
	}
	
	/**
	 * Returns the CAEX element that can be found at the specified path or null if no element was found.
	 * 
	 * @param start A CAEX model element to start traversing the model from
	 * @param path Location where the element can be found relative to the start element
	 * 
	 * @returns The CAEX element at the specified path or null  
	 */
	public static def EObject findByPath(EObject start, String path) {
		if(path === null) return null;
		val next = path.split("/").get(0) ?: path as String
		var all = start.eContents
		var found = all.filter[eClass.getEStructuralFeature("name") !== null && next.equals((eGet(eClass.getEStructuralFeature("name")) as String))]
		if (found.empty) throw new NoSuchElementException('''No element with name='«next»' was found at entity «start»!''')
		
		if(path.contains("/")) return findByPath(found.get(0), path.split("/").stream.skip(1).toArray.fold("",[s,t|s+"/"+t]).substring(1))
		return found.get(0)
	}
	
	private static class MalformedModelException extends RuntimeException {		
		new(String string) {
			super(string)
		}		
	}
}
