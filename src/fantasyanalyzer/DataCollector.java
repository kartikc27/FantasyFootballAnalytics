package fantasyanalyzer;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DataCollector {
	public ArrayList<Player> quarterbacks = new ArrayList<Player>();
	public ArrayList<Player> runningbacks = new ArrayList<Player>();
	public ArrayList<Player> widereceivers = new ArrayList<Player>();
	public ArrayList<Player> tightends = new ArrayList<Player>();
	public ArrayList<Player> defenses = new ArrayList<Player>();
	public ArrayList<Player> flex = new ArrayList<Player>();
	private Elements scraped = new Elements();


	public DataCollector(int week) {
		String url = "http://fantasyprospartners.appspot.com/draftkingsSalaryCap?sport=nfl&expertId=95&s=Kevin%20Hanson%20%2F%20EDSFootball&aff_url=http%3A%2F%2Fpartners.draftkings.com%2Faff_c%3Foffer_id%3D124%26aff_id%3D21400&h=1000";
		try {
			Document document = Jsoup.connect(url).timeout(0).get();
			scraped = document.select("table tr");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 1; i < scraped.size(); i++) {
			if ((scraped.get(i).getElementsContainingText("QB")).size() > 1) {
				String text = scraped.get(i).text();
				String delims = "[ ]+";
				String[] tokens = text.split(delims);
				String name = tokens[0] + " " + tokens[1];
				String opponent = tokens[6];
				String projectionStr = tokens[10]; 
				double projection = Double.parseDouble(projectionStr);
				String salaryStr = tokens[12];
				salaryStr = salaryStr.replaceAll("[^\\d.]", "");
				int salary = Integer.parseInt(salaryStr);
				//System.out.println (name + " " + opponent + " " + projection + " " + salary);
				Player p = new Player("QB", name, opponent, projection, salary);
				quarterbacks.add(p);
				
			}
			if ((scraped.get(i).getElementsContainingText("RB")).size() > 1) {
				String text = scraped.get(i).text();
				String delims = "[ ]+";
				String[] tokens = text.split(delims);
				String name = tokens[0] + " " + tokens[1];
				String opponent = tokens[6];
				String projectionStr = tokens[10]; 
				double projection = Double.parseDouble(projectionStr);
				String salaryStr = tokens[12];
				salaryStr = salaryStr.replaceAll("[^\\d.]", "");
				int salary = Integer.parseInt(salaryStr);
				//System.out.println (name + " " + opponent + " " + projection + " " + salary);
				Player p = new Player("RB", name, opponent, projection, salary);
				runningbacks.add(p);
			}
			if ((scraped.get(i).getElementsContainingText("WR")).size() > 1) {
				String text = scraped.get(i).text();
				String delims = "[ ]+";
				String[] tokens = text.split(delims);
				String name = tokens[0] + " " + tokens[1];
				String opponent = tokens[6];
				String projectionStr = tokens[10]; 
				double projection = Double.parseDouble(projectionStr);
				String salaryStr = tokens[12];
				salaryStr = salaryStr.replaceAll("[^\\d.]", "");
				int salary = Integer.parseInt(salaryStr);
				//System.out.println (name + " " + opponent + " " + projection + " " + salary);
				Player p = new Player("WR", name, opponent, projection, salary);
				widereceivers.add(p);
			}
			if ((scraped.get(i).getElementsContainingText("TE")).size() > 1) {
				String text = scraped.get(i).text();
				String delims = "[ ]+";
				String[] tokens = text.split(delims);
				String name = tokens[0] + " " + tokens[1];
				String opponent = tokens[6];
				String projectionStr = tokens[10]; 
				double projection = Double.parseDouble(projectionStr);
				String salaryStr = tokens[12];
				salaryStr = salaryStr.replaceAll("[^\\d.]", "");
				int salary = Integer.parseInt(salaryStr);
				//System.out.println (name + " " + opponent + " " + projection + " " + salary);
				Player p = new Player("TE", name, opponent, projection, salary);
				tightends.add(p);
			}
			if ((scraped.get(i).getElementsContainingText("DST")).size() > 1) {
				String text = scraped.get(i).text();
				String delims = "[ ]+";
				String[] tokens = text.split(delims);
				if ((tokens[0].equals("Kansas") ) || (tokens[0].equals("New")) || (tokens[0].equals("San")) || (tokens[0].equals("Tampa"))) {
					String name = tokens[0] + " " + tokens[1] + " " + tokens[2];
					String opponent = tokens[7];
					String projectionStr = tokens[11]; 
					double projection = Double.parseDouble(projectionStr);
					String salaryStr = tokens[13];
					salaryStr = salaryStr.replaceAll("[^\\d.]", "");
					int salary = Integer.parseInt(salaryStr);
					//System.out.println (name + " " + opponent + " " + projection + " " + salary);
					Player p = new Player("DST", name, opponent, projection, salary);
					defenses.add(p);
				}
				else {
					String name = tokens[0] + " " + tokens[1];
					String opponent = tokens[6];
					String projectionStr = tokens[10]; 
					double projection = Double.parseDouble(projectionStr);
					String salaryStr = tokens[12];
					salaryStr = salaryStr.replaceAll("[^\\d.]", "");
					int salary = Integer.parseInt(salaryStr);
					//System.out.println (name + " " + opponent + " " + projection + " " + salary);
					Player p = new Player("DST", name, opponent, projection, salary);
					defenses.add(p);
				}
			}
			flex.addAll(runningbacks);
			flex.addAll(widereceivers);
			flex.addAll(tightends);
		}
	}
	
	public static void main(String[] args) {
		DataCollector data = new DataCollector(9);
		LineupGenerator.simulatedAnnealing(data);
	}

}
