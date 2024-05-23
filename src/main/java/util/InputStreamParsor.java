package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamParsor {
	public static String parseToString(InputStream in) {
		String data = "";
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			while(br.ready()) {
				data += br.readLine() + "\n";
			}
			
			br.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
