import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Popup3 { 
    private int [] arrValue; 

    public int[] getArrValue() {
        return arrValue;
    }

    public void setArrValue(int[] arrValue) {
        this.arrValue = arrValue;
    }
    public Popup3() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 3, 3));
        JLabel lblNum = new JLabel();
        JTextField txtNum = new JTextField();
        
        lblNum.setText("Input number field:");
        
        panel.add(lblNum);
        panel.add(txtNum);
        
        
        Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        
        int result = JOptionPane.showOptionDialog(null, 
                panel,                                  
                "Popup3: Input number field",           
                JOptionPane.DEFAULT_OPTION,             
                JOptionPane.QUESTION_MESSAGE,           
                new ImageIcon(image),                   
                new String[] {"Create", "Cancel"},     
                "Cancel");
       if(result == JOptionPane.YES_OPTION){
           showNumberField(Integer.parseInt(txtNum.getText()));
       
       }
      
    }
     private void showNumberField(int num){
        arrValue = new int[num];
        JPanel panel = new JPanel(new GridLayout(num, 2, 3, 3));
        JLabel []lblNum = new JLabel[num];
        JTextField []txtNum = new JTextField[num];
        for(int i = 0; i < num; i++){
            lblNum[i] = new JLabel();
            lblNum[i].setText("Num "+(i+1) + ": ");
            txtNum[i] = new JTextField();
            panel.add(lblNum[i]);
            panel.add(txtNum[i]);
        }
        
       
        Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        
        int result = JOptionPane.showOptionDialog(null,
                panel,                                  
                "Popup4: Show num Field",             
                JOptionPane.DEFAULT_OPTION,             
                JOptionPane.QUESTION_MESSAGE,           
                new ImageIcon(image),                                   
                new String[] {"Print", "Cancel"},    
                "Cancel");
       if(result == JOptionPane.YES_OPTION){
           for(int i = 0; i < num; i++){
               arrValue[i] = Integer.parseInt(txtNum[i].getText());
           }
         }
       int [] myArr = getArrValue();
        for(int i = 0; i < myArr.length; i++){
            System.out.println("Field " + i + ": " + myArr[i]);
        }
       }       

       public static void main(String []args){
        Popup3 pop3= new Popup3();
        
    }
}
