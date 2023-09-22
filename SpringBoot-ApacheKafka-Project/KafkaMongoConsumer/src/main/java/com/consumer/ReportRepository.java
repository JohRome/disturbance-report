package com.consumer;

import com.consumer.entities.ReportEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<ReportEntity, String> {
}
