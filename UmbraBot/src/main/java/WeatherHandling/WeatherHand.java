package WeatherHandling;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherHand {
	private static double currTempFar;
	private static double currTempCel;
	private static double humi;
	private static String icon;
	private static String country;
	private static String locName;

	public static double getCurrTempFar() {
		return currTempFar;
	}

	public static double getCurrTempCel() {
		return currTempCel;
	}

	public static double getHumi() {
		return humi;
	}

	public static String getIcon() {
		return icon;
	}

	public static String getCountry() {
		return country;
	}

	public static String getLocName() {
		return locName;
	}

	public static void loadWebsite(String city) {
		String URL = "http://api.openweathermap.org/data/2.5/weather?q=" + city
				+ "&appid=2550e0628a9e7428e4ef85626feb1c95";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet getURL = new HttpGet(URL);
		try {
			CloseableHttpResponse response1 = httpclient.execute(getURL);
			HttpEntity entity = response1.getEntity();
			JSONObject json = new JSONObject(EntityUtils.toString(entity));
			WeatherHand.getInfo(json);
			response1.close();
		} catch (Exception e) {
			System.out.println("Failed");
		}

	}

	private static void getInfo(JSONObject json) {
		JSONObject info = json.getJSONObject("main");
		double temp = info.getDouble("temp");
		currTempFar = kelvinToFar(temp);
		currTempCel = kelvinToCel(temp);
		humi = info.getDouble("humidity");
		JSONArray array = json.getJSONArray("weather");
		JSONObject yea = array.getJSONObject(0);
		icon = yea.getString("icon");
		info = json.getJSONObject("sys");
		country = info.getString("country");
		locName = (String) json.get("name");
	}

	private static double kelvinToFar(double currTemp) {
		return (((currTemp - 273.15) * (1.8)) + 32);
	}

	private static double kelvinToCel(double currTemp) {
		return currTemp - 273.15;
	}	
}
