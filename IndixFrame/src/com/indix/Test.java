package com.indix;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
try {
	Test.getOyoApiData();
} catch (ClientProtocolException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
	
	private static String getOyoApiData() throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://httpbin.org/get");
		CloseableHttpResponse response = httpclient.execute(httpget);
		System.out.println("Called");

		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String json = EntityUtils.toString(entity);
			System.out.println(json);
			response.close();
			return json;

		}
		response.close();

		return null;
	}

}
