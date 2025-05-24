package ar.utn.edu.frba.ddsi.controllers;

import ar.utn.edu.frba.ddsi.models.dtos.input.CollectionCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.CollectionOutputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.services.impl.CollectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping
    public List<CollectionOutputDTO> getCollections(){
        return collectionService.getCollections();
    }

    @PostMapping
    public void createCollection(@RequestBody CollectionCreationDTO collectionCreationDTO) {
        collectionService.create(collectionCreationDTO);
    }

    @GetMapping("/{handler}/events")
    public List<EventOutputDTO> getEventsFromCollection(@PathVariable String handler) {
        return collectionService.getEventsFromCollection(handler);
    }
}
