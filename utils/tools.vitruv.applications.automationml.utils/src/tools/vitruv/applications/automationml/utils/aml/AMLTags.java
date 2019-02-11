package tools.vitruv.applications.automationml.utils.aml;

/**
 * class with strings, which can be used as Tags for the Correspondence-Model 
 * @author A.Lang
 */
public class AMLTags {
	public static final String INTERFACE_SCENE = "TagForExternalInterfaceAndScene";				// Verkn�pfung CAEX zu Collada
	public static final String INTERFACE_POU = "TagForExternalInterfaceAndPou";					// Verkn�pfung CAEX zu PLCopen
	public static final String CLASS_INTERFACE = "TagForExternalInterfaceAndInterfaceClass";
	public static final String PROTOTYPE_CLONE = "TagForSystemUnitClassAndInternalElement";		// Prototyp-Clone Beziehung
	public static final String AML_CAEX = "TagForContainerAndCAEXFile";
	public static final String AML_COLLADA = "TagForContainerAndColladaFile";
	public static final String AML_PLCOPEN = "TagForContainerAndPLCopenFile";
	public static final String SCENE_VISUAL = "TagForSceneAndVisualScene";
	public static final String GEOMETRY_INSTANCE = "TagForGeometryAndInstance";
	public static final String ROLE_ROLE = "TagForRoleAndInheritance";
	public static final String CLONE_ROLE = "TagForCloneAndHisRole";
}
