package com.example.duolingo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.duolingo.bd.MyDatabase;
import com.example.duolingo.entidades.Cliente;
import com.example.duolingo.fragments.Cadastro;

public class DuoUnitTest {

    private Cadastro cadastro;

    @Before
    public void setUp() {
        cadastro = new Cadastro();
    }

    @Test
    public void testInNomeValido() {
        assertTrue(cadastro.isNomeValido("Marcelo"));
        assertFalse(cadastro.isNomeValido("Luc4s"));
    }

}