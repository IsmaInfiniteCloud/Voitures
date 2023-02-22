package com.example.tp_voiture.Repository;


import com.example.tp_voiture.modele.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository <Client,Integer> {
}
