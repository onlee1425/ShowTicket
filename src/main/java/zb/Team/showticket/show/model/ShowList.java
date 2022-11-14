package zb.Team.showticket.show.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ShowList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //공연Id
    @Column(unique = true)
    private String mt20id;

    //공연명
    private String prfnm;
    //공연 장르명
    private String genrenm;
    //공연상태
    private String prfstate;
    //공연시작일
    private String prfpdfrom;
    //공연종료일
    private String prfpdto;
    //공연포스터
    private String poster;
    //공연시설명
    private String fcltynm;
    //오픈런
    private String openrun;

    @Override
    public String toString() {
        return mt20id + " "
                + prfnm + " "
                + genrenm + " "
                + prfstate + " "
                + prfpdfrom + " "
                + prfpdto + " "
                + poster + " "
                + fcltynm + " "
                + openrun;
    }
}
