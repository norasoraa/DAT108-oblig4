package no.hvl.dat108.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.util.LoginUtil;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

  @PostMapping()
  public String logout(HttpSession session, RedirectAttributes ra) {
    LoginUtil.loggUtBruker(session);
    ra.addFlashAttribute("redirectMessage", "Du er logget ut");
    return "redirect:login";
  }

}
