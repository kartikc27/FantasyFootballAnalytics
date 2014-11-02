package fantasyanalyzer;

import java.text.DecimalFormat;



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
	
	public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        if (name.length() < 15)
        	return (name + "\t\t\t" + position + "\t" + df.format(projection) + "\t" + salary);
        else
        	return (name + "\t\t" + position + "\t" + df.format(projection) + "\t" + salary); 
	}
}
