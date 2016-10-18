package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataJpaAuditingFailApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringDataJpaAuditingFailApplicationTests.class);

    @Autowired
    TestEntityRepository testEntityRepository;

    @Test
    //fails on 1.4.1, works on 1.4.0
    public void checkIfAuditingWorks() {
        //given:
        TestEntity newEntity = new TestEntity("test");
        //when:
        TestEntity savedEntity = testEntityRepository.save(newEntity);
        //then:
        assertThat(savedEntity.getCreatedDate()).isNotNull();
        assertThat(savedEntity.getModifiedDate()).isNotNull();
    }
}
