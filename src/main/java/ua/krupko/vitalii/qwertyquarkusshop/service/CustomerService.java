package ua.krupko.vitalii.qwertyquarkusshop.service;

import lombok.extern.slf4j.Slf4j;
import ua.krupko.vitalii.qwertyquarkusshop.persistence.entity.Customer;
import ua.krupko.vitalii.qwertyquarkusshop.persistence.repository.CustomerRepository;
import ua.krupko.vitalii.qwertyquarkusshop.service.dto.CustomerDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@Transactional
public class CustomerService {
    @Inject
    CustomerRepository customerRepository;
    public CustomerDto create(CustomerDto customerDto) {
        log.debug("Request to create Customer : {}", customerDto);
        return mapToDto(this.customerRepository.save(
                new Customer(customerDto.getFirstName(),
                        customerDto.getLastName(),
                        customerDto.getEmail(),
                        customerDto.getTelephone(),
                        Collections.emptySet(),
                        Boolean.TRUE)
        ));
    }
    public List<CustomerDto> findAll() {
        log.debug("Request to get all Customers");
        return this.customerRepository.findAll()
                .stream()
                .map(CustomerService::mapToDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public CustomerDto findById(Long id) {
        log.debug("Request to get Customer : {}", id);
        return this.customerRepository.findById(id)
                .map(CustomerService::mapToDto).orElse(null);
    }
    public List<CustomerDto> findAllActive() {
        log.debug("Request to get all active customers");
        return this.customerRepository.findAllByEnabled(true)
                .stream().map(CustomerService::mapToDto)
                .collect(Collectors.toList());
    }
    public List<CustomerDto> findAllInactive() {
        log.debug("Request to get all inactive customers");
        return this.customerRepository.findAllByEnabled(false)
                .stream().map(CustomerService::mapToDto)
                .collect(Collectors.toList());
    }
    public void delete(Long id) {
        log.debug("Request to delete Customer : {}", id);
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("Cannot find Customer with id " + id));
                                customer.setEnabled(false);
        this.customerRepository.save(customer);
    }
    public static CustomerDto mapToDto(Customer customer) {
        return new CustomerDto(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getTelephone()
        );
    }
}
