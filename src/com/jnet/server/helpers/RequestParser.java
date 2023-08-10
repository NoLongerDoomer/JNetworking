package com.jnet.server.helpers;

import com.jnet.server.beans.HttpRequest;
import com.jnet.server.enums.HttpMethod;

public class RequestParser {

	public static HttpRequest parse(String request) {
		String[] requestParts = request.split(" ");

		HttpMethod httpMethod = HttpMethod.valueOf(requestParts[0]);
		String requestedDir = requestParts[1];
		return new HttpRequest(httpMethod, requestedDir);
	}
	
}
