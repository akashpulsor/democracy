package com.example.hackathon.dto;

import com.example.hackathon.model.PropagandaFreeMedia;
import com.example.hackathon.model.State;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO change to states Dto for encapsulation
@Data
public class EventDto {
    private long eventId;
    private List<StatesDto> propagandaList = new ArrayList<>(); /// states [r
    private State eventState;
    private PropagandaFreeMedia propagandaFreeMediaMap;
    private State reportStatesDto;

}
