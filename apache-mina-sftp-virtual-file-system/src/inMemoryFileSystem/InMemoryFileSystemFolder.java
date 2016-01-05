package inMemoryFileSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryFileSystemFolder extends InMemoryFileSystemItem {
	private HashMap<String, InMemoryFileSystemItem> dir;
	
	public InMemoryFileSystemFolder(String name, InMemoryFileSystemItem parent) {
		super(name, parent);
		this.dir = new HashMap<String, InMemoryFileSystemItem>();
	}

	public InMemoryFileSystemFolder(String name) {
		super(name);
		this.dir = new HashMap<String, InMemoryFileSystemItem>();
	}

	@Override
	public boolean isDirectory() {
		return true;
	}

	@Override
	public boolean isFile() {
		return false;
	}

	@Override
	public long getSize() {
		return 0;
	}
	
	public void addItem(InMemoryFileSystemItem item) {
		item.setParent(this);
		this.dir.put(item.getName(), item);
	}
	
	public InMemoryFileSystemItem getItem(String name) {
		if(name.equals(".")) {
			return this;
		}
		if(name.equals("..") && !this.isRoot()) {
			return this.getParent();
		}
		return this.dir.get(name);
	}
	
	public InMemoryFileSystemItem removeItem(String name) {
		return this.dir.remove(name);
	}
	
	public List<InMemoryFileSystemItem> getContent() {
		return new ArrayList<InMemoryFileSystemItem>(this.dir.values());
	}
}
