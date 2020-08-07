package com.beletattirichard.cadastro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beletattirichard.cadastro.domain.Cliente;
import com.beletattirichard.cadastro.dto.ClienteDTO;
import com.beletattirichard.cadastro.repositories.ClienteRepository;
import com.beletattirichard.cadastro.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);

	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		Cliente cliente = new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail());
		return cliente;
	}

	public void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}