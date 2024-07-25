package com.nextisus.project.client.healthrecord.service;

import com.nextisus.project.client.healthrecord.dto.response.HealthRecordListDto;
import com.nextisus.project.domain.Condition;
import com.nextisus.project.domain.HealthRecord;
import com.nextisus.project.domain.Nft;
import com.nextisus.project.client.healthrecord.dto.response.HealthRecordResponseDto;
import com.nextisus.project.domain.User;
import com.nextisus.project.repository.ConditionRepository;
import com.nextisus.project.repository.HealthRecordRepository;
import com.nextisus.project.repository.NftRepository;
import com.nextisus.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HealthRecordServiceImpl implements HealthRecordService {

    private final NftRepository nftRepository;
    private final UserRepository userRepository;
    private final HealthRecordRepository healthRecordRepository;
    private final ConditionRepository conditionRepository;

    //건강기록 전체 조회
    @Override
    public List<HealthRecordListDto> getHealthRecord(Long userId) {

        //조회한 유저에게 존재하는 건강 기록 리스트 받아오기
        List<HealthRecord> healthRecordsList = healthRecordRepository.findAllByUser_Id(userId);

        List<HealthRecordListDto> healthRecordList = new ArrayList<>();

        for(HealthRecord healthRecord : healthRecordsList) {
            healthRecordList.add(HealthRecordListDto.builder()
                    .healthRecordId(healthRecord.getHealthRecordId())
                    .recordPeriod(healthRecord.getRecordPeriod())
                    .build()
            );
        }
        return healthRecordList;
    }

    //건강기록 생성
    @Override
    public HealthRecord createHealthRecord(Long userId, Long countNft) {

        User byUser = userRepository.getByUser(userId);

        //엔티티 생성
        HealthRecord healthRecord = HealthRecord.builder()
                .user(byUser)
                .build();
        //연관관계 맺기
        healthRecord.setUser(byUser);
        //DB에 저장
        HealthRecord save = healthRecordRepository.save(healthRecord);
        return save;
    }

    //건강기록 세부 조회
    @Override
    @Transactional
    public HealthRecordResponseDto getHealthRecordDetail(Long healthRecordId) {

        //건강기록 가져오기
        HealthRecord healthRecord = healthRecordRepository.getByHealthRecordId(healthRecordId);

        //클라이언트가 조회하고 싶은 건강 기록의 healthRecordId를 이용해서 nft찾기
        List<Nft> nfts = nftRepository.findAllByHealthRecord_HealthRecordId(healthRecordId);
        int nftSize = nfts.size();

        LocalDateTime startTime = nfts.get(0).getCreateAt();
        LocalDateTime endTime = nfts.get(nftSize - 1).getCreateAt();

        // "yyyy.M.dd"형식으로 포맷
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy.M.dd");
        String startDate1 = formatter1.format(startTime);
        String endDate1 = formatter1.format(endTime);
        String recordPeriod = startDate1 + " ~ " + endDate1;

        // "yyyyMMdd" 형식으로 포맷
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
        String startDate2 = formatter2.format(startTime);
        String endDate2 = formatter2.format(endTime);

        // LocalDate 객체 생성
        LocalDate start = LocalDate.parse(startDate2, formatter2);
        LocalDate end = LocalDate.parse(endDate2, formatter2);

        // 두 날짜 사이의 주 수 계산
        long weeksBetween = ChronoUnit.WEEKS.between(start, end);

        String week = weeksBetween + "주";

        //기록기간이랑 주 db에 넣어주기
        healthRecord.setPriod(recordPeriod,week);

        HealthRecordResponseDto response = new HealthRecordResponseDto(
                healthRecord.getHealthRecordId(),
                healthRecord.getRecordPeriod(),
                healthRecord.getWeek()
        );
        return response;
    }
}
