package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import app.AppController;
import elements.OurVideo;
import elements.Playlist;
import elements.PlaylistVideoComponent;
import elements.deletePlaylistButton;
import elements.importPlaylistButton;
import elements.savePlaylistButton;


public class PlaylistRightSideBarView extends JPanel{
	private Playlist playlist;
	private AppController app;
	private JPanel result;
	private JScrollPane jsc;
	private Box bigvbox=Box.createVerticalBox();
	
	public PlaylistRightSideBarView(AppController app){
		super();
		this.setLayout(new BorderLayout());
		playlist = new Playlist();
		this.app=app;
		
		Box topPanel = Box.createVerticalBox();
		
		topPanel.add(new JLabel("Your Playlist"));
		Box hbox = Box.createHorizontalBox();
		hbox.add(new importPlaylistButton(this));
		hbox.add(new savePlaylistButton(playlist));
		hbox.add(new deletePlaylistButton(this));
		topPanel.add(hbox);
		this.add(topPanel,BorderLayout.NORTH);
		
		
		this.result = new JPanel();
		result.setLayout(new FlowLayout(FlowLayout.CENTER));
		//result.setLayout(new BoxLayout(result,BoxLayout.Y_AXIS));
		result.setPreferredSize(new Dimension(335,100));
		jsc = new JScrollPane(result);
		this.add(jsc);

		jsc.setPreferredSize(new Dimension(355,800));
		//jsc.setMaximumSize(new Dimension(350,800));
		jsc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		//bigvbox.setSize(new Dimension(350,800));
		jsc.setVisible(true);
	}
	
	public Playlist getPlaylist(){
		return playlist;
	}
	
	public void updatePlaylist(OurVideo video) throws MalformedURLException, IOException{
		if(!(playlist.existsIn(video))){
			playlist.addVideo(video);
			final PlaylistVideoComponent playlistComp = new PlaylistVideoComponent(playlist, video, app);
			playlistComp.addMouseListener(new MouseAdapter(){
		    	@Override
		    	public void mousePressed(MouseEvent e){
		    		app.readOurVideo(playlistComp.getVideo());
		    	}
		    });
			//playlistComp.setAlignmentX(0);
			//bigvbox.add(playlistComp);
			result.add(playlistComp);
			result.repaint();
			result.revalidate();
		}
		

	}
	public void remove(PlaylistVideoComponent pvc){
		playlist.removeVideo(pvc.getVideo());
		result.remove(pvc);
		result.repaint();
		result.revalidate();
	}

	public void setPlaylist(Playlist playlistChoice) {
		playlist.removeAll();
		result.removeAll();
		if (playlistChoice!=null)
			for (OurVideo ov : playlistChoice.getVideos()){
				final PlaylistVideoComponent playlistComp = new PlaylistVideoComponent(playlist, ov, app);
				playlistComp.addMouseListener(new MouseAdapter(){
		    	@Override
		    	public void mousePressed(MouseEvent e){
		    		app.readOurVideo(playlistComp.getVideo());
		    	}
		    });
				result.add(new PlaylistVideoComponent(playlistChoice, ov, app));
			}
		result.repaint();
		result.revalidate();
		
		
	}

	
	

}
