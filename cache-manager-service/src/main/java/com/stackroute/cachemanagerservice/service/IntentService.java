package com.stackroute.cachemanagerservice.service;

import com.stackroute.cachemanagerservice.domain.Intent;
import com.stackroute.cachemanagerservice.repository.IntentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class IntentService {

    @Autowired
    IntentRepository intentRepository;

    public List<Intent> getAll() { return intentRepository.intent("intent");}
    // public List<Intent> getAll() { return intentRepository.intent("term");}

}