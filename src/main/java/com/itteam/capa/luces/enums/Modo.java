/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itteam.capa.luces.enums;

/**
 *
 * @author js-se
 */
public enum Modo {
    
    /*
        Con (Mode Array), los modos de operación existentes se pueden combinar y usar dentro de un solo comando.
        Debido a que se pueden enviar varios modos de operación como 1 comando, se reduce la cantidad de comandos.
    */
    
    /*
        La matriz de modo puede especificar los siguientes comandos:
        Comando de modo de operación: M
        Comandos de instrucción de operación: PP1, PP5, FP1, FP5, Pm
        Comandos de visualización: PP2, FP2
        El tipo de modo es un identificador de modo de operación. El valor de modo es un valor de configuración.
        Los tipos de modo son los siguientes:
    */

    M1_("m1"),//Display status (Mostrar estado)
    M2_("m2"),//Display status after pressing the CONFIRM button (Mostrar estado después de presionar el botón CONFIRMAR)
    M3_("m3"),//Display status after pressing the Fn key (Mostrar estado después de presionar la tecla Fn)
    M4_("m4"),//CONFIRM button，Fn key (Botón CONFIRMAR, tecla Fn)
    M8_("m8"),//Setup function of “poka yoke” light module (Función de configuración del módulo de luz "poka yoke")
    MA_("ma"),//Use / Not use of the quantity revision key (Usar/no usar la tecla de revisión de cantidad)
    MB_("mb"),//Scroll setup condition after pressing the Fn key * for the TW2060 only ( Condición de configuración de desplazamiento después de presionar la tecla Fn * solo para el TW2060)
    MD_("md"),//Specification of expand operation mode (Especificación del modo de operación de expansión )


    PP1_("PP105"),//PP1 Instrucción de operación de bloque con datos de visualización especificados después de presionar la tecla Fn
    PP5_("PP505"), //Instrucción de operación de bloque reescribible con datos de visualización especificado después de presionar la tecla Fn
    P1("P1"),//Instrucción de operación de bloque
    P4("P4"),//Instrucción de operación de bloque con modo de operación
    P5("P5"),//Instrucción de operación de bloque reescribible con modo de operación
    L("L"),//Instrucción de operación individual.
    FP1("FP1"),//Instrucción de operación de bloque con datos de visualización especificados después   presionando la tecla Fn para el tipo TW2060
    FP5("FP5");//Instrucción de operación de bloque reescribible con datos de visualización especificado después de presionar la tecla Fn para el tipo TW2060


    private final String modo;

    Modo(String modo) {
        this.modo = modo;
    }

    public String getModo() {
        return this.modo;
    }

}
