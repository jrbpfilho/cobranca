package br.com.rbsystems.cobranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rbsystems.cobranca.model.Titulo;

public interface Titulos extends JpaRepository <Titulo, Long> {

}
