/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coversorinter;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author LENOVO
 */
public class Metodos {

    static JTextField[] posicion = {new JTextField(), new JTextField(), new JTextField(), new JTextField()};
    static JTextField[] posicion2 = {new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField()};

    public static Border borde(Border color, String titulo) {
        Border compoundBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(titulo),
                BorderFactory.createCompoundBorder(color, BorderFactory.createEmptyBorder(5, 5, 5, 5))
        );
        return compoundBorder;
    }

    public static void llenarCombo(JComboBox combo, String[] monedas) {
        for (String moneda : monedas) {
            combo.addItem(moneda);
        }
    }

    public static void numeros(KeyEvent e, JTextField campo) {
        if (e.getSource() == campo) {
            char c = e.getKeyChar();
            if (Character.isLetter(c)) {
                e.consume();
            }
        }
    }

    public static void bloquearCaracteresEspecialesExceptoPunto(KeyEvent e, JTextField campo) {
        if (e.getSource() == campo) {
            char c = e.getKeyChar();
            if (!Character.isLetterOrDigit(c) && c != ' ' && c != '.') {
                e.consume();
            }
        }
    }

    public static void llenarInfo(JComboBox cambios, String[] monedas, Border blueBorder, JPanel panelInfo, int tama) {

        int x = 0, y = 0;
        int cont = 4;
        int cont2 = 0;
        int index = 0;
        int j = tama;
        while (cont != 0) {
            if (j != cambios.getSelectedIndex() - 1) {
                switch (cont2) {
                    case 0:
                        x = 120;
                        y = 40;
                        break;
                    case 1:
                        y = 100;
                        break;
                    case 2:
                        x = 350;
                        y = 40;
                        break;
                    case 3:
                        y = 100;
                        break;
                }
                posicion[index].setBounds(x, y, 155, 45);
                posicion[index].setBorder(Metodos.borde(blueBorder, monedas[j]));
                posicion[index].setName(monedas[j]);
                posicion[index].setFont(new Font("Times New Roman", Font.BOLD, 14));
                posicion[index].setHorizontalAlignment(JTextField.CENTER);
                posicion[index].setText("    ");
                panelInfo.add(posicion[index]);
                cont2++;
                cont--;
                index++;
            }
            j++;
        }
    }

    public static void llenarInfo2(JComboBox cambios, String[] monedas, Border blueBorder, JPanel panelInfo, int tama) {

        int x = 0, y = 0;
        int cont = 5;
        int cont2 = 0;
        int index = 0;
        int j = tama;
        while (cont != 0) {
            if (j != cambios.getSelectedIndex() - 1) {
                switch (cont2) {
                    case 0:
                        x = 120;
                        y = 30;
                        break;
                    case 1:
                        y = 80;
                        break;
                    case 2:
                        x = 350;
                        y = 30;
                        break;
                    case 3:
                        y = 80;
                        break;
                    case 4:
                        x = 230;
                        y = 140;
                        break;
                }
                posicion2[index].setBounds(x, y, 155, 45);
                posicion2[index].setBorder(Metodos.borde(blueBorder, monedas[j]));
                posicion2[index].setText("    ");
                posicion2[index].setFont(new Font("Times New Roman", Font.BOLD, 14));
                posicion2[index].setHorizontalAlignment(JTextField.CENTER);
                panelInfo.add(posicion2[index]);
                cont2++;
                cont--;
                index++;
            }
            j++;
        }
    }

    public static void limpiarMonedas() {
        for (int i = 0; i < posicion.length; i++) {
            posicion[i].setVisible(false);
        }
    }

    public static void limpiarTem() {
        for (int i = 0; i < posicion2.length; i++) {
            posicion2[i].setVisible(false);
        }
    }

    public static void activarMonedas() {
        for (int i = 0; i < posicion.length; i++) {
            posicion[i].setVisible(true);
        }
    }

    public static void activarTem() {
        for (int i = 0; i < posicion2.length; i++) {
            posicion2[i].setVisible(true);
        }
    }

    public static double resultadoConverMoneda(int seleccion, String contenido, int[] monedas) {
        int nume = Integer.parseInt(contenido);
        if (seleccion > 0 && seleccion <= 5) {
            return (nume / monedas[seleccion + 1]);
        } else {
            return (nume * monedas[seleccion + 1]);
        }
    }

    public static void prueba2(JComboBox cambios, int[] monedas, int tama, String contenido, JTextField resul) {
        double nume1 = Double.parseDouble(contenido);
        double r1  = 0;
        if(cambios.getSelectedIndex() > 0 ){
            if(cambios.getSelectedIndex() < 6){
                r1 = nume1 / monedas[cambios.getSelectedIndex() - 1];
            }else{
                r1 = nume1 * monedas[cambios.getSelectedIndex() - 1];
            }
        }
        
        DecimalFormat df1 = new DecimalFormat("#.##");
        String resultadoFormateado1 = df1.format(r1);
        resul.setText(resultadoFormateado1);
        
        int cont = 4;
        int index = 0;
        int j = tama;
        while (cont != 0) {
            if (j != cambios.getSelectedIndex() - 1) {
                double nume = Double.parseDouble(contenido);
                double r = 0;
                if(cambios.getSelectedIndex() > 0){
                    if(cambios.getSelectedIndex() < 6){
                        r = nume / monedas[j];
                    }else{
                        r = nume * monedas[j];
                    }
                }
                DecimalFormat df = new DecimalFormat("#.##");
                String resultadoFormateado = df.format(r);
                posicion[index].setText(resultadoFormateado);
                cont--;
                index++;
            }
            j++;
        }
    }

    public static void prueba3(JComboBox cambios, String contenido, JTextField campo, String[] tem) {
        int cont = 0;
        campo.setText(retornoTempe(cambios.getSelectedIndex() - 1, contenido));
        for (int i = 0; i < tem.length; i++) {
            if (!cambios.getSelectedItem().equals(tem[i])) {
               // System.out.println(tem[i] + i);
                posicion2[cont].setText(retornoTempe(i, contenido));
                cont++;
            }
        }

    }

    public static void limpiarPrinci(JTextField uno, JTextField dos) {
        uno.setText("");
        dos.setText("");
    }

    public static String retornoTempe(int seleccion, String dato) {

        DecimalFormat df1 = new DecimalFormat("#.##");
        double nume = Double.parseDouble(dato);
        double r1 = 0;

        switch (seleccion) {
            case 0:
                r1 = (nume * 9 / 5) + 32;
                break;
            case 1:
                r1 = nume + 273.15;
                break;
            case 2:
                r1 = (nume - 32) * 5 / 9;
                break;
            case 3:
                r1 = (nume + 459.67) * 5 / 9;
                break;
            case 4:
                r1 = nume - 273.15;
                break;
            case 5:
                r1 = (nume * 9 / 5) - 459.67;
                break;
        }
        return df1.format(r1);
    }

}
