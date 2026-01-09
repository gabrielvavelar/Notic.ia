const API_URL = 'http://localhost:8080';

async function performUnsubscribe() {
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('token');

    if (!token) {
        showState('error');
        document.getElementById('error-desc').innerText = "Nenhum token foi encontrado na URL.";
        return;
    }

    try {
        const response = await fetch(`${API_URL}/unsubscribe?token=${token}`, {
            method: 'DELETE'
        });

        if (response.status === 204) {
            showState('success');
        } else {
            showState('error');
        }
    } catch (err) {
        showState('error');
        console.error(err);
    }
}

function showState(state) {
    document.getElementById('state-loading').classList.add('hidden');
    document.getElementById(`state-${state}`).classList.remove('hidden');
}

window.onload = performUnsubscribe;