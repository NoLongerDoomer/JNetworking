package com.jnet.server.beans;

public class HttpHeaders {

	public final static String HTTP_OK = "HTTP/1.1 200 OK\r\n";
	public final static String TEXT_HTML = "Content-Type : text/html\r\n";
	public final static String CONTENT_DISPOSITION_FILE = "Content-Disposition: attachment; filename=";
	public final static String CONTENT_LENGTH = "Content-Length:";
	public final static String CONTENT_TYPE = "Content-Type:";
	public final static String APPLICATION_OCTET = "application/octet";
	private HttpHeaders() {};
}
