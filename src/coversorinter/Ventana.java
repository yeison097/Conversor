/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coversorinter;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author LENOVO
 */
public class Ventana extends JFrame implements KeyListener, ActionListener, MouseListener {

    Border blueBorder = new LineBorder(Color.BLUE);//Borde azul
    int[] moneda = {3976, 4424, 5111, 2807, 908, 3976, 4424, 5111, 2807, 908};
    String[] monedas
            = {"De Pesos a Dólar", "De Pesos a Euro", "De Pesos a Libras", "De Pesos a Yen", "De Pesos a Yen Coreano", "De Dólar a Pesos", "De Euro a Pesos", "De Libras a Pesos", "De Yen a Pesos", "De Yen Coreano a Pesos"};
    String[] temperaturas
            = {"Celsius a Fahrenheit", "Celsius a Kelvin", "Fahrenheit a Celsius", "Fahrenheit a Kelvin", "Kelvin a Celsius", "Kelvin a Fahrenheit"};
    JTextField campo1, campo2;
    JComboBox cambios;
    JPanel panelInfo;
    static int seleccion = 0;
    JLabel imagen, imagen2;
    JLabel titulo;
    static boolean bandera = true;

    public Ventana() throws IOException {
        setLayout(null);
        setTitle("Challenge-1");
        setSize(650, 480);
        this.getContentPane().setBackground(Color.GRAY);
        setLocationRelativeTo(null);//
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        componentesUi();

    }

    public void componentesUi() throws IOException {

        titulo = new JLabel("Conversor de Divisas");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Georgia", Font.BOLD, 20));
        titulo.setBounds(205, 10, 280, 40);

        cambios = new JComboBox();
        cambios.addActionListener(this);
        cambios.setBounds(235, 60, 180, 30);
        cambios.addItem("Seleccione una Opcion");
        Metodos.llenarCombo(cambios, monedas);

        campo1 = new JTextField();
        campo1.setBounds(120, 120, 180, 70);
        campo1.setBorder(Metodos.borde(blueBorder, "Valor a Convertir"));
        campo1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        campo1.setHorizontalAlignment(JTextField.CENTER);
        campo1.addKeyListener(this);

        campo2 = new JTextField();
        campo2.setBorder(Metodos.borde(blueBorder, "Resultado"));
        campo2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        campo2.setHorizontalAlignment(JTextField.CENTER);
        campo2.setBounds(350, 120, 180, 70);

        ImageIcon icon = new ImageIcon("src/imagenes/moneda.png");
        Image img = icon.getImage();
        Image nuevaImagen = img.getScaledInstance(50, 35,
                java.awt.Image.SCALE_SMOOTH);
        Icon nuevoIcono = new ImageIcon(nuevaImagen);
        imagen = new JLabel(nuevoIcono);
        imagen.setBounds(500, 40, 50, 35);
        imagen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imagen.addMouseListener(this);

        ImageIcon icon2 = new ImageIcon("src/imagenes/temperatura.png");
        Image img2 = icon2.getImage();
        Image nuevaImagen2 = img2.getScaledInstance(50, 35,
                java.awt.Image.SCALE_SMOOTH);
        Icon nuevoIcono2 = new ImageIcon(nuevaImagen2);
        imagen2 = new JLabel(nuevoIcono2);
        imagen2.setBounds(550, 40, 50, 35);
        imagen2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imagen2.addMouseListener(this);
        imagen2.setEnabled(false);

        panelInfo = new JPanel();
        panelInfo.setBorder(Metodos.borde(blueBorder, "Informacion"));
        panelInfo.setLayout(null);
        panelInfo.setBounds(10, 220, 610, 200);

        add(cambios);
        add(titulo);
        add(campo1);
        add(campo2);
        add(imagen);
        add(imagen2);
        add(panelInfo);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        Metodos.numeros(e, campo1);
        Metodos.bloquearCaracteresEspecialesExceptoPunto(e, campo1);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        try {
            //
            if(bandera){
                if (cambios.getSelectedIndex() > 0 && cambios.getSelectedIndex() < 6) {
                Metodos.prueba2(cambios, moneda, 0, campo1.getText(), campo2);
            } else {
                if (cambios.getSelectedIndex() > 0 && cambios.getSelectedIndex() >= 6) {
                    Metodos.prueba2(cambios, moneda, 5, campo1.getText(), campo2);
                }
            }
            }else{
              Metodos.prueba3(cambios, campo1.getText(),campo2,temperaturas);
             
            }
            

        } catch (NumberFormatException io) {
            io.getStackTrace();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cambios) {

            if (bandera) {

                if (cambios.getSelectedIndex() > 0) {
                    campo2.setBorder(Metodos.borde(blueBorder, monedas[cambios.getSelectedIndex() - 1]));
                    Metodos.activarMonedas();
                    Metodos.limpiarPrinci(campo1, campo2);
                }
                if (cambios.getSelectedIndex() > 0 && cambios.getSelectedIndex() < 6) {
                    Metodos.llenarInfo(cambios, monedas, blueBorder, panelInfo, 0);
                    Metodos.limpiarPrinci(campo1, campo2);

                } else if (cambios.getSelectedIndex() > 0 && cambios.getSelectedIndex() >= 6) {
                    Metodos.llenarInfo(cambios, monedas, blueBorder, panelInfo, 5);
                    Metodos.limpiarPrinci(campo1, campo2);

                }

            } else {

                if (cambios.getSelectedIndex() > 0) {
                    campo2.setBorder(Metodos.borde(blueBorder, temperaturas[cambios.getSelectedIndex() - 1]));
                    Metodos.llenarInfo2(cambios, temperaturas, blueBorder, panelInfo, 0);
                    Metodos.activarTem();
                    Metodos.limpiarPrinci(campo1, campo2);
                }

            }

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        campo1.setText("");
        campo2.setText("");
        if (e.getSource() == imagen) {
            this.getContentPane().setBackground(Color.GRAY);
            campo2.setBorder(Metodos.borde(blueBorder, "Resultado"));
            Metodos.limpiarTem();//revisar
            // Metodos.activarMonedas();//revisar
            titulo.setText("Conversor de Divisas");
            imagen.setEnabled(true);
            imagen2.setEnabled(false);
            cambios.removeAllItems();
            cambios.addItem("Seleccione una Opcion");
            Metodos.llenarCombo(cambios, monedas);
            bandera = true;

        } else {
            this.getContentPane().setBackground(Color.DARK_GRAY);
            campo2.setBorder(Metodos.borde(blueBorder, "Resultado"));
            Metodos.limpiarMonedas();//revisar
            //  Metodos.activarTem();//revisar
            titulo.setText("Conversor de Temperatura");
            imagen.setEnabled(false);
            imagen2.setEnabled(true);
            cambios.removeAllItems();
            cambios.addItem("Seleccione una Opcion");
            Metodos.llenarCombo(cambios, temperaturas);
            bandera = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
