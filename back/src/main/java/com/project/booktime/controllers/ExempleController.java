package com.project.booktime.controllers;

import com.project.booktime.params.Constants;
import org.bson.json.JsonObject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan
public class ExempleController
{
    @GetMapping(Constants.EXEMPLE_CONTROLLER_URL)
    public String getExemple()
    {
        return "Controller exemple";
    }
}


