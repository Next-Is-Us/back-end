package com.nextisus.project.nft.service;

import com.nextisus.project.repository.ConditionRepository;
import com.nextisus.project.domain.Condition;
import com.nextisus.project.domain.Nft;
import com.nextisus.project.domain.User;
import com.nextisus.project.healthrecord.service.HealthRecordServiceImpl;
import com.nextisus.project.nft.dto.response.NftResponseDto;
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
public class NftServiceImpl implements NftService {

    private final ConditionRepository conditionRepository;
    private final NftRepository nftRepository;
    private final UserRepository userRepository;
    private final HealthRecordServiceImpl healthRecordServiceImpl;

    @Override
    public Long getNfts(Long userId) {
        List<Condition> conditions = conditionRepository.getAllById(userId);
        int conditionSize = conditions.size();
        if (conditionSize % 5 == 0 && conditionSize != 0) { //기록한 상태가 5의 배수면 nft 조각 1개가 생성된 것
            return (long) conditionSize;
        }
        return null;
    }

    @Override
    public void createNft(Long userId) {

        List<Condition> conditions = conditionRepository.getAllById(userId);
        int conditionSize = conditions.size();
        LocalDateTime startTime = conditions.get(0).getCreateAt();
        LocalDateTime endTime = conditions.get(conditionSize - 1).getCreateAt();

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

        // 유저 찾기
        User user = userRepository.getByUser(userId);

        // 엔티티 생성
        Nft nft = Nft.builder()
                .user(user)
                .recordPeriod(recordPeriod)
                .week(week)
                .build();

        // 연관관계 맺기
        nft.createNft(user);

        // DB에 저장
        Nft save = nftRepository.save(nft);

        //발급 받은 nft의 갯수가 6의 배수 (6 , 12, 18 ... )
        if(save.getNftId() % 6 == 0 && save.getNftId() != 0) {
            healthRecordServiceImpl.createHealthRecord(userId);
        }
    }

    //nft 상세 조회
    @Override
    public NftResponseDto getDetailNft(Long nftId) {
        Nft nft = nftRepository.getByNft(nftId);
        NftResponseDto nftResponseDto = new NftResponseDto(
                nft.getNftId(),
                nft.getRecordPeriod(),
                nft.getWeek()
        );
        return nftResponseDto;
    }
}
