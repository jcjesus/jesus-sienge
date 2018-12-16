package br.com.jesus.challenge.sienge.services;

import br.com.jesus.challenge.sienge.handler.exceptions.CostParameterException;
import br.com.jesus.challenge.sienge.models.vo.CostVO;
import org.springframework.stereotype.Service;

@Service
public interface CostService {
    CostVO calculateCostTransportation(CostVO costVO, Long idTransport) throws CostParameterException;
}
