package com.perf.mtdatawriter;

public class Constants {

	public static final String Empty = "";
	public static final String pathSeperator = "//";
	public static final String CHARSET = "abcdefghijklmnopqrstuvwxyz";
	public static final int NTHREDS = 10;

	public static enum Operation {
		MULTIPLE("MULTIPLE"), MULTILEVEL("MULTILEVEL"), ERROR("Error");

		private String text;

		Operation(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}

		public static Operation fromString(String text) {
			if (text != null) {
				for (Operation b : Operation.values()) {
					if (text.equalsIgnoreCase(b.text)) {
						return b;
					}
				}
			}
			return Operation.ERROR;
		}
	}
}
