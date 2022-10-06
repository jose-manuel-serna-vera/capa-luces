/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itteam.capa.luces.services;

import com.itteam.capa.luces.dao.CommandoDao;
import com.itteam.capa.luces.enums.Modo;
import com.itteam.capa.luces.models.MA;
import com.itteam.capa.luces.utils.Commandos;
import com.itteam.capa.luces.utils.Convert;

/**
 *
 * @author js-se
 */
public class MAService extends  Commandos {

    public CommandoDao expresion(MA ma) {



        CommandoDao commandoDao = new CommandoDao();

        String respuesta = "0100"+validatema(ma.getA());
        respuesta += validatemaKey(ma.getB());
        System.out.println("bin = "+respuesta);

        long binario = Long.valueOf(respuesta);

        Convert convert =new Convert();
        String numero = convert.decimalToHex(binario);
        System.out.println("hex = "+ numero);

        respuesta = Modo.MA_.getModo()+"\\u00"+numero;
        commandoDao.setComando(respuesta);
        commandoDao.setLongitud(3);

        System.out.println("COMANDO "+Modo.MA_.getModo()+" = " + commandoDao.getComando());

        String trama = this.test(1, commandoDao.getLongitud(),commandoDao.getComando(), "", "");
        System.out.println("TRAMA =" + trama);



        return commandoDao;

    }



}
