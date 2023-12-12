package com.example.duolingo.bd;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.duolingo.dao.ClienteDao;
import com.example.duolingo.dao.ContaDao;
import com.example.duolingo.dao.MultiplaEscolhaDao;
import com.example.duolingo.entidades.Cliente;
import com.example.duolingo.entidades.Conta;
import com.example.duolingo.entidades.MultiplaEscolha;

@Database(entities = {MultiplaEscolha.class, Cliente.class, Conta.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    
    public abstract MultiplaEscolhaDao multiplaEscolhaDao();
    public abstract ClienteDao clienteDao();
    public abstract ContaDao contaDao();

}