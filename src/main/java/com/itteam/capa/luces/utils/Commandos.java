/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itteam.capa.luces.utils;

import java.util.List;

/**
 *
 * @author js-se
 */
public class Commandos extends Convert {

    // declaracion de atributos miembro
    private final String STX = "\u0002";     // inicio trama
    private final String ETX = "\u0003";     // finalizacion trama 
    private String SECUENCIA = "";          // secuencia comando  3 caracteres
    private String LONGITUD = "";                // Longitud de datos del commando 0004 Caracteres
    private String MENSAJE = "";               // mensaje a desplegar
    private String COMANDO = "";               // comando en formato para envio via SOCKET a HW Luz
    private String TRAMA = "";                 // trama con comando armado en formato especifico
    private String PARAMETROS = "";     // porcion parametros comando de instruccion
    private List<String> LIST_ADRESS;
    private String ADRESS = "";

    // metodo constructor
    public Commandos() {
        //constructor sin inicializacion de miembros de instancia
    }

    public String test(Integer secuencia,Integer longitud, String parametros, String address, String mensaje) {
        //TRAMA = "\u0002|001|005|m1|\u0032|\u0011|\u0023\u0003";

        SECUENCIA =String.format("%03d",secuencia);
        PARAMETROS = parametros;
        ADRESS = address;
        MENSAJE = mensaje;
       
        LONGITUD =String.format("%04d",longitud);
        TRAMA = STX + SECUENCIA + LONGITUD + PARAMETROS + ADRESS + MENSAJE + ETX;
        return TRAMA;
    }

    public String modoArray(List<String> Adress) {
        TRAMA = "\u0002|001|0022|m1|\u0033|\u0033|\u0021|m2|\u0032|\\u0022|\u0021|m4|\u0044|0160|0418|\u0003";
        TRAMA = STX + SECUENCIA + LONGITUD + COMANDO + PARAMETROS + ADRESS + MENSAJE + ETX;
        return TRAMA;
    }



    public String validate(String valor) {
        switch (valor) {
            case "ON": //ON
                return "2";
            case "OFF": //OFF
                return "1";
            case "FLASH": //FLASH
                return "3";
            case "FAST": //Flash at a high speed
                return "4";
            case "NOT": //No change
                return "7";
            case "F": //No change
                return "F";
            default:
                return "1";
        }
    }

    public String validateBtnM4(String valor) {
        switch (valor) {
            case "USE": //USE
                return "00";
            case "NOT_USE": //NOT_USE
                return "01";
            case "NOT_CHANGE": //NOT_CHANGE
                return "11";
            default:
                return "00";
        }
    }

    public String validateDecrementM4(String valor) {
        switch (valor) {
            case "USE": //USE
                return "01";
            case "NOT_USE": //NOT_USE
                return "00";
            case "NOT_CHANGE": //NOT_CHANGE
                return "11";
            default:
                return "00";
        }
    }

    public String validateM8(String valor) {
        switch (valor) {
            case "USE": //USE
                return "00";
            case "NOT_USE": //NOT_USE
                return "01";
            case "NOT_CHANGE": //NOT_CHANGE
                return "11";
            default:
                return "00";
        }
    }
    public String validateSecM8(String valor) {
        switch (valor) {
            case "SEC0": //SEC0
                return "000";
            case "SEC1": //SEC1
                return "001";
            case "SEC2": //SEC2
                return "010";
            case "SEC3": //SEC3
                return "011";
            case "NOT_SEC": //NOT_SEC
                return "111";
            default:
                return "000";
        }
    }

    public String specified(boolean valor) {
        if (!(valor)) { //NOT_SPEC
            return "00";
        } else if (valor) { //SPEC
            return "05";
        }
        return "00";
    }


    public String validatema(String valor) {
        switch (valor) {
            case "NORMAL": //El espacio no se trata como un valor numérico
                return "00";
            case "SPECIAL": //El espacio se trata como un valor numérico
                return "01";
            default:
                return "00";
        }
    }

    public String validatemaKey(String valor) {
        switch (valor) {
            case "DIG": //DigNumkey
                return "00";
            case "NOT_USE": //Not use
                return "01";
            case "KEY": //+/-key
                return "10";
            case "NOT_CHANGE": //NOT CHANGE
                return "11";
            default:
                return "00";
        }
    }


}
