package inMemoryFileSystem;

public abstract class InMemoryFileSystemItem {
	private String name;
	private InMemoryFileSystemItem parent;
	
	public InMemoryFileSystemItem(String name) {
		super();
		this.setName(name);
		this.parent = null;
	}

	public InMemoryFileSystemItem(String name, InMemoryFileSystemItem parent) {
		super();
		this.setName(name);
		this.setParent(parent);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public InMemoryFileSystemItem getParent() {
		return parent;
	}

	public void setParent(InMemoryFileSystemItem parent) {
		this.parent = parent;
	}

	public boolean isRoot(){
		return this.getParent() == null;
	}
	
	public String getAbsolutePath(){
		if(this.isRoot()) {
			return "/" + this.getName() + "/";
		} else {
			return this.getParent().getAbsolutePath() + this.getName() + "/";
		}
	}
	
	public abstract boolean isDirectory();
	public abstract boolean isFile();
	public abstract long getSize();
	public abstract Object getContent();
}
