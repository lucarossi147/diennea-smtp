package com.luca.smtp.diennea_smtp.util;

public class Mail {

    private String from;
    private String to;
    private String subject;
    private String body;

    public Mail(){}

    public Mail(String from, String to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return new StringBuffer(" From : ").append(this.from)
                .append(" To : ").append(this.to)
                .append(" Subject : ").append(this.subject).append(" Body : ")
                .append(this.body).toString();
    }
}
