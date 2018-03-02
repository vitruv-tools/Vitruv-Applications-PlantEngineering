# Vitruv-Applications-PlantEngineering
Vitruv applications repository for plant engineering.

The application repository shall cover the following consistency-preservations: 
1. Intra model-consistency within CAEX
2. Inter model-consistency between CAEX, COLLADA and PLCopen

# Setup
1. Download [https://www.eclipse.org/downloads/packages/eclipse-ide-java-and-dsl-developers/neon3 Eclipse Neon]
2. Install only the following features from the [https://vitruv-tools.github.io/updatesite/nightly Vitruv update site] (Help -> Install new software)
  * Vitruv Consistency Specification DSLs
  * Vitruv Core Framework
  * Vitruv Extension: DSLs Runtime
3. Clone the [https://github.com/kit-sdq/CAEXMetamodel3.0.git CAEX metamodel repository] and import all projects (Right click in the Eclipse ''Package Explorer'' view: ''Import -> Git -> Projects from Git'')
4. Clone the [https://github.com/kit-sdq/PLCOpenMetamodel.git PLCOpen metamodel repository] and import all projects
5. Clone the [https://github.com/vitruv-tools/Vitruv-Domains.git Vitruv-Domains repository] and import only all projects in the following subfolder of the "bundles" folder
  * caex
  * plcopen
6. Run a new Eclipse Application (Run - Run Configurations), all plug-ins are automatically deployed in this instance
7. Clone the current Github repository and import all projects
8. Again, run a new Eclipse Application where you can create your AutomationML models with the EMF tree-based editor and to which consistency-preserving rules are automatically applied if particular changes occur. 

# Creation of a CAEX model with the 

Currently, an exemplary CAEX model is created automatically. You can either change the model or delete the automatically created folders and create a newly CAEX model. Therefore, you need to first create an empty project (File > New > Project... > General/Project). Within this project, you can create a new CAEX model by selecting File > New > Other... >  CAEX/CAEX 3.0 Model. The newly created CAEX model will be immediately opened in the tree-based editor. It will contain already the root element CAEX File. Use the context menu New Child to add further elements to your CAEX model as well as the Properties view to set attributes for the elements.

The implementation of consistency-preserving rules in CAEX is on-going work. The implmentation of inter model-consistency rules between CAEX, COLLADA and PLCopen will start from May 2018.
