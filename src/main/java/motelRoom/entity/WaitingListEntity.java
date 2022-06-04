package motelRoom.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import motelRoom.entity.addressEntity.WardEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "waiting_list")
public class WaitingListEntity {
    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(40)", name = "waiting_list_id")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "user_id")
    @Type(type = "uuid-char")
    private UUID userId;

    @Column(name = "room_id")
    @Type(type = "uuid-char")
    private UUID roomId;

    @Column(name = "time")
    private LocalDateTime dateTime;

    /**relationship many waitingList one room**/
    @ManyToOne
    @JoinColumn(name = "room_id",insertable = false,updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private RoomEntity roomEntity;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private UserEntity userEntity;
}
