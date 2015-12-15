package futbolFileSystem;

public abstract class Futbol {

	protected String name;
	protected String country;
	protected String rating;
	
	public Futbol(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public abstract FutbolTypes getType();
}
