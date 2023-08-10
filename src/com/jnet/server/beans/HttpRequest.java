package com.jnet.server.beans;

import com.jnet.server.enums.HttpMethod;

public class HttpRequest {

	private HttpMethod httpMethod;
	private String requestDir;

	public HttpRequest(HttpMethod httpMethod, String requestedDir) {
		this.httpMethod = httpMethod;
		this.requestDir = requestedDir;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public String getRequestDir() {
		return requestDir;
	}

}
