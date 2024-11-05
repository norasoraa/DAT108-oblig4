package no.hvl.dat108.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/logout")
public class LogoutController {
  
  @PostMapping()
  public String logout() {
      return "redirect:login";
  }
  
}
