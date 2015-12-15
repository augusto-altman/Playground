import java.io.File;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.SaltedPasswordEncryptor;
import org.apache.ftpserver.usermanager.impl.BaseUser;

import futbolFileSystem.FutbolFileSystem;
import futbolFileSystem.FutbolFileSystemFactory;
import futbolFileSystem.Player;
import futbolFileSystem.Team;


public final class Main {

	public static void main(String[] args) throws FtpException {
		
		PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
		userManagerFactory.setFile(new File("myusers.properties"));
		userManagerFactory.setPasswordEncryptor(new SaltedPasswordEncryptor());
		UserManager um = userManagerFactory.createUserManager();
		BaseUser user = new BaseUser();
		user.setName("tito");
		user.setPassword("7170");
		user.setHomeDirectory("/");
		//user.setHomeDirectory(".\\tito");
		um.save(user);
		FtpServerFactory serverFactory = new FtpServerFactory();
		serverFactory.setUserManager(um);
	
		FutbolFileSystem.addTeam(new Team("San Lorenzo"));
		FutbolFileSystem.getTeam("San Lorenzo").addPlayer(new Player("Pipi Romagnoli"));
		FutbolFileSystem.getTeam("San Lorenzo").addPlayer(new Player("Buffarini"));
		FutbolFileSystem.getTeam("San Lorenzo").addPlayer(new Player("Torrico"));
		FutbolFileSystem.getTeam("San Lorenzo").addPlayer(new Player("Mas"));
		FutbolFileSystem.addTeam(new Team("Belgrano"));
		FutbolFileSystem.getTeam("Belgrano").addPlayer(new Player("El Chino"));
		FutbolFileSystem.getTeam("Belgrano").addPlayer(new Player("Olave"));
		FutbolFileSystemFactory fs = new FutbolFileSystemFactory();
		serverFactory.setFileSystem(fs);
		
		FtpServer server = serverFactory.createServer();
		
		// start the server
		server.start();
	}

}
