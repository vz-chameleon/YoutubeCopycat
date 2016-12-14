package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.AppController;

import elements.OurVideo;
import elements.addToPlaylistButton;

public class VideoTab extends JPanel{
	
	private VideoPlayer videoPlayer;
	private ImageIcon videoIcon;
	private JPanel videoDetails;
	private AppController app;
	
	public ImageIcon getVideoIcon() {
		return videoIcon;
	}

	public void setVideoIcon(ImageIcon videoIcon) {
		this.videoIcon = videoIcon;
	}

	public VideoTab(AppController app){
		super();
		this.app=app;
		this.setLayout(new BorderLayout());
		URL vidIconPath=SearchTab.class.getResource("/icons/search.png");
		videoIcon = new ImageIcon(vidIconPath);
		videoPlayer=new VideoPlayer();
		this.add(videoPlayer);
		videoDetails = new JPanel();
		videoDetails.setLayout(new BorderLayout());
		this.add(videoDetails,BorderLayout.SOUTH);
	}

public void setDetails(OurVideo ourVideo){
		System.out.println("VideoTab : setting details for "+ ourVideo.getName()+"    ");
		videoDetails.removeAll();
		
		JLabel title = new JLabel(ourVideo.getName()); 
		title.setFont(new Font("Arial",Font.BOLD|Font.LAYOUT_LEFT_TO_RIGHT,16));
		Box vbox1= Box.createVerticalBox();
		vbox1.add(new JLabel("Published on : "+ourVideo.getPublishedAt().toString()));
		vbox1.add(new JLabel("Published by : "+ourVideo.getChannelTitle()));
		Box vbox2= Box.createVerticalBox();
		vbox2.add(new JLabel(ourVideo.getViewCount()+" views"));
		vbox2.add(new JLabel(ourVideo.getLikeCount()+" likes / "+ourVideo.getDislikeCount() + " dislikes"));
		Box hbox = Box.createHorizontalBox();

		hbox.add(vbox1);
		hbox.add(Box.createHorizontalGlue());
		hbox.add(vbox2);
		Box vBox = Box.createVerticalBox();
		
		vBox.add(hbox);
		videoDetails.add(title,BorderLayout.NORTH);
		videoDetails.add(vBox);
		videoDetails.add(new addToPlaylistButton(ourVideo, app),BorderLayout.EAST);
	}
	
	
	
	public void playVideo(String url) throws InterruptedException{
		videoPlayer.play(url);

		// Waits until the player window be closed
		//Thread.currentThread().join();
	}
}
