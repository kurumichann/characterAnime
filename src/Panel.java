import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Panel implements ActionListener{
	
	private JTextArea textArea;
	private JPanel textPanel;
	
	public Panel(String path){
		
		JFrame jframe  = new JFrame("comic downloader");
		jframe.setSize(1200,720);
		Container con = jframe.getContentPane();
//		
//		JPanel textPanel = new JPanel();
//		textPanel.setLayout(new FlowLayout());
//		textPanel.setPreferredSize(new Dimension(1200,720));
		
		textArea = new JTextArea();				
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setPreferredSize(new Dimension(1200,720));
		textArea.setBorder(BorderFactory.createLoweredBevelBorder());
		Font x = new Font("Courier",Font.PLAIN,5);
		textArea.setFont(x);
		
		con.setLayout(new BorderLayout());
		con.add(textArea,BorderLayout.CENTER);
		jframe.setResizable(false);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        printVideo(path, textArea);
	}
	
	public String getPic(){
		return null;
	}
	
	/**
	 * param Path
	 * param textArea
	 */
	public void printVideo(String path, JTextArea textArea){
		File root = new File(path);
		String[] files = root.list();
		int length = files.length;
		String temp;
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try{
			for( int i = 0 ; i < length ; i++){
				reader = new BufferedReader(new FileReader((path+"/"+files[i])));
	            while ((temp = reader.readLine()) != null) {
	            	buffer.append(temp);
	            	buffer.append("\n");
	            }
	            textArea.setText(buffer.toString());
	            System.out.println(buffer.toString());
	            //buffer.setLength(0);
	        } 
			reader.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Panel("/Users/zhongbingyi/Downloads/temp");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
