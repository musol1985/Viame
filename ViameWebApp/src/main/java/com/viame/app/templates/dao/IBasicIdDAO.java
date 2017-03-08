package com.viame.app.templates.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.viame.app.templates.model.AModelId;



public interface IBasicIdDAO<I extends AModelId> extends MongoRepository<I, ObjectId>{

}
