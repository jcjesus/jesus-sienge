package br.com.jesus.challenge.sienge.models.enums;

public enum RoadTypeEnum {
    PAVED_HIGHWAY(0.54f),
    NOT_PAVED_HIGHWAY(0.62f);


    private final float costPerKilometer;

    RoadTypeEnum(float costPerKilometer) {
        this.costPerKilometer = costPerKilometer;
    }

    public float getCostPerKilometer() {
        return costPerKilometer;
    }

}
