package no.hvl.dat108.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
  
  @GetMapping()
  public String loginView(Model model) {
    return "loginView";
  }

  @PostMapping()
  public String login(Model model) {
    return "redirect:deltagerliste";
  }

}
