package com.example.duolingo.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (
        tableName = "Conta",
        foreignKeys = @ForeignKey(
                entity = Cliente.class,
                parentColumns = "id",
                childColumns = "cliente_id",
                onDelete = ForeignKey.CASCADE
        )
)
public class Conta {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "conta_id")
    private int conta_id;
    @ColumnInfo(name = "cliente_id")
    private int cliente_id;
    @ColumnInfo(name = "total_acertos")
    private int total_acertos;
    @ColumnInfo(name = "total_perguntas")
    private int total_perguntas;

    public Conta(int conta_id, int cliente_id) {
        this.conta_id = conta_id;
        this.cliente_id = cliente_id;
        this.total_acertos = 0;
        this.total_perguntas = 0;
    }

    public int getConta_id() {
        return conta_id;
    }

    public void setConta_id(int conta_id) {
        this.conta_id = conta_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getTotal_acertos() {
        return total_acertos;
    }

    public void setTotal_acertos(int total_acertos) {
        this.total_acertos = total_acertos;
    }

    public int getTotal_perguntas() {
        return total_perguntas;
    }

    public void setTotal_perguntas(int total_perguntas) {
        this.total_perguntas = total_perguntas;
    }
}