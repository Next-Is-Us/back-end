package com.nextisus.project.mom.condition.service;

import com.nextisus.project.domain.Nft;
import com.nextisus.project.mom.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.client.condition.dto.response.ConditionListResponseDto;
import com.nextisus.project.client.condition.dto.response.ConditionListResponseDtoByDate;
import com.nextisus.project.repository.ConditionRepository;
import com.nextisus.project.domain.Condition;
import com.nextisus.project.domain.User;
import com.nextisus.project.client.nft.service.NftServiceImpl;
import com.nextisus.project.repository.NftRepository;
import com.nextisus.project.repository.UserRepository;
import com.nextisus.project.util.response.SuccessResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConditionServiceImpl implements ConditionService {

    private final ConditionRepository conditionRepository;
    private final UserRepository userRepository;
    private final NftServiceImpl nftServiceImpl;
    private final NftRepository nftRepository;

    //오늘의 상태 기록
    @Override
    @Transactional
    public SuccessResponse<?> createCondition(CreateConditionRequestDto request, Long userId) {

        // 오늘 날짜
        LocalDateTime today = LocalDateTime.now();

        // 날짜 형식 포맷
        DateTimeFormatter yearformatter = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter monthformatter = DateTimeFormatter.ofPattern("M");
        DateTimeFormatter dayformatter = DateTimeFormatter.ofPattern("dd");
        String year = today.format(yearformatter);
        String month = today.format(monthformatter);
        String day = today.format(dayformatter);

        // 사용자 조회
        User user = userRepository.getByUser(userId);

        // 새로운 상태 엔티티 생성
        Condition condition = Condition.builder()
                .sleepTime(request.getSleepTime())
                .isBlushing(request.getIsBlushing())
                .isHeadache(request.getIsHeadache())
                .isStomachache(request.getIsStomachache())
                .isConstipated(request.getIsConstipated())
                .isMusclePainful(request.getIsMusclePainful())
                .isSkinTroubled(request.getIsSkinTroubled())
                .isNumbness(request.getIsNumbness())
                .isChilled(request.getIsChilled())
                .isDepressed(request.getIsDepressed())
                .record(request.getRecord())
                .year(Long.parseLong(year))
                .month(Long.parseLong(month))
                .day(Long.parseLong(day))
                .user(user)
                .build();

        // 연관관계 설정
        condition.createdCondition(user);

        // DB에 저장
        Condition save = conditionRepository.save(condition);

        //해당 유저가 기록한 상태 기록의 갯수
        Long countCondition = conditionRepository.countByUser_Id(userId);

        // 방금 기록한 상태의 상태id가 30의 배수면 (30번째, 60번째, 90번째 ... )
        if( countCondition % 30 == 0 && countCondition != 0 ) {
            //nft 생성
                Nft nft = nftServiceImpl.createNft(userId);
                //nftId가 null인 condition 가져옴
                List<Condition> allByNftIsNull = conditionRepository.findAllByNftIsNullAndUser_Id(userId);
                allByNftIsNull.forEach(c -> {
                    c.setNft(nft); //영속성 컨텍스트 어쩌구..
                });
        }
        // 성공 응답 생성
        return SuccessResponse.of(condition);
    }


    // 날짜별 상태 기록 여부 조회
    @Override
    public ConditionListResponseDtoByDate getConditionByDate(Long year, Long month, Long day) {

        List<Condition> findConditionByYear = conditionRepository.findByYear(year);
        Condition finConditionByDate = null;
        for(Condition condition : findConditionByYear) {
            if(condition.getMonth().equals(month) && condition.getDay().equals(day)) {
                finConditionByDate = condition;
            }
            else {
                ConditionListResponseDtoByDate response = new ConditionListResponseDtoByDate(
                        null
                );
                return response;
            }
        }
        //날짜 형식 포맷
        LocalDateTime createAt = finConditionByDate.getCreateAt();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String recordDate = createAt.format(formatter);

        ConditionListResponseDtoByDate response = new ConditionListResponseDtoByDate(
                recordDate
        );
        return response;
    }

    //해당 날짜에 기록한 내용 상세 조회
    @Override
    public ConditionListResponseDto getDetailConditionByDate(Long year, Long month, Long day) {

        List<Condition> findConditionByYear = conditionRepository.findByYear(year);
        Condition findConditionByDate = null;
        for(Condition condition : findConditionByYear) {
            if(condition.getMonth().equals(month) && condition.getDay().equals(day)) {
                findConditionByDate = condition;
            }
        }
        ConditionListResponseDto response = new ConditionListResponseDto(
                findConditionByDate.getYear(),
                findConditionByDate.getMonth(),
                findConditionByDate.getDay(),
                findConditionByDate.getSleepTime(),
                findConditionByDate.getIsBlushing(),
                findConditionByDate.getIsHeadache(),
                findConditionByDate.getIsStomachache(),
                findConditionByDate.getIsConstipated(),
                findConditionByDate.getIsMusclePainful(),
                findConditionByDate.getIsSkinTroubled(),
                findConditionByDate.getIsNumbness(),
                findConditionByDate.getIsChilled(),
                findConditionByDate.getIsDepressed(),
                findConditionByDate.getRecord()
        );
        return response;
    }
}
