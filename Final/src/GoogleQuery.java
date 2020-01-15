import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.ArrayList;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class GoogleQuery {

	public String searchKeyword;

	public String url;

	public String content;
	

	public GoogleQuery(String searchKeyword){

		this.searchKeyword = searchKeyword;

		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=10";

	}

	

	private String fetchContent() throws IOException{
		
		String retVal = "";

		URL u = new URL(url);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null){
			//retVal += line;
			retVal = retVal + line + "\n";
		}
		return retVal;
	}
	
	
	public ArrayList<Result> query() throws IOException{

		if(content==null){

			content= fetchContent();
//			System.out.println(content);
		}

		HashMap<String, String> searchResult = new HashMap<String, String>();
		
		Document doc = Jsoup.parse(content);
//		System.out.println(doc.text());
		Elements lis = doc.select("div");
		lis = lis.select(".ZINbbc");
//		System.out.println(lis.size());

		
		for(Element li : lis)
		{
			try 

			{
				Elements select = li.select("a[title]");
				if (select!=null&&select.size()>0) {
				String linkHref = select.get(0).attr("href");//獲取href值
                String linkText = select.get(0).text();//獲取text

				searchResult.put(linkHref, linkText);
				System.out.println(linkHref + linkText);}


			} catch (IndexOutOfBoundsException e)  {
			e.printStackTrace();
			}
		}
		
		KeywordList result_table = calculate(searchResult);
		
		ArrayList<Result> finalResult = new ArrayList<Result>();
	
		for(int i = finalResult.size() ; i > 0 ; i--) {
			if(result_table.getKeyWordByCount(i) != null) {
				String name = result_table.getKeyWordByCount(i);
				String resultUrl = searchResult.get(name);
				finalResult.add(new Result(name,resultUrl));
			}
		}
	
	
		return finalResult;
		
//		for(Element li : lis){
//			try 

//			{
//				System.out.println(li.select(".BNeawe").get(0).text());
//				System.out.println(li.select("a").get(0).attr("href"));
//				for(int i = 0 ; i < block.size(); i++)
		
//					System.out.println(block.get(i).text());
				
//				System.out.println(block.get(1).text());
//				System.out.println(block.get(2).text());
				
//				String title = block.get(1).text();
//				String citeUrl = block.get(2).text();
				
//				System.out.println(title+" "+citeUrl);

//				retVal.put(title, citeUrl);
				
//				String BNeawe = li.select(".BNeawe").get(0).text();
//				String aTag =li.select("a").get(0).attr("href");
//				for(int i = 0 ; i < lis.size(); i++)
//					System.out.println(lis.get(i).text());
				
//				String title = lis.get(1).text();
//				String citeUrl = lis.get(2).text();
				
//				result.put(title, citeUrl);
							

//			} catch (IndexOutOfBoundsException e) {

//				continue;
//			}

//		}

	}
	
	
	public KeywordList calculate(HashMap<String, String> searchResult) throws IOException{
		
		
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		keywords.add(new Keyword("台灣",5));
		keywords.add(new Keyword("研究所",10));
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
		
		
		KeywordList result_table = new KeywordList(100);
		
		for(String key : searchResult.keySet()){
			   String value = searchResult.get(key);
			   WebPage rootPage = new WebPage(value,key);
			   
			   rootPage.setScore(keywords);
//	           System.out.print(rootPage.name+","+rootPage.score);
			   result_table.add(new Keyword(key,rootPage.score));
		
		}
		
		return result_table;
	}
	
}