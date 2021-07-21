package phishy.domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "training_usergroup")
public class TrainingUsergroupEntity {

    public TrainingUsergroupEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tugId;

    @Column(name = "tug_nm")
    private String tugNm;

    @Builder
    public TrainingUsergroupEntity(Long tugId, String tugNm) {
        this.tugId = tugId;
        this.tugNm = tugNm;
    }

}
