package no.hvl.dat108.controller;

import java.util.List;
import java.util.Comparator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.service.DeltagerService;

@Controller
@RequestMapping("/deltagerliste")
public class DeltagerController {

  @GetMapping()
  public String deltagerliste(Model model) {
    List<Deltager> alleDeltagere = DeltagerService.getAlleDeltagere();
    alleDeltagere.sort(Comparator.comparing(Deltager::getFornavn).thenComparing(Deltager::getEtternavn));
    model.addAttribute("deltagere", alleDeltagere);
    return "deltagerliste";
  }

}
