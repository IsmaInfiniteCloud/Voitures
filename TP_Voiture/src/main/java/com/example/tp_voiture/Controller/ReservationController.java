package com.example.tp_voiture.Controller;

import com.example.tp_voiture.Services.*;
import com.example.tp_voiture.modele.Client;
import com.example.tp_voiture.modele.Reservation;
import com.example.tp_voiture.modele.Voiture;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ReservationController {
    private final ReservationService reservationService;
    private final VoitureService voitureService;
    private final ClientService clientService;

    public ReservationController(ReservationService reservationService, VoitureService voitureService, ClientService clientService) {
        this.reservationService = reservationService;
        this.voitureService = voitureService;
        this.clientService = clientService;
    }


    @GetMapping("/reservations")
    public String Welcome(Model model)
    {
        List<Reservation> listReservation = reservationService.listAll();
        //faire le lien entre le model et la vue
        model.addAttribute("voiture",new Voiture());
        model.addAttribute("reservation",new Reservation());
        model.addAttribute("listReservation",listReservation);
        return "reservations";
    }

    @GetMapping("/reservations/new")
    public String newreservation(Model model)
    {
        List<Client> clientList = clientService.listAllClients();
        List<Voiture> voitureList = voitureService.listAllDisponibles();
        model.addAttribute("voitureList",voitureList);
        model.addAttribute("clientList",clientList);
        model.addAttribute("reservation",new Reservation());
        model.addAttribute("pageTitle","Add New Reservation");
        return "reservations_form";
    }

    @GetMapping("/reservations/detail/{id}")
    public String detailreservetation(@PathVariable("id") Integer id,Model model )
    {
        Reservation reservation= reservationService.SearchById(id);
        //faire le lien entre le model et la vue
        model.addAttribute("voiture",new Voiture());
        model.addAttribute("client",new Client());
        model.addAttribute("reservation",reservation);
        return "reservationdetail";
    }

    @PostMapping(value = "/reservations/save")
    public String saveReservation(Reservation reservation, RedirectAttributes ra) {
        reservation.getVoiture().setRented(true);
        reservationService.save(reservation);
        ra.addFlashAttribute("message","La Reservation a ete sauvegarde avec succes");
        return "redirect:/reservations";
    }

    @GetMapping(value = "/reservations/editer/{id}")
    public String EditReservation(@PathVariable("id") Integer id,Model model)
    {
        Reservation reservation = reservationService.SearchById(id);
        reservation.getVoiture().setRented(false);
        reservationService.save(reservation);
        List<Client> clientList = clientService.listAllClients();
        List<Voiture> voitureList = voitureService.listAllDisponibles();
        model.addAttribute("voitureList",voitureList);
        model.addAttribute("clientList",clientList);
        model.addAttribute("reservation",reservation);
        model.addAttribute("pageTitle","Edit: (ID - "+ id +") Nom: "+reservation.getClient().getNom()+" "+reservation.getClient().getPrenom());
        return "reservations_form";
    }

    @GetMapping("/reservations/effacer/{id}")
    public String retourreservetation(@PathVariable("id") Integer id,RedirectAttributes ra)
    {
        ra.addFlashAttribute("message","La Reservation a ete effacé avec succes");
        Reservation reservation = reservationService.SearchById(id);
        reservation.getVoiture().setRented(false);
        reservationService.save(reservation);
        reservationService.delete(id);
        return "redirect:/reservations";
    }




}
