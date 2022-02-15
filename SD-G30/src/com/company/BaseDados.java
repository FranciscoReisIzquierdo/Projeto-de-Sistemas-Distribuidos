package com.company;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BaseDados implements Serializable {
    private Map<String, String> clientes;
    private Map<String, String> administradores;
    private List<LocalDate> cancelados;
    //Origem, Destino
    private Map<Pair, Voo> listaVoos;
    private Map<String, List<Reserva>> reservas;
    public Lock registosLock= new ReentrantLock();
    public Lock voosLock= new ReentrantLock();
    public Lock canceladosLock= new ReentrantLock();


    public BaseDados(){
        this.clientes= new ConcurrentHashMap<>();
        this.administradores= new ConcurrentHashMap<>();
        this.reservas= new ConcurrentHashMap<>();
        this.cancelados= new ArrayList<>();
        this.listaVoos= new ConcurrentHashMap<>();

        this.administradores.put("duarte", "1234567");
    }


    public Map<String, String> getClientes() {
        return clientes;
    }

    public void setClientes(Map<String, String> clientes) {
        this.clientes = clientes;
    }

    public Map<String, String> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(Map<String, String> administradores) {
        this.administradores = administradores;
    }

    public List<LocalDate> getCancelados() {
        return cancelados;
    }

    public void setCancelados(List<LocalDate> cancelados) {
        this.cancelados = cancelados;
    }

    public Map<String, List<Reserva>> getReservas() {
        return reservas;
    }

    public void setReservas(Map<String, List<Reserva>> reservas) {
        this.reservas = reservas;
    }

    public Map<Pair, Voo> getListaVoos() {
        return listaVoos;
    }

    public void setListaVoos(Map<Pair, Voo> listaVoos) {
        this.listaVoos = listaVoos;
    }

    public Voo getVoo(Pair pair){
        for(Map.Entry<Pair, Voo> entry: this.listaVoos.entrySet()){
            if(entry.getKey().getKey().equals(pair.getKey()) && entry.getKey().getValue().equals(pair.getValue())) return entry.getValue();
        }
        return null;
    }
}
