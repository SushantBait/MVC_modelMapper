package com.sprinboot.web_tutorial.service;

import com.sprinboot.web_tutorial.dto.EmployeeDTO;
import com.sprinboot.web_tutorial.entity.EmployeeEntity;
import com.sprinboot.web_tutorial.repo.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long employeeId) {
        EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).orElse(null);
//        ModelMapper mapper = new ModelMapper();  //this is skip becoz we can create ModelMapper Object every time
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeEntity inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(inputEmployee);

        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long employeeId) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exsits = isExistsEmployeeId(employeeId);
        if(!exsits) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> update) {

        boolean exsits = isExistsEmployeeId(employeeId);
        if(!exsits) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        update.forEach((key, value)->{
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, key);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }

    public boolean isExistsEmployeeId(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }
}
