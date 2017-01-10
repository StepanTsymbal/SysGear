package sysgears.task3;

import java.io.FileWriter;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.List;

/**
 * 
 * creates CSV file and writes in it List of Item elements
 *
 */
public class CsvFileWriter {
	
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private static final String FILE_HEADER = "шифр,код водителя,код путевого листа,опасный,хрупкий,температура,наименование";

	/**
	 * 
	 * @param fileName name of CSV file for writing
	 * @param itemList data for writing
	 * 
	 */
	public static void writeCsvFile(String fileName, List<String> itemList) {
		
		FileWriter fileWriter = null;
		Item item;
				
		try {
			fileWriter = new FileWriter(fileName);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new student object list to the CSV file
//			for (Item item : itemList) {
			for (String decodedItem : itemList) {
				
				item = Decoder.getDecodedItem(decodedItem);
				
				fileWriter.append(item.getCipher());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(item.getDriverCode());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(item.getWayBill());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append((item.isDanger()) ? "true" : "false");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(item.isFragile() ? "true" : "false");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(item.getTemperature());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(item.getDecodedItem());
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			System.out.println("CSV file was created successfully.");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter!");
                e.printStackTrace();
			}
			
		}
	}
}

