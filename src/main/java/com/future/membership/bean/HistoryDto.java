package com.future.membership.bean;

import java.util.Date;

public class HistoryDto {
    private Integer id;

    private Integer user_id;

    private Integer equipment_id;

    private Integer eqt_id;

    private Date exerciseTime;

    private Integer valid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public Integer getEqt_id() {
        return eqt_id;
    }

    public void setEqt_id(Integer eqt_id) {
        this.eqt_id = eqt_id;
    }

    public Date getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(Date exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}