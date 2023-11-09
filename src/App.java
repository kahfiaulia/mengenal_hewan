import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import  sun.audio.*;

public class App {
	public static void main(String[] args) {
		//Menu utama
		JFrame frame = new JFrame("Mengenal Hewan");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 100);
		JPanel panelContent = new JPanel();
		frame.add(panelContent);
		
		JPanel panel = new JPanel();
		panel.setBounds(61, 11, 81, 140);
		
		panelContent.add(panel);
		
		
		//Polimorfisme
		Hewan[] listHewan = new Hewan[6];
		Hewan hewan = new Hewan();
		Katak katak = new Katak();
		Kucing kucing = new Kucing();
		Burung burung = new Burung();
		Kambing kambing = new Kambing();
		Harimau harimau = new Harimau();
		LumbaLumba lumbaLumba = new LumbaLumba();

		listHewan[0] = katak;
		listHewan[0].nama = "Katak";
		listHewan[1] = kucing;
		listHewan[1].nama = "Kucing";
		listHewan[2] = burung;
		listHewan[2].nama = "Burung";
		listHewan[3] = kambing;
		listHewan[3].nama = "Kambing";
		listHewan[4] = harimau;
		listHewan[4].nama = "Harimau";
		listHewan[5] = lumbaLumba;
		listHewan[5].nama = "Lumba-Lumba";
		
		
		JLabel nama = new JLabel(hewan.nama + " : ");
		panel.add(nama);
		
		for(Hewan x: listHewan) {
		JButton button = new JButton(x.nama);
	
		panel.add(button);
		button.addActionListener(new Action(x.nama, x.namaDlmBhsInggris(), x.jenisBerdasarTempatHidup(), x.jenisBerdasarMakanan(), x.gambar(), x.suara()));
		}
	}
	
	
	//Menu deskripsi
	static class Action implements ActionListener{
		String nama;
		String jenisBerdasarTempatHidup;
		String gambar;
		String jenisBerdasarMakanan;
		String suara;
		String namaDlmBhsInggris;
		
		public Action(String nama, String namaDlmBhsInggris, String jenisBerdasarTempatHidup, String jenisBerdasarMakanan, String gambar, String suara) {
			this.nama = nama;
			this.jenisBerdasarTempatHidup = jenisBerdasarTempatHidup;
			this.gambar = gambar;
			this.jenisBerdasarMakanan = jenisBerdasarMakanan;
			this.suara = suara;
			this.namaDlmBhsInggris = namaDlmBhsInggris;
		}
		
		public void actionPerformed (ActionEvent e) {
			JFrame frame2 = new JFrame("Deskripsi");
			frame2.setVisible(true);
			frame2.setSize(500, 450);
			JPanel panelContent2 = new JPanel();
			frame2.add(panelContent2);
			
			JPanel panel2 = new JPanel();
			panel2.setBounds(61, 11, 81, 140);
			panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
			
			panelContent2.add(panel2);
			
			
			JLabel space1 = new JLabel(" ");
			JLabel space2 = new JLabel(" ");
			JLabel space3 = new JLabel(" ");
			JLabel space4 = new JLabel(" ");
			JLabel space5 = new JLabel(" ");
			
			
			//Gambar hewan
			File file = new File(this.gambar);
			BufferedImage image;
			try {
				image = ImageIO.read(file);
				
				JLabel labelGambar = new JLabel();
				labelGambar.setSize(200, 200);
				
				Image g = image.getScaledInstance(labelGambar.getWidth(), labelGambar.getHeight(),
				        Image.SCALE_SMOOTH);
				JLabel gambar = new JLabel(new ImageIcon(g));
				
				panel2.add(gambar);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			panel2.add(space1);
			
			//Nama hewan
			JLabel nama = new JLabel("Nama : " + this.nama);
			nama.setForeground(Color.blue);
			panel2.add(nama);
			panel2.add(space2);
			
			//Nama dalam bahasa Inggris
			JLabel namaDlmBhsInggris = new JLabel("Nama dalam bahasa Inggris : " + this.namaDlmBhsInggris);
			namaDlmBhsInggris.setForeground(Color.blue);
			panel2.add(namaDlmBhsInggris);
			panel2.add(space5);
			
			//Jenis berdasarkan makanan
			JLabel jenisMakan = new JLabel("Makanan : " + this.jenisBerdasarMakanan);
			jenisMakan.setForeground(Color.blue);
			panel2.add(jenisMakan);
			panel2.add(space3);
			
			//Jenis berdasarkan tempat hidup
			JLabel jenisTempat = new JLabel("Tempat Hidup : " + this.jenisBerdasarTempatHidup);
			jenisTempat.setForeground(Color.blue);
			panel2.add(jenisTempat);
			panel2.add(space4);
			
			//Suara hewan
			JButton sound = new JButton("Suara");
			sound.setForeground(Color.blue);
			sound.addActionListener(new PlaySound(this.suara));
			panel2.add(sound);
			
		}
		


	}
	
	//Sound Plater
	static class PlaySound implements ActionListener{
		String path;
		
		public PlaySound(String path){
			this.path = path;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			InputStream sound;
			try
			{
				sound = new FileInputStream(new File(this.path));
				AudioStream audio = new AudioStream(sound);
				AudioPlayer.player.start(audio);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Error");
			}
			
		}
	}
}

