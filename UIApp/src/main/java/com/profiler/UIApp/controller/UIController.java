package com.profiler.UIApp.controller;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.profiler.UIApp.domain.UIData;
import com.profiler.UIApp.repositories.UIRepository;
import com.profiler.UIApp.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@BasePathAwareController
public class UIController {
    private final UIRepository uiRepository;

    @Autowired
    public UIController(final UIRepository uiRepository) {
        this.uiRepository = uiRepository;
    }

    @RequestMapping(path = "uidata", method = RequestMethod.GET, produces = "application/hal+json")
    public @ResponseBody
    ResponseEntity<?> getUIData() {
        List<UIData> uiDataList = uiRepository.findAll();
        Resources<UIData> resources = new Resources<UIData>(uiDataList);
        resources.add(linkTo(methodOn(UIController.class).getUIData()).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @RequestMapping(path = "uidata/{id}", method = RequestMethod.GET, produces = "application/hal+json")
    public @ResponseBody
    ResponseEntity<?> getUIData(@PathVariable Integer id) {
        UIData uiData = uiRepository.getOne(id);
        Resource resource = new Resource(uiData);
        resource.add(linkTo(methodOn(UIController.class).getUIData(id)).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    private void getPersonInfo(Integer personId) {
        Person person = new Person();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String profileManagementService = "http://localhost:8081/persons/" + personId;
            ResponseEntity<String>
                response = restTemplate.getForEntity(profileManagementService, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = null;
            root = mapper.readTree(response.getBody());
            JsonNode name = root.path("name");
            JsonNode role = root.path("role");
            person.setName(name.asText());
            person.setRole(role.asText());
        } catch (IOException e) {
            person.setRole("Undefined");
            person.setName("Undefined");
            e.printStackTrace();
        }
    }
}
