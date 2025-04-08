package com.unican.polaflix;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.unican.polaflix.dominio.*;
import com.unican.polaflix.repositories.ArtistaRepository;
import com.unican.polaflix.repositories.SerieRepository;
import com.unican.polaflix.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Component
public class AppFeeder implements CommandLineRunner {

	@Autowired
	SerieRepository sr;

	@Autowired
	UsuarioRepository ur;

	@Autowired 
	ArtistaRepository ar;

	@Transactional
	public void persist(Usuario usr, Serie serie) {
		sr.saveAndFlush(serie);
		ur.saveAndFlush(usr);
			
	}

	@Transactional
	public Serie addNieR(){

		//Creamos la Serie NieR Automata
		Serie NieRAutomata = new Serie(	"NieR:Automata_Ver1.1a", "The distant future, 5012.\r\n" + 
										"\r\n" + 
										"The sudden aerial invasion of Earth by <Aliens> and their creations <Machine Lifeforms> led mankind to the brink of extinction. The surviving number of humans who took refuge on the moon to organize a counterattack using <android> soldiers to recapture Earth. However, the war reaches a stalemate as the <Machine Lifeforms> continue to multiply infinitely. In turn, humanity deploys a new unit of android soldiers as an ultimate weapon: YoRHa.\r\n" + //
										"\r\n" + 
										"Newly dispatched to Earth 2B joins 9S, the analyst currently stationed there, where amid their mission, they encounter a myriad of mysterious phenomena...\r\n" + //
										"\r\n" + 
										"This is the story of these lifeless <androids> and their endless fight for the sake of mankind.",
										Categoria.GOLD);

		//Creamos las 2 temporadas								
		Temporada NieRAutomata_T1 = new Temporada(NieRAutomata, 1);
		//Temporada NieRAutomata_T2 = new Temporada(NieRAutomata, 2);

		Capitulo NieRAutomata_T1_C1  = new Capitulo(NieRAutomata_T1, "or not to [B]e", 			"5012 AD.\r\n" + 
																										"Lifeforms otherwise known as <Aliens> begin their invasion of Earth.\r\n" + //
																										"Since mankind escaped to the moon, the ground battle between the <androids> created by humans and the <Machine Lifeforms> created by the Aliens has continued for a long time.\r\n" + 
																										"\r\n" + 
																										"This craft is the accompanying support unit entitled <Pod 042>.\r\n" + //
																										"In the 243rd Descent Operation held March 10, 11945, this unit will support Android <2B> of the <YoRHa Soldiers>. Thereafter, this unit will report regular mission status in tandem with <Pod 153>, the unit supporting YoRHa Aircraft Solider <9S> in this joint operation.", 1);

		Capitulo NieRAutomata_T1_C2  = new Capitulo(NieRAutomata_T1, "city e[S]cape", 			"Pod 153 - Report:\r\n" + //
																										"\r\n" + //
																										"New data not registered to the existing archive was discovered during the search on ground.\r\n" + //
																										"Transferring to shared server.\r\n" + //
																										"\r\n" + //
																										"The mission of the machine lifeform was to destroy the androids.\r\n" + //
																										"However, that machine lifeform no longer possessed the will to fight.\r\n" + //
																										"The machine lifeform found a small ring of flowers.\r\n" + //
																										"The machine lifeform felt a mysterious <Something> beginning to be born within it.", 2);

		Capitulo NieRAutomata_T1_C3  = new Capitulo(NieRAutomata_T1, "break ti[M]e", 			"Pod 042 - Report:\r\n" + //
																										"\r\n" + //
																										"YoRHa units 2B and 9S rescued a Resistance group from being attacked by machine lifeforms. The Resistance unit leader identified as <Lily> guides them to their base of residence, the Resistance Camp.\r\n" + //
																										"\r\n" + //
																										"During their stay at the camp, units 2B and 9S obtain information about unique machine lifeforms from the Resistance member identified as <Jackass>.\r\n" + //
																										"It is determined that this information exists in the same parameters of the investigation mission assigned to 2B and 9S.\r\n" + //
																										"Proposal: Begin exploration of the Desert Zone with Jackass.", 3);

		Capitulo NieRAutomata_T1_C4  = new Capitulo(NieRAutomata_T1, "a mountain too [H]igh", 	"Pod 153 - Report:\r\n" + //
																										"\r\n" + //
																										"During the investigation in the Desert Zone, several unique entities not found in previously recorded data were confirmed. Entities confirmed were characterized as \"machine lifeforms capable of speech\" and \"machine lifeforms that closely resemble androids\". Details uploaded to the server.\r\n" + //
																										"\r\n" + //
																										"In addition, both units 2B and 9S received orders from Command to search for YoRHa Soldiers.\r\n" + //
																										"It appears that contact has been lost with the missing unit during their ground mission.\r\n" + //
																										"Origin of black box signal has been detected. Proposal: Begin search for the target at the corresponding point.", 4);

		Capitulo NieRAutomata_T1_C5  = new Capitulo(NieRAutomata_T1, "mave[R]ick", 				"Pod 042 - Report:\r\n" + //
																										"\r\n" + //
																										"Verifying the trade information between the Resistance and machine lifeforms in the City Ruins.\r\n" + //
																										"\r\n" + //
																										"Despite the hostile relationship between androids and machine lifeforms, it appears the machine lifeforms they are trading with hold no hostile responses or combat intentions.\r\n" + //
																										"\r\n" + //
																										"This unit will investigate the area together with 2B and 9S, who Lily requested to transport materials to the machine lifeforms.", 5);

		Capitulo NieRAutomata_T1_C6  = new Capitulo(NieRAutomata_T1, "[L]one wolf", 				"Pod 042 and Pod 153 - Joint Report:\r\n" + //
																										"\r\n" + //
																										"Data discovered. Presumed to be part of a record from the past.\r\n" + //
																										"Transferring to shared server.\r\n" + //
																										"\r\n" + //
																										"____________________________________________________\r\n" + //
																										"December 8, 11941.\r\n" + //
																										"I've encountered androids dispatched to Earth on a top-secret mission.\r\n" + //
																										"These unknown androids call themselves <YoRHa Soldiers>.\r\n" + //
																										"Command that abandoned us. YoRHa Soldiers with nothing but secrets and full of mysteries.\r\n" + //
																										"But you can't protect your family if all you do is doubt.\r\n" + //
																										"\r\n" + //
																										"…I hope my choice, our battle, will be a breakthrough in this long war of attrition.", 6);

		Capitulo NieRAutomata_T1_C7  = new Capitulo(NieRAutomata_T1, "[Q]uestionable actions", 	"Pod 153 - Report:\r\n" + //
																										"\r\n" + //
																										"In the mission so far, this unit has encountered many abnormal machine lifeforms – some of which have even progressed as far as engaging in combat.\r\n" + //
																										"Each individual's information has been uploaded to the server as designed.\r\n" + //
																										"Proposal: In preparation for future encounters, request information analysis and sharing of results in the Bunker.\r\n" + //
																										"\r\n" + //
																										"―――Entity Identification Signal: <Pascal> response confirmed―――\r\n" + //
																										"\r\n" + //
																										"A member of a machine lifeform village appears to have gone missing.\r\n" + //
																										"Proposal: Begin reconnaissance in the Forest Kingdom area.", 7);

		Capitulo NieRAutomata_T1_C8  = new Capitulo(NieRAutomata_T1, "aji wo [K]utta?", 			"Pod 042 - Report:\r\n" + //
																										"\r\n" + //
																										"In the Forest Kingdom area, wanted YoRHa unit identified as <A2> was discovered.\r\n" + //
																										"\r\n" + //
																										"The fugitive unit in question abandoned her mission and continues to run away to this day. She is designated as a highly dangerous individual.\r\n" + //
																										"\r\n" + //
																										"Units 2B and 9S receive orders to track and investigate A2 from Command.\r\n" + //
																										"Proposal: Contact the Resistance and Pascal to begin collecting information.", 8);

		Capitulo NieRAutomata_T1_C9  = new Capitulo(NieRAutomata_T1, "hun[G]ry for knowledge", 	"Pod 153 - Report:\r\n" + //
																										"\r\n" + //
																										"Communication lost with 9S, the individual of this unit's accompanying support.\r\n" + //
																										"\r\n" + //
																										"While acting alone, 9S encountered a unit appearing to be <A2> in an abandoned building located in the Flooded City and began pursuit.\r\n" + //
																										"Strong jamming signal emission confirmed throughout the area at the time.\r\n" + //
																										"Hypothesis: 9S fell into an untraceable status while trying to restore communications.\r\n" + //
																										"\r\n" + //
																										"Proposal: Call 2B for assistance and begin searching.", 9);

		Capitulo NieRAutomata_T1_C10 = new Capitulo(NieRAutomata_T1, "over[Z]ealous", 			"Pod 042 - Report:\r\n" + //
																										"\r\n" + //
																										"YoRHa Unit 2B has successfully defeated the abnormal machine lifeform identified as <Eve>.\r\n" + //
																										"Hypothesis: Machine lifeforms in the region will gradually weaken due to the loss of Eve, the core of the machine network.\r\n" + //
																										"\r\n" + //
																										"9S returns to the Bunker for body repairs and data overhaul.\r\n" + //
																										"Hereafter, until the completion of 9S's repairs, 2B—together with this unit and Pod 153—will carry out the order to destroy <Adam> assigned by Command.", 10);

		Capitulo NieRAutomata_T1_C11 = new Capitulo(NieRAutomata_T1, "head[Y] battle", 			"Pod 153 - Report:\r\n" + //
																										"\r\n" + //
																										"Construction of new machine lifeform network confirmed. Hypothesis: The entity <Adam> is presumed to be the core.\r\n" + //
																										"\r\n" + //
																										"A Goliath-class machine lifeform has begun a hostile rampage during the search for Adam. Analysis: The same phenomenon has been confirmed simultaneously in various regions on the ground.\r\n" + //
																										"\r\n" + //
																										"Command has issued an order for all YoRHa Soldiers to intercept the enemy machine lifeforms.\r\n" + //
																										"\r\n" + //
																										"Many YoRHa Soldiers and Resistance members are currently reporting engagements with hostiles and requesting assistance.\r\n" + //
																										"\r\n" + //
																										"Proposal: Commence operation for Resistance Camp rescue with 2B.", 11);

		Capitulo NieRAutomata_T1_C12 = new Capitulo(NieRAutomata_T1, "flowers for m[A]chines", 	"Pod 042 and Pod 153 - Joint Report:\r\n" + //
																										"\r\n" + //
																										"Pod 042\r\n" + //
																										"Report: The rampant entity <Adam> was hacked by 9S, intercepted by the Resistance, and was bombarded with support fire from the Bunker. It was ■■■■\r\n" + //
																										"\r\n" + //
																										"―――Communication error―――\r\n" + //
																										"\r\n" + //
																										"―――Communication error―――\r\n" + //
																										"\r\n" + //
																										"―――Communication with Pod 042 is being restored 12 seconds until restoration―――\r\n" + //
																										"\r\n" + //
																										"―――Communication with Pod 153 is being restored 15 seconds until restoration―――\r\n" + //
																										"\r\n" + //
																										"Pod 042 to Pod 153: Temporary communication error occurred due to large-scale ground and air attacks. Currently undergoing restoration processes.\r\n" + //
																										"Pod 153 to Pod 042: Same failure for this unit confirmed. No abnormalities in recording systems.\r\n" + //
																										"Pod 042 to Pod 153: Agreed. Currently, there are no system abnormalities in this unit. Continuing to carry out investigation mission and recording of 2B, target of this unit's accompanying support.\r\n" + //
																										"Pod 153 to Pod 042: Agreed. This unit will also carry out missions and record 9S.", 12);

		NieRAutomata.anyadirTemporada(NieRAutomata_T1);
		NieRAutomata_T1.anyadirCapitulo(NieRAutomata_T1_C1);
		NieRAutomata_T1.anyadirCapitulo(NieRAutomata_T1_C2);
		NieRAutomata_T1.anyadirCapitulo(NieRAutomata_T1_C3);
		NieRAutomata_T1.anyadirCapitulo(NieRAutomata_T1_C4);
		NieRAutomata_T1.anyadirCapitulo(NieRAutomata_T1_C5);
		NieRAutomata_T1.anyadirCapitulo(NieRAutomata_T1_C6);
		NieRAutomata_T1.anyadirCapitulo(NieRAutomata_T1_C7);
		NieRAutomata_T1.anyadirCapitulo(NieRAutomata_T1_C8);
		NieRAutomata_T1.anyadirCapitulo(NieRAutomata_T1_C9);
		NieRAutomata_T1.anyadirCapitulo(NieRAutomata_T1_C10);
		NieRAutomata_T1.anyadirCapitulo(NieRAutomata_T1_C11);
		NieRAutomata_T1.anyadirCapitulo(NieRAutomata_T1_C12);

		Artista art_1 = new Artista("Yui Ishikawa");
		art_1.anyadirRol(Rol.ACTOR);

		NieRAutomata.anyadirArtista(art_1);

		sr.save(NieRAutomata);

		return NieRAutomata;
	}

