import java.util.List;


public class Main {

	
	public static void main(String[] args) {

		String text = "This is example text. #sdkfs .. http://www.facebook.com bhdd sd sd s '#; ;[' google.com // qdkjdw https://www.youtube.com/ ";
		
		Parser p = new Parser();
		
		//Create list of parsed domains from text
		List<String> domainList = p.Parse(text);

		//print out domains
		for (String domain : domainList) {
		    System.out.println(domain);
		}
		
	}

}
