package elice_project_1.elice_project_1.entity;

import elice_project_1.elice_project_1.dto.MemberDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String memberId;

    @Column
    private String memberPassword;

    @Column(unique = true)
    private String memberEmail;

    @Column(unique = true)
    private String memberNickname;

    @OneToMany
    private List<BoardEntity> boards;

    @Builder
    public MemberEntity(String memberId, String memberPassword, String memberEmail, String memberNickname) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberEmail = memberEmail;
        this.memberNickname = memberNickname;
    }

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
