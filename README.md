# Xml External Provider

### INFORMATION
This module allow you to create a mount point in your Digital Factory in order to search your data on a local Xml File.
Use this module as a tutorial or guide to do your own implementation.

### MINIMAL REQUIREMENTS
* (Digital Factory 7.2.3.1 + DF Module - External data provider V3.1.9) **OR** (Digital Factory 7.3.1.0 + DF Module - External data provider V3.2.2) **OR** Jahia 8.0.x.x + External Data Provider V3.x.x+
* **Important:** to use this module you must create yourself a xml file in your instance.  
You will find the example file to use [here](https://github.com/Jahia/xml-provider/blob/master/src/main/resources/META-INF/xml/edp-activity.xml)

### INSTRUCTIONS
After installing the module, you must go to the administration panel "Mount Points", the mount point type "XML Mount Point"
should be available. Add one, and specify its name, path to the XML file you'd like to import and eventually its local target path
(any content folder, the default path will be /mounts/ if you don't specify one)
You can use the "Xml Activities Component" to show the provided data.

NOTE: The activities id must be __unique__.
