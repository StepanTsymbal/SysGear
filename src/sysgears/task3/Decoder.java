package sysgears.task3;

public class Decoder {

	// driver's code length
	private static final int DRIVER_CODE_LENGTH = 4;
	
	/**
	 * returns decoded Item object
	 * 
	 * @param encodedString input String for decoding
	 * @return Item object with decoded Data from encodedString
	 * @throws Exception if input String has invalid format
	 * 
	 */
	public static Item getDecodedItem(String encodedString) throws Exception {
		
		Item item = new Item();
		
		// ammount of service symbols after the second block
		int stuffSymbols;
		// first block
		String cipher = encodedString;
		// driver's code (second block)
		String driverCode;
		// driver's code in the second block
		String wayBillDriverCode = "";
		// 'd' in second block (0 if is absent, 1 - otherwise)
		int danger = 0;
		// 'f' in second block (0 if is absent, 1 - otherwise)
		int fragile = 0;
		// third block
		String wayBill;
		// sixth block
		String temperature = "";
		// encoded seventh block
		String decodedItem;
		// length of (stuffSymbols + danger + fragile + 'r') part in the second block
		int rdfStuffSymbolsLength;
		// length of the billway number in the third block
		int wayBillNumberLength;

		try {
			stuffSymbols = getStuffSymbolsAmmount(cipher);
			danger = getDanger(stuffSymbols, cipher);
			fragile = getFragile(stuffSymbols, cipher, danger);
			driverCode = cipher.substring(0, 4);
			wayBillDriverCode = getWayBillDriverCode(cipher, stuffSymbols, danger, fragile, driverCode);
			wayBill = cipher.substring((DRIVER_CODE_LENGTH + stuffSymbols), (DRIVER_CODE_LENGTH + stuffSymbols + danger + fragile + ((danger == 1 || fragile == 1) ? 3 : 4) + wayBillDriverCode.length() + 1));
			rdfStuffSymbolsLength = stuffSymbols + fragile + danger + 1;
			wayBillNumberLength = (((danger == 1) || (fragile == 1)) ? 3 : 4);
			temperature = getTemperature(cipher, stuffSymbols, fragile, danger, wayBillDriverCode, rdfStuffSymbolsLength, wayBillNumberLength);
			decodedItem = getDecodedItem(cipher, rdfStuffSymbolsLength, wayBillNumberLength, wayBillDriverCode, temperature);

			item.setCipher(cipher);
			item.setDriverCode(driverCode);
			item.setDanger((danger == 1) ? true : false);
			item.setFragile((fragile == 1) ? true : false);
			item.setTemperature(temperature);
			item.setWayBill(wayBill);
			item.setDecodedItem(decodedItem);
			
			return item;
			
		} catch (Exception e) {
			throw new Exception("Incorrect input Data!");
		}

	}

	/**
	 * 
	 * returns number of service symbols after the second block (or '0' if they are absent)
	 * 
	 * @param encodedString string for decoding
	 * @return number of service symbols after the second block
	 * 
	 */
	private static int getStuffSymbolsAmmount(String encodedString) {
		if ((encodedString.substring(DRIVER_CODE_LENGTH, 7).indexOf('r') != -1)) {
			return encodedString.substring(DRIVER_CODE_LENGTH, 7).indexOf('r');
		} else {
			return encodedString.substring(DRIVER_CODE_LENGTH, 7).indexOf('R');
		}

	}

	/**
	 * 
	 * returns '1' if item is danger (has 'd') or '0' otherwise
	 * 
	 * @param stuffSymbols number of service symbols after the second block
	 * @param cipher string for decoding
	 * @return returns '1' if item is danger (has 'd') or '0' otherwise
	 * 
	 */
	private static int getDanger(int stuffSymbols, String cipher) {
		if ((cipher.substring((DRIVER_CODE_LENGTH + stuffSymbols), (DRIVER_CODE_LENGTH + stuffSymbols + 2)).indexOf('d')) != -1) {
			return 1;
		}
		
		return 0;
	}
	
