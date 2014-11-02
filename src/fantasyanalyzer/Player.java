package fantasyanalyzer;



public class Player {
	String position, name, opponent;
	double projection;
	int salary;
	
	public Player(String position, String name, String opponent, double projection, int salary) {
		this.position = position;
		this.name = name;
		this.opponent = opponent;
		this.projection = projection;
		this.salary = salary;
	}
}
