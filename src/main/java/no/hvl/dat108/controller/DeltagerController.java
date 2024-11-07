package no.hvl.dat108.controller;

import java.util.List;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.service.DeltagerService;
import no.hvl.dat108.util.LoginUtil;

@Controller
@RequestMapping("/deltagerliste")
public class DeltagerController {

  @Autowired
  private DeltagerService deltagerService;

  @GetMapping()
  public String deltagerliste(Model model, HttpSession session, RedirectAttributes ra) {
    if (!LoginUtil.erBrukerInnlogget(session)) {
      ra.addFlashAttribute("redirectMessage", "Du må være innlogget for å se deltagerlisten");
			return "redirect:login";
    }
    List<Deltager> alleDeltagere = deltagerService.getAlleDeltagere();
    alleDeltagere.sort(Comparator.comparing(Deltager::getFornavn).thenComparing(Deltager::getEtternavn));
    model.addAttribute("deltagerListe", alleDeltagere);
    return "deltagerliste";
  }

}
