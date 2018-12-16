package br.com.jesus.challenge.sienge.models.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostVO {
    private Integer distancePavedHighway;
    private Integer distanceNotPavedHighway;
    private String vehicleDescription;
    private Integer weightCargoCarried;
    private Float costTransportation;
}
