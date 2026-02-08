package com.hil4rion.lifeplanner.mapper;

import com.hil4rion.lifeplanner.dto.TaskDto;
import com.hil4rion.lifeplanner.model.Task;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patch(TaskDto taskDto, @MappingTarget Task task);
}
