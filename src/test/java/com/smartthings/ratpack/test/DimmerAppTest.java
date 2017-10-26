package com.smartthings.ratpack.test;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.smartthings.behaviour.constants.Constants;

public class DimmerAppTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		HttpClient httpclient = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(Constants.smartAppEndpointURL + Constants.getSwitchesPath);
			httpGet.setHeader("Authorization", Constants.authorizationKey);
			HttpResponse response = httpclient.execute(httpGet);
			InputStream inputStream = response.getEntity().getContent();
			assertEquals(response.getStatusLine().getStatusCode(), 200);
		} catch (Exception e) {
			fail("test case failed");
		}

	}
}
