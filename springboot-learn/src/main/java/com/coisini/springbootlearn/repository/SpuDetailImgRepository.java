package com.coisini.springbootlearn.repository;

import com.coisini.springbootlearn.model.SpuDetailImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description SpuDetailImg 仓储
 * @author coisini
 * @date Aug 14, 2021
 * @Version 1.0
 */
@Repository
public interface SpuDetailImgRepository extends JpaRepository<SpuDetailImg, Long> {

}
