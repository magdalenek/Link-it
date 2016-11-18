import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class Parser {
	
	public List<String> Parse(String input)
	{
		//make it lower case
		String text = input.toLowerCase();
		
        List<String> output = new ArrayList<String>();
		
        // separate input by spaces ( URLs don't have spaces )
        String [] parts = text.split("\\s+");
        
        String[] domains = {".com", ".co.uk", ".io", ".ly", ".net", ".org", ".edu", ".gov", ".int", ".it", ".biz", ".info", ".mobi", ".br", ".ca", ".cn", ".fr", ".in", ".jp", ".ru"};
        int domainsSize = domains.length;
        	        
        // Attempt to convert each item into an URL.   
        for( String item : parts ) 
        	
        	// iterate through items to make sure they contain a domain extension
        	for(int x = 0; x < domainsSize; x++)
        	
            //if text contains .com/.co.uk/etc. in array
            if(item.contains(domains[x]))
            {
            	// try to make a URL out of item
	        	try {
	            URL url = new URL(item);
	            // If possible then add to results and print out
	            output.add(item);
	
	         // words that are not URLs are caught below
	        } catch (MalformedURLException e) {
	            // try adding https:// to front
				try {
					URL url = new URL("https://www." + item);
					output.add("https://www." + item);
		            	
				} catch (MalformedURLException e1) {
		            // try adding http:// to front - this is redundant - https always works, it ignores whether you can actually go to site
					try {
						URL url1 = new URL("http://www." + item);
						output.add("http://www." + item);
			            
				} catch (MalformedURLException e2){
					System.out.println(e2);
				}
	        }
            }
		
	}
        
		return output;
	}
}
