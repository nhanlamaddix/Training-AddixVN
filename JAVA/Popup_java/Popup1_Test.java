
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FinalTest extends JFrame implements ActionListener {
    //Khai bao thuoc tinh
	private JPanel panel1,panel2;
	private JFrame jframe;
	private JButton btSum;
	private JButton btCancel;
	private JTextField tfNum1,tfNum2,tfResult;

	//tao Frame
           
	
                public FinalTest()
                {
                            jframe = new JFrame();
                            //setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
                            jframe.setSize(300, 400);
                            jframe.setResizable(false);
                            createPanel();
                            
                            pack();
                            setLocationRelativeTo(null);
                            setVisible(true);
                }
	private void createPanel()
	{
	//Tao bang frame chua 2 panel
                      panel1 = new JPanel(new GridLayout(2,2,5,5));
                      panel2 = new JPanel(new GridLayout(1,2,5,5));
		//panel1 
		panel1.add(createLabel ("Number 1"));
		panel1.add( createLabel("Number 2"));
		
		panel1.add(createJtext("tfNum1"));
		panel1.add(createJtext("tfNum2"));
		
		//panel 2
		btSum = createButton("SUM");
                             panel2.add(btSum);
                             btCancel=createButton("CANCEL");
                             panel2.add(btCancel);
		
                    add(panel1);
                    add(panel2);
	}
        public JLabel createLabel(String lbName)
            {
	JLabel lb = new JLabel(lbName);
	lb.setHorizontalAlignment(JLabel.LEFT);
	
	return lb;
            }
//Tao button
private JButton createButton(String btName)
{
	JButton btn= new JButton(btName);
	btn.addActionListener(this);
	return btn;
}
private JTextField createJtext(String tfName)
{
	JTextField tf = new JTextField(tfName);
                add(tf);
	tf.add(tfName, tf);
	
	return tf;
}
public void  process()
{
	String a= tfNum1.getText();
               String b = tfNum2.getText();
               double num1=Double.parseDouble( a);
               double num2=Double.parseDouble( b);
	double kq=0;
                if(a.equals(" "))
                    tfNum1.requestFocus();
                else if(b.equals(" "))
                    tfNum2.requestFocus();
                else {
                     kq= num1 + num2;
                }
                tfResult.setText(String.valueOf(kq));
                JOptionPane.showMessageDialog(jframe, "Result");
                
}
public void cancel()
{
    JOptionPane.showMessageDialog(jframe, "This is a messag dialog",
                  "Close", JOptionPane.CLOSED_OPTION);
         System.exit(0);
}
public void actionPerformed(ActionEvent e)
{
   if(e.getSource()==btSum)
   {
       process();
   }
   if(e.getSource()==btCancel)
       cancel();
}

 public static void main(String[] args) {
        new FinalTest();
    }
}


