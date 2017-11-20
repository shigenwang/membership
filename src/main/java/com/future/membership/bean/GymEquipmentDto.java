package com.future.membership.bean;

public class GymEquipmentDto {
    private Integer id;

    private Integer gym_id;

    private Integer equipment_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGym_id() {
        return gym_id;
    }

    public void setGym_id(Integer gym_id) {
        this.gym_id = gym_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }
}