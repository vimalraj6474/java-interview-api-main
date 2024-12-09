package com.talentreef.interviewquestions.takehome.respositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.talentreef.interviewquestions.takehome.models.Widget;

@Repository
public class WidgetRepository {

    private List<Widget> table = new ArrayList<>();

    // Delete Widget by Name
    public void deleteByName(String name) {
        table = table.stream()
                     .filter(widget -> !name.equals(widget.getName()))
                     .collect(Collectors.toCollection(ArrayList::new));
    }

    // Find All Widgets
    public List<Widget> findAll() {
        return new ArrayList<>(table); // Return a copy to prevent external modification
    }

    // Find Widget by Name
    public Optional<Widget> findByName(String name) {
        return table.stream()
                    .filter(widget -> name.equals(widget.getName()))
                    .findFirst();
    }

    // Save or Update Widget
    public Widget save(Widget widget) {
        deleteByName(widget.getName()); // Remove if exists to avoid duplicates
        table.add(widget);
        return widget;
    }    
}
