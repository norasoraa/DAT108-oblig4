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
const mobil = document.getElementById("mobil");

function sjekkMobilnummer(event) {
  event.preventDefault();
  const mobilValue = mobil.value;
  mobil.setCustomValidity("");

  fetch(`/sjekkMobilnummer?mobil=${mobilValue}`)
    .then(response => response.json())
    .then(data => {
      if (data.eksistererNummer) {
        mobil.setCustomValidity("Mobilnummeret er allerede registrert.");
        mobil.reportValidity();
      }
      else {
        mobil.setCustomValidity("");
        document.querySelector("form").submit();
      }
    })
    .catch(error => {
      console.error("Feil ved sjekk av mobilnummer", error);
    });
}

// Fjern feilmeldinger dynamisk når brukeren skriver
mobil.addEventListener("input", function () {
  mobil.setCustomValidity("");
})

// Validering av mobilnummer når skjemaet sendes
document.querySelector("form").addEventListener("submit", sjekkMobilnummer);
