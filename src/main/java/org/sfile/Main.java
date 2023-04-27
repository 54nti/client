package org.sfile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        // should be final because it will need to be accessed from an inner class
        final File[] fileToSend = new File[1];

        JFrame frame = new JFrame("Client");
        frame.setSize(450, 450);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("File Sender");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBorder(new EmptyBorder(20, 0, 10, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel fileName = new JLabel("Choose a file to send");
        fileName.setFont(new Font("Arial", Font.BOLD, 20));
        fileName.setBorder(new EmptyBorder(50, 0, 0, 0));
        fileName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton send = new JButton("Send file");
        send.setPreferredSize(new Dimension(150, 75));
        send.setFont(new Font("Arial", Font.BOLD, 20));

        JButton choose = new JButton("Choose file");
        choose.setPreferredSize(new Dimension(150, 75));
        choose.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel btnContainer = new JPanel();
        btnContainer.setBorder(new EmptyBorder(75, 0, 10, 0));

        btnContainer.add(send);
        btnContainer.add(choose);

        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setToolTipText("Choose a file to send");
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fileToSend[0] = chooser.getSelectedFile();
                    fileName.setText("The file you want to send is: " + fileToSend[0].getName());
                }
            }
        });

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileToSend[0] == null) {
                    fileName.setText("Please choose a file first");
                } else {
                    try {
                        FileInputStream input = new FileInputStream(fileToSend[0].getAbsolutePath());
                        Socket socket = new Socket("localhost", 1234);
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        String name = fileToSend[0].getName();
                        byte[] nameInBytes = name.getBytes();
                        byte[] fileContentBytes = new byte[(int)fileToSend[0].length()];
                        input.read(fileContentBytes);

                        // first we send an integer to the server which is the length of the data that it will be receiving
                        // The server then knows when it should stop expecting data from the client
                        dataOutputStream.writeInt(nameInBytes.length);
                        dataOutputStream.write(nameInBytes);

                        dataOutputStream.writeInt(fileContentBytes.length);
                        dataOutputStream.write(fileContentBytes);
                    } catch (IOException error) {
                        error.printStackTrace();
                    }

                }
            }
        });

        frame.add(title);
        frame.add(fileName);
        frame.add(btnContainer);
        frame.setVisible(true);
    }
}