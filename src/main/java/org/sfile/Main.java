package org.sfile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File fileToSend;

        JFrame frame = new JFrame("Client");
        frame.setSize(450, 450);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel title = new JLabel("File Sender");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBorder(new EmptyBorder(20, 0, 10, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel fileName = new JLabel("Choose a file to send");
        fileName.setFont(new Font("Arial", Font.BOLD, 20));
        fileName.setBorder(new EmptyBorder(50, 0, 0, 0));
        fileName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel btnContainer = new JPanel();
        btnContainer.setBorder(new EmptyBorder(75, 0, 10, 0));

        JButton send = new JButton("Send file");
        send.setPreferredSize(new Dimension(150, 75));
        send.setFont(new Font("Arial", Font.BOLD, 20));

        JButton choose = new JButton("Choose file");
        choose.setPreferredSize(new Dimension(150, 75));
        choose.setFont(new Font("Arial", Font.BOLD, 20));

        btnContainer.add(send);
        btnContainer.add(choose);


    }
}