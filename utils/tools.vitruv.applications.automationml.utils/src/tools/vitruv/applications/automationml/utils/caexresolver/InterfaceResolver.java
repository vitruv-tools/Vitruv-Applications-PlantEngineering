package tools.vitruv.applications.automationml.utils.caexresolver;

import java.util.LinkedList;

import org.eclipse.emf.common.util.EList;
import caex.caex30.caex.CAEXFile;
import caex.caex30.caex.InstanceHierarchy;
import caex.caex30.caex.InterfaceClass;
import caex.caex30.caex.InterfaceClassLib;
import caex.caex30.caex.InternalElement;
import caex.caex30.caex.RoleClass;
import caex.caex30.caex.RoleClassLib;
import caex.caex30.caex.SystemUnitClass;
import caex.caex30.caex.SystemUnitClassLib;

public class InterfaceResolver {	
	// löst einen Pfad auf, und liefert dan zugehörigen Fehlertyp zurück 
	public static ErrorCode checkIfPathIsExisting(String path, CAEXFile caexFile) {
		if(path == null || path.isEmpty() || caexFile == null) {
			return ErrorCode.EMPTY_STRING_OR_FILE;
		}

		if(path.lastIndexOf("/") == -1) {
			return ErrorCode.BREAK_RULE;
		}
		
		String[] partsOfPath = path.split("/");
		LinkedList<ErrorCode> actualCodes = new LinkedList<ErrorCode>();
		
		for(SystemUnitClassLib currLib : caexFile.getSystemUnitClassLib()) {
			if(currLib.getName().equals(partsOfPath[0])) {
				actualCodes.addFirst(checkSystemLib(currLib, partsOfPath));
			}
		}
		
		for(RoleClassLib currLib : caexFile.getRoleClassLib()) {
			if(currLib.getName().equals(partsOfPath[0])) {
				actualCodes.addFirst(checkRoleLib(currLib, partsOfPath));
			}
		}
		
		for(InterfaceClassLib currLib : caexFile.getInterfaceClassLib()) {
			if(currLib.getName().equals(partsOfPath[0])) {
				actualCodes.addFirst(checkInterfaceLib(currLib, partsOfPath));
			}
		}
		
		for(InstanceHierarchy currLib : caexFile.getInstanceHierarchy()) {
			if(currLib.getName().equals(partsOfPath[0])) {
				actualCodes.addFirst(checkInstanceHierarchy(currLib, partsOfPath));
			}
		}
		
		ErrorCode actualCode = ErrorCode.SEGMENT_NOT_FOUND;
		
		for(ErrorCode currCode : actualCodes) {
			if(currCode == ErrorCode.INTERFACE_FOUND) {
				return currCode;
			} else if(currCode == ErrorCode.WRONG_TYPE) {
				actualCode = currCode;
			} else if(currCode == ErrorCode.LAST_SEGMENT_NOT_FOUND && actualCode != ErrorCode.WRONG_TYPE) {
				actualCode = currCode;
			}
		}
		
		return actualCode;
	}
	
	private static ErrorCode checkSystemLib(SystemUnitClassLib lib, String[] partsOfPath) {
		EList<SystemUnitClass> currClasses = lib.getSystemUnitClass();
		for(int i = 1; i < partsOfPath.length; i++) {
			SystemUnitClass nextClass = null;
			for(SystemUnitClass currClass : currClasses) {
				nextClass = checkSystemClassForChild(currClass, partsOfPath[i]);
				if(nextClass != null) {
					break;
				}
			}	
			if(nextClass == null) {
				if(i == partsOfPath.length) {
					return ErrorCode.LAST_SEGMENT_NOT_FOUND;
				} else {
					return ErrorCode.SEGMENT_NOT_FOUND;
				}
			} else {
				if(i == partsOfPath.length) {
					return ErrorCode.INTERFACE_FOUND;
				} else {
					currClasses = nextClass.getSystemUnitClass();
				}
			}
		}
		return ErrorCode.SEGMENT_NOT_FOUND;
	}
	
