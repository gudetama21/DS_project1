
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Map;

public class KeywordList {
	private Keyword[] hash_table;
	private ArrayList<Keyword> keywords = new ArrayList<>();
	private int maxSize; 
	
	public KeywordList(int slots){
		maxSize = slots;
		this.hash_table = new Keyword[maxSize];
	}
	
	private int hash(int key){
        return key % maxSize;
    }
	
	
	public void add(Keyword keyword){
		
		int pos = hash(keyword.weight);
		if (hash_table[pos] == null){
			hash_table[pos] = keyword;
//			System.out.println("Done");
        }        
        pos = (pos + 1) % maxSize;    
        
    }
	
	public String getKeyWordByCount(int count){
		
		int i = hash(count);
		if(hash_table[i] != null)
        {
            return(hash_table[i].name);
        }            
		else return null;
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
//		System.out.println("Done");
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
			if(keyword.count > point.count){
				lt.add(keyword);
			}
			else if (keyword.count < point.count){
				gt.add(keyword);
			}
			else {
				eq.add(keyword);
			}
		}
		
		retVal.addAll(doQuickSort(gt));
		retVal.addAll(eq);
		retVal.addAll(doQuickSort(lt));
		
		return retVal;
	}
	
	
}
