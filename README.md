#nuxeo-admin-panel-log

#Synopsis

This plugin allows to download the file "server.log" of the day that the admin user wants to. To do this, I created a sub-tab in the administration area which contains a textbox where the admin user must enter the date of the "server.log" that he wants to download. If no date is entered, when the admin user clicks the button to download, the "server.log" of the current day is downloaded.

#Installation

You just have to compile the pom.xml using Maven and deploy the plugin in. To do this, you must use the following script:

	cd nuxeo-admin-panel-log-master
	mvn clean install
	cp target/athento-nx-admin-log-*.jar $NUXEO_HOME/nxserver/plugins

And then, restart your nuxeo server and enjoy.
