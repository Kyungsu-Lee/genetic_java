import java.io.*;

/**
 * Created by lks21c on 16. 1. 29.
 */
public class test{

	public static void main(String[] args) {
		run(args[0], "euc-kr");
	}

	private static void run(String path, String encoding) {
		BufferedReader br = null;
		String line;
		String cvsSplitBy = ",";
		BufferedWriter  fos;
		try {
			fos = new BufferedWriter(new FileWriter(path + "_back"));

			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));
			while ((line = br.readLine()) != null) {
				fos.write(line + '\n');
			}
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
