export const setMessage = (message, nbEssais = null) => {
    let messageZone = document.getElementById('messageZone');

    if (nbEssais)
        message += nbEssais;
    
    messageZone.innerHTML = message;
    messageZone.style.display = 'block';

    if (!message)
        messageZone.style.display = 'none';
}