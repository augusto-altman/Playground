package inMemoryFileSystem;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sshd.common.file.SshFile;

public class InMemorySshFile implements SshFile {
	InMemoryFileSystemItem item;
	Map<SshFile.Attribute, Object> attributes;
	
	public InMemorySshFile(InMemoryFileSystemItem i) {
		this.item = i;
        attributes = new HashMap<SshFile.Attribute, Object>();
        attributes.put(SshFile.Attribute.Owner, "owner");
        attributes.put(SshFile.Attribute.Group, "group");
        attributes.put(SshFile.Attribute.Size, this.getSize());
        attributes.put(SshFile.Attribute.IsDirectory, this.isDirectory());
        attributes.put(SshFile.Attribute.IsSymbolicLink, false);
        attributes.put(SshFile.Attribute.IsRegularFile, this.isFile());
        attributes.put(SshFile.Attribute.Permissions, EnumSet.noneOf(SshFile.Permission.class));
        attributes.put(SshFile.Attribute.LastModifiedTime, (long) 0);
	}

	@Override
	public String getAbsolutePath() {
		return this.item.getAbsolutePath();
	}

	@Override
	public String getName() {
		return this.item.getName();
	}

	@Override
	public Map<Attribute, Object> getAttributes(boolean followLinks) throws IOException {
		return attributes;
	}

	@Override
	public void setAttributes(Map<Attribute, Object> attributes) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getAttribute(Attribute attribute, boolean followLinks) throws IOException {
		return attributes.get(attribute);
	}

	@Override
	public void setAttribute(Attribute attribute, Object value) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String readSymbolicLink() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void createSymbolicLink(SshFile destination) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getOwner() {
		return null;
	}

	@Override
	public boolean isDirectory() {
		return this.item.isDirectory();
	}

	@Override
	public boolean isFile() {
		return this.item.isFile();
	}

	@Override
	public boolean doesExist() {
		return true;
	}

	@Override
	public boolean isReadable() {
		return true;
	}

	@Override
	public boolean isWritable() {
		return true;
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isRemovable() {
		return true;
	}

	@Override
	public SshFile getParentFile() {
		if(!this.item.isRoot())
			return new InMemorySshFile(this.item.getParent());
		else
			return null;
	}

	@Override
	public long getLastModified() {
		return 0;
	}

	@Override
	public boolean setLastModified(long time) {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getSize() {
		return this.item.getSize();
	}

	@Override
	public boolean mkdir() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean create() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void truncate() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean move(SshFile destination) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<SshFile> listSshFiles() {
		if (!this.item.isDirectory()) {
            return null;
        }
		
		@SuppressWarnings("unchecked")
		ArrayList<InMemoryFileSystemItem> list = (ArrayList<InMemoryFileSystemItem>) this.item.getContent();
		ArrayList<SshFile> result = new ArrayList<SshFile>();
		
		for (InMemoryFileSystemItem i : list) {
			result.add(new InMemorySshFile(i));
		}
		
		return result;
	}

	@Override
	public OutputStream createOutputStream(long offset) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public InputStream createInputStream(long offset) throws IOException {
		if(!this.item.isFile()) {
			return null;
		}
		InMemoryFileSystemFile f = (InMemoryFileSystemFile) this.item;
		return new ByteArrayInputStream( f.getContent().getBytes() );
	}

	@Override
	public void handleClose() throws IOException {
		// Noop
	}

}
