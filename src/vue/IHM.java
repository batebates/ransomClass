package vue;

import controller.API;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class IHM extends JFrame implements ActionListener{

        private String path = null;
        private JTextField txPath;
        private JTextArea txResultat;
        private API api;



        public IHM(){
            api = new API();
            setSize(800,600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);

            BorderLayout bord = new BorderLayout();
            bord.setVgap(20);
            bord.setHgap(20);

            getContentPane().setLayout(bord);
            getContentPane().add(pannelAddFile(), BorderLayout.NORTH);
            getContentPane().add(pannelCentre(), BorderLayout.CENTER);

            setVisible(true);
        }



        private JPanel pannelAddFile(){
            GridLayout grid = new GridLayout(2,3,10,10);
            JPanel box = new JPanel(grid);


            JLabel lbPath = new JLabel("Chemin");
            JLabel lbVide = new JLabel("");
            lbPath.setHorizontalAlignment(0);
            JButton btCalculer = new JButton("Calculer");
            JButton btSelectFile = new JButton("Parcourir");
            this.txPath = new JTextField();
            txPath.setEditable(false);
            btCalculer.addActionListener(this);
            btSelectFile.addActionListener(this);

            box.add(lbPath,0);
            box.add(btSelectFile,1);
            box.add(txPath,2);
            box.add(lbVide,3);
            box.add(btCalculer,4);



            return box;
        }

        private JPanel pannelCentre(){
            BorderLayout nwBord = new BorderLayout();
            JPanel box = new JPanel(nwBord);
            nwBord.setVgap(10);


            JLabel lbResultat = new JLabel("Resultat");
            lbResultat.setHorizontalAlignment(0);
            

            this.txResultat = new JTextArea();
            


            JPanel bordNord = new JPanel(new BorderLayout());
            bordNord.add(lbResultat, BorderLayout.NORTH);
            bordNord.add(txResultat, BorderLayout.CENTER);



            JPanel bordCentreBis = new JPanel(new BorderLayout());
            bordCentreBis.add(bordNord, BorderLayout.CENTER);




            box.add(bordCentreBis, BorderLayout.CENTER);

            return box;
        }


        public void actionPerformed(ActionEvent e){

            if(e.getActionCommand().equals("Calculer")){


                    System.out.println(path);
                try {
                    txResultat.setText(api.Rechercher(path));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }


            }
            if(e.getActionCommand().equals("Parcourir")){
                JFileChooser choix = new JFileChooser(path);
                choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int retour=choix.showOpenDialog(this);
                if(retour==JFileChooser.APPROVE_OPTION){
                    txPath.setText(choix.getSelectedFile().getName());

                    // chemin absolu du fichier choisi
                    path = choix.getSelectedFile().getAbsolutePath();
                }

            }

        }


    
}
