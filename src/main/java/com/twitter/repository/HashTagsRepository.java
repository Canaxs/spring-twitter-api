package com.twitter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.twitter.model.HashTags;

public interface HashTagsRepository extends JpaRepository<HashTags, Long>,JpaSpecificationExecutor<HashTags>{
}
