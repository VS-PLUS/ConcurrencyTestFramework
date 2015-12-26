package com.indix;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestTest {
	// int defaultTimeOut = 50000;

	@Test
	public void simpleTest() {
		try {
			Thread.sleep(Runner.delayBetweenRequests);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		try {
			Assert.assertEquals((RestTest.getOyoApiData()).contains("200 OK"),
					true);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue(false);
		}

	}

	private static String getOyoApiData() throws ClientProtocolException,
			IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://httpbin.org/get");
		CloseableHttpResponse response = httpclient.execute(httpget);
		// System.out.println("Called");

		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String json = EntityUtils.toString(entity);
			// System.out.println(json);
			response.close();
			System.out.println(response.getStatusLine());
			return response.getStatusLine().toString();

		}
		response.close();

		return null;
	}

}
