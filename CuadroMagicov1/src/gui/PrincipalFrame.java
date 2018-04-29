/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Rafael
 */
public class PrincipalFrame extends JFrame{
    private String[] numeros = {"3x3","4x4","5x5","NxN"};
    private JComboBox ListaNumeros;
    private JLabel lblNum;
    private final JPanel pnlCentro;
    private final JPanel pnlEnc;
    private final JButton bttIngresar;
    public controlador control;
    private Integer band=0;
    private MatrizPanel pnlMatriz;
    
    
    public PrincipalFrame(){
        super("Cuadro Mágico");
        super.setSize(330, 400);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        

        pnlCentro = new JPanel();
        pnlEnc = new JPanel();
        pnlCentro.setBackground(Color.white);
        
        lblNum = new JLabel("Cantidad de numeros: ");
        ListaNumeros = new JComboBox(numeros);
        ListaNumeros.setBackground(Color.white);
         
        pnlEnc.add(lblNum,BorderLayout.CENTER);
        pnlEnc.add(ListaNumeros,BorderLayout.SOUTH);
        
        
        
        //Panel botones
        
        bttIngresar = new JButton("Aceptar");
        bttIngresar.setBackground(Color.DARK_GRAY);
        bttIngresar.setFont(new Font("Courier New",Font.BOLD,14));
        bttIngresar.setForeground(Color.WHITE);
        pnlEnc.add(bttIngresar,BorderLayout.SOUTH);
       this.pnlMatriz = new MatrizPanel(1);
        
       
       super.add(this.crearPnlTrabajo(), BorderLayout.NORTH);
        super.add(this.pnlMatriz);
        super.add(this.crearPnlBotones(), BorderLayout.SOUTH);
       
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }
    
    private static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}

    }
        private JPanel crearPnlTrabajo() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        NumberFormat formato = NumberFormat.getInstance();
        formato.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(formato);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(Integer.MIN_VALUE);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField txtSize = new JFormattedTextField(formatter);
        txtSize.setColumns(4);
        pnlMatriz.setVisible(false);
        lblNum = new JLabel("Cantidad de numeros: ");
        ListaNumeros = new JComboBox(numeros);
        ListaNumeros.setBackground(Color.white);
        
        JButton btnCrear = new JButton("Crear cuadro");
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                switch(ListaNumeros.getSelectedIndex()){
                    case 0:
                        System.out.println("selección del caso 3x3");
                        band =3;
                        control = new controlador(band);
                        break;
                    case 1:
                        System.out.println("selección del caso 4x4");
                        band=4;
                        control = new controlador(band);
                        break;
                        
                    case 2:
                        System.out.println("selección del caso 5x5");
                        band=5;
                        control = new controlador(band);
                        break;
                    case 3:
                        String a;
                        do{
                            a = (String)JOptionPane.showInputDialog("Ingresa tamaño, solo numeros");
                        }while((!isNumeric(a)));
                        System.out.println(a);
                        
                        band =Integer.parseInt(a);
                        control = new controlador(band);
                        //control.cuadrito();
                        
                        break;
                    default:
                        System.out.println("fatalerror");
                        break;
                }
                int size = Integer.valueOf(band);
                pnlMatriz.setVisible(true);
                PrincipalFrame.super.remove(pnlMatriz);
                pnlMatriz = null;
                pnlMatriz = new MatrizPanel(size);
                PrincipalFrame.super.add(pnlMatriz);
                PrincipalFrame.super.repaint();
            }
        });

        panel.add(lblNum);
        panel.add(ListaNumeros);
        panel.add(btnCrear);

        return panel;
    }

    private JPanel crearPnlBotones() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton btnCalcular = new JButton("¿Es mágico?");
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int[][] matriz = pnlMatriz.getValores();
                control.setMatriz(matriz);
                control.cuadrito();
            }
        });
        
        panel.add(btnCalcular);
        return panel;
    }
}

