package br.com.danielamaral.starswarssocialnetwork.dto;

import java.sql.Timestamp;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.danielamaral.starswarssocialnetwork.model.Denunciation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title  = "Denunciation")
public class DenunciationDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private long accuserID;
    private long suspectID;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp reportDate = Timestamp.from(Instant.now());

    public static DenunciationDto parseDenunciation(Denunciation denunciation) {
        return new DenunciationDto(denunciation.getId(),
                denunciation.getAccuserID(),
                denunciation.getSuspectID(),
                denunciation.getReportDate());
    }
}
