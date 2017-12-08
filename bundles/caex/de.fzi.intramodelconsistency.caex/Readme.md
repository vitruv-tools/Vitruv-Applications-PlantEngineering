# CAEX Intramodel Consistency with Vitruvius

Within the Integrate project we resarch the capabilities of the Vitruvius Framework to reinstate model consistency after a change to a caex model. This project contains defined reactions for Vitruvius.

Currently we support the following use cases that are structured as follows:
#### File IC_ReactToSystemUnitClassChanges.reaction

|Original Change of SystemUnitClass|Consequential Change of Clone|Reaction|State|
|------|----|----|--|
| Change of _name_ | Update of _RefBaseSystemUnitPath_ for direct clones and clones of children of SystemUnitClass (recursive)| NameChanged | :heavy_check_mark: |
| Change of _changeMode_ | Update of _changeMode_ | ChangeModeChanged | :heavy_check_mark: |
| New Attribute inserted | Addition of respective Attribute | AttributeAdded | :heavy_check_mark: |
| Attribute deleted | Deletion of respective Attribute |  | :heavy_minus_sign: |
| New InternalElement inserted | Addition of respective InternalElement | InternalElementAdded | :heavy_minus_sign:|
| New AdditionalInformation inserted | Addition of respective AdditionalInformation | AdditionalInformationAdded | :heavy_minus_sign:|
| Change of location | Update of _RefBaseSystemUnitPath_ |  | :heavy_minus_sign:|
| Deletion of Prototype	| Deletion of clone | SystemUnitClassDeleted | :heavy_minus_sign: |

#### File IC_ReactToAttributeChanges.reaction
|Original Change of InternalElement|Consequential Change of Clone|Method|State|
|------|----|----|--|
| Change of _name_ | Update of _name_ | AttributeNameChange | :heavy_check_mark: |
| Change of _attributeDataType_ | Update of _attributeDataType_ | AttributeDataTypeChange | :heavy_check_mark: |
| Change of _changeMode_ | Update of _changeMode_ | AttributeChangeModeChange | :heavy_check_mark: |
| Change of _defaultValue_ | Update of _defaultValue_ | AttributeDefaultValueChange | :heavy_check_mark: |
| Change of _iD_ | Update of _iD_ | AttributeIDChange | :heavy_check_mark: |
| Change of _unit_ | Update of _unit_ | AttributeUnitChange | :heavy_check_mark: |
| Change of _value_ | Update of _value_ | AttributeValueChange | :heavy_check_mark: |
