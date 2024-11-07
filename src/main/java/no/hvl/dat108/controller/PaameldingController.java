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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
      @RequestParam String repetertPassord, HttpSession session, RedirectAttributes ra) {

    if (bindingResult.hasErrors()) {
      ra.addFlashAttribute("feilmeldinger", "Påmeldingsdetaljer er ugyldige");
      return "redirect:paameld";
    }

    // Validerer passord
    if (!passord.equals(repetertPassord)) {
      ra.addFlashAttribute("errorMessage", "Passordene er ikke like.");
      return "redirect:paameld";
    }

    // Sjekk om mobilnummer eksisterer
    if (deltagerService.eksistererNummer(mobil)) {
      ra.addFlashAttribute("errorMessage", "Mobilnummeret er allerede registrert.");
      return "redirect:paameld";
    }

    String salt = passordService.genererTilfeldigSalt();
    passord = passordService.hashMedSalt(passord, salt);
    Deltager nyDeltager = new Deltager(fornavn, etternavn, mobil, kjonn, passord, salt);
    deltagerService.saveDeltager(nyDeltager);
    
    model.addAttribute("deltager", nyDeltager);
    session.setAttribute("deltager", nyDeltager);

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
