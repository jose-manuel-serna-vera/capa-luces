package com.itteam.capa.luces.services;

import com.itteam.capa.luces.dao.CommandoDao;
import com.itteam.capa.luces.dao.RepeatablePP1Dao;
import com.itteam.capa.luces.enums.Modo;
import com.itteam.capa.luces.utils.Commandos;

import java.util.List;

public class PP5Service extends Commandos {

    //Puede especificar datos de visualización después de presionar la instrucción de operación de bloque FnkeyGive

    private CommandoDao commandoDao = new CommandoDao();
    private Integer longitudOp1 = 18;
    private Integer longitudOp2 = 23;
    private Integer longitudOp3 = 9;
    /*
    *
    Función
    La instrucción de operación de bloque que especifica los datos de visualización y los datos de visualización después de presionar el
    Tecla Fn. (Si se especifica Mode Array, envíe el modo de operación a los módulos de luz)
    * */

    //(1) Especificación del modo de operación・Instrucción de operación
    public CommandoDao operacion(boolean afterFn, String block,Integer longitud, String modeArray, String address, String displayData){
        String commando =
                Modo.PP5_.getModo()+ specified(afterFn)+ block+ modeArray+ address+displayData;
        commandoDao.setComando(commando);
        commandoDao.setLongitud(longitudOp1+longitud);
        return commandoDao;
    }

    //(2) Especificación del modo de operación ・Instrucción de operación ・especifique los datos de visualización después de presionar la tecla Fn
    public CommandoDao operacion(boolean afterFn,String block,Integer longitud,String modeArray,String address,String displayData,String displayPressAfterFn){
        String commando =
                Modo.PP5_.getModo()+ specified(afterFn)+ block+ modeArray+ address+displayData+displayPressAfterFn;
        commandoDao.setComando(commando);
        commandoDao.setLongitud(longitudOp2+longitud);
        return commandoDao;
    }

    //(3) Repetible, instrucción de operación y especificación de datos de visualización después de presionar la tecla Fn.
    public CommandoDao operacion(boolean afterFn, String block,Integer longitud, boolean omittedModeArray,String modeArray, List<RepeatablePP1Dao> address){
        String commando =
                Modo.PP5_.getModo()+ specified(afterFn)+ block;

        commando += omittedModeArray ? "" : modeArray;

        Integer contador = 0;
        for (RepeatablePP1Dao pp1Dao:address) {
                commando +=pp1Dao.getAddress() ;
                commando +=pp1Dao.getDisplayData() ;
                commando +=pp1Dao.getDisplayAfterData() != null ? pp1Dao.getDisplayAfterData() : "";

                contador += pp1Dao.getAddress().length();
                contador += pp1Dao.getDisplayData().length();
                contador += pp1Dao.getDisplayAfterData() != null ? pp1Dao.getDisplayAfterData().length() : 0;

        }
        commandoDao.setComando(commando);
        commandoDao.setLongitud(longitudOp3 +contador+ longitud);
        return commandoDao;
    }



}
