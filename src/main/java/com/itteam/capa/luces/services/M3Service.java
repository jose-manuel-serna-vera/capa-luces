/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itteam.capa.luces.services;

import com.itteam.capa.luces.dao.CommandoDao;
import com.itteam.capa.luces.enums.Modo;
import com.itteam.capa.luces.models.M1;
import com.itteam.capa.luces.models.M3;
import com.itteam.capa.luces.utils.Commandos;

/**
 *
 * @author js-se
 */
public class M3Service extends  Commandos {

    public CommandoDao expresion(M3 m3) {



        CommandoDao commandoDao = new CommandoDao();

        String respuesta = Modo.M3_.getModo()+"\\u00" + validate("FLASH");
        respuesta += validate(m3.getLED1());
        respuesta += "\\u00";
        respuesta += validate(m3.getLED2());
        respuesta += validate(m3.getLED3());
        respuesta += "\\u00";
        respuesta += validate(m3.getSEG());
        respuesta += validate(m3.getBUZ());

        commandoDao.setComando(respuesta);
        commandoDao.setLongitud(5);

        System.out.println("COMANDO "+Modo.M3_.getModo()+" = " + commandoDao.getComando());

        String trama = this.test(1, commandoDao.getLongitud(),commandoDao.getComando(), "", "");
        System.out.println("TRAMA =" + trama);


        return commandoDao;

    }



}
