package KomponentenServices;
import java.util.List;

import Exceptions.GastNichtGefundenException;
import Exceptions.GastRegistrierungFehlgeschlagenException;
import Exceptions.Ung�ltigeReservierendeG�steException;
import FachlicheTypen.BooleanTyp;
import FachlicheTypen.GastNrTyp;
import FachlicheTypen.NameTyp;
import FachlicheTypen.ReserveriendeG�steNrTyp;

public interface IGastKomponentenServices {
	GastNrTyp registriereGast(NameTyp vorname, NameTyp nachname,
			BooleanTyp isErwachsen, BooleanTyp hatBehinderung)
			throws GastRegistrierungFehlgeschlagenException;

	GastNrTyp holeGast(NameTyp vorname, NameTyp nachname,
			BooleanTyp isErwachsen, BooleanTyp hatBehinderung)
			throws GastNichtGefundenException;

	ReserveriendeG�steNrTyp validiereG�ste(List<GastNrTyp> reservierendeG�ste)
			throws Ung�ltigeReservierendeG�steException;
}
