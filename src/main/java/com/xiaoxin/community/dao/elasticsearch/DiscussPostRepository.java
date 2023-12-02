package com.xiaoxin.community.dao.elasticsearch;


import com.xiaoxin.community.entity.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost, Integer> {
}
