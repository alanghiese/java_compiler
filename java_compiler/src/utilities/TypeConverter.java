package utilities;

public class TypeConverter {

	private static  String[][] types = new String[TypeConverter.ROWS][TypeConverter.COLS];
	private final static int ROWS = 3;
	private final static int COLS = 3;
	private static boolean setted = false;

	public TypeConverter() {
		TypeConverter.types = new String[ROWS][COLS];

		// row 0
		TypeConverter.types[0][0] = Constants.L_INT;
		TypeConverter.types[0][1] = Constants.L_INT;
		TypeConverter.types[0][2] = Constants.CONV_NOT_ALLOWED;

		// row 1
		TypeConverter.types[1][0] = Constants.CONV_NOT_ALLOWED;
		TypeConverter.types[1][1] = Constants.US_INT;
		TypeConverter.types[1][2] = Constants.CONV_NOT_ALLOWED;

		// row 2
		TypeConverter.types[2][0] = Constants.CONV_NOT_ALLOWED;
		TypeConverter.types[2][1] = Constants.CONV_NOT_ALLOWED;
		TypeConverter.types[2][2] = Constants.STRING;
	}

	private static int stringToInt(String s) {
		
		switch (s) {
		case Constants.L_INT:
			return 0;
		case Constants.US_INT:
			return 1;
		case Constants.STRING:
			return 2;
		}
		return -1;
	}
	
	public static String getConversion(String s1, String s2) {
		
		if (!TypeConverter.setted) {
			// row 0
			TypeConverter.types[0][0] = Constants.L_INT;
			TypeConverter.types[0][1] = Constants.L_INT;
			TypeConverter.types[0][2] = Constants.CONV_NOT_ALLOWED;
	
			// row 1
			TypeConverter.types[1][0] = Constants.CONV_NOT_ALLOWED;
			TypeConverter.types[1][1] = Constants.US_INT;
			TypeConverter.types[1][2] = Constants.CONV_NOT_ALLOWED;
	
			// row 2
			TypeConverter.types[2][0] = Constants.CONV_NOT_ALLOWED;
			TypeConverter.types[2][1] = Constants.CONV_NOT_ALLOWED;
			TypeConverter.types[2][2] = Constants.STRING;
			
			TypeConverter.setted = true;
		}
		int i1 =  stringToInt(s1);
		int i2 =  stringToInt(s2);
		
		return TypeConverter.types[i1][i2];
	}
	
	public static boolean isValid(String type1, String type2) {
		return !TypeConverter.getConversion(type1,type2).equals(Constants.CONV_NOT_ALLOWED);
			
	}
	
	public static boolean isValidBidirectional(String type1, String type2) {
		return (isValid(type1,type2) || isValid(type2,type1));
			
	}

}
