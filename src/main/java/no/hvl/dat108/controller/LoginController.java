package no.hvl.dat108.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.service.DeltagerService;
import no.hvl.dat108.service.PassordService;
import no.hvl.dat108.util.LoginUtil;

@Controller
@RequestMapping("/login")
public class LoginController {

  @Autowired
  private PassordService passordService;
  @Autowired
  private DeltagerService deltagerService;

  @GetMapping()
  public String loginView(Model model) {
    return "loginView";
  }

  @PostMapping()
  public String login(@RequestParam String mobil, @RequestParam String passord, HttpServletRequest request,
      RedirectAttributes ra) {
    Deltager deltager = deltagerService.finnDeltager(mobil);

    if (!deltagerService.eksistererNummer(mobil) ||
        !passordService.erKorrektPassord(passord, deltager.getSalt(), deltager.getHash())) {
      ra.addFlashAttribute("errorMessage", "Ugyldig brukernavn og/eller passord");
      return "redirect:login";
    }
    LoginUtil.loggInnBruker(request, deltager, mobil, deltager.getHash(), deltager.getSalt());
    return "redirect:deltagerliste";
  }

}
