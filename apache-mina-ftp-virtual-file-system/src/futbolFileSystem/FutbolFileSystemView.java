package futbolFileSystem;

import org.apache.ftpserver.ftplet.FileSystemView;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.FtpFile;

public class FutbolFileSystemView implements FileSystemView  {
	private String currentDir = "/";
	
	@Override
	public boolean changeWorkingDirectory(String dir) throws FtpException {
		this.currentDir = dir;
		return true;
	}

	@Override
	public void dispose() {}

	@Override
	public FtpFile getFile(String path) throws FtpException {
		if(path.equals("./")) {
			return new FutbolFtpFile(this.currentDir);
		}
		return new FutbolFtpFile(this.currentDir + path);
	}

	@Override
	public FtpFile getHomeDirectory() throws FtpException {
		return new FutbolFtpFile("/");
	}

	@Override
	public FtpFile getWorkingDirectory() throws FtpException {
		return new FutbolFtpFile(this.currentDir);
	}

	@Override
	public boolean isRandomAccessible() throws FtpException {
		return false;
	}

}
