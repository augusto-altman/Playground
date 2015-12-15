Creating a java based FTP service using a virtual file system
=========

The idea of this POC is to expose an FTP service that consumes a *virtual file system* instead of the OS native file system. In this case, the virtual file system is just a static class ([FutbolFileSystem](https://github.com/augusto-altman/Playground/blob/master/apache-mina-ftp-virtual-file-system/src/futbolFileSystem/FutbolFileSystem.java)) that encapsulates a HashMap of futbol teams and their players. Each team is considered a *virtual directory* and each team's player is considered a *virtual file*. For this POC request concurrency is not allowed since there is no lock over the HashMap (nevertheless it would be very easy to refactor the FutbolFileSystem class in order to support concurrent requests). Also, file (players) reading and writting functionallity is not implemented.

The project was coded using Java and the [Apache Mina library](https://mina.apache.org/ftpserver-project/features.html). Its dependencies are directly included in the repo on the **/lib** folder since I haven't created a gradle/maven/ant configuration.

Environment setup
---------

*   Install [Eclipse](https://eclipse.org/downloads/).
*   Add the jars located in the **/lib** folder into the classpath.
*   Download and install the [FileZilla](https://filezilla-project.org/) client. You will use it for testing your service.

Info
---------

The credentials for logging into the FTP server are hardcoded in the [Main](https://github.com/augusto-altman/Playground/blob/master/apache-mina-ftp-virtual-file-system/src/Main.java) class. Just FYI they are *username = tito* and *password = 7170*.

If you want to run the FTP service over the OS native file system just swap the comments between these lines:

```java
user.setHomeDirectory("/");
//user.setHomeDirectory(".\\tito");
```

And set the home directory to whatever path you want.

When using FileZilla for testing your service remember to refresh (f5) the remote directory in order to get the newest data and to discard the local cache.