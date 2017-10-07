# Vitruv-Applications-PlantEngineering
Vitruv applications repository for plant engineering

# Setup
1. Download [https://www.eclipse.org/downloads/packages/eclipse-ide-java-and-dsl-developers/neon3 Eclipse Neon]
2. Install only the following features from the [https://vitruv-tools.github.io/updatesite/nightly Vitruv update site] (Help -> Install new software)
  * Vitruv Consistency Specification DSLs
  * Vitruv Core Framework
  * Vitruv Extension: DSLs Runtime
3. Clone the [https://github.com/kit-sdq/CAEXMetamodel.git CAEX metamodel repository] and import all projects (Right click in the Eclipse ''Package Explorer'' view: ''Import -> Git -> Projects from Git'')
4. Clone the [https://github.com/kit-sdq/PLCOpenMetamodel.git PLCOpen metamodel repository] and import all projects
5. Clone the [https://github.com/vitruv-tools/Vitruv-Domains.git Vitruv-Domains repository] and import only all projects in the following subfolder of the "bundles" folder
  * caex
  * plcopen
6. Run a new Eclipse Application (Run - Run Configurations), all plug-ins are automatically deployed in this instance
7. Open the Package-Explorer View
8. Right-Click in the empty workspace and choose "Generate AML Demo" (or similar :-)
