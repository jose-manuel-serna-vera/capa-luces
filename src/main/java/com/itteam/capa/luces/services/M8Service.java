/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itteam.capa.luces.services;

import com.itteam.capa.luces.dao.CommandoDao;
import com.itteam.capa.luces.enums.Modo;
import com.itteam.capa.luces.models.M4;
import com.itteam.capa.luces.models.M8;
import com.itteam.capa.luces.utils.Commandos;
import com.itteam.capa.luces.utils.Convert;

/**
 *
 * @author js-se
 */
public class M8Service extends  Commandos {

    public CommandoDao expresion(M8 m8) {



        CommandoDao commandoDao = new CommandoDao();

        String respuesta = "01"+validateM8(m8.getA());
        respuesta += validateM8(m8.getB());
        respuesta += validateM8(m8.getC());
        respuesta += "01";
        respuesta += validateSecM8(m8.getD());
        respuesta += validateSecM8(m8.getE());
        System.out.println("bin = "+respuesta);

        long binario = Long.valueOf(respuesta);

        Convert convert =new Convert();
        String numero = convert.decimalToHex(binario);
        System.out.println("hex = "+ numero);

        respuesta = Modo.M8_.getModo()+"\\u00"+numero;
        commandoDao.setComando(respuesta);
        commandoDao.setLongitud(4);

        System.out.println("COMANDO "+Modo.M8_.getModo()+" = " + commandoDao.getComando());

        String trama = this.test(1, commandoDao.getLongitud(),commandoDao.getComando(), "", "");
        System.out.println("TRAMA =" + trama);



        return commandoDao;

    }



}
