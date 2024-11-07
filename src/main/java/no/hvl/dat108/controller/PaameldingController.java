package no.hvl.dat108.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.service.DeltagerService;
import no.hvl.dat108.service.PassordService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/paameld")
public class PaameldingController {

  @Autowired
  private DeltagerService deltagerService;
  @Autowired
  private PassordService passordService;

  @GetMapping()
  public String paamelding(Model model) {
    model.addAttribute("deltager", new Deltager());
    return "paamelding";
  }

  @PostMapping()
  public String paameld(Model model,
      @Valid @ModelAttribute("deltager") Deltager deltager,
      BindingResult bindingResult, @RequestParam String fornavn, @RequestParam String etternavn,
      @RequestParam String mobil, @RequestParam String kjonn, @RequestParam String passord,
      @RequestParam String repetertPassord, HttpSession session) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("feilmeldinger", "Påmeldingsdetaljer er ugyldige");
      return "paamelding";
    }

    // Validerer passord
    if (!passord.equals(repetertPassord)) {
      model.addAttribute("errorMessage", "Passordene er ikke like.");
      return "paamelding";
    }

    // Sjekk for mobilnummer og legg til deltager dersom gyldig
    if (!deltagerService.addDeltager(deltager)) {
      model.addAttribute("errorMessage", "Mobilnummeret er allerede registrert.");
      return "paamelding";
    }

    String salt = passordService.genererTilfeldigSalt();
    passord = passordService.hashMedSalt(passord, salt);
    Deltager nyDeltager = new Deltager(fornavn, etternavn, mobil, kjonn, passord, salt);
    deltagerService.saveDeltager(nyDeltager);
     // legg til på ra
     model.addAttribute("deltager", nyDeltager);
    // legg til på session

    // Send til bekreftelse siden dersom gyldig deltaker
    return "kvittering";
  }

  @GetMapping("/sjekkMobilnummer")
  @ResponseBody
  public Map<String, Boolean> sjekkMobilnummer(@RequestParam String mobil) {
    Map<String, Boolean> respons = new HashMap<>();
    respons.put("eksistererNummer", deltagerService.eksistererNummer(mobil));
    return respons; // Returnerer et JSON-svar til frontend
  }

}
