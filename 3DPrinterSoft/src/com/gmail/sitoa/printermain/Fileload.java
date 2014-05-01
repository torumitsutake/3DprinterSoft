package com.gmail.sitoa.printermain;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Fileload extends JFrame implements ActionListener{
	private final JLabel label = new JLabel("ファイルを選択してください");
	private JTextField txtFile;
	private JTextField txtNozuru;
	private File file;
	private static Fileload frame;
	
	public static void fileload() {
					frame = new Fileload();
					frame.setVisible(true);
					frame.setSize(450, 300);
	}
	
	public Fileload() {
		getContentPane().setLayout(null);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(84, 12, 273, 33);
		getContentPane().add(label);
		
		txtFile = new JTextField();
		txtFile.setText("File");
		txtFile.setBounds(52, 122, 250, 23);
		getContentPane().add(txtFile);
		txtFile.setColumns(10);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(this);
		btnSelect.setActionCommand("select");
		btnSelect.setBounds(314, 122, 108, 23);
		getContentPane().add(btnSelect);
		
		txtNozuru = new JTextField();
		txtNozuru.setText("0.0");
		txtNozuru.setBounds(53, 181, 104, 17);
		getContentPane().add(txtNozuru);
		txtNozuru.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setActionCommand("go");
		btnOk.setBounds(314, 208, 108, 23);
		getContentPane().add(btnOk);
		
		JLabel lblFile = new JLabel("File");
		lblFile.setBounds(52, 87, 64, 13);
		getContentPane().add(lblFile);
		
		JLabel label_1 = new JLabel("ノズル大きさ");
		label_1.setBounds(52, 157, 85, 13);
		getContentPane().add(label_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("go")){
			double nozuru = Double.valueOf(txtNozuru.getText());
			if(nozuru != 0 && file != null){
			Startclass.setfile(file);
			Startclass.setnozuru(nozuru);
			try {
				Startclass.fileok();
				frame.setVisible(false);
				
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}else{
			 label.setText("ファイルまたはノズルサイズを設定してください");
			}
			
		}else if(e.getActionCommand().equalsIgnoreCase("select")){
		//ファイルロード設定
		    JFileChooser filechooser = new JFileChooser();

		    int selected = filechooser.showOpenDialog(this);
		    if (selected == JFileChooser.APPROVE_OPTION){
		       file = filechooser.getSelectedFile();
		      label.setText(file.getName());
		      String kakutyousi = kakutyousi(file.getName());
		      if(kakutyousi.equalsIgnoreCase("stl")){
		    	  txtFile.setText(file.getPath());
		      
		      }else{
		    	  label.setText("stlファイルを選択してください");
		      }
		    }else if (selected == JFileChooser.CANCEL_OPTION){
		      label.setText("キャンセルされました");
		    }else if (selected == JFileChooser.ERROR_OPTION){
		      label.setText("エラー又は取消しがありました");
		    }
		}
		
	}
	  public String kakutyousi(String fileName){
		  if (fileName == null)
		        return null;
		    int point = fileName.lastIndexOf(".");
		    if (point != -1) {
		        return fileName.substring(point + 1);
		    }
		    return fileName;
		  
		  
	  }
}
