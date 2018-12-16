package br.com.jesus.challenge.sienge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transport {

    @Id
    @GeneratedValue
    private Long id;
    private String vehicleDescription;
    private Float multiplierFactorCost;

}
