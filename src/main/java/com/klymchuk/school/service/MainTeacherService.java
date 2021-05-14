package com.klymchuk.school.service;

import com.klymchuk.school.dto.MTeacherDto;
import com.klymchuk.school.model.MainTeacher;
import com.klymchuk.school.repo.MainTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainTeacherService {

    private final MainTeacherRepository mainTeacherRepository;
    private final ModelMapper modelMapper;

    public void saveTeacher(MTeacherDto mainTeacher){
        mainTeacherRepository.save(modelMapper.map(mainTeacher, MainTeacher.class));
    }

}
