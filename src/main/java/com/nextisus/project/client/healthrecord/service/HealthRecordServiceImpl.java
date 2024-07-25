package com.nextisus.project.client.healthrecord.service;

import com.nextisus.project.domain.Condition;
import com.nextisus.project.domain.HealthRecord;
import com.nextisus.project.domain.Nft;
import com.nextisus.project.client.healthrecord.dto.response.HealthRecordResponseDto;
import com.nextisus.project.repository.ConditionRepository;
import com.nextisus.project.repository.HealthRecordRepository;
import com.nextisus.project.repository.NftRepository;
import com.nextisus.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HealthRecordServiceImpl implements HealthRecordService {

    private final NftRepository nftRepository;
    private final UserRepository userRepository;
    private final HealthRecordRepository healthRecordRepository;
    private final ConditionRepository conditionRepository;

    //건강기록 조회
    @Override
    public HealthRecordResponseDto getHealthRecord(Long userId) {

        List<Nft> nfts = nftRepository.getAllByUserId(userId);
        int nftSize = nfts.size();
        if(nftSize != 0 && nftSize % 6 == 0) {
            //로직 추가 해야함
        }
        return null;
    }

    //건강기록 생성
    @Override
    public HealthRecord createHealthRecord(Long userId, Long countNft) {
        //엔티티 생성
        HealthRecord healthRecord = HealthRecord.builder().build();

        //DB에 저장
        HealthRecord save = healthRecordRepository.save(healthRecord);
        return save;
    }

    //건강기록 세부 조회
    @Override
    public HealthRecordResponseDto getHealthRecordDetail(Long userId, Long healthRecordId) {

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
        String recordPeriod = startDate1 + "~" + endDate1;

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

        HealthRecordResponseDto response = new HealthRecordResponseDto(
                (long) healthRecord.getHealthRecordId(),
                recordPeriod,
                week
        );
        return response;
    }
}
