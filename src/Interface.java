/**
 * Created by jaxonf on 8/07/2014.
 */

//fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class Interface implements ActionListener {

    private String inputString;
    private String outputDirectoryString;
    private String outputFileString;


    private JFrame frame;
    private Container content;
    private JLabel inputFileLabel;

    private JLabel outputDirectoryLabel;
    private JTextField outputDirectory;

    private JButton browseOutput;


    private JTextField inputFileLocation;
    private JButton browse;
    private JLabel multiplierLabel;
    private JTextField multiplier;
    private JButton confirm;
    private JButton exit;
    private JFileChooser openFile;
    private JFileChooser chooseOutputFile;

    private File file; // the input file
    private File fileOutput;


    public Interface() {

        // GUI elements

        this.outputDirectoryLabel = new JLabel("Output File:");
        this.outputDirectory = new JTextField();

        this.browseOutput = new JButton("Save");

        this.frame = new JFrame();
        this.frame.setSize(800, 200);
        this.content = this.frame.getContentPane();
        this.inputFileLabel = new JLabel("Input File:");
        this.inputFileLocation = new JTextField();
        this.browse = new JButton("Browse");
        this.multiplierLabel = new JLabel("Multiplier:");
        this.multiplier = new JTextField();
        this.confirm = new JButton("Confirm");
        this.exit = new JButton("Exit");
        this.openFile = new JFileChooser();
        this.chooseOutputFile = new JFileChooser();

        // non GUI elements
        this.file = null;
        this.fileOutput = null;
    }

    public void run() {


        this.outputDirectoryLabel.setBounds(5, 45, 150, 25);
        this.outputDirectory.setBounds(150, 55, 200, 25);
        this.outputDirectory.setEditable(false);

        this.browseOutput.setBounds(350, 55, 80, 25);
        this.browseOutput.addActionListener(this);

        // sets the location and other initial values of all the GUI elements
        this.content.setLayout(null);
        this.inputFileLabel.setBounds(5, 5, 70, 25);
        this.inputFileLocation.setBounds(70, 5, 200, 25);
        this.inputFileLocation.setEditable(false);
        this.browse.setBounds(270, 5, 80, 25);
        this.browse.addActionListener(this);
        this.multiplierLabel.setBounds(400, 5, 100, 25);
        this.multiplier.setBounds(500, 5, 70, 25);
        this.exit.setBounds(705, 5, 70, 25);
        this.exit.addActionListener(this);
        this.confirm.setBounds(615, 5, 90, 25);
        //chooseOutputFile.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
        this.confirm.addActionListener(this);


        this.content.add(this.outputDirectoryLabel);
        this.content.add(this.outputDirectory);

        this.content.add(this.browseOutput);
        // adds all the GUI elements to the screen
        this.content.add(this.inputFileLabel);
        this.content.add(this.inputFileLocation);
        this.content.add(this.browse);
        this.content.add(this.multiplierLabel);
        this.content.add(this.multiplier);
        this.content.add(this.exit);
        this.content.add(this.confirm);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        Interface program = new Interface();
        program.run();
    }

    public void actionPerformed(ActionEvent e) {

        // determines which button was clicked


        if(e.getSource().equals(this.browse)) {
            this.browse();
        } else if(e.getSource().equals(this.exit)) {
            System.exit(0);
        } else if(e.getSource().equals(this.confirm)) {
            try {
                this.confirm();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        } else if(e.getSource().equals(this.browseOutput)) {
            this.browseOutput();
        }

    }

    // the browse button was clicked
    public void browse() {

        // opens open file dialog box
        int returnVal = this.openFile.showOpenDialog(this.browse);

        // if the users choice is valid it's assigned to file
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.file = this.openFile.getSelectedFile();
            this.inputFileLocation.setText(this.file.getAbsolutePath());
            this.inputString = this.file.getAbsolutePath();
        }
    }

    public void browseOutput() {

        // opens open file dialog box
        int returnVal = this.chooseOutputFile.showSaveDialog(this.browseOutput);

        // if the users choice is valid it's assigned to file
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.fileOutput = this.chooseOutputFile.getSelectedFile();
            this.outputDirectory.setText(this.fileOutput.getAbsolutePath());
            this.outputDirectoryString = this.fileOutput.getAbsolutePath();
        }
    }

    public void confirm() throws FileNotFoundException {

        GUIDepixel guiDepixel = new GUIDepixel(inputString,
                Integer.parseInt(multiplier.getText()), outputDirectoryString);
        guiDepixel.run();

    }

}
