package no.hvl.dat108.controller;

import java.util.List;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.service.DeltagerService;

@Controller
@RequestMapping("/deltagerliste")
public class DeltagerController {

  @Autowired
  private DeltagerService deltagerService;

  @GetMapping()
  public String deltagerliste(Model model, HttpSession session) {
    // Sjekke om bruker er innlogget
    List<Deltager> alleDeltagere = deltagerService.getAlleDeltagere();
    alleDeltagere.sort(Comparator.comparing(Deltager::getFornavn).thenComparing(Deltager::getEtternavn));
    model.addAttribute("deltagerListe", alleDeltagere);
    session.getAttribute("deltager");
    return "deltagerliste";
  }

}
