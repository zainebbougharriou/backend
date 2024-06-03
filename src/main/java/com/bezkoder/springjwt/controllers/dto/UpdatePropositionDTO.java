package com.bezkoder.springjwt.controllers.dto;

public class UpdatePropositionDTO {
    private  Integer id;
    private  String textProposition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextProposition() {
        return textProposition;
    }

    public void setTextProposition(String textProposition) {
        this.textProposition = textProposition;
    }
}
