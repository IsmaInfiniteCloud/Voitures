package com.example.tp_voiture.Controller;

import com.example.tp_voiture.Services.ReservationService;
import com.example.tp_voiture.Services.VoitureNotFoundEx;
import com.example.tp_voiture.Services.VoitureService;

import com.example.tp_voiture.modele.Option;
import com.example.tp_voiture.modele.Reservation;
import com.example.tp_voiture.modele.Voiture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//@RestController
@Controller
public class VoitureController {
    @Autowired
    private final VoitureService voitureService;
    private final ReservationService reservationService;

    public VoitureController(VoitureService voitureService, ReservationService reservationService) {
        this.voitureService = voitureService;
        this.reservationService = reservationService;
    }


    @GetMapping(path = "/voitures/detail/{idvoiture}")
    public String listVoitures(@PathVariable("idvoiture") Integer idvoiture,Model model)
    {
        List<Reservation> lista = reservationService.listAll();
        Reservation reservation = reservationService.SearchByIdVoiture(idvoiture,lista);
        //faire le lien entre le model et la vue
        model.addAttribute("voiture",new Voiture());
        model.addAttribute("reservation",reservation);
        return "voituredetail";
    }

    @GetMapping("/")
    public String Welcome(Model model)
    {
        List<Voiture> listVoiture = voitureService.listAll();
        model.addAttribute("option",new Option());
        model.addAttribute("voiture",new Voiture());
        model.addAttribute("listVoiture",listVoiture);
        return "index";
    }

    @GetMapping("/error")
    public String error(Model model, RedirectAttributes ra)
    {
        model.addAttribute("message","Erreur Prevue");
        List<Voiture> listVoiture = voitureService.listAll();
        model.addAttribute("option",new Option());
        model.addAttribute("voiture",new Voiture());
        model.addAttribute("listVoiture",listVoiture);
        return "redirect:/";
    }

    @GetMapping("/voitures")
    public String ListVoitures(Model model)
    {
        model.addAttribute("option",new Option());
        List<Voiture> listVoiture = voitureService.listAll();
        model.addAttribute("voiture",new Voiture());
        model.addAttribute("listVoiture",listVoiture);
        return "voitures";
    }

    @GetMapping(value = "/voitures/new")
    public String newVoiture(Model model)
    {
        model.addAttribute("voiture",new Voiture());
        model.addAttribute("pageTitle","New Voiture");
        return "voitures_form";
    }

    @PostMapping(value = "/voitures/save")
    public String saveVoiture(Voiture voiture, RedirectAttributes ra)
    {
        voitureService.save(voiture);
        ra.addFlashAttribute("message","La voiture a ete sauvegarde avec succes");
        return "redirect:/voitures";
    }

    @GetMapping(value = "/voitures/editer/{id}")
    public String EditVoiture(@PathVariable("id") Integer id,Model model,RedirectAttributes ra)
    {
        try {
            Voiture voiture = voitureService.findById(id);
            model.addAttribute("voiture",voiture);
            model.addAttribute("pageTitle","Edit: (ID: "+ id +") Model: "+voiture.getModel());
            return "voitures_form";
        }catch (VoitureNotFoundEx e)
        {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/voitures";
        }
    }
    @GetMapping(value = "/voitures/effacer/{id}")
    public String EffacerVoiture(@PathVariable("id") Integer id,RedirectAttributes ra)
    {
        try {
            ra.addFlashAttribute("message","Voiture effacé avec succes");
            voitureService.delete(id);
        }catch (VoitureNotFoundEx e)
        {
            ra.addFlashAttribute("message","Erreur de Sauvegarde");
            return "redirect:/voitures";
        }
        return "redirect:/voitures";
    }

    @PostMapping(value = "/index/search")
    public String searchIndexVoitureFilters(Option option,Model model)
    {
        List<Voiture> tempCheck = voitureService.listAll();
        List<Voiture> listVoiture = voitureService.SearchByFilter(option);
        model.addAttribute("option",new Option());
        model.addAttribute("voiture",new Voiture());
        model.addAttribute("listVoiture",listVoiture);
        model.addAttribute("message","Recherche avec succes");
        if (tempCheck.size()== listVoiture.size())
            model.addAttribute("message","Rien Trouve");
        return "index";
    }

    @PostMapping(value = "/voitures/search")
    public String searchVoitureByFilter(Option option,Model model, RedirectAttributes ra)
    {
        List<Voiture> tempCheck = voitureService.listAll();
        List<Voiture> listVoiture = voitureService.SearchByFilter(option);
        model.addAttribute("option",new Option());
        model.addAttribute("voiture",new Voiture());
        model.addAttribute("listVoiture",listVoiture);
        ra.addFlashAttribute("message","Recherche avec succes");
        if (tempCheck.size()== listVoiture.size())
            ra.addFlashAttribute("message","Rien Trouve");
        return "redirect:/voitures";
    }
}
