package com.example.tp_voiture.Repository;

import com.example.tp_voiture.modele.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture,Integer> {
}
