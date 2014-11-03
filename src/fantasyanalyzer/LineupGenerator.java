package fantasyanalyzer;

import java.text.DecimalFormat;
import java.util.Random;

public class LineupGenerator {

	public static Player[] generateInitialLineup(DataCollector data) {
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
		return playerSet;
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
	
	public static void simulatedAnnealing(DataCollector data) {
		double temp = 10000;
		double coolingRate = 0.003;
		
		Player[] currentLineup = new Player[9];
		currentLineup = generateInitialLineup(data);
		Player[] bestLineup = new Player[9];
		bestLineup = currentLineup.clone();
		
		while (temp > 1) {
			Player[] newLineup = new Player[9];
			int positionPos = (int) (newLineup.length * Math.random());
			int playerPos = 0;
			if (positionPos == 0) {
				positionPos = (int) (data.quarterbacks.size() * Math.random());
				newLineup[positionPos]  = data.quarterbacks.get(positionPos);
			}
			if ((positionPos == 1) || (positionPos == 2)) {
				positionPos = (int) (data.runningbacks.size() * Math.random());
				newLineup[positionPos]  = data.runningbacks.get(positionPos);
			}
			if ((positionPos == 3) || (positionPos == 4) || (positionPos == 6)) {
				positionPos = (int) (data.widereceivers.size() * Math.random());
				newLineup[positionPos]  = data.widereceivers.get(positionPos);
			}
			if (positionPos == 7) {
				positionPos = (int) (data.tightends.size() * Math.random());
				newLineup[positionPos]  = data.tightends.get(positionPos);
			}
			if (positionPos == 8) {
				positionPos = (int) (data.defenses.size() * Math.random());
				newLineup[positionPos]  = data.defenses.get(positionPos);
			}
			if (positionPos == 9) {
				positionPos = (int) (data.flex.size() * Math.random());
				newLineup[positionPos]  = data.flex.get(positionPos);
			}
			
			double currentEnergy = getPoints(currentLineup);
			double neighbourEnergy = getPoints(newLineup);
			
			if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                currentLineup = newLineup.clone();
            }
			
			if (getPoints(currentLineup) > getPoints(bestLineup)) {
                bestLineup = currentLineup.clone();
            }
			
			temp *= 1-coolingRate;

		}
		
		printLineup(bestLineup);
		double points = 0;
		int cost = 0;
		for (int i = 0; i < bestLineup.length; i++) {
			points += bestLineup[i].projection;
			cost += bestLineup[i].salary;
		}
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println ("\nThe starting number of points is " + df.format(points) + " with a cost of " + cost);


		
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
	
	public void geneticAlgorithm(DataCollector data) {
		
	}
	
	private static double getPoints(Player[] lineup) {
		double points = 0;
		for (int i = 0; i < lineup.length; i++) {
			points += lineup[i].projection;
		}
		return points;
	}

}



