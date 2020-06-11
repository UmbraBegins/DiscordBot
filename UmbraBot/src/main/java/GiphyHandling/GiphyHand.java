package GiphyHandling;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class GiphyHand {

	public String getURL(String tag) {
		JSONObject findGif = null;
		String URL = "http://api.giphy.com/v1/gifs/random?tag=" + tag
				+ "&api_key=ORVrhFswJyGmrgSWOnVcUjuE5ZCtaXSe&rating=r";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet getURL = new HttpGet(URL);
		try {
			CloseableHttpResponse response1 = httpclient.execute(getURL);
			HttpEntity entity = response1.getEntity();
			JSONObject json = new JSONObject(EntityUtils.toString(entity));
			response1.close();
			findGif = json.getJSONObject("data").getJSONObject("images").getJSONObject("downsized_medium");
		} catch (Exception e) {
			System.out.println("Failed");
		}
		return (String) (findGif.get("url"));

	}
}
