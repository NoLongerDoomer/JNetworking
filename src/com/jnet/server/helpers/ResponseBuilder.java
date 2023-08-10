package com.jnet.server.helpers;

import java.util.List;

import com.jnet.server.beans.FileDetails;
import com.jnet.server.beans.HttpHeaders;

public class ResponseBuilder {

	public static String buildDirectoryResponse(final String basePath, List<FileDetails> fileDetails) {
		StringBuilder builder = new StringBuilder();

		builder.append(HttpHeaders.HTTP_OK)
		.append(HttpHeaders.TEXT_HTML)
		.append("<html><body>")
		.append("<h1> Directory Listing </h1>")
		.append("<ul>");

		for (FileDetails file : fileDetails) {
			final String fullFilePath = basePath + "/" + file.getFileName();
			if (file.isFile()) {
				builder.append("<li>")
							.append("<a href = '/").append(fullFilePath).append("'>").append(file.getFileName()).append("</a>")
							.append("(").append(file.getFileSize()).append(" bytes").append(")")
							.append("<a href = 'download").append(fullFilePath).append("'> Download").append("</a>")
						.append("</li>");
			} else {
				builder.append("<li>")
							.append("<a href = '").append(fullFilePath).append("'>").append(fullFilePath).append("</a>")
						.append("</li>");
			}
		}

		builder.append("</ul></body></html>");
		
		return builder.toString();
	}
}
