package fantasyanalyzer;

import java.text.DecimalFormat;
import java.util.Random;

public class LineupGenerator {

	public static Player[] generateInitialLineup(DataCollector data, String type) {
		int cost = 0; 
		Player[] playerSet = new Player[9];
		while ((cost < 44000)) {
			if ((cost > 50000) || (cost < 44000)) {
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
				cost = getCost(playerSet);
			}
		}
		double points = 0;
		for (int i = 0; i < playerSet.length; i++) {
			points += playerSet[i].projection;
		}
		printLineup(playerSet, type);
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println ("\nThe starting number of points is " + df.format(points) + " with a cost of " + cost +"\n");
		return playerSet;
	}

	private static int generateRandomIndex(int limit) {
		Random rand = new Random();
		int randomNum = rand.nextInt(limit + 1);
		return randomNum;
	}

	public static void printLineup(Player[] playerSet, String type) {
		if (type.equals("propmonkey")) {
			for (int i = 0; i < playerSet.length-1; i++) {
				//if (i != 7)
					playerSet[i].printExcel();
//				else
//					System.out.println(playerSet[7].toString());
			}	
		}
		else if (type.equals("fantasypros")) {
			for (int i = 0; i < playerSet.length; i++) {
				System.out.println(playerSet[i].toString());
			}
		}
	}

	public static void simulatedAnnealing(DataCollector data, String type)  {
		float temp = Float.MAX_VALUE;
		double coolingRate = 0.0000001;

		Player[] currentLineup = new Player[9];
		currentLineup = generateInitialLineup(data, type);
		Player[] bestLineup = new Player[9];
		bestLineup = currentLineup.clone();
		while (temp > 1)  {
			Player[] newLineup = new Player[9];
			newLineup = currentLineup.clone();
			int positionPos = (int) (newLineup.length * Math.random());
			int playerPos = 0;
			if (positionPos == 0) {
				playerPos = (int) (data.quarterbacks.size() * Math.random());
				if ((newLineup[0] != data.quarterbacks.get(playerPos)))
					newLineup[positionPos]  = data.quarterbacks.get(playerPos);
			}
			if ((positionPos == 1) || (positionPos == 2)) {
				playerPos = (int) (data.runningbacks.size() * Math.random());
				if ((!(newLineup[1].name.equals(data.runningbacks.get(playerPos).name))) && 
						(!(newLineup[2].name.equals(data.runningbacks.get(playerPos).name))))
					newLineup[positionPos]  = data.runningbacks.get(playerPos);
			}
			if ((positionPos == 3) || (positionPos == 4) || (positionPos == 5)) {
				playerPos = (int) (data.widereceivers.size() * Math.random());
				if ((!(newLineup[3].name.equals(data.widereceivers.get(playerPos).name))) && 
						(!(newLineup[4].name.equals(data.widereceivers.get(playerPos).name))) &&
						(!(newLineup[5].name.equals(data.widereceivers.get(playerPos).name))))
					newLineup[positionPos]  = data.widereceivers.get(playerPos);
			}
			if (positionPos == 6) {
				playerPos = (int) (data.tightends.size() * Math.random());
				newLineup[positionPos]  = data.tightends.get(playerPos);
			}
			if (positionPos == 7) {
				playerPos = (int) (data.defenses.size() * Math.random());
				newLineup[positionPos]  = data.defenses.get(playerPos);
			}
			if (positionPos == 8) {
				playerPos = (int) (data.flex.size() * Math.random());
				if ((!(newLineup[1].name.equals(data.flex.get(playerPos).name))) && 
						(!(newLineup[2].name.equals(data.flex.get(playerPos).name))) &&
						(!(newLineup[3].name.equals(data.flex.get(playerPos).name))) &&
						(!(newLineup[4].name.equals(data.flex.get(playerPos).name))) &&
						(!(newLineup[5].name.equals(data.flex.get(playerPos).name))) &&
						(!(newLineup[6].name.equals(data.flex.get(playerPos).name))))
					newLineup[positionPos]  = data.flex.get(playerPos);
			}

			double currentEnergy = getPoints(currentLineup);
			double neighbourEnergy = getPoints(newLineup);

			if ((acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) && (getCost(newLineup) <= 50000)) {
				currentLineup = newLineup.clone();
			}

			if (getPoints(currentLineup) > getPoints(bestLineup))  {
				bestLineup = currentLineup.clone();
			}

			temp *= 1-coolingRate;
		}

		printLineup(bestLineup, type);
		double points = 0;
		int cost = 0;
		for (int i = 0; i < bestLineup.length; i++) {
			points += bestLineup[i].projection;
			cost += bestLineup[i].salary;
		}
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println ("\nThe ending number of points is " + df.format(points) + " with a cost of " + cost);



	}

	public static double acceptanceProbability(double energy, double newEnergy, double temperature) {
		// If the new solution is better, accept it
		if (newEnergy < energy) {
			return 1.0;
		}
		// If the new solution is worse, calculate an acceptance probability
		return Math.exp((energy - newEnergy) / temperature);
	}

	public void hillClimbing(DataCollector data) {

	}
	

	private static double getPoints(Player[] lineup) {
		double points = 0;
		for (int i = 0; i < lineup.length; ++i) {
			points += lineup[i].projection;
		}
		return points;
	}

	private static int getCost(Player[] lineup) {
		int cost = 0;
		for (int i = 0; i < lineup.length; ++i) {
			cost += lineup[i].salary;
		}
		return cost;
	}

}



