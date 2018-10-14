package utilities;

public class TypeConverter {

	private static  String[][] types;
	private final int ROWS = 3;
	private final int COLS = 3;

	public TypeConverter() {
		TypeConverter.types = new String[ROWS][COLS];

		// row 0
		TypeConverter.types[0][0] = "";
		TypeConverter.types[0][1] = "";
		TypeConverter.types[0][2] = "";

		// row 1
		TypeConverter.types[1][0] = "";
		TypeConverter.types[1][1] = "";
		TypeConverter.types[1][2] = "";

		// row 2
		TypeConverter.types[2][0] = "";
		TypeConverter.types[2][1] = "";
		TypeConverter.types[2][2] = "";
	}

	private static int stringToInt(String s) {
		switch (s) {
		case "linteger":
			return 0;
		case "usinteger":
			return 1;
		case Constants.STRING:
			return 2;
		}
		return -1;
	}
	
	public static String getConversion(String s1, String s2) {
		int i1 =  stringToInt(s1);
		int i2 =  stringToInt(s2);
		return TypeConverter.types[i1][i2];
	}

}
