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
	private double currTempFar;
	private double currTempCel;
	private double humi;
	private String icon;
	private String country;
	private String locName;

	public double getCurrTempFar() {
		return currTempFar;
	}

	public double getCurrTempCel() {
		return currTempCel;
	}

	public double getHumi() {
		return humi;
	}

	public String getIcon() {
		return icon;
	}

	public String getCountry() {
		return country;
	}

	public String getLocName() {
		return locName;
	}

	public String loadWebsite(String city) {
		String URL = "http://api.openweathermap.org/data/2.5/weather?q=" + city
				+ "&appid=2550e0628a9e7428e4ef85626feb1c95";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet getURL = new HttpGet(URL);
		try {
			CloseableHttpResponse response1 = httpclient.execute(getURL);
			HttpEntity entity = response1.getEntity();
			JSONObject json = new JSONObject(EntityUtils.toString(entity));
			this.getInfo(json);
			response1.close();
			return "Success";
		} catch (Exception e) {
			return "Failed";
		}

	}

	private void getInfo(JSONObject json) {
		JSONObject info = json.getJSONObject("main");
		double temp = info.getDouble("temp");
		currTempFar = kelvinToFar(temp);
		currTempCel = kelvinToCel(temp);
		humi = info.getDouble("humidity");
		JSONArray array = json.getJSONArray("weather");
		JSONObject arr = array.getJSONObject(0);
		icon = arr.getString("icon");
		info = json.getJSONObject("sys");
		country = info.getString("country");
		locName = (String) json.get("name");
	}

	private double kelvinToFar(double currTemp) {
		return (((currTemp - 273.15) * (1.8)) + 32);
	}

	private double kelvinToCel(double currTemp) {
		return currTemp - 273.15;
	}
}
