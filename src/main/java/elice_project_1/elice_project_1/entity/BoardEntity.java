package elice_project_1.elice_project_1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private MemberEntity memberEntity;

    @Column(name = "register_date")
    private String registerDate;

    @Builder
    public BoardEntity(String title, String content, MemberEntity memberEntity, String registerDate) {
        this.title = title;
        this.content = content;
        this.memberEntity = memberEntity;
        this.registerDate = registerDate;
    }

    public static BoardEntity createBoard(String title, String content, MemberEntity memberEntity) {
        return BoardEntity.builder()
            .title(title).content(content).memberEntity(memberEntity)
            .registerDate(String.valueOf(LocalDateTime.now()))
            .build();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
