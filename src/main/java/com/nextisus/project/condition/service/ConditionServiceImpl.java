package com.nextisus.project.condition.service;

import com.nextisus.project.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.condition.dto.response.TodaysConditionResponseDto;
import com.nextisus.project.condition.repository.ConditionRepository;
import com.nextisus.project.domain.Condition;
import com.nextisus.project.util.exception.BaseErrorCode;
import com.nextisus.project.util.response.ErrorResponse;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConditionServiceImpl implements ConditionService {

    private final ConditionRepository conditionRepository;

    //오늘의 상태 기록
    @Override
    public SuccessResponse<?> createCondition(CreateConditionRequestDto request) {
        //오늘 날짜
        LocalDateTime today = LocalDateTime.now();

        //날짜 형식 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String date = today.format(formatter);

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
                .date(date)
                .build();
        // TODO 연관관계 맺기

        // DB에 저장
        conditionRepository.save(condition);

        // 성공 응답 생성
        return SuccessResponse.of(condition);
    }

    //오늘의 상태 조회
    @Override
    public TodaysConditionResponseDto getTodaysConditions() {

        //오늘 날짜
        LocalDateTime today = LocalDateTime.now();
        //날짜 형식 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String date = today.format(formatter);

        Optional<Condition> findTodaysCondition = conditionRepository.findByDate(date);

        if(findTodaysCondition.isEmpty()) {
            log.info("TodaysCondition not found");
            return null;
        }
        //오늘 날짜로 작성된 기록이 있으면 응답 Dto 생성
        TodaysConditionResponseDto response = new TodaysConditionResponseDto(
                date,
                findTodaysCondition.get().getSleepTime(),
                findTodaysCondition.get().getIsBlushing(),
                findTodaysCondition.get().getIsHeadache(),
                findTodaysCondition.get().getIsStomachache(),
                findTodaysCondition.get().getIsConstipated(),
                findTodaysCondition.get().getIsMusclePainful(),
                findTodaysCondition.get().getIsSkinTroubled(),
                findTodaysCondition.get().getIsNumbness(),
                findTodaysCondition.get().getIsChilled(),
                findTodaysCondition.get().getIsDepressed(),
                findTodaysCondition.get().getRecord()
        );
        return response;
    }
}
