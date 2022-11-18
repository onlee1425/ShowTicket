package zb.Team.showticket.board.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.springframework.format.annotation.DateTimeFormat;
import zb.Team.showticket.board.domain.model.ShowPostDto;
import zb.Team.showticket.user.domain.entity.BaseEntity;
import zb.Team.showticket.user.domain.entity.Users;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AuditOverride(forClass = BaseEntity.class)
public class ShowPost extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long show_Id;
    @Column(nullable = false, length = 100)
    private String show_title; //공연제목
    @Column(length = 500)
    private String show_content; //공연내용
    private String show_place; //공연장소
    private String show_host; //공연 주최
    private String show_cast; //공연 출연진
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate show_date; //공연일
    private String show_time; //공연 시간
    private Integer total_seat; //공연 총좌석
    private Integer remaining_seat; //공연 잔여좌석
    private Integer show_price; //공연가격

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id")
    private Board board;
    private String board_name;
    private String board_type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users user;
    private String user_type;
    private String user_name;


    public ShowPost(Users users,Board board,ShowPostDto from){
        this.user = users;
        this.user_name = users.getName();
        this.user_type = users.getType();
        this.board = board;
        this.board_name = board.getName();
        this.board_type = board.getBoardType();
        this.show_title = from.getTitle();
        this.show_content = from.getContent();
        this.show_place = from.getPlace();
        this.show_host = from.getHost();
        this.show_cast = from.getHost();
        this.show_date = LocalDate.parse(from.getDate());
        this.total_seat = from.getTotal_seat();
        this.remaining_seat = from.getTotal_seat();
        this.show_price = from.getPrice();
        this.show_time = from.getTime();

    }

    // 수정시 데이터 처리
    public ShowPost setUpdate(ShowPostDto from) {
        this.show_title = from.getTitle();
        this.show_content = from.getContent();
        this.show_place = from.getPlace();
        this.show_host = from.getHost();
        this.show_cast = from.getHost();
        this.show_date = LocalDate.parse(from.getDate());
        this.total_seat = from.getTotal_seat();
        this.remaining_seat = from.getTotal_seat();
        this.show_price = from.getPrice();
        this.show_time = from.getTime();
        return this;
    }

}
