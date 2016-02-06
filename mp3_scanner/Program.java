package mp3_scanner;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Program {
	private static final String A = File.separator;
	private static final String FILE_PATH = "Data/";

	public static void main(String[] args) {
		List<File> files = directory_scanner.DirectoryScanner.listFilesRecursively(new File(FILE_PATH), new MP3Filter());
		for (File file: files) {
			System.out.println(file.getAbsolutePath());
			if (file.isFile())
			{
				try
				{
					ID3Tag tag = ID3Tag.readFile(file);
					if (tag != null)
					{
						System.out.println(tag);
					}
				}
				catch (IOException e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		
	}
}
