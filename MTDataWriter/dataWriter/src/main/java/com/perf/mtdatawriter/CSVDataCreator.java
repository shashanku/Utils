/**
 * 
 */
package com.perf.mtdatawriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.Callable;

/**
 * @author shashank.upadhyay
 * 
 */
public class CSVDataCreator implements Callable<String> {

	int rowsPerFile;
	boolean isTest = false;

	public CSVDataCreator() {
	}

	public CSVDataCreator(final int rowsPerFile) {
		this.rowsPerFile = rowsPerFile;
	}

	public String call() throws Exception {

		String threadId = String.valueOf(Thread.currentThread().getId());

		double start = System.currentTimeMillis();

		if (isTest) {

			System.out.println(threadId + ": START");
			Thread.sleep(5000);
			System.out.println(threadId + ": END");
			return "0|OK";

		}

		String retval = "-1|Error";

		String fileName = String.valueOf(System.currentTimeMillis()) + "_" + threadId;
		// System.out.println("FileName:" + fileName);

		File file = new File("./output/" + fileName + ".csv");

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		// System.out.println("ThreadId_" + threadId + ": START with fileName:"
		// + file.getName());

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < rowsPerFile; i++)
			bw.write("Diff:23885.0,Diff:25932.0,Diff:25948.0,Diff:25955.0,Diff:28743.0,Diff:28815.0,Diff:39419.0,Diff:45921.0,Diff:80307.0,Diff:89755.0"
					+ "\n");

		bw.close();

		double end = System.currentTimeMillis();
		// System.out.println("ThreadId_" + threadId + ": END with fileName:" +
		// file.getName());
		System.out.println("ThreadId_" + threadId + ": " + (end - start) / 1000 + "(s) with fileName:" + file.getName());

		retval = "0|OK";

		return retval;
	}
}
