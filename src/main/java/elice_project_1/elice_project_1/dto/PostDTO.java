package elice_project_1.elice_project_1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostDTO {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotNull
    private String content;
}
