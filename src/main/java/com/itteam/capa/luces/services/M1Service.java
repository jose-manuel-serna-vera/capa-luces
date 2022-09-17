/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itteam.capa.luces.services;

import com.itteam.capa.luces.dao.CommandoDao;
import com.itteam.capa.luces.enums.Modo;
import com.itteam.capa.luces.models.M1;
import com.itteam.capa.luces.utils.Commandos;

/**
 *
 * @author js-se
 */
public class M1Service extends  Commandos {


    public CommandoDao expresion(M1 m1) {


        CommandoDao commandoDao = new CommandoDao();

        String respuesta = Modo.M1_.getModo()+"\\u00" + validate("FLASH");
        respuesta += validate(m1.getLED1());
        respuesta += "\\u00";
        respuesta += validate(m1.getLED2());
        respuesta += validate(m1.getLED3());
        respuesta += "\\u00";
        respuesta += validate(m1.getSEG());
        respuesta += validate(m1.getBUZ());

        commandoDao.setComando(respuesta);
        commandoDao.setLongitud(5);

        System.out.println("COMANDO "+Modo.M1_.getModo()+" = " + commandoDao.getComando());

        String trama = this.test("001", commandoDao.getLongitud(),commandoDao.getComando(), "", "");
        System.out.println("TRAMA =" + trama);


        return commandoDao;

    }

}
