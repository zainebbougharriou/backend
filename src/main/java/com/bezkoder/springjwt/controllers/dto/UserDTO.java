package com.bezkoder.springjwt.controllers.dto;
import com.bezkoder.springjwt.models.User;

public class UserDTO {
    private  int userId;
    private  String name;
    private  String date;
    private  Boolean isActive;



    public UserDTO() {
    }

    public static UserDTO map(User user) {
        if(user == null ){
            return null;
        }
        UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getId().intValue());
            userDTO.setDate(user.getFormattedDateHeure());
            userDTO.setName(user.getName());
            userDTO.setIsActive(user.getActive());

        return userDTO ;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
