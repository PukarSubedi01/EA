package app;

import app.hsql.domain.Person;
import app.hsql.domain.Pets;
import app.hsql.repositories.PersonRepository;
import app.mongo.domain.PersonMongo;
import app.mongo.domain.PetsMongo;
import app.mongo.repositories.PersonRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonRepositoryMongo personRepositoryMongo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        StopWatch stopwatchSqlSave = new StopWatch();
        stopwatchSqlSave.start();
        personRepository.saveAll(generatePersonForSql());
        stopwatchSqlSave.stop();

        StopWatch stopwatchMongoSave = new StopWatch();
        stopwatchMongoSave.start();
        personRepositoryMongo.saveAll(generatePersonForMongo());
        stopwatchMongoSave.stop();

        System.out.println("Time taken for sql to save data is: " + stopwatchSqlSave.getTotalTimeMillis());
        System.out.println("Time taken for mongo to save data is: " + stopwatchMongoSave.getTotalTimeMillis());

        StopWatch stopWatchSqlFetch = new StopWatch();
        stopWatchSqlFetch.start();
        personRepository.findAll();
        stopWatchSqlFetch.stop();

        StopWatch stopWatchMongoFetch = new StopWatch();
        stopWatchMongoFetch.start();
        personRepositoryMongo.findAll();
        stopWatchMongoFetch.stop();

        System.out.println("Time taken for sql to fetch data is: " + stopWatchSqlFetch.getTotalTimeMillis());
        System.out.println("Time taken for mongo to fetch data is: " + stopWatchMongoFetch.getTotalTimeMillis());

    }

    public List<Person> generatePersonForSql() {

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            persons.add(new Person("Pukar", "Subedi", generatePetsForSql()));
        }

        return persons;

    }

    public List<Pets> generatePetsForSql() {
        List<Pets> pets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pets.add(new Pets("Tommy", 12));
        }
        return pets;
    }


    public List<PersonMongo> generatePersonForMongo() {

        List<PersonMongo> persons = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            persons.add(new PersonMongo("Pukar", "Subedi", generatePetsForMongo()));
        }

        return persons;

    }

    public List<PetsMongo> generatePetsForMongo() {
        List<PetsMongo> pets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pets.add(new PetsMongo("Tommy", 12));
        }
        return pets;
    }
}
