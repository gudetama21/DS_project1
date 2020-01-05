import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			GoogleQuery google = new GoogleQuery(scanner.next());
			ArrayList<Result> query = google.query();
			
			String[][] s = new String[query.size()][2];
			int num = 0;
			for(Result entry : query) {
				String key = entry.name;
				String value = entry.url;
				s[num][0] = key;
				s[num][1] = value;
				System.out.println(key + "     " +value);
				num++;
			}
		}
		
		scanner.close();
	}

}
