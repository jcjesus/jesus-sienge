package br.com.jesus.challenge.sienge.services;

import br.com.jesus.challenge.sienge.handler.exceptions.CostParameterException;
import br.com.jesus.challenge.sienge.models.vo.CostVO;

public interface CostService {
    CostVO calculateCostTransportation(CostVO costVO) throws CostParameterException;
    Float calculate(CostVO costVO);
    void validationCostParameters(CostVO costVO) throws CostParameterException;
}
