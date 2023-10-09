package com.example.apitest.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
  @Autowired
  TestRepository testRepository;
  public void save(Datas data) {
    testRepository.save(data);
  }
}
