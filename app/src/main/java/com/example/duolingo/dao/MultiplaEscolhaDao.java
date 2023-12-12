package com.example.duolingo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.duolingo.entidades.MultiplaEscolha;

import java.util.List;

@Dao
public interface MultiplaEscolhaDao {

    @Query("SELECT * FROM multiplaEscolha")
    List<MultiplaEscolha> getAll();
    @Query("SELECT * FROM multiplaEscolha WHERE id like :idInformado limit 1")
    MultiplaEscolha findById (int idInformado);
    @Insert
    void insert (MultiplaEscolha pergunta);
    @Insert
    void insertAll (List <MultiplaEscolha> perguntas);
    @Update
    void update (MultiplaEscolha pergunta);
    @Delete
    void delete (MultiplaEscolha pergunta);
}
