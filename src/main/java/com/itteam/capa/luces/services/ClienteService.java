/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itteam.capa.luces.services;

/**
 *
 * @author DELL
 */

import com.itteam.capa.luces.dao.CommandoDao;
import com.itteam.capa.luces.dao.RepeatablePP1Dao;
import com.itteam.capa.luces.models.*;
import com.itteam.capa.luces.utils.Commandos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringEscapeUtils;

public class ClienteService extends Conexion {

    public ClienteService() throws IOException {
        super("cliente");
    } //Se usa el constructor para cliente de Conexion

    public void startClient(String name) //Método para iniciar el cliente
    {
        try {
            Scanner in = new Scanner(System.in);
            switch (name) {
                case "PP5":
                    this.PP5();
                    break;
                case "Z1":
                    this.Z1();
                    break;
                case "Z":
                    this.Z();
                    break;
                case "A":
                    this.A();
                    break;
                case "T":
                    System.out.println("Enter No. block:");
                    String nro = in.nextLine();
                    this.closeT(Integer.parseInt(nro));
                    break;
                case "B":
                    this.B();
                    break;
                case "PP1":
                    this.PP1();
                case "SS":
                    din.read(tmpInp);
                    str3 = new String(tmpInp);
                    str3 = str3.replace("\u0000", "");
                    System.out.println("Entrada 2 = " + str3);
                    System.out.println("Entrada Length 2= " + str3.length());
                    break;
                case "TODO":
                    M1Service x1 = new M1Service();
                    M2Service x2 = new M2Service();
                    M3Service x3 = new M3Service();
                    M4Service x4 = new M4Service();
                    M8Service x8 = new M8Service();
                    MAService xa = new MAService();
                     in = new Scanner(System.in);
                    System.out.println("Enter address:");
                    String luz = in.nextLine();
                    System.out.println("Enter m1 config(ON,ON,ON,ON,OFF)");
                    System.out.println("Enter Led1 (ON /OFF /FLASH /FAST ) BOTON:");
                    String Led1 = in.nextLine();
                    System.out.println("Enter Led2 (ON /OFF /FLASH /FAST ) BOTON:");
                    String Led2 = in.nextLine();
                    System.out.println("Enter Led3 (ON /OFF /FLASH /FAST ) BOTON:");
                    String Led3 = in.nextLine();
                    System.out.println("Enter seg (ON /OFF /FLASH /FAST ) ENCIENDE LOS DISPLAY LED :");
                    String seg = in.nextLine();
                    System.out.println("Enter buz(ON /OFF /FLASH /FAST ) ENCIENDE EL SONIDO :");
                    String buz = in.nextLine();

                    //SETEAR MODELO M1
                    M1 m1 = new M1();
                    m1.setLED1(Led1);
                    m1.setLED2(Led2);
                    m1.setLED3(Led3);
                    m1.setSEG(seg);
                    m1.setBUZ(buz);
                    CommandoDao cm1 =x1.expresion(m1);
                    
                    Commandos c = new Commandos();
                    
                    System.out.println("------------------------------------");

                    //SETEAR MODELO M2
//                    M2 m2 = new M2();
//                    m2.setLED1("ON");
//                    m2.setLED2("ON");
//                    m2.setLED3("ON");
//                    m2.setSEG("ON");
//                    m2.setBUZ("OFF");
//                    CommandoDao cm2 =x2.expresion(m2);
//                    System.out.println("------------------------------------");
//
//                    //SETEAR MODELO M3
//                    M3 m3 = new M3();
//                    m3.setLED1("FAST");
//                    m3.setLED2("FAST");
//                    m3.setLED3("FAST");
//                    m3.setSEG("F");
//                    m3.setBUZ("F");
//                    CommandoDao cm3 =x3.expresion(m3);
//                    System.out.println("------------------------------------");
//
//                    //SETEAR MODELO M4
//                    M4 m4 = new M4();
//                    m4.setA("NOT_USE");
//                    m4.setB("NOT_USE");
//                    m4.setC("USE");
//                    CommandoDao cm4 = x4.expresion(m4);
//                    System.out.println("------------------------------------");
//
//                    //SETEAR MODELO M3
//                    M8 m8 = new M8();
//                    m8.setA("USE");
//                    m8.setB("USE");
//                    m8.setC("NOT_USE");
//                    m8.setD("SEC0");
//                    m8.setE("SEC2");
//                    CommandoDao cm8 =x8.expresion(m8);
//                    System.out.println("------------------------------------");
//
//
//
//                    //COMANDO M
//                    Commandos comando = new Commandos();
//                    Integer longitud = cm1.getLongitud()+cm2.getLongitud() + cm4.getLongitud();
//                    String parametros = cm1.getComando()+cm2.getComando()+cm4.getComando();
//                    System.out.println("Mode array = "+parametros);
//
                    PP5Service pp5Service = new PP5Service();
//
//                    List<RepeatablePP1Dao> pp1Daos = new ArrayList<>();
//                    RepeatablePP1Dao pp1Dao = new RepeatablePP1Dao();
//                    pp1Dao.setAddress("0418");
//                    pp1Dao.setDisplayData("00010");
//                    pp1Dao.setDisplayAfterData("00031");
//                    pp1Daos.add(pp1Dao);
//                    pp1Daos.add(pp1Dao);
//

                    // BLOQUEAR TECLAS S/-
                    MA ma = new MA();
                    ma.setA("NORMAL");
                    ma.setB("NOT_CHANGE");
                    CommandoDao cma = xa.expresion(ma);

                    String comando = cm1.getComando().concat(cma.getComando());//.concat("m4\u0044");
                    System.out.println("comando = "+comando);

                    CommandoDao pp5 = pp5Service.operacion(true,"01",cm1.getLongitud()+cma.getLongitud(),comando,luz,"00003","00005");//+14--32400000300005
                    System.out.println("operacion PP5 = "+pp5.getComando() +"--"+ pp5.getLongitud());
                    
                    String trama = c.test(jobID, pp5.getLongitud(), pp5.getComando(), "", "");
                    trama = StringEscapeUtils.unescapeJava(trama);                    

                    this.command(trama);
//                    CommandoDao pp1_ = pp1Service.operacion(true,"02",0,"","001","00003","00005");
//                    System.out.println("operacion PP1_ = "+pp1_.getComando() +"--"+ pp1_.getLongitud());
//                    CommandoDao pp1__ = pp1Service.operacion(true,"03",0,true,"",pp1Daos);
//                    System.out.println("operacion pp1__ = "+pp1__.getComando() +"--"+ pp1__.getLongitud());
//
//
//                    String trama = comando.test("001",pp1__.getLongitud(),pp1__.getComando(),"","");
//                    System.out.println("trama => "+trama);
                    break;
                default:
                    System.out.println("Comando no existe");
                    break;
            }
            this.jobID++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
   
}