	@Transactional
	public void remove(Usuario usr){
		Usuario u = ur.getUsuarioByNombre(usr.getNombre()).orElse(null);
		ur.delete(u);
	}

	@Transactional 
	public String getFirstSerie(String prompt){
		List<Serie> resultados = sr.findBynombreSerieLike(prompt + "%");

		if(resultados.size() == 0) return null;
		return resultados.get(0).getNombreSerie();
	}
	
	@Override
	public void run(String... args) throws Exception {

		Serie NieR = addNieR();
		
		Usuario Ismael = new Usuario("Ismael", "abcd", new IBAN());

		Serie OnePiece = new Serie("One Piece", "Lorem Ipsum", Categoria.STANDARD);

		Temporada T1_OP = new Temporada(OnePiece, 1);

		Capitulo C1_OP = new Capitulo(T1_OP, "El rey de los piratas", "???", 1);

		Ismael.anyadirSerie(OnePiece);
		Ismael.verCapitulo(C1_OP, true);

		persist(Ismael, OnePiece);

		Ismael.anyadirSerie(NieR);
		Ismael.verCapitulo(NieR.getTemporadaByNumber(1).getCapituloByNumber(1), true);
		Ismael.verCapitulo(NieR.getTemporadaByNumber(1).getCapituloByNumber(5), true);
		Ismael.verCapitulo(NieR.getTemporadaByNumber(1).getCapituloByNumber(12), true);


		ur.saveAndFlush(Ismael);

		//TODO: Terminar la capa de persistencia para permitir borrados y generados adecuados.
		//remove(Ismael);

		System.out.println("Coste en centimos: " + Integer.toString(Ismael.getFacturas().get(0).getImporteTotalCent()));
		System.out.println(getFirstSerie("O"));
		System.out.println(ar.getBynombre("Yui Ishikawa").orElse(null).getNombre());
		

		System.out.println("Application feeded");
	}
}
