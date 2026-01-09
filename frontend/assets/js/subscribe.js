const API_URL = 'http://localhost:8080';
const form = document.getElementById('subscribeForm');

form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const email = document.getElementById('emailInput').value;
    const inlineError = document.getElementById('inline-error');

    try {
        const response = await fetch(`${API_URL}/subscribe`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email: email })
        });

        if (response.status === 201) {
            showScreen('success');
        } else if (response.status === 400 || response.status === 409) {
            inlineError.classList.remove('hidden');
        } else {
            showScreen('error');
        }
    } catch (err) {
        showScreen('error');
        console.error("Erro na requisição:", err);
    }
});

function showScreen(screen) {
    document.getElementById('screen-form').classList.add('hidden');
    document.getElementById('screen-success').classList.add('hidden');
    document.getElementById('screen-error').classList.add('hidden');
    document.getElementById(`screen-${screen}`).classList.remove('hidden');
}

function resetView() {
    document.getElementById('emailInput').value = '';
    document.getElementById('inline-error').classList.add('hidden');
    showScreen('form');
}