import java.util.List;

import Exceptions.FehlgeschlageneReservierungException;
import Exceptions.UnerlaubteZimmerauswahlException;
import FachlicheTypen.AnforderungslisteTyp;
import FachlicheTypen.BooleanTyp;
import FachlicheTypen.ReservierungsNrTyp;
import FachlicheTypen.ZahlungsinformationsTyp;
import FachlicheTypen.ZimmerNrTyp;

public interface IBelegungKomponenteServices {
	
	BooleanTyp gibtFreieAnforderungserf�llendeZimmer(
			AnforderungslisteTyp anforderungsliste);
	
	List<ZimmerNrTyp> holeErf�llendeZimmerHoherPreisklasse(
			AnforderungslisteTyp anforderungsliste)
			throws UnerlaubteZimmerauswahlException;
	
	BooleanTyp validiereZahlunginformation(
			ZahlungsinformationsTyp zahlungsinformation);
	
	ReservierungsNrTyp reserviereZimmerMitAnforderungen(
			AnforderungslisteTyp anforderungsliste)
			throws FehlgeschlageneReservierungException;
	
	ReservierungsNrTyp reserviereAusgew�hltesZimmer(ZimmerNrTyp zimmerNr)
			throws FehlgeschlageneReservierungException;
	
}
