package com.project.booktime.controllers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.booktime.params.Constants;

@RestController
@ComponentScan
public class CollectAPIData
{
    @GetMapping(Constants.DATA_COLLECT_URL)
    public boolean collectData()
    {
        return true;
    }
}