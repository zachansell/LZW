import java.io.File;
import java.io.IOException;
import java.util.List;

public class LZWTest {

	public static void main(String[] args) throws IOException {
		
		Decompressor hi = new Decompressor(9,"boi.txt");
		Lzw lzw = new Lzw();
		String input = "aababab";
		List<Integer> results = lzw.compress(input);
		System.out.println(results);
		String output = lzw.decompress(results);
		System.out.println("input and output should be equal! " + input.equals(output));
		FileUtility fileUtility = new FileUtility();
		File thisfile = new File(".");
		System.out.println(thisfile.getAbsolutePath());
		String fileContent = fileUtility.readTextFile("./src/lzw-file1.txt");
		System.out.println(" file content: " + fileContent);
		
		byte[] resultInBytes = fileUtility.convertToByteArray(results);
		for(byte boi:resultInBytes)
		{
		System.out.println(boi);
	}
		
		fileUtility.writeBytesToFile(resultInBytes, "./src/lzw-file1-output.txt");

		// Testing Decompression
		String compressedContent = fileUtility.readTextFile("./src/lzw-file1-output.lzw");

		List<Integer> compressedDataFromFile = fileUtility.convertToIntegerList(compressedContent);
		
		System.out.println("compress data file: " + compressedDataFromFile);
		
		String  decompressContent = lzw.decompress(compressedDataFromFile);
		
		System.out.println(decompressContent);
		
		
		// testing with a bigger file
		String fileBigContent = fileUtility.readTextFile("./src/lzw-file3.txt");
		List<Integer> compressedBigContent = lzw.compress(fileBigContent);
		byte[] bigContentResultInBytes = fileUtility.convertToByteArray(compressedBigContent);
		
		fileUtility.writeBytesToFile(bigContentResultInBytes, "./src/zw-file3-output.lzw");



	}
				
}


