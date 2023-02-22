package com.example.tp_voiture.Services;

import com.example.tp_voiture.Repository.ReservationRepository;
import com.example.tp_voiture.modele.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {
    private EntityManager entityManager = null;
    private EntityManagerFactory entityManagerFactory = null;

    private Query query;
    @Autowired
    private ReservationRepository reservationRepository;

    public void save(Reservation reservation)
    {
        reservationRepository.save(reservation);
    }
    public List<Reservation> listAll(){
        return reservationRepository.findAll();
    }

    public Reservation SearchById(Integer id)
    {
        return reservationRepository.findById(id).get();
    }

    public void delete(Integer id) {

        reservationRepository.deleteById(id);
    }

    public Reservation SearchByIdVoiture(Integer idvoiture, List<Reservation> lista) {
        for (Reservation temp:lista) {
            if(temp.getVoiture().getIdVoiture()==idvoiture)
                return temp;
        }
        return null;
    }
    public Reservation findById(Integer id) throws ReservationNotFoundEx {
        Optional<Reservation> result = reservationRepository.findById(id);
        if (result.isPresent())
            return result.get();
        throw new ReservationNotFoundEx("Reservation "+id+" Non Trouvee");
    }
}