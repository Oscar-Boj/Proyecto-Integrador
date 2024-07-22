package com.Proyecto.Integrador.repository;

import com.Proyecto.Integrador.entity.UserMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository<UserMongoEntity, String> {
}
