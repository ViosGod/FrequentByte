/*
*  Считывать с консоли имена файлов, пока не будет введено слово exit
*  Передайте имя файла в нить. Назовем нить Thread. Нить ReadThread должна найти байт, который
*  встречается в файле максимальное число раз. И добавить его в словарь resultMap, где параметр String - имя файла,
*  а параметр Integer - искомый байт. Закрыть потоки.
*/
import java.util.*;
import java.io.*;

public class FrequentByteFinderTester extends Thread{
	private Map<String, Integer> resultMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		while (true){
			String fileName = console.readLine();
			if (!"exit".equals(fileName)){
				ReadThread readThread = new ReadThread(fileName);
				readThread.start();
			}
			else break;
		}
		console.close()

		//testing resultMap

		foreach(Map.Entry<String, Integer> pair : resultMap.entrySet()) {
			String flNm = pair.getKey();
			int maxByte = pair.getValue();
			System.out.println(flNm + " " + maxByte);
		}
	}
}