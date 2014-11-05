package fantasyanalyzer;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

import jxl.*;
import jxl.read.biff.BiffException;

public class DataCollector {
	public ArrayList<Player> quarterbacks = new ArrayList<Player>();
	public ArrayList<Player> runningbacks = new ArrayList<Player>();
	public ArrayList<Player> widereceivers = new ArrayList<Player>();
	public ArrayList<Player> tightends = new ArrayList<Player>();
	public ArrayList<Player> defenses = new ArrayList<Player>();
	public ArrayList<Player> flex = new ArrayList<Player>();
	private Elements scraped = new Elements();


	public DataCollector(int week, String method) throws BiffException, IOException {
		String url = "";
		if (week == 10)
			url = "http://fantasyprospartners.appspot.com/draftkingsSalaryCap?sport=nfl&week=10&expertId=-1&h=750";
		else if (week == 9)
			url = "http://fantasyprospartners.appspot.com/draftkingsSalaryCap?sport=nfl&expertId=95&s=Kevin%20Hanson%20%2F%20EDSFootball&aff_url=http%3A%2F%2Fpartners.draftkings.com%2Faff_c%3Foffer_id%3D124%26aff_id%3D21400&h=1000";
		else if (week == 8) 
			url = "http://fantasyprospartners.appspot.com/draftkingsSalaryCap?sport=nfl&week=8&expertId=-1&h=750";
		else if (week == 7)
			url = "http://fantasyprospartners.appspot.com/draftkingsSalaryCap?sport=nfl&week=7&expertId=-1&h=750";
		else if (week == 6)
			url = "http://fantasyprospartners.appspot.com/draftkingsSalaryCap?sport=nfl&week=6&expertId=-1&h=750";
		else if (week == 5)
			url = "http://fantasyprospartners.appspot.com/draftkingsSalaryCap?sport=nfl&week=5&expertId=-1&h=750";
		else if (week == 4)
			url = "http://fantasyprospartners.appspot.com/draftkingsSalaryCap?sport=nfl&week=4&expertId=-1&h=750";

		Document document = Jsoup.connect(url).timeout(0).get();
		scraped = document.select("table tr");


		if (method.equals("fantasypros")) {
			if (scraped.size() > 0) {
				for (int i = 1; i < scraped.size(); i++) {
					//System.out.println("data scrape successful");
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
						String position = tokens[4];
						if (position.equals("QB)")) {
							Player p = new Player("QB", name, opponent, projection, salary);
							quarterbacks.add(p);
						}

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
						String position = tokens[4];
						if (position.equals("RB)")) {
							Player p = new Player("RB", name, opponent, projection, salary);
							runningbacks.add(p);
						}
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
						String position = tokens[4];
						if (position.equals("WR)")) {
							Player p = new Player("WR", name, opponent, projection, salary);
							widereceivers.add(p);
						}
					}
					if ((scraped.get(i).getElementsContainingText("TE")).size() > 1) {
						String text = scraped.get(i).text();
						
						String delims = "[ ]+";
						String[] tokens = text.split(delims);
						String name = tokens[0] + " " + tokens[1];
						String position = tokens[4];
						String opponent = tokens[6];
						String projectionStr = tokens[10]; 
						double projection = Double.parseDouble(projectionStr);
						String salaryStr = tokens[12];
						salaryStr = salaryStr.replaceAll("[^\\d.]", "");
						int salary = Integer.parseInt(salaryStr);
						//System.out.println (name + " " + opponent + " " + projection + " " + salary);
						if (position.equals("TE)")) {
							Player p = new Player("TE", name, opponent, projection, salary);
							tightends.add(p);
						}
					}
					if ((scraped.get(i).getElementsContainingText("DST")).size() > 1) {
						String text = scraped.get(i).text();
						String delims = "[ ]+";
						String[] tokens = text.split(delims);
						if ((tokens[0].equals("Kansas") ) || (tokens[0].equals("New")) || (tokens[0].equals("San")) || (tokens[0].equals("Tampa")) || (tokens[0].equals("Green")) || (tokens[0].equals("St.")) ) {
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
		}



		// EXCEL SCRAPING
		else if (method.equals("propmonkey")) {
			Workbook workbook = Workbook.getWorkbook(new File("res/Week9.xls"));
			Sheet qbSheet = workbook.getSheet(0);
			Sheet rbSheet = workbook.getSheet(1);
			Sheet wrSheet = workbook.getSheet(2);
			Sheet teSheet = workbook.getSheet(3);
			Sheet dstSheet = workbook.getSheet(4);

			for (int i = 1; i < 25; i++) {
				String name = qbSheet.getCell(0, i).getContents();
				String opponent = qbSheet.getCell(2, i).getContents();
				String projectionStr = qbSheet.getCell(4, i).getContents(); 
				double projection = Double.parseDouble(projectionStr);
				String salaryStr = qbSheet.getCell(5, i).getContents();
				int salary = Integer.parseInt(salaryStr);
				Player p = new Player("QB", name, opponent, projection, salary);
				quarterbacks.add(p);
			}
			for (int i = 1; i < 28; i++) {
				String name = rbSheet.getCell(0, i).getContents();
				String opponent = rbSheet.getCell(2, i).getContents();
				String projectionStr = rbSheet.getCell(5, i).getContents(); 
				double projection = Double.parseDouble(projectionStr);
				String salaryStr = rbSheet.getCell(6, i).getContents();
				int salary = Integer.parseInt(salaryStr);
				Player p = new Player("RB", name, opponent, projection, salary);
				runningbacks.add(p);
			}
			for (int i = 1; i < 46; i++) {
				String name = wrSheet.getCell(0, i).getContents();
				String opponent = wrSheet.getCell(2, i).getContents();
				String projectionStr = wrSheet.getCell(4, i).getContents(); 
				double projection = Double.parseDouble(projectionStr);
				String salaryStr = wrSheet.getCell(5, i).getContents();
				int salary = Integer.parseInt(salaryStr);
				Player p = new Player("WR", name, opponent, projection, salary);
				widereceivers.add(p);
			}
			for (int i = 1; i < 25; i++) {
				String name = teSheet.getCell(0, i).getContents();
				String opponent = teSheet.getCell(2, i).getContents();
				String projectionStr = teSheet.getCell(4, i).getContents(); 
				double projection = Double.parseDouble(projectionStr);
				String salaryStr = teSheet.getCell(5, i).getContents();
				int salary = Integer.parseInt(salaryStr);
				Player p = new Player("TE", name, opponent, projection, salary);
				tightends.add(p);
			}
			for (int i = 1; i < 21; i++) {
				String name = dstSheet.getCell(0, i).getContents();
				String opponent = dstSheet.getCell(1, i).getContents();
				String projectionStr = dstSheet.getCell(2, i).getContents(); 
				double projection = Double.parseDouble(projectionStr);
				String salaryStr = dstSheet.getCell(3, i).getContents();
				int salary = Integer.parseInt(salaryStr);
				Player p = new Player("DST", name, opponent, projection, salary);
				defenses.add(p);
			}

			

			flex.addAll(runningbacks);
			flex.addAll(widereceivers);
			flex.addAll(tightends);

			//			for (int i = 0; i < quarterbacks.size(); i++) {
			//				quarterbacks.get(i).printExcel();
			//			}
			//			for (int i = 0; i < runningbacks.size(); i++) {
			//				runningbacks.get(i).printExcel();
			//			}
			//			for (int i = 0; i < widereceivers.size(); i++) {
			//				widereceivers.get(i).printExcel();
			//			}
			//			for (int i = 0; i < tightends.size(); i++) {
			//				tightends.get(i).printExcel();
			//			}
			//			for (int i = 0; i < defenses.size(); i++) {
			//				System.out.println(defenses.get(i).toString());
			//			}
		}
		
	}

	public static void main(String[] args) throws BiffException, IOException {
		DataCollector data = new DataCollector(10, "fantasypros");
		LineupGenerator.simulatedAnnealing(data, "fantasypros");
	}



}
