package com.jnet.server.beans;

public class HttpHeaders {

	public static final String HTTP_OK = "HTTP/1.1 200 OK\r\n";
	public static final String TEXT_HTML = "Content-Type : text/html\r\n";
	public static final String CONTENT_DISPOSITION_FILE = "Content-Disposition: attachment; filename=";
	public static final String CONTENT_LENGTH = "Content-Length:";
	public static final String CONTENT_TYPE = "Content-Type:";
	public static final String APPLICATION_OCTET = "application/octet";
	private HttpHeaders() {};
}
