package com.coisini.springbootlearn.repository;

import com.coisini.springbootlearn.model.Spu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Description Spu 仓储
 * @author coisini
 * @date Aug 14, 2021
 * @Version 1.0
 */
@Repository
public interface SpuRepository extends JpaRepository<Spu, Long> {

    /**
     * 通过ID查找
     * @param id ID
     * @return
     */
    Spu findOneById(Long id);

    /**
     * 通过分类ID查询
     * @param cid 分类ID
     * @param page 分页
     * @return
     */
    Page<Spu> findByCategoryIdOrderByCreateTimeDesc(Long cid, Pageable page);

    /**
     * 通过父级分类ID查询
     * @param cid 父级分类ID
     * @param page 分页
     * @return
     */
    Page<Spu> findByRootCategoryIdOrderByCreateTime(Long cid, Pageable page);

    /**
     * 根据title模糊查询
     * @param title 标题
     * @return
     */
    @Query(value="select a from Spu a where a.title like CONCAT('%',:title,'%') ")
    Page<Spu> findByTitle(String title, Pageable page);

}
