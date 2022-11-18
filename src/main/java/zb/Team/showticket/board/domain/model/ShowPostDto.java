package zb.Team.showticket.board.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowPostDto {
    @ApiModelProperty(value = "제목", required = true)
    private String title;
    @ApiModelProperty(value = "내용", required = true)
    private String content;
    @ApiModelProperty(value = "장소", required = true)
    private String place;
    @ApiModelProperty(value = "주최", required = true)
    private String host;
    @ApiModelProperty(value = "출연", required = true)
    private String cast;
    @ApiModelProperty(value = "공연 날짜", required = true)
    private String date;
    @ApiModelProperty(value = "공연 시간", required = true)
    private String time;
    @ApiModelProperty(value = "공연 좌석", required = true)
    private Integer total_seat;
    @ApiModelProperty(value = "공연 금액", required = true)
    private Integer price;


}
