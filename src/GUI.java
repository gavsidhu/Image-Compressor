import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI {
    static String filePath ="";

    static File imageFile;
    static String outputPath;

    public static void createGUI(){
        JFrame frame = new JFrame("File Compressor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);

        JLabel chooseFileLabel = new JLabel("File:");
        chooseFileLabel.setBounds(10,50,75,30);

        JLabel fileField = new JLabel();
        fileField.setBounds(75,50,150,30);

        JButton chooseFileButton = new JButton("Select File");
        chooseFileButton.setBounds(250,50,75,30);

        JLabel chooseOutput = new JLabel("Output:");
        chooseOutput.setBounds(10,100,75,30);

        JLabel outputField = new JLabel();
        outputField.setBounds(75,100,150,30);

        JButton chooseOutputButton = new JButton("Select Output");
        chooseOutputButton.setBounds(250,100,75,30);

        JButton compressButton = new JButton("Compress");
        compressButton.setBounds(10, 150, 75,30);

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFile();
                fileField.setText(filePath);
                System.out.println(filePath);
            }
        });

        chooseOutputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOutputPath();
                outputField.setText(outputPath);
                System.out.println(outputPath);
            }
        });

        compressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Compressing image");
                try {
                    compressImage();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        frame.add(chooseFileLabel);
        frame.add(fileField);
        frame.add(chooseFileButton);

        frame.add(chooseOutput);
        frame.add(outputField);
        frame.add(chooseOutputButton);

        frame.add(compressButton);

        frame.setLayout(null);
        frame.setVisible(true);
    }
    private static String getFile() {
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file == null) {
                return null;
            }

            filePath = chooser.getSelectedFile().getAbsolutePath();
            imageFile = new File(filePath);
        }
        return filePath;
    }

    private static String getOutputPath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showSaveDialog(null);

        System.out.println(chooser.getCurrentDirectory());
        System.out.println(chooser.getSelectedFile());
        outputPath = chooser.getSelectedFile().getAbsolutePath();

        return outputPath;
    }

    private static void compressImage() throws Exception {
        String ext = Utils.getExtension(imageFile);

        if(ext.equals(Utils.png)){
            ImageCompressor compressor = new ImageCompressor();

            compressor.compressPNG(imageFile,filePath,outputPath);
        }

    }


}
