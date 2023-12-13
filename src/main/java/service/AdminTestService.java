package service;

import dto.Test;
import repository.TestRepository;

public class AdminTestService {
    private final TestRepository testRepository = new TestRepository();

    public void createTestService(Test test) {
        if (testRepository.createTestRepository(test)) {
            System.out.println("Success");
        }else {
            System.out.println("test not added");
        }
    }


}
