package com.example.duolingo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.duolingo.entidades.Cliente;
import com.example.duolingo.entidades.Conta;

import java.util.List;

@Dao
public interface ContaDao {

    @Query("SELECT * FROM conta")
    List<Conta> getAll();
    @Query("SELECT * FROM conta WHERE cliente_id like :id limit 1")
    Conta findByClienteId (int id);
    @Insert
    void insert (Conta conta);
    @Insert
    void insertAll (List <Conta> contas);
    @Update
    void update (Conta conta);
    @Delete
    void delete (Conta conta);

}
