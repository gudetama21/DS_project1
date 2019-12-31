import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {


	public static void main(String[] args) throws IOException {
		WebPage rootPage = new WebPage("https://www.google.com/search?q=%E8%B3%87%E8%A8%8A%E7%AE%A1%E7%90%86&rlz=1C1SQJL_zh-TWTW810TW811&oq=%E8%B3%87%E8%A8%8A%E7%AE%A1%E7%90%86&aqs=chrome..69i57j0l7.5296j0j7&sourceid=chrome&ie=UTF-8", "MIS");
		WebTree tree = new WebTree(rootPage);
		
		tree.root.addChild(new WebNode(new WebPage("https://mis2.nccu.edu.tw/","NCCU MIS")));
		tree.root.addChild(new WebNode(new WebPage("https://oops.udn.com/oops/story/6699/2677303","MIS vs CS")));
		tree.root.addChild(new WebNode(new WebPage("https://www.unews.com.tw/News/Info/344","What is MIS")));
		tree.root.addChild(new WebNode(new WebPage("https://www.dcard.tw/f/graduate_school","dcard")));
		tree.root.addChild(new WebNode(new WebPage("https://www.ptt.cc/bbs/graduate/index.html","ptt")));
		tree.root.addChild(new WebNode(new WebPage("https://www.ptt.cc/bbs/graduate/M.1523095685.A.A9E.html","ptt")));
		tree.root.children.get(1).addChild(new WebNode(new WebPage("https://mis2.nccu.edu.tw/zh_tw/Admission", "NCCU MIS admission")));
		tree.root.children.get(4).addChild(new WebNode(new WebPage("https://www.dcard.tw/search/general?forum=graduate_school&query=%E8%B3%87%E7%AE%A1", "dcard MIS")));
		tree.root.children.get(5).addChild(new WebNode(new WebPage("https://www.ptt.cc/bbs/graduate/M.1523095685.A.A9E.html", "ptt searchMIS")));
		tree.root.children.get(5).addChild(new WebNode(new WebPage("https://www.ptt.cc/bbs/graduate/search?q=%E8%B3%87%E7%AE%A1", "ptt MIS")));

		
		
		
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			int numOfKeywords = scanner.nextInt();
			ArrayList<Keyword> keywords = new ArrayList<Keyword>();
			keywords.add(new Keyword("台灣",5));
			keywords.add(new Keyword("研究所",5));
			keywords.add(new Keyword("資訊管理",8));
			keywords.add(new Keyword("資管",8));
			keywords.add(new Keyword("碩士",8));
			keywords.add(new Keyword("推甄",8));
			keywords.add(new Keyword("甄試",8));
			keywords.add(new Keyword("入學考試",8));
			keywords.add(new Keyword("大四",5));
			keywords.add(new Keyword("正取",5));
			keywords.add(new Keyword("筆試",3));
			keywords.add(new Keyword("口試",3));
			keywords.add(new Keyword("考研",3));
			keywords.add(new Keyword("出路",-3));
			keywords.add(new Keyword("資工",-3));
			keywords.add(new Keyword("職涯",-5));
			keywords.add(new Keyword("工作",-5));
			
			for(int i =0;i<numOfKeywords;i++)
			{
				String name = scanner.next();
				Keyword k = new Keyword(name,7);
				keywords.add(k);
			}
			
			tree.setPostOrderScore(keywords);
			tree.eularPrintTree();
		}
		scanner.close();
		
//		try {
//			System.out.println(new GoogleQuery("資訊管理").query());
			
//		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}