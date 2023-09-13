import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class TextEditor implements ActionListener {
	
	private static final String FileReader = null;
	//Declare the properties of text Editor
	JFrame frame; 
	JMenuBar menuBar;
	JMenu file,edit;
	JMenuItem newFile, openFile, saveFile;
	JMenuItem cut, copy, paste, selectAll, close;
	JTextArea textArea;
	
TextEditor(){
	
	//initialize a frame
	frame=new JFrame();
	
	
	//initialize the menu bar
	menuBar=new JMenuBar();
	
	//initialize text area
	textArea=new JTextArea();
	
	//initialize menus
	file=new JMenu("File");
	edit=new JMenu("Edit");
	
	//initialize file menu items
	newFile=new JMenuItem("New Window");
	openFile=new JMenuItem("Open File");
	saveFile=new JMenuItem("Save File");
	
	//add action listener to file menu item
	newFile.addActionListener(this);
	openFile.addActionListener(this);
	saveFile.addActionListener(this);
	
	//add menu to file menu
	file.add(newFile);
	file.add(openFile);
	file.add(saveFile);
	
	//initialize edit menu items
	cut=new JMenuItem("Cut");
	copy=new JMenuItem("Copy");
	paste=new JMenuItem("Paste");
	selectAll=new JMenuItem("Select All");
	close=new JMenuItem("Close");
	
	//add action listener to edit menu items
	cut.addActionListener(this);
	copy.addActionListener(this);
	paste.addActionListener(this);
	selectAll.addActionListener(this);
	close.addActionListener(this);
	
	//adding to edit menu
	edit.add(cut);
	edit.add(copy);
	edit.add(paste);
	edit.add(selectAll);
	edit.add(close);
	
	
	//add menus to menu bar
	menuBar.add(file);
	menuBar.add(edit);
	
	//set menu bar to frame 
	//we are using  setJMenuBar() to create a menu bar as it auto generates the menu bar 
	frame.setJMenuBar(menuBar);
	
	//create content pane
	JPanel panel = new JPanel();
	panel.setBorder(new EmptyBorder (5, 5, 5, 5));
	panel.setLayout(new BorderLayout(0, 0));
	
	//add text area to panel
	panel.add(textArea, BorderLayout.CENTER);
	
	//create scroll pane
	JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	//add scroll pane to panel
	panel.add(scrollPane);
	
	//add panel to frame
	frame.add(panel);
	
	//set dimension of frame
	frame.setBounds(0, 0, 500, 500);
	frame.setTitle("Text Editor");
	frame.setVisible(true);
	frame.setLayout(null);
}
@Override
public void actionPerformed(ActionEvent actionEvent) {
	if(actionEvent.getSource()==cut) {
		//perform cut operation
		textArea.cut();
	}
	if(actionEvent.getSource()==copy) {
		//perform copy operation
		textArea.copy();
	}
	if(actionEvent.getSource()==paste) {
		//perform paste operation
		textArea.paste();
	}
	if(actionEvent.getSource()==selectAll) {
		//perform select all operation
		textArea.selectAll();
	}
	if(actionEvent.getSource()==close) {
		//perform close operation
		System.exit(0);
	}
	
	if(actionEvent.getSource()==openFile) {
		//open file chooser
		JFileChooser fileChooser=new JFileChooser("C:");
		int chooseOption = fileChooser.showOpenDialog(null);
		//if we have clicked o open button
		if(chooseOption==JFileChooser.APPROVE_OPTION) {
			//getting selected file
			File file = fileChooser.getSelectedFile();
			//get the path of the selected file
			String filePath = file.getPath();
			try {
				//initialize the file reader
				FileReader fileReader=new FileReader(filePath);
				//initialize a buffer reader
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String intermediate="", output="";
				//read content of file line by line
				while((intermediate=bufferedReader.readLine())!=null ) {
					output+=intermediate+"\n";
				}
				//set the output string to text area
				textArea.setText(output);
			}
			catch(FileNotFoundException fileNotFoundException) {
				fileNotFoundException.printStackTrace();
			}
			catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
	if(actionEvent.getSource()==saveFile) {
		//initialize file chooser 
		JFileChooser fileChooser = new JFileChooser("C:");
		//get choose option from file chooser
		int chooseOption = fileChooser.showSaveDialog(null);
		//check if we click on save button 
		if(chooseOption == JFileChooser.APPROVE_OPTION) {
			//create new file with choosen directory path and file name
			File file= new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");
			try {
				//initialize file writer
				FileWriter fileWriter = new FileWriter(file);
				//initialize Buffered writer
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				//write content if text area
				textArea.write(bufferedWriter);
				bufferedWriter.close();
			}
			catch(IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
	if(actionEvent.getSource()==newFile) {
		TextEditor newTextEditor = new TextEditor();
	}
	
}
	public static void main(String[] args) {
TextEditor textEditor = new TextEditor();
	}

}
