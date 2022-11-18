package ircding;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Victor Hugo Manata Pontes
 * @twitch twitch.tv/lechuck311
 * @github https://github.com/vhpontes
 * 
 */

public class IRCDing {
    
    public static final IRCDing appPanel = new IRCDing();
    public static JCheckBox checkBoxDingOn = new JCheckBox("Enable Ding on Message");
    public static JButton buttonPlaySE = new JButton("Sound Play Test"); 
    public static JComboBox soundFileCB = new JComboBox();
    public Sound se = new Sound();
    
    public static void main(String[] args) {
        
        File aDirectory = new File("./sounds/");
        String[] filesInDir = aDirectory.list();
        Arrays.sort(filesInDir);
        for ( int i = 0; i < filesInDir.length; i++ ) {
            IRCDing.soundFileCB.addItem(filesInDir[i]);
            System.out.println( "file: " + filesInDir[i] );
        }
        
        new HideToSystemTray();
        
        new Thread(new Runnable(){
            public void run(){
                try {
                    new TwitchBot().startTwitch(IRCDing.appPanel);
                } catch (Exception ex) {
                    Logger.getLogger(IRCDing.class.getName()).log(Level.OFF, null, ex);
                    System.out.println("ERROR");
                    System.exit(0);
                }
            }
        }).start();        
    }

    private static class WindowAdapterImpl extends WindowAdapter {

        public WindowAdapterImpl() {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
    
    public class CheckBoxHandler implements ItemListener {
        
        @Override
        public void itemStateChanged(ItemEvent e) {
            
            if(checkBoxDingOn.isSelected()){
                
            }
        }
    }
    
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
    
    public static class HideToSystemTray extends JFrame {
        TrayIcon trayIcon;
        SystemTray tray;
        
        HideToSystemTray(){
            super("Twitch IRC Ding");

            try {

                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch(Exception e) {
                System.out.println("Unable to set LookAndFeel");
            }
            if(SystemTray.isSupported()) {
                
                tray = SystemTray.getSystemTray();

                Image image = Toolkit.getDefaultToolkit().getImage("trayicon.png");
                
                ActionListener exitListener=new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Exiting....");
                        System.exit(0);
                    }
                };
                
                PopupMenu popup=new PopupMenu();
                MenuItem defaultItem=new MenuItem("Exit");
                
                defaultItem.addActionListener(exitListener);
                popup.add(defaultItem);
                
                defaultItem=new MenuItem("Open");
                defaultItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setVisible(true);
                        setExtendedState(JFrame.NORMAL);
                    }
                });
                
                popup.add(defaultItem);
                
                trayIcon=new TrayIcon(image, "IRC Ding", popup);
                trayIcon.setImageAutoSize(true);
            }
            else {
                
                System.out.println("system tray not supported");
            }
            
            addWindowStateListener(new WindowStateListener() {
                
                public void windowStateChanged(WindowEvent e) {
                    
                    if(e.getNewState()==ICONIFIED) {
                        
                        try {
                        
                            tray.add(trayIcon);
                            setVisible(false);
                        } 
                        catch (AWTException ex) {
                            
                            System.out.println("unable to add to tray");
                        }
                    }
                    
                    if(e.getNewState()==7) {
                        try{

                            tray.add(trayIcon);
                            setVisible(false);
                        }
                        catch(AWTException ex) {

                            System.out.println("unable to add to system tray");
                        }
                    }

                    if(e.getNewState()==MAXIMIZED_BOTH) {
                        tray.remove(trayIcon);
                        setVisible(true);
                    }

                    if(e.getNewState()==NORMAL) {
                        tray.remove(trayIcon);
                        setVisible(true);
                    }
                }
            });

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = new Insets(10, 10, 10, 10);
            constraints.gridx = 0;
            constraints.gridy = 0;

            JPanel sPanel = new JPanel(new GridBagLayout());
            sPanel.setBackground(Color.CYAN);
            sPanel.setOpaque(false);
            sPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Settings"));

            sPanel.add(soundFileCB, constraints);
            constraints.gridy += 1;
            
            checkBoxDingOn.setSelected(true);
            sPanel.add(checkBoxDingOn, constraints);
            constraints.gridy += 1;

            buttonPlaySE.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    appPanel.playSE(soundFileCB.getSelectedIndex());
                }  
            });  
            
            sPanel.add(buttonPlaySE, constraints);  
        
            getContentPane().add(sPanel, BorderLayout.PAGE_START);
            
            ImageIcon img = new ImageIcon("trayicon.png");
            setIconImage(img.getImage());
            setVisible(true);
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }    
}