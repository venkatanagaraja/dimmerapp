package com.smartthings.ratpack.handlers;

import static ratpack.jackson.Jackson.fromJson;

import java.io.InputStream;

import javax.inject.Inject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

import com.smartthings.behaviour.beans.DimmerLight;
import com.smartthings.behaviour.constants.Constants;

import ratpack.exec.ExecResult;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.test.exec.ExecHarness;

public class DimmerLightPostHandler implements Handler {

	public String val = "";

	@Inject
	public DimmerLightPostHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(Context ctx) throws Exception {
		Promise<DimmerLight> payLoadValue = ctx.parse(fromJson(DimmerLight.class));
		ExecResult<String> result1 = ExecHarness.yieldSingle(c -> payLoadValue.map(p -> p.getValue())

		);
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(Constants.smartAppEndpointURL + Constants.setLevlPath + result1.getValue());
		System.out.println(httppost.getURI());
		System.out.println(httppost.getRequestLine());
		httppost.setHeader("Authorization", Constants.authorizationKey);
		HttpResponse response = httpclient.execute(httppost);
		response.getStatusLine();
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				// do something useful
			} finally {
				instream.close();
			}
		}
		ctx.render("Success");
	}

}
