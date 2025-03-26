package com.unican.polaflix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.unican.polaflix.dominio.*;
import com.unican.polaflix.repositories.SerieRepository;
import com.unican.polaflix.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Component
public class AppFeeder implements CommandLineRunner {

	@Autowired
	SerieRepository sr;

	@Autowired
	UsuarioRepository ur;

	@Transactional
	public void persist(Usuario usr, Serie serie) {
		sr.save(serie);
		ur.save(usr);
	}

	@Transactional
	public void remove(Usuario usr){
		ur.delete(usr);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Usuario Ismael = new Usuario("Ismael", "abcd", new IBAN());

		Serie OnePiece = new Serie("One Piece", "Lorem Ipsum", Categoria.STANDARD);

		Temporada T1_OP = new Temporada(OnePiece, 1);

		Capitulo C1_OP = new Capitulo(T1_OP, "El rey de los piratas", "???", 1);

		Ismael.anyadirSerie(OnePiece);
		Ismael.verCapitulo(C1_OP, true);

		persist(Ismael, OnePiece);

		//TODO: Terminar la capa de persistencia para permitir borrados y generados adecuados.
		//remove(Ismael);

		System.out.println("Coste en centimos: " + Integer.toString(Ismael.getFacturas().get(0).getImporteTotalCent()));
		
		System.out.println("Application feeded");
	}
}
