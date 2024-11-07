package no.hvl.dat108.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginUtil {

  public void loggUtBruker(HttpSession session) {
    if (session != null) {
      session.invalidate();
    }
  }

  public void loggInnBruker(HttpServletRequest request, String mobil, String passord) {
    loggUtBruker(request.getSession());

    HttpSession session = request.getSession();
    session.setAttribute("mobil", mobil);
    session.setAttribute("passord", passord);
  }

  public boolean erBrukerInnlogget(HttpSession session) {
    return session != null && session.getAttribute("mobil") != null
        && session.getAttribute("passord") != null;
  }
}
