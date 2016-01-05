package inMemoryFileSystem;

import java.io.IOException;

import org.apache.sshd.common.Session;
import org.apache.sshd.common.file.FileSystemFactory;
import org.apache.sshd.common.file.FileSystemView;

public class InMemoryFileSystemFactory implements FileSystemFactory {

	private InMemoryFileSystemFolder home;
	
	public void setHome(InMemoryFileSystemFolder h) {
		this.home = h;
	}
	
	@Override
	public FileSystemView createFileSystemView(Session session) throws IOException {
		return new InMemoryFileSystemView(this.home);
	}

}
