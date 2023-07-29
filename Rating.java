import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class Rating {
    private javax.swing.JPanel JPanel;
    JFrame rating = new JFrame();
    private JPanel JPanel1;
    private JLabel rate;
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton button1;
    private JSlider slider1;

    public Rating()
    {
       rating.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       rating.setContentPane(JPanel);
       rating.pack();
       rating.setLocationRelativeTo(null);
       rating.setVisible(true);

       slider1.setMinimum(0);
       slider1.setMaximum(5);
       slider1.setPaintTicks(true);
       slider1.setPaintLabels(true);
       slider1.setMajorTickSpacing(1);
       button1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(textField1.getText().equals("") || textArea1.getText().equals(""))
               {
                   JOptionPane.showMessageDialog(null,"Please Fill NAME and FEEDBACK to submit.");
               }
               else
               {
                   try
                   {
                        String sql = "insert into rate" + "(Name,Rating,Feedback)" + "values (?,?,?)";
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost3306/intern", "root", "root");
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1,textField1.getText());
                        statement.setString(2,String.valueOf(slider1.getValue()));
                        statement.setString(3,textArea1.getText());
                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(null,"RATED SUCCESSFULLY");
                        textField1.setText("");
                        textArea1.setText("");
                   }
                   catch(Exception ex) {
                       JOptionPane.showMessageDialog(null, ex.getMessage());
                   }
               }
           }
       });
       slider1.addMouseListener(new MouseAdapter()
       {
           @Override
           public void mouseClicked(MouseEvent e)
           {
               rate.setText(String.valueOf(slider1.getValue())+"Star");
           }
       });
    }

}