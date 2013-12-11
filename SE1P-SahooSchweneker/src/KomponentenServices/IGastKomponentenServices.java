package KomponentenServices;
import java.util.List;

import Exceptions.GastNichtGefundenException;
import Exceptions.GastRegistrierungFehlgeschlagenException;
import Exceptions.UngültigeReservierendeGästeException;
import FachlicheTypen.BooleanTyp;
import FachlicheTypen.GastNrTyp;
import FachlicheTypen.NameTyp;
import FachlicheTypen.ReserveriendeGästeNrTyp;

public interface IGastKomponentenServices {
	GastNrTyp registriereGast(NameTyp vorname, NameTyp nachname,
			BooleanTyp isErwachsen, BooleanTyp hatBehinderung)
			throws GastRegistrierungFehlgeschlagenException;

	GastNrTyp holeGast(NameTyp vorname, NameTyp nachname,
			BooleanTyp isErwachsen, BooleanTyp hatBehinderung)
			throws GastNichtGefundenException;

	ReserveriendeGästeNrTyp validiereGäste(List<GastNrTyp> reservierendeGäste)
			throws UngültigeReservierendeGästeException;
}
