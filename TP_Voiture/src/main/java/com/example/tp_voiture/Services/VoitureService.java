package com.example.tp_voiture.Services;

import com.example.tp_voiture.Repository.VoitureRepository;
import com.example.tp_voiture.modele.Option;
import com.example.tp_voiture.modele.Reservation;
import com.example.tp_voiture.modele.Voiture;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VoitureService {
    @Autowired
    private VoitureRepository voitureService;

    public void save(Voiture voiture)
    {
        voitureService.save(voiture);
    }

    public List<Voiture> listAll(){
        return voitureService.findAll();
    }

    public Voiture findById(Integer idvoiture) throws VoitureNotFoundEx {
        Optional<Voiture> result = voitureService.findById(idvoiture);
        if (result.isPresent())
            return result.get();
        throw new VoitureNotFoundEx("Voiture "+idvoiture+" Non Trouvee");
    }

    public void delete(Integer id) throws VoitureNotFoundEx {
        voitureService.deleteById(id);
    }

    public List<Voiture> listAllDisponibles() {
        List<Voiture> templist = voitureService.findAll();
        List<Voiture> sortedList = new ArrayList<>();
        for (Voiture temp: templist) {
            if (temp.isRented()==false)
                sortedList.add(temp);
        }
        return sortedList;
    }

    public List<Voiture> SearchByFilter(Option option) {
        List<Voiture> voitureList=new ArrayList<>();
        String query = " ";
        boolean and = false;
        EntityManager entityManager=null;
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            if (option.getModel() == "" &&  option.getMilage()=="" && option.getPrix()=="") {
                voitureList = listAll();
            }
            else{
                query = " where ";
                if (option.getModel() != ""){
                    query += "model like '"+option.getModel()+"' ";
                    and = true;
                }
                if (option.getMilage() != ""){
                    if (and) {
                        query += " AND mileage <= "+Float.parseFloat(option.getMilage())+" ";
                    }
                    else
                    {
                        query += " mileage <= "+Float.parseFloat(option.getMilage())+" ";
                        and = true;
                    }
                }
                if (option.getPrix() != ""){
                    if (and) {
                        query += " AND price <= "+Float.parseFloat(option.getPrix())+" ";
                    }
                    else
                    {
                        query += " price <= "+Float.parseFloat(option.getPrix())+" ";
                    }
                }
            }
            voitureList = entityManager.createNativeQuery("SELECT * FROM voiture"+query,Voiture.class).getResultList();
            if (voitureList.isEmpty()) {
                voitureList=listAll();
            }
            entityManager.close();

        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            exception.printStackTrace();
        }
        return voitureList;
    }
}
