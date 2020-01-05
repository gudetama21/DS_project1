import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) throws IOException {
		WebPage rootPage = new WebPage("https://www.google.com.tw/", "google");		
	    WebTree tree = new WebTree(rootPage);
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			
			ArrayList<Keyword> keywords = new ArrayList<Keyword>();		
			keywords.add(new Keyword("台灣",5));
			keywords.add(new Keyword("研究所",5));
			keywords.add(new Keyword("碩士",8));
			keywords.add(new Keyword("推甄",8));
			keywords.add(new Keyword("入學考試",5));
			keywords.add(new Keyword("筆試",3));
			keywords.add(new Keyword("口試",3));
			keywords.add(new Keyword("考研",5));
			keywords.add(new Keyword("出路",-3));
			keywords.add(new Keyword("職涯",-5));
			keywords.add(new Keyword("工作",-5));
						
			String name = scanner.next();
		
			Keyword k = new Keyword(name, 7);
			keywords.add(k);
					
			try {
				GoogleQuery google = new GoogleQuery(keywords.get(keywords.size()-1).name);
				HashMap<String, String> query = google.query();
				String[][] s = new String[query.size()][2];
				
				int num = 0;
				for(Entry<String, String> entry : query.entrySet()) {
				    String key = entry.getKey();
				    String value = entry.getValue();
				    s[num][0] = key;
				    s[num][1] = value;
				    num++;
											    
				if(num>1) {
				tree.root.addChild(new WebNode(new WebPage(value,key)));
				}	
				
				}
				tree.setPostOrderScore(keywords);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			tree.eularPrintTree();
			
		}
		scanner.close();
		
	}

}
