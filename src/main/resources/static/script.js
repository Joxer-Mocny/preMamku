function generateTip() {
  const game = document.getElementById("game").value;
  const mode = document.getElementById("mode").value;
  const resultDiv = document.getElementById("result");

  fetch(`/api/tip?game=${game}&mode=${mode}`)
    .then(response => {
      if (!response.ok) throw new Error("Chyba pri načítaní tipu.");
      return response.json();
    })
    .then(data => {
      let html = `<strong class="main">Hlavné čísla:</strong> ${data.mainNumbers.join(", ")}`;
      if (data.extraNumbers && data.extraNumbers.length > 0) {
        html += `<br><strong class="extra">Dodatkové čísla:</strong> ${data.extraNumbers.join(", ")}`;
      }
      resultDiv.innerHTML = html;
    })
    .catch(error => {
      resultDiv.innerHTML = `<span style='color: red;'>${error.message}</span>`;
    });
}

function generateUnseenCombinations() {
  const resultDiv = document.getElementById("result");
  resultDiv.innerHTML = "<em>Načítavam kombinácie...</em>";

  fetch('/api/keno5/unseen')
    .then(response => {
      if (!response.ok) throw new Error("Nepodarilo sa načítať kombinácie.");
      return response.json();
    })
    .then(data => {
      const topCombos = pickTopCombinations(data, 3);
      resultDiv.innerHTML = `
        <strong>Najlepšie nepadnuté kombinácie:</strong><br>
        <ol>
          <li>${topCombos[0].join(", ")}</li>
          <li>${topCombos[1].join(", ")}</li>
          <li>${topCombos[2].join(", ")}</li>
        </ol>
        <small>(${data.length} nepadnutých kombinácií nájdených)</small>
      `;
    })
    .catch(error => {
      resultDiv.innerHTML = `<span style='color: red;'>${error.message}</span>`;
    });
}

function pickTopCombinations(combos, count) {
  return combos
    .map(combo => ({
      combo,
      range: Math.max(...combo) - Math.min(...combo)
    }))
    .sort((a, b) => b.range - a.range)
    .slice(0, count)
    .map(entry => entry.combo);
}
