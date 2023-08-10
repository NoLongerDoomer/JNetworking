package com.jnet.server.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jnet.server.beans.FileDetails;

public class FileManager {

	private String directory;

	public FileManager(String directory) {
		super();
		this.directory = directory;
	}

	public File getFile(String path) {
		return new File(directory, path);
	}

	public boolean isDirectory(String path) {
		File file = new File(path);
		return file.exists() && file.isDirectory();
	}

	public List<FileDetails> listFile(String path) {
		File directory = getFile(path);

		List<FileDetails> fileDetails = new ArrayList<>();

		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files != null && files.length > 0)
				for (File file : files) {
					fileDetails.add(new FileDetails(file.getName(), file.length(), file.isFile()));
				}
		}

		return fileDetails;

	}

}
