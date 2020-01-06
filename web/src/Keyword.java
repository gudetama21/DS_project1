public class Keyword {
	public String name;
	public double weight;
	
	public Keyword(String name,double weight){
		this.name = name;
		this.weight = weight;
	}
	
	public String toString(){
		return "["+name+","+weight+"]";
	}
}