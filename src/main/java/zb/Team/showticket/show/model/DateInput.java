package zb.Team.showticket.show.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateInput {

    private Integer stdate;
    private Integer eddate;
    private Integer cpage;

    public Integer StartDate() {
        return stdate;
    }
    public Integer EndDate() {
        return eddate;
    }
    public Integer cpageCount(){
        return cpage;
    }
}
