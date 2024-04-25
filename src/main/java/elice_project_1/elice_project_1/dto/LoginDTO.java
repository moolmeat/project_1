package elice_project_1.elice_project_1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank
    private String memberId;

    @NotBlank
    private String memberPassword;

}
