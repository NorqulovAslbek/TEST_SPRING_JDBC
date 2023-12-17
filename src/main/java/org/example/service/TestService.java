package org.example.service;

import org.example.dto.Profile;
import org.example.dto.Test;
import org.example.repository.ProfileRepository;
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
    private ProfileRepository profileRepository;
    @Autowired
    @Qualifier("scannerLine")
    private Scanner scanner;
    @Autowired
    @Qualifier("scannerInt")
    private Scanner scannerInt;

    public void createTestService(List<Test> tests) {
        for (Test test : tests) {
            if (!testRepository.createTestRepository(test)) {
                System.out.println("test not added");
            }
        }
    }

    public void startTheTestService(Profile profile) {
        int countAnswer = 0, i = 0;
        System.out.println("===========>>Test started<<============");
        List<Test> testList = testRepository.getTestListsRepository();
        Collections.shuffle(testList);
        for (Test test : testList) {
            List<String> testAnswers = new ArrayList<>();
            testAnswers.add(test.getA());
            testAnswers.add(test.getB());
            testAnswers.add(test.getC());
            testAnswers.add(test.getD());
            Collections.shuffle(testAnswers);
            System.out.println((i + 1 + ")") + test.getQuestion());
            i++;
            System.out.printf("""
                            a)%s
                            b)%s
                            c)%s
                            d)%s
                            """,
                    testAnswers.get(0), testAnswers.get(1), testAnswers.get(2), testAnswers.get(3));
            System.out.print("Choose the correct answer:");
            String answer = scanner.nextLine();
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
        System.out.println("\u001B[32m" + "Togri javolar:" + "\u001B[0m" + countAnswer + "\n" + "\u001B[31m" + "Hato javoblar:" + "\u001B[0m" + (testList.size() - countAnswer));
        profileRepository.updateProfileResult(countAnswer, profile.getId());
    }

    public void deleteTestService(Profile profile) {
        List<Test> testListsRepository = testRepository.getTestListsRepository();
        for (Test value : testListsRepository) {
            System.out.println("------------------------------------------------------------------");
            System.out.println(value.getId() + ")" + value.getQuestion());
        }
        System.out.print("O'chirmoqchi bo'gan test Id sini kiriting:");
        int id = scannerInt.nextInt();
        if (testRepository.deleteTestById(id)) {
            System.out.println("Test deleted successfully");
        }
    }


    public void changeTestService() {
        List<Test> testListsRepository = testRepository.getTestListsRepository();
        for (Test value : testListsRepository) {
            System.out.println("------------------------------------------------------------------");
            System.out.println(value.getId() + ")" + value.getQuestion());
        }
        System.out.print("O'zgartirmoqchi bo'lgan testingini Id sini kiriting:");
        int id = scannerInt.nextInt();
        System.out.print("Enter question:");
        String question = scanner.nextLine();
        System.out.print("a)");
        String a = scanner.nextLine();
        System.out.print("b)");
        String b = scanner.nextLine();
        System.out.print("c)");
        String c = scanner.nextLine();
        System.out.print("d)");
        String d = scanner.nextLine();
        System.out.print("Enter the concrete answer:");
        String concrete = scanner.nextLine();
        String answer = switch (concrete) {
            case "a" -> a;
            case "b" -> b;
            case "c" -> c;
            case "d" -> d;
            default -> throw new IllegalStateException("Unexpected value: " + concrete);
        };
        Test test = new Test();
        test.setId(id);
        test.setQuestion(question);
        test.setA(a);
        test.setB(b);
        test.setC(c);
        test.setD(d);
        test.setCorrectAnswer(answer);
        if (testRepository.changeTestByIdRepository(id, test)) {
            System.out.println("Test changed successfully !!");
        }
    }
}
