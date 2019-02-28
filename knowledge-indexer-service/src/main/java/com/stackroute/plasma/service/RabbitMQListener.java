package com.stackroute.plasma.service;

import com.stackroute.plasma.model.Description;
import com.stackroute.plasma.model.Evaluator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RabbitMQListener {

    @Autowired
    DescriptionService descriptionService;

    @Autowired
    RelationshipService relationshipService;

    Evaluator evaluator = new Evaluator();

    @RabbitListener(queues = "${javainuse1.rabbitmq.queue}", containerFactory = "jsaFactory")
    public void consume(Evaluator evaluator){
        System.out.println("Recieved Message From RabbitMQ: " + evaluator.toString());
        /*either remove the Evaluator object, hence getters and setter OR implement three methods for modularity and call other two methods in consume*/
        this.evaluator.setTimestamp(evaluator.getTimestamp());
        this.evaluator.setDomain(evaluator.getDomain());
        this.evaluator.setConcept(evaluator.getConcept());
        this.evaluator.setTitle(evaluator.getTitle());
        this.evaluator.setDescription(evaluator.getDescription());
        this.evaluator.setKeywords(evaluator.getKeywords());
        this.evaluator.setUrl(evaluator.getUrl());
        this.evaluator.setLevel(evaluator.getLevel());
        this.evaluator.setConfidenceScore(evaluator.getConfidenceScore());


        /*creates description node*/
        Description description = new Description();
        description.setTimestamp(evaluator.getTimestamp());
        description.setDomain(evaluator.getDomain());
        description.setConcept(evaluator.getConcept());
        description.setUrl(evaluator.getUrl());
        description.setDescription(evaluator.getDescription());
        description.setKeywords(evaluator.getKeywords());
        description.setTitle(evaluator.getTitle());
        descriptionService.create(description);

        /*creates the relationship*/
        relationshipService.create(evaluator.getConcept(),evaluator.getConfidenceScore(),evaluator.getLevel());
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }


}