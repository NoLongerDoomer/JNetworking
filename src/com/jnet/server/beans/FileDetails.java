package com.jnet.server.beans;

public class FileDetails {

	private String fileName;
	private long fileSize;
	private boolean isFile;

	public FileDetails(String fileName, long fileSize, boolean isFile) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.isFile = isFile;
	}

	public String getFileName() {
		return fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public boolean isFile() {
		return isFile;
	}

}
