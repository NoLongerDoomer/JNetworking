package com.jnet.server.services;

import java.io.OutputStream;
import java.io.PrintWriter;

public interface JSocketExposeService {

	void writeDirectoryToBrowser(PrintWriter printWriter, String directory, String currentUrl, OutputStream outputStream);

}
