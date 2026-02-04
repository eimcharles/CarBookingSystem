package com.eimc.car.model.enums;

public enum Model {

    GOLF(Brand.VOLKSWAGEN),
    TIGUAN(Brand.VOLKSWAGEN);

    private final Brand brand;

    Model(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

}
