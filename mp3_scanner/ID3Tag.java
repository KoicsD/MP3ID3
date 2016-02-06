package mp3_scanner;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class ID3Tag
{
	String title;
	private String artist;
	private String album;
	private int year;
	private String comment;
	private int track;
	private int genre;
	
	private ID3Tag(String title, String artist, String album, int year, String comment, int track, int genre)
	{
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.year = year;
		this.comment = comment;
		this.track = track;
		this.genre = genre;
	}
	
	private static byte[] readXBytes(byte[] bytes, int from, int to)
	{
		byte[] retBytes = new byte[to - from];
		{
			int j = 0;
			for (int i = from; i < to; ++i)
				retBytes[j++] = bytes[i];
		}
		return retBytes;
	}
	
	public static byte[] getTail(File file) throws IOException
	{
		RandomAccessFile fileHandler = new RandomAccessFile(file, "r");
		byte[] bytes = new byte[128];
		fileHandler.seek(fileHandler.length() - 128);
		fileHandler.read(bytes);
		fileHandler.close();
		return bytes;
	}
	
	public static boolean checkIfTag(byte[] bytes)
	{
		return bytes[0] == 'T' && bytes[1] == 'A' && bytes[2] == 'G';
	}
	
	public static ID3Tag parse(byte[] bytes)
	{
		// System.out.println(new String(readXBytes(bytes, 0, 3)));
		String title = new String(readXBytes(bytes, 3, 33)).trim();
		String artist = new String(readXBytes(bytes, 33, 63)).trim();
		String album = new String(readXBytes(bytes, 63, 93)).trim();
		int year;
		try {
			year = Integer.parseInt(new String(readXBytes(bytes, 93, 97)).trim());
		} catch (NumberFormatException e) {
			year = 0;
		}
		String comment; int track = 0; 
		if (bytes[125] == 0)
		{
			comment = new String(readXBytes(bytes, 97, 125)).trim();
			track = bytes[126];
		}
		else
		{
			comment = new String(readXBytes(bytes, 97, 127));
		}
		int genre = bytes[127];
		
		return new ID3Tag(title, artist, album, year, comment, track, genre);
	}
	
	public static ID3Tag readFile(File file) throws IOException
	{
		byte[] tail = getTail(file);
		if (checkIfTag(tail))
		{
			ID3Tag tag = parse(tail);
			return tag;
		}
		else
		{
			return null;
		}
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getArtist()
	{
		return artist;
	}

	public void setArtist(String artist)
	{
		this.artist = artist;
	}

	public String getAlbum()
	{
		return album;
	}

	public void setAlbum(String album)
	{
		this.album = album;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public int getTrack() {
		return track;
	}

	public void setTrack(int track) {
		this.track = track;
	}	
	
	public int getGenre()
	{
		return genre;
	}

	public void setGenre(int genre)
	{
		this.genre = genre;
	}
	
	@Override
	public String toString()
	{
		String content = "";
		content += "MP3 data:\n";
		content += "\tTitle: " + title + "\n";
		content += "\tArtist: " + artist + "\n";
		content += "\tAlbum: " + album + "\n";
		content += "\tYear: " + year + "\n";
		content += "\tComment: " + comment + "\n";
		content += "\tTrack: " + track + "\n";
		content += "\tGenre: " + genre + "\n";
		return content;
	}
}
