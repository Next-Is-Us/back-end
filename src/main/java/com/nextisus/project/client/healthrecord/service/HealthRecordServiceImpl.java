package com.nextisus.project.client.healthrecord.service;

import com.nextisus.project.client.healthrecord.dto.response.CreatePdfDto;
import com.nextisus.project.client.healthrecord.dto.response.HealthRecordListDto;
import com.nextisus.project.client.healthrecord.dto.response.PdfListDto;
import com.nextisus.project.domain.Condition;
import com.nextisus.project.domain.HealthRecord;
import com.nextisus.project.domain.Nft;
import com.nextisus.project.client.healthrecord.dto.response.HealthRecordResponseDto;
import com.nextisus.project.domain.User;
import com.nextisus.project.exception.healthrecord.PdfInternalServerErrorException;
import com.nextisus.project.image.service.S3UploadService;
import com.nextisus.project.repository.ConditionRepository;
import com.nextisus.project.repository.HealthRecordRepository;
import com.nextisus.project.repository.NftRepository;
import com.nextisus.project.repository.UserRepository;
import com.nextisus.project.util.response.PageResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    private final S3UploadService s3UploadService;

    //건강기록 전체 조회
    @Override
    public List<HealthRecordListDto> getHealthRecord(Long userId) {

        //조회한 유저에게 존재하는 건강 기록 리스트 받아오기
        List<HealthRecord> healthRecordsList = healthRecordRepository.findAllByUser_IdOrderByCreateAtDesc(userId);
        Long totalCount = nftRepository.countByUser_Id(userId);
        Long nftCount = totalCount % 6; //아직 건강기록으로 변환 안된 nft 갯수
        List<HealthRecordListDto> healthRecordList = new ArrayList<>();
        HealthRecord nullHealthRecord = HealthRecord.builder()
                .healthRecordId(null)
                .recordPeriod(null)
                .nftCount(nftCount)
                .build();
        healthRecordList.add(HealthRecordListDto.from(nullHealthRecord,false));
        for(HealthRecord healthRecord : healthRecordsList) {
            healthRecordList.add(HealthRecordListDto.from(healthRecord,true));
        }
        return healthRecordList;
    }

    //건강기록 생성
    @Override
    @Transactional
    public HealthRecord createHealthRecord(Long userId, Long countNft) {

        User byUser = userRepository.getByUser(userId);

        //recordPeroid, week 데이터 가공
        LocalDateTime startTime = conditionRepository.findOldestCreateAtByUserId(userId);
        LocalDateTime endTime = conditionRepository.findLatestCreateAtByUserId(userId);

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

        //nftCount 설정
        Long totalNft = nftRepository.countByUser_Id(userId);
        Long nftCount = null;
        if(totalNft % 6 == 0) {
            nftCount = 6L;
        }
        else{
            nftCount = totalNft % 6;
        }

        // 엔티티 생성
        HealthRecord healthRecord = HealthRecord.builder()
                .user(byUser)
                .recordPeriod(recordPeriod)
                .week(week)
                .nftCount(nftCount)
                .build();

        // 연관관계 맺기
        healthRecord.setUser(byUser);

        // DB에 저장
        HealthRecord savedHealthRecord = healthRecordRepository.save(healthRecord);

        List<Condition> nullCondition = conditionRepository.findAllByHealthRecordIsNullAndUser_Id(userId);
        nullCondition.forEach(condition -> {
            condition.setHealthRecord(savedHealthRecord);
        });
        return savedHealthRecord;
    }

    //건강기록 세부 조회
    @Override
    @Transactional
    public HealthRecordResponseDto getHealthRecordDetail(Long healthRecordId) {

        //건강기록 가져오기
        HealthRecord healthRecord = healthRecordRepository.getByHealthRecordId(healthRecordId);
        return HealthRecordResponseDto.from(healthRecord);
    }

    @Override
    public PageResponse<PdfListDto> getHealthRecordPdf(Long healthRecordId, Long userId, Pageable pageable) {
        conditionRepository.getAllByHealthRecord(healthRecordId);
        Page<PdfListDto> conditions = conditionRepository.findAllByHealthRecord_HealthRecordId(healthRecordId,pageable).map(PdfListDto::from);
        List<PdfListDto> pdfList = conditions.getContent();
        PageImpl<PdfListDto> data = new PageImpl<>(pdfList, pageable, conditions.getTotalElements());
        return PageResponse.of(data);
    }

    @Override
    public void savePdf(CreatePdfDto createPdfDto) {
        try {
            s3UploadService.upload(createPdfDto.getPdfFile(),"pdf-file");
        }
        catch (Exception e) {
            throw new PdfInternalServerErrorException();
        }
    }
}
