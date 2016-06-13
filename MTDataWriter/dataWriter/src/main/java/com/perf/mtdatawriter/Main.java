/**
 * 
 */
package com.perf.mtdatawriter;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * @author shashank.upadhyay
 * 
 */
public class Main {

	/**
	 * @param args
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		int recordsPerFile = 100000;
		int numFiles = 20;

		CallableFutures cf = new CallableFutures(numFiles, recordsPerFile);
		try {
			cf.execute();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		createMergedFile();

	}

	private static void createMergedFile() {
		String os = System.getProperty("os.name");
		if (os.toUpperCase().trim().contains("WIN"))
			executeCommand("dir & cd ./output/ & copy /b *.csv merged.csv", true);
		else if (os.toUpperCase().trim().contains("WIN"))
			executeCommand("ls ; cd ./output/ ; cat *.csv > merged.csv", false);

	}

	private static void executeCommand(String command, boolean isWin) {

		try {
			ProcessBuilder pb = null;

			if (isWin)
				pb = new ProcessBuilder("cmd", "/c", command);
			else
				pb = new ProcessBuilder(command);

			pb.redirectError();
			Process p = pb.start();
			InputStreamConsumer isc = new InputStreamConsumer(p.getInputStream());
			isc.start();
			int exitCode = p.waitFor();

			isc.join();
			System.out.println("Process terminated with " + exitCode);
		} catch (IOException | InterruptedException exp) {
			exp.printStackTrace();
		}

	}

}
