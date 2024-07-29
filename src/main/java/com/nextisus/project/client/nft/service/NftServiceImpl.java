package com.nextisus.project.client.nft.service;

import com.nextisus.project.domain.HealthRecord;
import com.nextisus.project.repository.ConditionRepository;
import com.nextisus.project.domain.Condition;
import com.nextisus.project.domain.Nft;
import com.nextisus.project.domain.User;
import com.nextisus.project.client.healthrecord.service.HealthRecordServiceImpl;
import com.nextisus.project.client.nft.dto.response.NftResponseDto;
import com.nextisus.project.repository.NftRepository;
import com.nextisus.project.repository.UserRepository;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
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
        List<Condition> conditions = conditionRepository.findAllByUser_Id(userId);
        List<Nft> nfts = nftRepository.findAllByUser_Id(userId);
        Long[] pieceOfNft = { 5L, 10L, 15L, 20L, 25L, 0L };
        int conditionSize = conditions.size(); //유저가 기록한 오늘의 상태 수
        int nftSize = nfts.size(); //유저가 발급 받은 nft 개수
        int index = conditionSize - (nftSize * 30);
        for(Long piece : pieceOfNft) {
            if(piece == index && conditionSize != 0){
                return piece;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Nft createNft(Long userId) {

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

        //해당 유저가 발급 받은 nft의 수
        Long countNft = nftRepository.countByUser_Id(userId);

        //발급 받은 nft의 갯수가 6의 배수 (6 , 12, 18 ... )
        if(countNft % 6 == 0 && countNft != 0) {
            HealthRecord healthRecord = healthRecordServiceImpl.createHealthRecord(userId,countNft);

            //healthInfoId가 null인 nft 가져오기
            List<Nft> allByHealthRecordIsNull = nftRepository.findAllByHealthRecordIsNullAndUser_Id(userId);
            allByHealthRecordIsNull.forEach(n -> {
                n.setHealthRecord(healthRecord);
            });
        }
        return save;
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
