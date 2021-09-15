import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

//this boi reads in bytes (bytecount) bytes at a time in order to go through de-ctionairy process
// and then we go brr, c, n, c+1st of next, add c+1st of next to dict
// if n not exist, add another entry, most recent entry + 1st byte from most recent
public class Decompressor {
	public Decompressor(int bytecount, String inputfile) throws IOException
	{
		HashMap<Integer,String>dict = new HashMap();
		for(char ch = 32;ch<=126;ch++)
		{
			dict.put((int) ch,""+ch);
		}
		File file = new File(inputfile);
		FileInputStream ree = new FileInputStream(inputfile);
		byte[] array = new byte[(int)file.length()];
		ArrayList<Integer> indexlist=new ArrayList();
		ree.read(array);
		StringBuffer builder = new StringBuffer();
		for(byte boi:array)
		{
			int indivbyte = boi & 0xff;
			
			//System.out.println(indivbyte);
			//System.out.println(Integer.toBinaryString(boi));
			String binarystring = Integer.toBinaryString(indivbyte);
			
			if(binarystring.length()<bytecount&&binarystring.charAt(0)=='1')
			{
				binarystring = String.format("%"+bytecount+"s",binarystring);
				binarystring = binarystring.replaceAll(" ","0");
				builder.append(binarystring);
			}
			else if(binarystring.length()>bytecount){
				builder.append(binarystring);
			}

		//	System.out.println(Integer.toBinaryString(boi)+"added "+builder.toString());
		}
		//this thing is the integer version of the byte
		String bytething ="";
		for(int c=0;c<builder.length();c++)
		{
			bytething+=builder.charAt(c);
			if(bytething.length()==bytecount)
			{
				indexlist.add(Integer.parseInt(bytething,2));
				
				bytething="";
				}
		}
		
		System.out.println(indexlist);
		//begin decompression algorithm
		int newestindex = 127;
		int current = indexlist.get(0);
		int next = -1;
		StringBuffer buffer = new StringBuffer();
		for(int a=0;a<indexlist.size()-1;a++)
		{
			String currentstr = dict.get(current);
			next = indexlist.get(a+1);
			//assumes no null shenanigans
			
			if(dict.containsKey(next))
			{
				
				String nextstr = dict.get(next);
				dict.put(newestindex,currentstr+nextstr.charAt(0));
				
				newestindex++;
			}
			else {
				
				dict.put(newestindex, currentstr+currentstr.charAt(0));
				newestindex++;
			}
			current=next;
			buffer.append(currentstr);
		}
		buffer.append(dict.get(current));
		System.out.println(buffer.toString());
	}
}
