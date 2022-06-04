package motelRoom.service.sharingDetailService;

import motelRoom.dto.roomSharing.RoomSharingDetailDto;
import motelRoom.dto.sharingDetail.SharingDetailCreateDto;
import motelRoom.dto.sharingDetail.SharingDetailDetailDto;
import motelRoom.dto.waitingList.WaitingListDetailDto;
import motelRoom.entity.SharingDetailEntity;
import motelRoom.mapper.SharingDetailMapper;
import motelRoom.repository.SharingDetailRepository;
import motelRoom.service.exceptionService.NotAcceptable;
import motelRoom.service.exceptionService.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class SharingDetailServiceImpl implements SharingDetailService{
    @Autowired
    private final SharingDetailRepository sharingDetailRepository;

    @Autowired
    private final SharingDetailMapper sharingDetailMapper;
    public SharingDetailServiceImpl(SharingDetailRepository sharingDetailRepository,
                                    SharingDetailMapper sharingDetailMapper) {
        this.sharingDetailRepository = sharingDetailRepository;
        this.sharingDetailMapper = sharingDetailMapper;
    }

    /**
     * Create SharingDetail
     **/
    @Override
    public SharingDetailDetailDto createSharingDetail(SharingDetailCreateDto sharingDetailCreateDto) {
        UUID user = sharingDetailCreateDto.getUserId();
        UUID sharing = sharingDetailCreateDto.getSharingId();
        if(user == null || sharing == null){
            throw new NotAcceptable("bạn cần nhập đủ thông tin" );
        }
        else {
            SharingDetailEntity userDuplicate = sharingDetailRepository.findByUserId(sharingDetailCreateDto.getUserId());
            if (userDuplicate != null) {
                throw new NotAcceptable(" Id user đã tồn tại");
            }
            else {
                SharingDetailEntity sharingDetailEntity =
                        sharingDetailMapper.fromSharingDetailCreateDto(sharingDetailCreateDto);
                SharingDetailEntity sharingDetailEntityCreate = sharingDetailRepository.save(sharingDetailEntity);
                SharingDetailDetailDto sharingDetailDetailDto = null;
                if(sharingDetailEntityCreate != null){
                    sharingDetailDetailDto = sharingDetailMapper.fromEntityToDto(sharingDetailEntityCreate);
                }
                return sharingDetailDetailDto;
            }
        }
    }

    /**
     * Show SharingDetail by id
     **/
    @Override
    public SharingDetailDetailDto findById(UUID sharingDetailId) {
        SharingDetailDetailDto sharingDetailDetailDto =
                sharingDetailMapper.fromEntityToDto(sharingDetailRepository.getById(sharingDetailId));
        return sharingDetailDetailDto;
    }

    /**
     * Show list SharingDetail
     **/
    @Override
    public List<SharingDetailDetailDto> findAll() {
        return sharingDetailMapper.fromListEntitiesToDtos(sharingDetailRepository.findAll());
    }
//    @Override
//    public List<SharingDetailDetailDto> getAllByRoomId(UUID id) {
//            List<SharingDetailDetailDto> list = sharingDetailMapper.fromListEntitiesToDtos(sharingDetailRepository.findByRoomIdOrderByDateTimeDesc(id));
//            if(list.isEmpty()){
//                throw new NotFoundException("Not find");
//            }
//            return list;
//
//    }
    /**
     * Update SharingDetail by id
     **/
    @Override
    public SharingDetailDetailDto updateSharingDetail(UUID sharingDetailId, SharingDetailDetailDto sharingDetailDetailDto) {
        UUID user = sharingDetailDetailDto.getUserId();
        UUID sharing = sharingDetailDetailDto.getSharingId();
        if(user == null || sharing == null){
            throw new NotAcceptable("Please enter information");
        }
        else {
            SharingDetailEntity sharingDetailEntity = sharingDetailMapper.fromSharingDetailUpdateDto(sharingDetailDetailDto);
            sharingDetailEntity.setSharingDetailId(sharingDetailId);
            if(sharingDetailEntity == null){
                return null;
            }
            sharingDetailRepository.save(sharingDetailEntity);
            SharingDetailDetailDto sharingDetailDetailDtoUpdate = sharingDetailMapper.fromEntityToDto(sharingDetailEntity);
            return sharingDetailDetailDtoUpdate;
        }
    }

    /**
     * Delete SharingDetail by id
     **/
    @Override
    public String deleteById(UUID id) {
        sharingDetailRepository.deleteById(id);
        return "Deleted";
    }

}