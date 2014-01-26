
/**
 *
 * @author divya gautam
 */
package app;
import java.io.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.Random;
import java.awt.event.InputEvent;
import java.awt.event.KeyListener;
import javax.swing.KeyStroke;
import java.awt.event.*;
import javax.swing.JRootPane;
import javax.swing.JComponent;
import javax.swing.*;
import javax.swing.JWindow;

public class SwitchTask {

    private int timeleft;
    
    public void halt(int x, Robot b) {
        for(int i=1; i<=x; i++){
            b.delay(60000);
        }
    }
    public void change(int n,int sign) {
        try {
            Random randomGenerator = new Random();
            int randomInt1 = randomGenerator.nextInt(1000);
            int randomInt2 = randomGenerator.nextInt(1000);
            //int randomInt3 = randomGenerator.nextInt(30);
            Random rand = new Random();
            int randomInt3 = showRandomInteger(-20,20,rand);
            
            //-----------------------------------------switch window
            Robot bot = new Robot();
            bot.keyPress(KeyEvent.VK_ALT);
            for(int i=0; i<n; i++ ) {
                
                bot.keyPress(KeyEvent.VK_TAB);
                bot.keyRelease(KeyEvent.VK_TAB);
            }   
            
            bot.keyRelease(KeyEvent.VK_ALT);
            halt(3,bot);
            timeleft = timeleft - 3;
            if(timeleft < 0) {
                System.exit(0);
            }
            
            //-----------------------------------------do some activity
            bot.mousePress(InputEvent.BUTTON1_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_MASK);
            halt(2,bot);
            timeleft = timeleft - 2;
            if(timeleft < 0) {
                System.exit(0);
            }
            for(int j=0; j<15; j++) {
                bot.keyPress(KeyEvent.VK_DOWN);
                bot.keyRelease(KeyEvent.VK_DOWN);
                bot.keyPress(KeyEvent.VK_RIGHT);
                bot.keyRelease(KeyEvent.VK_RIGHT);
            }
            for(int k=0; k<5; k++) {
                bot.keyPress(KeyEvent.VK_LEFT);
                bot.keyRelease(KeyEvent.VK_LEFT);
            }
            
            //bot.mouseWheel(sign * randomInt3);
            bot.mouseWheel(randomInt3);
            halt(3,bot);
            timeleft = timeleft - 3;
            if(timeleft < 0) {
                System.exit(0);
            }
            
            for(int k=0; k<5; k++) {
                bot.keyPress(KeyEvent.VK_LEFT);
                bot.keyRelease(KeyEvent.VK_LEFT);
            }
            halt(2,bot);
            timeleft = timeleft - 2;
            if(timeleft < 0) {
                System.exit(0);
            }
            
            //------------------------------------------switch tabs
            bot.keyPress(KeyEvent.VK_CONTROL);
            bot.keyPress(KeyEvent.VK_TAB);
            bot.keyRelease(KeyEvent.VK_TAB);
            bot.keyRelease(KeyEvent.VK_CONTROL);
            halt(2,bot);
            timeleft = timeleft - 2;
            if(timeleft < 0) {
                System.exit(0);
            }
            
            //------------------------------------------do some activity
            for(int j=0; j<15; j++) {
                bot.keyPress(KeyEvent.VK_UP);
                bot.keyRelease(KeyEvent.VK_UP);
                bot.keyPress(KeyEvent.VK_RIGHT);
                bot.keyRelease(KeyEvent.VK_RIGHT);
            }
            
            //bot.mouseMove(randomInt1,randomInt2);
            bot.mousePress(InputEvent.BUTTON1_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_MASK);
            halt(3,bot);
            timeleft = timeleft - 3;
            if(timeleft < 0) {
                System.exit(0);
            }
            //bot.mouseWheel(sign * randomInt3);
            bot.mouseWheel(randomInt3);
            halt(3,bot);
            timeleft = timeleft - 3;
            if(timeleft < 0) {
                System.exit(0);
            }
            
            for(int k=0; k<5; k++) {
                bot.keyPress(KeyEvent.VK_LEFT);
                bot.keyRelease(KeyEvent.VK_LEFT);
            }
            halt(2,bot);
            timeleft = timeleft - 2;
            if(timeleft < 0) {
                System.exit(0);
            }
        }catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    
    public int showRandomInteger(int aStart, int aEnd, Random aRandom){
    if ( aStart > aEnd ) {
      throw new IllegalArgumentException("Start cannot exceed End.");
    }
    //get the range, casting to long to avoid overflow problems
    long range = (long)aEnd - (long)aStart + 1;
    // compute a fraction of the range, 0 <= frac < range
    long fraction = (long)(range * aRandom.nextDouble());
    int randomNumber =  (int)(fraction + aStart);    
    return randomNumber;
  }
    
    public static void main(String[] args)  {
        
         String str = null;
         str = JOptionPane.showInputDialog(null,"Enter time in minutes.Time should be a multiple of 20.","",1);
         if(str != null) {
             
             Runnable runner = new Runnable() {
                 public void run() {
                    //Check the SystemTray is supported
                    if (!SystemTray.isSupported()) {
                        System.out.println("SystemTray is not supported");
                        return;
                    }
                    final PopupMenu popup = new PopupMenu();
                    Image image = Toolkit.getDefaultToolkit().getImage("gifIcon.gif");
                    final TrayIcon trayIcon = new TrayIcon(image, "Tray Icon", popup);
                    final SystemTray tray = SystemTray.getSystemTray();
       
                    // Create a pop-up menu components
                    MenuItem exitItem = new MenuItem("Exit");
                    exitItem.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae){
                            System.exit(0);
                            //System.out.println("Exit option clicked");
                        }
                    });
       
                    //Add components to pop-up menu
                    popup.add(exitItem);
                    trayIcon.setPopupMenu(popup);
                    try {
                        tray.add(trayIcon);
                    } catch (AWTException e) {
                        System.out.println("TrayIcon could not be added.");
                    }
                 }
            };
            EventQueue.invokeLater(runner);
             
             
             int total_time = Integer.parseInt(str);
             int n = total_time / 20;
             
             SwitchTask obj = new SwitchTask();
             obj.timeleft = total_time;
             int sign = 1;
             int j=0;
             for(int i=1; i<=n+1; i++) {
                 j = j+1;
                 if((i%50) == 0) {
                     j = 0;
                 }
                 sign = -1 * sign;
                 obj.change(j,sign);  
             }
           
         }
         else {
             
         }
         System.exit(0);
         
         
    } 
}
