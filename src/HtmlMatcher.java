import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Stack;

public class HtmlMatcher {
	private String urlStr;
    private String content;
    public HtmlMatcher(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException{
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	
		String retVal = "";
	
		String line = null;
		while ((line = br.readLine()) != null)
		{
		    retVal = retVal + line + "\n";
		}
	
		return retVal;
    }
    
    public void match() throws IOException{	
		if(content==null){
		    content = fetchContent();
		}		
		
		Stack<String> tagStack = new Stack<>();
		
		int indexOfOpen = 0;
 		
		while((indexOfOpen = content.indexOf("<",indexOfOpen))!=-1){
			
			int indexOfClose;
			String fullTag=content.substring(content.indexOf("<",indexOfOpen),content.indexOf(">",indexOfOpen)+1);
			indexOfClose=content.indexOf(">",indexOfOpen)+1;
			String tagName = null;
			int indexOfSpace = -1;
			if((indexOfSpace=fullTag.indexOf(" "))==-1){

				tagName=fullTag.substring(1, fullTag.length()-1);
			}else{
				
				tagName=fullTag.split(" ")[0].substring(1);	
			}
			if(tagName.equals("link") || tagName.equals("meta") || tagName.equals("!doctype")) {
				indexOfOpen = indexOfClose;
				continue;
			}
			
			int indexOfSlash = -1;
			if((indexOfSlash = tagName.indexOf("/"))==-1){
				//This is an open tag, so simply push it into stack
				tagStack.push(tagName);
				
			}else{  	
			    tagName=tagName.substring(1);	
						    	
				if(tagStack.isEmpty()){			
				    System.out.println("False");
				    return;
				}				
				
				String topMostTag = tagStack.peek();
				
				if(topMostTag.equals(tagName)){

					tagStack.pop();
				}else{
					System.out.println("False "+getStackString(tagStack));
					return;
				}
			}			
			indexOfOpen = indexOfClose;
		}		
		
		if(!tagStack.isEmpty()){

		    	System.out.println("False "+getStackString(tagStack));
		}else {
			System.out.println("True");
		} 
		
	}
    
    private String getStackString(Stack<String> stack){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<stack.size();i++){
		    if(i>0){
		    	sb.append(" ");
		    }
		    sb.append(stack.get(i));
		}	
		return sb.toString();
    }
}
