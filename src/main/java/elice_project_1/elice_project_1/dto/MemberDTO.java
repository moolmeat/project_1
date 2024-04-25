package elice_project_1.elice_project_1.dto;

import elice_project_1.elice_project_1.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

    private Long id;
    private String memberId;
    private String memberPassword;
    private String memberEmail;
    private String memberNickname;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberNickname(memberEntity.getMemberNickname());

        return memberDTO;
    }
}
