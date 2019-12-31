
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Map;

public class KeywordList {
	private Keyword[] hash_table;
	private ArrayList<Keyword> keywords = new ArrayList<>();
	
	public KeywordList(int slots){
		this.hash_table = new Keyword[slots];
	}
	
	public void add(Keyword keyword){
		keywords.add(keyword);
		//System.out.println("Done");
	}
	
	public void getKeyWordByCount(int count){
		
	}
	
	public void output(){
		StringBuilder sb = new StringBuilder();
		for(Keyword k : keywords){
			sb.append(k.toString()+" ");
		}
		
		System.out.println(sb.toString().trim());
	}
	
	public void sort(){
		this.keywords = doQuickSort(this.keywords);
		System.out.println("Done");
	}
	
	private ArrayList<Keyword> doQuickSort(ArrayList<Keyword> keywords) {
		if(keywords.size() <= 1){
			return keywords;
		}
		
		ArrayList<Keyword> lt = new ArrayList<>();
		ArrayList<Keyword> eq = new ArrayList<>();
		ArrayList<Keyword> gt = new ArrayList<>();
		ArrayList<Keyword> retVal = new ArrayList<>();
		int IndexOfList = keywords.size() / 2;
		Keyword point = keywords.get(IndexOfList);
		
		for(Keyword keyword : keywords){
			if(keyword.count < point.count){
				lt.add(keyword);
			}
			else if (keyword.count > point.count){
				gt.add(keyword);
			}
			else {
				eq.add(keyword);
			}
		}
		
		retVal.addAll(doQuickSort(lt));
		retVal.addAll(eq);
		retVal.addAll(doQuickSort(gt));
		
		return retVal;
	}
	
}
