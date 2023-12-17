package org.example.service;

import org.example.dto.Profile;
import org.example.dto.Test;
import org.example.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private Scanner scanner;

    public void createTestService(List<Test> tests) {
        for (Test test : tests) {
            if (!testRepository.createTestRepository(test)) {
                System.out.println("test not added");
            }
        }

    }

    public void startTheTestService(Profile profile) {
        int countAnswer = 0;

        System.out.println("===========>>Test started<<============");
        List<Test> testList = testRepository.getTestListsRepository();
        Collections.shuffle(testList);
        for (Test test : testList) {
            List<String> testAnswers = new ArrayList<>();
            testAnswers.add(test.getA());
            testAnswers.add(test.getB());
            testAnswers.add(test.getA());
            testAnswers.add(test.getC());
            Collections.shuffle(testAnswers);
            System.out.println(test.getCorrectAnswer());
            System.out.printf("""
                            a)%s
                            b)%s
                            c)%s
                            d)%s
                            """,
                    testAnswers.get(0), testAnswers.get(1), testAnswers.get(2), testAnswers.get(3));
            System.out.print("Choose the correct answer:");
            String answer = scanner.next();
            String correctAnswer = switch (answer) {
                case "a" -> testAnswers.get(0);
                case "b" -> testAnswers.get(1);
                case "c" -> testAnswers.get(2);
                case "d" -> testAnswers.get(3);
                default -> throw new IllegalStateException("Unexpected value: " + answer);
            };
            if (correctAnswer.equals(test.getCorrectAnswer())) {
                countAnswer++;
            }
        }
    }


}
