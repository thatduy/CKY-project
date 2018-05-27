/*
 * To change 
 */
package cky_project;

/**
 *
 * @author ASUS
 */
public class CKY_Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
        
    }
    
}
