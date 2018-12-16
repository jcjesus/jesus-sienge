package br.com.jesus.challenge.sienge.models.vo;

import br.com.jesus.challenge.sienge.models.Transport;
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
    private Transport transport;
    private Integer weightCargoCarried;
    private Float costTransportation;

}
