package br.com.jesus.challenge.sienge.controllers;

import br.com.jesus.challenge.sienge.handler.exceptions.CostParameterException;
import br.com.jesus.challenge.sienge.models.vo.CostVO;
import br.com.jesus.challenge.sienge.services.CostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CostController {

    private Logger logger = LoggerFactory.getLogger(CostController.class);

    @Autowired
    private CostService costService;

    @GetMapping("/costs")
    public ResponseEntity<CostVO> calculateCostOfTransportation(
            @RequestParam Integer distancePavedHighway,
            @RequestParam Integer distanceNotPavedHighway,
            @RequestParam Long idTransport,
            @RequestParam Integer weightCargoCarried) {

        CostVO costVO = CostVO.builder()
                .distanceNotPavedHighway(distanceNotPavedHighway)
                .distancePavedHighway(distancePavedHighway)
                .weightCargoCarried(weightCargoCarried)
                .build();
        CostVO cost = null;
        try {
            cost = costService.calculateCostTransportation(costVO, idTransport);
        } catch (CostParameterException e) {
            logger.error("Parametro(s) inválido(s).", e);
        }

        return new ResponseEntity<CostVO>(cost, HttpStatus.OK);

    }

}
