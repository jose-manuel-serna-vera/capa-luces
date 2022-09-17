/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itteam.capa.luces.services;

import com.itteam.capa.luces.dao.CommandoDao;
import com.itteam.capa.luces.enums.Modo;
import com.itteam.capa.luces.models.M3;
import com.itteam.capa.luces.models.M4;
import com.itteam.capa.luces.utils.Commandos;
import com.itteam.capa.luces.utils.Convert;

/**
 *
 * @author js-se
 */
public class M4Service extends  Commandos {

    public CommandoDao expresion(M4 m4) {


        CommandoDao commandoDao = new CommandoDao();

        String respuesta = "01"+validateDecrementM4(m4.getA());
        respuesta += validateBtnM4(m4.getB());
        respuesta += validateBtnM4(m4.getC());
        System.out.println("bin = "+respuesta);

        long binario = Long.valueOf(respuesta);

        Convert convert =new Convert();
        String numero = convert.decimalToHex(binario);
        System.out.println("hex = "+ numero);

        respuesta = Modo.M4_.getModo()+"\\u00"+numero;
        commandoDao.setComando(respuesta);
        commandoDao.setLongitud(3);

        System.out.println("COMANDO "+Modo.M4_.getModo()+" = " + commandoDao.getComando());

        String trama = this.test("001", commandoDao.getLongitud(),commandoDao.getComando(), "", "");
        System.out.println("TRAMA =" + trama);



        return commandoDao;

    }



}
