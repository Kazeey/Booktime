/*
* @param password : string
* @returns {boolean}
*
* @description
* Check the password complexity and if it contains :
* - at least 8 characters
* - at least 1 uppercase letter
* - at least 1 lowercase letter
* - at least 1 number
* - at least 1 special character
*/
const checkPassword = (password) => {
    const re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/;
    if (re.test(String(password))) 
        return true;
    else
        return false;
}

const passwordStrength = (password) => {
    let li = [];
    let passwordLength = password.length;
    let passwordUpperCase = password.match(/[A-Z]/g);
    let passwordNumber = password.match(/[0-9]/g);
    let passwordSpecial = password.match(/[^a-zA-Z0-9]/g);

    if (passwordLength < 8) {
        li.push(<li>Le mot de passe doit contenir au moins 8 caractères</li>);
    }

    if (!passwordUpperCase) {
        li.push(<li>Le mot de passe doit contenir au moins une majuscule</li>);
    }

    if (!passwordNumber) {
        li.push(<li>Le mot de passe doit contenir au moins un chiffre</li>);
    }

    if (!passwordSpecial) {
        li.push(<li>Le mot de passe doit contenir au moins un caractère spécial</li>);
    }

    return (
        <ul style={{ fontSize : '9px', listStyle : 'none', padding : 0}}>
            {li.map((item, index) => (<li key={index}>{item}</li>))}
        </ul>
    )
}

export { checkPassword, passwordStrength };