package no.hvl.dat108.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.service.DeltagerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/paameld")
public class PaameldingController {

  @GetMapping()
  public String paamelding() {
    return "paamelding";
  }

  @PostMapping()
  public String paameld(Model model,
      @Valid @ModelAttribute("deltager") Deltager deltager,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("feilmeldinger", "Påmeldingsdetaljer er ugyldige");
      return "redirect:paameld";
    }

    // Validerer passord
    if (!deltager.getPassord().equals(deltager.getRepetertPassord())) {
      model.addAttribute("errorMessage", "Passordene er ikke like.");
      return "redirect:paameld";
    }

    // Sjekk for mobilnummer og legg til deltager dersom gyldig
    if (!DeltagerService.addDeltager(deltager)) {
      model.addAttribute("errorMessage", "Mobilnummeret er allerede registrert.");
      return "redirect:paameld";
    }

    // Send til bekreftelse siden dersom gyldig deltaker
    return "kvittering"; 
  }

  @GetMapping("/sjekkMobilnummer")
  @ResponseBody
  public Map<String, Boolean> sjekkMobilnummer(@RequestParam String mobil) {
    Map<String, Boolean> respons = new HashMap<>();
    respons.put("eksistererNummer", DeltagerService.eksistererNummer(mobil));
    return respons; // Returnerer et JSON-svar til frontend
  }
  
}
