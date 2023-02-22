package com.example.tp_voiture.Services;
import com.example.tp_voiture.modele.Voiture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VoitureServiceTest {

    VoitureService voitureService = new VoitureService();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {

    }

    @Test
    void listAll() {
        Voiture voiture1 = new Voiture(2010,50000F,true,"Toyota","ABC-123",65F);
        voitureService.save(voiture1);
        List<Voiture> voitureList = voitureService.listAll();
        assertEquals(1,voitureList.size());
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

    @Test
    void listAllDisponibles() {
    }

    @Test
    void searchByFilter() {
    }
}