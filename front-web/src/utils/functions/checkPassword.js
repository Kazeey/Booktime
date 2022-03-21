/**
* @param {String} password 
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

/**
* @param {String} password 
* @returns {<ul> containing the requirements in <li>}
*
* @description
* Check the password complexity and if the requirements are not met, color the text :
* red : not met
* green : met
*/
const passwordStrength = (password) => {
    let li = [];
    let passwordLength = password.length;
    let passwordUpperCase = password.match(/[A-Z]/g);
    let passwordNumber = password.match(/[0-9]/g);
    let passwordSpecial = password.match(/[^a-zA-Z0-9]/g);

    let color;

    if (passwordLength < 8) 
        color = "red";
    else
        color = "green";

    li.push(<li style={{"color" : color}}>Le mot de passe doit contenir au moins 8 caractères</li>);
    

    if (!passwordUpperCase) 
        color = "red";
    else
        color = "green";

    li.push(<li style={{"color" : color}}>Le mot de passe doit contenir au moins une majuscule</li>);
    

    if (!passwordNumber) 
        color = "red";
    else
        color = "green";

    li.push(<li style={{"color" : color}}>Le mot de passe doit contenir au moins un chiffre</li>);
    

    if (!passwordSpecial) 
        color = "red";
    else
        color = "green";

    li.push(<li style={{"color" : color}}>Le mot de passe doit contenir au moins un caractère spécial</li>);
    

    return (
        <ul style={{ fontSize : '9px', listStyle : 'none', padding : 0}}>
            {li.map((item, index) => (<li key={index}>{item}</li>))}
        </ul>
    )
}

/**
 * @param {String} password 
 * @param {String} passwordConfirm 
 * @returns {<ul> containing the password check <li>}
 * 
 * @description
 * Check if the password and the password confirmation are the same
 * if they are not, color the text : red
 * if they are, color the text : green
 */
const samePassword = (password, passwordConfirm) => {
    let color;

    if (password === passwordConfirm && passwordConfirm !== '')
        color = "green";
    else
        color = "red";

    return (
        <ul style={{ fontSize : '9px', listStyle : 'none', padding : 0}}>
            <li style={{"color" : color}}>Les mots de passe doivent être identiques</li>
        </ul>
    )
}

export { checkPassword, passwordStrength, samePassword};