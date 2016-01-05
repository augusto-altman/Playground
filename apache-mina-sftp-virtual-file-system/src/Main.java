import java.util.ArrayList;
import java.util.List;

import org.apache.sshd.SshServer;
import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.UserAuth;
import org.apache.sshd.server.auth.UserAuthPassword;
import org.apache.sshd.server.command.ScpCommandFactory;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.sftp.SftpSubsystem;

import inMemoryFileSystem.InMemoryFileSystemFactory;
import inMemoryFileSystem.InMemoryFileSystemFile;
import inMemoryFileSystem.InMemoryFileSystemFolder;


public final class Main {

	public static void main(String[] args) {
		//Basic configs
	    SshServer sshd = SshServer.setUpDefaultServer();
	    sshd.setPort(22);
	    sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(".\\hostkey.ser"));

	    //Client authentication configs
	    List<NamedFactory<UserAuth>> userAuthFactories = new ArrayList<NamedFactory<UserAuth>>();
	    //userAuthFactories.add(new UserAuthNone.Factory()); //Allows anonymous connections
	    userAuthFactories.add(new UserAuthPassword.Factory());
	    sshd.setUserAuthFactories(userAuthFactories);
	    sshd.setPasswordAuthenticator(new Authenticator());
	    sshd.setCommandFactory(new ScpCommandFactory());

	    //Establish this SSH session as a secure FTP connection
	    List<NamedFactory<Command>> namedFactoryList = new ArrayList<NamedFactory<Command>>();
	    namedFactoryList.add(new SftpSubsystem.Factory());
	    sshd.setSubsystemFactories(namedFactoryList);
	    
	    //Create the virtual in memory file system (hashmap based)
	    InMemoryFileSystemFolder sanlorenzo = new InMemoryFileSystemFolder("sanlorenzo");
	    sanlorenzo.addItem(new InMemoryFileSystemFile("buffarini", "el buffa"));
	    sanlorenzo.addItem(new InMemoryFileSystemFile("romagnoli", "el pipi"));
	    sanlorenzo.addItem(new InMemoryFileSystemFile("torrico", "el cordor"));
	    
	    InMemoryFileSystemFolder sanlorenzoreserva = new InMemoryFileSystemFolder("reserva");
	    sanlorenzoreserva.addItem(new InMemoryFileSystemFile("altman quaranta", "tito"));
	    sanlorenzo.addItem(sanlorenzoreserva);
	    
	    InMemoryFileSystemFolder belgrano = new InMemoryFileSystemFolder("belgrano");
	    belgrano.addItem(new InMemoryFileSystemFile("zellarrayan", "el chino"));
	    
	    InMemoryFileSystemFolder root = new InMemoryFileSystemFolder("root");
	    root.addItem(sanlorenzo);
	    root.addItem(belgrano);
	    root.addItem(new InMemoryFileSystemFile("tempo", "push the tempo"));
	    
	    //Establish the recently created in memory file system as the SFTP file system
	    InMemoryFileSystemFactory fs = new InMemoryFileSystemFactory();
	    fs.setHome(root);
	    sshd.setFileSystemFactory(fs);

	    try {
	        sshd.start();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
