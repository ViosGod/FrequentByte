import java.util.*;
import java.io.*;

public class ReadThread extends Thread {
	private String fileName;
	public ReadThread(String fileName) {
		this.fileName = fileName;
	}
	 //inplementing file reading
	public void run() {
		//FileInputStream fileInputStream = null;
		try (FileInputStream fileInputStream = new FileInputStream(this.fileName)) {
			Map<Integer, Long> bytes = new HashMap<>();
			while (fileInputStream.available()>0) {
				int aByte = fileInputStream.read();
				if (!bytes.containsKey(aByte))
					bytes.put(aByte, 1L);
				else
					bytes.put(aByte, bytes.get(aByte)+1);
			}
				int maxByte=0;
				long maxCount=0;
				for(Map.Entry<Integer, Long>pair : bytes.entrySet()) {
					int aByte = pair.getKey();
					long count = pair.getValue();
					if (count>maxCount) {
					maxCount=count;
					maxByte=aByte;
					}

				}

				
			synchronized(this) {
				FrequentByteFinderTester.resultMap.put(this.fileName, maxByte);
			}				
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	}
