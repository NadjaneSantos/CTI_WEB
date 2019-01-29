package com.ifpe.CIT.Cadastro;

import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;


@Service
public class semaforoService {
			
		@Autowired
		private semaforoRepository repository;

		public List<semaforo> findAll() {
			return repository.findAll();
		}
		
		public semaforo findOne(BigInteger id) {
			return repository.findOne(id);
		}
		
		public semaforo save(semaforo semaforo) {
			return repository.saveAndFlush(semaforo);
		}
		
		public void delete(BigInteger id) {
			repository.delete(id);
		}

}
