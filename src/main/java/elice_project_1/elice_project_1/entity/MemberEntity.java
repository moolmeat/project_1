package elice_project_1.elice_project_1.entity;

import elice_project_1.elice_project_1.dto.MemberDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberId;

    @Column
    private String memberPassword;

    @Column(unique = true)
    private String memberEmail;

    @Column(unique = true)
    private String memberNickname;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberNickname(memberDTO.getMemberNickname());

        return memberEntity;
    }
}
