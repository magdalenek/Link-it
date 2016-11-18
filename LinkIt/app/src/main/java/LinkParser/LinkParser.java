package LinkParser;

import java.net.MalformedURLException;
import java.net.URL;


public class LinkParser {

    //parsing of a text - does not work for multiple links
    /*
    public String parseText(String input){
        System.out.println(input + " " + input);
        if(input.contains(" ")){
            String [] parts = input.split(" ");
            for( String item : parts )
                return parseLink(item);
        } else{
            return parseLink(input);
        }
        return input;
    }

    */

    //parsing of an item
    public String parseLink(String input) {

        //make it lower case
        input = input.toLowerCase();

        String[] domains = {".com", ".co.uk", ".io", ".ly", ".net", ".org", ".edu", ".gov", ".int", ".it", ".biz", ".info", ".mobi", ".br", ".ca", ".cn", ".fr", ".in", ".jp", ".ru"};

        // Attempt to convert each item into an URL.

        // iterate through items to make sure they contain a domain extension
        for(int x = 0; x < domains.length; x++)
            //if text contains .com/.co.uk/etc. in array
            if(input.contains(domains[x])) {
                // try to make a URL out of item
                try {
                    URL url = new URL(input);
                } catch (MalformedURLException e) {
                    try {
                        URL url = new URL("https://www." + input);
                        input = ("https://www." + input);

                    } catch (MalformedURLException e1) {
                        try {
                            URL url1 = new URL("http://www." + input);
                            input = ("http://www." + input);

                        } catch (MalformedURLException e2){
                            System.out.println(e2);
                        }
                    }
                }

            }

        return input;
    }



}
