/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itteam.capa.luces.models;

import com.itteam.capa.luces.services.ClienteService;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private final int PUERTO = 5003; //Puerto para la conexión
    private final String HOST = "192.168.1.254"; //Host para la conexión
    public Socket s;
    public byte[] tmp, tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7, tmp8 = new byte[256];
    public byte[] tmpInp = new byte[1000];
    public String str, str1, str3, str4, str5, str6;
    public DataInputStream din;
    public DataOutputStream dout;
    public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public Conexion(String tipo) throws IOException //Constructor
    {
        isFilled = false;     // sets false to false
        jobID = ++count;
        if (tipo.equals("cliente")) {
            s = new Socket("192.168.1.254", 5003);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

        }else{

        }

    }

    public static int count = 0;
    public int jobID;
    public String name;

    public boolean isFilled; // boolean defaults to false

    public void PP5() {
        //Resetea una luz
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Adress: ");
        name = in.nextLine();
        /*
        PP5 - Comando de instruccion de operacion
        
        En guia de comandos (Comref_V38E 2010 0526.pdf): 
            PG 31:  Comportamiento por defecto de los modulos segun familia
            PG 33:  Explicacion del modo ARRAY (la combinacion de posibles valores para los parametros de cada uno de los comandos de 
                    son guardados en arreglos de tibo binario
            PG 34:  Se explica cada uno de los modos: M1,M2,M3,M4,M8.... y los posibles combinaciones de parametrizaciones para: 
                    los LED 0,1,2, el SEG y el BUZ
            PG 38:  Se explica como se aplican los comandos de modo de operacion (M*) cuando aplican para direcciones de modulos 
                    especificas o cuando se trabaja con WILDCARDs lo que permite el envio de la informacion a multiples direcciones
                    de luz sin necesidad de especificar cada direccion individualmente (EJ: 1???, envia la informacion a todas las 
                    luces que comienzan por 1)
            PG 39:  Ejemplos de como aplicar las diferentes parametrizaciones de los comanos M* visto en dos formas, cuando el comando
                    aplica para direcciones especificas o cuando aplica para multiples direcciones cuando se usan WILDCARDS
        NOTAS IMPORTANTES: 
            - SE DEBE TERNER PRESENTE QUE EL COMANDO ENVIADO A LA LUZ, REQUIERE ESTAR EN FORMATO HEXADECIMAL
            ... EL COMANDO SE ENVIA EN FORMATO HEXADECIMAL ACOMPAÑADO DE LA SECUENCIA DE ESCAPE \U, POR EJEMPLO SI FUERA 
            ... REQUERIDO ENVIAR EL COMANDO 37 (37H), SE ENVIA ASI: \u0037
            - EL COMPORTAMIENTO DE CADA LED, SEG, BUZ ES INDICADO EN FORMATO BINARIO 1-0
            - UNA VEZ SE TIENE DEFINIDO EL COMPORTAMIENTO EN FORMATO BINARIO, ESTE DEBE SER TRADUCIDO HEXADECIMAL
         */
        try {
            /*
                Esta variacion del comnado PP5, incluye la parametrizacion para los comandos de modo de operacion M1,M2,M3,M4
                "\u00020020026PP5050000" 
                    -   Esta porcion corresponde al STX - Secuencia TX enviada a controladora - Longitud comando - Comando PP5 (PP5050000)
                + "m1" + "\u0032" + "\u0012" + "\u0021"
                    -   M1 comando de operacion indica si se varia el comportamiento de los LED, SEG, BUZ, por defecto antes del envio de datos a 
                        la luz. 
                        \u0032 + \u0012 + \u0021    =   1100100001001000100001
                        11 (fijo al inicio del comando) - 0010 (LED2 ON) - 0001 (LED1 OFF ) - 0010 (LED0 ON) - 0010 (SEG ON) 
                        0001 (BUZ OFF)
                        La combinacion de ON\OFF de los LED (2,1,0) permite variar el color del boton para determinadas familias de 
                        modulos de luces. Para el caso la combinacion es LED2 ON - LED1 OFF - LED0 ON, genera que el boton se ilumine 
                        en color porpura (Ver PG 125 carta de colores. Tener presente que segun se definan los comandos para direcciones
                        especificas o multiples direcciones con WILDCARD, la tabla aplica diferente y se parametriza en fomrma
                        diferente. 
                + "m4" + "\u0050" + "0160    3" + "\u0003";
                    -   \0050   =   Define el comportamiento para las funciones FN KEY DECREMENT, KEY FN y CONFIRM BUTTON
                                    El Hexadecimal equivale al binario 1010000
                                    1   (en realidad 01, es fijo dentro de la parametrizacion de este comando M)
                                    01  Indica si usa funcion FN KEY DECREMENT ( 01 = se usa)
                                    00  Indica si usa funcion FN KEY ( 00 = se usa)
                                    00  Indica si usa funcion COMFIRM BUTTON ( 00 = se usa)
             */
            str1 = "\u000200" + String.valueOf(jobID) + "0026PP5050000"
                    //+ "m1" + "\u0032" + "\u0012" + "\u0021" 
                    + "m1" + "\u0032" + "\u0013" + "\u0021"
                    //+ "m2" + "\u0031" + "\u0011" + "\u0011"
                    + "m4" + "\u0040" + name + "    8" + "\u0003";

//            str1 = "\u00020020041PP5050599" 
//                    // OJO: \u005c\ USADO PARA ESCAPAR SECUENCIA \U0022 Q' COINCIDE CON '"' - "\u005c\u0022";
//                    + "m1" + "\u0031" + "\u005c\u0022" + "\u0021" 
//                    + "m2" + "\u0031" + "\u0021" + "\u0072" 
//                    + "m3" + "\u0031" + "\u0021" + "\u0021"
//                    + "m4" + "\u0050" + "0160  91512345" + "\u0003";
            /*
             Escribir informacion en los BAY DISPLAY - MODULO DE 10 CARACTERES
             */
//            str1 = "\u0002" + "0020017X7070LIGHT MODULE" + "\u0003";
            System.out.println("Entrada ="+str1);
            tmp = str1.getBytes();
            dout.write(tmp);
            dout.flush();
            din.read(tmpInp);
            str3 = new String(tmpInp);
            System.out.println("Salida ="+str3);

        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Z1() {
        try {
            //Resetea todo el 
            str1 = "\u000200" + String.valueOf(jobID) + "0002Z1\u0003";//,str2="";
            System.out.println("Salida = " + str1);
            tmp = str1.getBytes();
            dout.write(tmp);
            dout.flush();
            din.read(tmpInp);
            str3 = new String(tmpInp);
            System.out.println("Entrada = " + str3);

            jobID = 0;
            //dout.close();  
            //s.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Z() {
        try {
            //Resetea una luz
            Scanner in = new Scanner(System.in);
            System.out.print("Enter Adress: ");
            String name = in.nextLine();
            str1 = "\u000200" + String.valueOf(jobID) + "0005Z" + name + "\u0003";//,str2="";
            System.out.println("Salida = " + str1);
            tmp = str1.getBytes();
            dout.write(tmp);
            dout.flush();
            din.read(tmpInp);
            str3 = new String(tmpInp);
            System.out.println("Entrada = " + str3);

            //dout.close();  
            //s.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void B() {
        try {
            //Resetea una luz
            Scanner in = new Scanner(System.in);
            System.out.print("Enter Adress: ");
            String name = in.nextLine();
            str1 = "\u000200" + String.valueOf(jobID) + "0005Z" + name + "\u0003";//,str2="";
            System.out.println("Salida = " + str1);
            tmp = str1.getBytes();
            dout.write(tmp);
            dout.flush();
            din.read(tmpInp);
            str3 = new String(tmpInp);
            
            String[] parts = str3.split(">");
            
            for (String part : parts) {
                System.out.println("Entrada = " + part);                
            }

            //dout.close();  
            //s.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void A() { //Comandos de Mantenimiento (A, Az, Ac)Cada módulo muestra la dirección propia o la configuración de la dirección para cada dispositivo.
        try {
            //Resetea todo el 
            str1 = "\u00020020001A\u0003";//,str2="";
            System.out.println("Salida = " + str1);
            tmp = str1.getBytes();
            dout.write(tmp);
            dout.flush();
            din.read(tmpInp);
            str3 = new String(tmpInp);
            System.out.println("Entrada = " + str3);

            //dout.close();  
            //s.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PP1() {
        /*
        PP1 = 
         */

        try {
            str1 = "\u00020020031PP5050000m1" + "\u0033" + "\u0033" + "\u0021"
                    + "m2" + "\u0032" + "\u005c\u0022\u0021"
                    + "m4" + "\u0044" + "7070" + "3240" + "\u0003";
            str1 = "\u00020010019M"
                    //+ "m1" + "\u0031" + "\u0021" + "\u0011" // original
                    + "m1" + "\u0032" + "\u005c\u0022" + "\u0021"
                    //+ "m2" + "\u0032" + "\u005c\u0022" + "\u0021"
                    + "m2" + "\u0031" + "\u0011" + "\u0011" // original
                    //+ "7070" + "\u0003"; // original
                    //+ "m4" + "\u0054" + "01603240" + "\u0003"; // original
                    + "m4" + "\u0054" + "7070" + "\u0003";
            System.out.println(str1);
            tmp = str1.getBytes();
            dout.write(tmp);
            dout.flush();
            din.read(tmpInp);
            str3 = new String(tmpInp);
            System.out.println(str3);
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
