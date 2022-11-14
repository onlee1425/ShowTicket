package zb.Team.showticket.show.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowInput {
    private String showCode;

    public String getShowCode() {
        return showCode != null ? showCode:"";
    }
}
