package sg.edu.rp.webservices.firebasetodoapp;

import java.io.Serializable;

public class Message implements Serializable {
    private String title;
    private String date;
    private int numOfDays;
    private String completed;

    public Message(){

    }

    public Message(String title, String date, int numOfDays, String completed) {
        this.title = title;
        this.date = date;
        this.numOfDays = numOfDays;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", numOfDays=" + numOfDays +
                ", completed=" + completed +
                '}';
    }
}
