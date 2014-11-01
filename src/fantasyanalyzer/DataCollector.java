package fantasyanalyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataCollector {
	private int week;
	private HashMap<Player, Double> quarterbacks;
	private HashMap<Player, Double> runningbacks;
	private HashMap<Player, Double> widereceivers;
	private HashMap<Player, Double> tightends;
	private HashMap<Player, Double> defenses;
	private HashMap<Player, Double> kickers;
	Elements scrapedQBs = new Elements();
	Elements scrapedRBs = new Elements();
	Elements scrapedWRs = new Elements();
	Elements scrapedTEs = new Elements();
	Elements scrapedDSTs = new Elements();
	
	
	public DataCollector(int week) {
		this.week = week;
	    String url = "http://fantasyprospartners.appspot.com/draftkingsSalaryCap?sport=nfl&expertId=95&s=Kevin%20Hanson%20%2F%20EDSFootball&aff_url=http%3A%2F%2Fpartners.draftkings.com%2Faff_c%3Foffer_id%3D124%26aff_id%3D21400&h=1000";
	    try {
			Document document = Jsoup.connect(url).get();
			Elements scraped = document.select("table tr");
			

			
			for (int i = 1; i < scraped.size(); i++) {
				System.out.println(scraped.get(i).text());
				scrapedQBs.addAll(scraped.get(i).getElementsContainingText("QB"));
				scrapedRBs.addAll(scraped.get(i).getElementsContainingText("RB"));
				scrapedWRs.addAll(scraped.get(i).getElementsContainingText("WR"));
				scrapedTEs.addAll(scraped.get(i).getElementsContainingText("TE"));
				scrapedDSTs.addAll(scraped.get(i).getElementsContainingText("DST"));
				
				if ((scraped.get(i).getElementsContainingText("QB")).size() > 1) {
					//Player p = new Player(scraped.get(i).data());
					//System.out.println(scraped.get(i).text());
				}
			}
			for (int i = 0; i < scrapedQBs.size(); i+=1 ) {
				scrapedQBs.remove(i);
			}
			for (int i = 0; i < scrapedRBs.size(); i+=1 ) {
				scrapedRBs.remove(i);
			}
			for (int i = 0; i < scrapedWRs.size(); i+=1 ) {
				scrapedWRs.remove(i);
			}
			for (int i = 0; i < scrapedTEs.size(); i+=1 ) {
				scrapedTEs.remove(i);
			}
			for (int i = 0; i < scrapedDSTs.size(); i+=1 ) {
				scrapedDSTs.remove(i);
			}
//			System.out.println(scrapedQBs.size());
//			System.out.println(scrapedRBs.size());
//			System.out.println(scrapedWRs.size());
//			System.out.println(scrapedTEs.size());
//			System.out.println(scrapedDSTs.size());
//			
//			for (int i = 0; i < scrapedQBs.size(); i++) {
//		        System.out.println(scrapedQBs.get(i).text());
//			}
//			for (int i = 0; i < scrapedRBs.size(); i++) {
//		        System.out.println(scrapedRBs.get(i).text());
//			}
//			for (int i = 0; i < scrapedWRs.size(); i++) {
//		        System.out.println(scrapedWRs.get(i).text());
//			}
//			for (int i = 0; i < scrapedTEs.size(); i++) {
//		        System.out.println(scrapedTEs.get(i).text());
//			}
//			for (int i = 0; i < scrapedDSTs.size(); i++) {
//		        System.out.println(scrapedDSTs.get(i).text());
//			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {

		DataCollector data = new DataCollector(2);

	}
	
	
}
