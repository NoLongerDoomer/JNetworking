package com.jnet.server.services.impls;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.jnet.server.services.JSocketExposeService;

public class JSocketExposeServiceImpl implements JSocketExposeService {

	private final String HTTP_OK = "HTTP/1.1 200";
	private final String CONTENT_TYPE_HTML = "Content-Type: text/html";
	private final String CONTENT_TYPE_OCTECT_STREAM = "Content-Type: application/octet-stream";
	private final String CONTENT_DISPOSITION = "Content-Disposition: attachment; filename=";

	@Override
	public void writeDirectoryToBrowser(final PrintWriter printWriter, final String directory, final String currentUrl,
			final OutputStream outputStream) {
		Path path = null;

		if (currentUrl == null)
			path = Paths.get(directory);
		else
			path = Paths.get(directory + "\\" + currentUrl);

		System.out.println("Received directory : " + directory);

		if (!Files.exists(path)) {
			throw new RuntimeException("File Does not exist!!");
		}

		if (Files.isDirectory(path)) {
			writeDirectoryToWebPage(printWriter, path, currentUrl);
		} else if (Files.isRegularFile(path))
			writeFileToWebPage(printWriter, path, outputStream);
	}

	private void writeDirectoryToWebPage(final PrintWriter printWriter, final Path path, final String currentUrl) {
		StringBuilder builder = new StringBuilder();
		printWriter.println(HTTP_OK);
		printWriter.println(CONTENT_TYPE_HTML);
		printWriter.println();
		builder.append("<html><body>");
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
			builder.append("<table><tr><th>name</th><th>size</th><th>download link</th></tr>");
			directoryStream.forEach(directory -> {
				builder.append("<tr>");
				builder.append("<td>");

				if (Files.isDirectory(path)) {
					builder.append("<a href ='/");

					if (currentUrl != null) {
						builder.append(currentUrl).append("/");
					}

					builder.append(directory.getFileName().toString()).append("'>")
							.append(directory.getFileName().toString()).append("</a>");
				} else {
					builder.append(directory.getFileName().toString());
				}
				builder.append("</td>");
				builder.append("<td>");

				if (Files.isDirectory(directory)) {
					builder.append("Directory");
				} else if (Files.isRegularFile(directory)) {
					try {
						builder.append(Files.size(directory));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					builder.append("");
				}

				builder.append("</td>");
				builder.append("<td>");

				if (Files.isDirectory(directory)) {
					builder.append("");
				} else if (Files.isRegularFile(directory)) {
					builder.append("<a href = '/");
					if (currentUrl != null) {

						builder.append(currentUrl).append("/");
					}
					builder.append(directory.getFileName().toString()).append("'>download</a>");
				}

				builder.append("</td>");
				builder.append("</tr>");
			});
			builder.append("</table>");
			builder.append("</body></html>");
			printWriter.println(builder.toString());
			printWriter.println();
			printWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeFileToWebPage(PrintWriter printWriter, Path path, OutputStream outputStream) {
		printWriter.println(HTTP_OK);
		printWriter.println(CONTENT_TYPE_OCTECT_STREAM);
		printWriter.println(CONTENT_DISPOSITION + path.getFileName());
		printWriter.println();

		try (FileInputStream fileInputStream = new FileInputStream(path.toString())) {
			byte[] buffer = new byte[4096];

			int bytesRead;
			while ((bytesRead = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
