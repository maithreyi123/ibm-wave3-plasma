package com.stackroute.knowledgequeryservice.controller;

import com.stackroute.knowledgequeryservice.model.Description;
import com.stackroute.knowledgequeryservice.service.DescriptionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@Api(description = "Read operation on description nodes present in knowledge graph")
public class DescriptionController {
    @Autowired
    DescriptionService descriptionService;
   /* @Autowired
    RabbitMQSender rabbitMQSender;*/

    /*
    @GetMapping("get")
    public List<Description> getAll(){
        return descriptionService.getAll();
    }
    */

    @GetMapping("get/{concept}/{level}")
    //concept   level   domain  userId
    public List<Description> concept(@PathVariable("concept") String concept,@PathVariable("level") String level){
       return descriptionService.concept(concept,level);

/*
     For sending
        List<Description> descriptions = descriptionService.concept(concept,level);
        for(Description description : descriptions){
            System.out.println(description);
            rabbitMQSender.sender(description);
        }
*/

    }
}
