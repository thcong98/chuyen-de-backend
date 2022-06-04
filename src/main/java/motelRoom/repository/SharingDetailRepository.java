package motelRoom.repository;

import motelRoom.entity.SharingDetailEntity;
import motelRoom.entity.WaitingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SharingDetailRepository extends JpaRepository<SharingDetailEntity, UUID> {
    @Query("SELECT u from SharingDetailEntity u WHERE u.userId = :userId")
    SharingDetailEntity findByUserId(UUID userId);
//    List<SharingDetailEntity> getAllByRoomId(UUID id);
//    List<SharingDetailEntity> findByRoomIdOrderByDateTimeDesc(UUID id);

}
