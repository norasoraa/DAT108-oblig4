// Validerer passord
const passord = document.getElementById("passord");
const passordRepert = document.getElementById("repetertPassord");

function sjekkPassord(event) {
  event.preventDefault();
  const passordValue = passord.value;
  const passordRepertValue = passordRepert.value;

  if (passordRepertValue !== passordValue) {
    passordRepert.setCustomValidity("Passordene samsvarer ikke.")
  }
  else {
    passordRepert.setCustomValidity("");
  }
}

// Passordvalidering i begge passordfeltene
passord.addEventListener("input", sjekkPassord);
passordRepert.addEventListener("input", sjekkPassord);


// Validerer mobilnummer
const mobilnummer = document.getElementById("mobilnummer");

function sjekkMobilnummer(event) {
  event.preventDefault();
  const mobilnummerValue = mobilnummer.value;
  mobilnummer.setCustomValidity("");

  fetch(`/sjekkMobilnummer?mobilnummer=${mobilnummerValue}`)
    .then(response => response.json())
    .then(data => {
      if (data.eksistererNummer) {
        mobilnummer.setCustomValidity("Mobilnummeret er allerede registrert.");
        mobilnummer.reportValidity();
      }
      else {
        mobilnummer.setCustomValidity("");
        document.querySelector("form").submit();
      }
    })
    .catch(error => {
      console.error("Feil ved sjekk av mobilnummer", error);
    });
}

// Fjern feilmeldinger dynamisk når brukeren skriver
mobilnummer.addEventListener("input", function () {
  mobilnummer.setCustomValidity("");
})

// Validering av mobilnummer når skjemaet sendes
document.querySelector("form").addEventListener("submit", sjekkMobilnummer);
