package br.com.danielamaral.starswarssocialnetwork.model;

import br.com.danielamaral.starswarssocialnetwork.dto.DenunciationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(name = "UniqueAccuserAndReporter", columnNames={"accuserID", "suspectID"})})
public class Denunciation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long accuserID;
    private long suspectID;
    private Timestamp reportDate;

    public static Denunciation parseDenunciation(DenunciationDto denunciationDto){
        return new Denunciation(denunciationDto.getId(),
                denunciationDto.getAccuserID(),
                denunciationDto.getSuspectID(),
                denunciationDto.getReportDate());
    }
}
