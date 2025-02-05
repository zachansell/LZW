import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LZWTest {

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		Lzw lzw = new Lzw();
		String input = "aababab";//Testing with basic string
		List<Integer> results = lzw.compress(input);
		System.out.println(results);
		String output = lzw.decompress(results);
		System.out.println("input and output should be equal! " + input.equals(output));
		
		//testing first file
		FileUtility fileUtility = new FileUtility();
		String fileContent = fileUtility.readTextFile("./src/lzw-file1.txt");
		System.out.println(" file content: " + fileContent);

		byte[] resultInBytes = fileUtility.convertToByteArray(results);

		System.out.println(resultInBytes);


		fileUtility.writeBytesToFile(resultInBytes, "./src/lzw-file1-output.lzw");

		// Testing Decompression
		String compressedContent = fileUtility.readTextFile("./src/lzw-file1-output.lzw");

		List<Integer> compressedDataFromFile = fileUtility.convertToIntegerList(compressedContent);

		System.out.println("compressed data file: " + compressedDataFromFile);

		String  decompressContent = lzw.decompress(compressedDataFromFile);

		System.out.println("Decompressed Content: "+decompressContent);


		// testing with a bigger file
		String fileBigContent = fileUtility.readTextFile("./src/lzw-file3.txt");
		List<Integer> compressedBigContent = lzw.compress(fileBigContent);
		byte[] bigContentResultInBytes = fileUtility.convertToByteArray(compressedBigContent);

		fileUtility.writeBytesToFile(bigContentResultInBytes, "./src/lzw-file3-output.lzw");
		//decompressing and write to file: 
		compressedContent = fileUtility.readTextFile("./src/lzw-file3-output.lzw");
		compressedDataFromFile = fileUtility.convertToIntegerList(compressedContent);
		decompressContent = lzw.decompress(compressedDataFromFile);
		PrintWriter writer = new PrintWriter("./src/lzw-file3-output-decoded.txt");
		writer.print(decompressContent);
		writer.close(); 
		System.out.println(decompressContent);


		System.out.println("Total time in milliseconds spent: "+(System.currentTimeMillis()-startTime));
		//Note: Original time = 234ms (first time it was run)
		//Time after optimizations: ~80ms on average
	}

}