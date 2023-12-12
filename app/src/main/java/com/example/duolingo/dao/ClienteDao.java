package com.example.duolingo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.duolingo.entidades.Cliente;

import java.util.List;

@Dao
public interface ClienteDao {

    @Query("SELECT * FROM cliente")
    List <Cliente> getAll();
    @Query("SELECT * FROM cliente WHERE email like :txtEmail limit 1")
    Cliente findByEmail (String txtEmail);
    @Query("SELECT * FROM cliente WHERE id like :idInformado limit 1")
    Cliente findById (int idInformado);
    @Insert
    void insert (Cliente cliente);
    @Insert
    void insertAll (List <Cliente> clientes);
    @Update
    void update (Cliente cliente);
    @Delete
    void delete (Cliente cliente);



}