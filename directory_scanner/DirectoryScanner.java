package directory_scanner;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class DirectoryScanner {
	private static List<File> fileList;
	
	public static List<File> listFilesRecursively(File file, FileFilter filter) {
		fileList = new ArrayList<>();
		fileList.add(file);
		
		for (int i = 0; i < fileList.size(); ++i) {
			File currentObject = fileList.get(i);
			if (currentObject.isDirectory()) {
				File[] subObjects = currentObject.listFiles(filter);
				for (File subObject: subObjects) {
					fileList.add(subObject);
				}
				fileList.remove(i--);
			}
		}
		
		return fileList;
	}
}