	/**
	 * 
	 * returns '1' if item is fragile (has 'f') or '0' otherwise
	 * 
	 * @param stuffSymbols number of service symbols after the second block
	 * @param cipher string for decoding
	 * @param danger '1' if item is danger (has 'd') or '0' otherwise
	 * @return returns '1' if item is fragile (has 'f') or '0' otherwise
	 * 
	 */
	private static int getFragile(int stuffSymbols, String cipher, int danger) {
		if ((cipher.substring((DRIVER_CODE_LENGTH + stuffSymbols), (DRIVER_CODE_LENGTH + stuffSymbols + 2 + danger)).indexOf('f')) != -1) {
			return 1;
		}
		
		return 0;
	}
	
	/**
	 * returns temperature or "" otherwise
	 * 
	 * @param cipher
	 * @param stuffSymbols
	 * @param fragile
	 * @param danger
	 * @param wayBillDriverCode
	 * @param rdfStuffSymbolsLength
	 * @param wayBillNumberLength
	 * @return returns temperature or "" otherwise
	 * 
	 */
	private static String getTemperature(String cipher, int stuffSymbols, int fragile, int danger, String wayBillDriverCode, int rdfStuffSymbolsLength, int wayBillNumberLength) {
		if ((cipher.substring(DRIVER_CODE_LENGTH + rdfStuffSymbolsLength + wayBillNumberLength + wayBillDriverCode.length() - 1).indexOf('­') != -1)) {
			return cipher.substring((DRIVER_CODE_LENGTH + rdfStuffSymbolsLength + wayBillNumberLength + wayBillDriverCode.length()),
					(DRIVER_CODE_LENGTH + rdfStuffSymbolsLength + wayBillNumberLength + wayBillDriverCode.length() + 4));
		} 
		
		if ((cipher.substring(DRIVER_CODE_LENGTH + rdfStuffSymbolsLength + wayBillNumberLength + wayBillDriverCode.length() - 1).indexOf('+') != -1)) {
			return cipher.substring((DRIVER_CODE_LENGTH + rdfStuffSymbolsLength + wayBillNumberLength + wayBillDriverCode.length()),
					(DRIVER_CODE_LENGTH + rdfStuffSymbolsLength + wayBillNumberLength + wayBillDriverCode.length() + 4));
		}
		
		return "";
	}
	
	/**
	 * returns code of the driver in the WayBill block
	 * 
	 * @param cipher
	 * @param stuffSymbols
	 * @param danger
	 * @param fragile
	 * @param driverCode
	 * @return code of the driver in the WayBill block
	 * 
	 */
	private static String getWayBillDriverCode(String cipher, int stuffSymbols, int danger, int fragile, String driverCode) {
		
		if (cipher.substring((DRIVER_CODE_LENGTH + stuffSymbols), (DRIVER_CODE_LENGTH + stuffSymbols + 1 + danger + fragile + 8)).contains(driverCode)) {
			return driverCode;
		}
		
		return "";
	}

	/**
	 * 
	 * returns decoded name of the good
	 * 
	 * @param cipher
	 * @param rdfStuffSymbolsLength
	 * @param wayBillNumberLength
	 * @param wayBillDriverCode
	 * @param temperature
	 * @return returns decoded name of the good
	 * 
	 */
	private static String getDecodedItem(String cipher, int rdfStuffSymbolsLength, int wayBillNumberLength, String wayBillDriverCode, String temperature) {
		String encodedItem = cipher.substring(DRIVER_CODE_LENGTH + rdfStuffSymbolsLength + wayBillNumberLength + wayBillDriverCode.length() + temperature.length());
		String clearItem = encodedItem.substring(0,  (encodedItem.length() - encodedItem.length() % 3));
		StringBuilder sb = new StringBuilder();
		
		// regex for splitting the string into 3-symbol-length parts
		String[] encodedLetters = clearItem.split("(?<=\\G.{3})");
		
		for(String symbol : encodedLetters){
			// decoing of OCT ASCII numbers to letters
			sb.append((char) Integer.parseInt(symbol, 8));
		}
		
		return sb.toString();
	}
}
