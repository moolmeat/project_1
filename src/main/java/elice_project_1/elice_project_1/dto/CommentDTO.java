package elice_project_1.elice_project_1.dto;

import elice_project_1.elice_project_1.entity.BoardEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {


    private Long id;

    @NotBlank
    private String content;

    private BoardEntity boardEntity;

}
