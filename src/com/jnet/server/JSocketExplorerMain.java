package com.jnet.server;

import com.jnet.server.services.HostFileService;
import com.jnet.server.services.impls.HostFileSingleThreadedImpl;

public class JSocketExplorerMain {
	private static final String FILE_ARGUMENT = "--file";
	private static final String PORT_ARGUEMENT = "--port";
	private static final String INVALID_ARGUEMENT_STR = "Invalid Arguement";

	public static void main(String[] args) {

		try {
			if (args.length < 4) 
				throw new RuntimeException("Invalid number of arguments, Provide Directory and Port! \n --file followed by path \n --port followed by port");

			String directory = null;
			int port = 0;
			
			for (int i = 0; i < args.length; i++) {
				if (FILE_ARGUMENT.equalsIgnoreCase(args[i])) {
					directory = args[++i];
				} else if (PORT_ARGUEMENT.equalsIgnoreCase(args[i])) {
					port = Integer.parseInt(args[++i]);
				} else {
					throw new RuntimeException(INVALID_ARGUEMENT_STR);
				}
			}

			HostFileService fileService = new HostFileSingleThreadedImpl();
			fileService.exposeFileToSocket(directory, port);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
