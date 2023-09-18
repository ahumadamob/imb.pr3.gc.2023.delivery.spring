package imb.pr3.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.delivery.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
