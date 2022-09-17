/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.itteam.capa.luces;

import com.itteam.capa.luces.dao.CommandoDao;
import com.itteam.capa.luces.dao.RepeatablePP1Dao;
import com.itteam.capa.luces.models.*;
import com.itteam.capa.luces.services.*;
import com.itteam.capa.luces.utils.Commandos;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class CapaLuces {


    public static void main(String[] args) throws IOException {
        ClienteService cli = new ClienteService(); //Se crea el cliente

        System.out.println("Iniciando cliente\n");

        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter command: ");
            String name = in.nextLine();
            cli.startClient(name); //Se inicia el cliente
        }

    }
}
