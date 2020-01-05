
import java.io.IOException;
import java.util.ArrayList;


public class WebTree {
	public WebNode root;
	
	public WebTree(WebPage rootPage){
		this.root = new WebNode(rootPage);
	}
	
	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException{
		setPostOrderScore(root, keywords);
	}
	
	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException{
		// 1. compute the score of children nodes
		// 2. setNode score of startNode
		
		for(WebNode child : startNode.children) {			
			setPostOrderScore(child, keywords);
		}		
		startNode.setNodeScore(keywords);		
	}
	
	public void eularPrintTree(){
		eularPrintTree(root);
	}
	
	private void eularPrintTree(WebNode startNode){
		int nodeDepth = startNode.getDepth();
		
		if(nodeDepth > 1) System.out.print("\n" + repeat("\t", nodeDepth-1));
		System.out.print("(");
		System.out.print(startNode.webPage.name+","+startNode.nodeScore);
		
		for(WebNode child : startNode.children){
			eularPrintTree(child);
		}
		System.out.print(")");
		if(startNode.isTheLastChild()) System.out.print("\n" + repeat("\t", nodeDepth-2));
		
	}
	
	private String repeat(String str,int repeat){
		String retVal  = "";
		for(int i=0;i<repeat;i++){
			retVal+=str;
		}
		return retVal;
	}
	
	private void sort() {
		
		for(int i=0;i<root.children.size()-1;i++){
			for(int k=i+1;k<root.children.size();k++) {
				if(root.children.get(i).nodeScore<root.children.get(k).nodeScore) {
					WebNode n=root.children.get(i);
					root.children.remove(i);
					root.children.add(i, root.children.get(k-1));;
					root.children.remove(k);
					root.children.add(k, n);;
					
				}
			}
		}
	}
}
