package elice_project_1.elice_project_1.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

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
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime registerDate;

    private String category;

    @Column(name = "view", columnDefinition = "integer default 0" ,nullable = false)
    private int view;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;

    @Builder
    public BoardEntity(String title, String content, MemberEntity memberEntity, LocalDateTime registerDate, String category) {
        this.title = title;
        this.content = content;
        this.memberEntity = memberEntity;
        this.registerDate = registerDate;
        this.category = category;
    }

    public static BoardEntity createBoard(String title, String content, String category, MemberEntity memberEntity) {
        return BoardEntity.builder()
            .title(title).content(content).memberEntity(memberEntity)
            .registerDate(LocalDateTime.now())
            .category(category)
            .build();
    }

    public void update(String title, String content,  String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

}
