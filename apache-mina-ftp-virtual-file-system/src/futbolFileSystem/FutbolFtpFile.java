package futbolFileSystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ftpserver.ftplet.FtpFile;

public class FutbolFtpFile implements FtpFile {
	private String path;
	private String[] pathParts;
	private String ownerName;
	private String groupName;
	private Futbol futfile;
	
	public FutbolFtpFile(String path) {
		this.path = path;
		if(this.path.charAt(0) == '.'){
			this.path = this.path.substring(1);
		}
		this.pathParts = this.path.split("/");
		this.ownerName = "user";
		this.groupName = "group";
	}
	
	private boolean isRoot() {
		return this.pathParts.length == 0;
	}
	
	@Override
	public boolean isDirectory() {
		if(isRoot()) {
			return true;
		}
		
		//return futfile.getType() == FutbolTypes.TEAM;
		return this.pathParts.length == 2;
	}
	
	private boolean isTeam() {
		return this.isDirectory() && !isRoot();
	}

	@Override
	public boolean isFile() {
		if(isRoot()) {
			return false;
		}
		
		//return futfile.getType() == FutbolTypes.PLAYER;
		return this.pathParts.length >= 3;
	}
	
	@Override
	public boolean doesExist() {
		if(this.isFile()) {
			if(!FutbolFileSystem.hasTeam(this.pathParts[1])) {
				return false;
			}
			
			return FutbolFileSystem.getTeam(pathParts[1]).hasPlayer(pathParts[2]);
		} else if(isTeam()) {
			return FutbolFileSystem.hasTeam(pathParts[1]);
		} else if(isRoot()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean delete() {
		if(this.doesExist()) {
			if(this.isFile()) {
				return FutbolFileSystem.getTeam(pathParts[1]).deletePlayer(pathParts[2]);
			} else if(isTeam()) {
				return FutbolFileSystem.deleteTeam(pathParts[1]);
			}
		}
		
		return false;
	}
	
	@Override
	public long getSize() {
		if(this.doesExist()) {
			if(this.isFile()) {
				return 1;
			} else if(isTeam()) {
				return FutbolFileSystem.getTeam(pathParts[1]).count();
			} else if(isRoot()) {
				return FutbolFileSystem.count();
			}
		}
		
		return 0;
	}
	
	@Override
	public boolean mkdir() {
		if(this.isFile()) {
			return false;
		} else if(isTeam() && !this.doesExist()) {
			this.futfile = new Team(this.pathParts[1]);
			FutbolFileSystem.addTeam((Team) this.futfile);
			return true;
		}
		
		return false;
	}
	
	@Override
	public List<FtpFile> listFiles() {
		List<FtpFile> fileList = new ArrayList<FtpFile>();
		
		if(isRoot()) {
			List<Team> teamList = new ArrayList<Team>(FutbolFileSystem.getAllTeams().values());
			for (Team t : teamList) {
				fileList.add(new FutbolFtpFile("/" + t.getName()));
			}
		} else if(isTeam()) {
			List<Player> playerList = new ArrayList<Player>(FutbolFileSystem.getTeam(pathParts[1]).getAllPlayers().values());
			for (Player p : playerList) {
				fileList.add(new FutbolFtpFile("/" + pathParts[1] + "/" + p.getName()));
			}
		} else {
			return null;
		}
		
		return fileList;
	}
	
	@Override
	public String getGroupName() {
		return this.groupName;
	}
	
	@Override
	public String getName() {
		if (this.path.equals("/")) {
			return "/";
		}

		String shortName = this.path;
		int filelen = this.path.length();
		if (shortName.charAt(filelen - 1) == '/') {
			shortName = shortName.substring(0, filelen - 1);
		}

		int slashIndex = shortName.lastIndexOf('/');
		if (slashIndex != -1) {
			shortName = shortName.substring(slashIndex + 1);
		}
		
		return shortName;
	}

	@Override
	public String getAbsolutePath() {
		String fullName = this.path;
		int filelen = fullName.length();
		
		if ((filelen != 1) && (fullName.charAt(filelen - 1) == '/')) {
			fullName = fullName.substring(0, filelen - 1);
		}
    
		return fullName;
	}
	
	@Override
	public String getOwnerName() {
		return this.ownerName;
	}
	
	@Override
	public boolean isHidden() {
		return false;
	}

	@Override
	public boolean isReadable() {
		return true;
	}

	@Override
	public boolean isRemovable() {
		return true;
	}

	@Override
	public boolean isWritable() {
		return true;
	}

	@Override
	public int getLinkCount() {
		return 0;
	}

	@Override
	public long getLastModified() {
		return 0;
	}

	@Override
	public boolean setLastModified(long paramLong) {
		return false;
	}

	@Override
	public boolean move(FtpFile paramFtpFile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OutputStream createOutputStream(long paramLong) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream createInputStream(long paramLong) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
