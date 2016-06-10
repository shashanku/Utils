/**
 * 
 */
package com.perf.mtdatawriter;

/**
 * @author shashank.upadhyay
 *
 */
public class CSVData {
	String data1;
	String data2;
	String data3;

	public CSVData() {
		// TODO Auto-generated constructor stub
	}

	public CSVData(String data1, String data2, String data3) {
		super();
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
	}

	@Override
	public String toString() {
		return String.format("CSVData [data1=%s, data2=%s, data3=%s]", data1, data2, data3);
	}
}
