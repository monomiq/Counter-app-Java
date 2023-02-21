package com.company;

import javax.swing.*;
import java.awt.*;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;   //Add needed libraries


public class Counter implements NativeKeyListener {                 //Create public class Counter that implements NativeKeyListener to be used when window is unfocused

    JFrame frame;
    JTextField textField;
    JTextField text;                                                //Create needed fields for the popup window

    public int count = 0;                                           //Default count is 0

    Counter(){
        frame = new JFrame("Wipe Counter");
        frame.setSize(300,300);
        frame.setLayout(null);                                       //Create window frame

        text = new JTextField();
        text.setBounds(120,20, 50, 30);
        text.setText("Wipes");
        text.setEditable(false);
        text.setBorder(null);
        text.setFont(new Font("Courier", Font.BOLD,15));    //Create text inside the window

        textField = new JTextField();
        textField.setBounds(100,70, 200, 100);
        textField.setBorder(null);
        textField.setEditable(false);
        textField.setFont(new Font("Courier", Font.BOLD,75));   //Create a text field that is changeable by keys

        frame.add(textField);
        frame.add(text);
        frame.setVisible(true);                                            //Add the created elements to the frame window

    }



    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VC_F9) {
            count++;
            textField.setText(count + "");
        }
        else if (e.getKeyCode() == NativeKeyEvent.VC_F10){
             count--;
             textField.setText(count+"");                                       //Assign buttons to set the text field count up and down when pressed.
            }                                                                   //Currently using F9 for + and F10 for -. Using NativeKeyEvent to work when unfocused
        }


    public void nativeKeyReleased(NativeKeyEvent e) {

    }
                                                                                //Possible to use key release or typed functions here
    public void nativeKeyTyped(NativeKeyEvent e) {

        }


    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);                                                     //Error code for nativehook
        }

        GlobalScreen.addNativeKeyListener(new Counter());                               //Add NativeKeyListener to Counter function
    }
}