	private static ErrorCode checkRoleLib(RoleClassLib lib, String[] partsOfPath) {
		EList<RoleClass> currClasses = lib.getRoleClass();
		for(int i = 1; i < partsOfPath.length; i++) {
			RoleClass nextClass = null;
			for(RoleClass currClass : currClasses) {
				nextClass = checkRoleClassForChild(currClass, partsOfPath[i]);
				if(nextClass != null) {
					break;
				}
			}	
			if(nextClass == null) {
				if(i == partsOfPath.length) {
					return ErrorCode.LAST_SEGMENT_NOT_FOUND;
				} else {
					return ErrorCode.SEGMENT_NOT_FOUND;
				}
			} else {
				if(i == partsOfPath.length) {
					return ErrorCode.INTERFACE_FOUND;
				} else {
					currClasses = nextClass.getRoleClass();
				}
			}
		}
		return ErrorCode.SEGMENT_NOT_FOUND;
	}

	private static ErrorCode checkInterfaceLib(InterfaceClassLib lib, String[] partsOfPath) {
		EList<InterfaceClass> currClasses = lib.getInterfaceClass();
		for(int i = 1; i < partsOfPath.length; i++) {
			InterfaceClass nextClass = null;
			for(InterfaceClass currClass : currClasses) {
				nextClass = checkInterfaceClassForChild(currClass, partsOfPath[i]);
				if(nextClass != null) {
					break;
				}
			}	
			if(nextClass == null) {
				if(i == partsOfPath.length) {
					return ErrorCode.LAST_SEGMENT_NOT_FOUND;
				} else {
					return ErrorCode.SEGMENT_NOT_FOUND;
				}
			} else {
				if(i == partsOfPath.length) {
					return ErrorCode.INTERFACE_FOUND;
				} else {
					currClasses = nextClass.getInterfaceClass();
				}
			}
		}
		return ErrorCode.SEGMENT_NOT_FOUND;
	}

	private static ErrorCode checkInstanceHierarchy(InstanceHierarchy lib, String[] partsOfPath) {
		EList<InternalElement> currClasses = lib.getInternalElement();
		for(int i = 1; i < partsOfPath.length; i++) {
			InternalElement nextClass = null;
			for(InternalElement currClass : currClasses) {
				nextClass = checkInternalElementForChild(currClass, partsOfPath[i]);
				if(nextClass != null) {
					break;
				}
			}	
			if(nextClass == null) {
				if(i == partsOfPath.length) {
					return ErrorCode.LAST_SEGMENT_NOT_FOUND;
				} else {
					return ErrorCode.SEGMENT_NOT_FOUND;
				}
			} else {
				if(i == partsOfPath.length) {
					return ErrorCode.INTERFACE_FOUND;
				} else {
					currClasses = nextClass.getInternalElement();
				}
			}
		}
		return ErrorCode.SEGMENT_NOT_FOUND;
	}
	
	private static SystemUnitClass checkSystemClassForChild(SystemUnitClass obj, String name) {
		for(SystemUnitClass currObj : obj.getSystemUnitClass()) {
			if(currObj.getName().equals(name)) {
				return currObj;
			}
		}
		return null;
	}
	
	private static RoleClass checkRoleClassForChild(RoleClass obj, String name) {
		for(RoleClass currObj : obj.getRoleClass()) {
			if(currObj.getName().equals(name)) {
				return currObj;
			}
		}
		return null;
	}
	
	private static InterfaceClass checkInterfaceClassForChild(InterfaceClass obj, String name) {
		for(InterfaceClass currObj : obj.getInterfaceClass()) {
			if(currObj.getName().equals(name)) {
				return currObj;
			}
		}
		return null;
	}
	
	private static InternalElement checkInternalElementForChild(InternalElement obj, String name) {
		for(InternalElement currObj : obj.getInternalElement()) {
			if(currObj.getName().equals(name)) {
				return currObj;
			}
		}
		return null;
	}
	
	public static enum ErrorCode {
		EMPTY_STRING_OR_FILE,
		BREAK_RULE,
		SEGMENT_NOT_FOUND,
		LAST_SEGMENT_NOT_FOUND,
		WRONG_TYPE,
		INTERFACE_FOUND
	}
}
