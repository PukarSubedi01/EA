package app.mongo.repositories;

import app.mongo.domain.PersonMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryMongo extends MongoRepository<PersonMongo, String> {
}
