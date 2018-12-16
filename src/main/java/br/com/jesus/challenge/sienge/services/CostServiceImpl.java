package br.com.jesus.challenge.sienge.services;

import br.com.jesus.challenge.sienge.handler.exceptions.CostParameterException;
import br.com.jesus.challenge.sienge.models.enums.RoadTypeEnum;
import br.com.jesus.challenge.sienge.models.vo.CostVO;
import org.springframework.stereotype.Service;

@Service
public class CostServiceImpl implements CostService {

    @Override
    public CostVO calculateCostTransportation(CostVO costVO) throws CostParameterException {
        validationCostParameters(costVO);
        Float cost = calculate(costVO);
        costVO.setCostTransportation(cost);
        return costVO;
    }

    @Override
    public Float calculate(CostVO costVO) {
        Float value = costVO.getDistanceNotPavedHighway() * RoadTypeEnum.NOT_PAVED_HIGHWAY.getCostPerKilometer() * costVO.getTransport().getMultiplierFactorCost()
                + costVO.getDistancePavedHighway() * RoadTypeEnum.PAVED_HIGHWAY.getCostPerKilometer() * costVO.getTransport().getMultiplierFactorCost();

        if(costVO.getWeightCargoCarried() > 5) {
            Integer exceed = costVO.getWeightCargoCarried() - 5;
            float exceedValue = exceed * 0.02f;
            int kmTotal = costVO.getDistanceNotPavedHighway() + costVO.getDistancePavedHighway();
            value = value + (exceedValue * kmTotal);
        }
        return value;
    }

    @Override
    public void validationCostParameters(CostVO costVO) throws CostParameterException {
        if(costVO.getDistanceNotPavedHighway() == null) {
            throw new CostParameterException("Distância não pavimentada não pode ser nulo.");
        }

        if(costVO.getDistancePavedHighway() == null) {
            throw new CostParameterException("Distância pavimentada não pode ser nulo.");
        }

        if(costVO.getWeightCargoCarried() == null) {
            throw new CostParameterException("Peso da carga não pode ser nulo.");
        }
    }


}
