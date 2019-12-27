import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebPage rootPage = new WebPage("http://www3.nccu.edu.tw/~yuf", "Fang Yu");		
		WebTree tree = new WebTree(rootPage);
		
		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Publications.html","Publication")));
		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Projects.html","Project")));
		tree.root.children.get(1).addChild(new WebNode(new WebPage("http://www.cs.ucsb.edu/~vlab/stranger/", "Stranger")));
//		tree.root.children.get(1).addChild(new WebNode(new WebPage("http://nccusoslab.com:280/data-structure/little-boat.html", "LittleBoat")));
		tree.root.addChild(new WebNode(new WebPage("http://www3.nccu.edu.tw/~yuf/short_biography.htm","Biography")));
		tree.root.children.get(2).addChild(new WebNode(new WebPage("http://www.cs.ucsb.edu/~vlab", "Vlab")));
		tree.root.addChild(new WebNode(new WebPage("http://www3.nccu.edu.tw/~yuf/course.htm","Course")));
		
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			int numOfKeywords = scanner.nextInt();
			ArrayList<Keyword> keywords = new ArrayList<Keyword>();
			
			for(int i =0;i<numOfKeywords;i++)
			{
				String name = scanner.next();
				double weight = scanner.nextDouble();
				Keyword k = new Keyword(name, weight);
				keywords.add(k);
			}
			
			tree.setPostOrderScore(keywords);
			tree.eularPrintTree();
		}
		scanner.close();
	}

}


/*
 * 
Input:
2
Fang 0.5
Yu 0.6
Output:
(Fang Yu,131.10000000000002
	(Publication,110.5)
	(Project,12.1
		(Stranger,0.0)
	)
	(Biography,2.8
		(Vlab,0.0)
	)
	(Course,2.3)
)
*/