package fantasyanalyzer;

import java.text.DecimalFormat;
import java.util.Random;

public class LineupGenerator {


	public static void generateInitialLineup(DataCollector data) {
		int cost = 0; 
		Player[] playerSet = new Player[9];
		while ((cost < 40000)) {
			if ((cost > 50000) || (cost < 40000)) {
				cost = 0;
				playerSet[0] = data.quarterbacks.get(generateRandomIndex(data.quarterbacks.size()-1));
				playerSet[1] = data.runningbacks.get(generateRandomIndex(data.runningbacks.size()-1));
				playerSet[2] = data.runningbacks.get(generateRandomIndex(data.runningbacks.size()-1));
				playerSet[3] = data.widereceivers.get(generateRandomIndex(data.widereceivers.size()-1));
				playerSet[4] = data.widereceivers.get(generateRandomIndex(data.widereceivers.size()-1));
				playerSet[5] = data.widereceivers.get(generateRandomIndex(data.widereceivers.size()-1));
				playerSet[6] = data.tightends.get(generateRandomIndex(data.tightends.size()-1));
				playerSet[7] = data.defenses.get(generateRandomIndex(data.defenses.size()-1));
				playerSet[8] = data.flex.get(generateRandomIndex(data.flex.size()-1));
				for (int i = 0; i < playerSet.length; i++) {
					cost += playerSet[i].salary;
				}
			}
		}
		double points = 0;
		for (int i = 0; i < playerSet.length; i++) {
			points += playerSet[i].projection;
		}
		printLineup(playerSet);
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println ("\nThe starting number of points is " + df.format(points) + " with a cost of " + cost);
	}

	private static int generateRandomIndex(int limit) {
		Random rand = new Random();
		int randomNum = rand.nextInt(limit + 1);
		return randomNum;
	}

	public static void printLineup(Player[] playerSet) {
		for (int i = 0; i < playerSet.length; i++) {
			System.out.println(playerSet[i].toString());
		}
	}
	
	public void simulatedAnnealing(DataCollector data) {
		
	}
	
	public void hillClimbing(DataCollector data) {
	
	}
	
	public void geneticAlgorithm(DataCollector data) {
		
	}

}



