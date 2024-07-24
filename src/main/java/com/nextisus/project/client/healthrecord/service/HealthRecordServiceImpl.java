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
    public HealthRecord createHealthRecord(Long userId, Long nftId) {
        //해당 유저가 발급 받은 nft들
        List<Nft> nfts = nftRepository.getAllByUserId(userId);
        nfts.forEach(nft->{

        });
        //방금 발급 받은 nftId(6,12,18...)로 찾은 condition
        //List<Condition> endCondition = conditionRepository.findAllByNft_Id(nftId);
        //방금 발급 받은 nftId-5(1,7,13...)로 찾은 condition
        //List<Condition> startCondition = conditionRepository.findAllByNft_Id(nftId-5);

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

        //엔티티 생성
        HealthRecord healthRecord = HealthRecord.builder()
                .recordPeriod(recordPeriod)
                .week(week)
                .build();

        //DB에 저장
        HealthRecord save = healthRecordRepository.save(healthRecord);
        return save;
    }
}
