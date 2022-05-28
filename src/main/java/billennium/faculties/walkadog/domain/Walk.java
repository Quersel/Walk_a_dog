package billennium.faculties.walkadog.domain;

import billennium.faculties.walkadog.domain.enums.WalkStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WALK")
public class Walk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "WALK_SLOTS_ID")
    private WalkSlots walkSlots;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PET_ID")
    private List<Pets> pets;

    @Enumerated
    @Column(name = "WALK_STATUS")
    private WalkStatus walkStatus = WalkStatus.PLANNED;
}
