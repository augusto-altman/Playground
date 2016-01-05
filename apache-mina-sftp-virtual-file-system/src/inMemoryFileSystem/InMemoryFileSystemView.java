package inMemoryFileSystem;

import org.apache.sshd.common.file.FileSystemView;
import org.apache.sshd.common.file.SshFile;

public class InMemoryFileSystemView implements FileSystemView {
	
	InMemoryFileSystemFolder home;
	String rootName;
	int i = 0;
	
	public InMemoryFileSystemView(InMemoryFileSystemFolder home) {
		this.home = home;
		this.home.setName("");
		this.rootName = "/";
	}

	@Override
	public SshFile getFile(String file) {	
		String dir = file;
		if(!file.startsWith(this.rootName)) {
			dir = this.rootName + file;
		}
		if(dir.equals(this.rootName) || dir.equals("/.")){
			return new InMemorySshFile(this.home);
		}
		
		String[] pathParts = dir.split("/");
		InMemoryFileSystemFolder current = this.home;
		int i;
		for(i = 1; i < pathParts.length - 1; i++) {
			if(!current.getItem(pathParts[i]).isDirectory()){
				return null;
			}
			current = (InMemoryFileSystemFolder) current.getItem(pathParts[i]);
		}
		return new InMemorySshFile(current.getItem(pathParts[i]));
	}

	@Override
	public SshFile getFile(SshFile baseDir, String file) {
		return getFile(baseDir.getAbsolutePath() + file);
	}
	
	@Override
	public FileSystemView getNormalizedView() {
		return this;
	}

}
