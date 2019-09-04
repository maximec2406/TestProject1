package ru.abr.dit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.abr.dit.Services.EntityService;

@RestController
public class RESTfulController {

    @Autowired
    EntityService es;

}
