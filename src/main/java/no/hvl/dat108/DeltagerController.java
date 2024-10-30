package no.hvl.dat108;

import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;

@Controller
public class DeltagerController {

  @GetMapping("/paamelding")
  public String paamelding(Model model) {
    return "paamelding";
  }

  @PostMapping("/paameld")
  public String paameld(Model model,
      @Valid @ModelAttribute("deltager") Deltager deltager,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("feilmeldinger", "Påmeldingsdetaljer er ugyldige");
      return "paamelding";
    }

    // Validerer passord
    if (!deltager.getPassord().equals(deltager.getRepetertPassord())) {
      model.addAttribute("errorMessage", "Passordene er ikke like.");
      return "paamelding";
    }

    // Sjekk for mobilnummer og legg til deltager dersom gyldig
    if (!Deltagere.addDeltager(deltager)) {
      model.addAttribute("errorMessage", "Mobilnummeret er allerede registrert.");
      return "paamelding";
    }

    // Send til bekreftelse siden dersom gyldig deltaker
    return "paameldt"; 
  }

  @GetMapping("/deltagerliste")
  public String deltagerliste(Model model) {
    List<Deltager> alleDeltagere = Deltagere.getAlleDeltagere();
    alleDeltagere.sort(Comparator.comparing(Deltager::getFornavn).thenComparing(Deltager::getEtternavn));
    model.addAttribute("deltagere", alleDeltagere);
    return "deltagerliste";
  }

  @GetMapping("/sjekkMobilnummer")
  @ResponseBody
  public Map<String, Boolean> sjekkMobilnummer(@RequestParam String mobilnummer) {
    Map<String, Boolean> respons = new HashMap<>();
    respons.put("eksistererNummer", Deltagere.eksistererNummer(mobilnummer));
    return respons; // Returnerer et JSON-svar til frontend
  }

}
