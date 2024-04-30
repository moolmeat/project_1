package elice_project_1.elice_project_1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotNull
    private String content;

    @NotBlank
    private String category;
}
