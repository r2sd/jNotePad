import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
class JApFrame extends JFrame implements ActionListener{
	JFrame f2;
	JFrame f3;
	String msg;
	JButton bFont;
	JMenuBar mb;
	JMenu file,edit,esub;
	JTextField tffSize,tffName;
	JMenuItem open,save,saveas,nw,exit,colour,cut,copy,paste,sall,fnr,incSize;
	JCheckBoxMenuItem wwrap;
	JTextArea ta;
	FileDialog fd;
	String name,path,n;
	String find;
	  	String repl;
	  	String tot;
	  	String temp;
	  	String t2;
	boolean flag=false;
	int fSize=17;
	String fName;
	
	//FNR
	JLabel l1=new JLabel("Find :");
    JTextField tf=new JTextField(10);
    JLabel l2=new JLabel("Replacement Text :");
    JTextField tf2=new JTextField(10);
    JPanel p=new JPanel();
    JPanel inner=new JPanel();
    BorderLayout bl=new BorderLayout();
    JButton b1=new JButton("Replace All");
    JButton b2=new JButton("Close");
    
    //FNRENDS
	
	JApFrame (String titleBarName){
		super("jNotePad");
		fd=new FileDialog(this,"Save As",FileDialog.SAVE);		
		msg="Enter text here";
		ta=new JTextArea();
		ta.getDocument().addDocumentListener(new DocumentListener()
		{
		    
			public void insertUpdate(DocumentEvent arg0) {
				flag=true;
			}

			public void removeUpdate(DocumentEvent arg0) {
				flag=true;				
			}

			public void changedUpdate(DocumentEvent arg0) {
				
			}
		});
		ta.setEditable(true);
		ta.setLineWrap(true);
		ta.setFont(new Font("",Font.PLAIN,fSize));
		add(ta);
		mb=new JMenuBar();
		file=new JMenu("File");
		edit=new JMenu("Edit");
		open=new JMenuItem("Open");
		save=new JMenuItem("Save");
		saveas=new JMenuItem("Save As");
		nw=new JMenuItem("New");
		exit=new JMenuItem("Exit");
		fnr=new JMenuItem("Find and Replace");
		incSize=new JMenuItem("Font");//Opens font dialog box
		colour=new JMenuItem("Invert Colours/Night Mode");
		cut=new JMenuItem("Cut");
		copy=new JMenuItem("Copy");
		paste=new JMenuItem("Paste");
		sall=new JMenuItem("Select All");
		wwrap=new JCheckBoxMenuItem("Word Wrap");
		nw.addActionListener(this);
		colour.addActionListener(this);
	    open.addActionListener(this);
	    save.addActionListener(this);
	    saveas.addActionListener(this);
	    exit.addActionListener(this);
	    fnr.addActionListener(this);
	    wwrap.addActionListener(this);
	    incSize.addActionListener(this);
	    
	    JScrollPane sp = new JScrollPane(ta);
	    add(sp);
	    
		
	    open.setAccelerator(KeyStroke.getKeyStroke(
	            java.awt.event.KeyEvent.VK_O, 
	            java.awt.Event.CTRL_MASK));
	    save.setAccelerator(KeyStroke.getKeyStroke(
	            java.awt.event.KeyEvent.VK_S, 
	            java.awt.Event.CTRL_MASK));
	    nw.setAccelerator(KeyStroke.getKeyStroke(
	            java.awt.event.KeyEvent.VK_N, 
	            java.awt.Event.CTRL_MASK));
	    fnr.setAccelerator(KeyStroke.getKeyStroke(
	            java.awt.event.KeyEvent.VK_F, 
	            java.awt.Event.CTRL_MASK));
	    colour.setAccelerator(KeyStroke.getKeyStroke(
	            java.awt.event.KeyEvent.VK_I, 
	            java.awt.Event.CTRL_MASK));
	    file.setMnemonic('F');
	    edit.setMnemonic('E');
	    
	  	mb.add(file);
	  	mb.add(edit);
	  	setJMenuBar(mb);
	    wwrap.setState(true);
		file.add(nw);
		file.add(open);
		file.add(save);
		file.add(saveas);
		file.addSeparator();
		file.add(exit);
		edit.add(colour);
		edit.add(fnr);
	    edit.add(incSize);
	    edit.add(wwrap);
	    
		GridLayout gl=new GridLayout(3,0);
		
		f3=new JFrame("Font");
		f3.setSize(400,200);
	    f3.setLayout(new GridLayout(4,1));
		f3.add(new Label("Enter Font Name : "));
		tffName=new JTextField();
		f3.add(tffName);
		f3.add(new Label("Enter Font Size : "));
		tffSize=new JTextField();
		f3.add(tffSize);
		bFont=new JButton("Set");
		bFont.addActionListener(this);
		f3.add(bFont);
		
	    f2=new JFrame("Find and Replace");
	    f2.setSize(600,100);
	    f2.setLayout(gl);
	    GridLayout gl2=new GridLayout(0,2);
	   
	    
	    p.setLayout(bl);
	    inner.setLayout(gl2);
	    b2.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent ae1)
	    	{
	    		f2.setVisible(false);
	    		f2.dispose();
	    	}
	    });
	    f2.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				f2.setVisible(false);
	    		f2.dispose();
			}
		});
	    p.add(inner,BorderLayout.EAST);
	    p.add(new Label(""),BorderLayout.WEST);
	    inner.add(b1);
	    inner.add(b2);
	    f2.add(l1);
	    f2.add(tf);
	    f2.add(l2);
	    f2.add(tf2);
	    f2.add(p);
	    b1.addActionListener(this);
    
	    setSize(500,500);
		setVisible(true);
	    addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				if(flag==true)
					saveas.doClick();
    			dispose();//To release memory
    			System.exit(0);
			}
		});
	}
	
	public void actionPerformed(ActionEvent ae){
		try{
			Object o=ae.getSource();
			if(o==nw){
				if(flag==true){
					saveas.doClick();
					ta.setText("");
					flag=false;
				}
				else{
					ta.setText("");
				}
			}
			else if(o==open){
				if(flag==false){
					fd=new FileDialog(f2,"Open",FileDialog.LOAD);
	    			fd.setVisible(true);
	    			name=fd.getFile();
	    			path=fd.getDirectory();
	    			n=path+name+"";
	    			File fl=new File(path,name);
	    			FileInputStream fis=new FileInputStream(fl);
	    			ta.setText("");
	    			String temp="";
	    			int i=fis.read();
	    			try{
	    				while(i!=-1){
	    				temp+=""+(char)i;
	    				i=fis.read();
	    				}
	    			}catch(IOException e){
	    				System.out.println("Copy Failed!!");
	    			}
	    			ta.setText(temp);
	    			save.setEnabled(true);
	    			fis.close();
	    			setTitle(fd.getFile()+"-jNotePad");
				}
				else{
					saveas.doClick();
					open.doClick();
				}
			}
			else if(o==save){
				fd=new FileDialog(f2,"Save",FileDialog.SAVE);
    			if(path==null)//When text is not changed
    			{
    				saveas.doClick();
    			}
    			else{//When text is changed
    				String str1=ta.getText();
    				File f=new File(n);
    				FileOutputStream fos=new FileOutputStream(f);
    				char arr[];
    				arr=str1.toCharArray();
    				for(int i=0;i<arr.length;i++)
    				{ 
    					fos.write(arr[i]);
    				}
    				flag=false;
    				fos.close();
    			}
			}
			else if(o==saveas){
    			fd=new FileDialog(f2,"Save As",FileDialog.SAVE);
    			fd.setVisible(true);
    			name=fd.getFile();
    			path=fd.getDirectory();
    			File f=new File(path,name);
    			String str1=ta.getText();
    			FileOutputStream fos=new FileOutputStream(f);
    			char a[];
    			a=str1.toCharArray();
    			for(int i:a)
    				fos.write(i);
    			flag=false;
    			fos.close();
    		}
			else if(o==exit)
    		{
				if(flag==true)
					saveas.doClick();
    			setVisible(false);
    			dispose();//To release memory
    			System.exit(0);
    		}
			else if(o==fnr){
    			f2.setVisible(true);
    		}
			else if(o==colour){
				if(ta.getForeground()==Color.white){
					ta.setBackground(Color.white);
					ta.setForeground(Color.black);
					ta.setCaretColor(Color.black);
				}
				else{
					ta.setBackground(Color.black);
					ta.setForeground(Color.white);
					ta.setCaretColor(Color.white);
				}
			}
			else if(o==incSize){
				f3.setVisible(true);
			}
			else if(o==bFont){
				fName=tffName.getText();
				try{
					fSize=Integer.parseInt(tffSize.getText());
				}catch(Exception e){
					fSize=25;
				}
				ta.setFont(new Font(fName,Font.PLAIN,fSize));
				f3.setVisible(false);
			}
			else if(o==wwrap){
				if(wwrap.getState()==true)
					ta.setLineWrap(true);
				else
					ta.setLineWrap(false);
			}
			else if(o==b1){
				find=tf.getText();
				repl=tf2.getText();
				ta.setText(ta.getText().replaceAll(find, repl));
			}
		}catch(Exception e){
			
		}
	}
}
public class jNotePad{
	public static void main(String args[]){
			new JApFrame("jNotePad");			
	}
}