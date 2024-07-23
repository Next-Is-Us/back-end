package com.nextisus.project.nft.service;

import com.nextisus.project.condition.repository.ConditionRepository;
import com.nextisus.project.domain.Condition;
import com.nextisus.project.domain.Nft;
import com.nextisus.project.domain.User;
import com.nextisus.project.nft.repository.NftRepository;
import com.nextisus.project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NftServiceImpl implements NftService {

    private final ConditionRepository conditionRepository;
    private final NftRepository nftRepository;
    private final UserRepository userRepository;

    @Override
    public Long getNfts(Long userId) {
        List<Condition> conditions = conditionRepository.getAllById(userId);
        int conditionSize = conditions.size();
        if(conditionSize % 5 == 0 && conditionSize != 0) { //기록한 상태가 5의 배수면 nft 조각 1개가 생성된 것
            if(conditionSize % 30 == 0) { //기록한 상태가 30의 배수이면 완전한 nft 1개가 생성된 것
                createNft(conditionSize, conditions, userId); //NFT 생성
            }
            return (long)conditionSize;
        }
        return null;
    }

    @Override
    public void createNft(int conditionSize, List<Condition> conditions, Long userId) {

        LocalDateTime startTime = conditions.get(0).getCreateAt();
        LocalDateTime endTime = conditions.get(conditionSize-1).getCreateAt();

        //날짜 형식 포멧
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.M.dd");
        String startDate = formatter.format(startTime);
        String endDate = formatter.format(endTime);
        String recordPeriod = startDate + "~" + endDate;

        //유저 찾기
        User user = userRepository.getByUser(userId);

        //엔티티 생성
        Nft nft = Nft.builder()
                .user(user)
                .recordPeriod(recordPeriod)
                .build();

        //연관관계 맺기
        nft.createNft(user);

        //DB에 저장
        nftRepository.save(nft);
    }
}
