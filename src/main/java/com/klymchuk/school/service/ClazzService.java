package com.klymchuk.school.service;

import com.klymchuk.school.dto.ClazzDto;
import com.klymchuk.school.dto.MainClazzDto;
import com.klymchuk.school.error.exceptions.EntityNotFoundException;
import com.klymchuk.school.model.Clazz;
import com.klymchuk.school.repo.ClazzRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@PropertySource(value = "classpath:exception.properties")
public class ClazzService {

    private final ClazzRepository clazzRepository;

    private final ModelMapper modelMapper;

    @Value("${aClass.exception.notfound}")
    private String classNotFoundExceptionMessage;

    public MainClazzDto getById(int id) {
        return modelMapper.map(getClazzById(id), MainClazzDto.class);
    }

    public MainClazzDto save(ClazzDto clazzDto) {
        Clazz clazz = modelMapper.map(clazzDto, Clazz.class);
        return modelMapper.map(clazzRepository.save(clazz), MainClazzDto.class);
    }

    public MainClazzDto update(ClazzDto clazzDto, int id) {
        Clazz clazz = getClazzById(id);
        modelMapper.map(clazzDto, clazz);
        return modelMapper.map(clazzRepository.save(clazz), MainClazzDto.class);
    }

    public void delete(int id) {
        Clazz clazz = getClazzById(id);
        clazzRepository.delete(clazz);
    }

    public List<MainClazzDto> getAll() {
        return clazzRepository.findAll()
                .stream()
                .map(c -> modelMapper.map(c, MainClazzDto.class))
                .collect(Collectors.toList());
    }

    private Clazz getClazzById(int id) {
        return clazzRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(classNotFoundExceptionMessage + id));
    }

}
