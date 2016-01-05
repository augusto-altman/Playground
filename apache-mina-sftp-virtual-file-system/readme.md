Creating a java based SFTP service using an in-memory virtual file system
=========

The idea of this POC is to expose a secured FTP (over SSH or SFTP) service that consumes an *in-memory virtual file system* instead of the OS native file system. In this case, the file system is composed by item that can be *virtual files* or *virtual folders*. The *virtual files* are just POJOs whose content is a Strig. The *virtual folders* are just HashMaps whose content are other items.

The project was coded using Java and the [Apache Mina SSHD library](https://mina.apache.org/sshd-project/index.html). Its dependencies are directly included in the repo on the **/lib** folder since I haven't created a gradle/maven/ant configuration for this POC.

Info
---------

The credentials for logging into the SFTP server are hardcoded in the **Authenticator** class. Just FYI they are *username = tito* and *password = 7170*.

For testing I recommend using [FileZilla](https://filezilla-project.org/) as a SFTP client. When using it remember to refresh (f5) the remote directory in order to get the newest data and to discard the local cache.
