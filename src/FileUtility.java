import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class FileUtility {

	public FileUtility() {}

	public String readTextFile(String fileName) {
		File file = new File(fileName);

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));


			StringBuilder fileContentInString = new StringBuilder();
			String lineInString = "";
			while ((lineInString = br.readLine()) != null) {
				fileContentInString.append(lineInString);
			}
			br.close();
			return fileContentInString.toString();
		} catch (FileNotFoundException e) {
			System.out.print(String.format(" Cannot find the file: %s", fileName));
			e.printStackTrace();
		} catch (IOException e) {
			System.out.print(String.format(" Cannot read the file: %s", fileName));
		}
		return "";

	}

	public byte[] convertToByteArray(List<Integer> results) {
		// "[1, 2, 3, 4]"
		// "1, 2, 3, 4]"
		// "1, 2, 3, 4"
		// "1,2,3,4"
		return results.toString().replace("[","").replace("]","").replace(" ", "").getBytes();
	}


	public void writeBytesToFile(byte[] resultInBytes, String fileName) {
		File outputFile =  new File(fileName);
		try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
			outputStream.write(resultInBytes);
		} catch (IOException e) {
			System.out.print(String.format(" Cannot read the file: %s", fileName));
		}
	}

	public List<Integer> convertToIntegerList(String compressedContent) {
		 List<Integer> results = new ArrayList<Integer>();
		 
		 if(compressedContent == null && "".equals(compressedContent) || !compressedContent.contains(",")) {
			 System.out.println("Empty content");
			 return results;
		 }
		 
		 String[] units = compressedContent.split(",");
		 for( String unit: units) {
			 results.add(Integer.valueOf(unit));
		 }
		 
		 return results;
	}

}
