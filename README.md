# Xml External Provider

### INFORMATION
This module allow you to create a mount point in your Digital Factory in order to search your data on a local Xml File.
Use this module as a tutorial or guide to do your own implementation.

### MINIMAL REQUIREMENTS
* Digital Factory 7.1.0.1
* DF Module - External data provider V3.0.1 
* **Important:** to use this module you must create yourself a xml file in your instance.  
You will find the example file to use [here](https://github.com/Jahia/xml-provider/blob/master/xml/edp-activity.xml)

### INSTRUCTIONS
After install the module, you need to add it to a site.
In the site settings tab you can find the option "Xml File Connector" and you need to configure the local path to the xml file.
You can use the "Xml Activities Component" to show the provided data under /mounts/xml-activities node.