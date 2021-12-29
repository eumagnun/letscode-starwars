package br.com.danielamaral.starswarssocialnetwork.controller;

import br.com.danielamaral.starswarssocialnetwork.business.ItemExchangeBusiness;
import br.com.danielamaral.starswarssocialnetwork.business.ReportBetrayalBusiness;
import br.com.danielamaral.starswarssocialnetwork.dto.DefaultResponse;
import br.com.danielamaral.starswarssocialnetwork.dto.DenunciationDto;
import br.com.danielamaral.starswarssocialnetwork.dto.LocationDto;
import br.com.danielamaral.starswarssocialnetwork.dto.RebelDto;
import br.com.danielamaral.starswarssocialnetwork.exception.IncompatiblePontuationException;
import br.com.danielamaral.starswarssocialnetwork.exception.InventoryBlockedException;
import br.com.danielamaral.starswarssocialnetwork.exception.MultipleDenunciationSameSuspectException;
import br.com.danielamaral.starswarssocialnetwork.exception.ParticipantNotFoundException;
import br.com.danielamaral.starswarssocialnetwork.model.*;
import br.com.danielamaral.starswarssocialnetwork.repository.RebelRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rebels")
@Schema(name = "Rebel")
public class RebelController {

    @Autowired
    private RebelRepository rebelRepository;

    @Autowired
    private ReportBetrayalBusiness reportBetrayalBusiness;

    @Autowired
    private ItemExchangeBusiness itemExchangeBusiness;

    @PostMapping(value = "/", consumes = "application/json")
    public DefaultResponse createRebel(@RequestBody RebelDto rebelDto) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            Rebel rebel = Rebel.parseRebel(rebelDto);
            rebelRepository.save(rebel);
            defaultResponse.setStatus(HttpStatus.OK.value());
            defaultResponse.setData("Cadastro realizado com sucesso");
        } catch (Exception ex) {
            defaultResponse.setData(ex.getMessage());
            defaultResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return defaultResponse;
    }

    @PatchMapping(value = "/{id}/location", consumes = "application/json")
    public DefaultResponse updateRebelLocation(@PathVariable long id, @RequestBody LocationDto locationDto) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            Optional<Rebel> optional = rebelRepository.findById(id);
            if (optional.isPresent()) {
                Rebel rebelUpdated = optional.get();
                rebelUpdated.setLocation(Location.parseLocation(locationDto));
                rebelRepository.save(rebelUpdated);
            }
            defaultResponse.setStatus(HttpStatus.OK.value());
            defaultResponse.setData("Localidade atualizada com sucesso");
        } catch (Exception ex) {
            defaultResponse.setData(ex.getMessage());
            defaultResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return defaultResponse;
    }

    @GetMapping(value = "/", produces = "application/json")
    public DefaultResponse listRebels() {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            List<RebelDto> rebelsDto = new ArrayList<>();
            rebelRepository.findAll().forEach(rebel -> rebelsDto.add(RebelDto.parseDto(rebel)));
            defaultResponse.setStatus(HttpStatus.OK.value());
            defaultResponse.setData(rebelsDto);

        } catch (Exception ex) {
            defaultResponse.setData(ex.getMessage());
            defaultResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return defaultResponse;
    }


    @PostMapping(value = "/denunciation", consumes = "application/json")
    public DefaultResponse denunciation(@RequestBody DenunciationDto denunciationDto) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            reportBetrayalBusiness.denounce(Denunciation.parseDenunciation(denunciationDto));
            defaultResponse.setStatus(HttpStatus.OK.value());
            defaultResponse.setData("Denúncia registrada com sucesso");
        } catch (MultipleDenunciationSameSuspectException ex) {
            defaultResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            defaultResponse.setData(ex.getMessage());
        }catch (Exception ex){
            defaultResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            defaultResponse.setData(ex.getMessage());
        }
        return defaultResponse;
    }

    @PostMapping(value = "/exchange", consumes = "application/json")
    public DefaultResponse exchange(@RequestBody Negotiation negotiation) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
	        itemExchangeBusiness.exchange(negotiation);
	        defaultResponse.setStatus(HttpStatus.OK.value());
	        defaultResponse.setData("Troca realizada com sucesso");
        } catch (IncompatiblePontuationException | InventoryBlockedException | ParticipantNotFoundException ex) {
            defaultResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            defaultResponse.setData(ex.getMessage());
        } catch (Exception ex){
            defaultResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            defaultResponse.setData(ex.getMessage());
        }
        return defaultResponse;
    }


}
