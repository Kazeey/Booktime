export default function generatePassword(): String 
{
    let password: String = "";

    let specials: string = "!@#$%^&*";
    let numbers: string = "0123456789";
    let lowercase: string = "abcdefghijklmnopqrstuvwxyz";
    let uppercase: string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    let all: String = "";
    all = all.concat(specials, numbers, lowercase, uppercase);

    password += specials.charAt(Math.floor(Math.random() * specials.length));
    password += numbers.charAt(Math.floor(Math.random() * numbers.length));
    password += lowercase.charAt(Math.floor(Math.random() * lowercase.length));
    password += uppercase.charAt(Math.floor(Math.random() * uppercase.length));

    // get 3 characters from all
    for (let i = 0; i < 8; i++)
    {
        password += all.charAt(Math.floor(Math.random() * all.length));
    }

    return password;
}