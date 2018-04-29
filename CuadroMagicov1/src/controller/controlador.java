/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class controlador {
    private Integer tamano;
    private int[][] matriz;

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }
    //Scanner sc = new Scanner(System.in);

    public Integer getCantidad() {
        return tamano;
    }

    public void setCantidad(Integer cantidad) {
        this.tamano = cantidad;
    }
    
    
    public controlador(Integer cant){
        this.tamano = cant;
        this.matriz = new int[tamano][tamano];
        for (int i = 0; i < tamano; i++) 
            for (int j = 0; j < tamano; j++)
                this.matriz[i][j]=0;
        
        
    }
    
    public void cuadrito(){
        /*for(int i =0 ;i<tamano;i++){
            for(int j=0; j<tamano;j++){
                matriz[i][j]=sc.nextInt();
            }
        }*/
        
        for(int i =0 ;i<tamano;i++){
            for(int j=0; j<tamano;j++){
                System.out.print(matriz[i][j]);
            }
            System.out.println("");
        }
        if(esCuadroMagicoNormal(matriz)){
            JOptionPane.showMessageDialog(null, "¡¡FELICIDADES!! SI es mágico");
            System.out.println("Es magia");
        }else{
            System.out.println("No hubo magia");
            JOptionPane.showMessageDialog(null, "NO es mágico");
        }
    }
    
    
    
    
    
    
    
    
    
    
    static boolean esCuadroMagicoNormal(int[][] c){
        if(valoresValidos(c)&& sumarPorFilas(c) &&
                sumarPorColumnas(c) && sumarPorDiagonales(c)){
            return true;
        }else{
            return false;
        }
    }
    
    static boolean sumarPorDiagonales(int[][] c){
        int N = c.length;
        int constanteMagica = ((int) Math.pow(N, 3)+N)/2;
        int sumaD1=0;
        int sumaD2=0;
        
        for(int i=0;i<c.length;i++){
            for(int j=0; j<c[i].length;j++){
                if(i==j){
                    sumaD1+=c[i][j];
                }
                if(i+j==N-1){
                    sumaD2+=c[i][j];
                }
            }
        }
        if(sumaD1 != constanteMagica || sumaD2 != constanteMagica){
            return false;
        }else{
            return true;
        }
    }
    
    static boolean valoresValidos(int[][] c){
        int valorValido = (int)Math.pow(c.length, 2);
        for(int i=0;i<c.length;i++){
            for(int j=0; j<c[i].length;j++){
                if(c[i][j]<1 || c[i][j]>valorValido){
                    return false;
                }
            }
        }
        return true;
    }
    
    static boolean sumarPorFilas(int[][] c){
        int N = c.length;
        int constanteMagica =((int)Math.pow(N, 3)+N)/2;
        int sumaFila=0;
        
        for(int i=0;i<c.length;i++){
            for(int j=0; j<c[i].length;j++){
                    sumaFila+=c[i][j];
            }
            if(sumaFila != constanteMagica){
                return false;
            }else{
                sumaFila=0;
            }
        }
        return true;
    }
    
    static boolean sumarPorColumnas(int[][] c){
        int N = c.length;
        int constanteMagica =((int)Math.pow(N, 3)+N)/2;
        int sumaColumna=0;
        
        for(int i=0;i<c.length;i++){
            for(int j=0; j<c[i].length;j++){
                    sumaColumna+=c[j][i];
            }
            if(sumaColumna != constanteMagica){
                return false;
            }else{
                sumaColumna=0;
            }
        }
        return true;
    }
}
