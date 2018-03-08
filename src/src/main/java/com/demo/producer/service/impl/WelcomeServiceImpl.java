package com.demo.producer.service.impl;


import com.demo.producer.dao.MongoDao;
import com.demo.producer.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WelcomeServiceImpl implements WelcomeService {

    @Autowired
    private MongoDao mongoDAO;

    @Override
    public long getVisitorCount() {
        try {
            return mongoDAO.eachClick();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
