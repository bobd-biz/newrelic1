package com.examples.bobd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examples.bobd.model.Customer;
import com.examples.bobd.repository.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CustomerService {

	@Autowired
	private final CustomerRepository repo;
	
	public Mono<Customer> findById(String id) {
		return Mono.justOrEmpty(repo.findById(id));
	}
	
	public Flux<Customer> findAll() {
		return Flux.fromIterable(repo.findAll());
	}
	
	public Flux<Customer> findAll(Pageable pageable) {
		return Flux.fromIterable(repo.findAll(pageable));
	}

    public Customer create(Customer customer){
        return repo.save(customer);
    }
    
	public Mono<Customer> update(Customer customer) {
		return findById(customer.getId()).map(c -> {
			Customer newcustomer = Customer.builder()
					.id(c.getId())
					.firstName(customer.getFirstName())
					.lastName(customer.getLastName())
					.companyName(customer.getCompanyName())
					.build();
			return repo.save(newcustomer);
		})
		.switchIfEmpty(Mono.error(new RuntimeException("Update: Customer not found id=" + customer.getId())));
	}
	
	public void delete(String id) {
		repo.deleteById(id);
	}
	
	public List<Customer> findByFirstName(String firstName) {
		return repo.findByFirstNameIgnoreCase(firstName);
	}
	
	public List<Customer> findByLastName(String lastName) {
		return repo.findByLastNameIgnoreCase(lastName);
	}
	
	public List<Customer> findByName(String firstName, String lastName) {
		return repo.findByFirstNameOrLastNameIgnoreCase(firstName, lastName);
	}

	public List<Customer> findByCompanyName(String pathVariable) {
		return repo.findByCompanyNameIgnoreCase(pathVariable);
	}
}