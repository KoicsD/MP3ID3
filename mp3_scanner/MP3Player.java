package mp3_scanner;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class MP3Player extends Application {
	private static final String MEDIA_URL = "https://raw.githubusercontent.com/KoicsD/MP3ID3/master/Data/Queen/Queen%20-%20Don39t%20Stop%20Me%20Now.mp3";
	//private static final String MEDIA_URL = "https://www.youtube.com/watch?v=-S_QhVHGKZQ";
	private MediaPlayer mediaPlayer;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Media media = new Media(MEDIA_URL);
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();
		System.out.println("end of start method");
	}

	public static void main(String[] args) {
		launch(args);
		System.out.println("end of main method");
	}
}
