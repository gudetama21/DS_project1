import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		WebPage rootPage = new WebPage("http://www3.nccu.edu.tw/~yuf", "Fang Yu");		
		WebTree tree = new WebTree(rootPage);
		
		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Publications.html","Publication")));
		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Projects.html","Project")));
		tree.root.children.get(1).addChild(new WebNode(new WebPage("http://www.cs.ucsb.edu/~vlab/stranger/", "Stranger")));
		tree.root.addChild(new WebNode(new WebPage("http://www3.nccu.edu.tw/~yuf/short_biography.htm","Biography")));
		tree.root.children.get(2).addChild(new WebNode(new WebPage("http://www.cs.ucsb.edu/~vlab", "Vlab")));
		tree.root.addChild(new WebNode(new WebPage("http://www3.nccu.edu.tw/~yuf/course.htm","Course")));
		
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			int numOfKeywords = scanner.nextInt();
			ArrayList<Keyword> keywords = new ArrayList<Keyword>();
			keywords.add(new Keyword("台灣",5));
			keywords.add(new Keyword("研究所",5));
			keywords.add(new Keyword("碩士",8));
			keywords.add(new Keyword("推甄",8));
			keywords.add(new Keyword("入學考試",5));
			keywords.add(new Keyword("筆試",3));
			keywords.add(new Keyword("口試",3));
			keywords.add(new Keyword("出路",-3));
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
	}

}

