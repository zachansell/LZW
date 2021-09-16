
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;

public class Lzw {

	public static final int DEFAULT_DICTIONARY_SIZE = 256;

	//modified for compliance with my code™
	public List<Integer> compress(int bitcount,String input,String outputfile) throws IOException {

		int dictSize = DEFAULT_DICTIONARY_SIZE;
		Map<String,Integer> dictionary = buildDictionaryForCompression(dictSize);

		List<Integer> results = new ArrayList<Integer>();
		
		String word = "";
		int index = 0;
		for (char character : input.toCharArray()) {
			
			if(dictionary.containsKey(word+character))
			{
				word = word+character;

			}
			else {


				results.add(dictionary.get(word));
				
				//System.out.println("curr "+curr+" dict "+dict.get(curr));
				if(index<Math.pow(2,bitcount)-dictSize) {
					dictionary.put(word+character, dictSize+index);
					
				}
				word=""+character;
				index++;

			}
		}

		//make filewriter, initialize to have output with extension or just filename
		FileOutputStream writer;
		StringBuffer builder = new StringBuffer("");
		if(outputfile.contains(".txt")){
			writer= new FileOutputStream(outputfile);
		}
		else {
			writer = new FileOutputStream(outputfile+".txt");
		}
		 
		
		for(int a=0;a<results.size();a++)
		{

			//next		System.out.println(st)
			//System.out.println(stringyboi.get(a));
			//WHAT IS THIS
			if(!Objects.isNull(results.get(a)))
			{
				String binaryver = Integer.toBinaryString(results.get(a));
				binaryver = String.format("%"+bitcount+"s",binaryver);
				binaryver = binaryver.replaceAll(" ","0");
				short number = Short.parseShort(binaryver, 2);
				ByteBuffer bytebuf = ByteBuffer.allocate(2).putShort(number);

				byte[] bytes = bytebuf.array();
			//	byte[] bytes = new BigInteger(binaryver,2).toByteArray();
				writer.write(bytes);
			}
		}

		

		writer.close();
		return results;
	}


	private Map<String,Integer> buildDictionaryForCompression(int dictionarySize) {
		Map<String,Integer> dictionary = new HashMap<String,Integer>();
		for (int i = 0; i < dictionarySize; i++) {
			dictionary.put("" + (char)i, i);
		}
		return dictionary;
	}
}
