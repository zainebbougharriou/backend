package com.bezkoder.springjwt.controllers.dto;

public class UpdateQuestionDTO {
    private  Integer id;
    private  String textQ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextQ() {
        return textQ;
    }

    public void setTextQ(String textQ) {
        this.textQ = textQ;
    }
}
