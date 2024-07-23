package com.nextisus.project.nft.repository;

import com.nextisus.project.domain.Nft;
import com.nextisus.project.nft.exception.NftNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NftRepository extends JpaRepository<Nft, Long> {
    Optional<Nft> findByNftId(Long nftId);

    default Nft getByNft(Long nftId) {
        return findByNftId(nftId).orElseThrow(NftNotFoundException::new);
    }

}
