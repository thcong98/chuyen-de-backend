package motelRoom.service.sharingDetailService;

import motelRoom.dto.room.RoomDetailDto;
import motelRoom.dto.roomSharing.RoomSharingDetailDto;
import motelRoom.dto.sharingDetail.SharingDetailCreateDto;
import motelRoom.dto.sharingDetail.SharingDetailDetailDto;
import java.util.List;
import java.util.UUID;

public interface SharingDetailService {
    SharingDetailDetailDto createSharingDetail(SharingDetailCreateDto sharingDetailCreateDto);
    SharingDetailDetailDto findById(UUID sharingDetailId);
    List<SharingDetailDetailDto> findAll();
   // public List<SharingDetailDetailDto> getAllByRoomId(UUID id);
    SharingDetailDetailDto updateSharingDetail(UUID sharingDetailId, SharingDetailDetailDto sharingDetailDetailDto);
    String deleteById(UUID id);
}