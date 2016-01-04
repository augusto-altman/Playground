import java.util.ArrayList;
import java.util.List;

import org.apache.sshd.SshServer;
import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.UserAuth;
//import org.apache.sshd.server.auth.UserAuthNone;
import org.apache.sshd.server.auth.UserAuthPassword;
import org.apache.sshd.server.command.ScpCommandFactory;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.sftp.SftpSubsystem;


public final class Main {

	public static void main(String[] args) {
	    SshServer sshd = SshServer.setUpDefaultServer();
	    sshd.setPort(22);
	    sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(".\\hostkey.ser"));

	    List<NamedFactory<UserAuth>> userAuthFactories = new ArrayList<NamedFactory<UserAuth>>();
	    //userAuthFactories.add(new UserAuthNone.Factory());
	    userAuthFactories.add(new UserAuthPassword.Factory());
	    sshd.setUserAuthFactories(userAuthFactories);
	    sshd.setPasswordAuthenticator(new Authenticator());
	    sshd.setCommandFactory(new ScpCommandFactory());

	    List<NamedFactory<Command>> namedFactoryList = new ArrayList<NamedFactory<Command>>();
	    namedFactoryList.add(new SftpSubsystem.Factory());
	    sshd.setSubsystemFactories(namedFactoryList);
	    VirtualFileSystemFactory vfsFactory = new VirtualFileSystemFactory();
	    vfsFactory.setUserHomeDir("tito", "C:\\Users\\aaquaran\\Downloads\\apache-mina-sftp-virtual-file-system\\apache-mina-sftp-virtual-file-system\\titoFolder");
	    sshd.setFileSystemFactory(vfsFactory);

	    try {
	        sshd.start();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
