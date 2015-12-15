package futbolFileSystem;

public class Player extends Futbol { //Player POJO
	
	private String position;
	private int age;
	
	public Player(String name) {
		super(name);
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public FutbolTypes getType() {
		return FutbolTypes.PLAYER;
	}
}
