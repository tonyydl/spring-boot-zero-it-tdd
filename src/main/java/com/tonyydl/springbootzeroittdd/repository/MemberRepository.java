package com.tonyydl.springbootzeroittdd.repository;

import com.tonyydl.springbootzeroittdd.data.po.MemberPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepository extends JpaRepository<MemberPo, Long>, JpaSpecificationExecutor<MemberPo> {
}
