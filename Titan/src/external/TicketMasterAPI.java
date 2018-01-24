package external;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Item;

public class TicketMasterAPI {
	private static final String URL = "https://app.ticketmaster.com/discovery/v2/events.json";
	private static final String DEFAULT_TERM = ""; // no restriction
	private static final String API_KEY = "cGZIXsMm1Fd1zRm1cPALNerLIw0A87oU";
	
    public JSONArray search(double lat, double lon, String term) {
    	
    	    if (term == null) {
    	    		term = DEFAULT_TERM;
    	    }
    	    try {
    	    		term = java.net.URLEncoder.encode(term,"UTF-8");    //重编码
    	    } catch (Exception e) {
    	    		e.printStackTrace();
    	    }
    	    
    	    String geoHash = GeoHash.encodeGeohash(lat, lon, 8);
    	    
    	    String query = String.format("apikey=%s&geoPoint=%s&keyword=%s&radius=%s", API_KEY, geoHash, term, 50);
    	    
    	    try {
    	    	    HttpURLConnection connection = (HttpURLConnection) new URL(URL + "?" + query).openConnection();
    	    		connection.setRequestMethod("GET");
    	    		
    	    		int responseCode = connection.getResponseCode();
    	    		System.out.println("Response Code: " + responseCode);
    	    		
    	    		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    	    		StringBuilder response = new StringBuilder();
    	    		String line = "";
    	    		while ((line = in.readLine()) != null) {
    	    			response.append(line);
    	    		}
    	    		in.close();
    	    		
    	    		JSONObject obj = new JSONObject(response.toString());
    	    		if (obj.isNull("_embedded")) {
    	    			return new JSONArray();
    	    		}
    	    		JSONObject embedded = obj.getJSONObject("_embedded");
    	    		JSONArray events = embedded.getJSONArray("events");
    	    		return events;
    	    		
    	    	
    	    } catch (Exception e) {
    	    		e.printStackTrace();
    	    }
    	    		
    		return new JSONArray();
    }

    private void queryAPI(double lat, double lon) {
		JSONArray events = search(lat, lon, null);
		try {
		    for (int i = 0; i < events.length(); i++) {
		        JSONObject event = events.getJSONObject(i);
		        System.out.println(event);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
    
	/**
	 * Helper methods
	 */
	private JSONObject getVenue(JSONObject event) throws JSONException {
		return null;
	}

	private String getImageUrl(JSONObject event) throws JSONException {
		return null;
	}

	private Set<String> getCategories(JSONObject event) throws JSONException {
		return null;
	}

	private double getDistance(JSONObject event) throws JSONException {
		return 0.0;
	}

	// Convert JSONArray to a list of item objects.
	private List<Item> getItemList(JSONArray events) throws JSONException {
		List<Item> itemList = new ArrayList<>();
		return null;
	}


    public static void main(String[] args) {
    	TicketMasterAPI tmApi = new TicketMasterAPI();
    	// Mountain View, CA
    	// tmApi.queryAPI(37.38, -122.08);
    	// Houston, TX
    	tmApi.queryAPI(29.682684, -95.295410);
    }
    
}



