package com.example.DesafioLapes.dominio.usario;

public enum UsuarioCargo {
    ADMIN("admin"),
    COMUM("comum");

    private String cargo;
    UsuarioCargo(String cargo){
        this.cargo = cargo;
    }

    public String listarCargo(){
        return cargo;
    }
}
