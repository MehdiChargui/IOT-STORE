package coreapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReviewRequestDTO {

    private String id;
    private String customer;
    private String article;
    private int note;
    private String comment;
    private Boolean active;
}