package mp3_scanner;

import java.io.File;
import java.io.FileFilter;

public class MP3Filter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		return pathname.isDirectory() ||
				pathname.getName().endsWith(".mp3") ||
				pathname.getName().endsWith("MP3");
	}

}
