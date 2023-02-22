package com.example.tp_voiture.Services;

import com.example.tp_voiture.Repository.ClientRepository;
import com.example.tp_voiture.modele.Client;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void save(Client client)
    {
        clientRepository.save(client);
    }

    public List<Client> listAllClients()
    {
       return clientRepository.findAll();
    }


    public Client findById(Integer id) throws ClientNotFoundEx {
        Optional<Client> result = clientRepository.findById(id);
        if (result.isPresent())
            return result.get();
        throw new ClientNotFoundEx("Client "+id+" Non Trouvee");
    }

    public void delete(Integer id) {
        clientRepository.deleteById(id);
    }

}
