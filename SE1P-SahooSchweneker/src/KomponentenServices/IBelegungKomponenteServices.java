package KomponentenServices;
import Exceptions.FehlgeschlageneReservierungException;
import Exceptions.UnerlaubteZimmerauswahlException;
import FachlicheTypen.AnforderungslisteTyp;
import FachlicheTypen.BooleanTyp;
import FachlicheTypen.ReservierungsNrTyp;
import FachlicheTypen.ZahlungsinformationsTyp;
import FachlicheTypen.ZimmerNrListeTyp;
import FachlicheTypen.ZimmerNrTyp;

public interface IBelegungKomponenteServices {
	
	BooleanTyp gibtFreieAnforderungserfüllendeZimmer(
			AnforderungslisteTyp anforderungsliste);
	
	ZimmerNrListeTyp holeErfüllendeZimmerHoherPreisklasse(
			AnforderungslisteTyp anforderungsliste)
			throws UnerlaubteZimmerauswahlException;
	
	BooleanTyp validiereZahlunginformation(
			ZahlungsinformationsTyp zahlungsinformation);
	
	ReservierungsNrTyp reserviereZimmerMitAnforderungen(
			AnforderungslisteTyp anforderungsliste)
			throws FehlgeschlageneReservierungException;
	
	ReservierungsNrTyp reserviereAusgewähltesZimmer(ZimmerNrTyp zimmerNr)
			throws FehlgeschlageneReservierungException;
	
}
