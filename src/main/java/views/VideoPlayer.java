package views;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;


import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class VideoPlayer extends JPanel {
	private Canvas videoSurface;
	private EmbeddedMediaPlayer mediaPlayer;
	
	public VideoPlayer() {
		registerLibrary();
		this.setLayout(new BorderLayout());
		videoSurface = new Canvas();
		videoSurface.setBackground(Color.BLACK);
		
		final List<String> vlcArgs = new ArrayList<String>();
		configureParameters(vlcArgs);
		
		this.add(videoSurface,BorderLayout.CENTER);
		
		mediaPlayer = createPlayer(vlcArgs, videoSurface);
		}


	/**
	 *  Runs the audio/video file
	 */
	public void play(final String filename) {
	
		mediaPlayer.playMedia(filename);
		
	}
	

	/**
	 *  Important: Notice where is the libvlc, which contains all native functions to manipulate the player
	 * 
	 *  Windows: libvlc.dll
	 *  Linux: libvlc.so
	 */
	private void registerLibrary() {
		NativeLibrary.addSearchPath(
				RuntimeUtil.getLibVlcLibraryName(), "d:/vlc-2.2.1");
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		LibXUtil.initialise();
	}

	




	/**
	 * Configure VLC parameters
	 */
	private void configureParameters(final List<String> vlcArgs) {
		vlcArgs.add("--no-plugins-cache");
		vlcArgs.add("--no-video-title-show");
		
		// Important, if this parameter is not set on Windows, the app won't work
		if (RuntimeUtil.isWindows()) {
			vlcArgs.add("--plugin-path=D:\\vlc-2.2.1\\plugins");
		}
	}


	/**
	 * Build the player
	 */
	private EmbeddedMediaPlayer createPlayer(final List<String> vlcArgs, final Canvas videoSurface) {



		MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(vlcArgs.toArray(new String[vlcArgs.size()]));
		mediaPlayerFactory.setUserAgent("vlcj test player");

		EmbeddedMediaPlayer embeddedMediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();

		embeddedMediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(videoSurface));
		embeddedMediaPlayer.setPlaySubItems(true);


		return embeddedMediaPlayer;
	}
	
}





   


