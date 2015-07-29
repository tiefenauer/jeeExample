# JEE Example
Full-Stack JEE Application with examples for the following JEE-APIs:
* JPA: Persisting data in a relational database
* JAX-RS: RESTful Service to store/retrieve the data from the database

## Technologies used
* Apache Wildfly as JEE Application Server
* Apache Derby as database
* Hibernate as ORM

# Getting started
Before you can run the application, there are a few things to be set up.

## Software
This project relies on you having installed the following tools. Download and install them at a location of your choice. If you're using Windows, it is recommended *NOT* to install them in the Programs directory, since this is write-protected by default!
Note where you installed each tool. In the following descriptions, the installation path of each tool is referenced by its placeholder.

| Tool                      | Version           | Link                          | Installation path placeholder |
|---------------------------|-------------------|-------------------------------|-------------------|
| Eclipse Mars IDE          | Mars              | http://www.eclipse.org/home/index.php | eclipse.home |
| - m2e Plugin              | latest            | http://www.eclipse.org/m2e/   | |
| - JBoss Tools             | 4.2.3.Final       | Eclipse Marketplace           | |
| Apache Wildfly            | 8.2.1.Final       | http://wildfly.org/downloads/ | wildfly.home |
| Apache Derby              | 10.11.1.1         | http://db.apache.org/derby/   | derby.home |
| Apache Maven              | lastest           | https://maven.apache.org/     | maven.home |
| Java SE JDK               | j8u45             | http://www.oracle.com/technetwork/java/javase/downloads/index.html | java.home |

## Environment variables
You need to define a few environmental variables in order to make things work. Not all of them are really needed, but it won't hurt if they're there.

| Variable name   | Variable value  |
|-----------------|-----------------|
| ECLIPSE_HOME    | eclipse.home    |
| DERBY_HOME      | derby.home      |
| JAVA_HOME       | java.home       |
| JBOSS_HOME      | wildfly.home    |
| MAVEN_HOME      | maven.home      |

Optionally, you can add the following to your PATH. This will make Maven, Wildfly and Derby tools available from the command line (in case you need it).
%MAVEN_HOME%\bin;%JBOSS_HOME%\bin;%DERBY_HOME%\bin

## Eclipse configuration
To start/stop Wildfly from Eclipse and deploy the application to the server, switch to the JavaEE-Perspective and add Wildfly as a new Server.

## Wildfly configuration
You can install Derby as embedded database (where Derby runs in the same process as the Wildfly-JVM) or as a network server (where Derby runs in its own process). You can find out more about the difference between embedded and network mode [here](http://db.apache.org/derby/papers/DerbyTut/embedded_intro.html). 
Because Derby does not allow multiple connections when running in embedded mode, we will install Derby as a network server.

### JDBC Driver installation
In order to use Derby as the underlying database, you need to install the JDBC-Drivers as a module in Wildfly. To do so, you need to follow these steps

1. create a folder in wildfly.home/modules/org/apache/derbynetwork/main. 
2. copy derby.home/lib/derbyclient.jar to wildfly.home/modules/org/apache/derbynetwork/main
3. create wildfly.home/modules/org/apache/derbynetwork/main/module.xml with the following content
```xml
<?xml version="1.0" ?>
<module xmlns="urn:jboss:module:1.1" name="org.apache.derbynetwork">
    <resources>
        <resource-root path="derbyclient.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
    </dependencies>
</module>
```
4. Install the driver in wildfly.home/standalone/configuration/standalone.xml
```xml
<?xml version="1.0" ?>
<server xmlns="urn:jboss:domain:2.2">
...
    <profile>
    ...
        <subsystem xmlns="urn:jboss:domain:datasources:2.0">
            <datasources>
            ...
                <drivers>
                ...
                    <driver name="derby-network" module="org.apache.derbynetwork">
                        <xa-datasource-class>org.apache.derby.jdbc.ClientXADataSource</xa-datasource-class>
                    </driver>
                </drivers>
            </datasources>
        </subsystem>
    <profile>
</server>
```
### Datasource
Start/Restart Wildfly using Eclipse. Navigate to http://localhost:9990/console/App.html#datasources and add a new datasource with the following attributes:
- Name: JEEExampleDS
- JNDI Name: java:/JEEExampleDS
- Driver: derby-network (the installed driver should be automatically detected)
- Connection URL: jdbc:derby://localhost:1527/jeeexampledata;create=true
- Username: app
- Password: app
- Security Domain: (empty)

## Postman-Configurations
[Postman](https://www.getpostman.com/)-Templates to test the API are available under the following path:
https://www.getpostman.com/collections/7373d107297b565d497c
