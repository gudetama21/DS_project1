
public class Result {
	public String name;
	public String url;
	
	public Result(String name,String url){
		this.name = name;
		this.url = url;
	}
	
	@Override
	public String toString(){
		return "["+name+","+url+"]";
	}
}
