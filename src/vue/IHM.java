package vue;

import controller.API;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IHM {
    private JPanel bordPannel;
    private JPanel pannelAddFile;
    private JPanel pannelCentre;
    private JButton parcourirButton;
    private JButton calculerButton;
    private JTextField cheminTextField;
    private JTextArea txResultat;

    public JFormattedTextField getTxError() {
        return txError;
    }

    public void setTxError(JFormattedTextField txError) {
        this.txError = txError;
    }

    private JFormattedTextField txError;
    private String path = null;
    private API api;




    public IHM(JFrame parent) {
        txError.setText("Everything is OK!");
        txError.setBackground(Color.GREEN);
        try {
            api = new API();
        } catch (Exception e) {
            e.printStackTrace();
            txError.setText("ERREUR: " + e.toString()+" " + e.getCause().getMessage());
            txError.setBackground(Color.RED);
        }

        parcourirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser choix = new JFileChooser(path);
                choix.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int retour = choix.showOpenDialog(parent);
                if(retour==JFileChooser.APPROVE_OPTION){
                    cheminTextField.setText(choix.getSelectedFile().getName());
                    // chemin absolu du fichier choisi
                    path = choix.getSelectedFile().getAbsolutePath();
                }

            }
        });
        calculerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(path);
                try {
                    txResultat.setText(api.Rechercher(path));

                } catch (Exception e1) {
                    e1.printStackTrace();
                    txError.setText("ERREUR: " + e1.toString() +" "+ e1.getCause().getMessage());
                    txError.setBackground(Color.RED);

                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RansomClass");
        frame.setContentPane(new IHM(frame).bordPannel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


}
