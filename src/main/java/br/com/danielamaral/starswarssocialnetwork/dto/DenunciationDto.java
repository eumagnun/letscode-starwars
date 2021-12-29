package br.com.danielamaral.starswarssocialnetwork.dto;

import br.com.danielamaral.starswarssocialnetwork.model.Denunciation;
import br.com.danielamaral.starswarssocialnetwork.model.Rebel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
