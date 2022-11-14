package zb.Team.showticket.show.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class ShowDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //공연id
    @Column(unique = true)
    private String mt20id;
    //공연명
    private String prfnm;
    //공연시작일
    private String prfpdfrom;
    //공연종료일
    private String prfpdto;
    //공연시설명
    private String fcltynm;
    //공연출연진
    private String prfcast;
    //공연제작진
    private String prfcrew;
    //공연 런타임
    private String prfruntime;
    //공연관람연령
    private String prfage;
    //제작사
    private String entrpsnm;
    //티켓가격
    private String pcseguidance;
    //공연 포스터
    private String poster;
    //공연장르
    private String genrenm;
    //공연상태
    private String prfstate;
    //오픈런
    private String openrun;
    //공연시설id
    private String mt10id;
    //공연시간
    private String dtguidance;

}
