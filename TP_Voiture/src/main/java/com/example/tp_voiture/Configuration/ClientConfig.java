package com.example.tp_voiture.Configuration;


import com.example.tp_voiture.Repository.ClientRepository;
import com.example.tp_voiture.Repository.VoitureRepository;
import com.example.tp_voiture.modele.Client;
import com.example.tp_voiture.modele.Reservation;
import com.example.tp_voiture.modele.Voiture;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ClientConfig {
    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository, VoitureRepository voitureRepository)
    {
        return args -> {
            List<Reservation> listreservation = new ArrayList<>();
            List<Reservation> listreservation2 = new ArrayList<>();
            Client client1,client2;


            Voiture voiture1 = new Voiture(2010,50000F,true,"Toyota","ABC-123",65F);
            Voiture voiture2 = new Voiture(2014,40000F,true,"Nissan","ZCA-112",50F);
            Voiture voiture3 = new Voiture(2011,20000F,false,"Vespa","ABC-121",80F);
            Voiture voiture4 = new Voiture(2012,35000F,true,"Ford","ZCA-114",100F);
            Voiture voiture5 = new Voiture(2013,10000F,true,"Chevrolet","ABC-423",200F);
            Voiture voiture6 = new Voiture(2015,100000F,false,"Hunday","ZFH-113",35F);

            client1 = new Client("Cabel","Victor","v@v.com");
            client2 = new Client("Cabel","Miguel","m@m.com");

            Reservation reservation1 = new Reservation("2022-11-21", "2022-11-21", "2022-11-25",voiture1,client1);
            Reservation reservation2 = new Reservation("2022-11-22","2022-11-22","2022-11-24",voiture2,client1);


            listreservation.add(reservation2);
            listreservation.add(reservation1);

            reservation1 = new Reservation("2022-11-21", "2022-11-21", "2022-11-25",voiture4,client2);
            reservation2 = new Reservation("2022-11-22","2022-11-22","2022-11-24",voiture5,client2);

            listreservation2.add(reservation2);
            listreservation2.add(reservation1);

            client1.setReservation(listreservation);
            client2.setReservation(listreservation2);

            voitureRepository.saveAll(List.of(voiture3,voiture6));

            clientRepository.saveAll(List.of(client1,client2));
        };
    }
}
