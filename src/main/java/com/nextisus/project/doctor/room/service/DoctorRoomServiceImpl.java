package com.nextisus.project.doctor.room.service;

import com.nextisus.project.doctor.room.dto.CreateRoomRequestDto;
import com.nextisus.project.doctor.room.dto.CreateRoomResponseDto;
import com.nextisus.project.domain.Room;
import com.nextisus.project.domain.User;
import com.nextisus.project.domain.UserRoom;
import com.nextisus.project.exception.room.RoomNameDuplicatedException;
import com.nextisus.project.image.service.S3UploadService;
import com.nextisus.project.repository.RoomRepository;
import com.nextisus.project.repository.UserRepository;
import com.nextisus.project.repository.UserRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorRoomServiceImpl implements DoctorRoomService {

    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;
    private final UserRepository userRepository;
    private final S3UploadService s3UploadService;

    @Transactional
    @Override
    public CreateRoomResponseDto createRoom(CreateRoomRequestDto dto, Long userId) {
        try{
            // 사용자 존재하는지 확인
            User user = userRepository.getByUser(userId);

            // 방 이름 중복 확인
            roomRepository.findByName(dto.getName())
                    .ifPresent(room -> {
                        throw new RoomNameDuplicatedException();
                    });

            //이미지 주소 변환
            String thumbnailPath = s3UploadService.upload(dto.getThumbnail(),"room-thumbnail");

            // 생성 및 저장
            Room room = roomRepository.save(Room.toEntity(dto,thumbnailPath));

            // 사용자와 룸 간의 연관관계 설정
            UserRoom userRoom = new UserRoom(user, room);
            user.addUserRoom(userRoom);
            room.addUserRoom(userRoom);

            // UserRoom 저장
            userRoomRepository.save(userRoom);

            return CreateRoomResponseDto.builder().roomId(room.getId()).build();
        }
        catch (DataAccessException dae) {
            return null;
        } catch (Exception e) {
            return null;
        }

    }
}
