package com.example.tp_voiture.Controller;


import com.example.tp_voiture.Services.ClientNotFoundEx;
import com.example.tp_voiture.Services.ClientService;
import com.example.tp_voiture.Services.ReservationService;
import com.example.tp_voiture.Services.VoitureNotFoundEx;
import com.example.tp_voiture.modele.Client;
import com.example.tp_voiture.modele.Reservation;
import com.example.tp_voiture.modele.Voiture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
//@RestController //Json
public class ClientController {
    private final ClientService clientService;
    private ReservationService reservationService;

public ClientController(ClientService clientService,ReservationService reservationService) {
        this.clientService = clientService;
        this.reservationService = reservationService;
}

    @GetMapping("/clients")
    public String Welcome(Model model)
    {
        List<Client> listClient = clientService.listAllClients();
        model.addAttribute("reservation",new Reservation());
        model.addAttribute("client",new Client());
        model.addAttribute("listclient",listClient);
        return "clients";
    }

    @GetMapping(value = "/clients/new")
    public String newClient(Model model)
    {
        model.addAttribute("client",new Client());
        model.addAttribute("pageTitle","New Client");
        return "clients_form";
    }

    @PostMapping(value = "/clients/save")
    public String saveClient(Client client, RedirectAttributes ra)
    {
        clientService.save(client);
        ra.addFlashAttribute("message","Le Client a ete sauvegarde avec succes");
        return "redirect:/clients";
    }

    @GetMapping(value = "/clients/editer/{id}")
    public String EditClient(@PathVariable("id") Integer id,Model model,RedirectAttributes ra)
    {
        try {
            Client client = clientService.findById(id);
            model.addAttribute("client",client);
            model.addAttribute("pageTitle","Edit: (ID: "+ id +") Nom: "+client.getPrenom()+" "+client.getNom());
            return "clients_form";
        }catch (ClientNotFoundEx e)
        {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/clients";
        }
    }
    @GetMapping(value = "/clients/effacer/{id}")
    public String EffacerClient(@PathVariable("id") Integer id,RedirectAttributes ra)
    {
        clientService.delete(id);
        ra.addFlashAttribute("message","Le Client Effacé avec succes");
        return "redirect:/clients";
    }

    @GetMapping("/clients/effacerreservation/{id}")
    public String retourreservetation(@PathVariable("id") Integer id,RedirectAttributes ra)
    {
        ra.addFlashAttribute("message","La Reservation a ete effacé avec succes");
        Reservation reservation = reservationService.SearchById(id);
        reservation.getVoiture().setRented(false);
        reservationService.save(reservation);
        reservationService.delete(id);
        return "redirect:/clients";
    }
}
