package com.talentreef.interviewquestions.takehome.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.takehome.respositories.WidgetRepository;
import com.talentreef.interviewquestions.takehome.services.WidgetService;
import com.talentreef.interviewquestions.takehome.globalexceptions.WidgetAlreadyExistsException;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/v1/widgets", produces = MediaType.APPLICATION_JSON_VALUE)
public class WidgetController {

    private final WidgetService widgetService;

    @Autowired
    private WidgetRepository widgetRepository;

    public WidgetController(WidgetService widgetService) {
        Assert.notNull(widgetService, "widgetService must not be null");
        this.widgetService = widgetService;
    }

    // Get all Widgets
    @GetMapping
    public ResponseEntity<List<Widget>> getAllWidgets() {
        List<Widget> widgets = widgetService.getAllWidgets();
        return ResponseEntity.ok(widgets);
    }

    // Get Widget by Name
    @GetMapping("/{name}")
    public ResponseEntity<Widget> getWidgetByName(@PathVariable String name) {
        Optional<Widget> widget = widgetRepository.findByName(name);
        return widget.map(ResponseEntity::ok)
                     .orElseThrow(() -> new IllegalArgumentException("Widget with name '" + name + "' not found."));
    }

    // Create a new Widget
    @PostMapping
    public ResponseEntity<Widget> createWidget(@Valid @RequestBody Widget widget) {
        if (widgetRepository.findByName(widget.getName()).isPresent()) {
            throw new WidgetAlreadyExistsException("Widget with name '" + widget.getName() + "' already exists.");
        }
        Widget savedWidget = widgetRepository.save(widget);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWidget);
    }

    // Update Widget (description or price)
    @PutMapping("/{name}")
    public ResponseEntity<Widget> updateWidget(@PathVariable String name, @RequestBody Widget updates) {
        Optional<Widget> widgetOptional = widgetRepository.findByName(name);

        if (widgetOptional.isPresent()) {
            Widget existingWidget = widgetOptional.get();
            if (updates.getDescription() != null) {
                existingWidget.setDescription(updates.getDescription());
            }
            if (updates.getPrice() != null) {
                existingWidget.setPrice(updates.getPrice());
            }
            Widget updatedWidget = widgetRepository.save(existingWidget);
            return ResponseEntity.ok(updatedWidget);
        } else {
            throw new IllegalArgumentException("Widget with name '" + name + "' not found.");
        }
    }

    // Delete Widget by Name
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteWidget(@PathVariable String name) {
        Optional<Widget> widgetOptional = widgetRepository.findByName(name);

        if (widgetOptional.isPresent()) {
            widgetRepository.deleteByName(name);
            return ResponseEntity.noContent().build();
        } else {
            throw new IllegalArgumentException("Widget with name '" + name + "' not found.");
        }
    }
}
