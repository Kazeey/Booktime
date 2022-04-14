/**
 * @param {String} name 
 * @returns boolean
 * 
 * @description
 * Check if the name is valid
 * The name if valid only if it contains :
 * - letters
 * - caracters : - _ . '
 * - white spaces
 * return true if the name is valid
 * return false if the name is not valid
 */
const checkNameFormat = (name) => {
    if (name === '') return false;
    if (!/^[a-zA-ZàâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ'\s\-\_\.]+$/i.test(name)) return false;
    return true;
}

export default checkNameFormat;