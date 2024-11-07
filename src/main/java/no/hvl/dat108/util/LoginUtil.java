package no.hvl.dat108.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.model.Deltager;

public class LoginUtil {

  public static void loggUtBruker(HttpSession session) {
    if (session != null) {
      session.invalidate();
    }
  }

  public static void loggInnBruker(HttpServletRequest request, Deltager deltager, String mobil, String hash,
      String salt) {
    loggUtBruker(request.getSession());

    HttpSession session = request.getSession();
    session.setAttribute("mobil", mobil);
    session.setAttribute("hash", hash);
    session.setAttribute("salt", salt);
    session.setAttribute("deltager", deltager);
  }

  public static boolean erBrukerInnlogget(HttpSession session) {
    return session != null && session.getAttribute("mobil") != null
        && session.getAttribute("hash") != null && session.getAttribute("salt") != null;
  }
}
