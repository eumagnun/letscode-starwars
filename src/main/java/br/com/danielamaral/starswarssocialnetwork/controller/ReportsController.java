package br.com.danielamaral.starswarssocialnetwork.controller;

import br.com.danielamaral.starswarssocialnetwork.business.ReportBusiness;
import br.com.danielamaral.starswarssocialnetwork.dto.DefaultResponse;
import br.com.danielamaral.starswarssocialnetwork.model.RebelStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reports")
@Schema(name = "Report")
public class ReportsController {

    @Autowired
    private ReportBusiness reportBusiness;

    @GetMapping(value = "/traitors-and-rebels-percentage", produces = "application/json")
    public DefaultResponse getTraitorsAndRebelsPercentageReport() {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            Map<RebelStatus, Double> result = reportBusiness.generateReportPercentRebelsAndTraitors();
            defaultResponse.setData(result.toString());
            defaultResponse.setStatus(HttpStatus.OK.value());
        }catch (Exception ex){
            defaultResponse.setData(ex.getMessage());
            defaultResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return defaultResponse;
    }

    @GetMapping(value = "/average-resources-by-rebel", produces = "application/json")
    public DefaultResponse getReportAverageResourcesByRebel() {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            Map<String, Double> result = reportBusiness.generateReportAverageResourcesByRebel();
            defaultResponse.setData(result.toString());
            defaultResponse.setStatus(HttpStatus.OK.value());
        }catch (Exception ex){
            defaultResponse.setData(ex.getMessage());
            defaultResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return defaultResponse;
    }

    @GetMapping(value = "/lost-points-related-to-traitors", produces = "application/json")
    public DefaultResponse getReportLostPointsRelatedToTraitors() {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            Map<String, Long> result = reportBusiness.generateReportLostPointsRelatedToTraitors();
            defaultResponse.setData(result.toString());
            defaultResponse.setStatus(HttpStatus.OK.value());
        }catch (Exception ex){
            defaultResponse.setData(ex.getMessage());
            defaultResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return defaultResponse;
    }

}
