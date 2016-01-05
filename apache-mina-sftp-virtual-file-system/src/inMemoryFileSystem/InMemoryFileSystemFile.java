package inMemoryFileSystem;

public class InMemoryFileSystemFile extends InMemoryFileSystemItem {
	private String content;
	
	public InMemoryFileSystemFile(String name, String content, InMemoryFileSystemItem parent) {
		super(name, parent);
		this.setContent(content);
	}

	public InMemoryFileSystemFile(String name, String content) {
		super(name);
		this.setContent(content);
	}
	
	@Override
	public boolean isDirectory() {
		return false;
	}

	@Override
	public boolean isFile() {
		return true;
	}

	@Override
	public long getSize() {
		return content.length();
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
