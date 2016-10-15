/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iphone;


import ecouteur.BouttonMenu;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author rojo
 */
public class Accueil extends JFrame {
    private JButton message;
    private JButton menuB;
    private JPanel container;
    private JButton contact;
    public Accueil() {
        container=new JPanel();
        this.setTitle("Accueil");
        this.setSize(300, 100);
        String title="Bienvenue"; 
        JLabel jl=new JLabel(title);
        jl.setIcon(new ImageIcon("/Users/rojo/NetBeansProjects/iphone/icon/Iphone.png"));
        container.add(jl);
        
        
        menuB=new JButton("Connexion Iphone");
        BouttonMenu men=new BouttonMenu();
        menuB.addActionListener(men);
        container.add(menuB);
        
        container.setSize(300, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        container.setLayout(new GridLayout(3,1));
        
        this.setContentPane(container);
        this.setVisible(true);
    }

}
