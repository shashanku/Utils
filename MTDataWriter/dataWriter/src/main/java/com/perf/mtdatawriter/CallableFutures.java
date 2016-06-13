package com.perf.mtdatawriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class CallableFutures {

	private int noOfFile = 0;
	private int occurrances;

	public CallableFutures(int numFiles, int occurrances) {

		this.noOfFile = numFiles;
		this.occurrances = occurrances;

	}

	public void execute() throws ParserConfigurationException, SAXException, IOException {

		createData(noOfFile);
	}

	private void createData(int noOfThreads) {
		ExecutorService executor = Executors.newFixedThreadPool(Constants.NTHREDS);
		List<Future<String>> list = new ArrayList<Future<String>>();

		for (int i = 0; i < noOfThreads; i++) {
			Callable<String> worker = new CSVDataCreator(occurrances);

			Future<String> submit = executor.submit(worker);
			list.add(submit);
		}

		String sum = Constants.Empty;
		System.out.println(list.size());

		for (Future<String> future : list) {
			try {
				sum = future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sum);
		executor.shutdown();

	}
}