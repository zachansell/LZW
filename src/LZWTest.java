import java.io.File;
import java.io.IOException;
import java.util.List;

public class LZWTest {

	public static void main(String[] args) throws IOException {
		
		
		Lzw lzw = new Lzw();
		String input = "aababab";
		List<Integer> results = lzw.compress(9,input,"testoutput");
		
		Decompressor hi = new Decompressor(9,"3.txt");
	
		FileUtility fileUtility = new FileUtility();

		String fileContent = fileUtility.readTextFile("./src/lzw-file1.txt");
		System.out.println(" file content: " + fileContent);
		
	}
		



	
				
}


