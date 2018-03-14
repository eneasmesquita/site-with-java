/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author eneas
 */
@ManagedBean
@RequestScoped
public class FaleConoscoBean {

    private String nome = "";
    private String email = "";
    private String contato = "";
    private String mensagem = "";

    /**
     * Creates a new instance of FaleConoscoBean
     */
    public FaleConoscoBean() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void enviarEmail(ActionEvent e) {

        Properties props = new Properties();

        /**
         * Parâmetros de conexão com servidor Hotmail*
         */
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.socketFactory.port", "25");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
        
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props,new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication("sigiloso@aigiloso.com.br", "senhasigilosa");
            }
        });

        try {
            javax.mail.Message message = new MimeMessage(session);
            /**
             * Remetente da mensagem*
             */
            //message.setFrom(new InternetAddress("email@hotmail.com"));
            message.setFrom(new InternetAddress(email));
            /**
             * Destinatário(s) - pode ser um ou vários*
             */
            message.setRecipients(javax.mail.Message.RecipientType.TO,
                    InternetAddress.parse("sigiloso@aigiloso.com.br"));

            message.setRecipients(javax.mail.Message.RecipientType.CC,
                    InternetAddress.parse("sigiloso@aigiloso.com.br"));

            /**
             * Assunto do Email*
             */
            message.setSubject(nome + " - " + contato);

            /**
             * Mensagem que vai no corpo do email*
             */
            message.setText(mensagem + "\r\n\r\n\r\nAtt, " + email + "\r\n\r\nResponder clicando no email acima, enviando cópia"
                    + " para sigiloso@aigiloso.com.br");

            /**
             * Método para enviar a mensagem criada
             */
            javax.mail.Transport.send(message);

            FacesMessage msg1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Email enviado com sucesso!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg1);

            FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_INFO, "\r\n\r\nO Mais breve possível responderemos a sua mensagem.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg2);

            this.contato = "";
            this.nome = "";
            this.mensagem = "";
            this.email = "";

        } catch (MessagingException me) {
            me.getMessage();
            me.printStackTrace();
            FacesMessage msg1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de envio de Email!", "Erro");
            FacesContext.getCurrentInstance().addMessage(null, msg1);

        }
    }
}
