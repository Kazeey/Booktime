import nodemailer from 'nodemailer';
import { Options } from 'nodemailer/lib/dkim';

let mailAdress = "booktime.projet@gmail.com";

const transporter = nodemailer.createTransport({
    host: 'smtp.gmail.com',
    port: 587,
    auth: {
        user: mailAdress,
        pass: "Booktime1!"
    }
});

const sendMail = (to: string, subject: string, text: string) => {
    const mailOptions = {
        from: mailAdress,
        to: to,
        subject: subject,
        text: text
    };
    transporter.sendMail(mailOptions, (error, info) => {
        if (error) 
            throw error;
        console.log('Message sent: %s', info.messageId);
    });
};

export default sendMail;