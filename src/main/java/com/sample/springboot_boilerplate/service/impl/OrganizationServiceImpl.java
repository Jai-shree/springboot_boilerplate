package com.sample.springboot_boilerplate.service.impl;

import com.sample.springboot_boilerplate.Db.OrgHandler;
import com.sample.springboot_boilerplate.dto.EmployeeDTO;
import com.sample.springboot_boilerplate.dto.OrganizationDTO;
import com.sample.springboot_boilerplate.dto.ProductDTO;
import com.sample.springboot_boilerplate.entity.Organization;
import com.sample.springboot_boilerplate.exception.ResourceNotFoundException;
import com.sample.springboot_boilerplate.mapper.OrganizationMapper;
import com.sample.springboot_boilerplate.repository.OrganizationRepository;
import com.sample.springboot_boilerplate.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final OrgHandler orgHandler;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper, OrgHandler orgHandler) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
        this.orgHandler = orgHandler;
    }

    @Override
    public List<OrganizationDTO> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream()
                .map(organizationMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<ProductDTO> getProductList(Integer id) {
        List<Object[]> orgs = orgHandler.getProductList(id);
        List<ProductDTO> products = new ArrayList<>();

        for  (Object[] org : orgs) {
             ProductDTO dto = new ProductDTO();
             dto.setId(Integer.parseInt(Objects.toString(org[0])));
             dto.setName((String) org[1]);

             products.add(dto);
        }

        return products;
    }
    @Override
    public List<EmployeeDTO> getEmployeeList(Integer id) {
        List<Object[]> org1 = orgHandler.getEmployeeList(id);
        List<EmployeeDTO> employees = new ArrayList<>();

        for  (Object[] org : org1) {
            EmployeeDTO dto = new EmployeeDTO();
            dto.setId(Integer.parseInt(Objects.toString(org[0])));
            dto.setEmployee_name((String) org[1]);

            employees.add(dto);
        }

        return employees;
    }
    @Override
    public List<EmployeeDTO> getManagersEmployeeList(Integer id, String empEmail) {
        List<Object[]> org2 = orgHandler.getManagerEmployeeList(id,empEmail);
        List<EmployeeDTO> employee = new ArrayList<>();

        for  (Object[] org : org2) {
            EmployeeDTO dto = new EmployeeDTO();
            dto.setId(Integer.parseInt(Objects.toString(org[0])));
            dto.setEmployee_name((String) org[1]);

            employee.add(dto);
        }

        return employee;
    }

    @Override
    public OrganizationDTO getOrganizationById(Integer id) {
        // Throw exception if the organization is not found
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with ID: " + id));

        return organizationMapper.toDTO(organization);
    }
}
