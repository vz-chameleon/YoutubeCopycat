package elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import com.google.api.client.util.DateTime;

import app.AppController;

public class LocalSearchButton extends JButton{
	public LocalSearchButton(ImageIcon icon2,final AppController app){
		super();
		this.setIcon(icon2);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			JFileChooser playlistChooser = new JFileChooser();
			playlistChooser.showOpenDialog(null);
			File choose = playlistChooser.getSelectedFile();

			DateTime date = new DateTime(System.currentTimeMillis());
			BigInteger zero = new BigInteger("0");
			OurVideo video = new OurVideo(choose.getAbsolutePath(), choose.getAbsolutePath(), zero, zero, zero, zero, "You", "Local video", date, "https://upload.wikimedia.org/wikipedia/commons/d/d5/Cyclic_quadrilateral.png", "100"); 
			app.readOurVideo(video);
			}
		});
	}
}
