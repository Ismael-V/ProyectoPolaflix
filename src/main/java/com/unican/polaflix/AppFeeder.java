package com.unican.polaflix;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.unican.polaflix.dominio.*;

@Component
public class AppFeeder implements CommandLineRunner {
	
	@Override
	public void run(String... args) throws Exception {
		
		Usuario Ismael = new Usuario("Ismael", "abcd", new IBAN());

		Serie OnePiece = new Serie("One Piece", "Lorem Ipsum", Categoria.STANDARD);

		Temporada T1_OP = new Temporada(OnePiece, 1);

		Capitulo C1_OP = new Capitulo(T1_OP, "El rey de los piratas", "???", 1);

		Ismael.anyadirSerie(OnePiece);
		Ismael.verCapitulo(C1_OP, true);

		System.out.println("Coste en centimos: " + Integer.toString(Ismael.getFacturas().get(0).getImporteTotalCent()));
		
		System.out.println("Application feeded");
	}
}
