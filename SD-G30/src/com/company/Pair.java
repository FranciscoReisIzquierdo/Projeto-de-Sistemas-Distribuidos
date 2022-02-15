package com.company;

import java.io.Serializable;

public class Pair implements Serializable {
    private String origem;
    private String destino;

    public Pair(String origem, String destino) {
        this.origem = origem;
        this.destino = destino;
    }


    public String getKey() {
        return origem;
    }

    public void setKey(String origem) {
        this.origem = origem;
    }

    public String getValue() {
        return destino;
    }

    public void setValue(String destino) {
        this.destino = destino;
    }
}
